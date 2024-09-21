package hari.personal.twod.models;

public class PostItemModel {

    private String title, body, id;

    public PostItemModel(String title, String body, String id) {
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public PostItemModel() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
