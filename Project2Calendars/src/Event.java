import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Event {

    //Attributes

//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private int duration;
    private String tags;
    private String title;
    private String detail;
    private ArrayList<User> users;

    Event(User user,
          int startYear, int startMonth, int startDay, int startHour, int startMin,
          int endYear, int endMonth, int endDay, int endHour, int endMin,
          String title, String detail, String tags){

//        this.startTime = LocalDateTime.of(startYear, startMonth, startDay, startHour, startMin);
//        this.endTime = LocalDateTime.of(endYear, endMonth, endDay, endHour, endMin);

        this.startTime = ZonedDateTime.of(startYear, startMonth, startDay,
                startHour, startMin, 0,0,
                ZoneId.of(ZoneId.SHORT_IDS.get(user.getConfigScreen().getTimeZone())));

        this.endTime = ZonedDateTime.of(endYear, endMonth, endDay,
                endHour, endMin, 0, 0,
                ZoneId.of(ZoneId.SHORT_IDS.get(user.getConfigScreen().getTimeZone())));

        long diffInMinutes = ChronoUnit.MINUTES.between(this.startTime, this.endTime);
        this.duration = (int)diffInMinutes;

        this.tags = tags;
        this.title = title;
        this.detail = detail;
        users = new ArrayList<User>();
        this.users.add(user);

    }
    Event(User user, ZonedDateTime startTime, ZonedDateTime endTime, String title, String detail, String tags){
        this.startTime = startTime;
        this.endTime = endTime;

        long diffInMinutes = ChronoUnit.MINUTES.between(this.startTime, this.endTime);
        this.duration = (int)diffInMinutes;

        this.title = title;
        this.detail = detail;
        this.tags = tags;
        users = new ArrayList<User>();
        this.users.add(user);
    }



    //Getters and Setters

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }


    //Custom Functions
    public void show(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, YYYY h:m a z");
        System.out.println("*****************************************");
        System.out.println("Title: " + this.title);
        System.out.println("Start Time: " + dtf.format(this.startTime));
        System.out.println("End Time: " + dtf.format(this.endTime));
        System.out.println("Duration: " + this.duration + " minutes");
        System.out.println("Details: " + this.detail);
        System.out.println("Tags: " + this.tags);
        System.out.println("Owner: " + this.users.get(0).getUserName());
        System.out.print("Shared with: ");
        for (int i = 1; i<this.users.size(); i++){
            System.out.print(this.users.get(i).getUserName() + ", ");
        }
        System.out.println();
        System.out.println("*****************************************");
    }

    public void showSummary(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, YYYY h:m a z");
        System.out.println("*****************************************");
        System.out.println("Title: " + this.title);
        System.out.println("Start Time: " + dtf.format(this.startTime));
        System.out.println("End Time: " + dtf.format(this.endTime));
        System.out.println("Owner: " + this.users.get(0).getUserName());
        System.out.print("Shared with: ");
        for (int i = 1; i<this.users.size(); i++){
            System.out.print(this.users.get(i).getUserName() + ", ");
        }
        System.out.println();
        System.out.println("*****************************************");
    }
}
