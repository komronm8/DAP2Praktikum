import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AufgabeB7A2 {
    public static void main(String[] args) {
        //Check if argument is given
        if (args.length < 1) {
            System.err.println("Rucksackkapazität fehlt.");
            return;
        }

        int capacity;
        //Get capacity from argument
        try {
            capacity = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Ungültige Rucksackkapazität: " + args[0]);
            return;
        }


        int[] values;
        int[] weights;

        //Get input list from Standard In
        //Only two input lists are allowed to be given
        try {
            Scanner scanner = new Scanner(System.in);
            values = getInput(scanner);
            weights = getInput(scanner);

            if(scanner.hasNextLine()){
                System.out.println("Input did not end after second list.");
                return;
            }
            
            if (values.length != weights.length) {
                System.err.println("The number of values does not match the number of weights.");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println("Die Eingabe enthält eine ungültige Ganzzahl.");
            return;
        }

        int[][] table = knapsack(values, weights, capacity);
        int optimalValue = table[values.length][capacity];
        System.out.println(optimalValue);
    }

    //Method that reads from the terminal(Standard input)
    public static int[] getInput(Scanner scanner) throws NumberFormatException {
        List<Integer> inputList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            int value = Integer.parseInt(line);
            inputList.add(value);
        }

        int[] inputArray = new int[inputList.size()];
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = inputList.get(i);
        }

        return inputArray;
    }

    //Returns a 2D table with the optimal values for diffrent items and capacities
    public static int[][] knapsack(int[] values, int[] weights, int capacity) {
        int n = values.length;
        int[][] table = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] <= j) {
                    table[i][j] = Math.max(values[i - 1] + table[i - 1][j - weights[i - 1]], table[i - 1][j]);
                } else {
                    table[i][j] = table[i - 1][j];
                }
            }
        }

        return table;
    }
}

