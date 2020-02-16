public class MainMenu {

    public SettingsMenu settingsMenu;
    public CalendarMenu calendarMenu;
    public EventMenu eventMenu;
    public TimerMenu timerMenu;

    public MainMenu(UserSet userset){
        this.settingsMenu = new SettingsMenu(userset);
        this.calendarMenu = new CalendarMenu(userset);
        this.eventMenu = new EventMenu(userset);
        this.timerMenu = new TimerMenu(userset);
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
}
