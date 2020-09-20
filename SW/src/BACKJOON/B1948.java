package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1948 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
				
		ArrayList<Integer> [] nlist = new ArrayList [N+1];
		ArrayList<Integer> [] clist = new ArrayList [N+1];
		
		ArrayList<Integer> [] elist = new ArrayList [N+1];
		ArrayList<Integer> [] dlist = new ArrayList [N+1];
		
		for (int i = 1; i<=N; i++) {
			nlist[i] = new ArrayList<Integer>();
			clist[i] = new ArrayList<Integer>();
			
			elist[i] = new ArrayList<Integer>();
			dlist[i] = new ArrayList<Integer>();
		}
		
		int [] inb = new int [N+1];
		
		for (int i = 1; i<=M; i++) {			
	
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nlist[a].add(b);			
			clist[a].add(c);
			
			elist[b].add(a);
			dlist[b].add(c);
			inb[b]++;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end	  = Integer.parseInt(st.nextToken());
		
		int [] tlist = new int [N+1];
		
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(start);
		
		while (!q.isEmpty()) {
			
			int now = q.poll();		
			
			for (int i = 0; i<nlist[now].size(); i++) {
				
				int next = nlist[now].get(i);
				inb[next]--;
				
				if (tlist[next] < tlist[now]+clist[now].get(i)) {
					tlist[next] = tlist[now]+clist[now].get(i);					
				} 
				
				if (inb[next] == 0) q.add(next);
				 
			}
			
		}
		
		q = new LinkedList<Integer>();		
		q.add(end);
		int count = 0;
						
		boolean [] visited = new boolean [N+1];
		
		while(!q.isEmpty()) {
			
			int now = q.poll();
			//visited[now] = true;
			for (int i = 0; i<elist[now].size(); i++) {
				
				int next = elist[now].get(i);
				
				//System.out.println("#"+now+ " "+next + " " + tlist[now]+ " " + dlist[now].get(i) + " " + tlist[next] );
				if (tlist[now] - dlist[now].get(i) == tlist[next] ) {				
					count++;
					if (!visited[next]) q.add(next);
					visited[next] = true;
				}
			}
			
		}
		
		System.out.println(tlist[end]+ " "+count);
		
		
		
	}


}
