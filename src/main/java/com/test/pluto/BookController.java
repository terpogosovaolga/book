package com.test.pluto;

import Services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller("/")
@RequestMapping()
public class BookController {

  /*  public BookController(IBookService bs) {
        myBook = bs;
    }*/

    @Autowired
    private IBookService myBook;

    @Autowired
    public BookController(IBookService myBook) {
        this.myBook = myBook;
    }


    @GetMapping(name="defaultRequest")
    @ResponseBody
    public ModelAndView getPopularBooks() throws Exception {
        System.out.println("you are in getpopularbooks!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return new ModelAndView("index", "popularBooks", myBook.getPopularBooks());
    }


}


/*@GetMapping(value = "search/param/") // при входе в учетную запись
    @ResponseBody
    public ModelAndView getBooksWithParams(@RequestParam Map<String, Object> attrs) throws Exception {
       // myBook.getBooksWithParams(attrs);
        ModelAndView model = new ModelAndView("catalog", "result", myBook.getBooksWithParams(attrs));

        return model;   // выкидывает на страницу главного каталога с результатами поиска
    }

    @GetMapping(value = "search/{search}") // при входе в учетную запись
    @ResponseBody
    public ModelAndView getBooksAfterSearch(@RequestParam @PathVariable("search") String search) throws Exception {

        ModelAndView model = new ModelAndView("catalog", "result", myBook.getBooksAfterSearch(search));
        //model.addObject(myBook.getBooksAfterSearch(search));
        return model;   // выкидывает на страницу главного каталога с результатами поиска
    }
    @GetMapping(value = "book/{id}") // при входе в учетную запись
    @ResponseBody
    public ModelAndView getBookById(@RequestParam @PathVariable("id") Long id) throws Exception { // returns sessionId

        ModelAndView model = new ModelAndView("book", "result", myBook.getBookById(id));

        return model;   // выкидывает на страницу выбранной книги
    }*/
    /*@GetMapping(value = "catalog") // при входе в учетную запись
    @ResponseBody
    public ModelAndView getAllBooks() throws Exception {
        ModelAndView model = new ModelAndView("catalog", "result", myBook.getBooks());

        return model;   // выкидывает на страницу выбранной книги
    }*/