package club.gclmit.download.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.util
 * @author: gclm
 * @date: 2018/9/8 下午9:13
 * @description:  http/net 工具类
 */
public class HttpClient {


    /**
     * 发送get 请求
     * @param url  请求url
     * @param encode  请求编码格式
     * @param headers  请求头
     * @return  返回响应内容
     * @throws IOException
     */
    public  String sendGet(String url,String encode,Map<String,String> headers) throws IOException {

         String body = "";
         CloseableHttpClient httpClient = HttpClients.createDefault();

         HttpGet httpGet = new HttpGet(url);

        //      设置请求头
        if(headers != null){
            for (Map.Entry<String,String> entry : headers.entrySet()){
                httpGet.setHeader(entry.getKey(),entry.getValue());
            }
        }

         HttpResponse response = httpClient.execute(httpGet);

         HttpEntity entity = response.getEntity();


         if(entity != null){
             body = EntityUtils.toString(entity,encode);
         }


        EntityUtils.consume(entity);

         return  body;
    }

    public void getViewSource(String url,String encode,Map<String,String> headers) throws Exception {

         String body = "";
         CloseableHttpClient httpClient = HttpClients.createDefault();

         HttpGet httpGet = new HttpGet(url);

        //      设置请求头
        if(headers != null){
            for (Map.Entry<String,String> entry : headers.entrySet()){
                httpGet.setHeader(entry.getKey(),entry.getValue());
            }
        }

         HttpResponse response = httpClient.execute(httpGet);

         HttpEntity entity = response.getEntity();


         if(entity != null){

             BufferedReader reader = new BufferedReader(
                new InputStreamReader(entity.getContent()));
             String str = null;
             if ( null !=(str = reader.readLine())){
                 System.out.println(str);
             }
         }
    }


    /**
     * 发送 post 请求
     * @param url 请求的url
     * @param encode 请求的编码格式
     * @param valuePair 请求参数
     * @param headers  请求头参数
     * @return
     * @throws IOException
     */
    public String sendPost(String url, String encode, Map<String,String> valuePair,Map<String,String> headers) throws IOException {
        String body = "";

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost();

//      设置请求头
        if(headers != null){
            for (Map.Entry<String,String> entry : headers.entrySet()){
                httpPost.setHeader(entry.getKey(),entry.getValue());
            }
        }

//      设置请求参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        if (valuePair != null){
            for (Map.Entry<String,String> entry : valuePair.entrySet()){
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        httpPost.setEntity(new UrlEncodedFormEntity(nvps,encode));

        System.out.println("请求地址:" + url);

        CloseableHttpResponse response = httpClient.execute(httpPost);

        HttpEntity entity = response.getEntity();


        if(entity != null){
            body = EntityUtils.toString(entity, encode);
        }

        EntityUtils.consume(entity);

        response.close();

        return  body;
    }

    /**
     * 发送获取请求头
     * @param url  请求url
     * @param encode  请求的编码格式
     * @param headers  请求头参数
     * @return
     * @throws IOException
     */
    public String sendHead(String url,String encode,Map<String,String> headers) throws IOException {

        String head = "";

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpHead httpHead = new HttpHead(url);


        if(headers != null){
            for (Map.Entry<String,String> entry : headers.entrySet()){
                httpHead.setHeader(entry.getKey(),entry.getValue());
            }
        }

        CloseableHttpResponse response = httpClient.execute(httpHead);

        HttpEntity entity = response.getEntity();

        if(entity != null){
           head = EntityUtils.toString(entity, encode);
        }


        EntityUtils.consume(entity);

        response.close();

        return  head;
    }


}
