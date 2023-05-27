import java.util.Scanner;
import java.util.ArrayList;

public class IntArrayFromStdin{
	
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
        
        //Print array for testing
        for(int i = 0; i < intArray.length; i++){
            System.out.println(intArray[i]);
        }
    }

}
