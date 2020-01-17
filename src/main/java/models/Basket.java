package models;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Baskets")
public class Basket  implements Serializable {

    public Basket(long id, long userId, Double sum, Date date, Boolean del) {
            this.id = id;
            this.userId = userId;
            this.sum=sum;
            dateOfSale = date;
            delievered=del;
    }

    public Basket() {}


    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Basket_id")
    private Long id;

    @NotNull
    @Column(name="User_id")
    private Long userId;

    @NotNull
    @Column(name="Cost")
    private Double sum;

    @Column(name="Date_of_purchase")
    private Date dateOfSale;

    @Column(name="Delievered")
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

    public boolean equals(Basket b) {
        if (b==null) return false;
        if (b==this) return true;
        if (b.getId() != this.id) return false;
        if (b.getUserId() != this.userId) return false;
        return (b.getSum() != getSum()) ;
    }

    public String toString() {
        return "id:" + getId() + "\n user id: " + getUserId() + "\n sum: " + getSum() + "\n date of purchase: " + getDateOfSale() + "delievered: " + getDelievered();
    }
}
