package es.torres.books.mapper;

import es.torres.books.model.Comment;
import es.torres.books.model.CommentDTO;
import es.torres.books.persistence.entity.CommentEntity;
import java.util.List;
import java.util.Set;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommentMapper {

  @Mapping(source = "user.nickname", target = "username")
  Comment entityToApi(CommentEntity entity);

  @InheritInverseConfiguration
  CommentEntity apiToEntity(Comment api);

  Set<Comment> entityListToApiList(Set<CommentEntity> entity);

  List<CommentEntity> apiListToEntityList(List<Comment> api);

  @Mapping(source = "user.nickname", target = "username")
  @Mapping(source = "book.id", target = "book_id")
  CommentDTO entityToApiWithBookId(CommentEntity entity);

  Set<CommentDTO> entityListToApiWithBookIdList(Set<CommentEntity> entity);

}
