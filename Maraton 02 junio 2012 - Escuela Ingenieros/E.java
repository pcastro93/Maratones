import java.io.*;
import java.util.*;
public class E {
	public static int atoi(String n){
		return Integer.parseInt(n);
	}
	public static boolean iguales(int arr[]){
		return (arr[0]==arr[1] && arr[1]==arr[2]);
	}
	public static boolean si(int arr[]){
		int tmp2[] = arr.clone();
		Arrays.sort(tmp2);
		int tmp []= arr2.clone();
		Arrays.sort(tmp);
		return (tmp2[0] == tmp[0] && tmp2[1] == tmp[1]&& tmp2[2] == tmp[2]);
	}
	public static int diegoPuta(int arr[]){
		int tmp[] = arr.clone();
		Arrays.sort(tmp);
		return (tmp[0]+60*tmp[1] + 3600*tmp[2]);
	}
	public static int arr2[] = new int[3];
	public static Set<Integer> ya = new HashSet<Integer>();
	public static ArrayList<int[]> minimo = new ArrayList<int[]>();
	public static int llegar;
	public static int d = 0;
	public static void solve(int[] arr, ArrayList<int[]> vector,int a, int b){
		//System.out.println(vector);
		//System.out.println(++d);
		//imp(arr);
		ArrayList<int[]> vector2 = (ArrayList<int[]>)vector.clone();
		vector2.add(arr);
		if(arr[0]==0 ||arr[1]==0 ||arr[2]==0)return;
		int ddd = 0;
		//if(ya.contains(ddd=diegoPuta(arr)))return;
		//ya.add(ddd);
		if(iguales(arr)){
			if(minimo.isEmpty())
				minimo = vector2;
			if(minimo.size()>=vector2.size())
				minimo = vector2;
			return;
			}
		for(int k=0;k<arr.length;k++){
			if(arr[k]==llegar)continue;
			if(arr[(k+1)%3]!=llegar && arr[(k+1)%3]<=arr[k] && (k!=b || (k+1)%3!=a)){
				int tmp[] = new int[3];
				tmp[k] = arr[k]-arr[(k+1)%3];
				tmp[(k+1)%3]=arr[(k+1)%3]*2;
				tmp[(k+2)%3] = arr[(k+2)%3];
				solve(tmp, vector2, k, (k+1)%3);
			}
			if(arr[(k+2)%3]!=llegar && arr[(k+2)%3]<=arr[k] && (k!=b || (k+2)%3!=a)){
				int tmp[] = new int[3];
				tmp[k] = arr[k]-arr[(k+2)%3];
				tmp[(k+2)%3]=arr[(k+2)%3]*2;
				tmp[(k+1)%3] = arr[(k+1)%3];
				solve(tmp, vector2, k ,(k+2)%3);
			}
		}
	}
	public static void imp(int t[]){
		for(int i=0;i<t.length-1;i++){
			String diegoPuta = ""+t[i];
			for(int k=0;k<4-diegoPuta.length();k++)
				System.out.print(" ");
			System.out.print(diegoPuta);
			}
		String diegoPuta = ""+t[t.length-1];
		for(int k=0;k<4-diegoPuta.length();k++)
			System.out.print(" ");
		System.out.println(diegoPuta);
	}
	public static void main(String fjdkfjdk[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while(!(input = br.readLine()).equals("0 0 0")){
			ya = new HashSet<Integer>();
			minimo = new ArrayList<int[]>();
			int arr[] = new int[3];
			int sum = 0;
			for(int c=0;c<arr.length;c++){
				arr[c] = atoi(input.split(" ")[c]);
				sum+=arr[c];
				}
			if(sum%3!=0){
				imp(arr);
				System.out.println("============");
				continue;
				}
			llegar = sum/3;
			arr2 = arr.clone();
			try{
			solve(arr, new ArrayList<int[]>(),-1,-1);}
			catch(java.lang.StackOverflowError e){
				minimo = new ArrayList<int[]>();
			}
			if(minimo.isEmpty()){
				imp(arr);
				System.out.println("============");
				continue;
			}
			for(int[] a:minimo)
				imp(a);
			System.out.println("============");
		}
		int arr[] = {0,0,0};
		imp(arr);
		System.out.println("============");
	}
}
