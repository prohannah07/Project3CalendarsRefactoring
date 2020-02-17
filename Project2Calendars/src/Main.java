import javax.sound.midi.Soundbank;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        UserSet allUsers = new UserSet();
        MainMenu mainMenu = new MainMenu(allUsers);
        Login login = new Login(allUsers);
        boolean isNotQuit = true;

        Login.showWelcome();

        Scanner input = new Scanner(System.in);
        login.login();

        while(isNotQuit){
            try{
                Menu.viewMainMenu();
                System.out.print("Type Command: ");
                String i = input.nextLine();

                if (MainMenu.isSettingsCommandChosen(i)){
                    mainMenu.settingsMenu.executeCommand(i);
                }else if (MainMenu.isCalendarCommandChosen(i)) {
                    mainMenu.calendarMenu.executeCommand(i);
                } else if(MainMenu.isEventCommandChosen(i)) {
                    mainMenu.eventMenu.executeCommand(i);
                }else if (MainMenu.isTimerCommandChosen(i)){
                    mainMenu.timerMenu.executeCommand(i);
                }else if(MainMenu.isChangeUserCommandChosen(i)){
                    login.login();
                }else if (MainMenu.isQuitCommandChosen(i)){
                    isNotQuit = false;
                }else{
                    MainMenu.invalidCommand();
                }
            }catch (Exception e){
                MainMenu.exceptionMessage();
            }

        }

    }
}
