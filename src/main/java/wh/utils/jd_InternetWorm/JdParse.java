package wh.utils.jd_InternetWorm;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class JdParse {
    /**
     * 根据实体获取程序所需数据
     * @param entity HTTP响应实体内容
     * @return
     */
    public static List<JdongBook> getData(String entity) {
        List<JdongBook> data = new ArrayList<>();
        //采用jsoup解析，关于jsoup的使用，见下文总结
        Document doc = Jsoup.parse(entity);

        //根据页面内容分析出需要的元素
        Elements elements = doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
        for(Element element : elements) {
            JdongBook book = new JdongBook();
            book.setBookId(element.attr("data-sku"));
            book.setBookName(element.select("div[class=p-name p-name-type-2]").select("em").text());
            book.setBookPrice(element.select("div[class=p-price]").select("strong").select("i").text());

            data.add(book);
        }
        return data;
    }

}
