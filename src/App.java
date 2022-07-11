import java.io.IOException;
import java.util.Scanner;

public class App {


    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        ClockInSystem<Integer> number = new ClockInSystem<>(778899);

        System.out.println("\n");
        System.out.println("****************************************");
        System.out.println("**** Hello there! We're 3928 Market ****");
        System.out.println("****************************************");

        while (true) {
            System.out.println("\n---Enter your Employee number---");
            Integer yourNum = input.nextInt();

            if (yourNum.equals(number.getEmployeeNum()));
                break;
        } 
            System.out.println(
                "Wrong Employee Number\n\n" +
                        "Try again or Press [C] to Exit\n\n");
        


       if (input.nextLine().equals("C")){
            System.out.println("---Have A Good Shift!---\n");
            System.exit(0);
        }

        clrscr();
        ClockInSystem.showMenu();
        int num1 = input.nextInt();

        switch (num1) {
            case 1:
                number.clockIn();
                break;
            case 2:
                number.clockOut();
            break;

            case 3:
               System.out.println("Your Total Hours is" ); //totalHours);

            
            break;
             
            case 4:
                System.out.println("Your Last Clock In was --" + number.clockIn);
                System.out.println("Your last Clock Out was --" + number.clockOut);
            break;

            case 5:
            System.out.println("---Have A Good Shift!---\n");
            break;

        
            default:
                break;
        }
    

        
        input.close();
    }


    public static void clrscr(){
    
        try{
            if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            else
            Runtime.getRuntime().exec("clear");
            System.out.println("\033\143");
        }catch(IOException | InterruptedException ex){}
    }
}


