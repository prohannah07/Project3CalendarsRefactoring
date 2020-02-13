import javax.sound.midi.Soundbank;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        UserSet allUsers = new UserSet();
        boolean isNotQuit = true;

        System.out.println("*********************************");
        System.out.println("* WELCOME TO THE CALENDARS APP! *");
        System.out.println("*********************************");

        Scanner input = new Scanner(System.in);
        login(allUsers);

        while(isNotQuit){
            try{
                viewMainMenu();
                System.out.print("Type Command: ");
                String i = input.nextLine();

                if (i.equals("vs") || i.equals("cs")){
                    settingsMainMenu(i, allUsers);
                }else if (i.equals("hc") || i.equals("ac") || i.equals("dc") ||
                        i.equals("ucp") || i.equals("ucv") || i.equals("vsc") ||
                        i.equals("vc") || i.equals("vac") ||i.equals("vsp")) {
                    calendarMainMenu(i, allUsers);
                } else if( i.equals("ae") || i.equals("re") || i.equals("se") ||
                        i.equals("rp") || i.equals("vse") || i.equals("vae") ||
                        i.equals("sch") || i.equals("sel") || i.equals("upd")) {
                    eventsMainMenu(i, allUsers);
                }else if (i.equals("at") || i.equals("vt")){
                    timersMenu(i, allUsers);
                }
                else if(i.equals("cu")){
                    login(allUsers);
                }else if (i.equals("q")){
                    isNotQuit = false;
                }else{
                    System.out.println();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("+ That is not a valid command. Try Again +");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++");

                }
            }catch (Exception e){
                System.out.println();
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+ Something went wrong, please re-select a command +");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println();
            }

        }




    }

    public static void login(UserSet userSet){
        if (userSet.getUserSet().isEmpty()){
            emptyUserSet(userSet);
        }else{
            System.out.println("Current Users:");
            userSet.printUsers();
            System.out.println();
            notEmptyUserSet(userSet);
        }
    }

    public static void notEmptyUserSet(UserSet userSet){
        Scanner input = new Scanner(System.in);

        System.out.println("Do you have an existing account? (y or n):");
        String answer = input.nextLine();

        if (answer.equals("y")){
            System.out.println("What is your Username?:");
            String uname = input.nextLine();

            if (userSet.inUserSet(uname)){
                yesInputValidUsername(userSet, uname);
            }else{
                yesInputNoUserName(userSet);
            }
        }else if(answer.equals("n")){
            System.out.println("Please enter a username:");
            String uname = input.nextLine();

            if(userSet.inUserSet(uname)){
                noInputInvalidUsername(userSet);
            }else{
                noInputValidUsername(userSet, uname);
            }
        }

    }

    public static void emptyUserSet(UserSet userSet){

            System.out.println("Your are the first user!");
            System.out.println("Please enter a username:");
            Scanner input = new Scanner(System.in);
            String uname = input.nextLine();
            userSet.addUser(new User(uname));
            System.out.println("Hello " + uname + "!");
            userSet.setCurrentUser(uname);
    }

    public static void yesInputValidUsername(UserSet userSet, String username){
        System.out.println("Hello " + username + "!");
        userSet.setCurrentUser(username);
//        System.out.println(userSet.getCurrentUser().getUserName());
    }

    public static void yesInputNoUserName(UserSet userSet){

        System.out.println("That account doesn't exist.");
        System.out.println("Try again.");
        System.out.println();
        System.out.println("Please enter a username:");

        boolean notCeatedValidUsername = true;
        Scanner input = new Scanner(System.in);
        String uname;

        while (notCeatedValidUsername){

            uname = input.nextLine();

            if (userSet.inUserSet(uname)){
                System.out.println("That is a valid username!");
                userSet.addUser(new User(uname));
                System.out.println("Hello " + uname + "!");
                userSet.setCurrentUser(uname);
                notCeatedValidUsername = false;
            }else{
                System.out.println("That account doesn't exist.");
                System.out.println("Try again:");
                System.out.println();
                System.out.println("Please enter a username:");
            }

        }

    }

    public static void noInputInvalidUsername(UserSet userSet){

        boolean notCreatedValidUsername = true;
        Scanner input = new Scanner(System.in);
        String uname;

        while(notCreatedValidUsername){
            System.out.println("Sorry that username is taken.");
            System.out.println("Please choose a different one:");
            uname = input.nextLine();
            if(!userSet.inUserSet(uname)){
                System.out.println("That is a valid username!");
                userSet.addUser(new User(uname));
                System.out.println("Hello " + uname + "!");
                userSet.setCurrentUser(uname);
                notCreatedValidUsername = false;
            }
        }

    }

    public static void noInputValidUsername(UserSet userSet, String username){
        System.out.println("That is a valid username!");
        userSet.addUser(new User(username));
        System.out.println("Hello " + username + "!");
        userSet.setCurrentUser(username);
    }

    public static void viewMainMenu(){
        System.out.println();
        System.out.println("===========================");
        System.out.println("= WHAT DO YOU WANT TO DO? =");
        System.out.println("===========================");
        System.out.println("-----------------------------USER SETTINGS---------------------------------");
        System.out.println("  vs: view settings");
        System.out.println("  cs: change settings");
        System.out.println("------------------------CALENDAR-CENTRIC COMMANDS--------------------------");
        System.out.println("  hc: check how many calendars do you have");
        System.out.println("  ac: add calendar");
        System.out.println("  dc: delete calendar");
        System.out.println("  ucp: update calendar privacy");
        System.out.println("  ucv: update calendar visualization");
        System.out.println("  vc: view MY calendar/s");
        System.out.println("  vsp: view SPECIFIC Calendar with events based on visualization setting");
        System.out.println("  vac: view my calendar/s and all PUBLIC calendars");
        System.out.println("-------------------------EVENT-CENTRIC COMMANDS----------------------------");
        System.out.println("  ae: add event");
        System.out.println("  re: remove event");
        System.out.println("  se: share event");
        System.out.println("  rp: repeat event weekly");
        System.out.println("  vae: view ALL events (mine and shared)");
        System.out.println("  vse: view shared events");
        System.out.println("  sel: select event for more details");
        System.out.println("  upd: update an event");
        System.out.println("  sch: search events that you can view");
        System.out.println("-------------------------TIMER COMMANDS----------------------------");
        System.out.println("  at: add Timer");
        System.out.println("  vt: view Timers");
        System.out.println("-------------------------------EXIT COMMANDS-------------------------------");
        System.out.println("  cu: change user");
        System.out.println("  q : quit");
    }

    public static void settingsMainMenu(String i, UserSet userSet){
        Scanner input = new Scanner(System.in);

        if (i.equals("vs")){
            userSet.getCurrentUser().getConfigScreen().show();
        }else if(i.equals("cs")){
            System.out.println("***************************");
            System.out.println("Change Settings");
            System.out.println();
            showTimezones();
            System.out.println();
            System.out.println("Please choose the abbreviation (i.e. PST) that corresponds to the timezone that you want:");
            String tz = input.nextLine();
            userSet.getCurrentUser().getConfigScreen().setTimeZone(tz);
            System.out.println();

            System.out.println("Choose theme number (0: Dark OR 1: Light)");
            String tm = input.nextLine();
            if(tm.equals("0")){
                userSet.getCurrentUser().getConfigScreen().setDarkMode(true);
            }else{
                userSet.getCurrentUser().getConfigScreen().setDarkMode(false);
            }

            userSet.getCurrentUser().getConfigScreen().show();
            int lenCalendars = userSet.getCurrentUser().getCalendars().size();
            for (int j = 0; j < lenCalendars; j++){
                userSet.getCurrentUser().getCalendars().get(j).updateEventTimeZones(userSet.getCurrentUser());
            }

        }
    }

    public static void showTimezones(){
        for (String key: ZoneId.SHORT_IDS.keySet()){
            System.out.println(key + " - " + ZoneId.SHORT_IDS.get(key));
        }
    }

    public static void calendarMainMenu(String i, UserSet userset){
        Scanner input = new Scanner(System.in);

        if (i.equals("hc"))
            System.out.println("Currently, " + userset.getCurrentUser().getUserName() + " has " + userset.getCurrentUser().getCalendars().size() + " calendar/s");
        else if(i.equals("ac")) userset.getCurrentUser().addCalendar(userset.getCurrentUser());
        else if(i.equals("vac")) {
            userset.showAllPublicCalendarsAndMyCalendars();
        } else if(userset.getCurrentUser().getCalendars().size()==0){
            System.out.println("**************************************************");
            System.out.println("U HAVE NO CALENDARS!");
            System.out.println("Please create a calendar before doing any Calendar commands!");
            System.out.println("**************************************************");
        } else if(i.equals("dc")){
//            int len = userset.getCurrentUser().getCalendars().size();
//            for(int j = 0; j < len; j++){
//                System.out.println("*************************************");
//                System.out.println("Calendar #: " + (j+1));
//                userset.getCurrentUser().getCalendars().get(j).show();
//            }
            userset.getCurrentUser().showMyCalendars();

            System.out.println("What # Calendar would you like to remove?");
            int ans = input.nextInt();
            userset.getCurrentUser().removeCalendar(ans);
        }else if(i.equals("vc")){
//            int len = userset.getCurrentUser().getCalendars().size();
//            for(int j = 0; j < len; j++){
//                System.out.println("*************************************");
//                System.out.println("Calendar #: " + (j+1));
//                userset.getCurrentUser().getCalendars().get(j).show();
//            }
            userset.getCurrentUser().showMyCalendars();
        } else if(i.equals("ucp")){
            System.out.println();
            userset.getCurrentUser().showMyCalendars();
            System.out.println("What # Calendar would you like to update its privacy?");
            int ans = input.nextInt();
            System.out.println();
            userset.getCurrentUser().changePrivateStatus(ans);

            userset.getCurrentUser().showMyCalendars();
            System.out.println("Successfully Updated!");
        }else if(i.equals("ucv")){
            System.out.println();
            userset.getCurrentUser().showMyCalendars();
            System.out.println("What # Calendar would you like to update its visualization?");
            int ans = input.nextInt();

            System.out.println("What kind of visualization do you want it to be?");
            System.out.println("1:Yearly | 2:Monthly | 3:Weekly | 4:Daily");
            int type = input.nextInt();
            userset.getCurrentUser().changeVisualization(ans, type);

            userset.getCurrentUser().showMyCalendars();
            System.out.println("Successfully Updated!");
        }else if(i.equals("vsp")){
            System.out.println();
            userset.getCurrentUser().showMyCalendars();
            System.out.println("What # Calendar would you like to view?");
            int ans = input.nextInt();
            userset.getCurrentUser().filterEvents(ans);
        }
    }

    public static void eventsMainMenu(String i, UserSet userset){
        Scanner input = new Scanner(System.in);

        if(userset.getCurrentUser().getCalendars().size()==0){
            System.out.println();
            System.out.println("**************************************************");
            System.out.println("U HAVE NO CALENDARS!");
            System.out.println("Please create a calendar before adding any events!");
            System.out.println("**************************************************");
            System.out.println();
        }else if (i.equals("ae")){
//            int len = userset.getCurrentUser().getCalendars().size();
//            for(int j = 0; j < len; j++){
//                System.out.println("*************************************");
//                System.out.println("Calendar #: " + (j+1));
//                userset.getCurrentUser().getCalendars().get(j).show();
//            }
            userset.getCurrentUser().showMyCalendars();

            System.out.println("What calendar # do you want to add an event to?");
            int calendarNumber = input.nextInt();
            input.nextLine();

            System.out.println("What is the title of the event?");
            String title = input.nextLine();
            System.out.println("What are the details of the event?");
            String detail = input.nextLine();
            System.out.println("What are tags for the event?");
            String tags = input.nextLine();

            System.out.println("What is the start year?");
            int startYear = input.nextInt();
            input.nextLine();
            System.out.println("What is the start month?");
            System.out.println("1:Jan | 2:Feb | 3:Mar | 4:Apr | 5:May | 6:Jun | 7:Jul | 8:Aug | 9: Sep | 10:Oct | 11:Nov | 12:Dec");
            int startMonth = input.nextInt();
            input.nextLine();
            System.out.println("What is the start day (day of the month : valid values 1-31)?");
            int startDay = input.nextInt();
            input.nextLine();
            System.out.println("What is the start hour? (valid values 0 - 23)");
            int startHour = input.nextInt();
            input.nextLine();
            System.out.println("What is the start minute? (valid values 0 - 59)");
            int startMin = input.nextInt();
            input.nextLine();

            System.out.println("What is the end year?");
            int endYear = input.nextInt();
            input.nextLine();
            System.out.println("What is the end month?");
            System.out.println("1:Jan | 2:Feb | 3:Mar | 4:Apr | 5:May | 6:Jun | 7:Jul | 8:Aug | 9: Sep | 10:Oct | 11:Nov | 12:Dec");
            int endMonth = input.nextInt();
            input.nextLine();
            System.out.println("What is the end day (day of the month : valid values 1-31)?");
            int endDay = input.nextInt();
            input.nextLine();
            System.out.println("What is the end hour? (valid values 0 - 23)");
            int endHour = input.nextInt();
            input.nextLine();
            System.out.println("What is the end minute? (valid values 0 - 59)");
            int endMin = input.nextInt();
            input.nextLine();

            userset.getCurrentUser().addEvent(calendarNumber, new Event(userset.getCurrentUser(),
                    startYear, startMonth, startDay, startHour, startMin,
                    endYear, endMonth, endDay, endHour, endMin,
                    title, detail, tags));

        }else if(i.equals("re")){
//            int len = userset.getCurrentUser().getCalendars().size();
//            for(int j = 0; j < len; j++){
//                System.out.println("*************************************");
//                System.out.println("Calendar #: " + (j+1));
//                userset.getCurrentUser().getCalendars().get(j).show();
//            }
            userset.getCurrentUser().showMyCalendars();

            System.out.println("What calendar # do you want to remove an event from?");
            int calendarNum = input.nextInt();
            input.nextLine();

            System.out.println();
            System.out.println("Events from Calendar# "+ calendarNum);
            int lenEvents = userset.getCurrentUser().getCalendars().get(calendarNum-1).getEvents().size();
            for (int j = 0; j < lenEvents; j++){
                userset.getCurrentUser().getCalendars().get(calendarNum-1).getEvents().get(j).show();
            }

            System.out.println("What is the title of the event you want to delete?");
            String title = input.nextLine();

            userset.getCurrentUser().removeEvent(calendarNum, title);

        }else if(i.equals("se")){
            userset.getCurrentUser().showMyCalendars();
            System.out.println("From which calendar # did you want to share an event?");
            int calNum = input.nextInt();
            input.nextLine();

            userset.getCurrentUser().getCalendars().get(calNum-1).showEventsSummary(calNum);
            System.out.println("What is the title of the event that you wanted to share?");
            String title = input.nextLine();

            System.out.println();
            System.out.println("Current users u can share with:");
            userset.printUsersExceptCurrent();
            System.out.println();
            System.out.println("Who do you hanna share the event with?");
            String username = input.nextLine();

            userset.getCurrentUser().shareEvent(userset, username, calNum, title);
            userset.getCurrentUser().getCalendars().get(calNum-1).showEventsSummary(calNum);

        }else if(i.equals("rp")){
            userset.getCurrentUser().showMyCalendars();
            System.out.println("From which calendar # did you want to repeat an event weekly?");
            int calNum = input.nextInt();
            input.nextLine();

            userset.getCurrentUser().getCalendars().get(calNum-1).showEventsSummary(calNum);
            System.out.println("Which Event # did you want to repeat weekly?");
            int eventNum = input.nextInt();
            input.nextLine();

            System.out.println("Until when did you want to repeat this event?");
            System.out.println("What is the end year?");
            int endYear = input.nextInt();
            input.nextLine();
            System.out.println("What is the end month?");
            System.out.println("1:Jan | 2:Feb | 3:Mar | 4:Apr | 5:May | 6:Jun | 7:Jul | 8:Aug | 9: Sep | 10:Oct | 11:Nov | 12:Dec");
            int endMonth = input.nextInt();
            input.nextLine();
            System.out.println("What is the end day (day of the month : valid values 1-31)?");
            int endDay = input.nextInt();
            input.nextLine();
            System.out.println("What is the end hour? (valid values 0 - 23)");
            int endHour = input.nextInt();
            input.nextLine();
            System.out.println("What is the end minute? (valid values 0 - 59)");
            int endMin = input.nextInt();
            input.nextLine();

            ZonedDateTime endRepeat = ZonedDateTime.of(endYear, endMonth, endDay, endHour, endMin, 0, 0, ZoneId.of(ZoneId.SHORT_IDS.get(userset.getCurrentUser().getConfigScreen().getTimeZone())));
            userset.getCurrentUser().getCalendars().get(calNum-1).repeat(eventNum, endRepeat);
            userset.getCurrentUser().getCalendars().get(calNum-1).showEventsSummary(calNum);

        }else if(i.equals("vse")){
            userset.getCurrentUser().showSharedEvent();
        }else if(i.equals("vae")){
            userset.getCurrentUser().showAllMyEventsAndSharedFromAllCalendars();
        }else if(i.equals("sch")){
            System.out.println();
            System.out.println("What are you looking for?");
            String query = input.nextLine();
            userset.getCurrentUser().search(query);
        }else if(i.equals("sel")){
            System.out.println();
            System.out.println("Which Event did you want to look at in more detail?");
            userset.getCurrentUser().eventTitlesCombined();
            int eventNum = input.nextInt();
            input.nextLine();
            userset.getCurrentUser().getEventDetail(eventNum);
            System.out.println();
        }else if(i.equals("upd")){
            System.out.println();
            System.out.println("Which Event did you want to update?");
            userset.getCurrentUser().showMyEventsTitles();
            int eventNum = input.nextInt();
            input.nextLine();
//            userset.getCurrentUser().getEventDetail(eventNum);
            System.out.println();
            System.out.println("What did u want to update?");
            System.out.println("1:startTime | 2:endTime | 3:title, detail and tags");
            int whatToUpdate = input.nextInt();
            input.nextLine();

            if (whatToUpdate==1){

                System.out.println("What is the start year?");
                int startYear = input.nextInt();
                input.nextLine();
                System.out.println("What is the start month?");
                System.out.println("1:Jan | 2:Feb | 3:Mar | 4:Apr | 5:May | 6:Jun | 7:Jul | 8:Aug | 9: Sep | 10:Oct | 11:Nov | 12:Dec");
                int startMonth = input.nextInt();
                input.nextLine();
                System.out.println("What is the start day (day of the month : valid values 1-31)?");
                int startDay = input.nextInt();
                input.nextLine();
                System.out.println("What is the start hour? (valid values 0 - 23)");
                int startHour = input.nextInt();
                input.nextLine();
                System.out.println("What is the start minute? (valid values 0 - 59)");
                int startMin = input.nextInt();
                input.nextLine();

                ZonedDateTime newStart = ZonedDateTime.of(startYear, startMonth, startDay, startHour, startMin, 0, 0, ZoneId.of(ZoneId.SHORT_IDS.get(userset.getCurrentUser().getConfigScreen().getTimeZone())));
                userset.getCurrentUser().setStartTime(eventNum,newStart);
                System.out.println("Event Updated!");
            }else if(whatToUpdate == 2){

                System.out.println("What is the end year?");
                int endYear = input.nextInt();
                input.nextLine();
                System.out.println("What is the end month?");
                System.out.println("1:Jan | 2:Feb | 3:Mar | 4:Apr | 5:May | 6:Jun | 7:Jul | 8:Aug | 9: Sep | 10:Oct | 11:Nov | 12:Dec");
                int endMonth = input.nextInt();
                input.nextLine();
                System.out.println("What is the end day (day of the month : valid values 1-31)?");
                int endDay = input.nextInt();
                input.nextLine();
                System.out.println("What is the end hour? (valid values 0 - 23)");
                int endHour = input.nextInt();
                input.nextLine();
                System.out.println("What is the end minute? (valid values 0 - 59)");
                int endMin = input.nextInt();
                input.nextLine();

                ZonedDateTime newEnd = ZonedDateTime.of(endYear, endMonth, endDay, endHour, endMin, 0, 0, ZoneId.of(ZoneId.SHORT_IDS.get(userset.getCurrentUser().getConfigScreen().getTimeZone())));
                userset.getCurrentUser().setEndTime(eventNum,newEnd);

            }else if(whatToUpdate==3){
                System.out.println("What is the title of the event?");
                String title = input.nextLine();
                System.out.println("What are the details of the event?");
                String detail = input.nextLine();
                System.out.println("What are tags for the event?");
                String tags = input.nextLine();

                userset.getCurrentUser().updateEvent(eventNum, title, detail,tags);
            }



        }
    }

    public static void timersMenu(String i, UserSet userset){
        Scanner input = new Scanner(System.in);

        if(userset.getCurrentUser().getCalendars().size()==0) {
            System.out.println("**************************************************");
            System.out.println("U HAVE NO CALENDARS!");
            System.out.println("Please create a calendar before doing any Timer commands!");
            System.out.println("**************************************************");
        }else if (i.equals("at")){
            userset.getCurrentUser().showMyCalendars();
            System.out.println("From which calendar # did you want to put a countdown timer?");
            int calNum = input.nextInt();
            input.nextLine();

            System.out.println("What is the name of this timer?");
            String title = input.nextLine();

            System.out.println("When is this timer ending?");
            System.out.println("What is the end year?");
            int endYear = input.nextInt();
            input.nextLine();
            System.out.println("What is the end month?");
            System.out.println("1:Jan | 2:Feb | 3:Mar | 4:Apr | 5:May | 6:Jun | 7:Jul | 8:Aug | 9: Sep | 10:Oct | 11:Nov | 12:Dec");
            int endMonth = input.nextInt();
            input.nextLine();
            System.out.println("What is the end day (day of the month : valid values 1-31)?");
            int endDay = input.nextInt();
            input.nextLine();
            System.out.println("What is the end hour? (valid values 0 - 23)");
            int endHour = input.nextInt();
            input.nextLine();
            System.out.println("What is the end minute? (valid values 0 - 59)");
            int endMin = input.nextInt();
            input.nextLine();

            ZonedDateTime end = ZonedDateTime.of(endYear, endMonth, endDay, endHour, endMin, 0, 0, ZoneId.of(ZoneId.SHORT_IDS.get(userset.getCurrentUser().getConfigScreen().getTimeZone())));

            userset.getCurrentUser().getCalendars().get(calNum-1).getTimerScreen().getTimers().add( new Timer(end,title));
            int len = userset.getCurrentUser().getCalendars().get(calNum-1).getTimerScreen().getTimers().size();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, YYYY h:m a z");
            System.out.println("**********************************************");
            System.out.println(userset.getCurrentUser().getCalendars().get(calNum-1).getTimerScreen().getTimers().get(len-1).getTitle());
            System.out.println(dtf.format(userset.getCurrentUser().getCalendars().get(calNum-1).getTimerScreen().getTimers().get(len-1).getTime()));
            System.out.println("**********************************************");
            System.out.println("Succesfully Added Timer!");

        }else if(i.equals("vt")){

            userset.getCurrentUser().showMyCalendars();
            System.out.println("From which calendar # did you want to see your timers?");
            int calNum = input.nextInt();
            input.nextLine();

//            userset.getCurrentUser().getCalendars().get(calNum-1).getTimerScreen().show(calNum);
            userset.getCurrentUser().getCalendars().get(calNum-1).showTimers(calNum);
        }
    }

}
