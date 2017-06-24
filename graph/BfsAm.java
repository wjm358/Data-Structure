package data_structure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 인접행렬로 BFS 구현 , 큐 사용 
public class BfsAm {
	private int graph[][];
	private boolean visited[];
	private int vertexNumber;
	private Queue<Integer> queue;

	// 초기화 생성자
	public BfsAm(int vertexNumber) {
		this.vertexNumber = vertexNumber;
		visited = new boolean[vertexNumber];
		graph = new int[vertexNumber][vertexNumber];
		queue = new LinkedList<Integer>();
	}

	// 정점 간에 간선 연결
	public void add(int vertex1, int vertex2) {
		graph[vertex1][vertex2] = 1;
		graph[vertex2][vertex1] = 1;
	}

	// BFS(너비 우선 탐색) 구현
	public void BFS(int startVertex) {
		queue.add(startVertex);
		while (!queue.isEmpty()) {
			int data = queue.poll();
			if (visited[data] == false) {
				visited[data] = true;
				System.out.printf("%d : ", data);
				for (int i = 0; i < vertexNumber; i++) {
					if (graph[data][i] == 1 && visited[i] == false)
						queue.add(i);
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

		BfsAm bfs = new BfsAm(vertexNumber);
		bfs.add(0, 1);
		bfs.add(0, 2);
		bfs.add(0, 4);
		bfs.add(1, 2);
		bfs.add(2, 3);
		bfs.add(2, 4);
		bfs.add(3, 4);

		bfs.BFS(0);
	}
}
