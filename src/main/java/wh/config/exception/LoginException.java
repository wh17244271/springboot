package wh.config.exception;

/**
 * 自定义异常，对前台参数进行解析时出现的相关错误
 */
public class LoginException extends RuntimeException {
    public LoginException() {
        super();
    }

    public LoginException(String msg) {
        super(msg);
    }
}
