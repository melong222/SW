package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11404 {

	static long dp [][];
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	    N = Integer.parseInt(br.readLine());
		int Q = Integer.parseInt(br.readLine());
		
		dp = new long [N+1][N+1];
		
		for (int i = 1; i<=N; i++) {
			for (int j = 1; j<=N; j++) {
				if (i==j) dp[j][i] = 0;
				else dp[j][i] = Integer.MAX_VALUE;
			}
		}


		for (int i = 1; i<=Q; i++) {			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());			
			if (dp[b][a] > c) dp[b][a] = c;	
		}
		

		for (int k = 1; k<=N; k++) {
			
			for (int i = 1; i<=N; i++) {
				for (int j = 1; j<=N; j++) {
					
					if (i!=j) {
						//dp[j][i] = Math.min(dp[j][i], dp[j][k]+dp[k][i]);
						System.out.println("#"+j+ " "+i + " vs "+ j+ " "+k+ " "+ k + " " +i);	
						dp[j][i] = Math.min(dp[j][i], dp[j][k]+dp[k][i]);
						//print();
						//System.out.println();
					}
					
				}
				System.out.println("!!!!!!!!!!!!!!!!!");
			}
			System.out.println("####################");
		}

		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i<=N; i++) {
			for (int j = 1; j<=N; j++) {
				if (dp[j][i] == Integer.MAX_VALUE) dp[j][i] = 0;
				sb.append(dp[j][i]+ " ");
			}sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
		
		
	}

	private static void print() {
		// TODO Auto-generated method stub
		for (int i = 1; i<=N; i++) {
			for (int j = 1; j<=N; j++) {
				System.out.print(dp[j][i]+" ");
			}System.out.println();  
		}
	}

}
