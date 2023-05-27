import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class KurzaufgabeB1A2{
    
    public static void main(String[] args){
        
        Scanner input = new Scanner(System.in);
        
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
		
        //Read numbers from standard input
		while(input.hasNextInt()){
            //If input is number add them to ArrayList
			try{
				arrayList.add(input.nextInt());
			}
            //Else throw error if input is non-number
			catch(NumberFormatException e){
				System.err.println("Input list contains a non-number.");
				return;
			}
		}

        //Insert numbers from ArrayList to an array of type int[]
        int[] intArray = new int[arrayList.size()];  
        for(int i = 0; i < arrayList.size(); i++){
            intArray[i] = arrayList.get(i);
        }

        printPermutations(intArray, 0);

    }

    //Recursive algorithm for creating the permutations
    public static int printPermutations(int[] data, int d){
        //If the sum of set positions in the array(d) is equal to the length of the array,
        //the finished permutation gets printed out
        if( d == data.length - 1 ){
            System.out.println(Arrays.toString(data) + " " + 1 );
            return 1;
        }
        else{

            int count = 0;

            //Otherwise for every set positions in the array(d), the permutations for the remainding elements
            //will be generated recursively
            for(int i = d; i < data.length; i++){
                
                //Swap the current element with the element at index i
                int tempNum = data[d];
                data[d] = data[i];
                data[i] = tempNum;

                //Generate every permutation for the remainding elements recursively
                count += printPermutations(data, d + 1);

                //Swap the elements back to their original positions
                tempNum = data[d];
                data[d] = data[i];
                data[i] = tempNum;

            }
            return count;
        }
    }
}
