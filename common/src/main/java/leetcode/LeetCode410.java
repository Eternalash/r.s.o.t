package leetcode;

/**
 * Author:bryan.c
 * Date:2021/7/15
 * https://leetcode-cn.com/problems/split-array-largest-sum/
 */
public class LeetCode410 {
    private static final LeetCode410 INSTANCE=new LeetCode410();
    public static void main(String... args){
        int[] nums=new int[]{7,2,5,10,8};
        System.out.println(INSTANCE.splitArray(nums,3));
    }

    public int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            right += nums[i];
            if (left < nums[i]) {
                left = nums[i];
            }
        }
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (check(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[] nums, int x, int m) {
        int sum = 0;
        int cnt = 1;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > x) {
                cnt++;
                sum = nums[i];
            } else {
                sum += nums[i];
            }
        }
        return cnt <= m;
    }
}
