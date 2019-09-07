package com.sdt.kid.aio.ssl;

import com.sdt.im.protobuf.TransMessageProtobuf;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Service
public class TwoWaySSLClient {

    public static void main(String[] args) {
        TwoWaySSLClient client = new TwoWaySSLClient();
        client.start();
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String currentHost = "192.168.66.81";
    private int currentPort = 9999;

    private String clientPassword = "ClientNetty2019";
    private String caFileName = "security/client.jks";


    private SSLEngine sslEngine;

    public TwoWaySSLClient() {
    }

    public void start() {
        initKeyStore(caFileName);
        if (sslEngine == null) {
            logger.error("init SSL failed!");
            return;
        }

        EventLoopGroup workGroup = new NioEventLoopGroup(4);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup).channel(NioSocketChannel.class);
        // 设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        // 设置禁用nagle算法
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        // 设置连接超时时长
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);
        // 设置初始化Channel
        bootstrap.handler(new TCPChannelInitializerHandler());
        try {
            Channel channel = bootstrap.connect(currentHost, currentPort).sync().channel();
            /**等待客户端链路关闭*/
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }

    private class TCPChannelInitializerHandler extends ChannelInitializer<Channel> {
        @Override
        protected void initChannel(Channel ch) throws Exception {
            final ChannelPipeline pipeline = ch.pipeline();


            // netty提供的自定义长度解码器，解决TCP拆包/粘包问题
            pipeline.addLast("frameEncoder", new LengthFieldPrepender(2));
            pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535,
                    0, 2, 0, 2));

            // 增加protobuf编解码支持
            pipeline.addLast(new ProtobufEncoder());
            pipeline.addLast(new ProtobufDecoder(TransMessageProtobuf.TransMessage.getDefaultInstance()));

            pipeline.addFirst(IdleStateHandler.class.getSimpleName(), new IdleStateHandler(
                    60000 * 3, 60000, 60000 * 4, TimeUnit.MILLISECONDS));

            pipeline.addFirst(SslHandler.class.getSimpleName(), new SslHandler(sslEngine));
            pipeline.addLast(NettySocketSSLHandler.class.getSimpleName(), new NettySocketSSLHandler());
        }
    }

    private void initKeyStore(String caFileName) {
        TrustManagerFactory trustManagerFactory = null;
        KeyManagerFactory keyManagerFactory = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            InputStream in = new ClassPathResource(caFileName).getInputStream();
            keyStore.load(in, clientPassword.toCharArray());

            keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, clientPassword.toCharArray());

            trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);
            initSSL(keyManagerFactory, trustManagerFactory);
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
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }
    }

    private void initSSL(KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws NoSuchAlgorithmException, KeyManagementException {

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
        sslEngine = sslContext.createSSLEngine();
        sslEngine.setUseClientMode(true);

        logger.info("支持的协议: " + Arrays.asList(sslEngine.getSupportedProtocols()));
        logger.info("启用的协议: " + Arrays.asList(sslEngine.getEnabledProtocols()));
        logger.info("支持的加密套件: " + Arrays.asList(sslEngine.getSupportedCipherSuites()));
        logger.info("启用的加密套件: " + Arrays.asList(sslEngine.getEnabledCipherSuites()));

    }
}
