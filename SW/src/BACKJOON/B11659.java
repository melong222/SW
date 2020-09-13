package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11659 {

	static int [] penwick;
	static int N;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer	st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		penwick = new int [N+2];
		
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i<=N; i++) {
			int x = Integer.parseInt(st.nextToken());
			update(i,x);
		}
		
		for (int i = 1; i<=N; i++) {
			System.out.print(penwick[i] + " ");
		}
		
		for (int i = 1; i<=Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int answer = sum(y)-sum(x-1);
			//System.out.println(answer);
		}
		
		
	}

	private static int sum(int i) {
		// TODO Auto-generated method stub
		int sum = 0;
		while (i > 0) {
			sum+= penwick[i];
			i -= (i & -i);
		}
		return sum;
	}

	private static void update(int i, int x) {
		// TODO Auto-generated method stub
		while (i<=N) { 
			penwick[i] += x;
			i+= (i&-i);
			
			for (int p = 1; p<=N; p++) {
				System.out.print(penwick[p] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("###################");
		
		
		
	}

}
