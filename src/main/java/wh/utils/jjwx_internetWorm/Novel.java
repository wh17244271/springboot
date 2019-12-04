package wh.utils.jjwx_internetWorm;

import java.util.List;

public class Novel {
    String title;		//书名
    String author;		//作者
    List<Chapter> chapters;//章节
    String novelId;	//小说id
    String url;		//小说url

    public Novel(String novelId) {//构造函数
        this.novelId = novelId;
        this.url = NovelUtils.NOVEL_HOME_URL+novelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String getNovelId() {
        return novelId;
    }

    public void setNovelId(String novelId) {
        this.novelId = novelId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
