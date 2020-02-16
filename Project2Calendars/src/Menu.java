import java.util.Scanner;

public abstract class Menu {

    protected UserSet userset;

    Menu(UserSet userset){
        this.userset = userset;
    }

    public static final void viewMainMenu(){
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

    public abstract void executeCommand(String input);

    public String getNewStringSetting(String prompt, Scanner input){
        System.out.println(prompt);
        return input.nextLine();
    }

    public static int getNewIntSetting(String prompt, Scanner input){
        System.out.println(prompt);
        int intInput = input.nextInt();
        input.nextLine();
        return intInput;
    }

    public boolean isCalendarEmpty(){
        return this.userset.getCurrentUser().getCalendars().size()==0;
    }

    public void showUserCalendars(){
        this.userset.getCurrentUser().showMyCalendars();
    }

}
