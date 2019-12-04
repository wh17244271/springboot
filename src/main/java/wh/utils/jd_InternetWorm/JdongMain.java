package wh.utils.jd_InternetWorm;
import java.io.IOException;
import java.util.List;

import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class JdongMain {
    public static void main(String[] args) {
        //生成一个客户端，通过客户端可url向服务器发送请求，并接收响应
        HttpClient client = new DefaultHttpClient();
        String url = "http://search.jd.com/Search?keyword=Python&enc=utf-8&book=y&wq=Python&pvid=33xo9lni.p4a1qb";
        List<JdongBook> bookList = null;
        try {
            bookList = URLHandle.urlParser(client, url);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        for(JdongBook book : bookList) {
            System.out.println(book);
        }
    }
}
