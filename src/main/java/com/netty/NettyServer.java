package com.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author WangGuanqian
 * @Date 2018/11/16 16:29
 */
public class NettyServer {

    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("k", "vvv");
        hashtable.get("k");

        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("", "");
        map.get("");
        map.size();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) {
                                System.out.println(s);
                            }
                        });
                    }
                }).bind(8000);

    }

}
