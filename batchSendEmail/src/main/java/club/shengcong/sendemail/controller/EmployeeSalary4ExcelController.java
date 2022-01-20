package club.shengcong.sendemail.controller;


import club.shengcong.sendemail.entity.EmployeeSalary4Excel;
import club.shengcong.sendemail.listener.EmployeeSalary4ExcelListener;
import club.shengcong.sendemail.service.EmployeeSalary4ExcelService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;


/**
 * 员工工资 的 Excel 控制器
 *
 * @author 金聖聰
 * @version v1.0
 * @email jinshengcong@163.com
 * @since Created in 2022/1/13 23:07
 */
@RestController
@RequestMapping("/excel")
public class EmployeeSalary4ExcelController {
    @Resource
    private EmployeeSalary4ExcelService employeeSalary4ExcelService;

    @RequestMapping("/readAndSendEmpSalExcelData")
    public String readExcel2EmployeeSalary(MultipartFile file) {
        return readEmpSalaryExcel(file);

    }

    private String readEmpSalaryExcel(MultipartFile file) {
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(file.getInputStream(), EmployeeSalary4Excel.class,
                    new EmployeeSalary4ExcelListener(employeeSalary4ExcelService)).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).headRowNumber(3).build();
            excelReader.read(readSheet);
            return "哇偶宝贝,聪聪为您批量下发工资条成功,快去倒杯咖啡,然后康康收件箱叭~";
        } catch (IOException e) {
            e.printStackTrace();
            return "啊呀巴比Q了,下发失败,快去找聪聪~";
        }finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }
}
