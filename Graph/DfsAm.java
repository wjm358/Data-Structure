package data_structure;

import java.util.Scanner;
import java.util.Stack;

//인접행렬로 DFS 구현
public class DfsAm {
	private int graph[][];
	private boolean visited[];
	private int vertexNumber;
	private Stack<Integer> stack;
	
	// 초기화 생성자
	public DfsAm(int vertexNumber) {
		graph = new int[vertexNumber][vertexNumber];
		visited = new boolean[vertexNumber];
		this.vertexNumber = vertexNumber;
		stack = new Stack<Integer>();
	}

	// 정점 간에 간선 연결
	public void add(int vertex1, int vertex2) {
		graph[vertex1][vertex2] = 1;
		graph[vertex2][vertex1] = 1;
	}

	/* DFS(깊이 우선 탐색)를 구현하는 메소드
	 * 스택 클래스 or 재귀 함수 둘 중 하나를 사용하여 구현하면 됨
	 	 */
	public void DFS(int startVertex) {
		stack.push(startVertex);
		
		//스택으로 구현
		while(!stack.isEmpty()) {
			int data = stack.pop();
			if(visited[data] == false)
			{
				visited[data]=true;
				System.out.printf("%d -> " , data);
				System.out.println();
				for(int i=vertexNumber-1;i>=0;i--){
					if(graph[data][i] ==1 && visited[i] ==false)
						stack.push(i);
				}
			}
			
		}
		
		//재귀 함수로 구현		
		visited[startVertex] = true;
		System.out.printf("%d -> ", startVertex);

		for (int i = 0; i < vertexNumber; i++) {
			if (graph[startVertex][i] == 1 && visited[i] == false) {
				DFS(i);
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int vertexNumber;

		System.out.print("간선 수 입력 : ");
		vertexNumber = scanner.nextInt();
		System.out.println();

		DfsAm dfs = new DfsAm(vertexNumber);
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
