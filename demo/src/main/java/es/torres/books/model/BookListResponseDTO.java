package es.torres.books.model;

public class BookListResponseDTO {
 
    private Long id;
 
    private String title;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Number id) {
        this.id = id.longValue();
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
}
