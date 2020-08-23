package com.leetcode;

/**
 * Author: Bryan.C <br>
 * Date:2020/4/21
 */
public class MoveZeroes {
    public static void moveZeroes(int[] nums) {
        int i = 0;
        int count = 0;
        int m = nums.length - 1;
        while (i <= m) {
            if (nums[i] == 0) {
                // 计算0的个数
                count++;
                i++;
            }
            // i不等于0
            else {// 将nums[i]向前移动count个位置
                nums[i - count] = nums[i];
                i++;
            }
        }
        // 将数组的最后count个元素置为0
        for (int j = count; j >= 1; j--) {
            nums[m] = 0;
            m--;
        }
    }
    public static void main(String[] args){
        int[] nums={0,1,0,3,0,12,2,54};
        moveZeroes(nums);
        for(int i:nums){
            System.out.println(i);
        }
    }

    public void moveZeroes2(int[] nums) {
        if (nums == null
                || nums.length == 0) {
            return;
        }
        int n = nums.length;
        // low指向为0的元素
        int low = 0;
        // high指向low后第一个不为0的元素
        int high = 0;
        while (low < n && high < n) {
            while (low < n && nums[low] != 0)
                low++;

            high = low;
            while (high < n && nums[high] == 0)
                high++;

            if (low < n && high < n) {
                int tmp = nums[low];
                nums[low] = nums[high];
                nums[high] = tmp;
                high = low + 1;
            }
        }
    }

}
