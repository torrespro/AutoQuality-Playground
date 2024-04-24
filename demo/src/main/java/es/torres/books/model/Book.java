package es.torres.books.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Book {

    private Long id;
    @NotBlank
    @Size(min = 2, max = 200, message = "book title should have at least 2 characters and not more than 200")
    private String title;
    private String summary;
    private String author;
    private String publisher;
    private Integer publicationYear;
    private Collection<CommentResponseDTO> comments = new ArrayList<>();

    public Book(Long id, String title, String summary, String author, String publisher, Integer publicationYear,
        Collection<CommentResponseDTO> comments) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.comments = comments;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Collection<CommentResponseDTO> getComments() {
        return comments;
    }

    public void setComments(Collection<CommentResponseDTO> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        var that = (Book) obj;
        return Objects.equals(this.id, that.id) &&
            Objects.equals(this.title, that.title) &&
            Objects.equals(this.summary, that.summary) &&
            Objects.equals(this.author, that.author) &&
            Objects.equals(this.publisher, that.publisher) &&
            Objects.equals(this.publicationYear, that.publicationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, summary, author, publisher, publicationYear);
    }

    @Override
    public String toString() {
        return "Book[" +
            "id=" + id + ", " +
            "title=" + title + ", " +
            "summary=" + summary + ", " +
            "author=" + author + ", " +
            "publisher=" + publisher + ", " +
            "publicationYear=" + publicationYear + ']';
    }

}
