package com.test.spi;

import com.alibaba.dubbo.common.URL;

public class NoActivateServiceImpl implements DemoService {

    @Override
    public String echo(URL url, String s) {
        return "ActivateOneService";
    }
}
