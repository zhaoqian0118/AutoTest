package Schedule.Json.Object;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class ScheduleSaveTrainJson {
    private boolean ok;
    private String data;
    private int errorCode;
    private String traininglog;

    public boolean getOK(){
        return ok;
    }

    public String getData(){return data;}

    public int getErrorCode(){
        return errorCode;
    }

    public String getTraininglog(){return traininglog;}
}
