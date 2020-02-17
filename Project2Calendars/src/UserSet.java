import java.util.*;

public class UserSet {

    //Attributes
    private static UserSet userSetSingleton;

    private ArrayList<User> userSet = new ArrayList<>();
    private User currentUser;

    //Constructor

    private UserSet() {
    }

    public static UserSet getInstance(){
        if (userSetSingleton == null){
            userSetSingleton = new UserSet();
        }
        return userSetSingleton;
    }

    //Getter and Setters

    public ArrayList<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(ArrayList<User> userSet) {
        this.userSet = userSet;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String username) {
//        this.currentUser = currentUser;
        int len = this.userSet.size();

        for (int i = 0; i < len; i++){
            if(this.userSet.get(i).getUserName().equals(username)){
                this.currentUser = this.userSet.get(i);
            }
        }
    }


    //Custom Functions

    public void addUser(User user){
        this.userSet.add(user);
    }

    public void removeUser(User user){
        this.userSet.remove(user);
    }

    public boolean inUserSet(String username){
        int len = this.userSet.size();

        for (int i = 0; i < len; i++){
            if (this.userSet.get(i).getUserName().equals(username)){
                return true;
            }
        }
        return false;
    }

    public void printUsers(){
        int len = this.userSet.size();

        for (int i = 0; i < len; i++){
            System.out.println(this.userSet.get(i).getUserName());
            }
        }

    public void printUsersExceptCurrent(){
        int len = this.userSet.size();

        for (int i = 0; i < len; i++){
            if(!this.currentUser.getUserName().equals(this.userSet.get(i).getUserName())){
                System.out.println(this.userSet.get(i).getUserName());
            }

        }
    }

    public void showAllPublicCalendarsAndMyCalendars(){

        System.out.println();
        System.out.println("ALL PUBLIC CALENDARS INCLUDING MY CALENDARS");
        int len = userSet.size();
        int count = 1;
        for (int i = 0; i<len; i++){
            int calLen = this.userSet.get(i).getCalendars().size();

            for(int j = 0; j<calLen; j++){
                if (!this.userSet.get(i).getCalendars().get(j).getPrivateStatus() || this.currentUser.getUserName().equals(this.userSet.get(i).getCalendars().get(j).getOwner().getUserName()) ){
                    System.out.println("*************************************");
                    System.out.println("Calendar #: " + count);
                    this.userSet.get(i).getCalendars().get(j).show();
                    count++;
                }


            }
        }
    }

}
