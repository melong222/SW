package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


public class B1922 { 

	static int [] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int R = Integer.parseInt(br.readLine());
		
		//Computer [] cList = new Computer[R];
		ArrayList<Computer> cList = new ArrayList<Computer>();
		
		
		for (int i = 0; i<R; i++ ) {			
			StringTokenizer st = new StringTokenizer(br.readLine());						
			//cList[i] = new Computer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			cList.add(new Computer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		/*Arrays.sort(cList, 0, R, new Comparator<Computer>() {

			public int compare(Computer a, Computer b) {
				// TODO Auto-generated method stub
				if (a.cost <= b.cost) {
					return -1;
				} else {
					return 1;
				}
			}

		});*/
		Collections.sort(cList, new Comparator<Computer>() {

			@Override
			public int compare(Computer a, Computer b) {
				// TODO Auto-generated method stub
				if (a.cost <= b.cost) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		
		
		parent = new int [N+1];
		for (int i = 1; i<N+1; i++) {
			parent[i] = i;
		}
		
		int answer = 0;
		int count = 0;
		
		/*for (int i = 0; i<cList.length; i++) {
			System.out.println(cList[i].from + " " +  cList[i].to +  " " + cList[i].cost);
		} */
		
		for (int i = 0; i<cList.size(); i++) {
			
			int a = cList.get(i).from;
			int b = cList.get(i).to;
			int c = cList.get(i).cost; 
			
			if (!isSameParent(a,b)) {
				union(a,b);
				answer += c;
				count++;
			}
			
			if (count == N-1) break;
			
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
		else return find(parent[a]);

	}

}

class Computer{
	
	int from;
	int to;
	int cost;
	public Computer(int from, int to, int cost) {
		super();
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
	
	
}
