package com.decucin;

import java.util.Random;



public class Utils {

    // 创建大小为size，数据范围在0-range的数组
    public static int[] createArray(int size, int range){
        Random random = new Random();
        int[] nums = new int[size];
        for(int i = 0; i < size; ++i){
            nums[i] = random.nextInt(range);
        }
        return nums;
    }

    public static void printArray(int[] nums){
        if(nums == null){
            throw new NullPointerException("Arrays can not be empty!");
        }
        for(int num : nums){
            System.out.print(num);
            System.out.print(" ");
        }
    }

}
