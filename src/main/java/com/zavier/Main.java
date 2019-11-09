package com.zavier;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.test.spi.ActivateOneServiceImpl;
import com.test.spi.ActivateTwoServiceImpl;
import com.test.spi.DemoService;
import com.test.spi.SimpleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class Main {

    @Test
    public void testDefaultExtension() {
        final DemoService defaultExtension = ExtensionLoader.getExtensionLoader(DemoService.class)
                .getDefaultExtension();
        assertTrue(defaultExtension instanceof SimpleServiceImpl);
        final String echo = defaultExtension.echo(null, null);
        assertSame("SimpleService", echo);
    }

    @Test
    public void testAdaptive() {
        final DemoService adaptiveExtension = ExtensionLoader.getExtensionLoader(DemoService.class)
                .getAdaptiveExtension();
        // service = complex
        final URL url = new URL("", "", 100, "", "service", "complex");
        final String echo = adaptiveExtension.echo(url, null);
        assertSame("ComplexService", echo);
    }

    @Test
    public void testActivateOne() {
        // select = actone(resources中配置)
        final URL url = new URL("", "", 100, "", "select", "actone");
        final List<DemoService> activateExtension = ExtensionLoader.getExtensionLoader(DemoService.class)
                .getActivateExtension(url, "select", "act");
        assertEquals(1, activateExtension.size());
        final DemoService demoService = activateExtension.get(0);
        assertTrue(demoService instanceof ActivateOneServiceImpl);
    }

    @Test
    public void testActivateTwo() {
        // select = actone(resources中配置)
        final URL url = new URL("", "", 100, "", "select", "actone,acttwo");
        final List<DemoService> activateExtension = ExtensionLoader.getExtensionLoader(DemoService.class)
                .getActivateExtension(url, "select", "act");
        assertEquals(2, activateExtension.size());
        final DemoService demoService1 = activateExtension.get(0);
        assertTrue(demoService1 instanceof ActivateOneServiceImpl);
        final DemoService demoService2 = activateExtension.get(1);
        assertTrue(demoService2 instanceof ActivateTwoServiceImpl);
    }
}
