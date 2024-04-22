package es.torres.books.service;

import es.torres.books.exception.BookNotFoundException;
import es.torres.books.exception.CommentNotFoundException;
import es.torres.books.mapper.BookMapper;
import es.torres.books.mapper.CommentMapper;
import es.torres.books.mapper.UserMapper;
import es.torres.books.model.Book;
import es.torres.books.model.BookListResponseDTO;
import es.torres.books.model.BookPostDTO;
import es.torres.books.model.Comment;
import es.torres.books.persistence.BookRepository;
import es.torres.books.persistence.CommentRepository;
import es.torres.books.persistence.UserRepository;
import es.torres.books.persistence.entity.BookEntity;
import es.torres.books.persistence.entity.CommentEntity;
import es.torres.books.persistence.entity.UserEntity;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    final BookRepository bookRepository;
    final CommentRepository commentRepository;
    final UserRepository userRepository;

    private final BookMapper mapper;
    private final CommentMapper commentMapper;
    private final UserMapper userMapper;
    
    @PersistenceContext
    private EntityManager entityManager;

    public BookService(BookRepository bookRepository, CommentRepository commentRepository,
        UserRepository userRepository, BookMapper mapper, CommentMapper commentMapper,
        UserMapper userMapper) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return mapper.entityListToApiList(bookRepository.findAll());
    }

    @Transactional(readOnly = true)
    public Book findById(long id) {
        return mapper.entityToApi(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Transactional
    public BookPostDTO save(BookPostDTO book) {
        BookEntity bookEntity = bookRepository.save(mapper.apiPostToEntity(book));
        return mapper.entityToApiPost(bookEntity);
    }

    @Transactional
    public Comment save(Comment comment, long bookId) {
        CommentEntity commentEntity = commentMapper.apiToEntity(comment);
        UserEntity userEntity = this.userRepository.findByNickname(comment.getUsername()).orElseGet(UserEntity::new);
        userEntity.setNickname(comment.getUsername());
        BookEntity bookEntity =  bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        commentEntity.setBook(bookEntity);
        commentEntity.setUser(userEntity);
        userRepository.save(userEntity);
        return commentMapper.entityToApi(commentRepository.save(commentEntity));
    }

    @Transactional
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void deleteComment(long bookId, long id) {
        commentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Comment findCommentById(long bookId, long id) {
        return commentMapper.entityToApi(commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id)));
    }

    @Transactional(readOnly = true)
    public List<BookListResponseDTO> findAllBooks() {
        List<BookListResponseDTO> postDTOs = entityManager.createQuery("""
                   select
                      p.id as id,
                      p.title as title
                   from BookEntity p
                """)
            .unwrap(org.hibernate.query.Query.class)
            .setResultTransformer(Transformers.aliasToBean(BookListResponseDTO.class))
            .getResultList();
        return postDTOs;
    }


}
