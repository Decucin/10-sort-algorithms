package com.decucin;

import java.util.Random;



public class Utils {

    // swap函数，交换数组中i 和 j的值
    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 另一种不需要第三个变量就能实现的swap函数
    public static void otherSwap(int[] nums, int i, int k){
        nums[i] = nums[i] + nums[k];
        nums[k] = nums[i] - nums[k];
        nums[i] = nums[i] - nums[k];
    }

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
