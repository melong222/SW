package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class B11400 {

	static ArrayList<Integer> [] list;	
	static int [] index;
	static ArrayList<Edge> edgeList;	
	static int count;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		list = new ArrayList [N+1] ;
		for (int i = 0; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 1; i<=E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		count = 0;
		index = new int [N+1];
		edgeList = new ArrayList<Edge>();
		
		answer = 0; 
		for (int i = 1; i<=N; i++) {
			if (index[i] == 0) {
				dfs(i,0);
			}
		}

		Collections.sort(edgeList, new Comparator<Edge>() {

			@Override
			public int compare(Edge a, Edge b) {
				// TODO Auto-generated method stub
				if (a.x < b.x) {
					return -1;
				} else if (a.x > b.x) {
					return 1;
				} else {
					if (a.y < b.y) {
						return -1;
					} else if (a.y > b.y) {
						return 1;
					} else {
						return -1;
					}
				}
			}
			
		});
		
		
		System.out.println(answer);
		for (int i = 0; i<edgeList.size(); i++) {
			System.out.println(edgeList.get(i).x + " " + edgeList.get(i).y);
		}
		
		
	} 

	private static int dfs(int node, int parent) { 
		// TODO Auto-generated method stub
		index[node] = ++count;
		int ret = index[node];
		
		for (int i = 0; i<list[node].size(); i++) {
			
			// 내가 온 길을 제외 한다.
			if (list[node].get(i) == parent) continue;
			
			if (index[list[node].get(i)] == 0) {

				int low = dfs(list[node].get(i), node);
				ret = Math.min(ret, low);
				
				// 자식들이 갈 수 있는 노드 index 값의 최소 값이 나의 index 보다 크다면 단절선/단절점 아님.
				if (low > index[node]) { 

					edgeList.add(new Edge(Math.min(list[node].get(i), node), Math.max(node, list[node].get(i))));					
					answer++;
				}	
				
			} else {
				
				ret = Math.min(ret, index[list[node].get(i)]);
				
			}
			
		}

		return ret;
	}
	static class Edge {
		int x;
		int y;
		public Edge(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
  
}



