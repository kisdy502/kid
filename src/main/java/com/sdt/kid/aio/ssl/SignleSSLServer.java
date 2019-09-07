package com.sdt.kid.aio.ssl;

import com.sdt.im.protobuf.TransMessageProtobuf;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;


@Service
public class SignleSSLServer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String pkPath="security/server.jks";
    private String serverPassword = "ServerNetty2019";
    private SSLEngine sslEngine;

    public static void main(String args){
        SignleSSLServer server=new SignleSSLServer();
        server.start();
    }

    public void start() {
        initKeyStore();
        if (sslEngine == null) {
            logger.error("init SSL Failed...");
        }
        //boss线程监听端口，worker线程负责数据读写
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            //辅助启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            //设置线程池
            bootstrap.group(boss, worker);

            //设置socket工厂
            bootstrap.channel(NioServerSocketChannel.class);

            //设置管道工厂
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //获取管道
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(2));
                    pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535,
                            0, 2, 0, 2));
                    pipeline.addLast(new ProtobufDecoder(TransMessageProtobuf.TransMessage.getDefaultInstance()));
                    pipeline.addLast(new ProtobufEncoder());

                    pipeline.addFirst(SslHandler.class.getSimpleName(), new SslHandler(sslEngine));
                    pipeline.addLast(NettySocketSSLHandler.class.getSimpleName(), new NettySocketSSLHandler());
                }
            });

            //设置TCP参数
            //1.链接缓冲池的大小（ServerSocketChannel的设置）
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            //维持链接的活跃，清除死链接(SocketChannel的设置)
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            //关闭延迟发送
            bootstrap.childOption(ChannelOption.TCP_NODELAY, true);

            //绑定端口
            ChannelFuture future = bootstrap.bind(9999).sync().addListeners(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("server start successed...... ");
                    } else {
                        System.out.println("server start failed...... ");
                    }
                }
            });

            //等待服务端监听端口关闭,阻塞当前线程
            future.channel().closeFuture().addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    System.out.println("server stop...... ");
                    //优雅退出，释放线程池资源
                    boss.shutdownGracefully();
                    worker.shutdownGracefully();

                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void initKeyStore() {
        KeyManagerFactory keyManagerFactory = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            InputStream in = new ClassPathResource(pkPath).getInputStream();
            keyStore.load(in, serverPassword.toCharArray());
            keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, serverPassword.toCharArray());
            initSSL(keyManagerFactory.getKeyManagers());
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private void initSSL(KeyManager[] keyManagers) throws NoSuchAlgorithmException, KeyManagementException {

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(keyManagers, null, null);
        sslEngine = sslContext.createSSLEngine();
        sslEngine.setUseClientMode(false);       //客户端工作模式

        logger.info("支持的协议: " + Arrays.asList(sslEngine.getSupportedProtocols()));
        logger.info("启用的协议: " + Arrays.asList(sslEngine.getEnabledProtocols()));
        logger.info("支持的加密套件: " + Arrays.asList(sslEngine.getSupportedCipherSuites()));
        logger.info("启用的加密套件: " + Arrays.asList(sslEngine.getEnabledCipherSuites()));

    }
}
