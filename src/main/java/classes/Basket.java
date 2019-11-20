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

    public Basket() {}


    @NotNull
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Double sum;

    private Date dateOfSale;

    private Boolean delievered;


    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    @NotNull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull Long userId) {
        this.userId = userId;
    }

    @NotNull
    public Double getSum() {
        return sum;
    }

    public void setSum(@NotNull Double sum) {
        this.sum = sum;
    }

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public Boolean getDelievered() {
        return delievered;
    }

    public void setDelievered(Boolean delievered) {
        this.delievered = delievered;
    }
}
