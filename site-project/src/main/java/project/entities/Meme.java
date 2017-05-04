package project.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "memes")
public class Meme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "meme")
    private Set<Comment> comments;

    @ManyToMany(mappedBy = "likedMemes", cascade = CascadeType.ALL)
    private Set<User> usersLiked;

    @ManyToMany(mappedBy = "dislikedMemes", cascade = CascadeType.ALL)
    private Set<User> usersDisliked;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    private String meme;

    private int points;

    public Meme() {
        comments = new HashSet<>();
    }


    public Set<User> getUsersLiked() {
        return usersLiked;
    }

    public void setUsersLiked(Set<User> usersLiked) {
        this.usersLiked = usersLiked;
    }

    public Set<User> getUsersDisliked() {
        return usersDisliked;
    }

    public void setUsersDisliked(Set<User> usersDisliked) {
        this.usersDisliked = usersDisliked;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMeme() {
        return meme;
    }

    public void setMeme(String meme) {
        this.meme = meme;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public int getPoints() {
        return this.getUsersLiked().size()-this.getUsersDisliked().size();
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    public void addLike(User curUser) {
        this.usersLiked.add(curUser);
    }
}
