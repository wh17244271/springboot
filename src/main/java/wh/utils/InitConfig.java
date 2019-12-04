package wh.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wh.config.redis.RedisService;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class InitConfig {
    @Autowired
    private RedisService redisService;

    @Bean
    public String shiroUt(){
        ShiroBean shiroBean = ShiroUtils.xmlShiroBean();
        String loginUrl = shiroBean.getLoginUrl();
        return loginUrl;
    }

    @Bean
    public String setRSAPublicOrPrivateKey(){
        Map<String, String> keyMap = new HashMap<>();
        try {
            keyMap = RSAUtils.createRSAKeys();
            //公钥
            String publicKey = keyMap.get(RSAUtils.PUBLIC_KEY_NAME);
            //私钥
            String privateKey = keyMap.get(RSAUtils.PRIVATE_KEY_NAME);


            redisService.setValue("publicKey",publicKey);
            redisService.setValue("privateKey",privateKey);
            Constant.publicKey = publicKey;
            Constant.privateKey = privateKey;
            System.err.println(">>>>>>>>>>>>>>>>>>>初始化成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
