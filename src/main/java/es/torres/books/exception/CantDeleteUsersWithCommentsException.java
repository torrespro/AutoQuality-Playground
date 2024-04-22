package es.torres.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CantDeleteUsersWithCommentsException extends RuntimeException {
    public CantDeleteUsersWithCommentsException(String id) {
        super("Could not delete a user with valid comments ");
    }
}
