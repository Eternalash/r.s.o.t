package leetcode.binarysearch;

/**
 * @author: bryan.c
 * @date: 2021/7/26 下午5:32
 * @package: leetcode.binarysearch
 * https://leetcode-cn.com/problems/koko-eating-bananas/
 */
public class LeetCode875 {
    private static final LeetCode875 INSTANCE=new LeetCode875();

    public static void main(String... args){
        int[] piles=new int[]{30,11,23,4,20};
        System.out.println(INSTANCE.minEatingSpeed(piles,5));
    }

    public int minEatingSpeed(int[] piles, int H) {
        int left = 1;
        int max=0;
        for(int i:piles){
            if(i>=max){
                max=i;
            }
        }
        int right = max;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(piles, mid) <= H) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    // 定义：速度为 x 时，需要 f(x) 小时吃完所有香蕉
    // f(x) 随着 x 的增加单调递减
    int f(int[] piles, int x) {
        int hours = 0;
        for (int i = 0; i < piles.length; i++) {
            hours += piles[i] / x;
            if (piles[i] % x > 0) {
                hours++;
            }
        }
        return hours;
    }
}
