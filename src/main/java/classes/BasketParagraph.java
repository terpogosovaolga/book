package classes;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class BasketParagraph {

    public BasketParagraph(long id, long basketId, long bookId, int number, Double sum) {
        this.id = id;
        this.basketId = basketId;
        this.bookId = bookId;
        this.numberOfBooks = number;
        this.sum = sum;
    }
    @NotNull
    Long id;

    @NotNull
    Long basketId;

    @NotNull
    Long bookId;

    @NotNull
    int numberOfBooks;

    @NotNull
    Double sum;
}
