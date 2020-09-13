package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B6549 {

	static int [] list;
	static int [] tree;
	static int cal;
	
	static int N;
	
	static int count;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		list = new int [N+1];
		
		for (int i = 1; i<=N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		list[0] = Integer.MAX_VALUE;

		int length = 1;
		while (length < N*2) { 
			length<<=1;
		}
		
		cal = length/2-1;
		tree = new int [length];
		
		makeInit(1, cal+1, length-1);
		
		/*System.out.println("###########");
		for (int i = 1; i<length; i++) {
			System.out.print(tree[i]+ " ");
		}*/
		
		count = 0;
		
		long answer = getMax(1, cal+1, length-1, cal+1,cal+N);
		
		System.out.println(answer);
		
		
		
	}


	private static long getMax(int node, int start, int end, int left, int right) {
		// TODO Auto-generated method stub
		//System.out.println("@@@@"+start + " "+ end + " "+ left + " "+ right);		
				
		int idx = find(node, start, end, left, right);

		long size = (right-left+1)*list[idx];
		
		//System.out.println("!!!!"+(idx+cal) + " "+ (right-left+1)+ " " +size);
		
		if (left == right ) return size;
		//if (idx <= 0 || idx+cal >= end) return 0; 	
		
		int tempL = 0;
		if (idx+cal-1 < left) tempL = left;
		else tempL = idx+cal-1;
		long ltemp = getMax(node, start, end, left, tempL);
		
		int tempR = 0;
		if (idx+cal+1 > right) tempR = right;
		else tempR = idx+cal+1;
		
		long rtemp = getMax(node, start, end, tempR, right);
		

		
		size = Math.max(size,Math.max(ltemp, rtemp));  
				
		return size;
	} 


	private static int find(int node, int start, int end, int left, int right) {
		// TODO Auto-generated method stub
			
		if (left > end || right < start) return 0;		
		if (left <= start && right >= end) return tree[node];
		
		int mid = (start+end)/2;
		
		int leftIdx = find(node*2, start, mid, left, right);
		int rightIdx = find(node*2+1, mid+1, end, left, right);
		
		//System.out.println("****"+start + " "+ end + " "+ left + " "+ right + "****"+ leftIdx+ " " + rightIdx);	
		
		if (leftIdx == 0) return rightIdx;
		if (rightIdx == 0) return leftIdx;
		
		if (list[leftIdx] <= list[rightIdx]) {
			return leftIdx;
		}
		else {
			return rightIdx;
		}
	
		
		
		
	}


	private static void makeInit(int node, int start, int end) {
		// TODO Auto-generated method stub
		
		if (start == end ) {
			if (node-cal > N) {
				return;
			}
			tree[node] = node-cal;
			return;
		}
		
		int mid = (start+end)/2;
		
		makeInit(node*2, start, mid);
		makeInit(node*2+1, mid+1, end);
		
		if (list[tree[node*2]] <= list[tree[node*2+1]]) {
			tree[node] = tree[node*2];
		} else {
			tree[node] = tree[node*2+1];
		}
		
		
	}

}
