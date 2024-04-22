package es.torres.books.mapper;

import es.torres.books.model.Book;
import es.torres.books.model.BookPostDTO;
import es.torres.books.model.CommentResponseDTO;
import es.torres.books.persistence.entity.BookEntity;
import es.torres.books.persistence.entity.CommentEntity;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {

  Book entityToApi(BookEntity entity);

  BookEntity apiToEntity(Book api);

  BookPostDTO entityToApiPost(BookEntity entity);

  BookEntity apiPostToEntity(BookPostDTO api);

  List<Book> entityListToApiList(List<BookEntity> entity);

  List<BookEntity> apiListToEntityList(List<Book> api);

  @Mapping(source = "user.nickname", target = "nick")
  @Mapping(source = "user.email", target = "email")
  CommentResponseDTO entityToApi(CommentEntity entity);

  @InheritInverseConfiguration
  CommentEntity apiToEntity(CommentResponseDTO api);
}
