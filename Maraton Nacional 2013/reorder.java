import java.io.*;
import java.util.*;

public class reorder{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String fdfd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int test = atoi(br.readLine());
		while(test-->0){
			HashMap<String, PriorityQueue<String>> ana = new HashMap<String, PriorityQueue<String>>();
			StringBuilder res = new StringBuilder("");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()){
				String pal = st.nextToken();
				if(pal.length()<=3)
					continue;
				StringBuilder sb = new StringBuilder(pal);
				sb.deleteCharAt(0);sb.deleteCharAt(sb.length()-1);
				char[] parr = sb.toString().toCharArray();Arrays.sort(parr);
				String palord = new String(parr);
				
				if(!ana.containsKey(pal.charAt(0)+palord+pal.charAt(pal.length()-1)))
					ana.put(pal.charAt(0)+palord+pal.charAt(pal.length()-1), new PriorityQueue<String>());
				ana.get(pal.charAt(0)+palord+pal.charAt(pal.length()-1)).offer(pal);
			}
			
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			while(st2.hasMoreTokens()){
				String query = st2.nextToken();
				if(query.length()<=3){
					res.append(query).append(" ");
					continue;
				}
				StringBuilder sbquery = new StringBuilder(query);
				sbquery.deleteCharAt(0);sbquery.deleteCharAt(sbquery.length()-1);
				char[] parrquery = sbquery.toString().toCharArray();Arrays.sort(parrquery);
				String palordquery = new String(parrquery);
				if(ana.containsKey(query.charAt(0)+palordquery+query.charAt(query.length()-1)))
					res.append(ana.get(query.charAt(0)+palordquery+query.charAt(query.length()-1)).peek()).append(" ");
				else
					res.append(query).append(" ");
			}
			System.out.println(res.deleteCharAt(res.length()-1));
		}
	}
}

