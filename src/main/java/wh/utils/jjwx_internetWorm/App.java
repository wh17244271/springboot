package wh.utils.jjwx_internetWorm;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        NovelUtils.login("wh17244271@163.com","wh19950724");
        //输入小说id
        String novelId = "2614796";

        //小说实体
        Novel novel = new Novel(novelId);
        novel = NovelUtils.getNovelInfo(novel);
        NovelUtils.outputFile(novel);
    }



}
