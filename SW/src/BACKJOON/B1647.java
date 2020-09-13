package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class B1647 {

	static int [] parent;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		//ArrayList<Route> rList = new ArrayList<Route>();
		Route [] rList = new Route[Q];
		for (int i = 0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());			
			//rList.add(new Route(a,b,c));
			rList[i] = new Route(a,b,c);
		}
		
		Arrays.sort(rList,0,Q, new Comparator<Route>() {
			@Override
			public int compare(Route a, Route b) {
				// TODO Auto-generated method stub
				if (a.c <= b.c) {
					return -1;
				} else {
					return 1;
				}				 
			}
		});
		 
		/*for (int i = 0; i<Q; i++) {
			System.out.println(rList[i].a + " " + rList[i].b + " " +rList[i].c);
		}*/
		parent = new int [N+1];		 
		for (int i = 1; i<=N; i++) {
			parent[i] = i;
		}

		int answer = 0;
		int count = 0;
		if (N>2) {
		for (int i = 0; i<Q; i++) {
			//int a = rList.get(i).a;
			//int b = rList.get(i).b;
			//int c = rList.get(i).c;
			int x = rList[i].a;
			int y = rList[i].b;
			int z = rList[i].c;

			if (!isSameParent(x,y)) {
				union(x,y);
				count++;
				answer += z;
				/*for (int j = 1; j<=N; j++) {
					System.out.print(parent[j] + " ");
				}System.out.println();*/
			}
			if (count >= N-2) break;		 	
		}
		}
		
		System.out.println(answer);
	}

	private static void union(int a, int b) {
		// TODO Auto-generated method stub
		int x = find(a);
		int y = find(b);
		if (x!=y) {
			parent[y] = x;
		}
		/*if (x==y) return;
		
		if (x>y) parent[y] = x;
		else     parent[x] = y;*/
	}

	private static boolean isSameParent(int a, int b) {
		// TODO Auto-generated method stub
		int x = find(a);
		int y = find(b);
		if (x!=y) return false;
		else return true;
	}

	private static int find(int a) {
		// TODO Auto-generated method stub
		if (a == parent[a]) return a;
		else return parent[a] = find(parent[a]);
	}

}

class Route{
	int a;
	int b;
	int c;
	public Route(int a, int b, int c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
}