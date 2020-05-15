package wh.service;

public interface AccountService {
    //转账
      void transfer(int outter,int inner,Integer money);


    String getConfig();
}
