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

    /**
     * 发送数据
     * @param employeeSalary4Excels  员工工资Excel实体 的 列表
     * @author 金聖聰
     * @email jinshengcong@163.com
     * Modification History:
     * Date         Author        Description        version
     *--------------------------------------------------------*
     * 2022/1/21 0:43    金聖聰     修改原因            1.0
     */
    void sendData(List<EmployeeSalary4Excel> employeeSalary4Excels);

}
