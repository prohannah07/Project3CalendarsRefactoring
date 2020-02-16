import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TimerMenu extends Menu {

    TimerMenu(UserSet userset){
        super(userset);
    }

    public void executeCommand(String i){
        Scanner input = new Scanner(System.in);

        if(this.userset.getCurrentUser().getCalendars().size()==0) {
            System.out.println("**************************************************");
            System.out.println("U HAVE NO CALENDARS!");
            System.out.println("Please create a calendar before doing any Timer commands!");
            System.out.println("**************************************************");
        }else if (i.equals("at")){
            this.userset.getCurrentUser().showMyCalendars();
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

            ZonedDateTime end = ZonedDateTime.of(endYear, endMonth, endDay, endHour, endMin, 0, 0, ZoneId.of(ZoneId.SHORT_IDS.get(this.userset.getCurrentUser().getConfigScreen().getTimeZone())));

            this.userset.getCurrentUser().getCalendars().get(calNum-1).getTimerScreen().getTimers().add( new Timer(end,title));
            int len = this.userset.getCurrentUser().getCalendars().get(calNum-1).getTimerScreen().getTimers().size();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, YYYY h:m a z");
            System.out.println("**********************************************");
            System.out.println(this.userset.getCurrentUser().getCalendars().get(calNum-1).getTimerScreen().getTimers().get(len-1).getTitle());
            System.out.println(dtf.format(this.userset.getCurrentUser().getCalendars().get(calNum-1).getTimerScreen().getTimers().get(len-1).getTime()));
            System.out.println("**********************************************");
            System.out.println("Succesfully Added Timer!");

        }else if(i.equals("vt")){

            this.userset.getCurrentUser().showMyCalendars();
            System.out.println("From which calendar # did you want to see your timers?");
            int calNum = input.nextInt();
            input.nextLine();

//            userset.getCurrentUser().getCalendars().get(calNum-1).getTimerScreen().show(calNum);
            this.userset.getCurrentUser().getCalendars().get(calNum-1).showTimers(calNum);
        }
    }

}
