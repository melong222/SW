package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1389 {

	static int N;
	
	static ArrayList<Integer> [] nlist;
	static PriorityQueue<Node> answer;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		nlist = new ArrayList [N+1];
		
		for (int i = 1; i<=N; i++) {
			nlist[i] = new ArrayList<Integer>();
		}
		
		for (int i = 1; i<=Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nlist[a].add(b);
			nlist[b].add(a);
		}
		
		answer = new PriorityQueue<Node>();
		
		for (int i = 1; i<=N; i++) {			
			bfs(i);
		}
		/*for (int i = 1; i<=N; i++) {
			System.out.println("#" + answer.peek().n + " " + answer.peek().c);
			answer.poll();
		}*/
		
		System.out.println(answer.peek().n);
		
	}

	private static void bfs(int node) {
		// TODO Auto-generated method stub
		Queue<Node> q = new LinkedList<Node>();
		boolean [] visited = new boolean [N+1];
		
		q.add(new Node(node,0));
		visited[node] = true;
		int x = 0;
		
		while (!q.isEmpty()) {			
			Node t = q.poll();
			x+=t.c;
			//System.out.println("t :"+t.n+ " "+t.c); 
			for (int i = 0; i<nlist[t.n].size(); i++) {
				if (!visited[nlist[t.n].get(i)]) {
					q.add(new Node(nlist[t.n].get(i), t.c+1));
					visited[nlist[t.n].get(i)] = true;
				}
			}			
		}
		
		answer.add(new Node(node, x));
		
	}
	
	static class Node implements Comparable<Node> {
		int n;
		int c;
		public Node(int n, int c) {
			super();
			this.n = n;
			this.c = c;
		}
		@Override
		public int compareTo(Node a) {
			// TODO Auto-generated method stub
			if (this.c > a.c) {
				return 1;
			} else if (this.c < a.c) {
				return -1;
			} else {
				if (this.n > a.n) {
					return 1;
				} else if (this.n < a.n) {
					return -1;
				} else {
					return 0;
				}
			}
		}
		
	}

}
