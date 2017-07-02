package data_structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

//Prim의 최소 비용 트리 구현
public class PrimMST {
	ArrayList<ArrayList<Edge>> edge;
	int edgeNumber;
	int vertexNumber;
	boolean visited[]; // 정점을 방문했는지 확인하기 위한 변수

	// 정점의 개수와 간선의 개수를 입력받아 생성자 호출
	public PrimMST(int vertexNumber, int edgeNumber) {
		edge = new ArrayList<>(vertexNumber);
		visited = new boolean[edgeNumber];
		this.edgeNumber = edgeNumber;
		this.vertexNumber = vertexNumber;
	}

	// ArrayList 초기화
	public void initEdge() {
		for (int i = 0; i < edgeNumber; i++) {
			edge.add(new ArrayList<>());
		}
	}

	/*
	 * 시작정점을 매개변수로 입력받는다
	 * 시작정점에 연결된 정점중에서 가장 작은 가중치를 가진 Edge객체를 우선순위 큐를 이용하여 삽입
	 * count가 정점보다 하나 작을 때까지 반복
	 */
	public void primMST(int startVertex) {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		int count = 0;

		// 시작정점의 리스트에 있는 Edge 객체들을 가져와 우선순위 큐에 삽입
		for (Edge e : edge.get(startVertex)) {
			queue.add(e);
		}

		/*
		 * 예를들어 0 -> 1 로 간다고 가정할 때 0은 from이고 1은 to 우선순위 큐에서 객체를 가져온 후 0을 true로
		 * 설정 사이클이 생기면 안되므로 조건문으로 생길시 continue로 다음 반복문 실행 그 다음오로 1에 있는 Edge 객체들을
		 * 하나씩 가져와서 --> (edge.get(e.to)) 정점 1에 연결되어 있고 방문된적이 없는 Edge객체를 큐에 삽입
		 */
		while (count < vertexNumber - 1) {
			Edge e = queue.poll();
			visited[e.from] = true;
			if (visited[e.from] == true && visited[e.to] == true) {
				continue;
			}

			System.out.print("e.v1: " + e.from + "     e.v2 : " + e.to + "     e.weight : " + e.weight);
			System.out.println();

			for (Edge tmp : edge.get(e.to)) {
				if (!visited[tmp.to])
					queue.add(tmp);
			}

			visited[e.to] = true;
			count++;
		}

	}

	// 무방향 그래프로 가정, 양쪽 정점의 ArrayList에 insert
	public void insert(int from, int to, int weight) {
		initEdge();
		edge.get(from).add(new Edge(from, to, weight));
		edge.get(to).add(new Edge(to, from, weight));
	}

	public static void main(String[] args) {
		PrimMST prim = new PrimMST(7, 9);

		prim.insert(0, 5, 9);
		prim.insert(5, 4, 8);
		prim.insert(4, 6, 7);
		prim.insert(6, 1, 6);
		prim.insert(4, 3, 15);
		prim.insert(3, 2, 20);
		prim.insert(0, 1, 10);
		prim.insert(1, 2, 12);
		prim.insert(3, 6, 18);

		prim.primMST(0);

	}

	/*
	 * 정점과 정점 사이의 가중치값을 가지는 Edge 클래스 구현, 오름차순으로 정렬하기 위해 Comparalbe 인터페이스를 구현
	 */
	private class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge arg0) {
			// TODO Auto-generated method stub
			if (this.weight > arg0.weight)
				return 1;
			else if (this.weight < arg0.weight)
				return -1;
			return 0;
		}

	}
}
