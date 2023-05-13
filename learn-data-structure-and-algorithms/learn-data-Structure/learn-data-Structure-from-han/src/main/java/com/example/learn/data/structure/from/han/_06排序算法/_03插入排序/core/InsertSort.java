package com.example.learn.data.structure.from.han._06排序算法._03插入排序.core;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {

    public static void main(String[] args) {
        /*
        int[] arr = {101, 34, 119, 1};
        System.out.println("插入前：");
        System.out.println(Arrays.toString(arr));
        insertSort(arr);
        System.out.println("插入后：");
        System.out.println(Arrays.toString(arr));
        */

        // 创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        System.out.println("插入排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        insertSort(arr); //调用插入排序算法

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }

    //插入排序
    public static void insertSort(int[] arr) {

        /*
        //使用逐步推导的方式来讲解，便利理解
        //第1轮 {101, 34, 119, 1};  => {34, 101, 119, 1}


        //{101, 34, 119, 1}; => {101,101,119,1}
        //定义待插入的数
        int insetVal = arr[1];
        int insertIndex = 1 - 1;//即arr[1]的前面这个数的下标

        //给insertVal 找到插入的位置
        //说明
        //1. insertIndex >= 0 保证在给 insertVal 找插入位置，不越界
        //2. insetVal < arr[insertIndex] 说明待插入的数，还没找到插入位置
        //3. 就需要将arr[insertIndex] 后移一位
        while (insertIndex >= 0 && insetVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; //将arr[insertIndex] 后移一位
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex +1
        arr[insertIndex + 1] = insetVal;
        System.out.println("第一轮插入后：");
        System.out.println(Arrays.toString(arr));

        //第二轮
        //定义待插入的数
        insetVal = arr[2];
        insertIndex = 2 - 1;//即arr[1]的前面这个数的下标
        while (insertIndex >= 0 && insetVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; //将arr[insertIndex] 后移一位
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex +1
        arr[insertIndex + 1] = insetVal;
        System.out.println("第二轮插入后：");
        System.out.println(Arrays.toString(arr));

        //第三轮
        //定义待插入的数
        insetVal = arr[3];
        insertIndex = 3 - 1;//即arr[1]的前面这个数的下标
        while (insertIndex >= 0 && insetVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; //将arr[insertIndex] 后移一位
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex +1
        arr[insertIndex + 1] = insetVal;
        System.out.println("第三轮插入后：");
        System.out.println(Arrays.toString(arr));
*/

        //使用for循环来把代码简化
        //定义待插入的数
        int insetVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insetVal = arr[i];
            insertIndex = i - 1;//即arr[1]的前面这个数的下标

            //给insertVal 找到插入的位置
            //说明
            //1. insertIndex >= 0 保证在给 insertVal 找插入位置，不越界
            //2. insetVal < arr[insertIndex] 说明待插入的数，还没找到插入位置
            //3. 就需要将arr[insertIndex] 后移一位
            while (insertIndex >= 0 && insetVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex]; //将arr[insertIndex] 后移一位
                insertIndex--;
            }
            //当退出while循环时，说明插入的位置找到，insertIndex +1
            //判断是否需要赋值
            if (insertIndex + 1 >= i) {
                arr[insertIndex + 1] = insetVal;
            }

        }
    }
}
