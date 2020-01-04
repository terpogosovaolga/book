package com.test.pluto;

import Services.IBasketParagraphService;
import Services.IBasketService;
import Services.IBookService;
import Services.IUserService;
import classes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    public ModelAndView getSelections() throws Exception {
        Map<String, Object> models = new HashMap<>();
        models.put("popularBooks", myBook.getPopularBooks());
        models.put("newArrivals", myBook.getNewArrivals());
        models.put("poems", myBook.getPoems());
        models.put("novels", myBook.getNovels());
        models.put("small", myBook.getSmallBooks());
        models.put("tolstoy", myBook.getBooksOfAuthor("Лев","Толстой"));
/*
        User user = getUser(session);
        try {
            if (!user.equals(null)) {

            }
        }
        catch(NullPointerException e){

            makeAnonUser(session);
        }*/
        return new ModelAndView("index", "models", models);

    }

    /*@GetMapping(value = "/search/{nameOfParam}/{valueOfParam}")  // доступен со страницы каталога
    @ResponseBody
    public ModelAndView getBooksWithParams(HttpSession session, @PathVariable("nameOfParam") String key, @PathVariable("valueOfParam") String value) throws Exception {
        addSearchParamsInSession(session, key, value);
        List<Book> booksFromDao = myBook.getBooksWithParams((Map<String, String>) session.getAttribute("params"));
        System.out.println("WE GOT " + booksFromDao.size() + " BOOKS");
        return new ModelAndView("catalog", "result", booksFromDao);
    }*/

    /*@GetMapping(value="/search/author/{surname}/{name}")
    @ResponseBody
    public ModelAndView getBooksOfAuthor(HttpSession session, @PathVariable("surname") String surname, @PathVariable("name") String name) {
        return new ModelAndView("catalog", "result", myBook.getBooksOfAuthor(name, surname));
    }*/
/*
    @RequestMapping(value = "/deleteParam/{nameOfParam}", method={RequestMethod.GET})  // доступен со страницы каталога
    public ModelAndView removeParam(HttpSession session, @PathVariable("nameOfParam") String key) throws Exception {
        Map<String, String> map = (Map<String, String>) session.getAttribute("params");
        map.remove(key);
        List<Book> booksFromDao = myBook.getBooksWithParams((Map<String, String>) session.getAttribute("params"));
        System.out.println("WE GOT " + booksFromDao.size() + " BOOKS");

      /*  if (books.size()<1)
            books = booksFromDao;
        else {
            List <Book> oldBooks = books;
            try {
                for (Book ob : oldBooks) {
                    boolean is = false;
                    for (Book b : booksFromDao) {
                        if (b.equals(ob)) {
                            is = true;
                            break;
                        }
                    }
                    if (!is)
                        books.remove(ob);
                }
            }
            catch(NullPointerException e){}
        }
        return new ModelAndView("catalog", "result", booksFromDao);
    }*/



    @GetMapping(value = "/search/{search}") //доступен со всех страниц
    @ResponseBody
    public ModelAndView getBooksAfterSearch(@PathVariable("search") String search) throws Exception {
        System.out.println("you are in search-method!!!!!");
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
    @RolesAllowed(value={"ROLE_ADMIN"})
    @GetMapping(value="/admin/addBook")
    public String displayAdmin_addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book",book);
        return "admin";
    }
    @RolesAllowed(value={"ROLE_ADMIN"})
    @RequestMapping(value="/admin/addBook", method=RequestMethod.POST)
    public String admin_addBook(@ModelAttribute("book") Book book, Model model) {
        myBook.admin_addBook(book);
        model.addAttribute("message", "Книга добавлена");
        return displayAdmin_addBook(model);
    }
    @RolesAllowed(value={"ROLE_ADMIN"})
    @GetMapping(value="admin/editBook/{id}")
    public String displayAdmin_editBook(Model model, @PathVariable("id") Long book_id) {
        Book book = myBook.getBookById(book_id);
        model.addAttribute("book", book);
        return "editBook";
    }
    /*
    @RolesAllowed(value={"ROLE_ADMIN"})
    @PostMapping(value="admin/editBook/{id}")
    public ModelAndView admin_editBook(HttpSession session, @PathVariable("id") Long book_id, @ModelAttribute Book book) throws Exception {
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
        return getSelections(session);
    }*/


    private void updateCostOfBasket(Long basketId, Long userId) {
        List<BasketParagraphBooked> allBp = myBp.getAllBasketParagraphsOfBasket(basketId);
        Double cout = 0.0;
        for (BasketParagraphBooked bpb : allBp)
        {
            cout+=bpb.getSum();
        }
        myBasket.updateCostOfBasket(userId, cout);
    }
/*
    private User getUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        User anonUser = (User) session.getAttribute("anonId");
        try {
            if (!user.equals(null)) {
                return user;   //пользователь уже вошел
            }
        }
        catch (NullPointerException e) {// пользоваль не выполнял вход, но возможно сессия уже заведена
            try {
                if (!anonUser.equals(null)) {
                    System.out.println("WE HAVE ANON USER IN SESSION!!!!!");
                    return anonUser;  //пользователь не вошел, но сессия заведна
                }
            } catch (NullPointerException ex) {
                return null; // сессию не заводили
            }
        }
        return null;
    }

    private void makeAnonUser(HttpSession session){
        System.out.println("making user");
        long num = myUser.getNumberOfUsers(); // максимальный айди в базе на данный момент
        String email = "email" + (num + 1);
        Long id = myUser.register(0, email, "name", "fullName", "password");
        System.out.println("registrated");
        User user = myUser.login(email, "password");
        System.out.println("id of logined user"+user.getId());
        session.setAttribute("anonId", user); // создаем юзера с id=num+1
        System.out.println(session.getAttribute("anonId"));
    }

    private void addSearchParamsInSession(HttpSession session, String key, String value) {
        if (session.getAttribute("params")==null) {
            Map<String, String> params = new HashMap<>();
            params.put(key, value);
            session.setAttribute("params", params);
        }
        else {
            Map<String, String> params = (Map<String, String>) session.getAttribute("params");
            params.put(key, value);
            session.setAttribute("params", params);
        }
    }*/
}

