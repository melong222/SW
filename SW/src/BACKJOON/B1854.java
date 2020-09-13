package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1854 {

	static int N;
	static int R;
	static int K;
	
	static ArrayList<Integer> [] nlist;
	static ArrayList<Integer> [] clist;
	
	static PriorityQueue<Integer> [] alist;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		R = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		nlist = new ArrayList [N+1];
		clist = new ArrayList [N+1];
				
		for (int i = 1; i<=N; i++) {
			nlist[i] = new ArrayList<Integer>();
			clist[i] = new ArrayList<Integer>();			
		}
		
		for (int i = 1; i<=R; i++) {
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nlist[a].add(b);
			//nlist[b].add(a);
			clist[a].add(c);
			//clist[b].add(c);			
		}
		
		alist = new PriorityQueue [N+1];
		
		for (int i = 1; i<=N; i++) {
			alist[i] = new PriorityQueue<Integer>(new CompareK());
		}
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Compare());
		
		q.add(1);
		alist[1].add(0);
		
		while (!q.isEmpty()) {
			
			int t = q.poll();
			
			for (int i = 0; i<nlist[t].size(); i++) {
				
				int x = nlist[t].get(i);
									
				
				if (alist[x].size() < K) {
					alist[x].add(clist[t].get(i)+nlist[t].get(i));
				} else {
					
				}
				
			}
			
		}
		
		
		
		
	}
	
	static class Compare implements Comparator<Integer>{

		@Override
		public int compare(Integer a, Integer b) {
			// TODO Auto-generated method stub
			if (a < b) {
				return -1;
			} else if (a > b) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}
	
	static class CompareK implements Comparator<Integer>{

		@Override
		public int compare(Integer a, Integer b) {
			// TODO Auto-generated method stub
			if (a < b) {
				return -1;
			} else if (a > b) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}
	

}
