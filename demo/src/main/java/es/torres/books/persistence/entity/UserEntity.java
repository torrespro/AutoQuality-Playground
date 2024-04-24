package es.torres.books.persistence.entity;

import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "user")
public class UserEntity {

    @Column
    @Email
    private String email;

    @Id
    private String nickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CommentEntity> comments;

    public UserEntity(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public UserEntity() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    public void addComment(CommentEntity comment) {
        comments.add(comment);
        comment.setUser(this);
    }

    public void removeComment(CommentEntity comment) {
        comments.remove(comment);
        comment.setUser(null);
    }
}
