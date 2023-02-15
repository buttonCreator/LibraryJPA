package out.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @NotEmpty(message = "Name book should not be empty")
    @Size(max = 40)
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Name author should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Name author should be in this format: Author_Name Author_LastName")
    @Size(min = 6, max = 40, message = "Enter the author's name between 6 and 40 characters and the pattern: FirstName LastName")
    @Column(name = "author")
    private String author;

    @Min(value = 1, message = "Year of writing should be greater than 1")
    @Max(value = 2022, message = "Year of writing should be less than 2022")
    @Column(name = "year_of_writing")
    private int yearOfWriting;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_taking")
    private Date dateOfTaking;

    @Transient
    private boolean expired;

    public Book() {

    }

    public Book(String name, String author, int yearOfWriting) {
        this.name = name;
        this.author = author;
        this.yearOfWriting = yearOfWriting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson_id() {
        return owner;
    }

    public void setPerson_id(Person owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getDateOfTaking() {
        return dateOfTaking;
    }

    public void setDateOfTaking(Date dateOfTaking) {
        this.dateOfTaking = dateOfTaking;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public boolean getExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearOfWriting=" + yearOfWriting +
                ", dateOfTaking=" + dateOfTaking +
                '}';
    }
}
