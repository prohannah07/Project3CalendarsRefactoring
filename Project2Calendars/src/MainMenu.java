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
}
