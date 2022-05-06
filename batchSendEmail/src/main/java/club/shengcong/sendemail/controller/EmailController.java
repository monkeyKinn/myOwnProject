package club.shengcong.sendemail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 电子邮件跳转类
 *
 * @author 金聖聰
 * @version v1.0
 * @email jinshengcong@163.com
 * @since Created in 2022/5/6 15:24
 */
@Controller
public class EmailController {
    @RequestMapping("/CuteWusx")
    public String cuteWusx(){
        return "index";
    }
}
