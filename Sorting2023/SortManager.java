import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * Write a description of class SortManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SortManager
{
    private static int size;
    private static Scanner scan;
    private static Integer[] fancyNumbers;
    private static int[] numbers;
    private static long ops;
    
    public static void main (String[] args){
        String fileName = "25_source.txt";
        Timer timer = new Timer();
        size = 0;

        // start the stopwatch 
        timer.startTimer();

        // Load the data
        loadData (fileName);
        
        // run our sort
        int[] results = recursionSortOpCount(numbers.clone());

        // stop the timer
        timer.endTimer();

        // Check results, and output report to the screen
        checkResults(results, true);
        
        // Report on timer
        System.out.println("It took " + timer + " and " + ops + " operations.");

    }
    
    public static void UI(){
        scan = new Scanner (System.in);
        Timer timer = new Timer();
        boolean exit = false;
        String fileName;
        
        while(!exit){
            int choice = 0;
            System.out.println("1. Select a file");
            System.out.println("2. Check sort time");
            System.out.println("3. Check number of operations required for sort");
            System.out.println("4. Check both");
            System.out.println("5. Exit");
            try{
                choice = Integer.parseInt(scan.nextLine());
                scan.nextLine();
            } catch(NumberFormatException e){
                System.out.println("Please enter an integer");
            }
            if(choice == 0){
                System.out.println("Invalid choice");
            }
            else if(choice == 1){
                System.out.println("Enter the file Name");
                loadData(scan.nextLine());
            }
            else if(choice == 2){
                timer.startTimer();
                int[] results = recursionSortOpCount(numbers.clone());
                timer.endTimer();
                System.out.println("It took " + timer);
            }
            else if(choice == 5){
                exit = true;
            }
            System.out.println("here");
        }
    }
    
    private static void loadData (String fileName){
        ArrayList<String> lines = new ArrayList<String>();
        System.out.println("Attempting to read integers from: " + fileName);

        try{
            scan = new Scanner (new File (fileName));            
        } catch (FileNotFoundException e){
            System.out.println("File not found.");

        }
        while (scan.hasNext()){
            lines.add(scan.nextLine());
        }  

        // populate our array
        numbers = new int[lines.size()];
        for (int i = 0; i < numbers.length; i++){
            numbers[i] = Integer.parseInt(lines.get(i));
        }
    }
    
    /**
     * This is an example of how to turn a sort algorithm
     * into an "opCount" method - it uses a rough count
     * of overall operations to provide some interesting
     * information - it's non-scientific, but when you compare
     * the results you can get proportionate and interesting
     * results.
     */
    public static int[] bubbleSortOpCount (int[] num)
    {
        ops = 0;
        ops++; // int i = 0;
        for (int i = 0; i < num.length; i++) {
            ops++; // i < num.length comparison
            ops++; // int x = 1
            for (int x = 1; x < num.length - i; x++) {
                ops++; // x < num.length - i
                ops++; // if statmement
                if (num[x - 1] > num[x]) {
                    int temp = num[x - 1];
                    num[x - 1] = num[x];
                    num[x] = temp;
                    ops += 5;
                }
                ops++; // x++
            }
            
            if (i % 10000 == 0 && i > 0){
                System.out.println ((((double)i / (double)num.length)*100) + " percent complete."); 
            }
            ops++; // i++
        }

        return num;
    }   

    public static int[] bubbleSort (int[] num)
    {

        for (int i = 0; i < num.length; i++) {
            for (int x = 1; x < num.length - i; x++) {
                if (num[x - 1] > num[x]) {
                    int temp = num[x - 1];
                    num[x - 1] = num[x];
                    num[x] = temp;

                }
            }
            if (i % 10000 == 0 && i > 0){
                System.out.println ((((double)i / (double)num.length)*100) + " percent complete."); 
            }
        }

        return num;
    }   
    
    /**
     * My own sort method (a very slow bubble sort)
     */
    public static void recursionSort(int [] array){
        for(int i = 0; i < array.length-1; i++){
            int currNum = array[i];
            int nextNum = array[i+1];
            if(currNum > nextNum){
                array[i] = nextNum;
                array[i+1] = currNum;
                recursionSort(array);
            }
        }
    }
    
    public static int[] recursionSortOpCount(int [] array){
        ops = 0;
        ops++; // set i = 0;
        for(int i = 0; i < array.length-1; i++){
            ops++; // check i < array length
            int currNum = array[i];
            int nextNum = array[i+1];
            
            ops++; // check if currNum > next Num
            if(currNum > nextNum){
                array[i] = nextNum;
                array[i+1] = currNum;
                recursionSortOpCount(array);
            }
        }
        return array;
    }
        
    /**
     * Sort result checker.
     * 
     * This modular method will check any given array of integers and 
     * return true if the values are correctly sorted or false if there
     * are any errors. Setting the parameter boolean reporting to true
     * will also output the results to the screen.
     * 
     * @author Jordan Cohen
     * @version April 2014
     */
    public static boolean checkResults (int[] theArray, boolean report)
    {
        System.out.println("Checking Validity");
        boolean stillValid = true;
        int counter = 0;
        while (stillValid && counter < theArray.length - 1)
        {
            if (theArray[counter] > theArray[counter + 1])
            {
                stillValid = false;
            }
            counter++;
        }
        if (report)
        {
            if (stillValid)
            {
                System.out.println("Checked " + theArray.length + " values. Sort is valid");
            }
            else
            {
                System.out.println("Checked " + theArray.length + " values. Found error at " + counter);
            }
        }

        return stillValid;
    }

}
