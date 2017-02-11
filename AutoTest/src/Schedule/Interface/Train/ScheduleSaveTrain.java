package Schedule.Interface.Train;

import Schedule.Base.ScheduleAPIConfig;
import Schedule.Base.ScheduleConfig;
import Schedule.Common.ScheduleClient;
import Schedule.Json.Gson.ScheduleSaveTrainGson;
import Schedule.Json.Gson.ScheduleSaveTrainParamGson;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class ScheduleSaveTrain {
    //json
    public static String json;

    public static String ScheduleSaveTrain(){
        String sr1=null;
        try{
            Thread.sleep(2000);
            json= ScheduleAPIConfig.savetrainjson01();
            System.out.println(json);
            ScheduleSaveTrainParamGson.ScheduleSaveTrainParamGson(json);
            System.out.println(ScheduleAPIConfig.savetrain01);
            sr1= ScheduleClient.doPostJson(ScheduleAPIConfig.savetrain01, json, ScheduleConfig.token);
            System.out.println(sr1);
            ScheduleSaveTrainGson.ScheduleSaveTrainGson(sr1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sr1;
    }
}
