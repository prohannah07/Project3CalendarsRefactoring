import java.util.*;

public class GregorianCalendar extends Calendar {

    private Calendar calendar;

    GregorianCalendar(Calendar calendar, User owner){
        this(owner);
        this.calendar = calendar;
        this.transfer();
    }

    GregorianCalendar(User owner){
        super(owner);
    }



    public void transfer(){
        this.setEvents(calendar.getEvents());
        this.setPrivateStatus(calendar.getPrivateStatus());
        this.setVisualization(calendar.getVisualization());
        this.setTimerScreen(calendar.getTimerScreen());
        this.setLanguage(calendar.getLanguage());
    }
}
