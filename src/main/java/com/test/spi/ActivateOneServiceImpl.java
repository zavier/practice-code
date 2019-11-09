package com.test.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;

@Activate(group = "act", value = "actOne", order = 1)
public class ActivateOneServiceImpl implements DemoService {

    @Override
    public String echo(URL url, String s) {
        return "ActivateOneService";
    }
}
