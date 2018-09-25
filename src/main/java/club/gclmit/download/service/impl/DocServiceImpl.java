package club.gclmit.download.service.impl;

import club.gclmit.download.api.BaiduWenkuApi;
import club.gclmit.download.entity.Doc;
import club.gclmit.download.enums.DocType;
import club.gclmit.download.enums.ResultEnum;
import club.gclmit.download.mapper.DocMapper;
import club.gclmit.download.service.DocService;
import club.gclmit.download.util.FileOutput;
import club.gclmit.download.util.ResultMsg;
import club.gclmit.download.util.UrlUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 孤城落寞
 * @since 2018-09-15
 */
@Service
@Slf4j
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements DocService {

    @Autowired
    private BaiduWenkuApi baiduWenkuApi;


    /**
     * 查询数据库是否存在该文件，存在返回文件的下载链接，不存在就执行解析下载。
     * @param url
     * @return
     */
    @Override
    public ResultMsg findDoc(String url) {
        ResultMsg docUrl = null;

        String doc_id = baiduWenkuApi.getDoc_id(url);

        Map<String, String> docInfo = baiduWenkuApi.getDocInfo(doc_id);

        String docType = docInfo.get("docType");
        String docTitle = docInfo.get("docTitle");

        Doc doc = baseMapper.selectOne(new QueryWrapper<Doc>()
                 .eq("doc_id",doc_id)
                 .eq("doc_type",DocType.getDocType(Integer.parseInt(docType)))
                 .eq("doc_title",docTitle));

        if(doc != null){
            return new ResultMsg(ResultEnum.Query,doc.getDocUrl());
        }else{
            if ("4".equals(docType) || "1".equals(docType)) {
                ResultMsg resultMsg = downloadDoc(url, docInfo);
                return  resultMsg;
            }else{
                ResultMsg resultMsg =  downloadDoc(doc_id,docInfo);
                return  resultMsg;
            }
        }
    }

    /**
     * 根据文档类型。动态执行下载
     * @param doc_id  文档id
     * @param docInfo  文档详情
     * @return
     */
    @Override
    public ResultMsg downloadDoc(String doc_id,Map<String, String> docInfo) {

        String docDownloadUrl = null;

        ResultMsg resultMsg = null;

        String docType = docInfo.get("docType");
        String docTitle = docInfo.get("docTitle");

        if ("4".equals(docType) || "1".equals(docType)) {
            System.out.println("下载文件格式为 word");
            // 生成文件的下载和写入文件路径
            String docUrl = baiduWenkuApi.getDocPath(docTitle,DocType.getDocType(Integer.parseInt(docType)));
            docDownloadUrl = baiduWenkuApi.getDocDownloadPath(docTitle,DocType.getDocType(Integer.parseInt(docType)));
            log.info("\n docUrl:"+docUrl+"\n docDownloadUrl:"+docDownloadUrl);
            // 执行文件的写入操作
            ResultMsg resultMsg1 = new FileOutput().downloadWord(docUrl, baiduWenkuApi.getWord(doc_id));
            for(int i = 0;i < 3;i++){
                if("下载失败".equals(resultMsg1.getMsg())){
                    resultMsg1 = new FileOutput().downloadWord(docUrl, baiduWenkuApi.getWord(doc_id));
                }else if("下载成功".equals(resultMsg1.getMsg())){
                    resultMsg = new ResultMsg().setResultMsgData(resultMsg1, docDownloadUrl);
                    continue;
                }
            }
            doc_id = baiduWenkuApi.getDoc_id(doc_id);
        }

        if ("8".equals(docType)) {
            System.out.println("下载文件格式为 Txt");
            List<String> texts = baiduWenkuApi.getText(doc_id, docInfo.get("totalPageNum"), docInfo.get("md5sum"), docInfo.get("rsign"));
            // 生成文件的下载和写入文件路径
            String docUrl = baiduWenkuApi.getDocPath(docTitle,"txt");
            docDownloadUrl = baiduWenkuApi.getDocDownloadPath(docTitle,"txt");
            // 执行文件的写入操作
            ResultMsg resultMsg1 = new FileOutput().txtWriteFile(docUrl, texts);
            for(int i = 0;i < 3;i++){
                if("下载失败".equals(resultMsg1.getMsg())){
                    resultMsg1 = new FileOutput().txtWriteFile(docUrl, texts);
                }else if("下载成功".equals(resultMsg1.getMsg())){
                    resultMsg = new ResultMsg().setResultMsgData(resultMsg1, docDownloadUrl);
                    continue;
                }
            }
        }

        if ("7".equals(docType)) {
            System.out.println("下载文件格式为 pdf");
            String docUrl = null;
            resultMsg = new ResultMsg(ResultEnum.NnDownload);
        }

        if ("3".equals(docType) ||"6".equals(docType) ) {
            System.out.println("下载文件格式为 ppt");
            List<String> imgs = baiduWenkuApi.getPpt(doc_id);
//          生成文件的下载和写入文件路径
            String docUrl = baiduWenkuApi.getDocPath(docTitle,"pdf");
            docDownloadUrl = baiduWenkuApi.getDocDownloadPath(docTitle,"pdf");
//          执行文件的写入操作
            ResultMsg resultMsg1 =  new FileOutput().imgToPdf(docUrl,imgs);
            for(int i = 0;i < 3;i++){
                if("下载失败".equals(resultMsg1.getMsg())){
                    resultMsg1 = new FileOutput().imgToPdf(docUrl,imgs);
                }else if("下载成功".equals(resultMsg1.getMsg())){
                    resultMsg = new ResultMsg().setResultMsgData(resultMsg1, docDownloadUrl);
                    continue;
                }
            }
        }
        log.info("文件的下载链接为："+docDownloadUrl);
        int insert = baseMapper.insert(new Doc(doc_id, DocType.getDocType(Integer.parseInt(docType)), docDownloadUrl, docTitle));
        if(insert == 1){
            log.info("文件插入数据成功");
        }
        return resultMsg;
    }

    /**
     *  url 预处理
     *  处理内容为：
     *        1. 去除传进来的 url=
     *        2. url 解密
     * @param url
     * @return
     */
    @Override
    public String pretreatmentURL(String url) {

        log.info("\n未处理的url:"+url);

        int i = url.indexOf("url=");

        if(i != -1){
            url = url.substring(url.indexOf("url=")+4);
        }

//      字符串解密
        url = new UrlUtil().getURLDecoderString(url,UrlUtil.GBK);

        log.info("\n处理后的url:"+url);

        return url;
    }
}
