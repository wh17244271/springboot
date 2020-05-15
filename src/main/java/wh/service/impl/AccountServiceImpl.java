package wh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.dao.AccountDao;
import wh.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

//    @Transactional
    public void transfer(int outter, int inner, Integer money) {

        accountDao.moveOut(outter, money); //转出
        int i = 1/0;  // 抛出异常
        accountDao.moveIn(inner, money); //转入
    }

    @Override
    public String getConfig() {
        return accountDao.getConfig();
    }
}