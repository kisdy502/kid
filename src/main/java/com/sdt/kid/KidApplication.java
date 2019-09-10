package com.sdt.kid;

import com.sdt.kid.aio.NettyServerDemo;
import com.sdt.kid.aio.ssl.OneWaySSLServer;
import com.sdt.kid.aio.ssl.OneWaySSLClient;
import com.sdt.kid.aio.ssl.TwoWaySSLClient;
import com.sdt.kid.aio.ssl.TwoWaySSLServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

@SpringBootApplication
public class KidApplication implements CommandLineRunner {

    @Resource
    NettyServerDemo demo;

    @Resource
    OneWaySSLServer oneWaySSLServer;
    @Resource
    OneWaySSLClient oneWaySSLClient;

    @Resource
    TwoWaySSLServer twoWaySSLServer;
    @Resource
    TwoWaySSLClient twoWaySSLClient;


    private static Logger logger = LoggerFactory.getLogger(KidApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(KidApplication.class);
        application.addListeners(new MyApplicationStartingEvent());
        ApplicationContext context = application.run(args);
//        String[] beanNames = context.getBeanDefinitionNames();
//        logger.info("bean总数:{}", context.getBeanDefinitionCount());
//        int i = 0;
//        for (String str : beanNames) {
//            logger.info("{},beanName:{}", ++i, str);
//        }


    }

    @Override
    public void run(String... args) throws Exception {
        demo.startServer(args);
        //oneWaySSLServer.start();
        //oneWaySSLClient.start();
        //twoWaySSLServer.start();
        //twoWaySSLClient.start();
    }
}
