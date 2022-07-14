package club.shengcong.sendemail.util;


import cn.hutool.core.lang.Console;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 邮件工具类
 *
 * @author 金聖聰
 * @version v1.0
 * @email jinshengcong@163.com
 * @since Created in 2022/1/14 0:48
 */
public class EmailUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtils.class);

    /**
     * 把传过来的list数据拼接
     * @param list 传过来的map数据
     * @return java.lang.String
     * @author 金聖聰
     * @email jinshengcong@163.com
     * Modification History:
     * Date         Author        Description        version
     *--------------------------------------------------------*
     * 2022/1/21 0:53    金聖聰     修改原因            1.0
     */
    public static String buildContent(List<Map<String, Object>> list) throws IOException {
        //加载邮件html模板
        String fileName = "/pod-scale-alarm.html";
        InputStream inputStream = PropertiesUtil.class.getResourceAsStream(fileName);

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            Console.log("读取文件失败，fileName:{}", fileName, e);

            LOGGER.error("读取文件失败，fileName:{}", fileName, e);
        } finally {
            inputStream.close();
            fileReader.close();
        }

        String contentText = "以下是您的本月工资明细, 敬请查看! 如有任何问题请咨询物源行政部，如核对无误请回复此邮件,谢谢！" +
                "<br>Below is your monthly salary details, please check. If you have any questions, " +
                "please consult the Administration Department of WuYuan. If you check it correctly, please reply to this " +
                "email. Thank you！";
        //邮件表格header
        String header =
                "<td>序号</td>" +
                        "<td>月份</td>" +
                        "<td>职工姓名</td>" +
                        "<td>部门</td>" +
                        "<td>岗位工资</td>" +
                        "<td>技能工资</td>" +
                        "<td>通讯补贴</td>" +
                        "<td>岗位津贴</td>" +
                        "<td>工龄工资</td>" +
                        "<td>高温费</td>" +
                        "<td>餐费补贴</td>" +
                        "<td>社保基数</td>" +
                        "<td>个人养老&个人失业&个人医疗" +
                        "<td>个人公积金</td>" +
                        "<td>个人小计</td>" +
                        "<td>病假天数</td>" +
                        "<td>病假扣款</td>" +
                        "<td>事假天数</td>" +
                        "<td>事假扣款</td>" +
                        "<td>迟到扣款</td>" +
                        "<td>小计</td>" +
                        "<td>应税工资</td>" +
                        "<td>代扣个人所得税</td>" +
                        "<td>岗位实发工资</td>" +
                        "<td>技能绩效实发工资</td>" +
                        "<td>合计</td>";
        StringBuilder linesBuffer = new StringBuilder();
        if (list != null && list.size() > 0) {
            list.forEach(map -> {
                linesBuffer.append(
                        "<tr><td>" + map.get("serialNumber") +
                                "</td><td>" + map.get("month") +
                                "</td><td>" + map.get("employeeName") +
                                "</td><td>" + map.getOrDefault("department", "无") +
                                "</td><td>" + map.getOrDefault("wageJobs", "无") +
                                "</td><td>" + map.getOrDefault("skillSalary", "无") +
                                "</td><td>" + map.getOrDefault("communicationAllowance", "无") +
                                "</td><td>" + map.getOrDefault("postAllowance", "无") +
                                "</td><td>" + map.getOrDefault("senioritySalary", "无") +
                                "</td><td>" + map.getOrDefault("highTemperatureFee", "无") +
                                "</td><td>" + map.getOrDefault("mealAllowance", "无") +
                                "</td><td>" + map.getOrDefault("socialSecurityBase", "无") +
                                "</td><td>" + map.getOrDefault("personal", "无") +
                                "</td><td>" + map.getOrDefault("personalProvidentFund", "无") +
                                "</td><td>" + map.getOrDefault("personalSubtotal", "无") +
                                "</td><td>" + map.getOrDefault("sickDays", "无") +
                                "</td><td>" + map.getOrDefault("sickLeaveDeduction", "无") +
                                "</td><td>" + map.getOrDefault("personalLeaveDays", "无") +
                                "</td><td>" + map.getOrDefault("personalLeaveDeduction", "无") +
                                "</td><td>" + map.getOrDefault("lateCharge", "无") +
                                "</td><td>" + map.getOrDefault("subtotal", "无") +
                                "</td><td>" + map.getOrDefault("taxableSalary", "无") +
                                "</td><td>" + map.getOrDefault("withholdingPersonalIncomeTax", "无") +
                                "</td><td>" + map.getOrDefault("postActualSalary", "无") +
                                "</td><td>" + map.getOrDefault("skillPerformancePayroll", "无") +
                                "</td><td>" + map.getOrDefault("total", "无") +
                                "</td></tr>");
            });
        }
        //颜色
        String emailHeadColor = "#fa5834";
        String date = DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss");
        //填充html模板中的五个参数
        String htmlText = MessageFormat.format(buffer.toString(), emailHeadColor, contentText, date, header, linesBuffer.toString());
        //改变表格样式
        htmlText = htmlText.replaceAll("<td>", "<td  style=\"padding:6px 6px; line-height: 150%;border: 1px solid " +
                "#eee; width: 100%; word-wrap: break-word;\">");
        return htmlText;
    }
}
