package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B7578 {

	static int cal;
	
	static int [] tree;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int [] alist = new int [N+1];
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());		
		for (int i = 1; i<=N; i++) {
			alist[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());		
		for (int i = 1; i<=N; i++) {
			 map.put(Integer.parseInt(st.nextToken()), i);
		}
		
		int size = 1;
		while (size <= N*2) {
			size<<=1;
		}
		cal = size/2-1;
		
		tree = new int [size];
		
		long answer = 0;
		
		for (int i = 1; i<=N; i++) {
			
			int a = alist[i];
			int bi = map.get(a);
			
			answer += sum(1, size/2, size-1, bi+cal, size-1);
			
			update(bi+cal, 1);
			
		}
		
		System.out.println(answer);
		
		
	}

	private static void update(int node, int value) {
		// TODO Auto-generated method stub
		while (node >= 1) {
			tree[node] += value;
			node /=2;
		}
	}

	private static long sum(int node, int start, int end, int left, int right) {
		// TODO Auto-generated method stub
		if (left > end || right < start) return 0;
		
		if (left <= start && right >= end) return tree[node];
		
		int mid = (start+end)/2;		
		return sum(node*2, start, mid, left, right)+sum(node*2+1, mid+1, end, left, right);
	}

}
