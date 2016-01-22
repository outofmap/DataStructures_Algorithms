package DataStructure;

public class RBTNode<T extends Comparable<T>> {
	public static final int BLACK = 1;
	public static final int RED = 0;
	
	public T key;
	
	RBTNode<T> parent;
	RBTNode<T> left;
	RBTNode<T> right;
	public int numleft = 0;
	public int numRight = 0;
	public int color;
	
	public RBTNode(){};
	
	public RBTNode(RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right, int numleft, int numRight, int color) {
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.numleft = numleft;
		this.numRight = numRight;
	}
	
	RBTNode(T key){
		this.key = key;
	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public RBTNode<T> getParent() {
		return parent;
	}

	public void setParent(RBTNode<T> parent) {
		this.parent = parent;
	}

	public RBTNode<T> getLeft() {
		return left;
	}

	public void setLeft(RBTNode<T> left) {
		this.left = left;
	}

	public RBTNode<T> getRight() {
		return right;
	}

	public void setRight(RBTNode<T> right) {
		this.right = right;
	}

	public int getNumleft() {
		return numleft;
	}

	public void setNumleft(int numleft) {
		this.numleft = numleft;
	}

	public int getNumRight() {
		return numRight;
	}

	public void setNumRight(int numRight) {
		this.numRight = numRight;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
}
