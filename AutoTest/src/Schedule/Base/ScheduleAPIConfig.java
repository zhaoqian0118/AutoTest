package Schedule.Base;

import Schedule.Common.RandomParam;
import Schedule.Common.ScheduleDateFormat;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class ScheduleAPIConfig {
    //上传训练数据
    public static final String savetrain01=ScheduleConfig.pre_domain+ScheduleConfig.version11+"";
    public static String savetrainjson01(){
        int duration= RandomParam.getTrainDuration();
        String end= ScheduleDateFormat.DateZoneEnd();
        String json01="";
        return json01;
    }
    //查询训练记录
    public static final String traininglog01=ScheduleConfig.pre_domain+ScheduleConfig.version11+"";
}
