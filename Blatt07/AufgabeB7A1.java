package Blatt07;

public class AufgabeB7A1{

    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a number as an argument.");
            return;
        }

        try {
            int n = Integer.parseInt(args[0]);
            if (n < 0) {
                System.out.println("Please provide a non-negative number.");
                return;
            }

            int result = fibDyn(n);
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide a valid number.");
        }
    }

    public static int fibDyn(int n) {
        if (n <= 1) {
            return n;
        }

        int[] fib = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        return fib[n];
    }

}