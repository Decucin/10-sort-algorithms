package com.decucin;

public class Main {

    public static void main(String[] args) {
        int[] nums = Utils.createArray(10, 100);
        System.out.println("排序前：");
        Utils.printArray(nums);
//        SortAlgorithms.betterBubbleSort(nums);
        SortAlgorithms.selectSort(nums);
        System.out.println("\n排序后：");
        Utils.printArray(nums);
    }

}
