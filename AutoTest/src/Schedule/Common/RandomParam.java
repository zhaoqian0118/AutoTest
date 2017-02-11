package Schedule.Common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class RandomParam {
    //Train_Calorie
    public static double getTrainCalorie(){
        Random rn=new Random();
        DecimalFormat dcmFmt=new DecimalFormat("0.0000000");
        double calorie=rn.nextDouble()*10;
        if(calorie<1){
            calorie+=1;
        }
        return Double.parseDouble(dcmFmt.format(calorie));
    }

    //Run&&Cycling_Calorie
    public static int getCalorie(){
        Random rn=new Random();
        int calorie=Integer.parseInt(String.valueOf(rn.nextInt(10))+String.valueOf(rn.nextInt(10))+String.valueOf(rn.nextInt(10)));
        if(calorie<100){
            calorie+=100;
        }
        return calorie;
    }

    //Run&&Cycling_Distance
    public static double getDistance(){
        Random rn=new Random();
        DecimalFormat dcmFmt=new DecimalFormat("0.00");
        double distance=rn.nextDouble()*10000;
        if(distance<10000){
            distance+=10000;
        }
        return Double.parseDouble(dcmFmt.format(distance));
    }

    //Train_Duration
    public static int getTrainDuration(){
        Random rn=new Random();
        int duration=rn.nextInt(10);
        return duration;
    }

    //Run&&Cycling_Duration
    public static int getDuration(){
        Random rn=new Random();
        int duration=Integer.parseInt(String.valueOf(rn.nextInt(10))+String.valueOf(rn.nextInt(10))+String.valueOf(rn.nextInt(10))+String.valueOf(rn.nextInt(10)));
        if(duration<1000){
            duration+=1000;
        }
        return duration;
    }

    //Run&&Cycling_AveragePace
    public static int getAveragePace(){
        Random rn=new Random();
        int averagePace=Integer.parseInt(String.valueOf(rn.nextInt(10))+String.valueOf(rn.nextInt(10))+String.valueOf(rn.nextInt(10)));
        if(averagePace<100){
            averagePace+=100;
        }
        return averagePace;
    }

    //Run&&Cycling_AverageSpeed
    public static double getAverageSpeed(){
        Random rn=new Random();
        DecimalFormat dcmFmt=new DecimalFormat("0.000000");
        double distance=rn.nextDouble()*10;
        if(distance<1){
            distance+=1;
        }
        return Double.parseDouble(dcmFmt.format(distance));
    }

    //Feel
    public static int getFeel(){
        Random rn=new Random();
        int feel=rn.nextInt(4)+1;
        return feel;
    }

    //int四舍五入
    public static int getRound(double round){
        BigDecimal b=new BigDecimal(round);
        int i=Integer.parseInt(String.valueOf(b.setScale(0, BigDecimal.ROUND_HALF_UP)));
        return i;
    }

    //double四舍五入
    public static double getRoundDouble(double round){
        BigDecimal b=new BigDecimal(round);
        DecimalFormat dcmFmt=new DecimalFormat("0.0000");
        double i=Double.parseDouble(String.valueOf(dcmFmt.format(b.setScale(5, BigDecimal.ROUND_HALF_UP))));
        return i;
    }
}
