package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Bryan.C <br>
 * Date:2020/3/20
 */
public class GetLeastNumbers {
    public static void main(String[] args){
        int[][] docs=new int[][]{{14, 15, 100, 9, 3},{32, 1, 9, 3, 5},{15, 29, 2, 6, 8, 7},{7, 10}};
        Map<Integer, List<Integer>> map=new HashMap<>();
        List<String> res = new ArrayList<>();
        int[][] ans = new int[docs.length][docs.length];
        for (int i = 0; i < docs.length; i++) {
            for (int j = 0; j < docs[i].length; j++) {
                List<Integer> list = map.get(docs[i][j]);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(docs[i][j], list);
                } else {
                    for (Integer k : list) {
                        ans[i][k]++;
                    }
                }
                list.add(i);
            }

            for (int k = 0; k < docs.length; k++) {
                if (ans[i][k] > 0) {
                    res.add(k + "," + i + ": " + String.format("%.4f", (double) ans[i][k] / (docs[i].length + docs[k].length - ans[i][k])));
                }
            }
        }
    }
}
