package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2056 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		ArrayList<Integer>[] nlist = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			nlist[i] = new ArrayList<Integer>();
		}

		int[] tlist = new int[N + 1];
		int[] alist = new int[N + 1];

		int[] inb = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			tlist[i] = t;
			int c = Integer.parseInt(st.nextToken());
			inb[i] = c;
			for (int j = 0; j < c; j++) {
				int x = Integer.parseInt(st.nextToken());
				nlist[x].add(i);
			}
		}

		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 1; i <= N; i++) {
			if (inb[i] == 0) {
				q.add(i);
				alist[i] = tlist[i];
			}
		}
		int cnt = 0;
		int answer = 0;
		while (!q.isEmpty()) {

			int now = q.poll();			

			for (int i = 0; i < nlist[now].size(); i++) {
				int next = nlist[now].get(i);
				
				if (alist[next] < alist[now] + tlist[next]) {
					alist[next] = alist[now] + tlist[next];
				}
				
				inb[next]--;
				if (inb[next] == 0) {
					q.add(next);					
				}
			}

		}

		
		
		 for (int i = 1; i<=N; i++) { System.out.print(alist[i]+ " ");
		 }System.out.println();
		 
		for (int i = 1; i<=N; i++) { 
			answer = Math.max(alist[i], answer);
		 }
		 

		System.out.println(answer);

	}

}
