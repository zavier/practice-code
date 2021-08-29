package com.zavier.thrift.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zavier.thrift.Tweet;
import com.zavier.thrift.TweetSearchResult;
import com.zavier.thrift.Twitter;
import com.zavier.thrift.TwitterUnavailable;
import org.apache.thrift.TException;

public class TwitterServiceImpl implements Twitter.Iface {
    @Override
    public void ping() throws TException {
        System.out.println("recv ping");
    }

    @Override
    public boolean postTweet(Tweet tweet) throws TwitterUnavailable, TException {
        System.out.println("postTweet tweet:" + JSON.toJSONString(tweet));
        return false;
    }

    @Override
    public TweetSearchResult searchTweets(String query) throws TException {
        System.out.println("searchTweets query:" + query);
        final TweetSearchResult result = new TweetSearchResult();
        final Tweet tweet = new Tweet();
        tweet.setText("hello");
        result.setTweets(Lists.newArrayList(tweet));
        return result;
    }

    @Override
    public void zip() throws TException {
        System.out.println("recv zip call");
    }
}
