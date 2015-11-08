import java.io.*;
import java.util.*;

public class winter{
	static int atoi(String n){return Integer.parseInt(n);}
	static ArrayList<HashMap<Integer, Integer>> g;
	static boolean solve(int a, int b, int c){
		boolean ec[] = new boolean[g.size()];
		boolean v[] = new boolean[g.size()];
		Queue<Integer> cola = new LinkedList<Integer>();
		cola.offer(a);
		while(!cola.isEmpty()){
			int act = cola.poll();
			if(v[act])continue;
			if(act==b)return true;
			v[act] = true;
			HashMap<Integer, Integer> ad =g.get(act); 
			for(int k: ad.keySet()){
				if(ad.get(k)>=c && !ec[k]){
					cola.offer(k);
					ec[k] = true;
				}
			}
		}
		return false;
	}
	public static void main(String fdjkfd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringTokenizer st;
		StringBuilder sb = new StringBuilder("");
		while((input = br.readLine())!=null && !input.equals("0 0")){
			ArrayList<int[]> roads = new ArrayList<int[]>();
			st = new StringTokenizer(input);
			int n = atoi(st.nextToken()), e = atoi(st.nextToken());
			g = new ArrayList<HashMap<Integer,Integer>>();
			for(int i=0;i<n;i++)
				g.add(new HashMap<Integer, Integer>());
			while(e-->0){
				st = new StringTokenizer(br.readLine());
				int a = atoi(st.nextToken())-1, b = atoi(st.nextToken())-1, c = atoi(st.nextToken());
				int arr[] = {a, b,c};
				roads.add(arr);
				g.get(a).put(b,c);
			}
			int q = atoi(br.readLine());
			while(q-->0){
				st = new StringTokenizer(br.readLine());
				char let = st.nextToken().charAt(0);
				int a = atoi(st.nextToken()),b = atoi(st.nextToken()),c;
				if(let=='S'){
					c = atoi(st.nextToken());
					boolean res = false;
					res = solve(a-1, b-1, c);
					sb.append((res?"1":"0")).append("\n");
					}
				else{
					if(a-1<0 || a-1>=roads.size()){System.out.println("fffff");return;}
					int rr[] = roads.get(a-1);
					int oo[] = {rr[0], rr[1], b};
					roads.set(a-1, oo);
					g.get(a-1).put(rr[1],b);
				}
				
			}
		}
		System.out.print(sb);
		}
}