package medicalStuff;
import java.util.Scanner;
import java.util.Locale;

public class Input {

    /**
     * Custom Scanner Functions
     */

    public String readString(){

        Scanner sc=new Scanner(System.in);
        String s;
        s=sc.nextLine();
        return s;
    }

    public int readInt(){

        Scanner sc=new Scanner(System.in);
        sc.useDelimiter("[\r\n]");
        int i;
        while (true){
            if(sc.hasNextInt()){
                i=sc.nextInt();
                break;
            }
            else{
                System.out.print("Enter an integer number please: ");
                sc.next();
            }
        }
        return i;
    }

    public double readDouble(){

        Scanner sc=new Scanner(System.in);
        sc.useDelimiter("[\r\n]");
        sc.useLocale(Locale.US);
        double d;
        while (true){
            if(sc.hasNextDouble()){
                d=sc.nextDouble();
                break;
            }
            else{
                System.out.print("Enter a double number please: ");
                sc.next();
            }
        }
        return d;
    }

    public long readLong(){

        Scanner sc=new Scanner(System.in);
        sc.useDelimiter("[\r\n]");
        long l;
        while (true){
            if(sc.hasNextLong()){
                l=sc.nextLong();
                break;
            }
            else{
                System.out.print("Enter a long number please: ");
                sc.next();
            }
        }
        return l;
    }
}
