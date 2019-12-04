package wh.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wh.AppStart;
import wh.common.response.VenusResponse;
import wh.common.response.VenusResponseHttpCode;
import wh.config.redis.RedisService;
import wh.entity.Student;
import wh.service.IStudentService;
import wh.utils.Constant;
import wh.utils.RSAUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
public class TestController {
    public static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private IStudentService studentImpl;
    @Autowired
    private RedisService redisService;


   /* @RequestMapping("/websocket/{name}")
    public String webSocket(@PathVariable String name, Model model){
        try{
            logger.info("跳转到websocket的页面上");
            model.addAttribute("username",name);
            return "websocket";
        }
        catch (Exception e){
            logger.info("跳转到websocket的页面上发生异常，异常信息是："+e.getMessage());
            return "error";
        }
    }*/


    @RequestMapping( "/setRedis" )
    @ResponseBody
    public Object setRedis() {
        redisService.setValue("key", "hello", 1000 * 10);
        Object key = redisService.getValue("key");
        return key;
    }

    @RequestMapping( "/getRedis" )
    @ResponseBody
    public Object getRedis(HttpServletRequest request) {
        Object key = redisService.getValue("key");
        Object publicKey = redisService.getValue("publicKey");
        String privateKey = Constant.privateKey;
        return "redis>>>>>>>>>>" + publicKey + "/nprivateKey>>>>>>>>>>" + privateKey + ":/npublicKey>>>>>>>>>>" + Constant.publicKey;
    }

    @RequestMapping( "/getUser" )
    @ResponseBody
    public JSONObject getUserById(Student student) {
        Student student2 = studentImpl.get(student.getId());
        JSONObject result = new JSONObject();
        result.put("success", student2);
        return result;

    }

    @RequestMapping( "/login" )
    public String login(HttpServletRequest request) {
        String localAddr = request.getLocalAddr();//取得服务器IP
        int localPort = request.getLocalPort();//取得服务器端口
        System.out.println(localAddr+":"+localPort);
        return "login";
    }

    @RequestMapping( "/index" )
    public String index(Student st, Map<String, Object> map) throws Exception {
        map.put("username", st.getUsername());
        if (true) {
//			 throw new Exception("some exception 异常内容！");
        }
        return "index";
    }


    @GetMapping( "/rsa" )
    public String rsa(Map<String, Object> map) throws Exception {
        map.put("publicKey", Constant.publicKey);
        map.put("privateKey", Constant.privateKey);
        return "rsaTest";
    }

    @GetMapping( "/encryptMyStr" )
    @ResponseBody
    public VenusResponse wodeGet(@RequestParam( "encryptMyStr" ) String encryptMyStr) throws Exception {
        String decode = RSAUtils.decodeByPrivateKey(encryptMyStr, Constant.privateKey);
        return new VenusResponse(VenusResponseHttpCode.OK, decode);
    }

    @GetMapping( "/wode" )
    public String wodeGet(Map<String, Object> map) throws Exception {
        map.put("message", "正常进来");
        if (true) {
//			 throw new Exception("some exception 异常内容！");
        }
        return "wode";
    }


    @RequestMapping( "/xss" )
    @ResponseBody
    public String xss(HttpServletRequest request) throws Exception {
        int a = 0;
        a = 2;
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] parameterValues = request.getParameterValues("username");
        String parameter = request.getParameter("username");
        String password = request.getParameter("password");
        return "";
    }

    static Map<String,List<String>> map = new HashMap<>();
    public static void main(String[] args) {
        List<String> a = new ArrayList<String>(){{
            this.add("wh");
            this.add("hyq");
        }};

        map.put("ceshi",a);

        b(a);
        List<String> ceshi = map.get("ceshi");
        for (String key : ceshi) {
            System.out.println("key = " + key);
        }
    }
    public static void b(List<String> a ){
        List<String> c = new ArrayList<>();
        c.add("sdf");
        c=a;
        c.remove(0);
        c.add("ds");
    }
    public static void a(String[] args) {
        List a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(6);
        a.add(7);
        a.add(8);
        a.add(9);
        a.add(10);
        int id = 5;
        int getnum = 3; // 432
        int start = 0;
        int end = 0;
        Collections.reverse(a);
        for (int i = 0; i < a.size(); i++) {
            int need = (int) a.get(i);
            if (need == id) {
                start = i ;
                end = i+getnum;
            }
            if(start<i&& i<=end){
                System.out.println(need);
            }
        }
    }
}
