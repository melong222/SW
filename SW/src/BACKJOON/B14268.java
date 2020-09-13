package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class B14268 {

	static int cal;
	static int rowCount;
	static int [] rank;
	static int [] child;
	static ArrayList [] childList;
	static int N;
	static int [] tree;
	static int [] lazy;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		childList = new ArrayList [N+1];
		
		for (int i = 1; i<=N; i++) {
			childList[i] = new ArrayList<Integer>();
			int parent = Integer.parseInt(st.nextToken());
			if (i >1) childList[parent].add(i); 
		}
		
		int count = 1;
		while (count < N*2) {
			count<<=1;
		}
		cal = count/2-1;
		
		rank = new int [N+1];
		child = new int [N+1];
		rowCount = 1;
		
		dfs(1);
		
		tree = new int [count];
		lazy = new int [count];
		
		for (int i = 0; i<Q; i++) {			
			st = new StringTokenizer(br.readLine()); 
			
			int cate = Integer.parseInt(st.nextToken());
			
			if (cate==1) {
				int index = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
								
				int left  = rank[index] - child[index] +cal;
				int right = rank[index] + cal;
				
				update(1, cal+1, count-1, left, right, value);
				
				
			} else {
				int index = Integer.parseInt(st.nextToken());
				long answer = sum(1, cal+1, count-1, rank[index]+cal, rank[index]+cal);
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
		return sum(node*2, start, mid, left, right) +  sum(node*2+1, mid+1, end, left, right);
		
	}



	private static void update(int node, int start, int end, int left, int right, int value) {
		// TODO Auto-generated method stub
		lazy_update(node, start, end);
		
		if (left > end || right < start) return;
		if (left <= start && right >= end) {
			tree[node] += (end - start + 1) * value;
			if (start != end) {
				lazy[node*2] += value;
				lazy[node*2+1] += value;
			}
			return;
		}
		
		int mid = (start+end) / 2;
		update(node*2, start, mid, left, right, value);
		update(node*2+1, mid+1, end, left, right, value);		
		tree[node] = tree[node*2] + tree[node*2+1];
		
	}



	private static void lazy_update(int node, int start, int end) {
		// TODO Auto-generated method stub
		if (lazy[node] != 0) {
			tree[node] += (end - start +1) * lazy[node];
			if (start != end) {
				lazy[node*2] += lazy[node];
				lazy[node*2+1] += lazy[node];
			}
			lazy[node] = 0;
		}
		
	}



	private static int dfs(int node) {
		// TODO Auto-generated method stub
		if (childList[node].size() == 0) {
			child[node] = 0;
			rank[node] = rowCount;
			rowCount++;
			return 0;
		}
		
		int childCount = 0;
		for (int i = 0; i<childList[node].size(); i++) {
			childCount += dfs((int) childList[node].get(i));
			childCount++;
		}
		rank[node] = rowCount; rowCount++;
		child[node] = childCount;				
		return childCount;		
		 
		
	}

}

