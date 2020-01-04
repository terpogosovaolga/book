package classes;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;

public class BasketParagraph {

    public BasketParagraph(long id, long basketId, long bookId, int number, Double sum) {
        this.id = id;
        this.basketId = basketId;
        this.bookId = bookId;
        this.numberOfBooks = number;
        this.sum = sum;
    }

    public BasketParagraph(long basketId, long bookId) {
        this.basketId = basketId;
        this.bookId = bookId;
    }

    public BasketParagraph() {}
    @NotNull
    private Long id;

    @NotNull
    private Long basketId;

    @NotNull
    private Long bookId;

    @NotNull
    private int numberOfBooks;

    @NotNull
    private Double sum;

    public BasketParagraph(Long bookId) { this.bookId = bookId;
    }


    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }


    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(@NotNull Long basketId) {
        this.basketId = basketId;
    }


    public Long getBookId() {
        return bookId;
    }

    public void setBookId(@NotNull Long bookId) {
        this.bookId = bookId;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    @ModelAttribute("numberOfBooks")
    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }


    public Double getSum() {
        return sum;
    }

    @ModelAttribute("sum")
    public void setSum( Double sum) {
        this.sum = sum;
    }

    public boolean equals(BasketParagraph bp) {
        if(this==null){
            throw new NullPointerException("this object is null");
        }
        if(bp==null){
            return false;
        }
        if(bp==this)
            return true;
        return (bp.getBasketId() == getBasketId() && bp.getBookId() == getBookId());
    }
}
