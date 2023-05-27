import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AufgabeB5A1 {
    
    public int[] data;

    //Constructor method for the class
    public AufgabeB5A1(int[] data){
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

    //Method that returns the smallest element in the array(Minimum)
    public int getMin(){
        int min = data[0];

        for(int i = 1; i < data.length; i++){
            if(data[i] < min){
                min = data[i];
            }
        }

        return min;
    }

    //Method that returns the largest element in the array(Maximum)
    public int getMax(){
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
    public int[] count(){
        int min = getMin();
        int max = getMax();

        int[] counts = new int[max - min + 1];
        for (int i = 0; i < data.length; i++) {
            counts[data[i] - min]++;
        }
        return counts;

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

        //Check whether the data was given or not
        if(data.length == 0){
            return;
        }

        AufgabeB5A1 aufgabeB5A1 = new AufgabeB5A1(data);

        System.out.println(Arrays.toString(aufgabeB5A1.count()));

    }

}
