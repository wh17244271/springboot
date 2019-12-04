package wh.utils.weather;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 获取某个 城市的天气状况数据，数据格式是Json
 */
public class WeatherUtils {


    public static String GetWeatherData(String cityid) {
        StringBuilder sb = new StringBuilder();
        ;
        try {
            String weatherUrl = "https://www.tianqiapi.com/api/?version=v1&cityid=" + cityid;
            URL url = new URL(weatherUrl);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8"); // 设置读取流的编码格式，自定义编码
            BufferedReader reader = new BufferedReader(isr);
            String line = null;
            while ((line = reader.readLine()) != null)
                sb.append(line + " ");
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }


    /**
     * 将JSON格式数据进行解析 ，返回一个weather对象
     *
     * @return
     */
    public static JSONObject GetWeather(String weatherInfobyJson) {
        JSONObject dataOfJson = JSONObject.parseObject(weatherInfobyJson);
        if (dataOfJson==null || dataOfJson.size()==0)
            return null;


        //从json数据中提取数据
        String data = dataOfJson.getString("data");
        JSONArray jsonArray = JSONObject.parseArray(data);
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String day = (String)jsonObject.get("day");
            System.out.println(day);
        }

        return null;
    }

    public static void main(String[] args) {
        String info = WeatherUtils.GetWeatherData("101110101");
    }
}