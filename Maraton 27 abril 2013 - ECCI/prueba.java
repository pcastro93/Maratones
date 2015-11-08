import java.io.*;
import java.util.*;
public class prueba{
	static void sacar()throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringBuilder in = new StringBuilder("");
		StringBuilder ou = new StringBuilder("");
		while((input = br.readLine())!=null){
			String cad = input.replaceAll(",", " ").replaceAll("(\\t)+", " ").replaceAll("( )+", " ").trim();
			if(cad.equals(""))continue;
			String div[] = cad.split(" ");
			in.append(div[0]+" "+div[1]+" "+div[2]+" "+div[3]+" "+div[4]+" "+div[5]).append("\n");
			ou.append(div[6]).append("\n");
		}
		System.out.print(ou);
	}
	
	public static void main(String fdjkfd[])throws IOException{
		/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<String> lis = new LinkedList<String>();
		String input = "";
		while((input = br.readLine())!=null)
			lis.add(input);
		Collections.sort(lis);
		StringBuilder sb = new StringBuilder("");
		for(String s: lis){
			sb.append(s).append("\n");}
		System.out.print(sb);*/
		/*HashSet<Integer> tm = new HashSet<Integer>();
		Integer a1[] = {0 ,10083 ,10084 ,1 ,0 ,1 ,10083 ,0 ,0 ,0 ,0};
		Integer a2[] = {1 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0,2112};
		tm.add(Arrays.hashCode(a1));tm.add(Arrays.hashCode(a2));
		System.out.println(Arrays.hashCode(a1));
		System.out.println(Arrays.hashCode(a2));
		System.out.println("tamano "+tm.size());
		System.out.println(Arrays.equals(a1, a2));
		System.out.println(a1.equals(a2));*/
		HashSet<B> ss = new HashSet<B>();
		int a1[] = {0 ,10083 ,10084 ,1 ,0 ,1 ,10083 ,0 ,0 ,0 ,0};
		int a2[] = {1 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0,2112};
		int a3[] = {0 ,10083 ,10084 ,1 ,0 ,1 ,10083 ,0 ,0 ,0 ,0};
		B ba1 = new B(a1);
		B ba2 = new B(a2);
		B ba3 = new B(a3);
		ss.add(ba1);
		ss.add(ba2);
		ss.add(ba3);
		System.out.println(ss.size());
	}
}

class B{
	int a[];
	public B(int arr[]){
		this.a = arr;
	}
	public boolean equals(Object oo){
		B o = (B)oo;
		return Arrays.equals(this.a, o.a);
	}
	public int hashCode(){
		return Arrays.hashCode(a);
	}
}
