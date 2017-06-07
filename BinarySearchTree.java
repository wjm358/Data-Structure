package jongmin;

public class BinarySearchTree {
 private Node root;// ��Ʈ ��� �ν��Ͻ� ����

 // ���� Ž�� Ʈ�� ������
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
  * �־��� ������ t �� ���ο� ��带 ����, �����Ѵ�
  * 
  * @param ������ ������ t
  * @return ������ �����ϸ� true ����, �����ϸ� false ���� ����
  */
 public boolean insert(int t) {
  Node newNode = new Node(t);// �־��� ������ t �� �� ��带 ����
  Node pointer;// ��带 ������ ��ġ���� Ʈ���� ��ȸ�ؾ��ϴ�, ������ �ν��Ͻ��� �����Ѵ�
  boolean insertComplete = false;
  if (root == null) {
   // Ʈ���� empty ������ ���, ��Ʈ ��忡 ����
   root = newNode;
  } else {
   pointer = root;
   // while ������ ��ȸ�� �����Ѵ�.
   while (!insertComplete) {
    if (pointer.getData() > t) {
     // ������ t �� �� ����� ���Ͽ�, ������ 
     // ���� �ڽ�����, ũ�� ������ �ڽ����� �̵��Ѵ�
     if (pointer.getLeft() != null) {
      pointer = pointer.getLeft();
     } else {
      pointer.setLeft(newNode);
      insertComplete = true;
      break;
      // ��� ������ �Ϸ��ٸ�, ���̻��� ������ ���ʿ��ϴ�.
      // break ������ �̿�, ������ ��������
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
     // ���� Ʈ���� �̹� �ߺ��Ǵ� ��尡 �����Ѵٸ�
     // ������ ���ʿ��ϴ�. �׷��� ������ �����Ѵ�
     break;
    }
   }
  }
  // ������ �������̶�� true ����
  // �ߺ� ��尡 �����Ѵٰų�, ����� ��Ȳ�� �־ ������ �����ߴٸ� false ���� ��ȯ�Ѵ�.
  return insertComplete;
 }

 /**
  * ������ t �� ���� ��带 Ʈ������ �����Ѵ�
  * 
  * @param t ������ �� ������ t
  * @return ���������� �������� �� true ����, �������� �� false ���� �����Ѵ�
  */
 public boolean remove(int t) {
  Node pointer, parentPointer;
  pointer = parentPointer = root;
  while (pointer != null && pointer.getData() != t) {
   // ������ ��带 ã�� ������ �������� Ʈ���� ��ȸ�Ѵ�
   parentPointer = pointer;// parentPointer �ν��Ͻ��� ������ ����� �θ� ��带 �����ϵ��� �Ѵ�.
   if (pointer.getData() > t) {
    pointer = pointer.getLeft();
   } else {
    pointer = pointer.getRight();
   }
  }

  if (pointer == null) {
   // ù��° ��Ȳ: Ʈ���� ������ ��尡 �������� ������
   return false;
  } else {
   if (pointer == root && pointer.getLeft() == null) {
    // �ι�° ��Ȳ: ������ ��尡 ��Ʈ ����̰�, ��Ʈ��尡 ���� �ڽ� ��带 ������ ���� ��
    root = root.getRight();
   } else if (pointer != root && pointer.getLeft() == null) {
    // ����° ��Ȳ: ��Ʈ ��尡 �ƴ� ������ ��尡 ���� �ڽ� ��带 ������ ���� ��
    if (pointer == parentPointer.getLeft()) {
     // ������ ��尡 �θ� ����� ���ʿ� ��ġ�� ��
     parentPointer.setLeft(pointer.getRight());
    } else {
     // ������ ��尡 �θ� ����� �����ʿ� ��ġ�� ��
     parentPointer.setRight(pointer.getRight());
    }
   } else {
    // �׹�° ��Ȳ: ������ ��尡 2���� �ڽ� ��� ��θ� ������ ���� ��
    // �̷��� ��Ȳ���� �츮�� ������ ��忡�� �Ļ��� ���� Ʈ������ ���� ū ��带 �˻��� ��, 
    // ������ ����� ��ġ�� ������ �� �̴�.
    // BST���� ���� �����ʿ� ��ġ�� ���� ���� ū ���� �����ٴ°� �������.
    // �׷��� BST�� ���� ���� Ʈ���� ���� �����ʿ� �����ϴ� ��(Leaf)���� ��Ʈ ����� ���� ���� ��ü�� �̴�.
    Node rightMostNode = pointer;
    // while ������ �̿��ؼ� ���� ������ ���� �̵�����
    while (rightMostNode.getRight() != null) {
     rightMostNode = rightMostNode.getRight();
    }
    // ������ ����� ������ ���� ���� ������ ����� ������ ������ �ٲ�����
    pointer.setData(rightMostNode.getData());
    rightMostNode = null;// ������ ��带 null�� �����
   }
  }
  return true;
 }

 /**
  * ������ t ���� ���� ��带 �˻��Ѵ�
  * 
  * @param t �˻��� ������ t
  * @return �����Ѵٸ� true ����, �׷��� �ʴٸ� false ���� ��ȯ�Ѵ�.
  */
 public boolean serach(int t) {
  Node pointer;
  if (root.getData() == t) {
   // ��Ʈ ��尡 ���� ������ �ִ��� Ȯ��
   return true;
  } else {
   // ���� ã�� �� ���� Ʈ���� ��ȸ����
   pointer = root;
   while (pointer != null) {
    if (pointer.getData() > t) {
     pointer = pointer.getLeft();
    } else if (pointer.getData() < t) {
     pointer = pointer.getRight();
    } else {
     // ������ t�� ���� �� ���� �� ũ���� ������ �ʴٸ�, �̴� ���� ��ġ���� ���Ѵ�. 
     // ������ �����Ѵ�
     break;
    }
   }
  }
  // ���� Ʈ���� ������ t ���� ���� ��尡 �������� �ʴ´ٸ�
  // ���� while �������� Ʈ���� ������ �����ϰ� �ɰŰ�, �ᱹ pointer �ν��Ͻ��� null ���� �����ϰ� �� �� �̴�
  // ������ Ʈ���� t���� ���� ��尡 �����Ѵٸ�, pointer �ν��Ͻ��� �� ��带 ������ �� �̴�.
  // �׷��� pointer �ν��Ͻ��� null ���� ������ �ʴ´ٸ�, Ʈ���� t ���� ���� ��尡 �����Ѵٰ� �� �� �ִ�.
  return pointer != null;
 }
}