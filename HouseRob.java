public class HouseRob {
    public static void main(String args[]) {
        int[] nums = { 1, 3, 4, 7, 6, 10, 12, 5, 1 };
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {
        int[] tab = new int[nums.length];
        tab[0] = nums[0];
        tab[1] = max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            tab[i] = max(tab[i - 2] + nums[i], tab[i - 1]);
        }

        return tab[nums.length - 1];
    }

    public static int max(int value1, int value2) {
        if (value1 > value2)
            return value1;
        else
            return value2;
    }
}