package wh.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wh.common.response.VenusResponse;
import wh.common.response.VenusResponseHttpCode;
import wh.config.redis.RedisService;
import wh.entity.Student;
import wh.service.IStudentService;
import wh.utils.Constant;
import wh.utils.JapaneseStudy;
import wh.utils.RSAUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;


@Controller
public class JapaneseStudyController {

	@RequestMapping("/getWord")
    @ResponseBody
	public JSONArray setRedis(Integer wordNum){
        JSONArray array = new JSONArray();
        for (int i = 0; i < wordNum; i++) {
            Random rom = new Random();
            int i1 = rom.nextInt(JapaneseStudy.words.length);
            array.add(JapaneseStudy.words[i1]);
        }
        return array;
    }

}
