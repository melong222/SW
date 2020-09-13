package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B11438_cop {
	
	static ArrayList<Integer> [] list;
	
	static int [] depth;
	static int [][] parent;
	static boolean [] visited;
	
	static int N;
	static int K;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList [N+1];
		
		for (int i = 1; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 1; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		int temp = 1;
	    K = 0;
		while (temp <=N ) {
			temp<<=1;
			K++;			
		}
		
		
		depth  = new int [N+1];
		visited = new boolean [N+1];
		parent = new int [N+1][K];
		
		dfs(1,1);
				
		fillparent();  
		  
		int Q = Integer.parseInt(br.readLine());
		
		for (int i = 1; i<=Q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int answer = getLca(a,b);
			System.out.println(answer);
		}
		
		
	}

	private static int getLca(int a, int b) {
		// TODO Auto-generated method stub
		// a기준으로
		if (depth[a] < depth[b]) {
			int temp = b;
			b = a;
			a = temp;
		} 
		
		// 높이 맞추기
		for (int i = K-1; i>=0; i--) {
			int diff = depth[a] - depth[b];
			
			if ((diff&(1<<i)) !=0) {
				a = parent[a][i];
			}
		}
		
		// 같으면 return;		
		if (a==b) return a;
		
		for (int i = K-1; i>=0; i--) {	
			if (parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}	
		}				
		return parent[a][0];
	}

	private static void fillparent() {
		// TODO Auto-generated method stub
		for (int i = 1; i<K; i++) {
			for (int j = 1; j<=N; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
			}
		}
		
	}

	private static void dfs(int node, int dep) {
		// TODO Auto-generated method stub	
		visited[node] = true;
		depth[node] = dep;
		
		for (int i = 0; i<list[node].size(); i++) {	
			if (!visited[list[node].get(i)]) {
				dfs(list[node].get(i), dep+1);
				parent[list[node].get(i)][0] = node;
			}
		}
	}

}
