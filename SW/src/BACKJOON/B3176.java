package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class B3176 {
	
	static int N;
	static int K;
	
	static int minvalue;
	static int maxvalue;
	
	static int depth [];
	static boolean visited [];
	
	static int parent[][];
	static int minlist[][];
	static int maxlist[][];
	
	static ArrayList<Integer> [] alist;
	static ArrayList<Integer> [] clist;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		alist = new ArrayList [N+1];
		clist = new ArrayList [N+1];
		for (int i = 0; i<=N; i++) {
			alist[i] = new ArrayList<Integer>();
			clist[i] = new ArrayList<Integer>();			
		}
		
		for (int i = 0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());			
			alist[s].add(e);
			alist[e].add(s);
			clist[s].add(c);
			clist[e].add(c);
		}
		
		depth = new int [N+1];
		visited = new boolean [N+1];
		
		int t = 1;
		K=0;
		while (t<=N) {
			t<<=1;
			K++;
		} 
				
		parent = new int [N+1][K];
		minlist = new int [N+1][K];
		maxlist = new int [N+1][K];
		
		for (int i = 0; i<=N; i++) {
			minlist[i][0] = Integer.MAX_VALUE;
			maxlist[i][0] = 0;
		}
		
		dfs(1,0);
		
		//print();
		
		fillParent();
		
		int Q = Integer.parseInt(br.readLine());
		
		minvalue = 0;
		maxvalue = 0;

		for (int i= 0; i<Q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			getLCA(x,y);
			System.out.println(minvalue + " " + maxvalue);
			
		}
		
	} 

	private static void print() {
		// TODO Auto-generated method stub
		for (int i = 1; i<=N; i++) {
			System.out.print(depth[i] + " ");
		}System.out.println();
		for (int i = 1; i<=N; i++) {
			System.out.print(parent[i][0] + " ");
		}System.out.println();
		for (int i = 1; i<=N; i++) {
			System.out.print(minlist[i][0] + " ");
		}System.out.println();
		for (int i = 1; i<=N; i++) {
			System.out.print(maxlist[i][0] + " ");
		}System.out.println();
	}

	private static void getLCA(int a, int b) {
		// TODO Auto-generated method stub
		// 높이 비교
		int rmin = Integer.MAX_VALUE;
		int rmax = Integer.MIN_VALUE;
		
		if (depth[a] < depth[b]) {
			int temp = b;
			b = a;
			a = temp;
		}
		
		// 높이 맞추기
		for (int i = K-1; i>=0; i--) {
			int diff = depth[a] -depth[b];
			if ((diff & (1<<i)) != 0) {
				rmin = Math.min(rmin, minlist[a][i]);
				rmax = Math.max(rmax, maxlist[a][i]);
				a = parent[a][i];
			}
		}
		
		if (a==b) {
			minvalue = rmin;
			maxvalue = rmax;
			return;
		}
		
		// lca찾기
		for (int i = K-1; i>=0; i--) {
			if (parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
				rmin = Math.min(minlist[b][i],Math.min(rmin, minlist[a][i]));
				rmax = Math.max(maxlist[b][i],Math.max(rmax, maxlist[a][i]));
			}			
		}
		
		minvalue = Math.min(minlist[b][0],Math.min(rmin, minlist[a][0]));
		maxvalue = Math.max(maxlist[b][0],Math.max(rmax, maxlist[a][0]));
		
		
	}

	private static void fillParent() {
		// TODO Auto-generated method stub
		for (int i = 1; i<K; i++) {
			for (int j = 1; j<=N; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
				minlist[j][i] = Math.min(minlist[j][i-1], minlist[parent[j][i-1]][i-1]);
				maxlist[j][i] = Math.max(maxlist[j][i-1], maxlist[parent[j][i-1]][i-1]);
			}
		}
	}

	private static void dfs(int node, int dep) {
		// TODO Auto-generated method stub
		depth[node] = dep;
		visited[node] = true;
		
		for (int i = 0; i<alist[node].size(); i++) {
			int x = alist[node].get(i);
			if (!visited[x]) {
				parent[x][0] = node;
				minlist[x][0] = clist[node].get(i);
				maxlist[x][0] = clist[node].get(i);
				dfs(x,dep+1);
			}
		}
		
	}

	
	
}
