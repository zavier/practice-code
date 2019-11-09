package com.test.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

@SPI("simple")
public interface DemoService {

    /**
     * @Adapter 如果不指定参数则默认为对应类名转为对应.分隔的名称
     * 如此例则为 DemoService => demo.service
     */
    @Adaptive("service")
    String echo(URL url, String s);
}
