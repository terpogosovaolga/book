package classes;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Basket {

    public Basket(long id, long userId, Double sum, Date date, Boolean del) {
            this.id = id;
            this.userId = userId;
            this.sum=sum;
            dateOfSale = date;
            delievered=del;
    }


    @NotNull
    Long id;

    @NotNull
    Long userId;

    @NotNull
    Double sum;

    Date dateOfSale;

    Boolean delievered;
}
