import java.io.*;
import java.util.*;
public class loopytransit{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void intercambiar(int ar[], int f,int s){
		int temp = ar[f];
		ar[f] = ar[s];
		ar[s] = temp;
	}
	public static void reversar(int ar[]){
		for(int i=0,j=ar.length-1;i<j;i++,j--)
			intercambiar(ar,i,j);
	}
	public static void reversar(int ar[],int b,int f){
		for(int i=b,j=f;i<j;i++,j--)
			intercambiar(ar,i,j);
	}
	public static int[] nextPermutation(int ar[]){
		int in1,in2;
		for(in1 = ar.length-2,in2= ar.length-1;ar[in2]<=ar[in1] && in1!=0;in1--,in2--);
		if(ar[in2]<=ar[in1]){
			reversar(ar);
			return ar;
		}else{
			for (in2 = ar.length-1;in2 > in1 && ar[in2] <= ar[in1]; in2--);
			intercambiar(ar,in1,in2);
			reversar(ar,in1+1,ar.length-1);
			return ar;
		}
	}
	public static ArrayList<String> permutaciones(String cosa){
		int ar[] = new int[cosa.length()];
		for(int i=0;i<ar.length;i++)
			ar[i] = i;
		ArrayList<String> permuts = new ArrayList<String>();
		permuts.add(cosa);
		String r = "";
		int i=0;
		while(true){
			int temp[] = nextPermutation(ar);
			r = "";
			for(int j=0;j<temp.length;j++){
				r += ""+cosa.charAt(temp[j]);
			}
			if(r.equals(cosa))
				break;
			permuts.add(r);
			i++;
		}
		return permuts;
	}
	static void addShifts(String p, HashSet<String> h){
		for(int i=0;i<p.length();i++){
			p=p.substring(1,p.length())+p.charAt(0);
			h.add(p);
		}
	}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = atoi(br.readLine());
		int e = atoi(br.readLine());
		boolean g[][] = new boolean[n][n];
		String input = "";
		for(int i=0;i<e;i++)
			g[atoi((input = br.readLine()).split(" ")[0])][atoi(input.split(" ")[1])]=true;//feo pero en una linea :)
		HashSet<String> shifts = new HashSet<String>();
		int tot = 0;
		for(int i=1;i<(1<<n);i++){//el conjunto vacio no importa, por eso empiezo en 1
			String bin = Integer.toBinaryString(i);
			while(bin.length()<n)bin = "0"+bin;
			//los numeros maximo son hasta 9 asi que no hay problema al guardarlos en un String :)
			String para = "";//los numeros a permutar
			for(int j=0;j<bin.length();j++)
				if(bin.charAt(j)=='1')
					para+=j;
			if(para.length()==1)continue;
			//verifico cada una de las permutaciones y aumento la cantidad de simple loops que encuentro
			ArrayList<String> pers = permutaciones(para);
			for(String p: pers){
				char arr[] = p.toCharArray();
				boolean esta  = true;
				for(int j=0;j<p.length() && esta;j++)
					esta = g[arr[j]-'0'][arr[(j+1)%arr.length]-'0'];
				if(esta)
					if(!shifts.contains(p)){//busco que no sea shift de ninguna que ya se haya agregado
						tot++;
						addShifts(p, shifts);
					}
			}
		}
		System.out.println(tot);
	}
}
