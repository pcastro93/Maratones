import java.io.*;
import java.util.*;
public class H {
	public static int atoi(String n){
		return Integer.parseInt(n);
	}
	public static String hijo(String papa, String mama, String genes){
		String res = "";
		for(int i=0;i<papa.length();i++)
			if(genes.charAt(i)=='R')
				res+=((papa.charAt(i)-'0') & (mama.charAt(i)-'0'));
			else
				res+=((papa.charAt(i)-'0') | (mama.charAt(i)-'0'));
		return res;
	}
	public static void main(String fjdkfjdk[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		//while(!(input = br.readLine()).equals("***")){
			String genes = input=br.readLine();
			ArrayList<String> papas = new ArrayList<String>();
			ArrayList<String> mamas = new ArrayList<String>();
			while(!(input= br.readLine()).equals("***"))
				if(input.split(" ")[1].equals("M"))
					papas.add(input);
				else
					mamas.add(input);
			ArrayList<String> hijos = new ArrayList<String>();
			while(!(input= br.readLine()).equals("***"))
				hijos.add(input);
			for(int i=0;i<hijos.size();i++){
				ArrayList<String> posibles =new ArrayList<String>();
				for(int k=0;k<papas.size();k++)
					for(int l=0;l<mamas.size();l++)
						if(hijo(papas.get(k).split(" ")[2], mamas.get(l).split(" ")[2], genes).equals(hijos.get(i).split(" ")[1]))
							posibles.add(mamas.get(l).split(" ")[0] + "-"+papas.get(k).split(" ")[0]);
				Collections.sort(posibles);
				if(posibles.isEmpty()){System.out.println(hijos.get(i).split(" ")[0]+" by");continue;}
				System.out.print(hijos.get(i).split(" ")[0]+" by ");
				for(int k=0;k<posibles.size()-1;k++){
					System.out.print(posibles.get(k)+" or ");
				}
				System.out.println(posibles.get(posibles.size()-1));
			}
		//}
	}
}
