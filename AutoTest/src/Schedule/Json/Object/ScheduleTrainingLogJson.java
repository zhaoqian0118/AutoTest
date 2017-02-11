package Schedule.Json.Object;

import java.util.List;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class ScheduleTrainingLogJson {
    private boolean ok;
    private int errorCode;
    private String text;
    private GetDataimpl data;

    public boolean getOK(){
        return ok;
    }

    public int getErrorCode(){
        return errorCode;
    }

    public String getText(){return text;}

    public GetDataimpl getData(){
        return data;
    }

    public class GetDataimpl {
        private String subclassType;
        private String _id;
        private String id;
        private int count;
        private List<GetGroupsList> groups;
        private String name;
        private String workoutName;
        private String workout;
        private String plan;
        private int feel;
        private int calorie;
        private int duration;
        private int secondDuration;
        private String timezone;
        private String serverEndTime;
        private String type;
        private String userAgent;
        private String device;
        private String clientVersion;
        private String os;
        private int completed;
        private int exerciseCount;
        private boolean official;
        private String user;
        private String doneDate;
        private boolean isOfficial;
        private boolean inSchedule;
        private int order;

        public String getSubclassType(){return subclassType;}
        public String get_id(){return _id;}
        public String getId(){return id;}
        public int getCount(){return count;}
        public String getName(){return name;}
        public String getWorkoutName(){return workoutName;}
        public String getWorkout(){return workout;}
        public String getPlan(){return plan;}
        public int getFeel(){return feel;}
        public int getCalorie(){return calorie;}
        public int getDuration(){return duration;}
        public int getSecondDuration(){return secondDuration;}
        public String getTimezone(){return timezone;}
        public String getServerEndTime(){return serverEndTime;}
        public String getType(){return type;}
        public String getUserAgent(){return userAgent;}
        public String getDevice(){return device;}
        public String getClientVersion(){return clientVersion;}
        public String getOs(){return os;}
        public int getCompleted(){return completed;}
        public int getExerciseCount(){return exerciseCount;}
        public boolean getOfficial(){return official;}
        public String getUser(){return user;}
        public String getDoneDate(){return doneDate;}
        public boolean getIsOfficial(){return isOfficial;}
        public boolean getInSchedule(){return inSchedule;}
        public int getOrder(){return order;}

        public class GetGroupsList{
            private String exercise;
            private String name;
            private String type;
            private int actualRep;
            private int actualSec;
            private int totalRep;
            private int totalSec;

            public String getExercise(){return exercise;}
            public String getName(){return name;}
            public String getType(){return type;}
            public int getActualRep(){return actualRep;}
            public int getActualSec(){return actualSec;}
            public int getTotalRep(){return totalRep;}
            public int getTotalSec(){return totalSec;}
        }

        public List<GetGroupsList> getGroups(){return groups;}
    }
}
