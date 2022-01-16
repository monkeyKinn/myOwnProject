package club.shengcong.sendemail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 员工工资Excel实体类
 *
 * @author 金聖聰
 * @version v1.0
 * @email jinshengcong@163.com
 * @since Created in 2022/1/13 21:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalary4Excel implements Serializable {
    private static final long serialVersionUID = -1643581977233381240L;
    /** 序号 */
    @ExcelProperty(index = 0)
    private String serialNumber;
    /** 月份 */
    @ExcelProperty(index = 1)
    private String month;
    /** 职工姓名 */
    @ExcelProperty(index = 2)
    private String employeeName;
    /** 部门 */
    @ExcelProperty(index = 3)
    private String department;
    /** 岗位工资 */
    @ExcelProperty(index = 4)
    private String wageJobs;
    /** 技能工资 */
    @ExcelProperty(index = 5)
    private String skillSalary;
    /** 餐费补贴 */
    @ExcelProperty(index = 6)
    private String mealAllowance;
    /** 社保基数 */
    @ExcelProperty(index = 7)
    private String socialSecurityBase;
    // /** 个人养老 */
    // @ExcelProperty(index = 8)
    // private String personalPension;
    // /** 个人失业 */
    // @ExcelProperty(index = 9)
    // private String individualUnemployment;
    // /** 个人医疗 */
    // @ExcelProperty(index = 10)
    // private String personalMedical;
    @ExcelProperty(index = 8)
    private String personal;
    /** 个人公积金 */
    @ExcelProperty(index = 9)
    private String personalProvidentFund;
    /** 个人小计 */
    @ExcelProperty(index = 10)
    private String personalSubtotal;
    /** 病假天数 */
    @ExcelProperty(index = 11)
    private String sickDays;
    /** 病假扣款 */
    @ExcelProperty(index = 12)
    private String sickLeaveDeduction;
    /** 事假天数 */
    @ExcelProperty(index = 13)
    private String personalLeaveDays;
    /** 事假扣款 */
    @ExcelProperty(index = 14)
    private String personalLeaveDeduction;
    /** 迟到扣款 */
    @ExcelProperty(index = 15)
    private String lateCharge;
    /** 小计 */
    @ExcelProperty(index = 16)
    private String subtotal;
    /** 应税工资 */
    @ExcelProperty(index = 17)
    private String taxableSalary;
    /** 代扣个人所得税 */
    @ExcelProperty(index = 18)
    private String withholdingPersonalIncomeTax;
    /** 岗位实发工资 */
    @ExcelProperty(index = 19)
    private String postActualSalary;
    /** 技能绩效实发工资 */
    @ExcelProperty(index = 20)
    private String skillPerformancePayroll;
    /** 合计 */
    @ExcelProperty(index = 21)
    private String total;
    /** 邮箱地址 */
    @ExcelProperty(index = 22)
    private String emailAddress;

}
