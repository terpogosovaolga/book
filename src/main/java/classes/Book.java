package classes;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.lang.reflect.Field;


public class Book {

    public Book(long id, String authorName, String sureName, String name) {
        this.id = id;
        this.authorName = authorName;
        this.authorSureName = sureName;
        this.name = name;
    }
    public Book() {

    }
    public Book(long id, String authorName, String authorSecondName, String authorSureName, String name, int yearOfWriting, String publisher, int year, String translater, int countOfPages, String genre, String originalLanguage, String language, Double cout, int count, String description, int numberOfWatching) {
        this.id=id;
        this.authorName = authorName;
        this.authorSecondName = authorSecondName;
        this.authorSureName = authorSureName;
        this.name=name;
        this.yearOfWriting = yearOfWriting;
        this.publisher = publisher;
        this.yearOfPublishing = year;
        this.originalLanguage = originalLanguage;
        this.language = language;
        this.translater = translater;
        this.cout = cout;
        this.count = count;
        this.countOfPages = countOfPages;
        this.description = description;
        this.genre = genre;
        this.numberOfWatching = numberOfWatching;
    }
    @NotNull
    private Long id;

    @NotNull
    private String authorName;

    private String authorSecondName;

    @NotNull
    private String authorSureName;

    @NotNull
    private String name;

    private int yearOfWriting;

    @NotNull
    private String publisher;

    @NotNull
    private int yearOfPublishing;

    private String translater;

    private int countOfPages;

   @NotNull
   private String genre;

    @NotNull
    private String originalLanguage;

    private String language;

    @NotNull
    private Double cout;

    @NotNull
    private Integer count;

    private String description;

    private int numberOfWatching;


    public Object getValueOfTheField(String nameOfField) {
        Field field;
        try {
            field = Book.class.getField(nameOfField);
            return field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void universalSet(String key, Object value) { // присваивает значение value полю key
        Field field;
        try {
             field = Book.class.getField(key);
            field.set(this, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId( Long id) {
        this.id = id;
    }

    @NotNull
    public String getAuthorName() {
        return authorName;
    }
    @ModelAttribute("authorName")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSecondName() {
        return authorSecondName;
    }
    @ModelAttribute("authorSecondName")
    public void setAuthorSecondName(String authorSecondName) {
        this.authorSecondName = authorSecondName;
    }

    @NotNull
    public String getAuthorSureName() {
        return authorSureName;
    }
    @ModelAttribute("authorSureName")
    public void setAuthorSureName(String authorSureName) {
        this.authorSureName = authorSureName;
    }

    @NotNull
    public String getName() {
        return name;
    }
    @ModelAttribute("name")
    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfWriting() {
        return yearOfWriting;
    }
    @ModelAttribute("yearOfWriting")
    public void setYearOfWriting(int yearOfWriting) {
        this.yearOfWriting = yearOfWriting;
    }

    @NotNull
    public String getPublisher() {
        return publisher;
    }
    @ModelAttribute("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }
    @ModelAttribute("yearOfPublishing")
    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public String getTranslater() {
        return translater;
    }
    @ModelAttribute("translater")
    public void setTranslater(String translater) {
        this.translater = translater;
    }

    public int getCountOfPages() {
        return countOfPages;
    }
    @ModelAttribute("countOfPages")
    public void setCountOfPages(int countOfPages) {
        this.countOfPages = countOfPages;
    }

    @NotNull
    public String getGenre() {
        return genre;
    }
    @ModelAttribute("genre")
    public void setGenre(@NotNull String genre) {
        this.genre = genre;
    }

    @NotNull
    public String getOriginalLanguage() {
        return originalLanguage;
    }
    @ModelAttribute("originalLanguage")
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getLanguage() {
        return language;
    }
    @ModelAttribute("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @NotNull
    public Double getCout() {
        return cout;
    }
    @ModelAttribute("cout")
    public void setCout( Double cout) {
        this.cout = cout;
    }

    @NotNull
    public Integer getCount() {
        return count;
    }
    @ModelAttribute("count")
    public void setCount( Integer count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }
    @ModelAttribute("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfWatching() {
        return numberOfWatching;
    }

    public void setNumberOfWatching(int numberOfWatching) {
        this.numberOfWatching = numberOfWatching;
    }
}
