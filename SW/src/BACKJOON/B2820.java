package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B2820 {

	static Man [] mList;
	static int rowCount;
	
	static int N;
	static int cal;
	
	static int [] tree;
	static int [] lazy;
	
	static int [] rank;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int	Q = Integer.parseInt(st.nextToken());
		
		mList = new Man [N+1];
		for (int i = 1; i<=N; i++) {				
			mList[i] = new Man(0, 0, 0, new ArrayList<Integer>());		
		}
		
		for (int i = 1; i<=N; i++) {	
			st = new StringTokenizer(br.readLine());
			if (i==1) mList[i].money = Integer.parseInt(st.nextToken());
			else {
				mList[i].money = Integer.parseInt(st.nextToken());
				mList[Integer.parseInt(st.nextToken())].childList.add(i);
			}
		}
	
		dfs(1,0);
		
		rank = new int [N+1];
		
		for (int i = 1; i<=N; i++) {
			int x = mList[i].rank;
			rank[x] = i;
		}
				
		int count = 1;
		while (count < N*2) {
			count <<=1;
		}
		cal = count/2-1;
		
		tree = new int [count];
		lazy = new int [count];
		
		initTree(1, cal+1, count-1);
				
		for (int i = 0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			String category = st.nextToken();
			if (category.equals("p")) {
				int index = Integer.parseInt(st.nextToken());
				int money = Integer.parseInt(st.nextToken());
				
				int left  = mList[index].rank-mList[index].child+cal;
				int right = mList[index].rank + cal-1;
				
				//if (left > right ) return;
				
				//System.out.println(left + "  " +  right);
				update(1, cal+1, count-1, left, right, money);
				
			} else {
				int index = Integer.parseInt(st.nextToken());
				
				int left  = mList[index].rank-mList[index].child+cal;
				int right = mList[index].rank + cal; 
				
				int answer = sum(1, cal+1, count-1, right, right);
				System.out.println(answer);
			}
			
			
		}
		
		
		
		
	}

	private static int sum(int node, int start, int end, int left, int right) {
		// TODO Auto-generated method stub
		lazy_update(node, start, end); 
		//return tree[right];
		if (left > end || right < start) return 0;
		if (left <= start && right >= end) return tree[node];
		int mid = (start+end)/2;
		return sum(node*2, start, mid, left, right)+sum(node*2+1, mid+1, end, left, right);
				
	}

	private static void update(int node, int start, int end, int left, int right, int money) {
		// TODO Auto-generated method stub
		
		lazy_update(node, start, end); 
		
		if (left > end || right < start) return;
		if (left <= start && right >= end) {
			tree[node] += (end - start +1) * money;
			if (start != end) {
				lazy[node*2] += money;
				lazy[node*2+1] += money;
			}
			return;
		}
		
		int mid = (start+end)/2;
		update(node*2, start, mid, left, right, money);
		update(node*2+1, mid+1, end, left, right, money);
		
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

	private static void initTree(int node, int start, int end) {
		// TODO Auto-generated method stub
		if (start == end) {
			if (start - cal> N) return;
			else {
				tree[node] = mList[rank[start-cal]].money;
				return;
			}
		}
		
		int mid = (start+end)/2;
		initTree(node*2, start, mid);
		initTree(node*2+1, mid+1, end);
		
		tree[node] = tree[node*2]  + tree[node*2+1];
				
	}

	private static int dfs(int node, int child) {
		// TODO Auto-generated method stub
		if (mList[node].childList.size() == 0) {
			mList[node].rank = rowCount+1;
			mList[node].child = 0;		
			rowCount++;
			return 0;
		}
		
		int childCount = 0;
		for (int i = 0; i<mList[node].childList.size(); i++) {
			childCount += dfs(mList[node].childList.get(i), 0);
			childCount++;
		}
		
		mList[node].rank = rowCount+1;
		mList[node].child = childCount;
		rowCount++;
		
		return mList[node].child;
				
	}

}

class Man{
	
	int rank;
	int child;
	int money;
	ArrayList<Integer> childList;
	public Man(int rank, int child, int money, ArrayList<Integer> childList) {
		super();
		this.rank = rank;
		this.child = child;
		this.money = money;
		this.childList = childList;
	}
	
	
	
}

