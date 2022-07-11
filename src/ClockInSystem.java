import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;



public class ClockInSystem<S1> {
    
    private int previousInAndOut;
    private final S1 employeeNum;
    static Instant start = null;
    static Instant end = null;
    static Instant breakStart = null;
    static Instant breakEnd = null;
    public String clockIn;
    public String clockOut;

    
    public ClockInSystem(S1 employeeNum){
        this.employeeNum = employeeNum;
    }
    
    public S1 getEmployeeNum() {
        return employeeNum;
    }
    public int getPreviousInAndOut() {
        return previousInAndOut;
    }
    
    /**
     * /menu

     * idNumber
     * showMenu
     * clockInTime/clockOutTime
     * breakStart/end
     * totalHours
     * previousClockInAndOutTime
     * exit
     **/
    
    static void clockIn(){
        start = Instant.now();
        System.out.println("\nYou clocked In at: " + start);
        System.out.println("       \n         --- Have A Good Shift! ---");
        System.out.println("\n");
    }

    
    static void clockOut(){
        end = Instant.now();
        System.out.println("\nYou're clocked Out at "+ end);
        System.out.println("\n         --- Done for the day! Bye! ---");
        totalHours(start, end);
    }

    static void breakStart(){
        breakStart = Instant.now();
        System.out.println("\n Enjoy your Break!");
        System.out.println("\n");
    }

    static void breakEnd(){
        breakEnd = Instant.now();
        System.out.println("\nHave a good shift!");
        System.out.println("\n");
    }

    static String totalHours(Instant start, Instant end){

        if(breakStart != null && breakEnd != null && end != null && start != null){
            Duration timeElapsed = Duration.between(start, end);
            long orjTime = timeElapsed.toMillis();
            
            orjTime = orjTime - totalBreakHours(breakStart, breakEnd);

            String totalBreakHoursConverted = String.format("%02dhrs: %02dmins: %02dseconds\n", 
            TimeUnit.MILLISECONDS.toHours(totalBreakHours(breakStart, breakEnd)),
            TimeUnit.MILLISECONDS.toMinutes(totalBreakHours(breakStart, breakEnd)) %
            TimeUnit.HOURS.toMinutes(1), 
            TimeUnit.MILLISECONDS.toSeconds(totalBreakHours(breakStart, breakEnd)) % 
            TimeUnit.MINUTES.toSeconds(1));

            String totalHoursConverted = String.format("%02dhrs: %02dmins: %02dseconds\n", 
            TimeUnit.MILLISECONDS.toHours(orjTime),
            TimeUnit.MILLISECONDS.toMinutes(orjTime) %
            TimeUnit.HOURS.toMinutes(1), 
            TimeUnit.MILLISECONDS.toSeconds(orjTime) % 
            TimeUnit.MINUTES.toSeconds(1));

            System.out.println("\nYour Total Work Hours Today: "+ totalHoursConverted);
            System.out.println("------------------------------------------------------");
            System.out.println("Your Total Break Hours Today: "+ totalBreakHoursConverted);

            return totalHoursConverted;
        }else{
            return "You cannot see total hours before clock out!\n";
        }
    }

    static long totalBreakHours(Instant breakStart, Instant breakEnd){
        Duration timeElapsed = Duration.between(breakStart, breakEnd);
         //System.out.println("\nYour Total Break Time Today: "+ timeElapsed.toMillis() +" Milli Seconds");
         //System.out.println("-------------------------------------------------");

        return timeElapsed.toMillis();
    }

    static void prevHours(Instant start, Instant end){
        Date startDate = new Date(start.toEpochMilli());
        Date endDate = new Date(end.toEpochMilli());

        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        String startDateFormatted = format.format(startDate);
        String endDateFormatted = format.format(endDate);
        // format.setTimeZone(TimeZone.g);
        System.out.println("\nYour last clock in was: "+ startDateFormatted );
        System.out.println("Your last clock out was: "+ endDateFormatted+"\n");

    }

    private static String Date(long epochMilli) {
        return null;
    }

    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu(){
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        int employeeNum = 0;
        boolean isEnter = false;
        while(true){
            if(!isEnter){
                System.out.println("================================================");
                System.out.println("      * Hello! We're 3928 Market *");
                System.out.println("   \n   === Enter your Employee number ===\n");
                System.out.println("================================================");


                try {
                    employeeNum = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\n - Please Enter Valid Employee Number :) -");
                }
                isEnter = true;
                sc.nextLine();
            }

            System.out.println("================================================");
            System.out.println("   * Hello There! We're 3928 Market *");
            System.out.println("   * Employee " + employeeNum + " *");
            System.out.println("================================================");
            System.out.println(" 1 : Clock In");
            System.out.println(" 2 : Clock Out"); 
            System.out.println(" 3 : Break Time Start");
            System.out.println(" 4 : Break Time Finish");
            System.out.println(" 5 : Total Hours");
            System.out.println(" 6 : Preview Clock In / Out Times");
            System.out.println(" 7 : Exit");
            System.out.println("================================================");
            

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\n - Please Enter Valid Employee Number! -");
            }
            
            sc.nextLine();
            switch (choice) {
                case 1:
                    clockIn();
                    break;
                case 2:
                    clockOut();
                    break;
                case 3:
                    breakStart();
                    break;
                case 4:
                    breakEnd();
                    break;
                case 5:
                    totalHours(start, end);
                    break;
                case 6:
                    prevHours(start, end);
                    break;
                case 7:
                    System.out.println(" \n* You're Done! Have a great day! * \n");
                    sc.close();
                    System.exit(1);
                break;
                    
                default:
                    break;
            }
        }
     }
    }


     
    