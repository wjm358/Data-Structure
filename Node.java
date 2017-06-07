package jongmin;

public class Node {
 private Node left, right;
 private int data;

 public Node(int data) {
  this.data = data;
  left = null;
  right = null;
 }

 /**
  * ���� ��忡�� ���� ��ȸ(inOrder Traversal)�� �����Ѵ�.
  */
 public void inOrder() {
  // ȸ�� �Լ�(Recursive Call)�� �̿�, ���� ���� ���� �̵��Ѵ�.
  if (left != null) {
   left.preOrder();
  }
  // �׸��� ��带 �д´�.
  System.out.println(data);
  // �� �� �ٽ� ȸ�� �Լ�(Recursive Call)�� �̿� ������ ��带 �д´�.
  if (right != null) {
   right.preOrder();
  }
 }

 /**
  * ���� ��忡�� ���� ��ȸ(PreOrder Traversal)�� �����Ѵ�
  */
 public void preOrder() {
  // ���� ��带 �д´�
  System.out.println(data);
  // ���� ȸ�� �Լ�(Recursive Call)�� �̿�
  // ���� ���� ��(Leaf)���� �̵�.
  if (left != null) {
   left.preOrder();
  }
  // �� �� ������ ��带 Ž���Ѵ�.
  if (right != null) {
   right.preOrder();
  }
 }

 /**
  * ���� ��忡�� ���� ��ȸ(PostOrder Traversal)�� �����Ѵ�
  */
 public void postOrder() {

  if (left != null) {
   left.postOrder();
  }

  if (right != null) {
   right.postOrder();
  }
  System.out.println(data);
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

 public int getData() {
  return data;
 }

 public void setData(int data) {
  this.data = data;
 }

}
