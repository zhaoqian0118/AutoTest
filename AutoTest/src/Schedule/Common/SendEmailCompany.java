package Schedule.Common;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class SendEmailCompany {
    //Gmail发送邮件
    public static boolean send_email(String[] receiver,String subject,String content,List<File> outattachement) throws IOException, AddressException, MessagingException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        boolean hastimeout=false;
        try {
            String SSL_FACTORY="javax.net.ssl.SSLSocketFactory";
            String username = "";
            String password = "";
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host","smtp.gmail.com") ;
            properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            properties.setProperty("mail.smtp.socketFactory.fallback", "false") ;
            properties.setProperty("mail.smtp.port", "") ;
            properties.setProperty("mail.smtp.socketFactory.port", "") ;
            properties.setProperty("mail.smtp.auth", "true") ;
            properties.setProperty("mail.smtp.connectiontimeout", "300000");
            properties.setProperty("mail.smtp.timeout", "300000");
            properties.put("mail.smtp.starttls.enable","true");
            Authenticator authenticator = new Schedule.Common.Email_Authenticator(username, password);
            Session sendMailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
            MimeMessage mailMessage = new MimeMessage(sendMailSession);
            mailMessage.setFrom(new InternetAddress(username));
            // Message.RecipientType.TO属性表示接收者的类型为TO
            List list=new ArrayList();
            for(int i=0;i<receiver.length;i++){
                list.add(new InternetAddress(receiver[i]));
            }
            InternetAddress[] address =(InternetAddress[])list.toArray(new InternetAddress[list.size()]);
            mailMessage.setRecipients(Message.RecipientType.TO, address);
            mailMessage.setSubject(subject, "UTF-8");
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            html.setContent(content.trim(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 添加附件的内容
            for(int i=0;i<outattachement.size();i++) {
                if (outattachement.get(i) != null) {
                    BodyPart attachmentBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(outattachement.get(i));
                    attachmentBodyPart.setDataHandler(new DataHandler(source));
                    //MimeUtility.encodeWord可以避免文件名乱码
                    attachmentBodyPart.setFileName(MimeUtility.encodeWord(outattachement.get(i).getName()));
                    mainPart.addBodyPart(attachmentBodyPart);
                }
            }
            mailMessage.setContent(mainPart);
            Transport.send(mailMessage);
        }catch(Exception e){
            hastimeout=true;
            e.printStackTrace();
        }finally {
            System.out.println("The Task is :" + sdf.format(new Date()));
            System.out.println("发送邮件...");
        }
        return hastimeout;
    }
}
