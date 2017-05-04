package project.models;

public class CommentModel {
    private String content;
    private String userName;
    private long memeId;

    public CommentModel(String content, String userName, long memeId) {
        this.content = content;
        this.userName = userName;
        this.memeId = memeId;
    }

    public CommentModel() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTrimmedUserName() {
        return trim(userName);
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getMemeId() {
        return memeId;
    }

    public void setMemeId(long memeId) {
        this.memeId = memeId;
    }

    private String trim(String user) {
        int atIndex = user.indexOf("@");
        return user.substring(0, atIndex);
    }
}
