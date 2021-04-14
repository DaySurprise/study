package com.daysurprise.study.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Class: com.daysurprise.study.leetCode.TwoNumAdd
 * @Author: daysurprise
 * @Date: 2021/3/23
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 两数之和
 */
public class TwoNumSum {
    public static void main(String[] args) {
        int[] nums = {2,5,12,13};
        int[] ints = twoSum(nums, 17);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    public static int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("no tow sum solution");
    }
}
