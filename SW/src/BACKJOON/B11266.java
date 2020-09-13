package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B11266 {

	static int count;
	
	static ArrayList<Integer> [] list;
	
	static int [] index;
	static boolean [] vertax;
	
	static int answer;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer	st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		list = new ArrayList [N+1];
		for (int i = 1; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 1; i<=E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b); 
			list[b].add(a);			
		}
		
		index = new int [N+1];
		vertax = new boolean [N+1];
		count = 0;
		
		for (int i = 1; i<=N; i++) {
			if (index[i] == 0) {
				dfs(i, true);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		answer = 0;
		
		for (int i = 1; i<=N; i++) {
			if (vertax[i]) {
				answer++;
				sb.append(i+ " ");
			}
		}
		
		System.out.println(answer);
		System.out.println(sb.toString()); 
		
		
	}

	private static int dfs(int node, boolean isRoot) {
		// TODO Auto-generated method stub
		index[node] = ++count;
		int ret = index[node];		
		int child = 0;
		
		for (int i = 0; i<list[node].size(); i++) {		
					
			if (index[list[node].get(i)] == 0) {
				child++;	
				int low = dfs(list[node].get(i),false);
				ret = Math.min(ret, low);
				
				// root node�� �ƴϰ�, �ڽĵ��� �� �� �ִ� ����� index �ּҰ��� ���� index �� ���� ũ�ٸ� �������̴�.
				if (!isRoot && low >= index[node]) {
					vertax[node] = true;
				}
				
			} else {
				// �ڽ� ��尡 �̹� �湮�� �ߴٸ�
				ret = Math.min(ret, index[list[node].get(i)]);
			}

		}

		
		// node�� root�̰� �ڽļ��� 2�� �̻��̸� �������̴�.
		if (isRoot && child >=2) {
			vertax[node] = true;
		}
		
		return ret;
		
	}

}