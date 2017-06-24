package data_structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 리스트로 BFS 구현 , 큐 사용
public class BfsLinkedList {
	private ArrayList<Integer>[] list;
	private boolean visited[];
	private Queue<Integer> queue;

	// 초기화 생성자
	public BfsLinkedList(int vertexNumber) {
		list = new ArrayList[vertexNumber];
		for (int i = 0; i < vertexNumber; i++) {
			list[i] = new ArrayList<Integer>();
		}
		visited = new boolean[vertexNumber];
		queue = new LinkedList<Integer>();
	}

	// 정점 간에 간선 연결
	public void add(int vertex1, int vertex2) {
		list[vertex1].add(vertex2);
		list[vertex2].add(vertex1);
	}

	// BFS(너비 우선 탐색) 구현
	public void BFS(int startVertex) {
		queue.add(startVertex);
		while (!queue.isEmpty()) {
			int data = queue.poll();
			if (visited[data] == false) {
				visited[data] = true;
				System.out.printf("%d : ", data);
				for (int temp : list[data]) {
					if (visited[temp] == false)
						queue.add(temp);
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int vertexNumber;

		System.out.print("간선 수 입력 : ");
		vertexNumber = scanner.nextInt();
		System.out.println();

		BfsLinkedList bfs = new BfsLinkedList(vertexNumber);
		bfs.add(0, 1);
		bfs.add(0, 2);
		bfs.add(0, 4);
		bfs.add(1, 2);
		bfs.add(2, 3);
		bfs.add(2, 4);
		bfs.add(3, 4);

		bfs.BFS(1);

	}
}
