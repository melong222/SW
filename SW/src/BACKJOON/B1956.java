package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1956 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int [][] dp = new int [N+1][N+1];
		
		for (int i = 1; i<=N; i++) {
			for (int j = 1; j<=N; j++) {
				dp[i][j] = 10001;
			}
		}
		
		for (int i = 1; i<=K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			dp[a][b] = c;
		}

		
		
		for (int k = 1; k<=N; k++) {
			
			for (int i = 1; i<=N; i++) {
				for (int j = 1; j<=N; j++) {		
						dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k][j]);						
				} 
			}	
		}
		
		int answer = Integer.MAX_VALUE;
		for (int i = 1; i<=N; i++) {
			for (int j = 1; j<=N; j++) {
				if (dp[i][j]!=10001 && dp[j][i] != 10001) answer = Math.min(answer, dp[i][j]+dp[j][i]);
			} 
		}	
		
		
		if (answer==Integer.MAX_VALUE) answer = -1;
		
		System.out.println(answer);
		
		
	}

}
