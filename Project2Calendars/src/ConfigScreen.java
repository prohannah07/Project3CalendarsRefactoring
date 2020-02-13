import java.time.ZoneId;
import java.util.*;

public class ConfigScreen {

    private String timeZone;
    private Boolean darkMode;

    ConfigScreen(){
        this.timeZone = "PST";
        this.darkMode = false;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Boolean getDarkMode() {
        return darkMode;
    }

    public void setDarkMode(Boolean darkMode) {
        this.darkMode = darkMode;
    }


    public void show(){
        System.out.println("***************************");
        System.out.println("Current Settings");
        System.out.println();
        System.out.println("TIMEZONE: " + this.timeZone + " - " + ZoneId.SHORT_IDS.get(this.timeZone));
        if(darkMode){
            System.out.println("THEME: Dark");
        }else{
            System.out.println("THEME: Light");
        }
        System.out.println("***************************");
    }

}
