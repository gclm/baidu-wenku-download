package club.gclmit.download.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.util
 * @author: gclm
 * @date: 2018/9/23 8:57 AM
 * @description: url 加密，解密
 */
@Slf4j
public class UrlUtil {


    public static final  String UTF8 = "UTF-8";
    public static final  String GBK = "GBK";

    /**
     * url 按照指定编码格式进行解密
     *
     * @param url 需要加密的内容
     * @param encode  加密的编码格式
     * @return
     */
    public String getURLDecoderString(String url,String encode){
        try {
            url =  URLDecoder.decode(url, encode);
        } catch (UnsupportedEncodingException e) {
           log.error("url 解码失败");
            e.printStackTrace();
        }
        return  url;
    }


    /**
     * url 按照指定编码格式进行加密
     *
     * @param url 需要加密的内容
     * @param encode  加密的编码格式
     * @return
     */
    public String getURLEncoderString(String url,String encode){
        try {
            url = URLEncoder.encode(url, encode);
        } catch (UnsupportedEncodingException e) {
            log.error("url 加密失败");
            e.printStackTrace();
        }
        return  url;
    }


}
