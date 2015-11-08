import java.io.*;
import java.util.*;

public class lru{

	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jkdfdp[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int cont =0;
		while((input = br.readLine())!=null && !input.equals("0")){
			StringTokenizer st = new StringTokenizer(input);
			System.out.println("Simulation "+ ++cont);
			int c = atoi(st.nextToken());
			String cad = st.nextToken();
			ArrayList<Character> memo = new ArrayList<Character>();
			for(int i=0;i<cad.length();i++){
				char act = cad.charAt(i);
//				System.out.println(act);
				int pos = -1;
				if(act=='!'){
					StringBuilder sb = new StringBuilder("");
					for(int k=0;k<memo.size();k++)
						sb.append(memo.get(k));
					System.out.println(sb);
					continue;
				}
				for(int j=0;j<memo.size();j++)
					if(memo.get(j)==act){
						pos = j;
						break;
					}
				if(pos==-1 && memo.size()>=c){
					memo.remove(0);
					memo.add(act);
					continue;
				}
				if(pos!=-1)
					memo.remove(pos);
				memo.add(act);
			}
			
		}
	}
}