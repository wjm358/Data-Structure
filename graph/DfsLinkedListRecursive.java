package data_structure;

import java.util.ArrayList;
import java.util.Scanner;

//리스트로 DFS 구현, 재귀함수 사용
public class DfsLinkedListRecursive {
	private ArrayList<Integer>[] list;
	private boolean visited[];

	// 초기화 생성자
	public DfsLinkedListRecursive(int vertexNumber) {
		list = (ArrayList<Integer>[]) new ArrayList[vertexNumber];
		for (int i = 0; i < vertexNumber; i++) {
			list[i] = new ArrayList<Integer>();
		}
		visited = new boolean[vertexNumber];
	}

	// 정점 간에 간선 연결
	public void add(int vertex1, int vertex2) {
		list[vertex1].add(vertex2);
		list[vertex2].add(vertex1);
	}

	// DFS(깊이 우선 탐색) 구현
	public void DFS(int startVertex) {

		visited[startVertex] = true;
		System.out.printf("%d ", startVertex);

		for (int i = 0; i < list[startVertex].size(); i++) {
			if (visited[list[startVertex].get(i)] == false)
				DFS(list[startVertex].get(i));
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int vertexNumber;

		System.out.print("간선 수 입력 : ");
		vertexNumber = scanner.nextInt();
		System.out.println();

		DfsLinkedListRecursive dfs = new DfsLinkedListRecursive(vertexNumber);
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
