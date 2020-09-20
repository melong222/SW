package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1613 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int [][] dp = new int [N+1][N+1];
		
		for (int i = 1; i<=K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dp[a][b] = -1;
			dp[b][a] = 1;
		}
	
		
		for (int k = 1; k<=N; k++) {
			
			for (int i = 1; i<=N; i++) {
				for (int j = 1; j<=N; j++) {
					if (i!=j) {
						if (dp[i][j] == 0 ) {
							if (dp[i][k] == -1 && dp[k][j] == -1) dp[i][j] = -1; 
							if (dp[i][k] ==  1 && dp[k][j] ==  1) dp[i][j] =  1; 
						}
					}
				}
			}
						
		}
		//print(dp); 
		
		int S = Integer.parseInt(br.readLine());
		
		for (int i = 1; i<=S; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = dp[a][b];
			int answer = 0;
			if (t > 0) answer = 1;
			else if (t<0) answer = -1;
			else answer = 0;
			System.out.println(answer);
		}
		
		
	}

	private static void print(int[][] dp) {
		// TODO Auto-generated method stub
		System.out.println("dddd");
		for (int i = 1; i<=5; i++) {
			for (int j = 1; j<=5; j++) {
				System.out.print(dp[i][j] + " ");
			}System.out.println();
		}
	}

}
