package data_structure;

//플로이드 최단경로 알고리즘 
public class FloydAlgorithm {
	int vertexNum;
	int graph[][];

	public FloydAlgorithm(int vertexNum) {
		this.vertexNum = vertexNum;
		graph = new int[vertexNum][vertexNum];
	}

	// 자기 자신을 가르킬때는 0, 아니면 모두 무한대로 설정
	public void initGraph() {
		for (int i = 0; i < vertexNum; i++) {
			for (int j = 0; j < vertexNum; j++) {
				if (i != j)
					graph[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	public void printGraph() {
		for (int i = 0; i < vertexNum; i++) {
			for (int j = 0; j < vertexNum; j++) {
				if (graph[i][j] == Integer.MAX_VALUE) {
					System.out.print("INF ");
				} else
					System.out.printf("%d ", graph[i][j]);
			}
			System.out.println();
			System.out.println();
		}
	}

	// from -> to : weight
	public void insertValue(int from, int to, int weight) {
		graph[from][to] = weight;
	}

	// 최단경로 메소드
	public void floyd() {

		for (int k = 0; k < vertexNum; k++) {
			for (int i = 0; i < vertexNum; i++) {
				if (k == i) // 경유지와 출발점이 같을 경우
					continue;

				for (int j = 0; j < vertexNum; j++) {
					// 경유지와 도착점이 같지않고 출발점과 도착점이 같지 않을 때
					if (k != j && i != j) {
						// i에서 경유지 k로 가는 경로가 무한대이거나 k에서 j로 가는 경로가 무한대일 경우 계산하는 의미가 없기때문에 제외해야 하므로
						if (graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE) {
							if (graph[i][j] > graph[i][k] + graph[k][j])
								graph[i][j] = graph[i][k] + graph[k][j];
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		FloydAlgorithm flyod = new FloydAlgorithm(5);
		flyod.initGraph();
		flyod.insertValue(0, 1, 4);
		flyod.insertValue(0, 2, 1);
		flyod.insertValue(2, 1, 2);
		flyod.insertValue(2, 3, 4);
		flyod.insertValue(3, 4, 4);
		flyod.insertValue(1, 4, 4);

		flyod.printGraph();
		flyod.floyd();
		System.out.println();

		flyod.printGraph();

	}

}
