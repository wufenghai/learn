package com.example.learn.data.structure.from.net.tree;

/**
 * 孩子双亲表示法
 *
 * @author wfh
 * @create 2023/5/6 14:08
 */
public class ChildAndParentNode {
    int data;   // 数据域
    ChildAndParentNode left;    // 左孩子的引用，常常代表左孩子为根的整棵左子树
    ChildAndParentNode right;   // 右孩子的引用，常常代表右孩子为根的整棵右子树
    ChildAndParentNode parent;  // 当前节点的根节点
    
}
