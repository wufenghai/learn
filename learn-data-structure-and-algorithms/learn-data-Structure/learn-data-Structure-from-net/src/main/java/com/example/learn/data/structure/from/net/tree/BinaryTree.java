package com.example.learn.data.structure.from.net.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树
 *
 * @author wfh
 * @create 2023/5/6 14:10
 */
public class BinaryTree {
    private class BTNode {
        char data; //数据
        BTNode left;//左孩子引用
        BTNode right;//右孩子引用

        public BTNode(char data) {
            this.data = data;
        }
    }


    public BTNode root;//二叉树的根节点

    /**
     * 二叉树的创建
     *
     * @return
     */
    public BTNode creatTree() {
        BTNode nodeA = new BTNode('A');
        BTNode nodeB = new BTNode('B');
        BTNode nodeC = new BTNode('C');
        BTNode nodeD = new BTNode('D');
        BTNode nodeE = new BTNode('E');
        BTNode nodeF = new BTNode('F');
        BTNode nodeG = new BTNode('G');
        BTNode nodeH = new BTNode('H');
        root = nodeA;
        nodeA.left = nodeB;
        nodeA.right = nodeC;
        nodeB.left = nodeD;
        nodeB.right = nodeE;
        nodeC.left = nodeF;
        nodeC.right = nodeG;
        nodeE.right = nodeH;
        return root;
    }

    /**
     * 前序遍历
     *
     * @param root
     */
    public void preOrder(BTNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");//打印跟节点
        preOrder(root.left);//左子树
        preOrder(root.right);//右子树
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void inOrder(BTNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);//左子树
        System.out.print(root.data + " ");//根节点
        inOrder(root.right);//右子树
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public void postOrde(BTNode root) {
        if (root == null) {
            return;
        }
        postOrde(root.left);//左子树
        postOrde(root.right);//右子树
        System.out.print(root.data + " ");//根节点

    }

    /**
     * 层级查询
     *
     * @param root
     */
    public void levelOrder(BTNode root) {
        Queue<BTNode> queue = new LinkedList<>();
        if (root == null) {
            return;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            BTNode temp = queue.poll();
            System.out.println(temp.data + " ");
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }

    }

    /**
     * 整棵树结点的个数 = 左子树的结点个数+右子树的结点个数+1（根节点）
     * 如果根结点为空，表示该树没有结点，则返回0。
     * <p>
     * 获取二叉树结点的个数
     *
     * @param root
     * @return
     */
    public int size(BTNode root) {
        if (root == null) {
            return 0;
        }
        return size(root.left) + size(root.right) + 1;
    }

    /**
     * 整棵树叶子结点的个数 = 左子树的叶子结点个数+右子树的叶子结点个数
     * 如果根结点为空，表示该树没有结点，则返回0
     * 如果结点的左右子树都为空，表示该结点为叶子结点，则返回1。
     * <p>
     * 获取二叉树叶子结点的个数
     *
     * @param root
     * @return
     */
    public int getLeafNodeCount(BTNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return getLeafNodeCount(root.left) + getLeafNodeCount(root.right);
    }

    /**
     * k=1，表示第一层结点的个数；k=2，表示第二层结点的个数。每递进一层，k就会减1。
     * 一棵二叉树第k层结点的个数 = 左子树第k-1层结点的个数 + 右子树第k-1层结点的个数。
     * 递归终止条件：结点为空或 k<=0（k是从第一层开始的），返回0 ；k 一直递减，当k=1时，返回1。
     * <p>
     * 获取二叉树第 k 层节点的个数
     *
     * @param root
     * @param k
     * @return
     */
    public int getKLeafNodeCount(BTNode root, int k) {
        if (root == null || k <= 0) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        return getKLeafNodeCount(root.left, k - 1) + getKLeafNodeCount(root.right, k - 1);
    }

    /**
     * 整棵树的高度 = 左子树的高度和右子树的高度取最大值，然后+1（根节点）
     * 如果根结点为空，则这棵树的高度为0，返回0。
     * <p>
     * 获取二叉树的高度
     *
     * @param root
     * @return
     */
    public int maxDepth(BTNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    /**
     * 如果根结点为空，则这棵二叉树不存在value元素，直接返回null
     * 先判断根节点的值是不是要找值，如果不是就在左子树中找，如果在左子树中没有找到，再去右子树中找。
     * 如果找到了，就返回value元素的地址（结点的地址）；找不到返回null。
     * <p>
     * 检测值为value的元素是否存在
     *
     * @param root
     * @param val
     * @return
     */
    public BTNode find(BTNode root, char val) {
        if (root == null) {
            return null;
        }
        if (root.data == val) {
            return root;
        }
        BTNode ret = find(root.left, val);
        if (ret != null) {
            return ret;
        }
        ret = find(root.right, val);
        if (ret != null) {
            return ret;
        }
        return null;
    }


    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        BTNode root = binaryTree.creatTree();
        System.out.print("前序遍历：");
        binaryTree.preOrder(root);
        System.out.print("\n中序遍历：");
        binaryTree.inOrder(root);
        System.out.print("\n后序遍历：");
        binaryTree.postOrde(root);
        System.out.println();
        System.out.println("size:" + binaryTree.size(root));
        System.out.println("getLeafNodeCount:" + binaryTree.getLeafNodeCount(root));
    }

}