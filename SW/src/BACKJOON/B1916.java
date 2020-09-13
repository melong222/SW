package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1916 {

	static int N;
	static int R;
	
	static ArrayList<Integer> [] nlist;
	static ArrayList<Integer> [] clist;
	
	static int [] alist;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		R = Integer.parseInt(br.readLine());
		
		nlist = new ArrayList [N+1];
		clist = new ArrayList [N+1];
				
		for (int i = 1; i<=N; i++) {
			nlist[i] = new ArrayList<Integer>();
			clist[i] = new ArrayList<Integer>();			
		}
		
		alist = new int [N+1];
		
		for (int i = 1; i<=R; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nlist[a].add(b);
			//nlist[b].add(a);
			clist[a].add(c);
			//clist[b].add(c);			
		}
		
		Arrays.fill(alist, 1, N+1, Integer.MAX_VALUE);
				
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Compare());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end   = Integer.parseInt(st.nextToken());
		
		alist[start] = 0;
		q.add(start);
		
				
		while (!q.isEmpty()) {
			
			int t = q.poll();
			
			for (int i = 0; i<nlist[t].size(); i++) {
				
				int x = nlist[t].get(i);
				
				if (alist[x] > alist[t] + clist[t].get(i)) {
					alist[x] = alist[t] + clist[t].get(i);
					q.add(x);
				}

			}	

		}		
		
		long answer = 0;
		
		if (alist[end] == Integer.MAX_VALUE) {
			answer = -1;
		} else {
			answer = alist[end];
		}
		System.out.println(answer);
		 
		
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
