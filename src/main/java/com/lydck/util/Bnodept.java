package com.lydck.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**二叉树
 * @author Lydck
 *
 * @param <T>
 */
public class Bnodept<T> {
	T data;
	Bnodept<T> lchild, rchild;
	public Bnodept() {}
	public Bnodept(T data) {
		this.data = data;
		lchild = null;
		rchild = null;
	}
	public Bnodept(T data, Bnodept<T> lchild, Bnodept<T> rchild) {
		this.data = data;
		this.lchild = lchild;
		this.rchild = rchild;
	}
	
	/**二叉树的先序遍历
	 * @param tree
	 */
	static <T> void preorder(Bnodept<T> tree) {
		if(tree != null) {
			visit(tree.data);
			preorder(tree.lchild);
			preorder(tree.rchild);
		}
	}
	/**二叉树中序遍历
	 * @param tree
	 */
	static <T> void inorder(Bnodept<T> tree) {
		if(tree != null) {
			inorder(tree.lchild);
			visit(tree.data);
			inorder(tree.rchild);
		}
	}
	/**二叉树后序遍历
	 * @param tree
	 */
	void posorder(Bnodept<T> tree) {
		if(tree != null) {
			posorder(tree.lchild);
			posorder(tree.rchild);
			visit(tree.data);
		}
	}
	private static <T> void visit(T t) {
		System.out.println(t);
	}
	static <T> Bnodept<T> createTree(Iterator<T> iterator) {
		if(!iterator.hasNext()) {
			return null;
		}
		Bnodept<T> bt = new Bnodept<>();
		bt.data = iterator.next();
		if(iterator.hasNext()) {
			bt.lchild = new Bnodept<T>(iterator.next());
		}
		if(iterator.hasNext()) {
			bt.rchild = new Bnodept<T>(iterator.next());
		}
		if(bt.lchild != null) {
			bt.lchild.lchild = createTree(iterator);
		}
		//bt.rchild = createTree(iterator);
		return bt;
	}
	public static void main(String[] args) {
		List<String> strs = new ArrayList<>();
		strs.add("a");
		strs.add("b");
		strs.add("c");
		strs.add("d");
		strs.add("e");
		Bnodept<String> tree = createTree(strs.iterator());
		inorder(tree);
	}
}
