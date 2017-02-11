package Schedule.Json.Gson;

import Schedule.Json.Object.ScheduleSaveTrainJson;
import com.google.gson.Gson;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class ScheduleSaveTrainGson {
    public static boolean ok;
    public static String data;
    public static int errorCode;
    public static String traininglog;

    public static void ScheduleSaveTrainGson(String sr){
        Gson gson = new Gson();
        ScheduleSaveTrainJson d1= gson.fromJson(sr, ScheduleSaveTrainJson.class);
        ok=d1.getOK();
        data=d1.getData();
        errorCode=d1.getErrorCode();
        if(ok){
            traininglog=d1.getTraininglog();
        }
    }
}
