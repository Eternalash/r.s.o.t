package leetcode.maxArea;

import java.util.Arrays;

/**
 * @Author:bryan.c
 * @Date:2020/11/19
 */
public class MaxArea {
    public static void main(String[] args) {

    }


    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int lenx=horizontalCuts.length;
        int leny=verticalCuts.length;
        long maxX=horizontalCuts[0];
        long maxY=verticalCuts[0];
        for(int i=1;i<lenx;i++){
            maxX=Math.max(horizontalCuts[i]-horizontalCuts[i-1],maxX);
        }
        maxX=Math.max(maxX,h-horizontalCuts[lenx-1]);
        for(int i=1;i<leny;i++){
            maxY=Math.max(verticalCuts[i]-verticalCuts[i-1],maxY);
        }
        maxY=Math.max(maxY,w-verticalCuts[leny-1]);
        return (int)((maxX*maxY)%(1000000007));
    }
}
