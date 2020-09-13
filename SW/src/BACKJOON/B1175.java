package BACKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1175 {

 public static void main(String[] args) throws IOException {
  // TODO Auto-generated method stub

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  
  StringTokenizer st = new StringTokenizer(br.readLine());
  
  int N = Integer.parseInt(st.nextToken());
  int M = Integer.parseInt(st.nextToken());
  
  char [][] map = new char [M][N];
  
  int x = 0;
  int y = 0;
  
  for (int i = 0; i<N; i++) {
   st = new StringTokenizer(br.readLine());
   StringBuilder sb = new StringBuilder();
   sb.append(st.nextToken());
   for (int j = 0; j<M; j++) {
    map[j][i] = sb.charAt(j);
    String temp = map[j][i]+"";
    if (temp.equals("S")) {
     x = j;
     y = i;
    }
   }   
  }
  
  boolean [][][][][] visited  = new boolean [M][N][4][2][2];
  
  Queue<String> queue = new LinkedList<String>();
  HashMap hashmap  = new HashMap<String, Integer>();
  
  
  
  
  
  
  
  
 }

}