import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class AufgabeB3A1{
    
    public int subsetSize;

    //Constructor method that initializes the subsetSize
    public AufgabeB3A1(int subsetSize){
        this.subsetSize = subsetSize;
    }

    //Creates the subset for the given array 'data'
    public int[][] createSubsets(int[] data){
        if(data.length > 0 && data != null){
            int[][] subsets = new int[binomial(data.length, subsetSize)][subsetSize];
            int[] indices = new int[subsetSize];
            for (int i = 0; i < subsetSize; i++) {
                indices[i] = i;
            }

            //Iterate over all possible subsets
            for (int j = 0; j < subsets.length; j++) {
                for (int i = 0; i < subsetSize; i++) {
                    subsets[j][i] = data[indices[i]];
                }

                int k = subsetSize - 1;
                while (k >= 0 && indices[k] == data.length - subsetSize + k) {
                    k--;
                }
                if (k < 0) {
                    break;
                }
                indices[k]++;
                for (int i = k + 1; i < subsetSize; i++) {
                    indices[i] = indices[k] + i - k;
                }
            }

            return subsets;
        }
        else{
            return null;
        }
        
    }

    //Method for reading input from the terminal
    public static int[] readStandardIn() throws NumberFormatException{

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

    //Removes duplicates from the array data and returns it
    public int[] removeDuplicates(int[] data){
        ArrayList<Integer> uniqueArr = new ArrayList<Integer>();
        int[] array = data;
        if(array.length > 0){
            //Sort array first then add every element that has not been added
            Arrays.sort(array);
            int lastNum = array[0];
            uniqueArr.add(lastNum);

            for(int i = 1; i < array.length; i++){
                if(lastNum != array[i]){
                    uniqueArr.add(array[i]);
                    lastNum = array[i];
                }
            }

            int[] intArray = new int[uniqueArr.size()];
            for(int i = 0; i < intArray.length; i++){
                intArray[i] = uniqueArr.get(i);
            }
            return intArray;
        }
        else{
            return null;
        }
    }

    public static void main(String[] args){
        
        int[] data = null;

        try{
            data = readStandardIn();
        }
        catch(NumberFormatException e){
            System.err.println("Input list contains a non-number.");
            return;
        }
        

        //Check if only one argument is given  
        if(args.length != 1){
            System.out.println("Exactly one argument is required.");
            return;
        }
        
        if(data.length == 0){
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

        AufgabeB3A1 aufgabeB3A1 = new AufgabeB3A1(k);
        data = aufgabeB3A1.removeDuplicates(data);

        if(aufgabeB3A1.subsetSize > data.length){
            System.out.println("ERROR: k is too large.");
            return;
        }

        int[][] subsets = aufgabeB3A1.createSubsets(data);
        if(subsets == null){
            return;
        }
        for(int[] subset : subsets){
            System.out.println(Arrays.toString(subset));
        }
        System.out.println(subsets.length + " subsets in total");

    }

    //Helping-method for calculating binomial
    private static int binomial(int n, int k){
        int result = 1;
        for(int i = 0; i < k; i++){
            result = result * (n-i);
            result = result / (i+1);
        }
        return result;
    }

    
}
