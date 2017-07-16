package data_structure;

public class AVLTree {
	Node root;

	public AVLTree() {
		root = null;
	}

	public Node insert(int value, Node root) {
		if (root == null)
			return (new Node(value));
		else if (value < root.value)
			root.left = insert(value, root.left);
		else if (value > root.value)
			root.right = insert(value, root.right);

		//삽입 후 트리 조정
		root = root.rebalance(root);

		return root;
	}

	// 값 삭제
	public Node delete(int value, Node root) {
		if (root == null)
			return root;

		if (value < root.value) {
			root.left = delete(value, root.left);
		} else if (value > root.value) {
			root.right = delete(value, root.right);
		} else {
			//단말 노드 이거나 한쪽 노드만 존재할 경우
			if (root.left == null || root.right == null) {
				Node temp = null; 
				if (root.left == temp)
					temp = root.right;
				else
					temp = root.left;

				if (temp == null) {
					temp = root;
					root = null;
				} else
					root = temp;
			} else {
				//양쪽 노드가 모두 존재할 경우
				Node temp = getSuccessor(root);
				root.value = temp.value;
				root.left = delete(temp.value, root.left);
			}
			
		}
		
		if(root != null)
			root= root.rebalance(root);

		return root;

	}

	
	// 삭제하려는 노드의 왼쪽노드의 가장 오른쪽노드를 탐색 
	public Node getSuccessor(Node deleteNode) {
		Node succ = deleteNode.left;
		while (succ.right != null) 
			succ = succ.right;
		return succ;
	}

	// 값 탐색
	public boolean search(int key) {
		Node tempRoot = root;
		while (tempRoot != null) {
			if (key == tempRoot.value) {
				System.out.println(key + " 탐색 성공");
				return true;
			}
			
			if (key < tempRoot.value)
				tempRoot = tempRoot.left;
			else
				tempRoot = tempRoot.right;
		}
		System.out.println(key + "탐색 실패");
		return false;
	}

	// 트리 출력
	public void printTree() {
		root.inOrder(root);
		System.out.println();
	}

	// LL 회전
	public Node LLRotation(Node parent) {
		Node child = parent.left;
		parent.left = child.right;
		child.right = parent;
		return child;
	}

	// RR 회전
	public Node RRRotation(Node parent) {
		Node child = parent.right;
		parent.right = child.left;
		child.left = parent;
		return child;
	}

	// LR 회전
	public Node LRRotation(Node parent) {
		Node child = parent.left;
		parent.left = RRRotation(child);
		return LLRotation(parent);
	}

	// RL 회전
	public Node RLRotation(Node parent) {
		Node child = parent.right;
		parent.right = LLRotation(child);
		return RRRotation(parent);
	}

	public static void main(String[] args) {
		AVLTree avl = new AVLTree();

		 /* The constructed AVL Tree would be
		        9
		       /  \
		      1    10
		     /  \    \
		     0    5    11
		     /    /  \
		     -1   2    6
		*/
		avl.root = avl.insert(9, avl.root);
		avl.root = avl.insert(5, avl.root);
		avl.root = avl.insert(10, avl.root);
		avl.root = avl.insert(0, avl.root);
		avl.root = avl.insert(6, avl.root);
		avl.root = avl.insert(11, avl.root);
		avl.root = avl.insert(-1, avl.root);
		avl.root = avl.insert(1, avl.root);
		avl.root = avl.insert(2, avl.root);
		avl.printTree();
		
		/* The AVL Tree after deletion of 10
		        1
		       /  \
		      0    9
		     /     / \
		     -1    5   11
			     /  \
			     2    6
		 */
		avl.root = avl.delete(10, avl.root);
		avl.printTree();
		
		avl.search(2);
	}

	class Node {
		int value;
		Node left;
		Node right;

		public Node(int value) {
			this.value = value;
			left = null;
			right = null;
		}

		// 높이 구하기
		public int getHeight(Node node) {
			int height = 0;
			if (node != null) {
				height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
			}
			return height;
		}

		// 균형 인수 구하기
		public int getBalanceFactor(Node node) {
			if (node == null)
				return 0;
			return getHeight(node.left) - getHeight(node.right);
		}

		// 트리를 균형트리로 만듬
		public Node rebalance(Node node) {
			int height = getBalanceFactor(node);
			if (height > 1) {
				// 자식 노드의 균형인수 값에 따라 LL또는 LR을 호출
				if (getBalanceFactor(node.left) > 0) {
					System.out.println("LL호출");
					node = LLRotation(node);
				} else {
					System.out.println("LR호출");
					node = LRRotation(node);
				}
			} else if (height < -1) {
				// 자식 노드의 균형인수 값에 따라 RR또는 RL을 호출
				if (getBalanceFactor(node.right) < 0) {
					System.out.println("RR호출");
					node = RRRotation(node);
				} else {
					System.out.println("RL호출");
					node = RLRotation(node);
				}
			}

			return node;
		}

		// 중위 순회
		public void inOrder(Node root) {
			if (root == null)
				return;
			else {
				root.inOrder(root.left);
				System.out.print(root.value + " ");
				root.inOrder(root.right);
			}
		}
	}
}
