package Schedule.Common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class ScheduleDateFormat {
    //当前时间（北京时间）
    public static String DateNow(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        String sr1=date.format(now);
        String sr2=time.format(new Date(now.getTime()-8*60*60*1000));
        return sr1+"T"+sr2+".000Z";
    }

    //输出xls以当前时间命名
    public static String XlsDateNow(){
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        String sr1=date.format(now);
        return sr1;
    }

    //排行榜日期
    public static String RankDate(){
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String sr1=date.format(now);
        return sr1;
    }

    //每日训练日期
    public static String DateDailyNow(){
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
        Date now = new Date();
        String sr1=date.format(now);
        return sr1;
    }

    private static String DateForTime(Date zone){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String sr1=date.format(zone);
        String sr2=time.format(zone);
        return sr1+"T"+sr2+".000Z";
    }

    //时间戳
    public static String DateTimstamp(){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String s=simpleDateFormat.format(now);
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    //时区截止时间
    public static String DateZoneEnd(){
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        TimeZone timeBJ = TimeZone.getTimeZone("PRC");//得到北京时间的时区
        c.setTimeInMillis(date.getTime()
                - timeBJ.getOffset(date.getTime()));
        String sr1=DateForTime(c.getTime());
        return sr1;
    }

    //时区开始时间
    public static String DateZoneStart(){
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        TimeZone timeBJ = TimeZone.getTimeZone("PRC");//得到北京时间的时区
        c.setTimeInMillis((date.getTime()
                - timeBJ.getOffset(date.getTime()))-300000);
        String sr1=DateForTime(c.getTime());
        return sr1;
    }
}
