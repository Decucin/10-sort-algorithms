package com.decucin;

public class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        int[] nums = Utils.createArray(10, 100);
        System.out.println("排序前：");
        Utils.printArray(nums);
//        SortAlgorithms.bubbleSort(nums);
//        SortAlgorithms.betterBubbleSort(nums);
//        SortAlgorithms.selectSort(nums);
//        SortAlgorithms.insertSort(nums);
//        SortAlgorithms.betterInsertSort(nums);
//        SortAlgorithms.shellSort(nums);
//        int[] res = SortAlgorithms.mergeSort(nums);
//        SortAlgorithms.quickSort(nums, 0, nums.length - 1);
        SortAlgorithms.heapSort(nums);
        System.out.println("\n排序后：");
        Utils.printArray(nums);
    }

}
