package club.gclmit.download.service;

import club.gclmit.download.entity.Doc;
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


    String findDoc(String url);


    String downloadDoc(String doc_id,Map<String, String> docInfo);
}
