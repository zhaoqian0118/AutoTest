package Schedule.Json.Gson;

import Schedule.Json.Object.ScheduleTrainingLogJson;
import com.google.gson.Gson;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class ScheduleTrainingLogGson {
    public static boolean ok;
    public static int errorCode;
    public static String subclassType;
    public static String _id;
    public static String id;
    public static int count;
    public static int groupSize;
    public static String[] gexercise;
    public static String[] gname;
    public static String[] gtype;
    public static int[] gactualRep;
    public static int[] gactualSec;
    public static int[] gtotalRep;
    public static int[] gtotalSec;
    public static String name;
    public static String workoutName;
    public static String workout;
    public static String plan;
    public static int feel;
    public static int calorie;
    public static int duration;
    public static int secondDuration;
    public static String timezone;
    public static String serverEndTime;
    public static String type;
    public static String userAgent;
    public static String device;
    public static String clientVersion;
    public static String os;
    public static int completed;
    public static int exerciseCount;
    public static boolean official;
    public static String user;
    public static String doneDate;
    public static boolean isOfficial;
    public static boolean inSchedule;
    public static int order;

    public static void ScheduleTrainingLogGson(String sr) {
        Gson gson = new Gson();
        ScheduleTrainingLogJson d1=gson.fromJson(sr, ScheduleTrainingLogJson.class);
        ok=d1.getOK();
        errorCode=d1.getErrorCode();
        if(ok){
            subclassType=d1.getData().getSubclassType();
            _id=d1.getData().get_id();
            id=d1.getData().getId();
            count=d1.getData().getCount();
            name=d1.getData().getName();
            workoutName=d1.getData().getWorkoutName();
            workout=d1.getData().getWorkout();
            plan=d1.getData().getPlan();
            feel=d1.getData().getFeel();
            calorie=d1.getData().getCalorie();
            duration=d1.getData().getDuration();
            secondDuration=d1.getData().getSecondDuration();
            timezone=d1.getData().getTimezone();
            serverEndTime=d1.getData().getServerEndTime();
            type=d1.getData().getType();
            userAgent=d1.getData().getUserAgent();
            device=d1.getData().getDevice();
            clientVersion=d1.getData().getClientVersion();
            os=d1.getData().getOs();
            completed=d1.getData().getCompleted();
            exerciseCount=d1.getData().getExerciseCount();
            official=d1.getData().getOfficial();
            user=d1.getData().getUser();
            doneDate=d1.getData().getDoneDate();
            isOfficial=d1.getData().getIsOfficial();
            inSchedule=d1.getData().getInSchedule();
            order=d1.getData().getOrder();
            groupSize=d1.getData().getGroups().size();
            gexercise=new String[groupSize];
            gname=new String[groupSize];
            gtype=new String[groupSize];
            gactualRep=new int[groupSize];
            gactualSec=new int[groupSize];
            gtotalRep=new int[groupSize];
            gtotalSec=new int[groupSize];
            for(int i=0;i<groupSize;i++){
                gexercise[i]=d1.getData().getGroups().get(i).getExercise();
                gname[i]=d1.getData().getGroups().get(i).getName();
                gtype[i]=d1.getData().getGroups().get(i).getType();
                gactualRep[i]=d1.getData().getGroups().get(i).getActualRep();
                gactualSec[i]=d1.getData().getGroups().get(i).getActualSec();
                gtotalRep[i]=d1.getData().getGroups().get(i).getTotalRep();
                gtotalSec[i]=d1.getData().getGroups().get(i).getTotalSec();
            }
        }
    }
}
