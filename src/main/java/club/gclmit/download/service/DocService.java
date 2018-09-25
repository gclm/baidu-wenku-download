package club.gclmit.download.service;

import club.gclmit.download.entity.Doc;
import club.gclmit.download.util.ResultMsg;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 孤城落寞
 * @since 2018-09-15
 */
public interface DocService extends IService<Doc> {


    ResultMsg findDoc(String url);


    ResultMsg downloadDoc(String doc_id,Map<String, String> docInfo);


    String pretreatmentURL(String url);
}
