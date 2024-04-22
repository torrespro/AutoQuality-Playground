package es.torres.books.service;

import es.torres.books.exception.CantDeleteUsersWithCommentsException;
import es.torres.books.exception.UserNotFoundException;
import es.torres.books.mapper.CommentMapper;
import es.torres.books.mapper.UserMapper;
import es.torres.books.model.CommentDTO;
import es.torres.books.model.UserDTO;
import es.torres.books.persistence.CommentRepository;
import es.torres.books.persistence.UserRepository;
import es.torres.books.persistence.entity.UserEntity;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    final UserRepository userRepository;
    final CommentRepository commentRepository;
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;

    public UserService(UserRepository userRepository, CommentRepository commentRepository,
        UserMapper userMapper, CommentMapper commentMapper) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAllUsers() {
        return userMapper.entityListToApiList(userRepository.findAll());
    }

    @Transactional
    public UserDTO save(UserDTO newUser) {
        return userMapper.entityToApi(userRepository.save(userMapper.apiToEntity(newUser)));
    }

    @Transactional
    public void deleteById(String username) {
        UserEntity userEntity = userRepository.getById(username);
        if (userEntity.getComments() == null || userEntity.getComments().isEmpty())
            userRepository.deleteById(username);
        else throw new CantDeleteUsersWithCommentsException(username);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(String userId) {
        return userMapper.entityToApi(
            userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)));
    }


    public UserDTO update(UserDTO updatedUser, String username) {
        UserEntity userEntity = userRepository.findById(username).orElseThrow();
        userEntity.setEmail(updatedUser.email());
        return userMapper.entityToApi(userRepository.save(userEntity));
    }

    @Transactional(readOnly = true)
    public Set<CommentDTO> findCommentByUserId(String userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        return commentMapper.entityListToApiWithBookIdList(userEntity.getComments());

    }
}
