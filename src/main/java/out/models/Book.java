package out.models;

import javax.validation.constraints.*;

public class Book {

    private int id_book;

    @NotEmpty(message = "Name book should not be empty")
    @Size(max = 40)
    private String nameBook;

    @NotEmpty(message = "Name author should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Name author should be in this format: Author_Name Author_LastName")
    @Size(min = 6, max = 40)
    private String author;

    private Integer id_person;

    @Min(value = 1, message = "Year of writing should be greater than 1")
    @Max(value = 2022, message = "Year of writing should be less than 2022")
    private int yearOfWriting;

    public Book() {

    }

    public Book(String nameBook, String author, int yearOfWriting) {
        this.nameBook = nameBook;
        this.author = author;
        this.yearOfWriting = yearOfWriting;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public Integer getId_person() {
        return id_person;
    }

    public void setId_person(Integer id_person) {
        this.id_person = id_person;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfWriting() {
        return yearOfWriting;
    }

    public void setYearOfWriting(int yearOfWriting) {
        this.yearOfWriting = yearOfWriting;
    }
}
