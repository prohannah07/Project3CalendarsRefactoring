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
        UserMenuFacade.showUserConfigSetting(this.userset);
    }

    public void changeTimezone(String newTz){
       UserMenuFacade.changeCurrentUserTimezone(newTz, this.userset);
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
            UserMenuFacade.changeCurrentUserTheme(true, userset);
        }else{
            UserMenuFacade.changeCurrentUserTheme(false, userset);
        }
    }

    public void changeThemeMenu(Scanner input){
        changeTheme(getNewStringSetting("Choose theme number (0: Dark OR 1: Light)", input));
    }

//    public void showCurrentUserConfigScreen(){
//        UserMenuFacade.showUserConfigSetting(this.userset);
//    }

    public void changeCurrentUserEventsTimezone(){
        int lenCalendars = UserMenuFacade.currentUserCalendarLen(this.userset);
        UserMenuFacade.reflectCurrentUserTimezone(lenCalendars, this.userset);
    }

    public void changeTimezoneAndTheme(Scanner input){
        System.out.println("***************************");
        System.out.println("Change Settings");
        changeTimezoneMenu(input);
        changeThemeMenu(input);
        showCurrentUserConfigSettings();
        changeCurrentUserEventsTimezone();
    }

}
