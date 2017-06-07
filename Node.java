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
  * 현재 노드에서 중위 순회(inOrder Traversal)을 시작한다.
  */
 public void inOrder() {
  // 회귀 함수(Recursive Call)을 이용, 가장 왼쪽 노드로 이동한다.
  if (left != null) {
   left.preOrder();
  }
  // 그리고 노드를 읽는다.
  System.out.println(data);
  // 그 후 다시 회귀 함수(Recursive Call)을 이용 오른쪽 노드를 읽는다.
  if (right != null) {
   right.preOrder();
  }
 }

 /**
  * 현재 노드에서 전위 순회(PreOrder Traversal)을 시작한다
  */
 public void preOrder() {
  // 현재 노드를 읽는다
  System.out.println(data);
  // 그후 회귀 함수(Recursive Call)을 이용
  // 가장 왼쪽 잎(Leaf)노드로 이동.
  if (left != null) {
   left.preOrder();
  }
  // 그 후 오른쪽 노드를 탐색한다.
  if (right != null) {
   right.preOrder();
  }
 }

 /**
  * 현재 노드에서 후위 순회(PostOrder Traversal)을 시작한다
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
