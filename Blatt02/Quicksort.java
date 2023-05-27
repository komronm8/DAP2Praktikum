import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.time.Duration;
import java.time.Instant;

public class Quicksort {
    
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
        
        //Check if the length of array is not 0
        if(intArray.length == 0){
            System.out.println("Min: " + 0  + ", Med: " + 0.0 + ", Max: " + 0);
        }
        else{

            int arrLen = intArray.length;
            double median = 0;
            //Check if the array length is even for the median
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

    //Partitions the Subarray with l being the leftside and r the rightside.
    //Once the pivot is set to l, the subarray will be sorted in a way so that the elements to
    //the right of the pivot are bigger and to the left are smaller than it
    public static int partition(int[] data, int l, int r){
        
        int pivot = data[l];
        int i = l;

        for(int k = l; k <= r; k++){
            if(data[k] > pivot){
                i++;
                swap(data, i, k);
            }
        }
        
        swap(data, i, l);

        return i;
    }   

    //Helping method for swaping positions of two elements in the positions "first" and "second" in the array"data"
    public static void swap(int[] data, int first, int second ){
        int temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    //Method that recursivly sorts an array with l being the left end of the array and r being the right end
    public static int[] qSort(int[] data, int l, int r){
        
        //If the left boundry is smaller than the right one, that means the subarray is still not sorted
        if( l < r ) {
            int p = partition(data, l, r);
            qSort(data, l, p-1);
            qSort(data,p+1 , r);
        }

        return data;
        
    }

    //This method starts the whole Quicksort algorithm, initializing the left boundry
    //with the first element of the array and the right boundry with the last element
    public static int[] qSort(int[] data){
        return qSort(data, 0, data.length - 1);
    }

    //This method checks if the array has been succesfully sorted or not
    public static boolean isSorted(int[] data){
        for (int i = 1; i < data.length; i++) {
            
            if ( ( data[i] > data[i-1] ) ) {
                return false;
            }
        }
            System.out.println("Array is correctly sorted");
        return true;
    }




}
