package com.test.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;

@Activate(group = "act", value = "actTwo", order = 2)
public class ActivateTwoServiceImpl implements DemoService {

    @Override
    public String echo(URL url, String s) {
        return "ActivateTwoService";
    }
}
