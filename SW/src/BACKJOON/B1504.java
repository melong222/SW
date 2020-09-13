package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1504 {

	static int N;
	static int Q;
	
	static final int INF = 20000000;
	
	static ArrayList<Integer> [] nlist;
	static ArrayList<Integer> [] clist;
	
	static int [] alist;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		nlist = new ArrayList[N+1];
		clist = new ArrayList[N+1];
		
		for (int i = 1; i<=N; i++) {
			nlist[i] = new ArrayList<Integer>();
			clist[i] = new ArrayList<Integer>();
		}
		
		for (int i = 1; i<=Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nlist[a].add(b);
			nlist[b].add(a);
			clist[a].add(c);
			clist[b].add(c);			
		}
						
		st = new StringTokenizer(br.readLine());
		
		int ess1 = Integer.parseInt(st.nextToken());
		int ess2 = Integer.parseInt(st.nextToken());
		long result1 = 0;
		long result2 = 0;
						
		result1 = daikstra(1,ess1)+ daikstra(ess1,ess2)+ daikstra(ess2,N);
		result2 = daikstra(1,ess2)+ daikstra(ess2,ess1)+ daikstra(ess1,N);
		
		long answer = Math.min(result1, result2);
		if (answer >= INF) {
			answer = -1;
		}
							
		System.out.println(answer);
		
	}
	
	private static int daikstra(int start, int end) {
		// TODO Auto-generated method stub
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Compare());
		
		alist = new int [N+1];
		
		Arrays.fill(alist, INF);
		
		q.add(start);
		alist[start] = 0;
				
		while (!q.isEmpty()) { 
			
			int t = q.poll();
			
			for (int i = 0; i<nlist[t].size(); i++) {
				
				int x = nlist[t].get(i);
				
				if (alist[t] + clist[t].get(i) <= alist[x]) {
					alist[x] = alist[t] + clist[t].get(i);
					q.add(x);
				}	
			}		
						
		}
					
		return alist[end];		
		
	}

	static class Compare implements Comparator<Integer>{

		@Override
		public int compare(Integer a, Integer b) {
			// TODO Auto-generated method stub
			if (alist[a] < alist[b]) {
				return -1;
			} else if (alist[a] > alist[b]) {
				return 1;
			} else {
				return 0;
			}

		}
		
	}

}
