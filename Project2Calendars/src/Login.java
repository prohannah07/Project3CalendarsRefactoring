import java.util.Scanner;

public class Login {
    private UserSet userSet;

    Login(UserSet userSet){
        this.userSet = userSet;
    }

    public static void showWelcome(){
        System.out.println("*********************************");
        System.out.println("* WELCOME TO THE CALENDARS APP! *");
        System.out.println("*********************************");
    }

    public void login(){
        if (this.userSet.getUserSet().isEmpty()){
            emptyUserSet();
        }else{
            System.out.println("Current Users:");
            this.userSet.printUsers();
            System.out.println();
            notEmptyUserSet();
        }
    }

    public void notEmptyUserSet(){
        Scanner input = new Scanner(System.in);

        System.out.println("Do you have an existing account? (y or n):");
        String answer = input.nextLine();

        if (answer.equals("y")){
            System.out.println("What is your Username?:");
            String uname = input.nextLine();

            if (this.userSet.inUserSet(uname)){
                yesInputValidUsername(uname);
            }else{
                yesInputNoUserName();
            }
        }else if(answer.equals("n")){
            System.out.println("Please enter a username:");
            String uname = input.nextLine();

            if(this.userSet.inUserSet(uname)){
                noInputInvalidUsername();
            }else{
                noInputValidUsername(uname);
            }
        }

    }

    public void emptyUserSet(){

        System.out.println("Your are the first user!");
        System.out.println("Please enter a username:");
        Scanner input = new Scanner(System.in);
        String uname = input.nextLine();
        this.userSet.addUser(new User(uname));
        System.out.println("Hello " + uname + "!");
        this.userSet.setCurrentUser(uname);
    }

    public void yesInputValidUsername(String username){
        System.out.println("Hello " + username + "!");
        this.userSet.setCurrentUser(username);
//        System.out.println(userSet.getCurrentUser().getUserName());
    }

    public void yesInputNoUserName(){

        System.out.println("That account doesn't exist.");
        System.out.println("Try again.");
        System.out.println();
        System.out.println("Please enter a username:");

        boolean notCeatedValidUsername = true;
        Scanner input = new Scanner(System.in);
        String uname;

        while (notCeatedValidUsername){

            uname = input.nextLine();

            if (this.userSet.inUserSet(uname)){
                System.out.println("That is a valid username!");
                this.userSet.addUser(new User(uname));
                System.out.println("Hello " + uname + "!");
                this.userSet.setCurrentUser(uname);
                notCeatedValidUsername = false;
            }else{
                System.out.println("That account doesn't exist.");
                System.out.println("Try again:");
                System.out.println();
                System.out.println("Please enter a username:");
            }

        }

    }

    public void noInputInvalidUsername(){

        boolean notCreatedValidUsername = true;
        Scanner input = new Scanner(System.in);
        String uname;

        while(notCreatedValidUsername){
            System.out.println("Sorry that username is taken.");
            System.out.println("Please choose a different one:");
            uname = input.nextLine();
            if(!this.userSet.inUserSet(uname)){
                System.out.println("That is a valid username!");
                this.userSet.addUser(new User(uname));
                System.out.println("Hello " + uname + "!");
                this.userSet.setCurrentUser(uname);
                notCreatedValidUsername = false;
            }
        }

    }

    public void noInputValidUsername(String username){
        System.out.println("That is a valid username!");
        this.userSet.addUser(new User(username));
        System.out.println("Hello " + username + "!");
        this.userSet.setCurrentUser(username);
    }

}
