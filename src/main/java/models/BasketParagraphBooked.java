package models;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class BasketParagraphBooked   implements Serializable {
    public BasketParagraphBooked() {}

    public BasketParagraphBooked(BasketParagraph bp, Book book) {
        id=bp.getId();
        basketId=bp.getId();
        this.book=book;
        numberOfBooks=bp.getNumberOfBooks();
        sum=bp.getSum();
    }
    @NotNull
    private Long id;

    @NotNull
    private Long basketId;

    @NotNull
    private Book book;

    @NotNull
    private int numberOfBooks;

    @NotNull
    private Double sum;

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    @NotNull
    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(@NotNull Long basketId) {
        this.basketId = basketId;
    }

    @NotNull
    public Book getBook() {
        return book;
    }

    public void setBook(@NotNull Book book) {
        this.book = book;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    @NotNull
    public Double getSum() {
        return sum;
    }

    public void setSum(@NotNull Double sum) {
        this.sum = sum;
    }

    public boolean equals(BasketParagraphBooked bpb) {
        if (bpb==null) return false;
        if (bpb==this) return true;
        if (!bpb.book.equals(book)) return false;

        return(bpb.basketId != basketId);
    }
}
