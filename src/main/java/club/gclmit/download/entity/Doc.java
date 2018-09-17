package club.gclmit.download.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 孤城落寞
 * @since 2018-09-15
 */
@TableName("tb_doc")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Doc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Getter @Setter
    private String docId;

    @Getter @Setter
    private String docType;

    @Getter @Setter
    private String docUrl;

    @Getter @Setter
    private String docTitle;


    public Doc(String docId, String docType, String docUrl, String docTitle) {
        this.docId = docId;
        this.docType = docType;
        this.docUrl = docUrl;
        this.docTitle = docTitle;
    }

    @Override
    public String toString() {
        return "Doc{" +
        "id=" + id +
        ", docId=" + docId +
        ", docType=" + docType +
        ", docUrl=" + docUrl +
        "}";
    }
}
