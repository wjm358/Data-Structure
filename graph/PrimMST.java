package data_structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

//Prim�� �ּ� ��� Ʈ�� ����
public class PrimMST {
	ArrayList<ArrayList<Edge>> edge;
	int edgeNumber;
	int vertexNumber;
	boolean visited[]; // ������ �湮�ߴ��� Ȯ���ϱ� ���� ����

	// ������ ������ ������ ������ �Է¹޾� ������ ȣ��
	public PrimMST(int vertexNumber, int edgeNumber) {
		edge = new ArrayList<>(vertexNumber);
		visited = new boolean[edgeNumber];
		this.edgeNumber = edgeNumber;
		this.vertexNumber = vertexNumber;
	}

	// ArrayList �ʱ�ȭ
	public void initEdge() {
		for (int i = 0; i < edgeNumber; i++) {
			edge.add(new ArrayList<>());
		}
	}

	/*
	 * ���������� �Ű������� �Է¹޴´�
	 * ���������� ����� �����߿��� ���� ���� ����ġ�� ���� Edge��ü�� �켱���� ť�� �̿��Ͽ� ����
	 * count�� �������� �ϳ� ���� ������ �ݺ�
	 */
	public void primMST(int startVertex) {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		int count = 0;

		// ���������� ����Ʈ�� �ִ� Edge ��ü���� ������ �켱���� ť�� ����
		for (Edge e : edge.get(startVertex)) {
			queue.add(e);
		}

		/*
		 * ������� 0 -> 1 �� ���ٰ� ������ �� 0�� from�̰� 1�� to �켱���� ť���� ��ü�� ������ �� 0�� true��
		 * ���� ����Ŭ�� ����� �ȵǹǷ� ���ǹ����� ����� continue�� ���� �ݺ��� ���� �� �������� 1�� �ִ� Edge ��ü����
		 * �ϳ��� �����ͼ� --> (edge.get(e.to)) ���� 1�� ����Ǿ� �ְ� �湮������ ���� Edge��ü�� ť�� ����
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

	// ������ �׷����� ����, ���� ������ ArrayList�� insert
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
	 * ������ ���� ������ ����ġ���� ������ Edge Ŭ���� ����, ������������ �����ϱ� ���� Comparalbe �������̽��� ����
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
