package Schedule.Base;

import Schedule.Common.ScheduleDateFormat;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class ScheduleConfig {
    //domain
    public static final String pre_domain="";
    public static final String social="";
    public static final String pd="";
    //token
    private static final String pre_token="";
    private static final String test_token="";
    public static final String token=pre_token;
    //version
    public static final String version11="";
    public static final String version2="";
    //xls
    private static String local="/Users/zhaoqian/Desktop/test/TCXls/"+"TCStageXls"+ ScheduleDateFormat.XlsDateNow()+".xls";
    private static String jenkins="/data/jenkins/workspace/SCKeepAutoTest/Xls/"+"TCStageXls"+ ScheduleDateFormat.XlsDateNow()+".xls";
    public static String outputFile=local;
    //Email-title
    public static final String title = "TC接口测试结果";
    //Email-content
    public static final String content = "接口已验证，详见附件，请您查收，谢谢。" + "<br>";
    //Receiver
    private static final String zhaoqian="";
    public static final String[] receiver={zhaoqian};

}
