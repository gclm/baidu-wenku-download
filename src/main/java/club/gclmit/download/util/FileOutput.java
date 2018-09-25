package club.gclmit.download.util;

import club.gclmit.download.enums.ResultEnum;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.util
 * @author: gclm
 * @date: 2018/9/12 下午5:38
 * @description: 文件写入工具类
 */
@Slf4j
public class FileOutput {

    private ResultMsg resultMsg = null;

    /**
     * 把 Txt文件写入文本
     * @param txtPath
     * @param texts
     */
    public ResultMsg txtWriteFile(String txtPath,List<String> texts){

        long time = System.currentTimeMillis();
        try {

            // 判断文件是否存在
            fileExits(txtPath);

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(txtPath)));

//          把文本写入缓存区
            for (String txt : texts){
                writer.write(txt);
            }

//          把内容从缓存区写入文本
            writer.flush();
//          关闭文本
            writer.close();

        } catch (IOException e) {
            log.error("\nTxt文件"+txtPath+"写入失败");
            return  new ResultMsg(ResultEnum.NnDownload);
        }

        log.info("\n写入Txt文件"+txtPath+"花费了"+(int) (System.currentTimeMillis() - time)+"时间");

        return new ResultMsg(ResultEnum.Download);
    }


    /**
     *  判断文件是否存在，不存在就创建
     * @param filePath 文件路径
     */
    public void fileExits(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.isDirectory()){
            file.createNewFile();
        }
    }


    /**
     *  图片转换成 pdf 的实现方法
     * @param pdfPath
     * @param imgs
     */
    public ResultMsg imgToPdf(String pdfPath, List<String> imgs){
//      获取当前系统时间 毫秒级
        long time = System.currentTimeMillis();
        try {

//          判断文件是否存在
            fileExits(pdfPath);

            FileOutputStream fileOutputStream = null;

            fileOutputStream = new FileOutputStream(pdfPath);

//          因为我们的图片大部分是来自互联网，所以这里采取通过 url读取输入流的方法获取获取的基本信息
            URL imgUrl = new URL(imgs.get(0));
            URLConnection uri = imgUrl.openConnection();
            InputStream inputStream = uri.getInputStream();
//          获取图片的基本信息
            BufferedImage img = ImageIO.read(inputStream);

//          创建文档
            Document document = new Document(new Rectangle(img.getWidth(),img.getHeight()),0,0,0,0);

            PdfWriter.getInstance(document,fileOutputStream);

            document.open();

            Image image = null;

            for (int i = 0;i<imgs.size();i++){
                log.info("\n当前进度："+i+"/"+imgs.size()+"图片");
                //          实例化图片
                image = Image.getInstance(imgs.get(i));

                image.scaleAbsolute(img.getWidth(),img.getHeight());
//              添加图片到文档
                document.add(image);
            }
//          关闭流
            document.close();

        } catch (Exception e) {
            log.error("\nPDF文件"+pdfPath+"转换失败");
            return new ResultMsg(ResultEnum.NnDownload);
        }

        log.info("\nPDF文件"+pdfPath+"转换图片花费了"+(int) (System.currentTimeMillis() - time)+"时间");
        return new ResultMsg(ResultEnum.Download);
    }


    /**
     *  下载文件
     * @param filePath 文件路径
     * @param url 文件下载链接
     */
    public ResultMsg downloadWord(String filePath,String url){
//      获取当前系统时间 毫秒级
        long time = System.currentTimeMillis();

        try {

//          判断文件是否存在
            fileExits(filePath);

            HttpURLConnection httpURLConnection = null;

            URL httpUrl = new URL(url);

            URLConnection urlConnection = httpUrl.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setUseCaches(true);

            urlConnection.connect();


            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());


            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);


            byte[] buf = new byte[4096];
            int length = bufferedInputStream.read(buf);
            //保存文件
            while(length != -1)
            {
                bos.write(buf, 0, length);
                length = bufferedInputStream.read(buf);
            }
            bos.close();
            bufferedInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            log.error("\n文件"+filePath+"下载失败");
            return new ResultMsg(ResultEnum.NnDownload);
        }

        log.info("\n文件"+filePath+"下载花费了"+(int) (System.currentTimeMillis() - time)+"时间");
        return new ResultMsg(ResultEnum.Download);
    }

}
