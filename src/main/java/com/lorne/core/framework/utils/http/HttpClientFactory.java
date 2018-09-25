//package com.lorne.core.framework.utils.http;
//
//import org.apache.http.HttpEntityEnclosingRequest;
//import org.apache.http.HttpRequest;
//import org.apache.http.NoHttpResponseException;
//import org.apache.http.client.HttpRequestRetryHandler;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.ConnectTimeoutException;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.protocol.HttpContext;
//
//import javax.net.ssl.SSLException;
//import javax.net.ssl.SSLHandshakeException;
//import java.io.IOException;
//import java.io.InterruptedIOException;
//import java.net.UnknownHostException;
//
///**
// * Created by yuliang on 2016/3/21.
// */
//public class HttpClientFactory {
//
//    public static CloseableHttpClient createHttpClient() {
//        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
//        ConnectionSocketFactory sslsf = new EasySSLConnectionSocketFactory();
//        //SSLConnectionSocketFactory.getSocketFactory();
//        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", plainsf)
//                .register("https", sslsf)
//                .build();
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
//        // 将最大连接数增加到200
//        cm.setMaxTotal(200);
//        // 将每个路由基础的连接增加到20
//        cm.setDefaultMaxPerRoute(20);
//        //请求重试处理
//        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
//            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
//                if (executionCount >= 5) {// 如果已经重试了5次，就放弃
//                    return false;
//                }
//                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
//                    return true;
//                }
//                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
//                    return false;
//                }
//                if (exception instanceof InterruptedIOException) {// 超时
//                    return false;
//                }
//                if (exception instanceof UnknownHostException) {// 目标服务器不可达
//                    return false;
//                }
//                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
//                    return false;
//                }
//                if (exception instanceof SSLException) {// ssl握手异常
//                    return false;
//                }
//
//                HttpClientContext clientContext = HttpClientContext.adapt(context);
//                HttpRequest request = clientContext.getRequest();
//                // 如果请求是幂等的，就再次尝试
//                if (!(request instanceof HttpEntityEnclosingRequest)) {
//                    return true;
//                }
//                return false;
//            }
//        };
//        CloseableHttpClient httpClient =  HttpClients.custom()
//                .setConnectionManager(cm)
//                .setRetryHandler(httpRequestRetryHandler)
//                .build();
//        return httpClient;
//
//    }
//}
