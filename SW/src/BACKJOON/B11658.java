package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11658 {

	static int N;
	static int [][] penwick;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer	st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		penwick = new int [N+1][N+1];
		
		for (int j = 1; j<=N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i<=N; i++) {
				int x = Integer.parseInt(st.nextToken());
				update(i,j,x);
			}
		}
		
		//print();
		
		for (int i = 1; i<=Q; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			if (c==1) {
				int y1 = Integer.parseInt(st.nextToken());
				int x1 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int answer = sum(x2,y2) + sum(x1-1, y1-1)- (sum(x1-1,y2)+sum(x2,y1-1));
				System.out.println(answer);
				
			} else {
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());				
				int temp = sum(x,y) + sum(x-1, y-1)- (sum(x,y-1)+sum(x-1,y));

				update(x,y,z-temp);
				//print();				
			}
		}
		
	}

	private static int sum(int x, int y) {
		// TODO Auto-generated method stub
		int sum = 0;
		for (int j = y; j>0; j-=(j&-j)) {
			for (int i =x;  i>0; i-=(i&-i)) {
				sum += penwick[i][j];
			}
		}
		return sum;
	}

	private static void update(int x, int y, int z) {
		// TODO Auto-generated method stub
		for (int j = y; j<=N; j+=(j&-j)) {
			for (int i = x; i<=N; i+=(i&-i)) {
				penwick[i][j] += z;
			}
		}
	}

	private static void print() {
		// TODO Auto-generated method stub
		for (int j = 1; j<=N; j++) {
			for (int i = 1; i<=N; i++) {
				System.out.print(penwick[i][j] + " ");
			}System.out.println();
		}
		System.out.println("#################"); 
	}

	

}
