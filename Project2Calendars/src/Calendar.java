
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Calendar {

    //Attributes

    private ArrayList<Event> events;
    private Boolean privateStatus;
    private String visualization;
    private TimerScreen timerScreen;
    private String language;
    private User owner;


    //Constructor
    Calendar(User owner){
        this.events = new ArrayList<Event>();
        this.privateStatus = false;
        this.visualization = "yearly";
        timerScreen = new TimerScreen();
        language = "English-US";
        this.owner = owner;
    }

    //Getters and Setters

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }


    public Boolean getPrivateStatus() {
        return privateStatus;
    }

    public void setPrivateStatus(Boolean privateStatus) {
        this.privateStatus = privateStatus;
    }

    public String getVisualization() {
        return visualization;
    }

    public void setVisualization(String visualization) {
        this.visualization = visualization;
    }

    public TimerScreen getTimerScreen() {
        return timerScreen;
    }

    public void setTimerScreen(TimerScreen timerScreen) {
        this.timerScreen = timerScreen;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    //Custom Methods


    public void repeat(int eventNum, ZonedDateTime endtime){

        long diffInMinutes = ChronoUnit.WEEKS.between(this.events.get(eventNum-1).getStartTime(), endtime);
        int weeks = (int)diffInMinutes;
        int count = 2;
        for (int i = 0;i<weeks;i++){
            this.events.add(new Event( this.owner, this.events.get(eventNum-1).getStartTime().plusWeeks(i+1),
                    this.events.get(eventNum-1).getEndTime().plusWeeks(i+1),
                    this.events.get(eventNum-1).getTitle() + " #" + count ,
                    this.events.get(eventNum-1).getDetail(),
                    this.events.get(eventNum-1).getTags()));
            count++;
        }

    }

    public void updateEventTimeZones(User currentUser){
        int len = this.events.size();

        for (int i=0; i<len; i ++){
            this.events.get(i).setStartTime( this.events.get(i).getStartTime().withZoneSameInstant( ZoneId.of(ZoneId.SHORT_IDS.get( currentUser.getConfigScreen().getTimeZone() )) ) );
            this.events.get(i).setEndTime( this.events.get(i).getEndTime().withZoneSameInstant( ZoneId.of(ZoneId.SHORT_IDS.get( currentUser.getConfigScreen().getTimeZone() )) ) );
        }

        if (len != 0){
            System.out.println("Events timezone succesfully updated!");
        }
    }


    public void show(){

        System.out.println("# of Events: " + this.events.size());
        System.out.println("# of Timers: " + this.timerScreen.getTimers().size());
        System.out.println("Owner: " + this.owner.getUserName());

//        if (this.events.size()!=0){
//            this.events.get(0).show();
//        }

        if(this.privateStatus){
            System.out.println("Visibility: Private");
        }else {
            System.out.println("Visibility: Public");
        }
        System.out.println("Visualization: " + this.visualization);
        System.out.println("TimeZone: " + this.owner.getConfigScreen().getTimeZone());
        System.out.println("Language: " + this.language);
        System.out.println("*************************************");

    }

    public void showEventsSummary(int calendarNum){
        int len = this.events.size();
        int count = 1;
        System.out.println("EVENTS FOR " + this.owner.getUserName() +"'s " + "CALENDAR #" +  calendarNum);
        System.out.println("*************************************");
        for (int i = 0; i < len; i++){
            System.out.println("Event# " + count);
            this.events.get(i).showSummary();
            count++;
        }
        System.out.println("*************************************");
    }

    public void showTimers(int calNum){
        this.timerScreen.show(calNum);
    }


}
