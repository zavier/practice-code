package com.zavier.thrift.demo;

import com.zavier.thrift.Twitter;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class Server {
    public static void main(String[] args) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);

            final Twitter.Processor<TwitterServiceImpl> processor = new Twitter.Processor<>(new TwitterServiceImpl());
            final TServer.Args serverArgs = new TServer.Args(serverTransport).processor(processor);
            TServer server = new TSimpleServer(serverArgs);

            // Use this for a multithreaded server
            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
