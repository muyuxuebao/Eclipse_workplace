package com.yinliang.DP.Demo11_Composite;

public class Tree {
	TreeNode root = null;

	public Tree(String name) {
		root = new TreeNode(name);
	}

	public static void main(String[] args) {
		TreeNode nodeB = new TreeNode("B");
		TreeNode nodec = new TreeNode("C");
		nodeB.add(nodec);

		Tree tree = new Tree("A");
		tree.root.add(nodeB);
		System.out.println("build the tree finished!");
	}
}
