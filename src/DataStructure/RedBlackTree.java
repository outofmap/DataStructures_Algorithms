package DataStructure;

public class RedBlackTree<T extends Comparable<T>> {

	private RBTNode<T> nil = new RBTNode<T>();
	private RBTNode<T> root = nil;
	
	public RedBlackTree() {
		root.left = nil;
		root.right = nil;
		root.parent = nil;
	}

	public void insert(RedBlackTree<T> tree, RBTNode<T> newNode) {
		// initialize sentinel node
		RBTNode<T> parentNode = tree.nil;
		RBTNode<T> curNode = tree.root;
		// tree의 curNode가 tree의 맨 마지막 노드인 nil일 될때까지 탐색. parentNode를 확정짓고 while
		// loop를 빠져나옴.
		while (curNode != tree.nil) {
			parentNode = curNode;
			if (newNode.key.compareTo(curNode.key) < 0) {
				curNode = curNode.left;
			} else {
				curNode = curNode.right;
			}
		}
		// 확정한 parentNode는 newNode parentNode이다.
		newNode.parent = parentNode;
		if (parentNode == tree.nil) {
			tree.root = newNode;
		} else if (newNode.key.compareTo(parentNode.key) < 0) {
			parentNode.left = newNode;
		} else {
			parentNode.right = newNode;
		}

		// newnNode의 child를 nil로 초기화,color는 RED로 초기
		newNode.left = tree.nil;
		newNode.right = tree.nil;
		if(newNode != tree.root) {
		newNode.color = RBTNode.RED;

		insertFixup(tree, newNode); // newNode의 color가 Red이고, parent가 red인 경우
									// 조정.
		}

	}

	public void insertFixup(RedBlackTree<T> t, RBTNode<T> fixupNode) {
	//무한루프를돈다.
		while (fixupNode.parent.color == RBTNode.RED) {
			System.out.println("여기2");
			// fixupNode의 parent가 GP의 left child 일 때,
			if (fixupNode.parent == fixupNode.parent.parent.left) {
				RBTNode<T> uncleNode = fixupNode.parent.parent.right;
				// unclde이 red 일 때, parent와uncle을 black으로 변경. Grand Parent를 RED로
				// 바꾼 후 문제를 Grand Parent로 넘긴다.
				if (uncleNode.color == RBTNode.RED) {
					fixupNode.parent.color = RBTNode.BLACK;
					uncleNode.color = RBTNode.BLACK;
					fixupNode.parent.parent.color = RBTNode.RED;
					fixupNode = fixupNode.parent.parent;
				} else {
					if (fixupNode == fixupNode.parent.right) {
						fixupNode = fixupNode.parent;
						leftRotate(t, fixupNode);
					}
					fixupNode.parent.color = RBTNode.BLACK;
					fixupNode.parent.parent.color = RBTNode.RED;
					rightRotate(t, fixupNode.parent.parent);
				}
				//t.root.color = RBTNode.BLACK;
			} 
			else {
				//fixupNode의 parent가 GP의 right child일 때, 
				//위의 left child일때와 대칭적인 문제이므로 left<->right를 변경해 해결함
				RBTNode<T> uncleNode = fixupNode.parent.parent.left;
				if(uncleNode.color == RBTNode.RED) {
					fixupNode.parent.color = RBTNode.BLACK;
					uncleNode.color = RBTNode.BLACK;
					fixupNode.parent.parent.color = RBTNode.RED;
					fixupNode = fixupNode.parent.parent;
				} else {
					if(fixupNode == fixupNode.parent.left) {
						fixupNode = fixupNode.parent;
						rightRotate(t, fixupNode);
					}
					fixupNode.parent.color = RBTNode.BLACK;
					fixupNode.parent.parent.color = RBTNode.RED;
					leftRotate(t, fixupNode.parent.parent);
				}
			}
		}
		t.root.color = RBTNode.BLACK;
	}

	public void rightRotate(RedBlackTree<T> t, RBTNode<T> y) {
		// x는 y의 left child일 때
		RBTNode<T> x = y.left;
		// x의 right child를 y의 left child로 조정
		y.left = x.right;
		if (x.right != nil) {
			x.right.parent = y;
		}
		// y의 부모를 x의 부모로 바꾸고,y가 left /right child였는지 확인
		x.parent = y.parent;
		if (y.parent == nil) {
			t.root = x;
		} else if (y.parent.left == y) {
			y.parent.left = x;
		} else {
			y.parent.right = x;
		}
		// y와 x의 관계를 조정
		y.parent = x;
		x.right = y;

	}

	public void leftRotate(RedBlackTree<T> t, RBTNode<T> x) {
		// y는 x의 right child일 때
		RBTNode<T> y = x.right;
		// y의 left child를 x의 right child로 조정
		x.right = y.left;
		if (y.left != nil) {
			y.left.parent = x;
		}
		// x의 부모를 y의 부모로 바꾸고,x가 left 혹은 right child였는지 확인
		y.parent = x.parent;
		if (x.parent == nil) {
			t.root = y;
		} else if (x.parent.left == x) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		// x와 y를 rotate이후 관게로 조정
		x.parent = y;
		y.left = x;
	}

	public void changeNode(RedBlackTree<T> t, RBTNode<T> oldNode, RBTNode<T> newNode) {
		if (oldNode.parent == t.nil) {
			t.root = newNode;
		} else if (oldNode == oldNode.parent.left) {
			oldNode.parent.left = newNode;
		} else {
			oldNode.parent.right = newNode;
		}
		newNode.parent = oldNode.parent;
	}

	public void delete(RedBlackTree<T> t, RBTNode<T> node) {
		// 삭제하는 Node의 색깔이 black이거나, successor의 색깔이 black인 경우 black height 1증가
		// 필요.
		int deletedColor = node.color;
		RBTNode<T> successor;
		RBTNode<T> fixupNode;
		if (node.left == t.nil) {
			fixupNode = node.right;
			changeNode(t, node, node.right);
		} else if (node.right == t.nil) {
			fixupNode = node.left;
			changeNode(t, node, node.left);
		} else {
			// 삭제하는 Node child가 2개인 경우
			successor = treeMinimum(node.right);
			deletedColor = successor.color;
			fixupNode = successor.right;
			if (successor.parent == node) {
				// successor.right가 nil인 경우에 fixupNode.parent재 지정
				fixupNode.parent = successor;
			} else {
				changeNode(t, successor, successor.right);
				successor.right = node.right;
				successor.right.parent = successor;
			}
			changeNode(t, node, successor);
			successor.left = node.left;
			successor.left.parent = successor;
			successor.color = node.color;
		}
		if (deletedColor == RBTNode.BLACK) {
			deleteFixup(t, fixupNode);
		}
	}

	private void deleteFixup(RedBlackTree<T> t, RBTNode<T> fixupNode) {
		RBTNode<T> sibling;
		while ((fixupNode != t.root) && (fixupNode.color == RBTNode.BLACK)) {
			if (fixupNode == fixupNode.parent.left) {
				sibling = fixupNode.parent.right;
				// sibling을 black으로 변경.
				if (sibling.color == RBTNode.RED) {
					sibling.color = RBTNode.BLACK;
					fixupNode.parent.color = RBTNode.RED;
					leftRotate(t, fixupNode.parent);
					sibling = fixupNode.parent.right;
				}
				// sibling을 red로 변경. fixupNode를 fixupNode의 parent로 변경.
				if (sibling.left.color == RBTNode.BLACK && sibling.right.color == RBTNode.BLACK) {
					sibling.color = RBTNode.RED;
					fixupNode = fixupNode.parent;
				} else {
					// sibling.left를 red에서 black으로 변경. sibling을 red로 변경.
					if (sibling.right.color == RBTNode.BLACK) {
						sibling.left.color = RBTNode.BLACK;
						sibling.color = RBTNode.RED;
						rightRotate(t, sibling);
						sibling = fixupNode.parent.right;
					}
					sibling.color = fixupNode.parent.color;
					fixupNode.parent.color = RBTNode.BLACK;
					sibling.right.color = RBTNode.BLACK;
					leftRotate(t, fixupNode.parent);
					fixupNode = t.root;
				}
			} else {
				sibling = fixupNode.parent.left;
				if (sibling.color == RBTNode.RED) {
					sibling.color = RBTNode.BLACK;
					fixupNode.parent.color = RBTNode.RED;
					rightRotate(t, fixupNode.parent);
					sibling = fixupNode.parent.left;
				}
				if (sibling.right.color == RBTNode.BLACK && sibling.left.color == RBTNode.BLACK) {
					sibling.color = RBTNode.RED;
					fixupNode = fixupNode.parent;
				} else {
					if (sibling.left.color == RBTNode.BLACK) {
						sibling.right.color = RBTNode.BLACK;
						sibling.color = RBTNode.RED;
						leftRotate(t, sibling);
						sibling = fixupNode.parent.left;
					}
					sibling.color = fixupNode.parent.color;
					fixupNode.parent.color = RBTNode.BLACK;
					sibling.left.color = RBTNode.BLACK;
					rightRotate(t, fixupNode.parent);
					fixupNode = t.root;
				}
			}
			fixupNode.color = RBTNode.BLACK;
		}
	}

	public RBTNode<T> treeMinimum(RBTNode<T> node) {
		while (node.left != nil) {
			node = node.left;
		}
		return node;
	}

	public void printTree(RBTNode<T> node) {
		if (node != null) {
			printTree(node.left);
			System.out.print(((node.color == RBTNode.RED) ? "Color: Red " : "Color: Black ") + "Key: " + node.key
					+ " Parent: " + node.parent.key + "\n");
			printTree(node.right);
		}
	}

	public static void main(String[] args) {
		RedBlackTree<Integer> tree = new RedBlackTree<>();
        RBTNode<Integer> node1 = new RBTNode<>(26);
        RBTNode<Integer> node2 = new RBTNode<>(17);
        RBTNode<Integer> node3 = new RBTNode<>(41);
        RedBlackTree<Integer> rbt = new RedBlackTree<>();
        rbt.insert(tree, node1);
        rbt.insert(tree, node2);
        rbt.insert(tree, node3);
        rbt.printTree(tree.root);
	}
}
