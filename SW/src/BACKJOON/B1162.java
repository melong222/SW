package BACKJOON;    

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1162 {

	static int N;
	static int R;
	static int K;
	
	static ArrayList<Integer> [] nlist;
	static ArrayList<Integer> [] clist;
	
	static long alist [][]; 
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		nlist = new ArrayList [N+1];
		clist = new ArrayList [N+1];
		
		alist = new long [N+1][K+1];

		for (int j = 0; j<=K; j++) {
		for (int i = 1; i<=N; i++) {
			alist[i][j] = Long.MAX_VALUE;

		}
		}
		
		
		for (int i = 1; i<=N; i++) {
			nlist[i] = new ArrayList<Integer>();
			clist[i] = new ArrayList<Integer>();						
		}
		
		for (int i = 1; i<=R; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			nlist[s].add(e);
			nlist[e].add(s);
			clist[s].add(v);
			clist[e].add(v);
		}
		
		alist[1][0] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		pq.add(new Node(1,0,0));
		
		while (!pq.isEmpty()) {
			
	    Node t = pq.poll();
	       
	    if (alist[t.node][t.count] < t.cost) {
	    	continue;
	    }
			
	    for (int i = 0; i<nlist[t.node].size(); i++) {
	    	
	    	int x = nlist[t.node].get(i);
	    	long c = clist[t.node].get(i);

	    	// 도로 포장을 하는 경우
	    	if (t.count+1 <= K && alist[x][t.count+1] > alist[t.node][t.count]) {
	    		alist[x][t.count+1] = alist[t.node][t.count];
	    		pq.add(new Node(x,  alist[x][t.count+1], t.count+1));
	    		//System.out.println("##"+t.node+" "+ t.cost+ " "+t.count+" "+x);
	    	}
	    	
	    	// 도로 포장을 하지 않는 경우 
	    	if (alist[x][t.count] > alist[t.node][t.count]+c) {
	    		alist[x][t.count] = alist[t.node][t.count]+c;
	    		pq.add(new Node(x, alist[x][t.count],t.count));
	    		//System.out.println("###"+t.node+" "+ t.cost+ " "+t.count + " " +x);
	    	}
	    	
	    	
	    }
			
			 
		}
		
		long answer = Long.MAX_VALUE;
		for (int i= 0; i<=K; i++) {
			answer = Math.min(answer, alist[N][K]);
		}
		
		System.out.println(answer);
		
	}
	
	static class Node implements Comparable<Node>{

		int node;
		long cost;
		int count;
				
		public Node(int node, long cost, int count) {
			super();
			this.node = node;
			this.cost = cost;
			this.count = count;
		}

		@Override
		public int compareTo(Node a) {
			// TODO Auto-generated method stub
			if (this.cost < a.cost) {
				return -1;
			} else if (this.cost > a.cost) {
				return 1;
			} else {
				return 0;
			}
		}

		
 
		
		
	}

}
