package data_structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

//����Ʈ�� DFS ����, ���� ���
public class DfsLinkedListStack {
	private ArrayList<Integer>[] list;
	private boolean visited[];
	private Stack<Integer> stack; 

	// �ʱ�ȭ ������
	public DfsLinkedListStack(int vertexNumber) {
		list = (ArrayList<Integer>[]) new ArrayList[vertexNumber];
		for (int i = 0; i < vertexNumber; i++) {
			list[i] = new ArrayList<Integer>();
		}
		visited = new boolean[vertexNumber];
		stack = new Stack<Integer>();
	}

	// ���� ���� ���� ����
	public void add(int vertex1, int vertex2) {
		list[vertex1].add(vertex2);
		list[vertex2].add(vertex1);
	}

	// DFS(���� �켱 Ž��) ����
	public void DFS(int startVertex) {

		stack.push(startVertex);
		for (ArrayList<Integer> temp : list) {
			Collections.reverse(temp);
		}
		while (!stack.isEmpty()) {
			int data = stack.pop();
			if (visited[data] == false) {
				System.out.printf("%d ->", data);
				visited[data] = true;
				for (int temp : list[data]) {
					if (visited[temp] == false) {
						stack.push(temp);
					}
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int vertexNumber;

		System.out.print("���� �� �Է� : ");
		vertexNumber = scanner.nextInt();
		System.out.println();

		DfsLinkedListStack dfs = new DfsLinkedListStack(vertexNumber);
		dfs.add(0, 2);
		dfs.add(0, 1);
		dfs.add(0, 4);
		dfs.add(1, 2);
		dfs.add(2, 3);
		dfs.add(2, 4);
		dfs.add(3, 4);

		dfs.DFS(0);
	}
}
