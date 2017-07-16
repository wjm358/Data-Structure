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

		//���� �� Ʈ�� ����
		root = root.rebalance(root);

		return root;
	}

	// �� ����
	public Node delete(int value, Node root) {
		if (root == null)
			return root;

		if (value < root.value) {
			root.left = delete(value, root.left);
		} else if (value > root.value) {
			root.right = delete(value, root.right);
		} else {
			//�ܸ� ��� �̰ų� ���� ��常 ������ ���
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
				//���� ��尡 ��� ������ ���
				Node temp = getSuccessor(root);
				root.value = temp.value;
				root.left = delete(temp.value, root.left);
			}
			
		}
		
		if(root != null)
			root= root.rebalance(root);

		return root;

	}

	
	// �����Ϸ��� ����� ���ʳ���� ���� �����ʳ�带 Ž�� 
	public Node getSuccessor(Node deleteNode) {
		Node succ = deleteNode.left;
		while (succ.right != null) 
			succ = succ.right;
		return succ;
	}

	// �� Ž��
	public boolean search(int key) {
		Node tempRoot = root;
		while (tempRoot != null) {
			if (key == tempRoot.value) {
				System.out.println(key + " Ž�� ����");
				return true;
			}
			
			if (key < tempRoot.value)
				tempRoot = tempRoot.left;
			else
				tempRoot = tempRoot.right;
		}
		System.out.println(key + "Ž�� ����");
		return false;
	}

	// Ʈ�� ���
	public void printTree() {
		root.inOrder(root);
		System.out.println();
	}

	// LL ȸ��
	public Node LLRotation(Node parent) {
		Node child = parent.left;
		parent.left = child.right;
		child.right = parent;
		return child;
	}

	// RR ȸ��
	public Node RRRotation(Node parent) {
		Node child = parent.right;
		parent.right = child.left;
		child.left = parent;
		return child;
	}

	// LR ȸ��
	public Node LRRotation(Node parent) {
		Node child = parent.left;
		parent.left = RRRotation(child);
		return LLRotation(parent);
	}

	// RL ȸ��
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

		// ���� ���ϱ�
		public int getHeight(Node node) {
			int height = 0;
			if (node != null) {
				height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
			}
			return height;
		}

		// ���� �μ� ���ϱ�
		public int getBalanceFactor(Node node) {
			if (node == null)
				return 0;
			return getHeight(node.left) - getHeight(node.right);
		}

		// Ʈ���� ����Ʈ���� ����
		public Node rebalance(Node node) {
			int height = getBalanceFactor(node);
			if (height > 1) {
				// �ڽ� ����� �����μ� ���� ���� LL�Ǵ� LR�� ȣ��
				if (getBalanceFactor(node.left) > 0) {
					System.out.println("LLȣ��");
					node = LLRotation(node);
				} else {
					System.out.println("LRȣ��");
					node = LRRotation(node);
				}
			} else if (height < -1) {
				// �ڽ� ����� �����μ� ���� ���� RR�Ǵ� RL�� ȣ��
				if (getBalanceFactor(node.right) < 0) {
					System.out.println("RRȣ��");
					node = RRRotation(node);
				} else {
					System.out.println("RLȣ��");
					node = RLRotation(node);
				}
			}

			return node;
		}

		// ���� ��ȸ
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
