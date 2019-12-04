package wh.service.impl;

import org.springframework.stereotype.Service;
import wh.service.ITest;

@Service("test")
public class TestImpl implements ITest {

    @Override
    public String getValue() {
        return "wh";
    }
}
