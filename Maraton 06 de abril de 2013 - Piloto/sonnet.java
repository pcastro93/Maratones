import java.io.*;
import java.util.*;

public class sonnet{
	static String quitar(String p){
		String res = "";
		for(int i=0;i<p.length();i++)
			if(Character.isLetter(p.charAt(i)))
				res+=p.charAt(i);
		return res;
	}
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jfkdfd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		String msj1 = "Not a chance!";
		while((input = br.readLine())!=null){
			String suf[] = input.split(" ");
			String title = br.readLine();
			ArrayList<String> list = new ArrayList<String>();
			int cont =0;
			while( (input = br.readLine()) != null && !input.equals("")){
				input = input.toLowerCase();
				input = quitar(input);
				if(input.length()>0 && input.charAt(input.length()-1)=='s')
					input = input.substring(0, input.length()-1);
				list.add(input);
			}
			String fff = (input==null)?"":"\n";
			if(list.size()!=14){
				System.out.print(title + ": "+msj1+fff);
				continue;
				}
			String arr[] = new String[list.size()];
			for(int i=0;i<arr.length;i++)
				arr[i] = list.get(i);
			char let = (char)('A'-1);
			HashMap<String,Character> map = new HashMap<String, Character>();
			String result = "";
			boolean falla = false;//no rima
			for(int i=0;i<arr.length && !falla;i++){
				boolean entro = false;
				for(int j=0;j<suf.length && !entro;j++){
					if(arr[i].endsWith(suf[j])){
						entro = true;
						if(!map.containsKey(suf[j])){
							result+=(char)(++let);
							map.put(suf[j],(char)(let));
						}
						else
							result+=(map.get(suf[j]));
					}
				}
				if(!entro)
					falla = true;
			}
			System.out.print(title+": ");
			if(!result.matches("(ABBAABBACDECDE|ABBAABBACDEDCE|ABBAABBACDCDCD)") || falla)
				System.out.print(msj1+fff);
			else
				System.out.print(result+fff);
			//int p1[] = {A,B,B,A,A,B,B,A,C,D,E,C,D,E};
			//int p2[] = {A,B,B,A,A,B,B,A,C,D,E,D,C,E};
			//int p3[] = {A,B,B,A,A,B,B,A,C,D,C,D,C,D};
		}
	}
}