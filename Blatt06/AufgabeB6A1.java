import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AufgabeB6A1 {
    
    public int[] data;

    //Constroctor method for this class
    public AufgabeB6A1(int[] data){
        this.data = data;
    }

    //Method that reads the input from the terminal(Standard Input)
    public static int[] readInput() throws NumberFormatException{
        Scanner input = new Scanner(System.in);
    
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        
        //Read numbers from standard input and insert them to ArrayList
        while(input.hasNextLine()){
            try{
                arrayList.add(Integer.parseInt(input.nextLine()));
            }    
            catch(NumberFormatException e){
                throw new NumberFormatException("Input list contains a non-number.");
            }
        }

        //Insert numbers from ArrayList to an Array of type int
        int[] intArray = new int[arrayList.size()];
        for(int i = 0; i < intArray.length; i++){
            intArray[i] = arrayList.get(i);
        }

        return intArray;
    }

    public static void main(String[] args){

        int[] data = null;

        //Trys to read the input by calling readInput()
        try{
            data = readInput();
        }
        catch(NumberFormatException e){
            System.err.println("Input list contains a non-number.");
            return;
        }

        if(data.length == 0){
            return;
        }

        AufgabeB6A1 aufgabeB6A1 = new AufgabeB6A1(data);

        System.out.println("Before sorting: " + Arrays.toString(data));

        // call the function with time measuring
        Instant start = Instant.now();
        aufgabeB6A1.lsdRadix();
        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();

        System.out.println("Sort after LSD: " + Arrays.toString(aufgabeB6A1.data));
        System.out.println("LSD took: " + time);

        // call the function with time measuring
        start = Instant.now();
        aufgabeB6A1.msdRadix(0, data.length-1, 3);
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();

        System.out.println("Sort after MSD: " + Arrays.toString(aufgabeB6A1.data));
        System.out.println("MSD took: " + time);

    }    

    public void sortByByte(int l, int r, int b){

        // initializing minimum, maximum and help-array
        int minimum = getMin(l, r, b);
        int maximum = getMax(l, r, b);

        // counting the frequency of numbers in an array
        int[] count = count(minimum, maximum, l, r, b);

        // initializing help-array
        int[] temp = new int[r - l + 1];

        // position is found based on frequency array
        for (int i = l; i <= r; i++) {
            int currentValue = getByte(data[i], b);
            int position = 0;
            for (int j = count.length - 1; j > currentValue; j--) {
                position += count[j];
            }
            // increasing position index
            while (temp[position] != 0) {
                position++;
            }
            temp[position] = data[i];
        }

        // filling output array in descending order
        for (int i = l; i <= r; i++) {
            data[i] = temp[i - l];
        }
    }  

    //Calls sortByByte starting fromm the least significant Byte until the most significant byte
    //This way the array will get sorted
    public void lsdRadix(){
        for(int i = 0; i < 4; i++) {
            sortByByte( 0, data.length - 1, i);
        }
    }

    //Recursive method that sorts the array starting from the most significant byte
    public void msdRadix(int l, int r, int b){
        //recursion exit
        if(r - l + 1 <= 3) {
            insertionSort(l, r);
            return;
        }
        
        //recursive call
        int[] C = sortByByteMSD(l, r, b);
        for(int i = 0; i < C.length - 1; i++) {
            if(C[i + 1] + 1 < C[i] && b > 0) {
                msdRadix(C[i + 1] + 1, C[i], b - 1);
            }
        }
    }

    //Helping method that 
    private void insertionSort(int l, int r){
        for (int i = l; i <= r; ++i) {
            int key = data[i];
            int j = i - 1;

            while (j >= l && data[j] < key) {
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = key;
        }
    }

    // counting the frequency of numbers in an array
    public int[] countDiff(int min, int max, int l, int r, int b) {
        // initializing of a new array
        int[] C = new int[max - min + 1];
        for (int i = l; i <= r; i++){
            int currentValue = getByte(data[i], b);
            C[currentValue - min]++;
        }
        return C;
    }

    private int[] sortByByteMSD(int l, int r, int b){

        // initializing minimum, maximum and help-array
        int minimum = getMin(l, r, b);
        int maximum = getMax(l, r, b);

        // counting the frequency of numbers in an array
        int[] C = countDiff(minimum, maximum, l, r, b);

        // initializing help-array
        int[] temp = new int[r - l + 1];

        //position is found based on frequency array
        for (int i = l; i <= r; i++) {
            int currentValue = getByte(data[i], b);
            int position = 0;
            for (int j = C.length - 1; j > currentValue - minimum; j--) {
                position += C[j];
            }
            while (temp[position] != 0) {
                position++;
            }
            temp[position] = data[i];
        }
        for (int i = l; i <= r; i++) {
            data[i] = temp[i - l];
        }

        System.out.println(Arrays.toString(data));

        //Find the intervals for sorting the array with the most significant digit
        int[] intervals = new int[257];
        for(int i = 256; i > maximum; i--){
            intervals[i] = l - 1;
        }
        // System.out.println(Arrays.toString(intervals));
        for(int i = maximum; i > minimum; i--){
                intervals[i] = intervals[i + 1] + C[i - minimum];
        }
        // System.out.println(Arrays.toString(intervals));
        for(int i = minimum; i >= 0; i--){
            intervals[i] = r;
        }
        // System.out.println(Arrays.toString(intervals));
        return intervals;
    }  

    //Method that counts the freqiencies of the elements in the array
    //This is done by finding the right index by using the minimum as a left barrier
    public int[] count(int min, int max, int l, int r, int b){
        // initializing of a new array
        int[] count = new int[256];
        for (int i = l; i <= r; i++){
            int currentValue = getByte(data[i], b);
            count[currentValue]++;
        }
        return count;

    }

    //extract minimum from array
    private int getMin(int l, int r, int b) {
        int min = getByte(data[l], b);
        for (int i = l; i <= r; i++) {
            int currentValue = getByte(data[i], b);
            if (min > currentValue) {
                min = currentValue;
            }
        }
        return min;
    }

    //extract maximum from array
    private int getMax(int l, int r, int b) {
        int max = getByte(data[l], b);
        for (int i = l; i <= r; i++) {
            int currentValue = getByte(data[i], b);
            if (max < currentValue) {
                max = currentValue;
            }
        }
        return max;
    }

    private int getByte(int a, int b){
        return (a >> (8 * b)) & 0xFF;
    }

}
