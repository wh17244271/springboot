package wh.utils.bjx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UrlHandler {


    public static void main(String[] args) {
        String url = "http://www.bjx.com.cn/";
        getElement(url);
    }

    public static void getElement(String url) {
        List<News> newsList = new ArrayList<>();
        Document doc = requestByGet(url);
//        Elements elements = doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
        Element ul = doc.getElementById("bjx_main3_mid_ul01");
        Elements lis = ul.getElementsByTag("li");

        News news = null;
        for (Element li : lis) {

            Element a = li.getElementsByTag("a").get(0);
            String title = a.attr("title");
            String href = a.attr("href");
            String breaf = a.text();

            news = new News(title,href,breaf,"");
            System.out.println(">>>>>>>>>>>>>>>>>>>");
            System.out.println("标题:" + title + ",连接:" + href);
            System.out.println("内容:" + breaf);
            newsList.add(news);
        }
        //开始解析url

    }

    public static Document requestByGet(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return doc;
    }

    public static Document requestByPost(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url)
                    .timeout(5000)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")//.header()设置头信息
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.9")
                    .header("Cache-Control", "max-age=0")
                    .header("Host", "www.jjwxc.net")
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .post();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return doc;
    }











/*
    public static HttpResponse getHttpResponse(HttpClient client, String url) {
        //获取响应文件，即HTML，采用get方法获取响应数据
        HttpGet getMethod = new HttpGet(url);
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");

        try {
            //通过client执行get方法
            response = client.execute(getMethod);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return response;
    }

    public static String getEntity(HttpClient client, String url) throws IOException {
        String entity = "";
        //获取响应资源
        HttpResponse response = getHttpResponse(client, url);
        //获取响应的状态码
        int sattusCode = response.getStatusLine().getStatusCode();
        if(sattusCode == 200) {//200表示成功
            //获取响应实体内容，并且将其转换为utf-8形式的字符串编码
             entity = EntityUtils.toString(response.getEntity(), "utf-8");

        } else {
            EntityUtils.consume(response.getEntity());//释放资源实体
        }
        return entity;
    }

    public static void analysisDocument(){
        Document doc = Jsoup.parse(entity);
    }*/
}
