package com.zavier.startup;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        final WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setDescriptor("./src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase("./src/main/webapp");
        // 解决静态资源无法自动更新问题
//        String descriptor = "./src/main/resources/webdefault.xml";
//        context.setDefaultsDescriptor(descriptor);
//        context.setParentLoaderPriority(true);

        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
