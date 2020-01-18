package classes;

public class Attribute {
    private String name;
    private String value;
    public Attribute(String n, String v) {
        name = n; value=v;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRightValue() {

    }
}
