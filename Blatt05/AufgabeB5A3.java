import java.util.ArrayList;
import java.util.Scanner;

public class AufgabeB5A3 {
    
    public int[] data;

    //Constroctor method for this class
    public AufgabeB5A3(int[] data){
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

    //Method that returns the k-th smallest element by creating an boolean array 
    //Using the boolean array we can return the k-th smallest element
    public int exactSelect(int k){

        //find the range of the input values
        int minVal = getMin();
        int maxVal = getMax();

        //initialize the counting array
        boolean[] present = new boolean[maxVal - minVal + 1];
        
        //fill the counting array with the presence information
        for(int i = 0; i < data.length; i++) {
            present[data[i] - minVal] = true;
        }
        
        //find the k-th smallest value
        int count = 0;
        for(int i = 0; i < present.length; i++) {
            if(present[i]) {
                count++;
                if(count == k) {
                    return i + minVal;
                }
            }
        }
        return -1;
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

        //Check if only one argument is given  
        if(args.length != 1){
            System.out.println("Exactly one argument is required.");
            return;
        }

        int k = -1;
        try{
            k = Integer.parseInt(args[0]);
        }
        catch(NumberFormatException e){
            System.err.println("Input list contains a non-number.");
                return;
        }
        if( k < 0 ){
            return;
        }

        AufgabeB5A3 aufgabeB5A3 = new AufgabeB5A3(data);

        if(aufgabeB5A3.exactSelect(k) == -1){
            return;
        }

        System.out.println(aufgabeB5A3.exactSelect(k));
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

}
