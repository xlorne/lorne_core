package com.lorne.core.framework.utils.http;


import com.lorne.core.framework.exception.HttpException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * httpclient 4.5.2 请求工具类
 * Created by yuliang on 2016/3/21.
 */
public class HttpUtils {

    private static  Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static RestTemplate restTemplate = new RestTemplate();


    /**
     * http get请求
     *
     * @param url 请求地址
     * @return
     */
    public static String get(String url) {
        return restTemplate.getForObject(url,String.class);
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
        PostParam[] postParams =  new PostParam[params.size()];
        return post(url,params.toArray(postParams));
    }

    /**
     * http post请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return  result 网络结果
     */
    public static String post(String url, PostParam... params) {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        if (params != null&&params.length>0) {
            for(PostParam param:params) {
                map.add(param.getName(), param.getValue());
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        return restTemplate.postForObject( url, request , String.class );
    }

    /**
     *  http post 请求
     * @param url   请求地址
     * @param postParams    字符串型地址
     * @return  网络相应结果
     */
    public static String post(String url, String postParams) {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        if (StringUtils.isNotEmpty(postParams)) {
            String[] params = postParams.split("&");
            for (String param : params) {
                try {
                    param = URLDecoder.decode(param, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String[] nv = param.split("=");
                if (nv.length == 1) {
                    map.add(nv[0], "");
                } else {
                    map.add(param.substring(
                            0, param.indexOf("=")), param.substring(param
                            .indexOf("=") + 1));
                }
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        return restTemplate.postForObject( url, request , String.class );
    }

    /**
     * 提交字符串数据
     *
     * @param url   请求地址
     * @param str   请求数据
     * @return  result 相应结果
     * @throws HttpException  StringEntity(str)转码异常
     */
    public static String postString(String url, String str) throws HttpException {
        HttpEntity<String> requestEntity = new HttpEntity<String>(str);
        return restTemplate.postForObject(url,requestEntity,String.class);
    }

    /**
     * http 下载请求
     *
     * @param url  请求地址
     * @param path 保存路径
     * @return  result 相应结果
     */
    public static boolean download(String url, String path) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Resource> httpEntity = new HttpEntity<Resource>(headers);
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity,byte[].class);
        try {
            FileUtils.writeByteArrayToFile(new File(path),response.getBody());
            return true;
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(),e);
            return false;
        }
    }


    /**
     * http 提交文件
     *
     * @param url        请求地址
     * @param fileParams 文件参数
     * @return  result 相应结果
     */
    public static String postFile(String url, PostFileParam... fileParams) {
        if (fileParams == null) {
            return null;
        }
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        for (PostFileParam fileParam : fileParams) {
            if (fileParam.getValue() instanceof File) {
                File file = (File) fileParam.getValue();
                FileSystemResource resource = new FileSystemResource(file);
                param.add(fileParam.getName(),resource);
            } else if (fileParam.getValue() instanceof String) {
                String value = (String) fileParam.getValue();
                param.add(fileParam.getName(),value);
            }
        }

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String,Object>>(param);
        return restTemplate.patchForObject(url, httpEntity, String.class);
    }


    /**
     * http post json请求
     *
     * @param url  请求地址
     * @param json 请求参数
     * @return  result 相应结果
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
     * @return  result 相应结果
     */
    public static String postString(String url, String data, String type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(type));
        HttpEntity<String> requestEntity = new HttpEntity<String>(data,headers);
        return restTemplate.postForObject(url,requestEntity,String.class);
    }


    /**
     * http post xml请求
     *
     * @param url 请求地址
     * @param xml 请求参数
     * @return  result 相应结果
     */
    public static String postXml(String url, String xml) {
        return postString(url, xml, "text/xml");
    }

}