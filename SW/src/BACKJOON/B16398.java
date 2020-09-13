package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class B16398 {
	
	static int [] parent;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		int N = Integer.parseInt(br.readLine());
		//int N = 1000;
		
		int [][] map = new int [N+1][N+1];
		for (int i = 1; i<=N; i++) {
			StringTokenizer	st = new StringTokenizer(br.readLine());
			for (int j = 1; j<=N; j++) {
				map[j][i] = Integer.parseInt(st.nextToken());
				//map[j][i] = 100000000-j;							
			}
		} 
		ArrayList<Route> rList = new ArrayList<Route>();
		
		for (int i = 1; i<=N; i++) {
			for (int j = 1; j<=N; j++) {
				if (i!=j) {
					rList.add(new Route(j, i, map[j][i]));
				}
			}
		}
		
		Collections.sort(rList, new Comparator<Route>() {
			@Override
			public int compare(Route a, Route b) {
				// TODO Auto-generated method stub
				if (a.cost <= b.cost) {
					return -1;
				} else {
					return 1;
				}

			}
		});
		
		/*for (int i = 0; i<rList.size(); i++) {
			System.out.println(rList.get(i).from + " " +rList.get(i).to + " " +rList.get(i).cost);
		}System.out.println("###########");*/
		parent = new int [N+1];
		int answer = 0;
		int count = 0;
		for (int i = 1; i<=N; i++) parent[i] = i;
		
		for (int i = 0; i<rList.size(); i++) {
			
			int a = rList.get(i).from;
			int b = rList.get(i).to;
			int c = rList.get(i).cost;
			
			if (!isSameParent(a,b)) {
				union(a,b);
				answer+= c;
				count++;		
				/*for (int j = 1; j<=N; j++) {
					System.out.print(parent[j] + " ");
				}System.out.println();*/
			} 
			if (count >= N-1) break;			
		}
		System.out.println(answer);
		
		
	}
	
	
	
	
private static void union(int a, int b) {
		// TODO Auto-generated method stub
		int x = find(a);
		int y = find(b);
		if (x==y) return;
		else if (x<y) parent[y] = x;
		else parent[x] = y;
	}




private static boolean isSameParent(int a, int b) {
		// TODO Auto-generated method stub
		int x = find(a);
		int y = find(b);
		if (x==y) return true;
		else	return false;
	}




private static int find(int a) {
	// TODO Auto-generated method stub
	if (a == parent[a]) return a;
	 parent[a] = find(parent[a]);
	 return parent[a];
}




static class Route{
	int	from;
	int to;
	int cost;
	public Route(int from, int to, int cost) {
		super();
		this.from = from;
		this.to = to;
		this.cost = cost;
	}	
	
}
	
	
}





