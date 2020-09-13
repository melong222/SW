package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10999 {

	static int N;
	static int Q;
	
	static int cal;
	
	static int list [];
	static long tree [];
	static long lazy [];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
		
		list = new int [N+1];
		
		for (int i = 1; i<=N; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		int size  = 1;
		while (size<N*2) {
			size<<=1;
		}
		cal = size/2-1;
		
		tree = new long [size];
		lazy = new long [size];
		
		makeInit(1, cal+1, size-1);
		
		
		
		for (int i = 0; i<Q; i++) {
			
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			if (g==1) {				
				int left  = Integer.parseInt(st.nextToken())+cal;
				int right = Integer.parseInt(st.nextToken())+cal;
				long value = Integer.parseInt(st.nextToken());	
				update(1, cal+1, size-1, left, right, value);			
			} else {
				int left  = Integer.parseInt(st.nextToken())+cal;
				int right = Integer.parseInt(st.nextToken())+cal;
				long answer = sum(1, cal+1, size-1, left, right);
				System.out.println(answer);
			}
			
			
		}
		
		
	}

	private static long sum(int node, int start, int end, int left, int right) {
		// TODO Auto-generated method stub
		lazy_update(node, start, end);
		
		if (left > end || right < start) return 0;
		if (left <= start && right >= end) return tree[node];
		
		int mid = (start+end)/2;
		return sum(node*2, start, mid, left, right) +sum(node*2+1, mid+1, end, left, right);

	}

	private static void print() {
		// TODO Auto-generated method stub
		for (int i = 1; i<=15; i++) {
		System.out.print(tree[i]+" ");
		}System.out.println();
		for (int i = 1; i<=15; i++) {
		System.out.print(lazy[i]+" ");
		}System.out.println();
		System.out.println("######################");
	}

	private static void update(int node, int start, int end, int left, int right, long value) {
		// TODO Auto-generated method stub
		
		lazy_update(node, start, end);
				
		if (left > end || right < start) return;
		
		if (left <= start && right >= end) {
			tree[node] += (end-start+1)*value;
			if (start != end) {
				lazy[node*2] += value;
				lazy[node*2+1] += value;
			}
			return;
		}
		
		int mid = (start+end)/2;
		update(node*2, start, mid, left, right, value);
		update(node*2+1, mid+1, end, left, right, value);
		
		tree[node] = tree[node*2]+tree[node*2+1];
		
	}

	private static void lazy_update(int node, int start, int end) {
		// TODO Auto-generated method stub
		if (lazy[node] != 0) {			
			tree[node] += (end-start+1)*lazy[node];
			if (start!=end) {
				lazy[node*2] += lazy[node];
				lazy[node*2+1] += lazy[node];
			}
			lazy[node] = 0;
		}
		
		
	}

	private static void makeInit(int node, int start, int end) {
		// TODO Auto-generated method stub
		if (start == end) {
			if (start - cal > N) {
				tree[node] = 0;
			} else {
				tree[node] = list[start-cal];
			}
			return;
		}
		int mid = (start+end)/2;
		makeInit(node*2, start, mid);
		makeInit(node*2+1, mid+1, end);
		
		tree[node] = tree[node*2]+tree[node*2+1];
	}

}
