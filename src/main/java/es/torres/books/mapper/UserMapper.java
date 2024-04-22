package es.torres.books.mapper;

import es.torres.books.model.UserDTO;
import es.torres.books.persistence.entity.UserEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

  UserDTO entityToApi(UserEntity entity);

  UserEntity apiToEntity(UserDTO api);

  List<UserDTO> entityListToApiList(List<UserEntity> entity);

  List<UserEntity> apiListToEntityList(List<UserDTO> api);

}
