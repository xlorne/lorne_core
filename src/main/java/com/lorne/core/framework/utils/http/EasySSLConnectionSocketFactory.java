//package com.lorne.core.framework.utils.http;
//
//import org.apache.http.HttpHost;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.protocol.HttpContext;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSocket;
//import javax.net.ssl.TrustManager;
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.net.Socket;
//
///**
// * Created by yuliang on 2016/6/5.
// */
//public class EasySSLConnectionSocketFactory implements ConnectionSocketFactory {
//
//
//    private SSLContext sslcontext = null;
//
//    @Override
//    public Socket createSocket(HttpContext context) throws IOException {
//        return getSSLContext().getSocketFactory().createSocket();
//    }
//
//
//    private static SSLContext createEasySSLContext() throws IOException {
//        try {
//            SSLContext context = SSLContext.getInstance("TLS");
//            context.init(null, new TrustManager[]{new EasyX509TrustManager(
//                    null)}, null);
//            return context;
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//    private SSLContext getSSLContext() throws IOException {
//        if (this.sslcontext == null) {
//            this.sslcontext = createEasySSLContext();
//        }
//        return this.sslcontext;
//    }
//
//    @Override
//    public Socket connectSocket(int connectTimeout, Socket sock, HttpHost host, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpContext context) throws IOException {
//        SSLSocket sslsock = (SSLSocket) ((sock != null) ? sock : createSocket(context));
//        if (localAddress != null) {
//            InetSocketAddress isa = new InetSocketAddress(localAddress.getAddress(),
//                    localAddress.getPort());
//            sslsock.bind(isa);
//        }
//        sslsock.connect(remoteAddress, connectTimeout);
//        return sslsock;
//    }
//}
