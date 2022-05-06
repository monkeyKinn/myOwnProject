package club.shengcong.sendemail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 备案控制类
 *
 * @author 金聖聰
 * @version v1.0
 * @email jinshengcong@163.com
 * @since Created in 2022/5/6 15:18
 */
@Controller
public class PutOnRecordController {
    @RequestMapping("/")
    public String authToExamineList(){
        return "beian";
    }
}
