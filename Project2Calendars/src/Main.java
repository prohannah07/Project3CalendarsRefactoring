import javax.sound.midi.Soundbank;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        UserSet allUsers = new UserSet();
        MainMenu mainMenu = new MainMenu(allUsers);
        boolean isNotQuit = true;

        showWelcome();

        Scanner input = new Scanner(System.in);
        login(allUsers);

        while(isNotQuit){
            try{
                Menu.viewMainMenu();
                System.out.print("Type Command: ");
                String i = input.nextLine();

                if (isSettingsCommandChosen(i)){
//                    settingsMainMenu(i, allUsers);
                    mainMenu.settingsMenu.executeCommand(i);
                }else if (isCalendarCommandChosen(i)) {
//                    calendarMainMenu(i, allUsers);
                    mainMenu.calendarMenu.executeCommand(i);
                } else if(isEventCommandChosen(i)) {
//                    eventsMainMenu(i, allUsers);
                    mainMenu.eventMenu.executeCommand(i);
                }else if (isTimerCommandChosen(i)){
//                    timersMenu(i, allUsers);
                    mainMenu.timerMenu.executeCommand(i);
                }else if(isChangeUserCommandChosen(i)){
                    login(allUsers);
                }else if (isQuitCommandChosen(i)){
                    isNotQuit = false;
                }else{
                    invalidCommand();
                }
            }catch (Exception e){
                exceptionMessage();
            }

        }




    }

    public static void showWelcome(){
        System.out.println("*********************************");
        System.out.println("* WELCOME TO THE CALENDARS APP! *");
        System.out.println("*********************************");
    }

    public static void invalidCommand(){
        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+ That is not a valid command. Try Again +");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
    }

    public static void exceptionMessage(){
        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+ Something went wrong, please re-select a command +");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
    }

    public static boolean isSettingsCommandChosen(String input){
        return input.equals("vs") || input.equals("cs");
    }

    public static boolean isCalendarCommandChosen(String input){
        return input.equals("hc") || input.equals("ac") || input.equals("dc") ||
                input.equals("ucp") || input.equals("ucv") || input.equals("vsc") ||
                input.equals("vc") || input.equals("vac") || input.equals("vsp");
    }

    public static boolean isEventCommandChosen(String input){
        return input.equals("ae") || input.equals("re") || input.equals("se") ||
                input.equals("rp") || input.equals("vse") || input.equals("vae") ||
                input.equals("sch") || input.equals("sel") || input.equals("upd");
    }

    public static boolean isTimerCommandChosen(String input){
        return input.equals("at") || input.equals("vt");
    }

    public static boolean isChangeUserCommandChosen(String input){
        return input.equals("cu");
    }

    public static boolean isQuitCommandChosen(String input){
        return input.equals("q");
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
    

}
