package club.shengcong.sendemail.service.impl;

import club.shengcong.sendemail.entity.EmployeeSalary4Excel;
import club.shengcong.sendemail.service.EmployeeSalary4ExcelService;
import club.shengcong.sendemail.util.Email;
import club.shengcong.sendemail.vo.EmployeeSalary4ExcelVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 员工工资条excel服务实现类
 *
 * @author 金聖聰
 * @version v1.0
 * @email jinshengcong@163.com
 * @since Created in 2022/1/13 23:16
 */
@Service
public class EmployeeSalary4ExcelServiceImpl implements EmployeeSalary4ExcelService {
    // 发件人邮箱
    private String userName = "";
    // 发件人密码(不是登录密码是授权码，需要到邮箱里设置开通授权码功能)
    private String password = "";
    // 邮件服务器
    private String smtpHost = "smtp.ym.163.com";
    // 邮件服务器端口号
    private Integer port = 465;
    // 收件人，多个收件人以半角逗号分隔
    private String to = null;
    // 抄送，多个抄送以半角逗号分隔
    private String cc = null;
    // 主题
    private String subject = "本月工资条请查收";


    @Override
    public void sendData(List<EmployeeSalary4Excel> employeeSalary4ExcelLists) {
        Map<String, List<EmployeeSalary4Excel>> empsMap = new HashMap<>(
                employeeSalary4ExcelLists.stream()
                        .collect(Collectors.groupingBy(EmployeeSalary4Excel::getEmailAddress, Collectors.toList()))
        );
        // send email
        // key 是实体类属性 ,value是值
        List<Map<String, Object>> list = new ArrayList<>();
        for (String key : empsMap.keySet()) {

            List<EmployeeSalary4Excel> employeeSalary4Excels = empsMap.get(key);
            List<EmployeeSalary4ExcelVO> employeeSalary4ExcelVOS = new ArrayList<>();
            employeeSalary4Excels.forEach(employeeSalary4Excel -> {
                EmployeeSalary4ExcelVO employeeSalary4ExcelVO = new EmployeeSalary4ExcelVO();
                BeanUtils.copyProperties(employeeSalary4Excel, employeeSalary4ExcelVO);
                employeeSalary4ExcelVOS.add(employeeSalary4ExcelVO);
            });
            // 设置收件人
            this.to=key;
            // 实体类转map
            Map<String, Object> map = JSON.parseObject(JSON.toJSONString(employeeSalary4ExcelVOS.get(0)), Map.class);
            list.add(map);
            try {
                this.send(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.clear();
        }

    }

    /**
     * 发送邮件
     * @param list <Map<String, Object> key 是实体类属性 ,value是值
     * @return void
     * @author 金聖聰
     * @email jinshengcong@163.com
     * Modification History:
     * Date         Author        Description        version
     *--------------------------------------------------------*
     * 2022/1/14 1:37    金聖聰     修改原因            1.0
     */
    private void send(List<Map<String, Object>> list) throws Exception {
        String body = Email.buildContent(list); // 正文，可以用html格式的哟
//        List<String> attachments = Arrays.asList("D:\\tmp\\1.png", "D:\\tmp\\2.png"); // 附件的路径，多个附件也不怕

        Email email = Email.entity(smtpHost, port, userName, password, to, cc, subject, body, null);

        email.send(); // 发送！
    }
}
