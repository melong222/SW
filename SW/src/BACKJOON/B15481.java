package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class B15481 {

	static int N;
	static int Q;
	static int K;
	
	static ArrayList<Route> rlist;
	static ArrayList<Route> rlist2;
	
	static int [] root;
	static int [] rank;
	
	static int [][] parent;
	static int [][] high;
	static int [] depth;
	
	
	static ArrayList<Integer> [] nlist;
	static ArrayList<Integer> [] clist;
 
	static boolean [] visited;
	
	static int mst;
	static int templca;
	
	static int maxvalue;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken()); 
		
		rlist = new ArrayList<Route>();
		rlist2 = new ArrayList<Route>();
		
		for (int i = 1; i<=Q; i++) {
			st = new StringTokenizer(br.readLine());
			int s =Integer.parseInt(st.nextToken());
			int e =Integer.parseInt(st.nextToken());
			int c =Integer.parseInt(st.nextToken());
			rlist.add(new Route(s,e,c, false));
			rlist2.add(new Route(s,e,c, false));
		}
		
		Collections.sort(rlist, new Comparator<Route>() {

			@Override
			public int compare(Route a, Route b) {
				// TODO Auto-generated method stub
				if (a.c < b.c) {
					return -1;
				} else if (a.c> b.c) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		
		root = new int [N+1];
		rank   = new int [N+1];
		
		for (int i = 1; i<=N; i++) {
			root[i] = i;
		}
		
		mst = 0;
		int count = 0;

		for (int i = 0; i<rlist.size(); i++) {
			
			int x = rlist.get(i).s;
			int y = rlist.get(i).e;
			int z = rlist.get(i).c;
			
			if (!isSameParent(x,y)) {
				union(x,y);
				mst+=z;
				count++;
				rlist.get(i).flag = true;
			}
			if (count >= N-1) break; 
			
		}
		
		/*for (int i = 0; i<Q; i++) {
			if (rlist.get(i).flag) {
			System.out.println(rlist.get(i).s+ " "+rlist.get(i).e+ " "+rlist.get(i).c);
			}
		}*/
		
		// lca 시작
		
	    K = 0;
		int temp = 1;
		while (temp <= N) { 
			temp<<=1;
			K++;
		}
		
		depth = new int [N+1];
		visited = new boolean [N+1];
		
		parent = new int [N+1][K];
		high   = new int [N+1][K];
		
		
		nlist = new ArrayList [N+1];
		clist = new ArrayList [N+1];
				
		for (int i = 1; i<=N; i++) {
			nlist[i] = new ArrayList<Integer>();
			clist[i] = new ArrayList<Integer>();			
		}
		
		for (int i = 0; i<Q; i++) {
			int s = rlist.get(i).s;
			int e = rlist.get(i).e;
			int c = rlist.get(i).c;
			if (rlist.get(i).flag) {
			nlist[s].add(e);
			nlist[e].add(s);
			clist[s].add(c);
			clist[e].add(c);
			}
		}
		
						
		dfs(1, 0, 0);
		
		fillParent();
		
		//print();
		
		
		
		for (int i = 0; i<rlist2.size(); i++) {
			
			int x = rlist2.get(i).s;
			int y = rlist2.get(i).e;
			int z = rlist2.get(i).c;
			maxvalue = 0;
			getLCA(x,y);
						
			int answer = mst-maxvalue+z;
			
			//System.out.println(mst+ " "+ templca + " "+z);
			
			//System.out.println(mst+ " "+ templca + " "+z);
			
			System.out.println(answer);
			
			
		}
		
		
		
		
	}

	private static void getLCA(int a, int b) {
		// TODO Auto-generated method stub
		int rmax = Integer.MIN_VALUE;
		
		// 높이 비교						
		if (depth[a] < depth[b]) {
			int temp = b;
			b = a; 
			a = temp;
		}
		
		// 높이 맞추기
		for (int i = K-1; i>=0; i--){
			int diff = depth[a] - depth[b];
			if ((diff & (1<<i)) != 0) {
				rmax = Math.max(rmax, high[a][i]);
				a = parent[a][i];
			}
		}
		
		if (a==b) {
			maxvalue = rmax;
			return;
		}
		
		for (int i = K-1; i>=0; i--) {
			if (parent[a][i] != parent[b][i]) {
				rmax = Math.max(high[b][i],Math.max(rmax, high[a][i]));
				a = parent[a][i];
				b = parent[b][i];
			} 
		}
		maxvalue = Math.max(high[b][0],Math.max(rmax, high[a][0]));
		
		return;

	}


	private static void fillParent() {
		// TODO Auto-generated method stub
		for (int i = 1; i<K; i++) {
			for (int j = 1; j<=N; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
				high  [j][i] = Math.max(high[j][i-1], high[parent[j][i-1]][i-1]);
			}
		}
		
	}


	private static void print() {
		// TODO Auto-generated method stub
		for (int i = 1; i<=N; i++) {
			System.out.print(parent[i][0]+ " ");
		}System.out.println();
		for (int i = 1; i<=N; i++) {
			System.out.print(depth[i]+ " ");
		}System.out.println();
		
		System.out.println("#######################");
		for (int i = 1; i<=N; i++) {
			System.out.print(high[i][0]+ " ");
		}System.out.println();
		for (int i = 1; i<=N; i++) {
			System.out.print(high[i][1]+ " ");
		}System.out.println();
		for (int i = 1; i<=N; i++) {
			System.out.print(high[i][2]+ " ");
		}System.out.println();
		System.out.println("************************");
		for (int i = 1; i<=N; i++) {
			System.out.print(parent[i][0]+ " ");
		}System.out.println();
		for (int i = 1; i<=N; i++) {
			System.out.print(parent[i][1]+ " ");
		}System.out.println();
		for (int i = 1; i<=N; i++) {
			System.out.print(parent[i][2]+ " ");
		}System.out.println();
	}


	private static void dfs(int node, int dep, int hig) {
		// TODO Auto-generated method stub
		depth[node] = dep;
		visited[node] = true;
		
		for (int i = 0; i<nlist[node].size(); i++) {
			
			int x = nlist[node].get(i);
			
			if (!visited[x]) {				
				parent[x][0] = node;
				//high[x] = Math.max(hig, clist[node].get(i));			
				high[x][0] = clist[node].get(i);		
				dfs(x, dep+1,high[x][0]);
			}
			
		}
		
		
	}


	private static void union(int x, int y) {
		// TODO Auto-generated method stub
		if (rank[x] < rank[y]) {
			root[x] = y;
		} else if (rank[x] > rank[y]) {
			root[y] = x;
		} else {
			root[y] = x;
			rank[x]++;
		}
		
		
	}


	private static boolean isSameParent(int x, int y) {
		// TODO Auto-generated method stub
		int a = find(x);
		int b = find(y);
		if (a==b) return true;
		else	return false;
	}


	private static int find(int x) {
		// TODO Auto-generated method stub
		if (x == root[x]) return x;
		else return root[x] = find(root[x]);
	}


	static class Route{
		int s;
		int e;
		int c;
		boolean flag;
		public Route(int s, int e, int c, boolean flag) {
			super();
			this.s = s;
			this.e = e;
			this.c = c;
			this.flag = flag;
		}
		
	}
	
}
