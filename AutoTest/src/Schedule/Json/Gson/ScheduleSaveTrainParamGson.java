package Schedule.Json.Gson;

import Schedule.Json.Object.ScheduleSaveTrainParamJson;
import com.google.gson.Gson;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class ScheduleSaveTrainParamGson {
    public static double calorie;
    public static String doneDate;
    public static int duration;
    public static int exerciseCount;
    public static int feel;
    public static int groupsSize;
    public static int[] actualRep;
    public static int[] actualSec;
    public static String[] exercise;
    public static String[] name;
    public static int[] totalRep;
    public static int[] totalSec;
    public static String[] type;
    public static String inSchedule;
    public static int scheduleDay;
    public static int secondDuration;
    public static String serverEndTime;
    public static String workoutId;

    public static void ScheduleSaveTrainParamGson(String sr){
        Gson gson = new Gson();
        ScheduleSaveTrainParamJson d1= gson.fromJson(sr, ScheduleSaveTrainParamJson.class);
        calorie=d1.getCalorie();
        doneDate=d1.getDoneDate();
        duration=d1.getDuration();
        exerciseCount=d1.getExerciseCount();
        feel=d1.getFeel();
        groupsSize=d1.getGroups().size();
        actualRep=new int[groupsSize];
        actualSec=new int[groupsSize];
        exercise=new String[groupsSize];
        name=new String[groupsSize];
        totalRep=new int[groupsSize];
        totalSec=new int[groupsSize];
        type=new String[groupsSize];
        for(int i=0;i<groupsSize;i++){
            actualRep[i]=d1.getGroups().get(i).getActualRep();
            actualSec[i]=d1.getGroups().get(i).getActualSec();
            exercise[i]=d1.getGroups().get(i).getExercise();
            name[i]=d1.getGroups().get(i).getName();
            totalRep[i]=d1.getGroups().get(i).getTotalRep();
            totalSec[i]=d1.getGroups().get(i).getTotalSec();
            type[i]=d1.getGroups().get(i).getType();
        }
        inSchedule=d1.getInSchedule();
        scheduleDay=d1.getScheduleDay();
        secondDuration=d1.getSecondDuration();
        serverEndTime=d1.getServerEndTime();
        workoutId=d1.getWorkoutId();
    }
}
