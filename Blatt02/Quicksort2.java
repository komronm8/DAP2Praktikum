import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.time.Duration;
import java.time.Instant;

public class Quicksort2{

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
    
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        
        //Read numbers from standard input and insert them to ArrayList
        while(input.hasNextLine()){
            try{
                arrayList.add(Integer.parseInt(input.nextLine()));
            }
            catch(NumberFormatException e){
                System.err.println("Input list contains a non-number.");
                return;
            }
        }

        //Insert numbers from ArrayList to an Array of type int
        int[] intArray = new int[arrayList.size()];
        for(int i = 0; i < intArray.length; i++){
            intArray[i] = arrayList.get(i);
        }
 
        //If amount of numbers are less than 20, the QuickSort results with min, med and max will be shown
        //otherwise only the min, med and max will be shown
        if(intArray.length <= 20){
            System.out.println(Arrays.toString(intArray));
            qSort(intArray);
            System.out.println(Arrays.toString(intArray));
        }
        else{
            Instant start = Instant.now();
            qSort(intArray);
            Instant finish = Instant.now();
            long time = Duration.between(start, finish).toMillis();
            System.out.println("Time: " + time);
        }

        if(intArray.length == 0){
            System.out.println("Min: " + 0  + ", Med: " + 0.0 + ", Max: " + 0);
        }
        else{
            int arrLen = intArray.length;
            double median = 0;

            if(intArray.length%2 == 0){
                int i = arrLen/2;
                median = (intArray[i-1] + intArray[i])/2.0;
            }
            else{
                median = intArray[(arrLen/2)];
            }

            System.out.println("Min: " + intArray[arrLen - 1] + ", Med: " + median + ", Max: " + intArray[0]);
        }
    }

    public static int[] partition(int[] data, int l, int r){
    
        int greater = 0;
        int smaller = 0;

        //Initialize the two pivots
        int p1 = Math.max(data[l], data[r]);
        int p2 = Math.min(data[l], data[r]);
        data[l] = p1;
        data[r] = p2;

        //Sorts the elements that are bigger than the left pivot to left of it
        for(int i = l + 1; i <= r; i++){
            int current = data[i];

            if (current >= p1) {
                //frees one cell for another value
                //moves pivot-element to the next cell
                //fill the freed cell with new value
                data[i] = data[l + greater + 1];
                data[l + greater + 1] = p1;
                data[l + greater] = current;
                greater++;
            }
        }

        //Sorts the elements that are smaller than the right pivot to the right of it
        for (int i = r - 1; i > l + greater; i--) {
            int current = data[i];
            if (current <= p2) {
                data[i] = data[r - smaller - 1];
                data[r - smaller - 1] = p2;
                data[r - smaller] = current;
                smaller++;
            }
        }

        int[] result = {l + greater, r - smaller};
        return result;
    }

    //Divides the array into three parts and calls itself for every divided part
    public static int[] qSort(int[] data, int l, int r){
        
        //If the left boundry is smaller than the right one, that means the subarray is still not sorted
        if( l < r ) {
            int[] m = partition(data, l, r);
            int m1 = m [0];
            int m2 = m[1];
            qSort(data, l, m1 - 1);
            qSort(data, m1 + 1, m2 - 1);
            qSort(data, m2 + 1, r);
        }

        return data;
        
    }

    //Sorts the array by calling the recursive method qSort with the arguments 0 for the left boundry
    //and the length of the array for the right boundry
    public static int[] qSort(int[] data){
        
        return qSort(data, 0, data.length - 1);
        
    }
}
