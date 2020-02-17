public class UserMenuFacade {

    public static void showUserConfigSetting(UserSet userset){
        userset.getCurrentUser().getConfigScreen().show();
    }

    public static void changeCurrentUserTimezone(String newTz, UserSet userset){
        userset.getCurrentUser().getConfigScreen().setTimeZone(newTz);
    }

    public static void changeCurrentUserTheme(boolean theme, UserSet userset){
        userset.getCurrentUser().getConfigScreen().setDarkMode(theme);
    }

    public static int currentUserCalendarLen(UserSet userset){
        return userset.getCurrentUser().getCalendars().size();
    }

    public static void reflectCurrentUserTimezone(int lenCalendars, UserSet userset){
        for (int j = 0; j < lenCalendars; j++){
            userset.getCurrentUser().getCalendars().get(j).updateEventTimeZones(userset.getCurrentUser());
        }
    }

    public static void showCurrentUserFormattedCalendarLen(UserSet userset){
        System.out.println("Currently, " + userset.getCurrentUser().getUserName() + " has " + userset.getCurrentUser().getCalendars().size() + " calendar/s");
    }

    public static void removeCurrentUserCalendar(UserSet userset, int calendarNumber){
        userset.getCurrentUser().removeCalendar(calendarNumber);
    }

    public static void changeCurrentUserPrivateStatus(int ans, UserSet userset){
        userset.getCurrentUser().changePrivateStatus(ans);
    }

    public static void changeCurrentUserCalendarVisualization(int ans, int type, UserSet userset){
        userset.getCurrentUser().changeVisualization(ans, type);
    }

    public static void viewCurrentUserSpecificCalendar(UserSet userset, int ans){
        userset.getCurrentUser().filterEvents(ans);
    }

    public static int showCurrentUserEventsLen(UserSet userset, int calendarNum){
        return userset.getCurrentUser().getCalendars().get(calendarNum-1).getEvents().size();
    }

    public static void showCurrentUserEventsFromSpecificCalendar(UserSet userset, int calendarNum, int lenEvents){
        for (int j = 0; j < lenEvents; j++){
            userset.getCurrentUser().getCalendars().get(calendarNum-1).getEvents().get(j).show();
        }
    }

    public static void removeCurrentUserEvent(UserSet userset, int calendarNum, String title){
        userset.getCurrentUser().removeEvent(calendarNum, title);
    }
}
