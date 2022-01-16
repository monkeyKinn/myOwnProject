package club.shengcong.sendemail.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 邮件模板展示实体类的VO
 *
 * @author 金聖聰
 * @version v1.0
 * @email jinshengcong@163.com
 * @since Created in 2022/1/14 0:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalary4ExcelVO implements Serializable {
    private static final long serialVersionUID = -7486576207901604622L;
    /** 序号 */
    private String serialNumber;
    /** 月份 */
    private String month;
    /** 职工姓名 */
    private String employeeName;
    /** 部门 */
    private String department;
    /** 岗位工资 */
    private String wageJobs;
    /** 技能工资 */
    private String skillSalary;
    /** 餐费补贴 */
    private String mealAllowance;
    /** 社保基数 */
    private String socialSecurityBase;
    // /** 个人养老 */
    // private String personalPension;
    // /** 个人失业 */
    // private String individualUnemployment;
    // /** 个人医疗 */
    // private String personalMedical;
    private String personal;
    /** 个人公积金 */
    private String personalProvidentFund;
    /** 个人小计 */
    private String personalSubtotal;
    /** 病假天数 */
    private String sickDays;
    /** 病假扣款 */
    private String sickLeaveDeduction;
    /** 事假天数 */
    private String personalLeaveDays;
    /** 事假扣款 */
    private String personalLeaveDeduction;
    /** 迟到扣款 */
    private String lateCharge;
    /** 小计 */
    private String subtotal;
    /** 应税工资 */
    private String taxableSalary;
    /** 代扣个人所得税 */
    private String withholdingPersonalIncomeTax;
    /** 岗位实发工资 */
    private String postActualSalary;
    /** 技能绩效实发工资 */
    private String skillPerformancePayroll;
    /** 合计 */
    private String total;
}
