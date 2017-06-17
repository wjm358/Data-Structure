package data_structure;

public class BinarySearchTree<T extends Comparable<T>> {
	public Node root;

	// 초기화 생성자
	public BinarySearchTree() {
		root = null;
	}

	// data를 가진 노드 반환
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

	// 노드 삽입
	public void insertNode(T data) {

		// 루트가 공백일 때
		if (root == null) {
			root = new Node(data);
			return;
		}

		// 데이터가 루트보다 크면 오른쪽으로 이동, 작으면 왼쪽으로 이동
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

		// 데이터가 루트보다 크면 오른쪽에 삽입, 작으면 왼쪽에 삽입
		Node newNode = new Node(data);
		if (data.compareTo(parentNode.getData()) < 0)
			parentNode.setLeft(newNode);
		else
			parentNode.setRight(newNode);
	}

	//노드 삭제
	public void deleteNode(T data) {
		boolean isLeft = false;
		Node current = root;
		Node parent = root;

		//current가 null이면 root가 null이므로 종료
		if (current == null)
			return;
		
		//루트가 null이 아니거나 데이터가 탐색되지 않을 때 까지
		while ( data.compareTo(current.getData()) != 0) {
			//parent는 current의 전 노드
			parent = current;
			int compare = data.compareTo(current.getData());
			
			if (compare < 0) {
				//작으면 왼쪽으로 이동
				current = current.getLeft();
				isLeft = true;
			} else {
				//크면 오른쪽으로 이동
				current = current.getRight();
				isLeft = false;
			}
		}
		
	
		//지우려는 노드(current) 의 왼쪽과 오른쪽 노드가 둘다 없을 때 (즉 단말 노드일 때)
		if (current.getLeft() == null && current.getRight() == null) {
			//current가 root일때
			if (current == root)
				root = null;
			//parent의 왼쪽에 current가 있을 때 parent의 왼쪽을 null로 만듬
			else if (parent.getLeft() == current)
				parent.setLeft(null);
			 //parent의 오른쪽에 current가 있을 때 parent의 오른쪽을 null로 만듬
			else
				parent.setRight(null);
		} 
		//지우려는 노드(current)가 오른쪽 노드만 있을 때
		else if (current.getLeft() == null) {
			//parent의 오른쪽 노드를 current의 오른쪽 노드로 설정
			parent.setRight(current.getRight());
		} 
		//지우려는 노드(current)가 왼쪽 노드만 있을 때
		else if (current.getRight() == null) {
			//parent의 왼쪽 노드를 current의 왼쪽 노드로 설정
			parent.setLeft(current.getLeft());
		} 
		/*지우려는 노드(current)가 왼쪽 노드와 오른쪽 노드 둘다 가지고 있을 때
		 * current의 오른쪽 노드의 가장 왼쪽노드를 current 자리로 옮겨야한다.
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
			
			//current가 root면 
			if (current == root)
				root = succ;
			
			//current가 parent의 왼쪽 노드일 때
			else if (isLeft)
				parent.setLeft(succ);
			//current가 parent의 오른쪽 노드일 때
			else
				parent.setRight(succ);
			
			//current의 왼쪽 노드를 succ의 왼쪽 노드로 설정
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

	// 노드 구현
	public class Node {
		T data;
		Node left;
		Node right;

		Node(T data) {
			this.data = data;
			left = right = null;
		}

		// 전위 순회
		public void preOrder(Node root) {
			if (root == null)
				return;
			else {
				root.preOrder(root.getLeft());
				System.out.println(root.getData());
				root.preOrder(root.getRight());
			}
		}

		// 중위 순회
		public void inOrder(Node root) {
			if (root == null)
				return;
			else {
				System.out.println(root.getData());
				root.inOrder(root.getLeft());
				root.inOrder(root.getRight());
			}
		}

		// 후위 순회
		public void postOrder(Node root) {
			if (root == null)
				return;
			else {
				root.postOrder(root.getLeft());
				root.postOrder(root.getRight());
				System.out.println(root.getData());
			}
		}

		// 레벨 순회
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
