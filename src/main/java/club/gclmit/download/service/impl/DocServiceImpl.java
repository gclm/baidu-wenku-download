package club.gclmit.download.service.impl;

import club.gclmit.download.api.BaiduWenkuApi;
import club.gclmit.download.entity.Doc;
import club.gclmit.download.enums.DocType;
import club.gclmit.download.mapper.DocMapper;
import club.gclmit.download.service.DocService;
import club.gclmit.download.util.FileOutput;
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

    @Override
    public String findDoc(String url) {
       String docUrl = null;

       String doc_id = baiduWenkuApi.getDoc_id(url);

       Map<String, String> docInfo = baiduWenkuApi.getDocInfo(doc_id);

        String docType = docInfo.get("docType");
        String docTitle = docInfo.get("docTitle");

        Doc doc = baseMapper.selectOne(new QueryWrapper<Doc>()
                 .eq("doc_id",doc_id)
                 .eq("doc_type",DocType.getDocType(Integer.parseInt(docType)))
                 .eq("doc_title",docTitle));

        if(doc != null){
            docUrl = doc.getDocUrl();
        }else{
            if ("4".equals(docType) || "1".equals(docType)) {
                docUrl = downloadDoc(url,docInfo);
            }else{
                docUrl =  downloadDoc(doc_id,docInfo);
            }
        }

        return docUrl;
    }

    @Override
    public String downloadDoc(String doc_id,Map<String, String> docInfo) {

        String docUrl = null;

        String docType = docInfo.get("docType");
        String docTitle = docInfo.get("docTitle");

        if ("4".equals(docType) || "1".equals(docType)) {
            System.out.println("下载文件格式为 word");
            docUrl = baiduWenkuApi.getDocPath(docTitle,DocType.getDocType(Integer.parseInt(docType)));
            new FileOutput().downloadWord(docUrl,baiduWenkuApi.getWord(doc_id));
            doc_id = baiduWenkuApi.getDoc_id(doc_id);
        }

        if ("8".equals(docType)) {
            System.out.println("下载文件格式为 Txt");
            List<String> texts = baiduWenkuApi.getText(doc_id, docInfo.get("totalPageNum"), docInfo.get("md5sum"), docInfo.get("rsign"));
            docUrl = baiduWenkuApi.getDocPath(docTitle,"txt");
            new FileOutput().txtWriteFile(docUrl,texts);
        }

        if ("7".equals(docType)) {
            System.out.println("下载文件格式为 pdf");
            docUrl = null;
        }

        if ("3".equals(docType) ||"6".equals(docType) ) {
            System.out.println("下载文件格式为 ppt");
            List<String> imgs = baiduWenkuApi.getPpt(doc_id);
            docUrl = baiduWenkuApi.getDocPath(docTitle,"pdf");
            new FileOutput().imgToPdf(docUrl,imgs);
        }

//public Doc(String docId, String docType, String docUrl, String docTitle) {
        int insert = baseMapper.insert(new Doc(doc_id, DocType.getDocType(Integer.parseInt(docType)), docUrl, docTitle));
        if(insert == 1){
            log.info("文件插入数据成功");
        }
        return docUrl;
    }
}
