package com.test.pluto;

import Services.IBasketParagraphService;
import Services.IBasketService;
import Services.IBookService;
import Services.IUserService;
import classes.Attribute;
import models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Attr;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/")
@RequestMapping()
public class BookController {

    private final IBookService myBook;
    private final IUserService myUser;
    private final IBasketParagraphService myBp;
    private final IBasketService myBasket;
    private List<Attribute> attrs = new ArrayList<>();

    public BookController(IBookService myBook, IUserService myUser, IBasketParagraphService myBp, IBasketService myBasket) {
        this.myBook = myBook;
        this.myUser = myUser;
        this.myBp = myBp;
        this.myBasket = myBasket;
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
        return new ModelAndView("index", "models", models);

    }
   /* @RequestMapping(value = "/deleteParam/{nameOfParam}", method={RequestMethod.GET})  // доступен со страницы каталога
    public ModelAndView removeParam(HttpSession session, @PathVariable("nameOfParam") String key) throws Exception {
        Map<String, String> map = (Map<String, String>) session.getAttribute("params");
        map.remove(key);
        List<Book> booksFromDao = myBook.getBooksWithParams((Map<String, String>) session.getAttribute("params"));
        System.out.println("WE GOT " + booksFromDao.size() + " BOOKS");

        if (books.size()<1)
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

        Book book = myBook.getBookById(id);
        ModelAndView model = new ModelAndView("book", "result", book);
        myBook.view(book);
        return model;   // выкидывает на страницу выбранной книги
    }

    @GetMapping(value="/catalog/search/{nameParametr}/{valueParametr}")
    @ResponseBody
    public ModelAndView getBooksAfterSearchWithParams(@PathVariable String valueParametr, @PathVariable String nameParametr) throws Exception {
        Attribute newAttr = new Attribute(nameParametr, valueParametr);
        System.out.println(newAttr.toString());
        if (attrs.contains(newAttr)){
            Attribute delete = null;
            for (Attribute a : attrs) {
                if (a.equals(newAttr)){
                    delete = a;
                    System.out.println("this attribute was deleted coz it has already been in out list");
                }
            }
            attrs.remove(delete);
        }
        else attrs.add(newAttr);
        System.out.println("count of attrs now: " + attrs.size());
        if (attrs.size()!=0) {
            ModelAndView model = new ModelAndView("catalog");
            Map<String, Object> map = new HashMap<>();
            map.put("result", myBook.getBooksAfterSearchWithParams(attrs));
            map.put("attrs", attrs);
            model.addAllObjects(map);
            return model;
        }
        return getAllBooks();
    }

    @GetMapping(value = "/catalog") //выполняется при переходе на каталог
    @ResponseBody
    public ModelAndView getAllBooks() throws Exception {
        ModelAndView model = new ModelAndView("catalog", "result", myBook.getAllBooks());
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
        myBook.save(book);
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

    @RolesAllowed(value={"ROLE_ADMIN"})
    @PostMapping(value="admin/editBook/{id}")
    public ModelAndView admin_editBook(@PathVariable("id") Long book_id, @ModelAttribute Book book) throws Exception {
        myBook.update(book);
        return getSelections();
    }

}

