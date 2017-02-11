package Schedule.Json.Object;

import java.util.List;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class ScheduleSaveTrainParamJson {
    private double calorie;
    private String doneDate;
    private int duration;
    private int exerciseCount;
    private int feel;
    private String inSchedule;
    private int scheduleDay;
    private int secondDuration;
    private String serverEndTime;
    private String workoutId;
    private List<GetGroupsList> groups;

    public double getCalorie(){return calorie;}
    public String getDoneDate(){return doneDate;}
    public int getDuration(){return duration;}
    public int getExerciseCount(){return exerciseCount;}
    public int getFeel(){return feel;}
    public String getInSchedule(){return inSchedule;}
    public int getScheduleDay(){return scheduleDay;}
    public int getSecondDuration(){return secondDuration;}
    public String getServerEndTime(){return serverEndTime;}
    public String getWorkoutId(){return workoutId;}

    public class GetGroupsList{
        private int actualRep;
        private int actualSec;
        private String exercise;
        private String name;
        private int totalRep;
        private int totalSec;
        private String type;

        public int getActualRep(){return actualRep;}
        public int getActualSec(){return actualSec;}
        public String getExercise(){return exercise;}
        public String getName(){return name;}
        public int getTotalRep(){return totalRep;}
        public int getTotalSec(){return totalSec;}
        public String getType(){return type;}
    }

    public List<GetGroupsList> getGroups(){return groups;}
}
