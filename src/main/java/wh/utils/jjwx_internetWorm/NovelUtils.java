package wh.utils.jjwx_internetWorm;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NovelUtils {
    public final static String DOMAIN = "http://my.jjwxc.net";
    public final static String NOVEL_HOME_URL = "http://www.jjwxc.net/onebook.php?novelid=";
    public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586";
    private final static String FILE_ENCODING = "UTF-8";	//文件编码


    private static Map<String, String> cookies;
    public static void login(String loginName, String password) {
        Connection.Response response = null;
        try {
            response = Jsoup.connect("http://my.jjwxc.net/login.php")
                    .followRedirects(false)
                    .userAgent(USER_AGENT)//设置useragent
                    .data("loginname",loginName)//.data()传参
                    .data("loginpassword",password)
                    .header("Accept", "*/*")//设置头信息
                    .header("Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.9")
                    .header("Host", "my.jjwxc.net")
                    .header("Referer", "http://www.jjwxc.net/")
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .execute();
        } catch (IOException e) {
            System.out.println("登陆失败");
            e.printStackTrace();
        }
        cookies = response.cookies();//获取cookies
    }

    public static Novel getNovelInfo(Novel novel) {
        //小说首页dom
        Document doc = null;
        try {
            doc = Jsoup.connect(novel.getUrl())
                    .timeout(5000)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")//.header()设置头信息
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.9")
                    .header("Cache-Control", "max-age=0")
                    .header("Host", "www.jjwxc.net")
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .userAgent(USER_AGENT)
                    .cookies(cookies)
                    .get();
        } catch (IOException e1) {
            System.out.println("小说首页获取失败");
            e1.printStackTrace();
        }
        //小说名和作者名
        novel.setTitle(doc.getElementById("oneboolt").getElementsByTag("tbody").get(0).child(0).getElementsByTag("h1").get(0).text().trim());
        novel.setAuthor(doc.getElementById("oneboolt").getElementsByTag("tbody").get(0).child(0).getElementsByTag("h2").get(0).getElementsByTag("a").text().trim());
        System.out.println(novel.getTitle()+" - "+novel.getAuthor());
        //章节table列表
        List<Element> chapterTrList = doc.getElementById("oneboolt").getElementsByTag("tbody").get(0).getElementsByTag("tr");
        //待set进novel的章节列表
        List<Chapter> chapterList = new ArrayList<Chapter>();
        //获取每章节信息
        for (Element chapterTr : chapterTrList) {
            //如果不是章节列
            if (!chapterTr.hasAttr("itemprop")) {
                continue;
            }
            Chapter chapter = new Chapter();
            chapter.setVip(chapterTr.getElementsByTag("td").size() == 5);//一行只有5列则为vip章节
            if (chapter.isVip()) {//不获取v章内容
                break;
            }
            chapter.setChapterNum(chapterTr.child(0).text().trim());
            chapter.setChapterTitle(chapterTr.child(1).text().trim());
            //锁章没有url
            try {
                chapter.setUrl(chapterTr.child(1).getElementsByTag("a").get(0).attr("href"));
            } catch(IndexOutOfBoundsException e) {
                chapter.setContent("章节已锁");
            }
            chapter.setContent(getChapterContent(chapter, novel.getUrl()));
            chapterList.add(chapter);
        }
        novel.setChapters(chapterList);
        return novel;
    }

    public static String getChapterContent(Chapter chapter, String novelUrl) {
        System.out.println("正在获取第"+chapter.getChapterNum()+"章 "+chapter.getChapterTitle());
        //如果是锁章，返回
        if (chapter.getUrl() == null) {
            return chapter.getContent();
        }
        //获取文章dom
        Document doc = null;
        try {
            doc = Jsoup.connect(chapter.getUrl())
                    .timeout(5000)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.9")
                    .header("Cache-Control", "max-age=0")
                    .header("Connection", "keep-alive")
                    .header("Host", "www.jjwxc.net")
                    .header("Referer", novelUrl)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .userAgent(USER_AGENT)
                    .get();
        } catch (IOException e) {
            System.out.println("章节获取失败");
            e.printStackTrace();
        }
        //为了保留换行
        String content = Jsoup.clean(new String(doc.getElementsByClass("noveltext").get(0).html()), "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
        //去头去尾
        int beginIndex = content.indexOf("查看收藏列表") + "查看收藏列表".length();
        int endIndex = content.lastIndexOf("插入书签");
        int authorIndex = content.lastIndexOf("插入书签") + "插入书签".length();
        if (beginIndex != -1 && endIndex != -1 && authorIndex != -1) {
            content = content.substring(beginIndex, endIndex).trim() + content.substring(authorIndex).trim();
        }
        return content;
    }

    public static void outputFile(Novel novel) {
        System.out.println("输出文件");
        //文件名：作品名 - 作者.txt
        String fileName = novel.getTitle() + " - " + novel.getAuthor() + ".txt";
        File file = new File(fileName);
        //文件不存在则创建文件
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        //输出到文件
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), FILE_ENCODING));
            List<Chapter> chapterList = novel.getChapters();
            for (Chapter chapter : chapterList) {
                System.out.println("正在输出第"+chapter.getChapterNum()+"章 "+chapter.getChapterTitle());
                bw.write("第"+chapter.getChapterNum()+"章");
                if (chapter.getContent() != null) {
                    bw.write(chapter.getContent());
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch(Exception e) {
            System.out.println("输出失败");
            e.printStackTrace();
        }
        System.out.println("输出完成");
    }
}
