package club.gclmit.download.controller;


import club.gclmit.download.service.DocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/search")
    public ModelAndView index(){
        return new ModelAndView("/index");
    }

    @GetMapping("/hello")
    public String hello(){
        return  "hello, world";
    }


    @PostMapping("/search")
    public void  getAnalysisUrl(@RequestParam(value ="url") String url){
        System.out.println("url"+url);
        String pattern = "/^https?:\\/\\/(([a-zA-Z0-9_-])+(\\.)?)*(:\\d+)?(\\/((\\.)?(\\?)?=?&?[a-zA-Z0-9_-](\\?)?)*)*$/i";
        boolean matches = Pattern.matches(pattern, url);


        if(matches){
            String doc = docService.findDoc(url);
        }
        System.out.println(matches);

    }

//    public String getOriginalUrl(String )
}

