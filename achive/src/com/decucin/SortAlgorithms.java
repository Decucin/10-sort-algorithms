package com.decucin;


import java.util.Arrays;

public class SortAlgorithms {



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
                    Utils.swap(nums, j, j + 1);
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
                    Utils.swap(nums, j, j + 1);
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
            Utils.swap(nums, maxIndex, n - i - 1);
        }
    }

    // 插入排序实现
    public static void insertSort(int[] nums){
        if(nums == null){
            throw new NullPointerException("Arrays can not be empty!");
        }
        int n = nums.length;
        // 从第二个数开始
        for(int i = 1; i < n; ++i){
            int index = i;
            while(index > 0){
                // 这里我的做法是直接插入，而不是先判断位置再插入
                // 如果是先判断位置再插入，那么实际上就可以通过二分进行位置的判断，就实现了优化操作
                if(nums[index] < nums[index - 1]){
                    Utils.swap(nums, index, index - 1);
                }else{
                    break;
                }
                --index;
            }
        }
    }

    // 插入排序的优化
    public static void betterInsertSort(int[] nums){
        if(nums == null){
            throw new NullPointerException("Arrays can not be empty!");
        }
        int n = nums.length;
        for(int i = 1; i < n; ++i){
            // 这里就不是直接交换了，而是先找到哪些位置需要交换
            // 把最后一个数先保存起来
            int temp = nums[i];
            int left = 0;
            // 这里其实右边界是前一个元素
            int right = i - 1;
            while(left <= right){
                // 这里不使用(left + right) >> 1是为了防止left + right超出int范围
                int mid = left + ((right - left) >> 1);
                // 大于或者等于表示在右边（保持稳定性，等于也包含）
                if(temp >= nums[mid]){
                    left = mid + 1;
                }else{
                    // 小于表示在左边
                    right = mid - 1;
                }
            }
            // 这里已经找到了位置left，把left后面的全部后移一位
            for(int j = i; j > left; --j){
                nums[j] = nums[j - 1];
            }
            nums[left] = temp;
        }
    }

    // 希尔排序实现
    public static void shellSort(int[] nums){
        if(nums == null){
            throw new NullPointerException("Arrays can not be empty!");
        }
        int n = nums.length;
        // 这里的增量为选择的是 n / 2，关于增量的选取其实众说纷纭
        int gap = n >> 1;
        // 增量大于等于1就一直排序
        while(gap >= 1){
            // 第一组一定有序
            for (int i = gap; i < n; ++i){
                // 先把当前的数字保存起来
                // 这里保存数字其实本质上是因为没有使用swap，不然两两交换其实是不需要这个的
                int temp = nums[i];
                // 看需要比较几次（这里因为后面对j的操作都是j -= gap，因此实际上就是 j / gap就是比较的次数）
                int j = i - gap;
                // 这里就一直比较（最多j / gap次），找到插入位置
                while(j >= 0 && nums[j] > temp){
                    // 后移增量个单位
                    nums[j + gap] = nums[j];
                    // 继续向下判断
                    j -= gap;
                }
                // 把之前保存的值插入
                // 这里的j已经是到了不满足的那一位，因此要找到他后面的满足的情况
                nums[j + gap] = temp;
            }
            // 增量减半
            gap /= 2;
        }
    }

    // 归并排序实现
    public static int[] mergeSort(int[] nums){
        if(nums == null){
            throw new NullPointerException("Arrays can not be empty!");
        }
        int n = nums.length;
        if(n > 1){
            // 先分
            int[] left = Arrays.copyOfRange(nums, 0, n >> 1);
            int[] right = Arrays.copyOfRange(nums, n >> 1, n);
//            mergerSort(left);
//            mergerSort(right);
            // 后治
//            return merge(left, right);
            return merge(mergeSort(left), mergeSort(right));
        }else{
            return nums;
        }
        // 这里如果长度为1，那直接啥也不做就行
    }

    // 归并排序中合并两个有序数组
    private static int[] merge(int[] left, int[] right){
        // 现在left和right都是有序的
        // 合并两个有序数组并返回
        int n1 = left.length;
        int n2 = right.length;
        int[] res = new int[n1 + n2];
        // 数组1走到了哪
        int index1 = 0;
        // 数组2走到了哪
        int index2 = 0;
        // 结果数组走到了哪
        int index0 = 0;
        while(index1 < n1 && index2 < n2){
            // 这个时候两个还没有放完
            if(left[index1] <= right[index2]){
                res[index0++] = left[index1++];
            }else{
                res[index0++] = right[index2++];
            }
        }
        // 现在看还有那边的元素
        while(index1 < n1){
            res[index0++] = left[index1++];
        }
        while(index2 < n2){
            res[index0++] = right[index2++];
        }
        return res;
    }

    // 快速排序实现
    public static void quickSort(int[] nums, int left, int right){
        if(nums == null){
            throw new NullPointerException("Arrays can not be empty!");
        }
        if(left >= right){
            return;
        }
        // 基准
        int pivot = left;
        // 这里index表示的是：如果不满足了，应该放在哪里
        // 先放在基准的后面，最后把不满足的最后一位和基准做交换即可
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            // 如果不满足的话应该是和index交换
            if (nums[i] < nums[pivot]) {
                Utils.swap(nums, i, index);
                // 下一个不满足的元素应该放在下一位
                ++index;
            }
        }
        // 这里index的前一位就是最后一个不满足的，两者交换即可
        --index;
        Utils.swap(nums, pivot, index);
        // 以后递归的快排左部分和右部分
        quickSort(nums, left, index - 1);
        quickSort(nums, index + 1, right);
    }

    // 堆排序实现
    public static void heapSort(int[] nums){
        if(nums == null){
            throw new NullPointerException("Arrays can not be empty!");
        }
        int n = nums.length;
        // 先根据nums数组建造堆
        buildMaxHeap(nums, n);

        // 之后不断的选择和调整堆
        for(int i = n - 1; i > 0; --i){
            // 选出最大的元素
            Utils.swap(nums, 0, i);
            // 调整堆(还有i个元素要调整)
            heapify(nums, 0, i);
        }
    }

    // 堆排序用到的大顶堆的构建
    private static void buildMaxHeap(int[] nums, int n){
        // 构建大顶堆实际上就是从堆的非叶子节点开始调整堆
        for(int i = n / 2; i >= 0; --i){
            heapify(nums, i, n);
        }
    }

    // 堆排序用到的用于调整堆结构
    private static void heapify(int[] nums, int start, int n){
        // 左节点
        int left = 2 * start + 1;
        // 右节点
        int right = 2 * start + 2;
        // 最大元素下标
        int largestIndex = start;

        // 这里的做法是先选出最大值再交换
        if(left < n && nums[largestIndex] < nums[left]){
            largestIndex = left;
        }
        if(right < n && nums[largestIndex] < nums[right]){
            largestIndex = right;
        }
        // 不相等说明需要调整
        // 这里largestIndex只可能是starter, left或是right
        if(largestIndex != start){
            Utils.swap(nums, largestIndex, start);
            // 这里交换只是确保当前的(star(parent), left, right)满足，后面的可能不符合堆的性质，再继续
            // 这里注意其实需要调整的是之前最大值所在的位置(left或是right)及其后面的，因为其前面的元素不受到插入值的影响
            heapify(nums, largestIndex, n);
        }
    }

    // 计数排序实现
    public static void countingSort(int[] nums){
        if(nums == null){
            throw new NullPointerException("Arrays can not be empty!");
        }
        int n = nums.length;
        // max - min + 1就是额外需要的空间
        int min = nums[0];
        int max = nums[0];
        for(int num : nums){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        // 开始计数
        int[] counting = new int[max - min + 1];
        for(int num : nums){
            ++counting[num - min];
        }
        // 这里index表示的是在新开辟的空间中的下标
        int index = 0;
        // 对应的数就是最小的数 + 下标
        for(int i = 0; i < n; ++i){
            nums[i] = min + index;
            --counting[index];
            // 这里不加前一个判断的话最后一次必定越界
            while (index < max - min && counting[index] == 0){
                ++index;
            }
        }
    }

    // 桶排序实现
    public static void bucketSort(int[] nums){
        if(nums == null){
            throw new NullPointerException("Arrays can not be empty!");
        }
        int n = nums.length;
        int max = nums[0];
        for(int num : nums){
            max = Math.max(max, num);
        }
        // 这里我的映射函数是value / 10
        // 即0 - 9 第0个桶 …… 90 — 99第9个桶
        int[][] buckets = new int[max / 10 + 1][0];
        for(int num : nums){
            // 当前数所在的桶下标
            int index = num / 10;
            // 把这个数放到对应的桶
            buckets[index] = appendArr(buckets[index], num);
        }
        // 原始数组的下标
        int index = 0;
        // 对每个桶进行排序并返回
        for(int[] bucket : buckets){
            // 长度小于等于0表示没有
            if(bucket.length <= 0){
                continue;
            }
            insertSort(bucket);
            for(int num : bucket){
                nums[index++] = num;
            }
        }
    }

    // 桶排序和基数排序中用到的把对应的数放到对应的桶的函数
    private static int[] appendArr(int[] nums, int num){
        // 这里是拷贝原桶，实际上性能很差
        int n = nums.length;
        nums = Arrays.copyOf(nums, n + 1);
        nums[n] = num;
        return nums;
    }

    // 基数排序实现
    public static void radixSort(int[] nums){
        if(nums == null){
            throw new NullPointerException("Arrays can not be empty!");
        }
        int n = nums.length;
        int max = nums[0];
        for (int num : nums){
            max = Math.max(max, num);
        }
        // 找到最高位的长度
        int maxLen = 0;
        if(max == 0){
            maxLen = 1;
        }else{
            while(max != 0){
                max /= 10;
                ++maxLen;
            }
        }

        int mod = 10;
        int dev = 1;
        for(int i = 0; i < maxLen; ++i, dev *= 10, mod *= 10){
            // 扩展 0 - 9表示负数，10-19表示正数
            int[][] counter = new int[mod * 2][0];
            // 排maxLen次，每次排列之后在i位都是递增的
            for (int j = 0; j < n; j++) {
                int bucket = ((nums[j] % mod) / dev) + mod;
                // 放入对应的位置
                counter[bucket] = appendArr(counter[bucket], nums[j]);
            }

            int index = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    nums[index++] = value;
                }
            }
        }
    }

}
