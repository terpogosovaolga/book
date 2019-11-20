package com.test.pluto;

import Services.IBasketParagraphService;
import Services.IBasketService;
import Services.IBookService;
import Services.IUserService;
import classes.*;
import org.hsqldb.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.beans.Beans;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/")
@RequestMapping()
public class BookController {


    @Autowired
    private IBookService myBook;
    @Autowired
    private IUserService myUser;
    @Autowired
    private IBasketParagraphService myBp;
    @Autowired
    private IBasketService myBasket;

    @Autowired
    public BookController(IBookService myBook) {
        this.myBook = myBook;
    }

    @RequestMapping(name="defaultRequest", method=RequestMethod.GET)
    @ResponseBody
    public ModelAndView getSelections(HttpSession session) throws Exception {
        Map<String, Object> models = new HashMap<>();
        models.put("popularBooks", myBook.getPopularBooks());
        models.put("newArrivals", myBook.getNewArrivals());
        try {
            if (!session.getAttribute("anonId").equals(null) && !session.getAttribute("user").equals(null)) {

            }
        }
        catch(NullPointerException e)
        {
            long num = myUser.getNumberOfUsers(); // максимальный айди в базе на данный момент
            String email = "email" + Long.toString(num + 1);
            myUser.register(0, email, "name", "fullName", "password");
            long id = myUser.getNumberOfUsers(); // id нашего пользователя
            User user = myUser.login("email" + id, "anonpassword");
            session.setAttribute("anonId", user); // создаем юзера с id=num+1
        }
        return new ModelAndView("index", "models", models);

    }

    @GetMapping(value = "/search/param")  // доступен со страницы каталога
    @ResponseBody
    public ModelAndView getBooksWithParams(@RequestParam Map<String, Object> attrs) throws Exception {

        return new ModelAndView("catalog", "result", myBook.getBooksWithParams(attrs));  // выкидывает на страницу главного каталога с результатами поиска
    }

    @GetMapping(value = "/search/{search}") //доступен со всех страниц
    @ResponseBody
    public ModelAndView getBooksAfterSearch(@RequestParam @PathVariable("search") String search) throws Exception {
        return  new ModelAndView("catalog", "result", myBook.getBooksAfterSearch(search));   // выкидывает на страницу главного каталога с результатами поиска
    }

    @GetMapping(value = "/book/{id}") //доступен со всех страниц, с которых можно выбрать книгу
    @ResponseBody
    public ModelAndView getBookById(@PathVariable("id") Long id) throws Exception { // returns sessionId

        System.out.println(id);
        ModelAndView model = new ModelAndView("book", "result", myBook.getBookById(id));
        myBook.increaseNumberOfWatchings(id);

        return model;   // выкидывает на страницу выбранной книги
    }

    @GetMapping(value = "/catalog") //выполняется при переходе на каталог
    @ResponseBody
    public ModelAndView getAllBooks() throws Exception {
        ModelAndView model = new ModelAndView("catalog", "result", myBook.getBooks());

        return model;
    }

    @GetMapping(value="/admin/addBook")
    public String displayAdmin_addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book",book);
        return "admin";
    }

    @RequestMapping(value="/admin/addBook", method=RequestMethod.POST)
    public String admin_addBook(@ModelAttribute("book") Book book, Model model) {
        myBook.admin_addBook(book);
        model.addAttribute("message", "Книга добавлена");
        return "admin";
    }

    @GetMapping(value="editBook/{id}")
    public String displayAdmin_editBook(Model model, @PathVariable("id") Long book_id) {
        Book book = myBook.getBookById(book_id);
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping(value="editBook/{id}")
    public String admin_editBook(Model model, @PathVariable("id") Long book_id, @ModelAttribute Book book) {
        Book preBook = myBook.getBookById(book_id);
        // МЕНЯЕМ ЦЕНЫ В БАСКЕТПАРАГРАФАХ И БАСКЕТАХ
        myBook.updateBook(book);
        if (preBook.getCout()!=book.getCout())
        {
            List <BasketParagraph> listOfBP = myBp.getAllBasketParagraphsWithBook(book_id);
            for (BasketParagraph bp : listOfBP)
            {
                myBp.setPrice(bp.getId(), book.getCout());
                Basket basket = myBasket.getBasketByBasketId(bp.getBasketId());
                updateCostOfBasket(basket.getId(), basket.getUserId());
            }
        }
        return "index";
    }

    private void updateCostOfBasket(Long basketId, Long userId) {
        List<BasketParagraphBooked> allBp = myBp.getAllBasketParagraphsOfBasket(basketId);
        Double cout = 0.0;
        for (BasketParagraphBooked bpb : allBp)
        {
            cout+=bpb.getSum();
        }
        myBasket.updateCostOfBasket(userId, cout);
    }
}

