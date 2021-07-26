package leetcode.binarysearch;

/**
 * @author: bryan.c
 * @date: 2021/7/26 下午3:23
 * @package: leetcode
 * https://leetcode-cn.com/problems/binary-search/solution/hua-jie-suan-fa-704-er-fen-cha-zhao-by-guanpengchn/
 */
public class LeetCode704 {
    private static final LeetCode704 INSTANCE=new LeetCode704();
    public static void main(String... args){

    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left<=right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
