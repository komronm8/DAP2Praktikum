import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class AufgabeB4A2 {
    
    //Partitions the Subarray with l being the leftside, r the rightside and p the pivot.
    //Once the pivot is set to p, the subarray will be sorted in a way so that the elements to
    //the right of the pivot are bigger and to the left are smaller than it
    //Returns the index where the pivot is at.
    public static int partition(int[] arr, int l, int r, int p) {
        
        int pivot = arr[p];
        swap(arr, p, r);

        int j = l;
        for (int i = l; i < r; i++) {

            if (arr[i] < pivot) {
                swap(arr, i, j);
                j++;
            }

        }
        swap(arr, j, r);

        return j;
    } 

    //Method that returns the k-th smallest element by partitioning the array untill the k-th element is found
    //This is done by just partitioning to the sub array where k is. The pivot here will be initialized 
    //by the first element
    public static int quickSelectFirst(int[] arr, int k) {
        
        int[] array = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            array[i] = arr[i];
        }

        int l = 0, r = array.length - 1;
        while (l <= r) {
            int p = partition(array, l, r, l);
            if (p == k - 1) {
                return array[p];
            } else if (p < k - 1) {
                l = p + 1;
            } else {
                r = p - 1;
            }
        }
        return -1;
    }

    //Method that returns the k-th smallest element by partitioning the array untill the k-th element is found
    //This is done by just partitioning to the sub array where k is. The pivot here will be initialized 
    //by a random element
    public static int quickSelectRandom(int[] arr, int k) {
        Random rand = new Random();

        int[] array = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            array[i] = arr[i];
        }

        int l = 0, r = array.length - 1;
        while (l <= r) {
            int p = partition(array, l, r, rand.nextInt(r - l + 1) + l);
            if (p == k - 1) {
                return array[p];
            } else if (p < k - 1) {
                l = p + 1;
            } else {
                r = p - 1;
            }
        }
        return -1;
    }

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

        if(args.length == 0 ) {
            System.out.println("Please enter an argument k");
            return;
        }
        
        int k = 0;

        try {
            k = Integer.parseInt(args[0]);
        }
        catch(NumberFormatException e) {
            System.out.println("Please enter an integer argument k");
            return;
        }

        if ( intArray == null || intArray.length < k) {
            return;
        }

        int result = 0;
        Instant start = Instant.now();
        for(int i = 0; i < 20; i++){
            result = AufgabeB4A1.heapSelect(intArray, k);
        }
        Instant finish = Instant.now();
        System.out.println("Elapsed time for heapSelect is " + Duration.between(start, finish).toMillis() + " milliseconds. Result: " + result);
        
        start = Instant.now();
        for(int i = 0; i < 20; i++){
            AufgabeB4A1.heapSelectFast(intArray, k);
        }
        finish = Instant.now();
        System.out.println("Elapsed time for heapSelectFast is " + Duration.between(start, finish).toMillis() + " milliseconds. Result: " + result);

        start = Instant.now();
        for(int i = 0; i < 20; i++){
            quickSelectFirst(intArray, k);
        }
        finish = Instant.now();
        System.out.println("Elapsed time for quickSelectFirst is " + Duration.between(start, finish).toMillis() + " milliseconds. Result: " + result);

        start = Instant.now();
        for(int i = 0; i < 20; i++){
            quickSelectRandom(intArray, k);
        }
        finish = Instant.now();
        System.out.println("Elapsed time for quickSelectRandom is " + Duration.between(start, finish).toMillis() + " milliseconds. Result: " + result);

    }

    //Helping method for swaping positions of two elements in the positions "first" and "second" in the array"arr"
    public static void swap(int[] arr, int first, int second ){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

}
