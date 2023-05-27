
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AufgabeB3A2 {
    
    public int[] data;

    //Constructor method that initializes the data array
    public AufgabeB3A2(int[] data){
        this.data = data;
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

        int[] data = intArray;

        //Error handling
        if(data.length  == 0) {
            System.out.println("Please enter at least 1 int value");
            return;
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

        AufgabeB3A2 aufgabeB3A2 = new AufgabeB3A2(data);

        if(aufgabeB3A2.data == null){
            return;
        }

        int[] arr = new int[data.length];
        for(int i = 0; i < data.length; i++){
            arr[i] = data[i];
        }

        Arrays.sort(arr);
        System.out.println("Sorted input:\n" + Arrays.toString(arr));

        //For every argument passed from StandardIn the permutations will be shown
        for(int i = 0; i < args.length; i++){
            k = Integer.parseInt(args[i]);

            if(aufgabeB3A2.fact(k)>aufgabeB3A2.fact(arr.length)){
                return;
            }

            if(k < 1){
                return;
            }

            int[] result = aufgabeB3A2.choosePermutation(k);
            System.out.println("The " + k + "-smallest permutation is:\n" + Arrays.toString(result));
        }

    }

    //Method that returns the kSmallest permutation of the 'data' array
    public int[] choosePermutation(int kSmallest){

        if(fact(kSmallest) > fact(data.length)){
            return null;
        }

        if(kSmallest > 0 && data.length != 0){
            //Create another array to not change the data array
            int[] array = new int[data.length];
            for(int i = 0; i < data.length; i++){
                array[i] = data[i];
            }
            Arrays.sort(array);
            find(array, 0, kSmallest);
            return array;
        }
        else{
            return null;
        }
    }

    //Method that recursively determines the permutations of k of the given array 'arr', with 'd' being the index of the array
    public void find(int[] arr, int d, int k){
        
        int n = arr.length - d;
        if(n == 1){
            return;
        }

        int i = (k - 1) / fact(n - 1) + d;
        int k_new = k - (i - d)* fact(n - 1);
        //move all elements except for a[i] to the right
        //then move a[i] to its position
        int temp = arr[i];
        for(int j = i; j > d; j--) {
            arr[j] = arr[j - 1];
        }
        arr[d] = temp;
        find(arr, d + 1, k_new);
    }

    //Helping-method for calculating the factorial of n
    private int fact(int n){
        int factorial = 1;
        
        for(int i = 1; i <= n; i++) {
            factorial = factorial * i;
        }
        return factorial;
    }

}
