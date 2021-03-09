package com.daysurprise.study.rpc2.framework.protocol.http;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * @Class: com.daysurprise.study.rpc2.framework.protocol.http.HttpServer
 * @Author: daysurprise
 * @Date: 2021/3/2
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class HttpServer {


    public void start(String host, Integer port){
        // 启动一个内置tomcat
        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);

        StandardEngine engine = new StandardEngine();
        engine.setDefaultHost(host);

        StandardHost standardHost = new StandardHost();
        standardHost.setName(host);

        String contextPath = "";
        StandardContext standardContext = new StandardContext();
        standardContext.setPath(contextPath);
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());

        standardHost.addChild(standardContext);
        engine.addChild(standardHost);

        service.setContainer(engine);
        service.addConnector(connector);

        tomcat.addServlet(contextPath,"dispatcher", new DispatchServlet());
        standardContext.addServletMappingDecoded("/*","dispatcher");

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
