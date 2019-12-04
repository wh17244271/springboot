package wh.utils.bjx;

public class News {
    private String title;
    private String url;
    private String brief;
    private String text;

    public News(){}
    public News(String title, String url, String brief, String text) {
        this.title = title;
        this.url = url;
        this.brief = brief;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", brief='" + brief + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
