package wh.config.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ExceptionHand {

    /**
     * 重定向
     * @param ex
     * @return
     */
    @ExceptionHandler({LoginException.class})
    public String  loginException(LoginException ex) {
        ex.printStackTrace();
        return "redirect:/loginerror";

    }

    /**
     * 返回视图
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ModelAndView  exception(Exception ex) {
        ex.printStackTrace();
    	ModelAndView mav = new ModelAndView();
    	//返回信息
    	mav.addObject("message", ex.getMessage());
    	//返回页面
    	mav.setViewName("/index");
     return mav;
    	
    }
 


}
