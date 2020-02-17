import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class EventMenu extends Menu {

    EventMenu(UserSet userset){
        super(userset);
    }

    public void executeCommand(String i){
        Scanner input = new Scanner(System.in);

        if(this.userset.getCurrentUser().getCalendars().size()==0){
            System.out.println();
            System.out.println("**************************************************");
            System.out.println("U HAVE NO CALENDARS!");
            System.out.println("Please create a calendar before adding any events!");
            System.out.println("**************************************************");
            System.out.println();
        }else if (i.equals("ae")){
            this.userset.getCurrentUser().showMyCalendars();

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

            this.userset.getCurrentUser().addEvent(calendarNumber, new Event(userset.getCurrentUser(),
                    startYear, startMonth, startDay, startHour, startMin,
                    endYear, endMonth, endDay, endHour, endMin,
                    title, detail, tags));

        }else if(i.equals("re")){
            showUserCalendars();

            System.out.println("What calendar # do you want to remove an event from?");
            int calendarNum = input.nextInt();
            input.nextLine();

            System.out.println();
            System.out.println("Events from Calendar# "+ calendarNum);
            int lenEvents = UserMenuFacade.showCurrentUserEventsLen(this.userset, calendarNum);

            UserMenuFacade.showCurrentUserEventsFromSpecificCalendar(this.userset, calendarNum, lenEvents);

            System.out.println("What is the title of the event you want to delete?");
            String title = input.nextLine();

            UserMenuFacade.removeCurrentUserEvent(this.userset,calendarNum, title);

        }else if(i.equals("se")){
            this.userset.getCurrentUser().showMyCalendars();
            System.out.println("From which calendar # did you want to share an event?");
            int calNum = input.nextInt();
            input.nextLine();

            this.userset.getCurrentUser().getCalendars().get(calNum-1).showEventsSummary(calNum);
            System.out.println("What is the title of the event that you wanted to share?");
            String title = input.nextLine();

            System.out.println();
            System.out.println("Current users u can share with:");
            this.userset.printUsersExceptCurrent();
            System.out.println();
            System.out.println("Who do you hanna share the event with?");
            String username = input.nextLine();

            this.userset.getCurrentUser().shareEvent(userset, username, calNum, title);
            this.userset.getCurrentUser().getCalendars().get(calNum-1).showEventsSummary(calNum);

        }else if(i.equals("rp")){
            this.userset.getCurrentUser().showMyCalendars();
            System.out.println("From which calendar # did you want to repeat an event weekly?");
            int calNum = input.nextInt();
            input.nextLine();

            this.userset.getCurrentUser().getCalendars().get(calNum-1).showEventsSummary(calNum);
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

            ZonedDateTime endRepeat = ZonedDateTime.of(endYear, endMonth, endDay, endHour, endMin, 0, 0, ZoneId.of(ZoneId.SHORT_IDS.get(this.userset.getCurrentUser().getConfigScreen().getTimeZone())));
            this.userset.getCurrentUser().getCalendars().get(calNum-1).repeat(eventNum, endRepeat);
            this.userset.getCurrentUser().getCalendars().get(calNum-1).showEventsSummary(calNum);

        }else if(i.equals("vse")){
            this.userset.getCurrentUser().showSharedEvent();
        }else if(i.equals("vae")){
            this.userset.getCurrentUser().showAllMyEventsAndSharedFromAllCalendars();
        }else if(i.equals("sch")){
            System.out.println();
            System.out.println("What are you looking for?");
            String query = input.nextLine();
            this.userset.getCurrentUser().search(query);
        }else if(i.equals("sel")){
            System.out.println();
            System.out.println("Which Event did you want to look at in more detail?");
            this.userset.getCurrentUser().eventTitlesCombined();
            int eventNum = input.nextInt();
            input.nextLine();
            this.userset.getCurrentUser().getEventDetail(eventNum);
            System.out.println();
        }else if(i.equals("upd")){
            System.out.println();
            System.out.println("Which Event did you want to update?");
            this.userset.getCurrentUser().showMyEventsTitles();
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

                ZonedDateTime newStart = ZonedDateTime.of(startYear, startMonth, startDay, startHour, startMin, 0, 0, ZoneId.of(ZoneId.SHORT_IDS.get(this.userset.getCurrentUser().getConfigScreen().getTimeZone())));
                this.userset.getCurrentUser().setStartTime(eventNum,newStart);
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

                ZonedDateTime newEnd = ZonedDateTime.of(endYear, endMonth, endDay, endHour, endMin, 0, 0, ZoneId.of(ZoneId.SHORT_IDS.get(this.userset.getCurrentUser().getConfigScreen().getTimeZone())));
                this.userset.getCurrentUser().setEndTime(eventNum,newEnd);

            }else if(whatToUpdate==3){
                System.out.println("What is the title of the event?");
                String title = input.nextLine();
                System.out.println("What are the details of the event?");
                String detail = input.nextLine();
                System.out.println("What are tags for the event?");
                String tags = input.nextLine();

                this.userset.getCurrentUser().updateEvent(eventNum, title, detail,tags);
            }



        }
    }

}
