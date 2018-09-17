package club.gclmit.download.api;

import club.gclmit.download.entity.BaiduWenku;
import club.gclmit.download.util.HttpClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.api
 * @author: gclm
 * @date: 2018/9/8 下午5:50
 * @description:百度文库 api
 *
 *
 */
@Slf4j
@Component
public class BaiduWenkuApi {

    @Autowired
    private  BaiduWenku baiduWenku;

    //  创建对象
    private HttpClient httpClient = null;

    private List<String> list = null;

    /**
     *  获取PPT 文档下载链接
     * @param doc_id 文档 id
     * @return
     */
    public List<String> getPpt(String doc_id) {

        log.info("下载文件为 PPT,文档下载路径：" + String.format(baiduWenku.getPpt(), doc_id));

        httpClient = new HttpClient();
        list = new ArrayList<>();

        try {
            String s = httpClient.sendGet(String.format(baiduWenku.getPpt(), doc_id), "UTF-8", getHeaders());

            JSONArray arrays = JSONObject.parseObject(s.substring(5, s.length() - 1)).getJSONArray("list");

            for (int i = 0; i < arrays.size(); i++) {
                list.add(arrays.getJSONObject(i).getString("zoom"));
            }
        } catch (IOException e) {
            log.error("获取 PPT 文件下载链接失败");
            e.printStackTrace();
        }
        return  list;
    }

    /**
     * 获取 Word 文档下载链接
     * @param url 文档 id
     * @return
     */
    public String getWord(String url) {
        return String.format(baiduWenku.getWord(), url);
    }

    /**
     * 获取 PDF 文档下载链接
     * @param doc_id 文档 id
     * @return
     */
    public String getPdf(String doc_id) {
        return String.format(baiduWenku.getPdf(), doc_id);
    }

    /**
     * 获取 Text 文档下载链接
     * @param doc_id 文档 id
     * @return
     *
     *   docInfo.put(" totalPageNum ", totalPageNum);
     *                 docInfo.put("md5sum",md5sum);
     *                 docInfo.put("rsign",rsign);
     *
     */
    public List<String> getText(String doc_id,String totalPageNum,String md5sum,String rsign) {

        List<String> list = new ArrayList<>();
        String url = String.format(baiduWenku.getTxt(),doc_id,totalPageNum,md5sum,rsign);

        log.info("Txt 文件下载路径："+url);

        try {

            String s = httpClient.sendGet(url, "UTF-8", getHeaders());
            JSONArray jsonArray = JSONArray.parseArray(s);
            for (int i = 0;i < jsonArray.size() ;i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONArray parags = jsonObject.getJSONArray("parags");
                list.add(parags.getJSONObject(0).getString("c"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;

    }

    /**
     * 获取文档 详情
     * @param doc_id 文档 id
     * @return
     */
    public Map<String,String> getDocInfo(String doc_id)  {

        Map<String,String> docInfo = new HashMap<>();

        httpClient = new HttpClient();

        try {
            log.info("\n文档详情请求路径：" + String.format(baiduWenku.getDoc(), doc_id));

            String url = String.format(baiduWenku.getDoc(), doc_id);

            String s = httpClient.sendGet(url, "UTF-8",getHeaders());

            JSONObject jsonObjects = JSON.parseObject(s.substring(7, s.length() - 1));

            JSONObject jsonObject = jsonObjects.getJSONObject("docInfo");

            String docTitle = jsonObject.getString("docTitle");

            String docType = jsonObject.getString("docType");

            docInfo.put("doc_id",doc_id);
            docInfo.put("docTitle",docTitle);
            docInfo.put("docType",docType);

            if ("8".equals(docType)) {
                String md5sum = jsonObjects.getString("md5sum");

                String rsign = jsonObjects.getString("rsign");

                String totalPageNum = jsonObject.getString("totalPageNum");

//               Txt 文档下载必备参数
                docInfo.put("totalPageNum",totalPageNum);
                docInfo.put("md5sum",md5sum);
                docInfo.put("rsign",rsign);

                log.info("\nTxt 文档下载参数 totalPageNum"+totalPageNum+" md5sum："+md5sum+" rsign："+rsign);
            }

            log.info("\ndocTitle:" + docTitle + "  docType：" + docType);

        } catch (IOException e) {
            log.error("文档详情获取失败");
            e.printStackTrace();
        }

        return docInfo;
    }

    /**
     *  获取 百度文库的伪造请求头
     * @return  返回伪造请求头的 map 集合
     */
    public Map<String,String>  getHeaders(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("User - Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
        map.put("Accept - Encoding", "gzip, deflate, br");
        map.put("Accept - Language", "zh-CN,zh;q=0.9");
//        map.put("Host", "wkretype.bdimg.com");
        map.put("Pragma", "no-cache");
        map.put("Upgrade-Insecure-Requests", "1");
//        map.put("Referer","https://wenku.baidu.com");

        return  map;
    }

    /**
     *  获取百度文库 doc_id
     * @param url 百度文库URL
     * @return
     */
    public  String  getDoc_id(String url){
        url = url.substring(url.indexOf("view/") + 5, url.indexOf(".html"));
        log.info("\ndoc_id:" + url);
        return  url;
    }


    /**
     * 根据文件 文件名和文件类型 生成文件路径
     * @param docTitle  文件名
     * @param docType   文件类型
     * @return
     */
    public String getDocPath(String docTitle,String docType){
        return  String.format(baiduWenku.getDocPath(),docType,docTitle,docType);
    }
}