import java.util.Scanner;

public class CalendarMenu extends Menu {

    CalendarMenu(UserSet userset){
        super(userset);
    }

    public void executeCommand(String i){
        Scanner input = new Scanner(System.in);

        if (isHowManyCalendarsCommandChosen(i))
            showHowManyCalendars();
        else if(isAddCalendarCommandChosen(i))  this.userset.getCurrentUser().addCalendar(this.userset.getCurrentUser());
        else if(isViewAllCalendarsCommandChosen(i)) {
            this.userset.showAllPublicCalendarsAndMyCalendars();
        } else if(isCalendarEmpty()){
            showNoCalendars();
        } else if(isDeleteCalendarCommandChosen(i)){
            deleteCalendar(input);
        }else if(isViewMyCalendarsCommandChosen(i)){
            showUserCalendars();
        } else if(isUpdateCalendarPrivacyCommandChosen(i)){
            changePrivacy(input);
        }else if(isUpdateCalendarVisualizationCommandChosen(i)){
            changeVisualization(input);
        }else if(isViewSpecificCalendarCommandChosen(i)){
            viewSpecificCalendar(input);
        }
    }

    public boolean isHowManyCalendarsCommandChosen(String input){
        return input.equals("hc");
    }
    public boolean isAddCalendarCommandChosen(String input){
        return input.equals("ac");
    }
    public boolean isDeleteCalendarCommandChosen(String input){
        return input.equals("dc");
    }
    public boolean isUpdateCalendarPrivacyCommandChosen(String input){
        return input.equals("ucp");
    }
    public boolean isUpdateCalendarVisualizationCommandChosen(String input){
        return input.equals("ucv");
    }
    public boolean isViewMyCalendarsCommandChosen(String input){
        return input.equals("vc");
    }
    public boolean isViewSpecificCalendarCommandChosen(String input){
        return input.equals("vsp");
    }
    public boolean isViewAllCalendarsCommandChosen(String input){
        return input.equals("vac");
    }

    public void showHowManyCalendars(){
        System.out.println("Currently, " + this.userset.getCurrentUser().getUserName() + " has " + this.userset.getCurrentUser().getCalendars().size() + " calendar/s");
    }

    public void showNoCalendars(){
        System.out.println("**************************************************");
        System.out.println("U HAVE NO CALENDARS!");
        System.out.println("Please create a calendar before doing any Calendar commands!");
        System.out.println("**************************************************");
    }

    public void deleteCalendar(Scanner input){
        showUserCalendars();
        int ans =  getNewIntSetting("What # Calendar would you like to remove?", input);
        this.userset.getCurrentUser().removeCalendar(ans);
    }

    public void changePrivacy(Scanner input){
        System.out.println();
        showUserCalendars();
        int ans =  getNewIntSetting("What # Calendar would you like to update its privacy?", input);
        System.out.println();
        this.userset.getCurrentUser().changePrivateStatus(ans);

        showUserCalendars();
        System.out.println("Successfully Updated!");
    }

    public void changeVisualization(Scanner input){
        System.out.println();
        showUserCalendars();

        int ans =  getNewIntSetting("What # Calendar would you like to update its visualization?", input);
        int type =  getNewIntSetting("What kind of visualization do you want it to be?\n 1:Yearly | 2:Monthly | 3:Weekly | 4:Daily", input);

        this.userset.getCurrentUser().changeVisualization(ans, type);

        showUserCalendars();
        System.out.println("Successfully Updated!");
    }

    public void viewSpecificCalendar(Scanner input){
        System.out.println();
        showUserCalendars();
        int ans =  getNewIntSetting("What # Calendar would you like to view?", input);
        this.userset.getCurrentUser().filterEvents(ans);
    }
}
