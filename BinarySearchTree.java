package data_structure;

public class BinarySearchTree<T extends Comparable<T>> {
	public Node root;

	// �ʱ�ȭ ������
	public BinarySearchTree() {
		root = null;
	}

	// data�� ���� ��� ��ȯ
	public Node searchNode(T data) {
		Node temp = root;

		int compare = data.compareTo(temp.getData());
		while (temp != null) {
			if (compare < 0)
				temp.getLeft();
			else if (compare > 0)
				temp.getRight();
			else
				return temp;
		}
		return null;

	}

	// ��� ����
	public void insertNode(T data) {

		// ��Ʈ�� ������ ��
		if (root == null) {
			root = new Node(data);
			return;
		}

		// �����Ͱ� ��Ʈ���� ũ�� ���������� �̵�, ������ �������� �̵�
		Node tempRoot = root;
		Node parentNode = null;
		while (tempRoot != null) {
			parentNode = tempRoot;
			int compare = data.compareTo(tempRoot.getData());
			if (compare < 0)
				tempRoot = tempRoot.getLeft();
			else if (compare > 0)
				tempRoot = tempRoot.getRight();
			else
				return;
		}

		// �����Ͱ� ��Ʈ���� ũ�� �����ʿ� ����, ������ ���ʿ� ����
		Node newNode = new Node(data);
		if (data.compareTo(parentNode.getData()) < 0)
			parentNode.setLeft(newNode);
		else
			parentNode.setRight(newNode);
	}

	//��� ����
	public void deleteNode(T data) {
		boolean isLeft = false;
		Node current = root;
		Node parent = root;

		//current�� null�̸� root�� null�̹Ƿ� ����
		if (current == null)
			return;
		
		//��Ʈ�� null�� �ƴϰų� �����Ͱ� Ž������ ���� �� ����
		while ( data.compareTo(current.getData()) != 0) {
			//parent�� current�� �� ���
			parent = current;
			int compare = data.compareTo(current.getData());
			
			if (compare < 0) {
				//������ �������� �̵�
				current = current.getLeft();
				isLeft = true;
			} else {
				//ũ�� ���������� �̵�
				current = current.getRight();
				isLeft = false;
			}
		}
		
	
		//������� ���(current) �� ���ʰ� ������ ��尡 �Ѵ� ���� �� (�� �ܸ� ����� ��)
		if (current.getLeft() == null && current.getRight() == null) {
			//current�� root�϶�
			if (current == root)
				root = null;
			//parent�� ���ʿ� current�� ���� �� parent�� ������ null�� ����
			else if (parent.getLeft() == current)
				parent.setLeft(null);
			 //parent�� �����ʿ� current�� ���� �� parent�� �������� null�� ����
			else
				parent.setRight(null);
		} 
		//������� ���(current)�� ������ ��常 ���� ��
		else if (current.getLeft() == null) {
			//parent�� ������ ��带 current�� ������ ���� ����
			parent.setRight(current.getRight());
		} 
		//������� ���(current)�� ���� ��常 ���� ��
		else if (current.getRight() == null) {
			//parent�� ���� ��带 current�� ���� ���� ����
			parent.setLeft(current.getLeft());
		} 
		/*������� ���(current)�� ���� ���� ������ ��� �Ѵ� ������ ���� ��
		 * current�� ������ ����� ���� ���ʳ�带 current �ڸ��� �Űܾ��Ѵ�.
		 */
		else {

			Node succParent = current;
			Node succ = current.getRight();
			
			while (succ.getLeft() != null) {
				succParent = succ;
				succ = succ.getLeft();
			}

			
			if (current != succParent) {
				succParent.setLeft(succ.getRight());
				succ.setRight(current.getRight());
			}
			
			//current�� root�� 
			if (current == root)
				root = succ;
			
			//current�� parent�� ���� ����� ��
			else if (isLeft)
				parent.setLeft(succ);
			//current�� parent�� ������ ����� ��
			else
				parent.setRight(succ);
			
			//current�� ���� ��带 succ�� ���� ���� ����
			succ.setLeft(current.getLeft()); 
		}

	}

	   public Node getSuccessor(Node deleleNode){
	        Node successsor =null;
	        Node successsorParent =null;
	        Node current = deleleNode.getRight();
	        while(current!=null){
	            successsorParent = successsor;
	            successsor = current;
	            current = current.getLeft();
	        }
	        if(successsor!=deleleNode.getRight()){
	            successsorParent.setLeft(successsor.getRight());
	            successsor.setRight(deleleNode.getRight());
	        }
	        return successsor;
	    }

	// ��� ����
	public class Node {
		T data;
		Node left;
		Node right;

		Node(T data) {
			this.data = data;
			left = right = null;
		}

		// ���� ��ȸ
		public void preOrder(Node root) {
			if (root == null)
				return;
			else {
				root.preOrder(root.getLeft());
				System.out.println(root.getData());
				root.preOrder(root.getRight());
			}
		}

		// ���� ��ȸ
		public void inOrder(Node root) {
			if (root == null)
				return;
			else {
				System.out.println(root.getData());
				root.inOrder(root.getLeft());
				root.inOrder(root.getRight());
			}
		}

		// ���� ��ȸ
		public void postOrder(Node root) {
			if (root == null)
				return;
			else {
				root.postOrder(root.getLeft());
				root.postOrder(root.getRight());
				System.out.println(root.getData());
			}
		}

		// ���� ��ȸ
		public void levelOrder(Node root) {
			QueueFromList<Node> queue = new QueueFromList<Node>();

			if (root == null)
				return;
			else {
				queue.enQueue(root);
				while (!queue.isEmpty()) {
					Node temp = queue.deQueue();
					System.out.print(temp.getData() + " --> ");
					if (temp.getLeft() != null)
						queue.enQueue(temp.getLeft());
					if (temp.getRight() != null)
						queue.enQueue(temp.getRight());
				}
				System.out.println();
			}
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

	}
}
