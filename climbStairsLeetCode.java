import java.util.Scanner;

public class climbStairsLeetCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(climbStairsMemo(n));
        sc.close();
    }

    public static int climbStairs(int n) {
        return climbStairs(n, 0);
    }

    public static int climbStairs(int n, int step) {
        int steps = 0;

        if (n < 0)
            return 0;
        if (n == 0)
            return 1;

        steps += climbStairs(n - 1, 1);
        steps += climbStairs(n - 2, 2);

        return steps;
    }

    public static int climbStairsMemo(int n) {
        int[] memo = new int[n + 1];
        memo[0] = 1;

        for (int i = 1; i < memo.length; i++) {
            if ((i - 1) >= 0)
                memo[i] += memo[i - 1];
            if ((i - 2) >= 0)
                memo[i] += memo[i - 2];

        }
        return memo[n];
    }

}
