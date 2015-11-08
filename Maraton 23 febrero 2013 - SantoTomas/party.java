import java.io.*;
import java.util.*;

public class party{
	public static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String fdjfkd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!= null && !input.equals("0")){
			int num = atoi(input);
			String arr[] = new String[num];
			for(int i=0;i<num;i++)
				arr[i] = br.readLine();
			Arrays.sort(arr);
			String menor = arr[(num>>1)-1];
			String mayor  = arr[(num>>1)];
			
			String pref = "";
			int lon = (int)Math.min(menor.length(), mayor.length());
			for(int i=0;i<lon;i++)
				if(menor.charAt(i)==mayor.charAt(i))
					pref+=menor.charAt(i);
			int emp = pref.length();
			if(menor.length()==emp){
				System.out.println(menor);
				continue;
				}
			else if(mayor.length()==emp){
				System.out.println(mayor);
				continue;
			}
				if(Math.abs(menor.charAt(emp)-mayor.charAt(emp))>1)
					pref+=(char)(menor.charAt(emp)+1);
				if(Math.abs(menor.charAt(emp)-mayor.charAt(emp))==1)
					pref+=(char)mayor.charAt(emp);
				System.out.println(pref);
		}
		
	}
}