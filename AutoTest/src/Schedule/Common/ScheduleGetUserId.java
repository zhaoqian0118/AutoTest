package Schedule.Common;

import Schedule.Base.ScheduleConfig;
import Schedule.Json.Object.ScheduleGetUserIdJson;
import com.google.gson.Gson;
import org.owasp.esapi.codecs.Base64;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class ScheduleGetUserId {
    private static String ScheduleGetUserIdObject(String sr){
        Gson gson = new Gson();
        ScheduleGetUserIdJson d1= gson.fromJson(sr, ScheduleGetUserIdJson.class);
        String sr1=d1.get_id();
        return sr1;
    }

    //从token获取uid
    public static String Uid(){
        String sr1;
        String sr2=null;
        byte[] uidbyte= Base64.decode(ScheduleConfig.token.split("\\.")[1]);
        try{
            sr1=new String(uidbyte,"UTF-8");
            sr2=ScheduleGetUserIdObject(sr1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sr2;
    }
}
