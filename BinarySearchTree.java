package jongmin;

public class BinarySearchTree {
 private Node root;// 루트 노드 인스턴스 생성

 // 이진 탐색 트리 생성자
 public BinarySearchTree() {
  root = null;
 }

 public boolean isEmpty() {
  return root == null;
 }

 public void clear() {
  root = null;
 }

 public void inOrder() {
  root.inOrder();
 }

 public void preOrder() {
  root.preOrder();
 }

 public void postOrder() {
  root.postOrder();
 }

 /**
  * 주어진 인터져 t 로 새로운 노드를 생성, 삽입한다
  * 
  * @param 삽입할 인터져 t
  * @return 삽입이 성공하면 true 갑을, 실패하면 false 값을 리턴
  */
 public boolean insert(int t) {
  Node newNode = new Node(t);// 주어진 인터져 t 로 새 노드를 생성
  Node pointer;// 노드를 삽입할 위치까지 트리를 순회해야하니, 포인터 인스턴스를 생성한다
  boolean insertComplete = false;
  if (root == null) {
   // 트리가 empty 상태일 경우, 루트 노드에 삽입
   root = newNode;
  } else {
   pointer = root;
   // while 루프로 순회를 시작한다.
   while (!insertComplete) {
    if (pointer.getData() > t) {
     // 인터져 t 를 각 노드들과 비교하여, 작으면 
     // 왼쪽 자식으로, 크면 오른쪽 자식으로 이동한다
     if (pointer.getLeft() != null) {
      pointer = pointer.getLeft();
     } else {
      pointer.setLeft(newNode);
      insertComplete = true;
      break;
      // 노드 삽입이 완료됬다면, 더이상의 루핑은 불필요하다.
      // break 구문을 이용, 루프를 종료하자
     }
    } else if (pointer.getData() < t) {
     if (pointer.getRight() != null) {
      pointer = pointer.getRight();
     } else {
      pointer.setRight(newNode);
      insertComplete = true;
      break;
     }
    } else {
     // 만약 트리에 이미 중복되는 노드가 존재한다면
     // 삽입은 불필요하다. 그러니 루핑을 종료한다
     break;
    }
   }
  }
  // 삽입이 성공적이라면 true 값을
  // 중복 노드가 존재한다거나, 예상외 상황이 있어서 삽입을 실패했다면 false 값을 반환한다.
  return insertComplete;
 }

 /**
  * 인터져 t 를 가진 노드를 트리에서 제거한다
  * 
  * @param t 제거할 값 인터져 t
  * @return 성공적으로 제거했을 때 true 값을, 실패했을 때 false 값을 리턴한다
  */
 public boolean remove(int t) {
  Node pointer, parentPointer;
  pointer = parentPointer = root;
  while (pointer != null && pointer.getData() != t) {
   // 삭제할 노드를 찾을 때까지 루핑으로 트리를 순회한다
   parentPointer = pointer;// parentPointer 인스턴스가 삭제할 노드의 부모 노드를 참조하도록 한다.
   if (pointer.getData() > t) {
    pointer = pointer.getLeft();
   } else {
    pointer = pointer.getRight();
   }
  }

  if (pointer == null) {
   // 첫번째 상황: 트리에 삭제할 노드가 존재하지 않을때
   return false;
  } else {
   if (pointer == root && pointer.getLeft() == null) {
    // 두번째 상황: 제거할 노드가 루트 노드이고, 루트노드가 왼쪽 자식 노드를 가지지 않을 때
    root = root.getRight();
   } else if (pointer != root && pointer.getLeft() == null) {
    // 세번째 상황: 루트 노드가 아닌 제거할 노드가 왼쪽 자식 노드를 가지지 않을 때
    if (pointer == parentPointer.getLeft()) {
     // 제거할 노드가 부모 노드의 왼쪽에 위치할 때
     parentPointer.setLeft(pointer.getRight());
    } else {
     // 제거할 노드가 부모 노드의 오른쪽에 위치할 때
     parentPointer.setRight(pointer.getRight());
    }
   } else {
    // 네번째 상황: 제거할 노드가 2개의 자식 노드 모두를 가지고 있을 때
    // 이러한 상황에서 우리는 제거할 노드에서 파생된 서브 트리에서 가장 큰 노드를 검색한 후, 
    // 제거할 노드의 위치에 삽입할 것 이다.
    // BST에서 가장 오른쪽에 위치한 노드는 가장 큰 값을 가졌다는걸 기억하자.
    // 그러니 BST의 왼쪽 서브 트리의 가장 오른쪽에 존재하는 잎(Leaf)노드는 루트 노드의 가장 좋은 대체제 이다.
    Node rightMostNode = pointer;
    // while 루핑을 이용해서 가장 오른쪽 노드로 이동하자
    while (rightMostNode.getRight() != null) {
     rightMostNode = rightMostNode.getRight();
    }
    // 제거할 노드의 데이터 값을 가장 오른쪽 노드의 데이터 값으로 바꿔주자
    pointer.setData(rightMostNode.getData());
    rightMostNode = null;// 오른쪽 노드를 null로 만든다
   }
  }
  return true;
 }

 /**
  * 인터져 t 값을 가진 노드를 검색한다
  * 
  * @param t 검색할 인터져 t
  * @return 존재한다면 true 값을, 그렇지 않다면 false 값을 반환한다.
  */
 public boolean serach(int t) {
  Node pointer;
  if (root.getData() == t) {
   // 루트 노드가 값을 가지고 있는지 확인
   return true;
  } else {
   // 값을 찾을 때 까지 트리를 순회하자
   pointer = root;
   while (pointer != null) {
    if (pointer.getData() > t) {
     pointer = pointer.getLeft();
    } else if (pointer.getData() < t) {
     pointer = pointer.getRight();
    } else {
     // 인터져 t가 노드와 비교 했을 때 크지도 작지도 않다면, 이는 값이 일치함을 말한다. 
     // 루핑을 종료한다
     break;
    }
   }
  }
  // 만약 트리에 인터져 t 값을 가진 노드가 존재하지 않는다면
  // 위의 while 루프에서 트리의 끝까지 여행하게 될거고, 결국 pointer 인스턴스는 null 값을 참조하게 될 것 이다
  // 하지만 트리에 t값을 가진 노드가 존재한다면, pointer 인스턴스는 그 노드를 참조할 것 이다.
  // 그렇니 pointer 인스턴스가 null 값을 가지지 않는다면, 트리에 t 값을 가진 노드가 존재한다고 볼 수 있다.
  return pointer != null;
 }
}