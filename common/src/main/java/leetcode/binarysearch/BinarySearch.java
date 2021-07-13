package leetcode.binarysearch;

import java.math.BigDecimal;

/**
 * Author:bryan.c
 * Date:2021/7/12
 * 二分查找框架
 * int binarySearch(int[] nums, int target) {
 *     int left = 0, right = ...;
 *
 *     while(...) {
 *     //另外声明一下，计算 mid 时需要防止溢出，代码中left + (right - left) / 2就和(left + right) / 2的结果相同，但是有效防止了left和right太大直接相加导致溢出。
 *         int mid = left + (right - left) / 2;
 *         if (nums[mid] == target) {
 *             ...
 *         } else if (nums[mid] < target) {
 *             left = ...
 *         } else if (nums[mid] > target) {
 *             right = ...
 *         }
 *     }
 *     return ...;
 * }
 * 其中...标记的部分，就是可能出现细节问题的地方
 * https://mp.weixin.qq.com/s/M1KfTfNlu4OCK8i9PSAmug
 */
public class BinarySearch {
    private static final BinarySearch INSTANCE=new BinarySearch();

    public static void main(String[] args){
        int[] nums=new int[]{1,2,4,6,7,8,8,9,11,20};
        System.out.println( INSTANCE.binarySearch(nums,9));
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if(nums[mid] == target) {
                // 直接返回
                return mid;
            }
        }
        return -1;
    }

    int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定左侧边界
                right = mid - 1;
            }
        }
        // 最后要检查 left 越界的情况
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }


    int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右侧边界
                left = mid + 1;
            }
        }
        // 最后要检查 right 越界的情况
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
    }
}
