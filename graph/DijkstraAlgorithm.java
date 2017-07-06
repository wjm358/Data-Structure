package data_structure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

//정점 클래스
class Vertex {
	String name; // 정점 이름
	int dist; // 시작 정점으로부터 해당정점간의 거리
	List<Edge> edges; // 해당 정점에서 연결된 다른 정점과 가중치를 가진 edge객체의 List
	Vertex prev; // 이전 정점
	boolean visited; // 방문여부

	public Vertex(String name) {
		this.name = name;
		dist = Integer.MAX_VALUE; // 무한대로 설정
		edges = new LinkedList<Edge>();
		visited = false;
		prev = null;
	}

	// 간선 추가
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

//간선 클래스
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
	 * 가중치에 대해서 오름차순으로 정렬하기 위해서 Comparable 인터페이스의 compareTo 메소드 override
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

	// 그래프 초기화
	public DijkstraAlgorithm() {
		graph = new HashMap<>();
	}

	// 해당 정점을 그래프에 추가
	public void addVertex(Vertex vertex) {
		String name = vertex.getName();
		graph.put(name, vertex);
	}

	/*
	 * 우선순위 큐를 이용한 다익스트라 최단경로 메소드 Edge객체의 weight필드를 기준으로 오름차순으로 queue에 삽입
	 */
	public void dijkstra(String start) {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		Vertex startVertex = graph.get(start);
		startVertex.setDist(0); // 시작 정점 0으로 초기화
		queue.add(new Edge(startVertex, startVertex.getDist()));

		while (!queue.isEmpty()) {
			Edge e = queue.poll();
			Vertex v = e.dest;

			if (v.visited == true) // 방문된 정점이면 continue
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

	// 경로 출력
	public void printRoute(Vertex destVertex) {
		// 재귀 함수로 정점의 prev가 null이 아닐때 까지 호출
		if (destVertex.prev != null) {
			printRoute(destVertex.prev);
			System.out.print(" - > ");
		}
		System.out.print(destVertex.name);
	}

	public void printRoute(String destVertexName) {
		Vertex v = graph.get(destVertexName);
		if (v == null) {
			System.out.println("잘못된 정점입니다.");
			return;
		} else if (v.dist == Integer.MAX_VALUE)
			System.out.println(destVertexName + " 는 갈 수 없는 경로입니다.");
		else {
			System.out.printf("최소 비용: %d , 경로: (", v.getDist());
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
