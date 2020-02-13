import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.*;

public class User {

    private ArrayList<Calendar> calendars;
    private String userName;
    private ConfigScreen configScreen;
    private ArrayList<Event> sharedEvents;
    private ArrayList<Event> allEventsUnderUser;

    User(String userName){
        calendars = new ArrayList<Calendar>();
        this.userName = userName;
        this.configScreen = new ConfigScreen();
        sharedEvents = new ArrayList<Event>();
        allEventsUnderUser = new ArrayList<Event>();
    }

    public ArrayList<Calendar> getCalendars() {
        return calendars;
    }

    public void setCalendars(ArrayList<Calendar> calendars) {
        this.calendars = calendars;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ConfigScreen getConfigScreen() {
        return configScreen;
    }

    public void setConfigScreen(ConfigScreen configScreen) {
        this.configScreen = configScreen;
    }

    public ArrayList<Event> getSharedEvents() {
        return sharedEvents;
    }

    public void setSharedEvents(ArrayList<Event> sharedEvents) {
        this.sharedEvents = sharedEvents;
    }



    //----------------------------------------------

    public void setStartTime(int eventNum, ZonedDateTime start){
        int len = this.calendars.size();
        int count = 1;
        for (int i = 0; i < len; i++){

            int eventLen = this.calendars.get(i).getEvents().size();
            for (int j = 0; j < eventLen; j++){
                if (count==eventNum){
                    this.calendars.get(i).getEvents().get(j).setStartTime(start);
                }
                count++;
            }
        }

    }

    public void setEndTime(int eventNum, ZonedDateTime start){
        int len = this.calendars.size();
        int count = 1;
        for (int i = 0; i < len; i++){

            int eventLen = this.calendars.get(i).getEvents().size();
            for (int j = 0; j < eventLen; j++){
                if (count==eventNum){
                    this.calendars.get(i).getEvents().get(j).setEndTime(start);
                }
                count++;
            }
        }

    }

    //----------------------------------------------

    public void addEvent(int calendarNum, Event event){
        this.calendars.get(calendarNum-1).getEvents().add(event);

        int len = this.calendars.get(calendarNum-1).getEvents().size();
        this.calendars.get(calendarNum-1).getEvents().get(len-1).show();
    }

    public void removeEvent(int calendarNum, String title){
        int len = this.calendars.get(calendarNum-1).getEvents().size();

        for (int i = 0; i <len; i++){
            if (this.calendars.get(calendarNum-1).getEvents().get(i).getTitle().equals(title)){
                this.calendars.get(calendarNum-1).getEvents().remove(i);
            }
        }

        System.out.println("Event '"+ title+"' successfully removed!");
    }

    public void updateEvent(int eventNum, String title, String detail, String tags){
        int len = this.calendars.size();
        int count = 1;
        for (int i = 0; i < len; i++){

            int eventLen = this.calendars.get(i).getEvents().size();
            for (int j = 0; j < eventLen; j++){
                if (count==eventNum){
                    this.calendars.get(i).getEvents().get(j).setTitle(title);
                    this.calendars.get(i).getEvents().get(j).setDetail(detail);
                    this.calendars.get(i).getEvents().get(j).setDetail(tags);
                }
                count++;
            }
        }

    }


    public void addCalendar(User owner){
        this.calendars.add(new GregorianCalendar(owner));
        System.out.println("*************************************");
        System.out.println("Succesfully Added Gregorian Calendar!");
        int last = this.calendars.size()-1;
        System.out.println("Calendar #: " + this.calendars.size());
        System.out.println("Owner: " + owner.getUserName());
        System.out.println("# of Events: " + this.calendars.get(last).getEvents().size());
        System.out.println("Visibility: public");
        System.out.println("Visualization: yearly");
        System.out.println("Language: " + this.calendars.get(last).getLanguage());
        System.out.println("*************************************");

    }

    public void showMyCalendars(){
        int len = this.calendars.size();
        for (int i= 0; i<len; i++){
            System.out.println("*************************************");
            System.out.println("Calendar #: " + (i+1));
            this.calendars.get(i).show();
        }
    }

    public void removeCalendar(int i){
        this.calendars.remove(i-1);
    }

    public void changeVisualization(int calNum, int type){

        Map<Integer, String> visualizationTypes = new HashMap<Integer, String>();
        visualizationTypes.put(1, "yearly");
        visualizationTypes.put(2, "monthly");
        visualizationTypes.put(3, "weekly");
        visualizationTypes.put(4, "daily");
        this.calendars.get(calNum-1).setVisualization(visualizationTypes.get(type));
    }

    public void changePrivateStatus(int calNum){
//        System.out.println(this.calendars.get(calNum-1).getPrivateStatus());
        if (this.calendars.get(calNum-1).getPrivateStatus()){
            this.calendars.get(calNum-1).setPrivateStatus(false);
        }else{
            this.calendars.get(calNum-1).setPrivateStatus(true);
        }


    }

    public void showSharedEvent(){
        int len = this.sharedEvents.size();

        if (len==0){
            System.out.println("****************************");
            System.out.println("NO SHARED EVENTS WITH YOU :(");
            System.out.println("****************************");
        }else{
            for (int i = 0; i < len ; i++){
                this.sharedEvents.get(i).show();
            }
        }

    }


    public void shareEvent(UserSet userSet, String username, int calendarNum, String title){

        int len = userSet.getUserSet().size();
        int userNum = 0;
        for (int i = 0; i<len;i++){
            if (userSet.getUserSet().get(i).getUserName().equals(username)){
                userNum = i;
            }
        }

        int eventsLen = this.calendars.get(calendarNum-1).getEvents().size();
        for (int i = 0; i< eventsLen; i++){
            if (this.calendars.get(calendarNum-1).getEvents().get(i).getTitle().equals(title)){
                userSet.getUserSet().get(userNum).getSharedEvents().add(this.calendars.get(calendarNum-1).getEvents().get(i));
                this.calendars.get(calendarNum-1).getEvents().get(i).getUsers().add(userSet.getUserSet().get(userNum));

            }
        }

    }

    public void showMyEventsTitles(){
        int len = this.calendars.size();
        int count = 1;
        for (int i = 0; i < len; i++){

            int eventLen = this.calendars.get(i).getEvents().size();
            for (int j = 0; j < eventLen; j++){
                System.out.println(count +": " + this.calendars.get(i).getEvents().get(j).getTitle());
                count++;
            }
        }
    }

    public void showAllMyEventsAndSharedFromAllCalendars(){
        int len = this.calendars.size();
        for (int i = 0; i < len; i++){

            int eventLen = this.calendars.get(i).getEvents().size();
            for (int j = 0; j < eventLen; j++){
                this.calendars.get(i).getEvents().get(j).show();
            }
        }

        len = this.sharedEvents.size();
        for (int i = 0; i<len; i++){
            this.sharedEvents.get(i).show();
        }

    }

    public void filterEvents(int calNum){
        if(this.calendars.get(calNum-1).getVisualization().equals("yearly")){
            filterYearly(calNum);
        }else if(this.calendars.get(calNum-1).getVisualization().equals("monthly")){
            filterMonthly(calNum);
        }else if(this.calendars.get(calNum-1).getVisualization().equals("weekly")){
            filterWeekly(calNum);
        }else{
            filterDaily(calNum);
        }

    }

    private void filterYearly(int calNum){
        Map<Integer, ArrayList<Event>> byYear = new HashMap<Integer, ArrayList<Event>>();

        int eventLen = this.calendars.get(calNum-1).getEvents().size();
        for (int j = 0; j < eventLen; j++){

            byYear.putIfAbsent(this.calendars.get(calNum-1).getEvents().get(j).getStartTime().getYear(), new ArrayList<Event>());
            byYear.get(this.calendars.get(calNum-1).getEvents().get(j).getStartTime().getYear()).add(this.calendars.get(calNum-1).getEvents().get(j));
        }


        System.out.println("******************");
        System.out.println("* FILTER BY YEAR *");
        System.out.println("******************");
        for (int year : byYear.keySet()) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("YEAR: " + year);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (int i = 0; i < byYear.get(year).size(); i++){
                byYear.get(year).get(i).show();
            }
        }

    }

    private void filterMonthly(int calNum){
        Map<Month, ArrayList<Event>> byYear = new HashMap<Month, ArrayList<Event>>();

        int eventLen = this.calendars.get(calNum-1).getEvents().size();
        for (int j = 0; j < eventLen; j++){

            byYear.putIfAbsent(this.calendars.get(calNum-1).getEvents().get(j).getStartTime().getMonth(), new ArrayList<Event>());
            byYear.get(this.calendars.get(calNum-1).getEvents().get(j).getStartTime().getMonth()).add(this.calendars.get(calNum-1).getEvents().get(j));
        }


        System.out.println("******************");
        System.out.println("* FILTER BY YEAR *");
        System.out.println("******************");
        for (Month month : byYear.keySet()) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("MONTH: " + month);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (int i = 0; i < byYear.get(month).size(); i++){
                byYear.get(month).get(i).show();
            }
        }

    }

    private void filterWeekly(int calNum){
        Map<DayOfWeek, ArrayList<Event>> byYear = new HashMap<DayOfWeek, ArrayList<Event>>();

        int eventLen = this.calendars.get(calNum-1).getEvents().size();
        for (int j = 0; j < eventLen; j++){

            byYear.putIfAbsent(this.calendars.get(calNum-1).getEvents().get(j).getStartTime().getDayOfWeek(), new ArrayList<Event>());
            byYear.get(this.calendars.get(calNum-1).getEvents().get(j).getStartTime().getDayOfWeek()).add(this.calendars.get(calNum-1).getEvents().get(j));
        }


        System.out.println("******************");
        System.out.println("* FILTER BY YEAR *");
        System.out.println("******************");
        for (DayOfWeek day : byYear.keySet()) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("DAY: " + day);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (int i = 0; i < byYear.get(day).size(); i++){
                byYear.get(day).get(i).show();
            }
        }

    }

    private void filterDaily(int calNum){
        System.out.println("******************");
        System.out.println("* FILTER DAILY*");
        System.out.println("******************");

        int len = this.calendars.get(calNum-1).getEvents().size();
        for (int i = 0; i < len ; i++){
            this.calendars.get(calNum-1).getEvents().get(i).show();
        }

    }

    public void eventTitlesCombined(){
        int len = this.calendars.size();
        for (int i = 0; i < len; i++){

            int eventLen = this.calendars.get(i).getEvents().size();
            for (int j = 0; j < eventLen; j++){
                this.allEventsUnderUser.add(this.calendars.get(i).getEvents().get(j));
            }
        }
        len = this.sharedEvents.size();
        for (int i = 0; i<len; i++){
            this.allEventsUnderUser.add(this.sharedEvents.get(i));
        }

        len = this.allEventsUnderUser.size();
        int count = 1;
        for (int i = 0; i < len; i++){
            System.out.println(count + ": " + this.allEventsUnderUser.get(i).getTitle());
            count++;
        }
    }

    public void getEventDetail(int eventNum){
        this.allEventsUnderUser.get(eventNum-1).show();
    }

    public void search(String query){
        int len = this.calendars.size();
        for (int i = 0; i < len; i++){

            int eventLen = this.calendars.get(i).getEvents().size();
            for (int j = 0; j < eventLen; j++){

                if (this.calendars.get(i).getEvents().get(j).getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        this.calendars.get(i).getEvents().get(j).getDetail().toLowerCase().contains(query.toLowerCase()) ||
                        this.calendars.get(i).getEvents().get(j).getTags().toLowerCase().contains(query.toLowerCase())){
                    this.calendars.get(i).getEvents().get(j).show();
                }

            }
        }

        len = this.sharedEvents.size();
        for (int i = 0; i<len; i++){

            if(this.sharedEvents.get(i).getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    this.sharedEvents.get(i).getDetail().toLowerCase().contains(query.toLowerCase()) ||
                    this.sharedEvents.get(i).getTags().toLowerCase().contains(query.toLowerCase())){
                this.sharedEvents.get(i).show();
            }


        }

    }




}
