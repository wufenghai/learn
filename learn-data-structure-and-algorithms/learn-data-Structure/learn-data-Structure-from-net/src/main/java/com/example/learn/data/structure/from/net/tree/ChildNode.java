package com.example.learn.data.structure.from.net.tree;

/**
 * 孩子表示法
 *
 * @author wfh
 * @create 2023/5/6 14:05
 */
public class ChildNode {
    int data;   //数据域
    ChildNode left; // 左孩子的引用，常常代表左孩子为根的整棵左子树
    ChildNode right;    // 右孩子的引用，常常代表右孩子为根的整棵右子树
}
