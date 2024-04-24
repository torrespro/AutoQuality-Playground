package es.torres.books.model;

public record CommentDTO(Long id, String username, String text, Short rating, Long book_id) {
}

