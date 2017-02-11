package Schedule.Common;

import Schedule.Base.ScheduleConfig;
import Schedule.Interface.TCStage.CreateXls;
import Schedule.Interface.TCStage.TCInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class SendEmail {
    //邮件标题
    private static final String subject = ScheduleConfig.title;
    //收件人
    private static final String[] receiver=ScheduleConfig.receiver;

    public static void main(String[] args){
        boolean sendfail=false;
        int num=0;
        try {
            CreateXls.CreateXls();
            //邮件附件
            List<File> outattachement = new ArrayList<File>();
            File attachement = new File(ScheduleConfig.outputFile);
            outattachement.add(attachement);
            //邮件正文
            String totalcontentpass = ScheduleConfig.content + "<br>" + TCInterface.errorcontent + "<br>";
            if(TCInterface.errorcontent.equals("")){
                totalcontentpass= totalcontentpass+ "接口已验证成功" + "<br>";
            }
            //发送邮件
            sendfail=SendEmailCompany.send_email(receiver, subject, totalcontentpass, outattachement);
            //由于发送邮件超时，重试10次
            while (sendfail&&num<=10){
                Thread.sleep(2000);
                sendfail=SendEmailCompany.send_email(receiver, subject, totalcontentpass, outattachement);
                num+=1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
