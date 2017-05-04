package project.models;

import project.entities.Comment;

import java.util.Set;

public class MemeModel {
    private Long id;
    private String title;
    private String user;
    private Set<Comment> comments;
    private String type;
    private String meme;
    private int points;

    public MemeModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getUser() {
        return trim(user);
    }

    private String trim(String user) {
        int atIndex = user.indexOf("@");
        return user.substring(0,atIndex);
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeme() {
        return meme;
    }

    public void setMeme(String meme) {
        this.meme = meme;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
