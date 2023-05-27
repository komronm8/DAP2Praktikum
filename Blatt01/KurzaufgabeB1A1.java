import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class KurzaufgabeB1A1{
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

        //Insert numbers from ArrayList to an array of type int[]
        int[] intArray = new int[arrayList.size()];
        for(int i = 0; i < intArray.length; i++){
            intArray[i] = arrayList.get(i);
        }

        //Check if given number list contains at least 10 numbers, otherwise just sort the array
        if(intArray.length < 10){
            System.out.println("The list must contain at least k=10 numbers, but only " + intArray.length + " were provided.");
            return;
        }
        else{
            Arrays.sort(intArray);
        }

        //Check if only one argument is given  
        if(args.length != 1){
            System.out.println("Exactly one argument is required.");
            return;
        }

        try{
            int k = Integer.parseInt(args[0]);
            if( k > intArray.length ){
                System.out.println("The given argument exceeds the length of the given list.");
                return;
            }
            else if(k < 1){
                System.out.println("The given argument is smaller than 1.");
                return;
            }
            else{
               System.out.println("The " + k + "-smallest value is " + intArray[k-1]);
            }
        }
        catch(NumberFormatException e){
            System.err.println("Input argument is a non-number.");
            return;
        }

    }
}
