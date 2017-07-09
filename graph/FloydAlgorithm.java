package data_structure;

//�÷��̵� �ִܰ�� �˰��� 
public class FloydAlgorithm {
	int vertexNum;
	int graph[][];

	public FloydAlgorithm(int vertexNum) {
		this.vertexNum = vertexNum;
		graph = new int[vertexNum][vertexNum];
	}

	// �ڱ� �ڽ��� ����ų���� 0, �ƴϸ� ��� ���Ѵ�� ����
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

	// �ִܰ�� �޼ҵ�
	public void floyd() {

		for (int k = 0; k < vertexNum; k++) {
			for (int i = 0; i < vertexNum; i++) {
				if (k == i) // �������� ������� ���� ���
					continue;

				for (int j = 0; j < vertexNum; j++) {
					// �������� �������� �����ʰ� ������� �������� ���� ���� ��
					if (k != j && i != j) {
						// i���� ������ k�� ���� ��ΰ� ���Ѵ��̰ų� k���� j�� ���� ��ΰ� ���Ѵ��� ��� ����ϴ� �ǹ̰� ���⶧���� �����ؾ� �ϹǷ�
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
