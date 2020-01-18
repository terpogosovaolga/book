package models;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="Books")
public class Book  implements Serializable {

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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name="Book_id")
    private Long id;

    @Column(name = "Author_name")
    @NotNull
    private String authorName;

    @Column(name = "Author_secondname")
    private String authorSecondName;

    @Column(name = "Author_surname")
    @NotNull
    private String authorSureName;

    @Column(name = "Book_name")
    @NotNull
    private String name;

    @Column(name = "Year_of_writing")
    private int yearOfWriting;

    @Column(name = "Publisher")
    @NotNull
    private String publisher;

    @Column(name = "Year_of_publishing")
    @NotNull
    private int yearOfPublishing;

    @Column(name = "Translater")
    private String translater;

    @Column(name = "Pages_number")
    private int countOfPages;

    @Column(name = "Genre")
   @NotNull
   private String genre;

    @Column(name = "Original_language")
    @NotNull
    private String originalLanguage;

    @Column(name = "Language")
    private String language;

    @Column(name = "Price")
    @NotNull
    private Double cout;

    @Column(name = "Count")
    @NotNull
    private Integer count;

    @Column(name = "Description")
    private String description;

    @Column(name = "Number_of_watchings")
    private int numberOfWatching;



    public Long getId() {
        return id;
    }

    public void setId( Long id) {
        this.id = id;
    }


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

    public String getAuthorSureName() {
        return authorSureName;
    }

    @ModelAttribute("authorSureName")
    public void setAuthorSureName(String authorSureName) {
        this.authorSureName = authorSureName;
    }

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

    public String getGenre() {
        return genre;
    }

    @ModelAttribute("genre")
    public void setGenre(@NotNull String genre) {
        this.genre = genre;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    @ModelAttribute("originalLanguage")
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getLanguage() {
        if (language==null) return originalLanguage;
        else return language;
    }

    @ModelAttribute("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getCout() {
        return cout;
    }

    @ModelAttribute("cout")
    public void setCout( Double cout) {
        this.cout = cout;
    }

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

    public String getFullNameOfAuthor() {
        if (authorSecondName!=null)
            return authorSureName + " " + authorName + " " + authorSecondName;
        return authorSureName + " " + authorName;
    }

    public boolean equals(Book b) {
        if(b==null){
            return false;
        }
        if(b==this){
            return true;
        }
        if(this.getId() != b.getId()) return false;
        if(this.getYearOfPublishing() != b.getYearOfPublishing()) return false;
        if(this.getYearOfWriting() != b.getYearOfWriting()) return false;
        if (!this.getName().equals(b.getName())) return false;
        if (!this.getAuthorSureName().equals(b.getAuthorSureName())) return false;
        if (!this.getAuthorSecondName().equals(b.getAuthorSecondName())) return false;
        return (this.getPublisher().equals((b.getPublisher())));
    }

    public String toString() {
        return "id: " + getId() + "\n author: " + getFullNameOfAuthor() + "\n book name: " + getName() + "\n publisher: " + getPublisher() + "\n price: " + getCout();
    }
}
