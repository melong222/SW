package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1766 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int [] inb = new int [N+1];
		ArrayList<Integer> [] nlist = new ArrayList [N+1];
		for (int i = 0; i<=N; i++) {
			nlist[i] = new ArrayList<Integer>();
		}
		
		
		for (int i = 0 ; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nlist[a].add(b);
			inb[b]++;
		}
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Compare());
		
		
		ArrayList<Integer> alist = new ArrayList<Integer>();
		
		for (int i = 1; i<=N; i++) {
			if (inb[i] == 0) {			
				q.add(i);
				//alist.add(i);	
			}
		}
				
		while (!q.isEmpty()) {
			
			int now = q.poll();
			alist.add(now);
			//System.out.println(now);
			
			for (int j = 0; j<nlist[now].size(); j++) {
				int next = nlist[now].get(j);
				//System.out.println(now + " " + next);
				inb[next]--;
				if (inb[next] == 0) {
					q.add(next);
					//alist.add(next); 
				} 
			}
			
		}
			
		
		for (int i = 0; i<alist.size(); i++) {
			System.out.print(alist.get(i)+ " ");
		}
		
	}
	
	static class Compare implements Comparator<Integer>{

		@Override
		public int compare(Integer a, Integer b) {
			// TODO Auto-generated method stub
			if (a  < b) {
				return -1;
			} else if (a > b) {
				return 1;
			} else {
				return 0;
			}
		}

		
		
	}
}
