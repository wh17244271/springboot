package wh.controller;

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
import wh.entity.Node;
import wh.entity.Student;
import wh.service.INodeService;
import wh.service.IStudentService;
import wh.utils.Constant;
import wh.utils.RSAUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
public class NodeController {

    @Autowired
    private INodeService nodeService;

    @GetMapping(value = "/tree")
    @ResponseBody
    public List<Node> getNodeTree(String id) {
        return nodeService.getNodeTree(id);
    }


}
