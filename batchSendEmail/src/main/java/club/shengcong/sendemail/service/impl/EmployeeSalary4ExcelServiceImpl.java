package club.shengcong.sendemail.service.impl;

import club.shengcong.sendemail.entity.EmployeeSalary4Excel;
import club.shengcong.sendemail.service.EmployeeSalary4ExcelService;
import club.shengcong.sendemail.util.EmailUtils;
import club.shengcong.sendemail.vo.EmployeeSalary4ExcelVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
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
    // 收件人
    private String to;

    // 发件人
    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.cc}")
    private String cc;

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public void sendData(List<EmployeeSalary4Excel> employeeSalary4ExcelLists) {
        // key 是 emailAddress value是对应的表格行数据
        Map<String, List<EmployeeSalary4Excel>> empsMap = new HashMap<>(
                employeeSalary4ExcelLists.stream()
                        .collect(Collectors.groupingBy(EmployeeSalary4Excel::getEmailAddress, Collectors.toList()))
        );
        // send email
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
            this.to = key;
            // 实体类转map
            Map<String, Object> map = JSON.parseObject(JSON.toJSONString(employeeSalary4ExcelVOS.get(0)), Map.class);
            list.add(map);
            this.setEmailDateAndSend(list);
            list.clear();
        }

    }

    /**
     * 发送邮件
     *
     * @param list <Map<String, Object> key 是实体类属性 ,value是值
     * @author 金聖聰
     * @email jinshengcong@163.com
     * Modification History:
     * Date         Author        Description        version
     * --------------------------------------------------------*
     * 2022/1/14 1:37    金聖聰     修改原因            1.0
     */
    private void setEmailDateAndSend(List<Map<String, Object>> list) {
        String subject = "本月工资条请查收";
        String body;
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            body = EmailUtils.buildContent(list); // 正文，可以用html格式的哟
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(this.to);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setCc(this.cc);
            mimeMessageHelper.setText(body, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}
