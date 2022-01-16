package club.shengcong.sendemail.service;

import club.shengcong.sendemail.entity.EmployeeSalary4Excel;

import java.util.List;

/**
 * 员工工资excel的服务类
 *
 * @author 金聖聰
 * @version v1.0
 * @email jinshengcong@163.com
 * @since Created in 2022/1/13 23:11
 */
public interface EmployeeSalary4ExcelService {

    void sendData(List<EmployeeSalary4Excel> employeeSalary4Excels);
}
