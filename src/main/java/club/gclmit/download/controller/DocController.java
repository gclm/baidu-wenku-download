package club.gclmit.download.controller;


import club.gclmit.download.enums.ResultEnum;
import club.gclmit.download.service.DocService;
import club.gclmit.download.util.ResultMsg;
import club.gclmit.download.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 孤城落寞
 * @since 2018-09-15
 */

@Slf4j
@RequestMapping("/doc")
@RestController
public class DocController {


    @Autowired
    private DocService docService;

    /**
     * seach 搜索下载链接
     * @return
     */
    @GetMapping("/search")
    public ModelAndView index(){
        return new ModelAndView("/index");
    }

    @GetMapping("/hello")
    public String hello(){
        return  "hello, world";
    }


    /**
     * 流程：
     * 字符串预处理 -> 执行文件解析
     *                      -> 解析成功 -> 开始下载 -> 生成下载链接
     *                      -> 解析失败
     * @param url
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/search")
    public ResultMsg getAnalysisUrl(@Valid String url){

        url = docService.pretreatmentURL(url);

        ResultMsg resultMsg = null;

        if (url.indexOf("wenku.baidu.com/view/") != -1){
            resultMsg = new ResultMsg(ResultEnum.Resolve, docService.findDoc(url).getData());
            log.info("\n解析成功 resultMsg:"+resultMsg);
        }else{
            log.info("\n解析失败");
            resultMsg = new ResultMsg(ResultEnum.UnResolve);
        }
        System.out.println(resultMsg);
        return  resultMsg;
    }
}

