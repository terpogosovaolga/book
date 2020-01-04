package Services;

import Dao.IBookDao;
import classes.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookService implements IBookService {

    @Autowired
   public BookService(IBookDao bd)
  {
     bookDao=bd;
   }

    @Autowired
    IBookDao bookDao;


    @Override
    public List<Book> getBooks() {

        return bookDao.getAllBooks();
    }

    @Override
    public Book getBookById(Long id) {
        return bookDao.getBookById(id);
    }

    @Override
    public List<Book> getPopularBooks() {
        return bookDao.getPopularBooks();
    }

    @Override
    public List<Book> getNewArrivals() {
        return bookDao.getNewArrivals();
    }

    @Override
    public List<Book> getPoems() {
        return bookDao.getPoems();
    }

    @Override
    public List<Book> getNovels() {
        return bookDao.getNovels();
    }

    @Override
    public List<Book> getSmallBooks() {
        return bookDao.getSmallBooks();
    }

    @Override
    public List<Book> getBooksOfAuthor(String surname) {
        return bookDao.getBooksOfAuthor(surname);
    }

    @Override
    public List<Book> getBooksOfAuthor(String name, String surname) {
        return bookDao.getBooksOfAuthor(name, surname);
    }

    @Override // перенести к BasketParagraph
    public void addBookToBasket(Long bookId, Integer number, Long userId) {

    }

    @Override
    public void admin_addBook(Book book) {
        System.out.println("ФАМИЛИЯ АВТОРА: " + book.getAuthorSureName());
        bookDao.admin_addBook(book);
    }

    @Override
    public void increaseNumberOfWatchings(Long book_id) {
        bookDao.increaseNumberOfWatchings(book_id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
    @Override
    public List<Book> getBooksAfterSearch(String search) {
        List<Book> allBooks = bookDao.getAllBooks();
        System.out.println("we have " + allBooks.size() + " in db");
        List<Book> result = new ArrayList<>();
        search = search.toLowerCase();
        for (Book book : allBooks)
        {
            System.out.println("search:  " + search + " name: " + book.getName() + " surname: " + book.getAuthorSureName());
            if (book.getAuthorSureName().toLowerCase().contains(search)) {
                result.add(book);
                System.out.println("we added book " + book.getName() + " SURNAME");
            }
            else if (book.getName().toLowerCase().contains(search)) {
                result.add(book);
                System.out.println("we added book " + book.getName() + " NAME");
            }
            /*else if (book.getDescription().toLowerCase().contains(search)) {
                result.add(book);
                System.out.println("we added book " + book.getName() + " DESCRIPTION");
            }*/
            else if (!search.contains(" ")) {
                String[] bookName = book.getName().toLowerCase().split(" ");
                for (int i = 0; i<bookName.length; i++)
                {
                    if (getNumberOfDifferentCharsInStrings(bookName[i].toLowerCase(), search) < search.length()*0.4) {//рассчитываем на ошибку
                        result.add(book);
                        System.out.println("we added book " + book.getName() + " ОПЕЧАТКА");
                    }
                }
            }

        }

        return result;

    }



    public int getNumberOfDifferentCharsInStrings(String s1, String s2) {
        char c1[] = s1.toCharArray();
        char c2[] = s2.toCharArray();
        int differenceBetweenLength = c1.length - c2.length; // насколько c1 больше c2
        //проверка по размеру
        if(Math.abs(differenceBetweenLength)>2)
            return Integer.MAX_VALUE;
        int count = 0;
        int length;
        if (differenceBetweenLength>=0) // c1 > c2
        {
            length = c2.length;
        }
        else length = c1.length;
        for (int i = 0; i <length; i++) {
            if (c1[i]!=c2[i])
                count++;
        }
        return count;
    }

    @Override
    public List<Book> getBooksWithParams(Map<String, String> attrs) {
        if (attrs.size()<1)
            return bookDao.getAllBooks();
        for (Map.Entry<String, String> e : attrs.entrySet())
        {
            e.setValue(setValueOfQuery(e.getKey(), e.getValue()));
        }
        return bookDao.search(attrs);
    }

    @Override
    public List<Book> getBooksWithParams(String key, String value){
        String newValue = setValueOfQuery(key, value);
        return bookDao.search(key, newValue);
    }

    public String setValueOfQuery(String key, String value) {
        System.out.println("WE HAVE " + key + ": " + value);
        if (key.equals("genre")) {
            System.out.println(key + " : " + value);
            System.out.println(value + "== novel: " + value.equals("novel"));
            if (value.equals("novel")) {
                System.out.println("NOVEL!!!!!!!!!!!!!!!!!!!!!!!");
                return "роман";
            }
            else if (value.equals("poem"))
                return "поэзия";
            else if (value.equals("detective"))
                return "детектив";
            else if (value.equals("tale"))
                return "сказки";
        } else if (key.equals("publisher")) {
            if (value.equals("eksmo"))
                return "Эксмо";
            else if (value.equals("azbuka"))
                return "Азбука";
            else if (value.equals("prosveshenie"))
                return "Просвещение";
            else if (value.equals("communizm"))
                return  "Коммунизм";
            System.out.println(key + " : " + value);
        } else if (key.equals("language")) {
            if (value.equals("russian"))
                return  "русский";
            else if (value.equals("english"))
                return "английский";
            System.out.println(key + " : " + value);
        } /*else if (key.equals("price")) {
            value = Double.parseDouble((String) value);
            System.out.println(key + " : " + value);*/
        return value;
        }

}
