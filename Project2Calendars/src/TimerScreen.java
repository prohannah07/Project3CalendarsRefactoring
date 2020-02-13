import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimerScreen {

    private ArrayList<Timer> timers;

    TimerScreen(){
        timers = new ArrayList<Timer>();
    }

    public ArrayList<Timer> getTimers() {
        return timers;
    }

    public void setTimers(ArrayList<Timer> timers) {
        this.timers = timers;
    }


    public void show(int calNum){
        System.out.println("*********************************************************");
        System.out.println("TIMERS FOR CALENDAR #"+calNum);
        System.out.println("*********************************************************");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, YYYY h:m a z");
        int len = this.timers.size();
        for (int i = 0; i<len; i++){
            System.out.println("TITLE: " + this.timers.get(i).getTitle());
            System.out.println("END Time: " + dtf.format(this.timers.get(i).getTime()));
            if (this.timers.get(i).countDownFinished()){
                System.out.println("STATUS: DONE!");
            }else {

                System.out.println("STATUS: Currently in Progress!");
            }
            System.out.println("*********************************************************");

        }

    }


}
