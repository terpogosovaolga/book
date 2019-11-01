package classes;

import org.jetbrains.annotations.NotNull;



public class Book {

    public Book(long id, String authorName, String sureName, String name)
    {
        this.id = id;
        this.authorName = authorName;
        this.authorSureName = sureName;
        this.name = name;
    }

    @NotNull
    Long id;

    @NotNull
    String authorName;

    String authorSecondName;

    @NotNull
    String authorSureName;

    @NotNull
    String name;

    int yearOfWriting;

    @NotNull
    String publisher;

    @NotNull
    int yearOfPublishing;

    String translater;

    int countOfPages;

   @NotNull
    String genre;

    @NotNull
    String originalLanguage;

    String language;

    @NotNull
    Double cout;

    @NotNull
    Integer count;

    String description;

    int numberOfWatching;

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



    public String getGenre() {return genre;}

    public String getPublisher() {return publisher;}

    public int getNumberOfWatching() {return numberOfWatching;}

    public String getLanguageOrOriginalLanguage() { // при значении language=null возвращает originalLanguage
        if (language.equals(null))
            return originalLanguage;
        else return language;
    }

    public Boolean translated() {
        return (!language.equals(null));

    }

    public Double getCout(){
        return cout;
    }

    public String getAuthorSureName(){return authorSureName;}

    public String getName() {return name;}

    public String getDescription() {return description;}

    public Long getId() {return id;}
}
