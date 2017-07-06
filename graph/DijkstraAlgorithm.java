package data_structure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

//���� Ŭ����
class Vertex {
	String name; // ���� �̸�
	int dist; // ���� �������κ��� �ش��������� �Ÿ�
	List<Edge> edges; // �ش� �������� ����� �ٸ� ������ ����ġ�� ���� edge��ü�� List
	Vertex prev; // ���� ����
	boolean visited; // �湮����

	public Vertex(String name) {
		this.name = name;
		dist = Integer.MAX_VALUE; // ���Ѵ�� ����
		edges = new LinkedList<Edge>();
		visited = false;
		prev = null;
	}

	// ���� �߰�
	public void addEdge(Vertex to, int weight) {
		edges.add(new Edge(to, weight));
	}

	public String getName() {
		return name;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

}

//���� Ŭ����
class Edge implements Comparable<Edge> {
	Vertex dest;
	int weight;

	public Edge(Vertex to, int weight) {
		this.dest = to;
		this.weight = weight;
	}

	public Vertex getDest() {
		return dest;
	}

	public void setDest(Vertex dest) {
		this.dest = dest;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	/*
	 * ����ġ�� ���ؼ� ������������ �����ϱ� ���ؼ� Comparable �������̽��� compareTo �޼ҵ� override
	 */
	@Override
	public int compareTo(Edge arg) {
		if (this.weight < arg.weight)
			return -1;
		else if (this.weight > arg.weight)
			return 1;
		return 0;
	}
}

public class DijkstraAlgorithm {
	Map<String, Vertex> graph;

	// �׷��� �ʱ�ȭ
	public DijkstraAlgorithm() {
		graph = new HashMap<>();
	}

	// �ش� ������ �׷����� �߰�
	public void addVertex(Vertex vertex) {
		String name = vertex.getName();
		graph.put(name, vertex);
	}

	/*
	 * �켱���� ť�� �̿��� ���ͽ�Ʈ�� �ִܰ�� �޼ҵ� Edge��ü�� weight�ʵ带 �������� ������������ queue�� ����
	 */
	public void dijkstra(String start) {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		Vertex startVertex = graph.get(start);
		startVertex.setDist(0); // ���� ���� 0���� �ʱ�ȭ
		queue.add(new Edge(startVertex, startVertex.getDist()));

		while (!queue.isEmpty()) {
			Edge e = queue.poll();
			Vertex v = e.dest;

			if (v.visited == true) // �湮�� �����̸� continue
				continue;

			v.visited = true;

			for (Edge edge : v.edges) {
				Vertex dest = edge.getDest();
				int weight = edge.getWeight();

				if (dest.getDist() > weight + v.getDist()) {
					dest.setDist(weight + v.getDist());
					dest.prev = v;
					queue.add(new Edge(dest, dest.getDist()));
				}
			}
		}

	}

	// ��� ���
	public void printRoute(Vertex destVertex) {
		// ��� �Լ��� ������ prev�� null�� �ƴҶ� ���� ȣ��
		if (destVertex.prev != null) {
			printRoute(destVertex.prev);
			System.out.print(" - > ");
		}
		System.out.print(destVertex.name);
	}

	public void printRoute(String destVertexName) {
		Vertex v = graph.get(destVertexName);
		if (v == null) {
			System.out.println("�߸��� �����Դϴ�.");
			return;
		} else if (v.dist == Integer.MAX_VALUE)
			System.out.println(destVertexName + " �� �� �� ���� ����Դϴ�.");
		else {
			System.out.printf("�ּ� ���: %d , ���: (", v.getDist());
			printRoute(v);
			System.out.println(")");
		}
	}

	public static void main(String[] args) {
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();

		Vertex nodeA = new Vertex("a");
		Vertex nodeB = new Vertex("b");
		Vertex nodeC = new Vertex("c");
		Vertex nodeD = new Vertex("d");
		Vertex nodeE = new Vertex("e");
		Vertex nodeF = new Vertex("f");

		nodeA.addEdge(nodeB, 10);
		nodeA.addEdge(nodeC, 15);

		nodeB.addEdge(nodeD, 12);
		nodeB.addEdge(nodeF, 15);

		nodeC.addEdge(nodeE, 10);

		nodeD.addEdge(nodeE, 2);
		nodeD.addEdge(nodeF, 1);

		nodeF.addEdge(nodeE, 5);

		dijkstra.addVertex(nodeA);
		dijkstra.addVertex(nodeB);
		dijkstra.addVertex(nodeC);
		dijkstra.addVertex(nodeD);
		dijkstra.addVertex(nodeE);
		dijkstra.addVertex(nodeF);

		dijkstra.dijkstra("a");
	
		Set<String> keySet = dijkstra.graph.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			dijkstra.printRoute(key);
			
		}

	}
}
