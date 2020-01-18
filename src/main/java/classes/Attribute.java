package classes;

public class Attribute {
    private String name;
    private String value;
    public Attribute(String n, Object v) {
        name = n; value=(String)v;
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
        if (value.equals("novel")) return "роман";
        if (value.equals("poem")) return "стихи";
        if (value.equals("tale")) return "сказка";
        if (value.equals("detective")) return "детектив";
        else return value;
    }


    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) return false;
        if (o==null) return false;
        Attribute a = (Attribute) o;
        if (a.getValue() != this.getValue()) return false;
        return a.getName() == this.getName();
    }

    public String toString() {
        return "ATTRIBUTE: \n" + "name: " + getName() + " value: " + getValue() + " right value: " + getRightValue();
    }
}
