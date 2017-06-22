package data_structure;

import java.util.Scanner;

//������ķ� DFS ����, ����Լ� ���
public class DfsAmRecursive {
	private int graph[][];
	private boolean visited[];
	private int vertexNumber;

	// �ʱ�ȭ ������
	public DfsAmRecursive(int vertexNumber) {
		graph = new int[vertexNumber][vertexNumber];
		visited = new boolean[vertexNumber];
		this.vertexNumber = vertexNumber;
	}

	// ���� ���� ���� ����
	public void add(int vertex1, int vertex2) {
		graph[vertex1][vertex2] = 1;
		graph[vertex2][vertex1] = 1;
	}

	// DFS(���� �켱 Ž��) ����
	public void DFS(int startVertex) {

		// ��� �Լ��� ����
		visited[startVertex] = true;
		System.out.printf("%d ", startVertex);

		for (int i = 0; i < vertexNumber; i++) {
			if (graph[startVertex][i] == 1 && visited[i] == false) {
				DFS(i);
			}
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int vertexNumber;

		System.out.print("���� �� �Է� : ");
		vertexNumber = scanner.nextInt();
		System.out.println();

		DfsAmRecursive dfs = new DfsAmRecursive(vertexNumber);
		dfs.add(0, 1);
		dfs.add(0, 2);
		dfs.add(0, 4);
		dfs.add(1, 2);
		dfs.add(2, 3);
		dfs.add(2, 4);
		dfs.add(3, 4);

		dfs.DFS(0);
	}

}
