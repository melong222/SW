package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B11438 {

 static ArrayList<Integer> [] list;
 static int [] depth;
 static int [][] parent;
 static boolean[] visited;
 
 static int N;
 static int K;
 
 public static void main(String[] args) throws NumberFormatException, IOException {
  // TODO Auto-generated method stub

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  
  N = Integer.parseInt(br.readLine());  
  K = 1;
  int t = 1;
  
  while (t <=N) {
   t<<=1;
   K++;
  }
  
  list = new ArrayList[N+1];
  depth = new int [N+1];
  parent = new int [N+1][K+1];  
  visited = new boolean [N+1]; 
  
  for (int i = 1; i<=N; i++) {
   list[i] = new ArrayList<Integer>();
  }

  for (int i = 1; i<N; i++) {   
   StringTokenizer st = new StringTokenizer(br.readLine());
   int a = Integer.parseInt(st.nextToken());
   int b = Integer.parseInt(st.nextToken());     
   list[a].add(b);
   list[b].add(a);
  }
  
  dfs(1,1);
  
  for (int i = 0; i<=N; i++) {
	  System.out.print(depth[i]+ " ");
  }System.out.println();
  System.out.println("############");
  for (int i = 0; i<=N; i++) {
	  System.out.print(parent[i][0]+ " ");
  }System.out.println();
  
  fillParent();
  
  System.out.println();
  System.out.println();
  System.out.println();
  
  
  
  for (int i = 0; i<K; i++) {
   for (int j = 1; j<=N; j++) {
    System.out.print(parent[j][i] + " ");
   }System.out.println();
  }
  
  
   int Q = Integer.parseInt(br.readLine());
  
   for (int i = 1; i<=Q; i++) {
   StringTokenizer st = new StringTokenizer(br.readLine());
   int a = Integer.parseInt(st.nextToken());
   int b = Integer.parseInt(st.nextToken());     
   
   int answer = lca(a,b);
   System.out.println(answer);
   }
  
  
  
 }

 private static void fillParent() {
  // TODO Auto-generated method stub
  for (int i = 1; i<K; i++) {
   for (int j = 1; j<=N; j++) {
    parent[j][i] = parent[parent[j][i-1]][i-1];
   }
  }
 }
 

 

 private static int lca(int a, int b) {
  // TODO Auto-generated method stub

  if (depth[a] < depth[b]) { 
   int temp = b;
   b = a;
   a = temp;
  }
  
  
  for (int i = K-1; 0<=i; i--) {
   int diff = depth[a] - depth[b];
   //System.out.println("i : " + i+ "# diff :" + diff );
   if (Math.pow(2, i) <= diff) {
    a = parent[a][i];
   }   
  }

  
  if (a == b) return a;
  
  
  for (int i = K-1; 0<=i; i--) {
	  // System.out.println("i : " + i+ " a :" + a + " b : "+b);
   if (parent[a][i] != parent [b][i]) {
	  // System.out.println("pi : " + i+ " pa :" + a + " pb : "+b);
    a = parent[a][i];
    b = parent[b][i];    
   }
  }  
  return parent[a][0];  
 
 }

 private static void dfs(int node, int dep) {
  // TODO Auto-generated method stub
  if (visited[node]) return;
  visited[node] = true;
  depth[node] = dep;
  
  for (int i = 0; i<list[node].size(); i++) {
   if (!visited[list[node].get(i)]) {
    parent[list[node].get(i)][0] = node;
    dfs(list[node].get(i), dep+1);
   }
   
  }
  
 }

}