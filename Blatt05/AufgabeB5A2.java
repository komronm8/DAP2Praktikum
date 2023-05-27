import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AufgabeB5A2 {
    
    public int[] data;

    //Constroctor method for this class
    public AufgabeB5A2(int[] data){
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

    //Method that sorts the array using the frequency of numbers in the array
    public int[] countingSort(){

        // initialize minimum and maximum
        int minimum = getMin();
        int maximum = getMax();

        // count the frequency of numbers in the array
        int[] freq = count();

        // sort descending by using the frequency count of the array
        int j = 0;
        for (int i = maximum; i >= minimum; i--){
            while(freq[i - minimum] > 0){
                data[j] = i;
                j++;
                freq[i - minimum]--;
            }
            
        }
        return count();
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

        AufgabeB5A2 aufgabeB5A2 = new AufgabeB5A2(data);

        System.out.println("Before Sorting: " + Arrays.toString(data));
        System.out.println("Frequencies: " + Arrays.toString(aufgabeB5A2.count()));
        aufgabeB5A2.countingSort();
        System.out.println("After Sorting: " + Arrays.toString(aufgabeB5A2.data));

    }

    //Method that returns the smallest element in the array(Minimum)
    private int getMin(){
        int min = data[0];

        for(int i = 1; i < data.length; i++){
            if(data[i] < min){
                min = data[i];
            }
        }

        return min;
    }

    //Method that returns the largest element in the array(Maximum)
    private int getMax(){
        int max = data[0];

        for(int i = 1; i < data.length; i++){
            if(data[i] > max){
                max = data[i];
            }
        }

        return max;
    }

    //Method that counts the freqiencies of the elements in the array
    //This is done by finding the right index by using the minimum as a left barrier
    private int[] count(){
        int min = getMin();
        int max = getMax();

        int[] counts = new int[max - min + 1];
        for (int i = 0; i < data.length; i++) {
            counts[data[i] - min]++;
        }
        return counts;
    }

}
