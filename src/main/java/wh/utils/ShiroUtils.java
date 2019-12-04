package wh.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShiroUtils {

    public static ShiroBean xmlShiroBean(){
        //1.创建Reader对象
        SAXReader reader = new SAXReader();
        //2.加载xml
        ClassPathResource classPathResource = new ClassPathResource("shiro-config.xml");
        InputStream inputStream = null;

        try {
            inputStream = classPathResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //3.获取根节点
        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator();
        ShiroBean shiroBean = new ShiroBean();
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        while (iterator.hasNext()){
            Element stu = (Element) iterator.next();

            String name = stu.getName();
            String textTrim = stu.getTextTrim();
            if("anon".equals(name) || "authc".equals(name)){
                Iterator iterator1 = stu.elementIterator();
                while (iterator1.hasNext()){
                    Element stuChild = (Element) iterator1.next();
                    String url = stuChild.getTextTrim();
                    filterChainDefinitionMap.put(url,name);
                    shiroBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
                }
            }else if("loginUrl".equals(name)){
                shiroBean.setLoginUrl(textTrim);
            }else if("unauthorizedUrl".equals(name)){
                shiroBean.setUnauthorizedUrl(textTrim);
            }

        }
        return shiroBean;
    }
}
