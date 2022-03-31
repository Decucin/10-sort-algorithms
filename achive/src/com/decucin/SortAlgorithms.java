package com.decucin;

public class SortAlgorithms {

    // swap函数，交换数组中i 和 j的值
    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 冒泡排序
    public static void bubbleSort(int[] nums){
        if(nums == null){
            throw new NullPointerException("Arrays can not be empty!");
        }
        int n = nums.length;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n - 1; ++j){
                // 这里只有在后一个比现在的大的时候才交换，否则不交换
                if(nums[j] > nums[j + 1]){
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    // 冒泡算法改进（最好情况能达到O(n)的时间复杂度）
    public static void betterBubbleSort(int[] nums){
        if(nums == null){
            throw new NullPointerException("Arrays can not be empty!");
        }
        int n = nums.length;
        boolean ifChange = false;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n - 1; ++j){
                // 这里只有在后一个比现在的大的时候才交换，否则不交换
                if(nums[j] > nums[j + 1]){
                    swap(nums, j, j + 1);
                    ifChange = true;
                }
            }

            // 若是第一趟没有交换，那么就证明序列有序，直接返回即可，无需操作
            if(!ifChange){
                return;
            }
        }
    }

    // 选择排序实现
    public static void selectSort(int[] nums){
        if(nums == null){
            throw new NullPointerException("Arrays can not be empty!");
        }
        int n = nums.length;
        for(int i = 0; i < n; ++i){
            // 每轮循环初始时都假定下标为0的数为本次中的最大值
            int maxIndex = 0;
            int max = nums[0];
            for(int j = 0; j < n - i; ++j){
                // 遍历过程中不断更新本次遍历的最大值及其索引
                if(max < nums[j]){
                    maxIndex = j;
                    max = nums[j];
                }
            }
            swap(nums, maxIndex, n - i - 1);
        }
    }

}
