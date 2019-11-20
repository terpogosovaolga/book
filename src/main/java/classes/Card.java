package classes;

import org.springframework.web.bind.annotation.ModelAttribute;

public class Card {

    public Card(String number, String name, String cvc){
        numberOfCard=number;
        this.name=name;
        this.cvc=cvc;
    }
    public Card(){}

    private String numberOfCard;
    private String name;
    private String cvc;

    public String getNumberOfCard() {
        return numberOfCard;
    }
    @ModelAttribute("numberOfCard")
    public void setNumberOfCard(String numberOfCard) {
        this.numberOfCard = numberOfCard;
    }

    public String getName() {
        return name;
    }

    @ModelAttribute("name")
    public void setName(String name) {
        this.name = name;
    }

    public String getCvc() {
        return cvc;
    }
    @ModelAttribute("cvc")
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
}
