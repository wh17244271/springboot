package wh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wh.service.AccountService;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;


    @RequestMapping("/transfer")
    public String test(){
        try {
            // andy 给lucy转账50元
            accountService.transfer(1, 2, 50);
            return "转账成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "转账失败";
        }
    }


    @RequestMapping("/getConfig")
    public String getConfig(){
        String config = accountService.getConfig();
        return config;
    }
}
