import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Mergesort{
    
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
        if(intArray.length < 20){
            System.out.println(Arrays.toString(intArray));
            mSort(intArray);
            System.out.println(Arrays.toString(intArray));
        }
        else{
            mSort(intArray);
        }

        //Check if array length is not equal to zero
        if(intArray.length == 0){
            System.out.println("Min: " + 0  + ", Med: " + 0.0 + ", Max: " + 0);
        }
        else{
            int arrLen = intArray.length;
            double median = 0;
            
            //Check if array length is even for finding the median
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

    //Merges two sorted subarrays(left and right) into a single sorted array
    public static int[] merge(int[] data, int l, int r, int m){
        
        int arrSize1 = m - l + 1;
        int arrSize2 = r - m;
        
        int[] leftArr = new int[arrSize1];
        int[] rightArr = new int[arrSize2];
    
        //Add the elements from the array to the subArrays
        for(int i = 0; i < arrSize1; i++){
            leftArr[i] = data[l + i];
        }

        for(int j = 0; j < arrSize2; j++){
            rightArr[j] = data[m + 1 + j];
        }

        //Compare the first element of each subarray and add the bigger one to the original array "data"
        //and increases the pointer of that subArray 
        int i = 0, j = 0, k = l;
        while(i < arrSize1 && j < arrSize2){
            if(leftArr[i] >= rightArr[j]){
                data[k] = leftArr[i];
                i++;
            }
            else{
                data[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        //The rest of the remainding elements will be added to the original array
        while (i < arrSize1) {
            data[k] = leftArr[i];
            i++;
            k++;
        }

        while(j < arrSize2){
            data[k] = rightArr[j];
            j++;
            k++;
        }

        return data;

    }

    //Recursive function that divides the array into two halves and calls
    //itself until l(left) is no longer less than r(right)
    public static int[] mSort(int[] data, int l, int r){
        
        if( l < r ){
            int mid = l + (r - l) / 2;
            
            mSort(data, l, mid);
            mSort(data, mid + 1, r);
            
            //Call merge function to connect the arrays back together
            merge(data, l, r, mid);
        }

        return data;

    }
    
    //Sorts the whole array by calling the recursive method mSort with the attributes for
    //the left boundry as the start of the array and the right boundry as the end
    public static int[] mSort(int[] data){
        return mSort(data, 0, data.length - 1);
    }

}
