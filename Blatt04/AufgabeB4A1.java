
import java.util.ArrayList;
import java.util.Scanner;

public class AufgabeB4A1{

    //Method that returns the k-smallest element from the array arr.
    //This is done by sorting the array first with heapsort and extracting the max.
    public static int heapSelect(int[] arr, int k){
        MaxHeap maxHeap = new MaxHeap(arr.length);
        for(int i = 0; i < arr.length; i++){
            maxHeap.add(arr[i]);
        }

        int[] sortedArray = new int[maxHeap.getCapacity()];
    
        for(int i = sortedArray.length - 1; i >= 0; i--){
            sortedArray[i] = maxHeap.extractMax();
        }

        return sortedArray[k-1];
    }

    //Method for returning the k-smallest element from the array arr.
    //This is done by creating an heap with the size k. Afterwards the array is sorted
    //and for the remainding elements of the array arr the max will be swapped, so that
    //at the ened the heap will have only the k-th smallest elements in it
    public static int heapSelectFast(int[] arr, int k){
        
        MaxHeap maxHeap = new MaxHeap(k);
        for(int i = 0; i < k; i++){
            maxHeap.add(arr[i]);
        }

        for(int i = k; i < arr.length; i++){

            if(arr[i] < maxHeap.peekMax()){
                maxHeap.heap[0] = arr[i];
                maxHeap.maxHeapify(0);
            }

        }

        return maxHeap.heap[0];

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

        int result =  heapSelectFast(intArray, k);
        System.out.println(result);

    }

}
