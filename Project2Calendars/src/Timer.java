import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Timer {

    private ZonedDateTime endTime;
    private String title;

    //Constructor
    public Timer(ZonedDateTime time, String title){
        this.endTime = time;
        this.title = title;
    }

    //Getters and Setters

    public ZonedDateTime getTime() {
        return endTime;
    }

    public void setTime(ZonedDateTime time) {
        this.endTime = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //Custom Functions

    public boolean countDownFinished(){
        long diffInMinute = ChronoUnit.MINUTES.between(ZonedDateTime.now(), endTime);
        long diffInSeconds = ChronoUnit.SECONDS.between(ZonedDateTime.now(), endTime);

        if ((int)diffInSeconds <= 0){
            return true;
        }
        System.out.println("Left: " + (int)diffInMinute + "min ");
        return false;

    }


}
