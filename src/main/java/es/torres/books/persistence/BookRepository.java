package es.torres.books.persistence;

import es.torres.books.persistence.entity.BookEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
