package Schedule.Interface.TCStage;

import Schedule.Base.ScheduleAPIConfig;
import Schedule.Interface.Train.ScheduleSaveTrain;
import Schedule.Interface.Train.ScheduleTrainingLog;
import Schedule.Json.Gson.ScheduleSaveTrainGson;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class TCInterface {
    //errorcontent
    public static String errorcontent="";
    //插入行数
    private static int insert_row=1;
    //执行case
    public static void Execute(){
        //普通训练上传数据-TrainingLog内容校验
        ScheduleSaveTrainToLog();
    }

    //普通训练上传数据-TrainingLog内容校验
    private static String ScheduleSaveTrainToLog(){
        CreateXls.rowbefore(insert_row,"普通训练上传数据","普通训练上传数据","TrainingLog内容校验", ScheduleAPIConfig.savetrain01,"POST");
        String sr=null;
        String re=null;
        String result=null;
        String json=null;
        int i=0;
        insert_row+=1;
        try{
            re= ScheduleSaveTrain.ScheduleSaveTrain();
            json= ScheduleSaveTrain.json;
            sr= ScheduleTrainingLog.ScheduleSaveTrainToLog();
            result=ScheduleTrainingLog.ScheduleSaveTrainLogResult+ ScheduleTrainingLog.ScheduleSaveTrainLogErrorResult;
            if(result.equals("")){
                errorcontent+="普通训练上传数据-TrainingLog内容校验 该接口请求失败"+"<br>";
            }
            if(ScheduleTrainingLog.ScheduleSaveTrainLogErrorResult.equals("")){
                i=1;
            }else{
                i=0;
                errorcontent+=ScheduleTrainingLog.ScheduleSaveTrainLogErrorResult+"<br>";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        CreateXls.rowafter(json, re, result, ScheduleAPIConfig.traininglog01 + ScheduleSaveTrainGson.traininglog, sr, i);
        return sr;
    }
}
