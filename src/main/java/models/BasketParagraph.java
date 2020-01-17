package models;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="basketparagraphs")
public class BasketParagraph implements Serializable {

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name="BasketParagraph_id")
    private Long id;

    @NotNull
    @Column(name="Basket_id")
    private Long basketId;

    @NotNull
    @Column(name="Book_id")
    private Long bookId;

    @NotNull
    @Column(name="Count")
    private int numberOfBooks;

    @NotNull
    @Column(name="Cost")
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
        if(bp==null){
            return false;
        }
        if(bp==this)
            return true;
        return (bp.getBasketId() == getBasketId() && bp.getBookId() == getBookId());
    }

    public String toString() {
        return "id: " + getId() + "\n basket id: " + getBasketId() + "\n book id: " + getBookId() + "\n count of books: " + getNumberOfBooks() + "\n sum: " + getSum();
    }
}
