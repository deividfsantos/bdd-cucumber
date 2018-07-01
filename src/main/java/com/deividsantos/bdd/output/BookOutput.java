package com.deividsantos.bdd.output;

public class BookOutput {

    private String name;
    private Integer pages;
    private String genre;
    private String author;

    public BookOutput() {
    }

    public BookOutput(String name, Integer pages, String genre, String author) {
        this.name = name;
        this.pages = pages;
        this.genre = genre;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookOutput{" +
                "name='" + name + '\'' +
                ", pages=" + pages +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
