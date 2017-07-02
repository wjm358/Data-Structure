package data_structure;

//Kruskal의 최소 비용 트리 구현
public class KruskalMST {
	Edge[] heapEdge;
	int edgeNumber;
	int heapSize;
	int[] cycleCheck;

	// 정점의 수와 간선의 수를 입력받아 생성자 호출
	public KruskalMST(int edgeNumber, int vertexNumber) {
		heapEdge = new Edge[vertexNumber];
		this.edgeNumber = edgeNumber;
		heapSize = 0;
	}

	// 사이클 생성여부를 확인하기 위한 cycle 배열 초기화
	public void initCycleCheck() {
		cycleCheck = new int[edgeNumber];
		for (int i = 0; i < edgeNumber; i++)
			cycleCheck[i] = -1;
	}

	
	public void kruskalMST() {
		initCycleCheck();
		int count = 0;
		while (count < edgeNumber - 1) {
			Edge temp = deleteEdge();
			int u1 = find(temp.v1);
			int u2 = find(temp.v2);
			if (u1 != u2) {
				System.out.printf("(%d,%d) %d \n", temp.v1, temp.v2, temp.weight);
				unionFind(u1, u2);
				count++;
			}
		}
	}

	// 가중치에 대해 최소 히프를 이용하여 Edge 객체 삽입
	public void insertEdge(int v1, int v2, int weight) {
		Edge newEdge = new Edge(v1, v2, weight);
		heapEdge[heapSize] = newEdge;

		int current = heapSize;
		int parent = getParent(current);
		while (current != 0 && getWeight(current) < getWeight(parent)) {
			swap(current, parent);
			current = parent;
			parent = getParent(current);
		}
		heapSize++;
	}

	// 최소 히프 출력
	public void printHeap() {
		for (int i = 0; i < heapSize; i++) {
			System.out.printf(heapEdge[i].weight + " ");
		}
		System.out.println();
	}

	// 가중치에 대해 최소 히프를 이용하여 가장 작은 가중치를 가진 Edge 객체를 반환
	public Edge deleteEdge() {
		Edge temp = heapEdge[0];
		heapEdge[0] = heapEdge[heapSize - 1];

		int start = 0;
		int end = 1;
		while (end < heapSize) {
			if (getWeight(end + 1) < getWeight(end))
				end++;
			if (getWeight(start) <= getWeight(end))
				break;
			swap(start, end);
			start = end;
			getChild(end);
		}
		heapSize--;
		return temp;
	}

	// 부모 노드의 인덱스 반환
	public int getParent(int index) {
		return (index - 1) / 2;
	}

	// 자식 노드의 인덱스 반환
	public int getChild(int index) {
		return index * 2 + 1;
	}

	// 해당 인덱스의 가중치 반환
	public int getWeight(int index) {
		return heapEdge[index].weight;
	}

	// 부모노드와 자식노드 교환
	public void swap(int current, int parent) {
		Edge temp = heapEdge[current];
		heapEdge[current] = heapEdge[parent];
		heapEdge[parent] = temp;
	}

	// 해당 정점이 소속되어 있는 집합에서 최상위 정점값을 반환
	public int find(int v) {
		while (cycleCheck[v] >= 0) {
			v = cycleCheck[v];
		}
		return v;

	}

	// 해당 정점이 소속 되어있는 집합 2개를 병합하는데 집합 크기가 더 작은 집합을 큰 집합에 병합한다.
	public void unionFind(int v1, int v2) {
		if (cycleCheck[v1] <= cycleCheck[v2]) {
			cycleCheck[v2] += cycleCheck[v1]; // 두 집합의 크기를 더함
			cycleCheck[v1] = v2; // 작은 집합의 최상위 정점이 큰 집합을 가르키게 한다
		} else {
			cycleCheck[v1] += cycleCheck[v2];
			cycleCheck[v2] = v1;
		}
	}

	public static void main(String[] args) {
		KruskalMST mst = new KruskalMST(7, 9);
		mst.insertEdge(0, 1, 29);
		mst.insertEdge(1, 2, 16);
		mst.insertEdge(2, 3, 12);
		mst.insertEdge(3, 4, 22);
		mst.insertEdge(4, 5, 27);
		mst.insertEdge(5, 0, 10);
		mst.insertEdge(6, 1, 15);
		mst.insertEdge(6, 3, 18);
		mst.insertEdge(6, 4, 25);

		mst.kruskalMST();
	}

	// 정점과 정점 사이의 가중치값을 가지는 Edge 클래스 구현
	private class Edge {
		int v1;
		int v2;
		int weight;

		public Edge(int v1, int v2, int weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}
	}
}
