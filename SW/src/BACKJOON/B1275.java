package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B1275 {

	static ArrayList<Integer> initList;
	static int [] tree;
	static int cal;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
			
		st = new StringTokenizer(br.readLine());
		
		initList = new ArrayList<Integer>();
		
		for (int i = 0; i<N; i++) {
			initList.add(Integer.parseInt(st.nextToken()));
		}
		
		int count = 1;
		while (count<N*2) {
			count<<=1;
		}
		cal = count/2-1;
		tree = new int [count];
		
		makeInit(1, cal+1, count-1);
		
/*		for (int i = 1; i<count; i++) {
			System.out.print(tree[i] + " ");
		}*/
		
		for (int i = 0; i<Q; i++) {
			
		st = new StringTokenizer(br.readLine());
		
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		int start = Math.min(s, e);
		int end   = Math.max(e, s);		
		
		long answer = sum(1, cal+1, count-1, start+cal, end+cal);
		System.out.println(answer);
		
		int org = initList.get(x-1);
		int value = v-org;
		
		//update(cal+x, value);
		
		update2(1, cal+1, count-1, cal+x, cal+x, value);
		
		/*for (int j = 1; j<=count-1; j++) {
			System.out.print(tree[j] + " ");
		}*/
	
		}
		
	}

	/*private static void update(int node, int value) {
		// TODO Auto-generated method stub
	
		while (node >= 1) {
			tree[node] += value;
			node /= 2;
		}		
		
	}*/

	private static void update2(int node, int start, int end, int left, int right, int value) {
		// TODO Auto-generated method stub
		
		if (left > end || right < start) return;
		
		if (start >= left && end <= right) {	
			//System.out.println("#"+node+ " "+ start + " " + end + " " +  left + " " + right);
			tree[node] += (end-start+1) * value;
			return;
		}
		
		int mid = (start+end)/2;	
		
		update2(node*2, start, mid, left, right, value);
		update2(node*2+1, mid+1, end, left, right, value);
		
		tree[node] = tree[node*2]+tree[node*2+1];
		
		
	}

	private static long sum(int node, int start, int end, int left, int right) {
		// TODO Auto-generated method stub
		if (left > end || right < start) return 0;
		if (left <= start && right >= end) return tree[node];

		int mid = (start+end)/2;		
		return sum(node*2, start, mid, left, right) + sum(node*2+1, mid+1, end, left, right);

	}

	private static void makeInit(int node, int start, int end) {
		// TODO Auto-generated method stub
				
		if (start == end) {
			if (start - cal <= N) {
				tree[start] = initList.get(start-cal-1);
			}
			return;
		}
		
		int mid = (start+end)/2;
		makeInit(node*2, start, mid);
		makeInit(node*2+1, mid+1, end);
	
		tree[node] = tree[node*2] + tree[node*2+1];
		
	}

}
