package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Author: Bryan.C <br>
 * Date:2020/4/20
 */
public class SingleNumber {
    public static void main(String[] args){
        int[] nums={4,1,2,1,2,2};
        System.out.println(singleNumber(nums));
    }
    public static int singleNumber(int[] nums) {
        LinkedList<Integer> result=new LinkedList<>();
        Map<Integer,Integer> filter=new HashMap<>();
        for(Integer i :nums){
            if(result.contains(i)||filter.containsKey(i)) {result.remove(i);filter.put(i,i);}
            else{
                result.add(i);
            }
        }
        return result.get(0);
    }

    /**
     * just for leetcode,when element appears three times or more,it won't work
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums){
        int len = nums.length;
        int res = -1;
        if(len >= 1) {
            res = nums[0];
            for (int i = 1; i < len; i++) {
                res = res ^ nums[i];
            }
        }
        return res;
    }
}
