package club.shengcong.sendemail.listener;

import club.shengcong.sendemail.entity.EmployeeSalary4Excel;
import club.shengcong.sendemail.service.EmployeeSalary4ExcelService;
import cn.hutool.core.lang.Console;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 工资条实体的监听类
 *
 * @author 金聖聰
 * @version v1.0
 * @email jinshengcong@163.com
 * @since Created in 2022/1/13 22:40
 */
public class EmployeeSalary4ExcelListener extends AnalysisEventListener<EmployeeSalary4Excel> {
    /**
     * 每隔3000条做业务处理，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    List<EmployeeSalary4Excel> contentList = new ArrayList<>();
    private final EmployeeSalary4ExcelService employeeSalary4ExcelService;

    public EmployeeSalary4ExcelListener(EmployeeSalary4ExcelService employeeSalary4ExcelService) {
        this.employeeSalary4ExcelService = employeeSalary4ExcelService;
    }
    /**
     * 这个每一条数据解析都会来调用
     * @param employeeSalary4Excel 每一行的记录
     * @param analysisContext  解析上下文
     * @author 金聖聰
     * @email jinshengcong@163.com
     * Modification History:
     * Date         Author        Description        version
     *--------------------------------------------------------*
     * 2022/1/13 23:00    金聖聰     修改原因            1.0
     */
    @Override
    public void invoke(EmployeeSalary4Excel employeeSalary4Excel, AnalysisContext analysisContext) {
        contentList.add(employeeSalary4Excel);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (contentList.size() >= BATCH_COUNT) {
            sendData();
            // 存储完成清理 list
            contentList.clear();
        }
    }

   /**
    * 所有数据解析完成了 都会来调用
    * @param analysisContext 解析上下文
    * @author 金聖聰
    * @email jinshengcong@163.com
    * Modification History:
    * Date         Author        Description        version
    *--------------------------------------------------------*
    * 2022/1/13 23:02    金聖聰     修改原因            1.0
    */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        sendData();
    }

    /**
     * 加上存储数据库
     */
    private void sendData() {
        Console.log("解析到的数据:"+contentList);
        employeeSalary4ExcelService.sendData(contentList);
    }
}
