package BACKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B3653 {
	
	static int cal;
	
	static int N;
	static int M;
	
	static int [] tree;
	static int [] index;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			int size = 1;
			while (size < (N+M)*2) {
				size<<=1;
			}
			cal = size/2-1;
			
			tree = new int [size];
			init(1, size/2, size-1);
			
			index = new int [N+1];
			
			for (int i = 1; i<=N; i++) {
				index[i] = i+M;
			}
			
			StringBuilder sb = new StringBuilder();
			int answer = 0;			
			
			st = new StringTokenizer(br.readLine());
		
			for (int i = 1; i<=M; i++) {
				int x = Integer.parseInt(st.nextToken());
				
				answer = sum(1, size/2, size-1, size/2, index[x]+cal-1);
				sb.append(answer + " ");
				
				update(index[x]+cal, -1);
				index[x] =(M-i+1); 
				update(index[x]+cal, 1);
				//for (int p = 1; p<size; p++) { 
				//	System.out.print(tree[p] + " "); 
				//}System.out.println();
			}
			
			bw.write(sb.toString()+"\n");
			bw.flush();
			
		}
			bw.close();
	}

	private static void init(int node, int start, int end) {
		// TODO Auto-generated method stub
		//System.out.println(node+" "+start+ " "+end);
		if (start == end) {
			if (node-M > cal && start - cal <= (N+M)) {
				tree[node] = 1;
			}
			return;
		}
		
		int mid = (start+end)/2;		
		init(node*2, start, mid);
		init(node*2+1, mid+1, end);
		
		tree[node] = tree[node*2]+tree[node*2+1];
		
	}

	private static void update(int node, int value) {
		// TODO Auto-generated method stub
		while (node >= 1) {
			tree[node] += value;
			node /=2;
		}
	}

	private static int sum(int node, int start, int end, int left, int right) {
		// TODO Auto-generated method stub
		if (left > end || right < start ) return 0;
		if (left <= start && right >= end ) return tree[node];
		
		int mid   = (start+end)/2;
		return sum(node*2, start, mid, left, right)+sum(node*2+1, mid+1, end, left, right);
	}

}
