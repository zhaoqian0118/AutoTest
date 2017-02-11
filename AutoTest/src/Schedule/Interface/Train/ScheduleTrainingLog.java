package Schedule.Interface.Train;

import Schedule.Base.ScheduleAPIConfig;
import Schedule.Base.ScheduleConfig;
import Schedule.Common.ScheduleClient;
import Schedule.Common.ScheduleGetUserId;
import Schedule.Json.Gson.ScheduleSaveTrainGson;
import Schedule.Json.Gson.ScheduleSaveTrainParamGson;
import Schedule.Json.Gson.ScheduleTrainingLogGson;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class ScheduleTrainingLog {
    //Result
    public static String ScheduleSaveTrainLogResult="";
    public static String ScheduleSaveTrainLogErrorResult="";

    //普通训练上传数据-TrainingLog内容校验
    public static String ScheduleSaveTrainToLog(){
        String sr1=null;
        try{
            Thread.sleep(2000);
            sr1=ScheduleTrainingLog();
            ScheduleSaveTrainCheck();
        }catch (Exception e){
            e.printStackTrace();
        }
        return sr1;
    }

    //所有训练历史－比对traininglog
    public static String ScheduleTrainingLog(String traininglogId){
        String sr1=null;
        try{
            System.out.println(ScheduleAPIConfig.traininglog01+ traininglogId);
            sr1= ScheduleClient.doGet(ScheduleAPIConfig.traininglog01 + traininglogId, ScheduleConfig.token);
            ScheduleTrainingLogGson.ScheduleTrainingLogGson(sr1);
            System.out.println(sr1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sr1;
    }

    private static String ScheduleTrainingLog(){
        String sr1=null;
        try{
            System.out.println(ScheduleAPIConfig.traininglog01+ ScheduleSaveTrainGson.traininglog);
            sr1= ScheduleClient.doGet(ScheduleAPIConfig.traininglog01+ ScheduleSaveTrainGson.traininglog, ScheduleConfig.token);
            ScheduleTrainingLogGson.ScheduleTrainingLogGson(sr1);
            System.out.println(sr1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sr1;
    }

    //完成训练校验
    private static boolean ScheduleSaveTrainCheck(){
        boolean judge=false;
        int groupjudgeSize=0;
        String group_con="";
        if(ScheduleSaveTrainGson.traininglog.equals(ScheduleTrainingLogGson._id)&&
                ScheduleGetUserId.Uid().equals(ScheduleTrainingLogGson.user)&&
                ScheduleSaveTrainParamGson.workoutId.equals(ScheduleTrainingLogGson.id)&&
                ScheduleSaveTrainParamGson.workoutId.equals(ScheduleTrainingLogGson.workout)&&
                ScheduleSaveTrainParamGson.workoutId.equals(ScheduleTrainingLogGson.plan)&&
                "plan".equals(ScheduleTrainingLogGson.type)&&
                "Android".equals(ScheduleTrainingLogGson.os)&&
                1==ScheduleTrainingLogGson.order&&false==ScheduleTrainingLogGson.official&&
                false==ScheduleTrainingLogGson.isOfficial&&false==ScheduleTrainingLogGson.inSchedule){
            for(int i=0;i<ScheduleSaveTrainParamGson.groupsSize;i++){
                String[] group_content=new String[ScheduleSaveTrainParamGson.groupsSize];
                if(ScheduleSaveTrainParamGson.actualRep[i]==ScheduleTrainingLogGson.gactualRep[i]&&
                        ScheduleSaveTrainParamGson.actualSec[i]==ScheduleTrainingLogGson.gactualSec[i]&&
                        ScheduleSaveTrainParamGson.exercise[i].equals(ScheduleTrainingLogGson.gexercise[i])&&
                        ScheduleSaveTrainParamGson.name[i].equals(ScheduleTrainingLogGson.gname[i])&&
                        ScheduleSaveTrainParamGson.totalRep[i]==ScheduleTrainingLogGson.gtotalRep[i]&&
                        ScheduleSaveTrainParamGson.totalSec[i]==ScheduleTrainingLogGson.gtotalSec[i]&&
                        ScheduleSaveTrainParamGson.type[i].equals(ScheduleTrainingLogGson.gtype[i])){
                    group_content[i]="actualRep["+i+"]:"+ScheduleSaveTrainParamGson.actualRep[i]+"="+ScheduleTrainingLogGson.gactualRep[i]+"\n"+
                            "actualSec["+i+"]:"+ScheduleSaveTrainParamGson.actualSec[i]+"="+ScheduleTrainingLogGson.gactualSec[i]+"\n"+
                            "exercise["+i+"]:"+ScheduleSaveTrainParamGson.exercise[i]+"="+ScheduleTrainingLogGson.gexercise[i]+"\n"+
                            "name["+i+"]:"+ScheduleSaveTrainParamGson.name[i]+"="+ScheduleTrainingLogGson.gname[i]+"\n"+
                            "totalRep["+i+"]:"+ScheduleSaveTrainParamGson.totalRep[i]+"="+ScheduleTrainingLogGson.gtotalRep[i]+"\n"+
                            "totalSec["+i+"]:"+ScheduleSaveTrainParamGson.totalSec[i]+"="+ScheduleTrainingLogGson.gtotalSec[i]+"\n"+
                            "type["+i+"]:"+ScheduleSaveTrainParamGson.type[i]+"="+ScheduleTrainingLogGson.gtype[i]+"\n";
                    group_con+=group_content[i];
                    groupjudgeSize+=1;
                }else{
                    String resulterror="普通训练上传数据-group["+i+"]信息校验失败"+"\n";
                    String resulterrorinfo="actualRep["+i+"]:"+ScheduleSaveTrainParamGson.actualRep[i]+"="+ScheduleTrainingLogGson.gactualRep[i]+"\n"+
                            "actualSec["+i+"]:"+ScheduleSaveTrainParamGson.actualSec[i]+"="+ScheduleTrainingLogGson.gactualSec[i]+"\n"+
                            "exercise["+i+"]:"+ScheduleSaveTrainParamGson.exercise[i]+"="+ScheduleTrainingLogGson.gexercise[i]+"\n"+
                            "name["+i+"]:"+ScheduleSaveTrainParamGson.name[i]+"="+ScheduleTrainingLogGson.gname[i]+"\n"+
                            "totalRep["+i+"]:"+ScheduleSaveTrainParamGson.totalRep[i]+"="+ScheduleTrainingLogGson.gtotalRep[i]+"\n"+
                            "totalSec["+i+"]:"+ScheduleSaveTrainParamGson.totalSec[i]+"="+ScheduleTrainingLogGson.gtotalSec[i]+"\n"+
                            "type["+i+"]:"+ScheduleSaveTrainParamGson.type[i]+"="+ScheduleTrainingLogGson.gtype[i]+"\n";
                    ScheduleSaveTrainLogErrorResult=resulterror+resulterrorinfo;
                    System.out.println(ScheduleSaveTrainLogErrorResult);
                    break;
                }
            }
            if(groupjudgeSize==ScheduleSaveTrainParamGson.groupsSize){
                if(ScheduleSaveTrainParamGson.feel==ScheduleTrainingLogGson.feel&&
                        (int)ScheduleSaveTrainParamGson.calorie==ScheduleTrainingLogGson.calorie&&
                        ScheduleSaveTrainParamGson.duration==ScheduleTrainingLogGson.duration&&
                        ScheduleSaveTrainParamGson.secondDuration==ScheduleTrainingLogGson.secondDuration&&
                        ScheduleSaveTrainParamGson.exerciseCount==ScheduleTrainingLogGson.exerciseCount&&
                        ScheduleSaveTrainParamGson.doneDate.equals(ScheduleTrainingLogGson.doneDate)&&
                        ScheduleSaveTrainParamGson.serverEndTime.equals(ScheduleTrainingLogGson.serverEndTime)){
                    judge=true;
                    String result="普通训练上传数据-TrainingLog内容校验成功"+"\n";
                    ScheduleSaveTrainLogResult="_id:"+ScheduleSaveTrainGson.traininglog+"="+ScheduleTrainingLogGson._id+"//traininglogId"+"\n"+
                            "id:"+ScheduleSaveTrainParamGson.workoutId+"="+ScheduleTrainingLogGson.id+"//workoutId"+"\n"+
                            "user:"+ScheduleGetUserId.Uid()+"="+ScheduleTrainingLogGson.user+"//userId"+"\n"+
                            "plan:"+ScheduleSaveTrainParamGson.workoutId+"="+ScheduleTrainingLogGson.plan+"//planId"+"\n"+
                            "workout:"+ScheduleSaveTrainParamGson.workoutId+"="+ScheduleTrainingLogGson.workout+"//workoutId"+"\n"+
                            "type:"+"plan"+"="+ScheduleTrainingLogGson.type+"\n"+
                            "os:"+"Android"+"="+ScheduleTrainingLogGson.os+"\n"+
                            "official:"+"false"+"="+ScheduleTrainingLogGson.official+"\n"+
                            "isOfficial:"+"false"+"="+ScheduleTrainingLogGson.isOfficial+"\n"+
                            "inSchedule:"+"false"+"="+ScheduleTrainingLogGson.inSchedule+"\n"+
                            "order:"+"1"+"="+ScheduleTrainingLogGson.order+"//"+"\n"+
                            group_con+
                            "feel:"+ScheduleSaveTrainParamGson.feel+"="+ScheduleTrainingLogGson.feel+"\n"+
                            "calorie:"+(int)ScheduleSaveTrainParamGson.calorie+"="+ScheduleTrainingLogGson.calorie+"\n"+
                            "duration:"+ScheduleSaveTrainParamGson.duration+"="+ScheduleTrainingLogGson.duration+"\n"+
                            "secondDuration:"+ScheduleSaveTrainParamGson.secondDuration+"="+ScheduleTrainingLogGson.secondDuration+"\n"+
                            "exerciseCount:"+ScheduleSaveTrainParamGson.exerciseCount+"="+ScheduleTrainingLogGson.exerciseCount+"\n"+
                            "doneDate:"+ScheduleSaveTrainParamGson.doneDate+"="+ScheduleTrainingLogGson.doneDate+"\n"+
                            "serverEndTime:"+ScheduleSaveTrainParamGson.serverEndTime+"="+ScheduleTrainingLogGson.serverEndTime+"\n";
                    ScheduleSaveTrainLogResult=result+ScheduleSaveTrainLogResult;
                    System.out.println(ScheduleSaveTrainLogResult);
                    ScheduleSaveTrainLogErrorResult="";
                }else{
                    String resulterror="普通训练上传数据-上传数据信息校验失败"+"\n";
                    String resulterrorinfo="feel:"+ScheduleSaveTrainParamGson.feel+"="+ScheduleTrainingLogGson.feel+"\n"+
                            "calorie:"+(int)ScheduleSaveTrainParamGson.calorie+"="+ScheduleTrainingLogGson.calorie+"\n"+
                            "duration:"+ScheduleSaveTrainParamGson.duration+"="+ScheduleTrainingLogGson.duration+"\n"+
                            "secondDuration:"+ScheduleSaveTrainParamGson.secondDuration+"="+ScheduleTrainingLogGson.secondDuration+"\n"+
                            "exerciseCount:"+ScheduleSaveTrainParamGson.exerciseCount+"="+ScheduleTrainingLogGson.exerciseCount+"\n"+
                            "doneDate:"+ScheduleSaveTrainParamGson.doneDate+"="+ScheduleTrainingLogGson.doneDate+"\n"+
                            "serverEndTime:"+ScheduleSaveTrainParamGson.serverEndTime+"="+ScheduleTrainingLogGson.serverEndTime+"\n";
                    ScheduleSaveTrainLogErrorResult=resulterror+resulterrorinfo;
                    System.out.println(ScheduleSaveTrainLogErrorResult);
                }
            }
        }else{
            String resulterror="普通训练上传数据-workout信息校验失败"+"\n";
            String resulterrorinfo="_id:"+ScheduleSaveTrainGson.traininglog+"="+ScheduleTrainingLogGson._id+"//traininglogId"+"\n"+
                    "id:"+ScheduleSaveTrainParamGson.workoutId+"="+ScheduleTrainingLogGson.id+"//workoutId"+"\n"+
                    "user:"+ScheduleGetUserId.Uid()+"="+ScheduleTrainingLogGson.user+"//userId"+"\n"+
                    "plan:"+ScheduleSaveTrainParamGson.workoutId+"="+ScheduleTrainingLogGson.plan+"//planId"+"\n"+
                    "workout:"+ScheduleSaveTrainParamGson.workoutId+"="+ScheduleTrainingLogGson.workout+"//workoutId"+"\n"+
                    "type:"+"plan"+"="+ScheduleTrainingLogGson.type+"\n"+
                    "os:"+"Android"+"="+ScheduleTrainingLogGson.os+"\n"+
                    "official:"+"false"+"="+ScheduleTrainingLogGson.official+"\n"+
                    "isOfficial:"+"false"+"="+ScheduleTrainingLogGson.isOfficial+"\n"+
                    "inSchedule:"+"false"+"="+ScheduleTrainingLogGson.inSchedule+"\n"+
                    "order:"+"1"+"="+ScheduleTrainingLogGson.order+"\n";
            ScheduleSaveTrainLogErrorResult=resulterror+resulterrorinfo;
            System.out.println(ScheduleSaveTrainLogErrorResult);
        }
        return judge;
    }
}
