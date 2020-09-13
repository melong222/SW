package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B1761 {

 static long distance[];
 static int [] depth;
 static int [][] parent;
 static boolean [] visited;
 
 static ArrayList<Integer> [] list;
 static ArrayList<Integer> [] dlist;
 
 static int N;
 static int K;
 
 public static void main(String[] args) throws NumberFormatException, IOException {
  // TODO Auto-generated method stub

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  
  N = Integer.parseInt(br.readLine());
  K = 20;
  
  depth  = new int [N+1];
  distance = new long [N+1];
  parent   = new int [N+1][K];
  
  list  = new ArrayList [N+1];
  dlist = new ArrayList [N+1];
  
  visited = new boolean [N+1];
  
  for (int i = 1; i<=N; i++) {
   list[i] = new ArrayList<Integer>();
   dlist[i] = new ArrayList<Integer>();
  }
  
  for (int i = 1; i<N; i++) {
   StringTokenizer st = new StringTokenizer(br.readLine());
   int a = Integer.parseInt(st.nextToken());
   int b = Integer.parseInt(st.nextToken());
   int c = Integer.parseInt(st.nextToken());   
   list[a].add(b);
   list[b].add(a);
   dlist[a].add(c);
   dlist[b].add(c);   

  }
  
  dfs(1,0,0);
 
  fillParent();

  /*for (int i = 1; i<=N; i++) {
   System.out.print(parent[i][0]+ " ");
  }System.out.println();
  for (int i = 1; i<=N; i++) {
   System.out.print(depth[i]+ " ");
  }System.out.println();
  for (int i = 1; i<=N; i++) {
   System.out.print(distance[i]+ " ");
  }System.out.println(); */
  
  /*for (int i = 0; i<=2; i++) {
   for (int j = 1; j<=N; j++) {
    System.out.print(parent[j][i]+" ");
   }System.out.println();
  }*/
  
  
  int Q = Integer.parseInt(br.readLine());
  long answer = 0;
  
  for (int i = 0; i<Q; i++) {
   StringTokenizer st = new StringTokenizer(br.readLine());
   int a = Integer.parseInt(st.nextToken());
   int b = Integer.parseInt(st.nextToken());
   answer = getLca(a,b);
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

 private static long getLca(int low, int high) {
  // TODO Auto-generated method stub
	 
  if (depth[low] < depth[high]) {
   int temp = high;
   high = low;
   low = temp;
  }
  
  int a = low;
  int b = high;
  
  for (int i = K-1; i>=0; i--) {
   int diff = depth[a] - depth[b];
   if (Math.pow(2, i) <= diff) {
    a = parent[a][i];
   }
  }
  
  //System.out.println("#a : "+a + "#b :"+b);
  
  if (a == b) {	  
	  return distance[low] - distance[high];
  }
  
  for (int i = K-1; i>=0; i--) {
   if (parent[a][i] != parent[b][i]) {
    a = parent[a][i];
    b = parent[b][i];
   } 
  }
  int lca = parent[a][0];  
  long cost = distance[lca];
  //System.out.println("#lca : "+lca);
  
  long answer = distance[low] + distance[high] - 2*cost;
  
  return answer;

 }

 private static void dfs(int node, int dep, int value) {
  // TODO Auto-generated method stub
  if (visited[node]) return;
  visited[node] = true;
  depth[node] = dep;
  distance[node] = value;
  
  for (int i = 0; i<list[node].size(); i++) {
   if (!visited[list[node].get(i)]) {
    dfs(list[node].get(i), dep+1,dlist[node].get(i)+value);
    parent[list[node].get(i)][0] = node;    
   }
  }
  
  
 }

}

 

 