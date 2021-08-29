package com.zavier.thrift.demo;

import com.zavier.thrift.Tweet;
import com.zavier.thrift.Twitter;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws Exception {
        TTransport transport = new TSocket("localhost", 9090);
        transport.open();

        TProtocol protocol = new TBinaryProtocol(transport);
        Twitter.Client client = new Twitter.Client(protocol);

        perform(client);

        TimeUnit.SECONDS.sleep(5);
        transport.close();
    }

    private static void perform(Twitter.Client client) throws TException
    {
        client.ping();

        final boolean b = client.postTweet(new Tweet(100, "aa", "bb"));
        System.out.println("rev " + b);
    }
}
