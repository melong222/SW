package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B9426 {

	static int [] tree;
	static int S;
	static int C;
	static int F;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer	st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int [] list = new int [N+1];
		
		F = (K+1)/2;
		
		S = 1;
		while (S <= 65535*2) {
			S<<=1;
		}
		C = S/2;
		
		tree = new int [S+1];
		
		for (int i = 1; i<K; i++) {
			int x = Integer.parseInt(br.readLine());
			list[i] = x;
			x += C;
			update(x,1);
			
			//print();
		}
		
		long answer = 0;
		for (int i = K; i<=N; i++) {
			int x = Integer.parseInt(br.readLine());
			list[i] = x;
			x += C;
			update(x,1);
			
			//print();
			
			long temp = get(F, 1, C, S-1)-C;
			//System.out.println(temp);
			answer+=temp;
			
			x = list[i-(K-1)];
			x+=C;
			update(x,-1);
			
		}
		
		System.out.println(answer);
		
	}


	private static long get(long value, int node, int start, int end) {
		// TODO Auto-generated method stub
		
		if (start == end) return start;
		int mid = (start+end)/2;
		if (value <= tree[node*2]) {
			//System.out.println("###"+value+" "+node+" "+start+" "+end);
			return get(value, node*2, start, mid);
		} else {
			//System.out.println("####"+value+" "+node+" "+start+" "+end);
			return get(value-tree[node*2], node*2+1, mid+1, end);
		}
	}







	private static void print() {
		// TODO Auto-generated method stub
		System.out.println();
		for (int i = 1; i<=S; i++) {
			System.out.print(tree[i]+ " ");
		}System.out.println();
		System.out.println("###############");
	}

	private static void update(int node, int value) {
		// TODO Auto-generated method stub
		while(node>=1) {
			tree[node]+=value;
			node/=2;
		}
	}

}
