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
import java.util.*;
import java.util.stream.Collectors;

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
     *
     * @param list 传过来的map数据
     * @return java.lang.String
     * @author 金聖聰
     * @email jinshengcong@163.com
     * Modification History:
     * Date         Author        Description        version
     * --------------------------------------------------------*
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
                "please consult the Administration Department of WuYuan. If you check it correctly, please reply to " +
                "this " +
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
        String htmlText = MessageFormat.format(buffer.toString(), emailHeadColor, contentText, date, header,
                linesBuffer.toString());
        //改变表格样式
        htmlText = htmlText.replaceAll("<td>", "<td  style=\"padding:6px 6px; line-height: 150%;border: 1px solid " +
                "#eee; width: 100%; word-wrap: break-word;\">");
        return htmlText;
    }


    public static void main(String[] args) {
        List<String> list;
        List<Player> playerList;
        list = new ArrayList<>();
        // 原始的
        playerList = getPlayers(list);
        System.out.println("原来的");
        new ArrayList<>(playerList).forEach(System.out::println);
        System.out.println("----------------");
        // 去重后的set形式
        Set<Player> playerSetDiff = new TreeSet<>(Comparator.comparing(o -> (o.getName() + o.getSex())));
        playerSetDiff.addAll(playerList);
        // 去重后的list形式
        List<Player> playerListDiff = new ArrayList(playerSetDiff);
        // 原始的和去重后的差集
        List<Player> players = subList1(playerList, playerListDiff);
        System.out.println("去重后的");
        new ArrayList<>(playerListDiff).forEach(System.out::println);
        System.out.println("----------------");
        System.out.println("去重后的与原始的差集");
        new ArrayList<>(players).forEach(System.out::println);
        System.out.println("----------------");
        // 根据name和sex 分组
        System.out.println("去重后的与原始的差集 根据name和sex 分组");
        Map<String, List<Player>> collect = players.stream().collect(
                Collectors.groupingBy(a -> format("{0}#{1}", a.getName(), a.getSex())));
        Set<String> set = collect.keySet();
        for (String o : set) {
            System.out.println(o + "_" + collect.get(o));
        }
        System.out.println("----------------");
        // 去重后的去掉分组的key的元素
        System.out.println("去重后的去掉分组的key的元素");
        for (int i = playerListDiff.size() - 1; i >= 0; i--) {
            Player player = playerListDiff.get(i);
            for (String s : set) {
                if ((player.getName() + "#" + player.getSex()).equals(s)) {
                    playerListDiff.remove(player);
                }
            }
        }
        new ArrayList<>(playerListDiff).forEach(System.out::println);
        System.out.println("----------------");
        // playerListDiff把这个作为最终的初始的状态
        // 这个最终的和原来的差集
        System.out.println("这个最终的和原来的差集");
        List<Player> players1 = subList1(playerList, playerListDiff);
        new ArrayList<>(players1).forEach(System.out::println);
        System.out.println("----------------");
        //分组
        System.out.println("这个最终的和原来的差集 根据name和sex 分组");
        Map<String, List<Player>> collect1 = players1.stream().collect(
                Collectors.groupingBy(a -> format("{0}#{1}", a.getName(), a.getSex())));
        Set<String> set1 = collect1.keySet();
        for (String o : set1) {
            System.out.println(o + "_" + collect1.get(o));
        }
        System.out.println("----------------");
        // 每组中取出年龄最小的
        System.out.println("每组中取出年龄最小的");
        Map<String,Player> map =new HashMap<>();
        for (String o : set1) {
            List<Player> players2 = collect1.get(o);
            Player player = players2.stream().min(Comparator.comparing(Player::getAge)).get();
            map.put(o,player);
        }
        Set<String> set2 = map.keySet();
        for (String o : set2) {
            System.out.println(o + "_" + map.get(o));
        }
        System.out.println("----------------");
        // 最终插入到playerListDiff
        System.out.println("最终的结果:找到了原始的中,相同名字相同性别的,并年龄最小的list");
        for (String o : set2) {
            playerListDiff.add(map.get(o));
        }
        new ArrayList<>(playerListDiff).forEach(System.out::println);
        System.out.println("----------------");

    }

    public static String format(String value, Object... paras) {
        return MessageFormat.format(value, paras);
    }

    /**
     * 差集(基于常规解法）优化解法1 适用于中等数据量
     * 求List1中有的但是List2中没有的元素
     * 空间换时间降低时间复杂度
     * 时间复杂度O(Max(list1.size(),list2.size()))
     */
    public static List<Player> subList1(List<Player> list1, List<Player> list2) {
        //空间换时间 降低时间复杂度
        Map<Player, Player> tempMap = new HashMap<>();
        for (Player item : list2) {
            tempMap.put(item, item);
        }
        //LinkedList 频繁添加删除 也可以ArrayList容量初始化为List1.size(),防止数据量过大时频繁扩容以及数组复制
        List<Player> resList = new LinkedList<>();
        for (Player item : list1) {
            if (!tempMap.containsKey(item)) {
                resList.add(item);
            }
        }
        return resList;
    }

    private static List<Player> getPlayers(List<String> list) {
        List<Player> playerList;
        list.add("kobe");
        list.add("james");
        list.add("curry");
        list.add("zimug");
        list.add("zimug");

        playerList = new ArrayList<>();
        playerList.add(new Player("kobe", "男", 10000));
        playerList.add(new Player("james", "男", 32));
        playerList.add(new Player("curry", "男", 30));
        playerList.add(new Player("zimug", "男", 1));
        playerList.add(new Player("zimug", "女", 18));
        playerList.add(new Player("zimug", "女", 16));
        playerList.add(new Player("zimug", "男", 18));
        playerList.add(new Player("zimug", "男", 18));
        return playerList;
    }

    private static class Player {
        private String name;
        private String sex;
        private Integer age;

        public Player() {
        }

        public Player(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public Player(String name, String sex, Integer age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
