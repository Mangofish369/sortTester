import java.util.Scanner;

/**
 * Write a description of class test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class test
{
    static Scanner scan = new Scanner(System.in);

    /**
     * Constructor for objects of class test
     */
    public test()   
    {

    }


    public static void collect()
    {
        System.out.println("Give me a number");
        int yourNum = scan.nextInt();
        
        //doNothing(yourNum);
        
        System.out.println("Give me a name");
        String yourName = scan.nextLine();
        
        System.out.println(yourNum);
        System.out.println(yourName);
    }
    public static void doNothing(int num){
        System.out.println("I did nothing");
    }
}
