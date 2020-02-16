import java.time.ZoneId;
import java.util.Scanner;

public class SettingsMenu extends Menu {

    SettingsMenu(UserSet userset){
        super(userset);
    }


    public void executeCommand(String input){
        Scanner userInput = new Scanner(System.in);
        if (isViewSettingsCommandChosen(input)){
            showCurrentUserConfigSettings();
        }else if(isChangeSettingsCommandChosen(input)){
            changeTimezoneAndTheme( userInput);
        }
    }

    public boolean isViewSettingsCommandChosen(String input){
        return input.equals("vs");
    }

    public boolean isChangeSettingsCommandChosen(String input){
        return input.equals("cs");
    }

    public void showCurrentUserConfigSettings(){
        this.userset.getCurrentUser().getConfigScreen().show();
    }

    public void changeTimezone(String newTz){
        this.userset.getCurrentUser().getConfigScreen().setTimeZone(newTz);
    }

    public void changeTimezoneMenu(Scanner input){
        System.out.println();
        showTimezones();
        System.out.println();
        changeTimezone(getNewStringSetting("Please choose the abbreviation (i.e. PST) that corresponds to the timezone that you want:", input));
        System.out.println();
    }

    public void showTimezones(){
        for (String key: ZoneId.SHORT_IDS.keySet()){
            System.out.println(key + " - " + ZoneId.SHORT_IDS.get(key));
        }
    }

    public void changeTheme(String newTheme){
        if(newTheme.equals("0")){
            this.userset.getCurrentUser().getConfigScreen().setDarkMode(true);
        }else{
            this.userset.getCurrentUser().getConfigScreen().setDarkMode(false);
        }
    }

    public void changeThemeMenu(Scanner input){
        changeTheme(getNewStringSetting("Choose theme number (0: Dark OR 1: Light)", input));
    }

    public void showCurrentUserConfigScreen(){
        this.userset.getCurrentUser().getConfigScreen().show();
    }

    public void changeCurrentUserEventsTimezone(){
        int lenCalendars = this.userset.getCurrentUser().getCalendars().size();
        for (int j = 0; j < lenCalendars; j++){
            this.userset.getCurrentUser().getCalendars().get(j).updateEventTimeZones(this.userset.getCurrentUser());
        }
    }

    public void changeTimezoneAndTheme(Scanner input){
        System.out.println("***************************");
        System.out.println("Change Settings");
        changeTimezoneMenu(input);
        changeThemeMenu(input);
        showCurrentUserConfigScreen();
        changeCurrentUserEventsTimezone();
    }

}
