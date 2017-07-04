package com.lorne.core.framework.utils.http;


import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * httpclient 4.5.2 请求工具类
 * Created by yuliang on 2016/3/21.
 */
public class HttpUtils {


    /**
     * 执行http请求方法
     *
     * @param httpClient
     * @param request
     * @return
     */
    public static String execute(CloseableHttpClient httpClient, HttpRequestBase request) {
        String res = null;
        try {
            CloseableHttpResponse response = httpClient.execute(request, CookieContext.createHttpClientContext());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) {
                String location = response.getFirstHeader("Location").getValue();
                return get(location);
            }
            res = IOUtils.getStringFromInputStream(response);
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return res;
    }


    /**
     * http get请求
     *
     * @param url 请求地址
     * @return
     */
    public static String get(String url) {
        CloseableHttpClient httpClient = HttpClientFactory.createHttpClient();
        HttpGet request = new HttpGet(url);
        return execute(httpClient, request);
    }


    /**
     * http post请求
     *
     * @param url 请求地址
     * @return
     */
    public static String post(String url) {
        List<PostParam> params = null;
        return post(url, params);
    }


    public static String post(String url, List<PostParam> params) {
        CloseableHttpClient httpClient = HttpClientFactory.createHttpClient();
        HttpPost request = new HttpPost(url);
        if (params != null) {
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (PostParam param : params) {
                NameValuePair pair = new BasicNameValuePair(param.getName(), param.getValue());
                pairList.add(pair);
            }
            request.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
        }

        return execute(httpClient, request);
    }

    /**
     * http post请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    public static String post(String url, PostParam... params) {
        CloseableHttpClient httpClient = HttpClientFactory.createHttpClient();
        HttpPost request = new HttpPost(url);
        if (params != null) {
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.length);
            for (PostParam param : params) {
                NameValuePair pair = new BasicNameValuePair(param.getName(), param.getValue());
                pairList.add(pair);
            }
            request.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
        }
        return execute(httpClient, request);
    }

    public static String post(String url, String postParams) {
        HttpEntity postEntity = null;
        if (postParams != null && !postParams.equalsIgnoreCase("")) {
            String[] params = postParams.split("&");
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            if (params != null && params.length > 0)
                for (String param : params) {
                    try {
                        param = URLDecoder.decode(param, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String[] nv = param.split("=");
                    if (nv.length == 1) {
                        formParams.add(new BasicNameValuePair(nv[0], ""));
                    } else {
                        formParams.add(new BasicNameValuePair(param.substring(
                                0, param.indexOf("=")), param.substring(param
                                .indexOf("=") + 1)));
                    }
                }
            try {
                postEntity = new UrlEncodedFormEntity(formParams, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        CloseableHttpClient httpClient = HttpClientFactory.createHttpClient();
        HttpPost request = new HttpPost(url);
        if (postEntity != null) {
            request.setEntity(postEntity);
        }
        return execute(httpClient, request);
    }

    /**
     * 提交字符串数据
     *
     * @param url
     * @param str
     * @return
     * @throws Exception
     */
    public static String postString(String url, String str) throws Exception {
        HttpEntity postEntity = new StringEntity(str);
        CloseableHttpClient httpClient = HttpClientFactory.createHttpClient();
        HttpPost request = new HttpPost(url);
        if (postEntity != null) {
            request.setEntity(postEntity);
        }
        return execute(httpClient, request);
    }

    /**
     * http 下载请求
     *
     * @param url  请求地址
     * @param path 保存路径
     * @return
     */
    public static boolean download(String url, String path) {
        CloseableHttpClient httpClient = HttpClientFactory.createHttpClient();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = null;
        boolean res = false;
        try {
            response = httpClient.execute(request, CookieContext.createHttpClientContext());
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity httpEntity = response.getEntity();
                InputStream is = httpEntity.getContent();
                if (!new File(path).exists()) {
                    new File(path).getParentFile().mkdirs();
                    new File(path).createNewFile();
                }
                FileUtils.copyInputStreamToFile(is, new File(path));
                if (httpEntity != null) {
                    EntityUtils.consume(httpEntity);
                }
                res = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return res;
    }


    /**
     * http 提交文件
     *
     * @param url        请求地址
     * @param fileParams 文件参数
     * @return
     */
    public static String postFile(String url, PostFileParam... fileParams) {
        if (fileParams == null) {
            return null;
        }
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Charset.forName("UTF-8"));
        for (PostFileParam fileParam : fileParams) {
            if (fileParam.getValue() instanceof File) {
                if (fileParam.getContentType() != null) {
                    File file = (File) fileParam.getValue();
                    FileBody fileBody = new FileBody(file, fileParam.getContentType());
                    builder.addPart(fileParam.getName(), fileBody);
                } else {
                    File file = (File) fileParam.getValue();
                    FileBody fileBody = new FileBody(file);
                    builder.addPart(fileParam.getName(), fileBody);
                }
            } else if (fileParam.getValue() instanceof String) {
                if (fileParam.getContentType() != null) {
                    String str = (String) fileParam.getValue();
                    StringBody stringBody = new StringBody(str, fileParam.getContentType());
                    builder.addPart(fileParam.getName(), stringBody);
                } else {
                    String str = (String) fileParam.getValue();
                    StringBody stringBody = new StringBody(str, ContentType.MULTIPART_FORM_DATA);
                    builder.addPart(fileParam.getName(), stringBody);
                }
            } else if (fileParam.getValue() instanceof InputStream) {
                if (fileParam.getContentType() != null) {
                    InputStream inputStream = (InputStream) fileParam.getValue();
                    InputStreamBody fileBody = new InputStreamBody(inputStream, fileParam.getContentType());
                    builder.addPart(fileParam.getName(), fileBody);
                } else {
                    InputStream inputStream = (InputStream) fileParam.getValue();
                    InputStreamBody fileBody = new InputStreamBody(inputStream, ContentType.APPLICATION_OCTET_STREAM);
                    builder.addPart(fileParam.getName(), fileBody);
                }
            }
        }
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        CloseableHttpClient httpClient = HttpClientFactory.createHttpClient();
        HttpPost request = new HttpPost(url);
        request.setEntity(builder.build());
        return execute(httpClient, request);
    }


    /**
     * http post json请求
     *
     * @param url  请求地址
     * @param json 请求参数
     * @return
     */
    public static String postJson(String url, String json) {
        return postString(url, json, "application/json");
    }

    /**
     * http post string请求
     *
     * @param url  请求地址
     * @param data 请求数据
     * @param type 请求数据格式
     * @return
     */
    public static String postString(String url, String data, String type) {
        CloseableHttpClient httpClient = HttpClientFactory.createHttpClient();
        HttpPost request = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(data, "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType(type);
        request.setEntity(stringEntity);
        return execute(httpClient, request);
    }


    /**
     * http post xml请求
     *
     * @param url 请求地址
     * @param xml 请求参数
     * @return
     */
    public static String postXml(String url, String xml) {
        return postString(url, xml, "text/xml");
    }

}