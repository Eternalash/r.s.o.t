package leetcode.binarysearch;

/**
 * Author:bryan.c
 * Date:2021/7/7
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 *
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 *
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 *
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 */
public class LeetCode4 {
    private static final LeetCode4 INSTANCE=new LeetCode4();

    public static void main(String[] args){
        int[] nums1=new int[]{2,4,6,15};
        int[] nums2=new int[]{1,5,7,8,9,10,17};
        System.out.println(INSTANCE.findMedianSortedArrays(nums1,nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length)
            return findMedianSortedArrays(nums2,nums1);
        int m=nums1.length;
        int n=nums2.length;
        //(m+n+1)/2 整型溢出
        //(m+n+1)/2 +1可以忽略总长度奇数偶数判断
        int totalleft=m+(n-m+1)/2;
        //在nums1的区间[0,m]里查找恰当的分割线
        //使得nums1[left-1]<=nums2[totalleft-mid] && nums2[totalleft-mid-1]<=nums1[left]
        int left=0;
        int right=m;

        //搜索区间 [left,right),right=m,如果右闭则越界，即left最大值为m-1
        while(left<right){
            int mid =left+(right-left+1)/2;
            if(nums1[mid-1]>nums2[totalleft-mid]){
                //下一轮搜索区间[left,mid-1]
                right=mid-1;
            }else{
                left=mid;
            }
        }

        int nums1LeftMax=left==0?Integer.MIN_VALUE:nums1[left-1];
        int nums1RightMin=left==m?Integer.MAX_VALUE:nums1[left];
        int nums2LeftMax=totalleft-left==0?Integer.MIN_VALUE:nums2[totalleft-left-1];
        int nums2RightMin=totalleft-left==m?Integer.MAX_VALUE:nums2[totalleft-left];
        if((m+n)%2==1){
            return Math.max(nums1LeftMax,nums2LeftMax);
        }else{
            return (double)(Math.max(nums1LeftMax,nums2LeftMax)+Math.min(nums1RightMin,nums2RightMin))/2;
        }
    }
}
