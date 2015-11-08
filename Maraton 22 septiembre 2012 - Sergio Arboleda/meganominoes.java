import java.io.*;
import java.util.*;

public class meganominoes {
	public static int atoi(String n) {
		return Integer.parseInt(n);
	}

	public static void main(String fjkdfdfd[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int arch = 1;
		String input = "";
		int tot=1, n,m;
		try{
		//tot= atoi(input = br.readLine());
		}catch(Exception e){
			System.out.println("fdfdfd");
			System.exit(0);
		}
		ArrayList<Pair> parejas;
		for(int i=0;i<tot;i++){
			try{
			n = atoi((input = br.readLine()).split(" ")[0]);
			m = atoi((input).split(" ")[1]);
			parejas= new ArrayList<Pair>();
			//Parejas
			for(int j=0;j<n;j++){
				long a = Long.parseLong((input = br.readLine()).split(" ")[0]);
				long b = Long.parseLong((input).split(" ")[1]);
				parejas.add(new Pair(a,b));
			}
			for(int j=0;j<m;j++){
				long q=0;
				q = Long.parseLong(br.readLine());
				int cum = 0;
				Set<String> mapa = new HashSet<String>();
				for(int mm=0;mm<parejas.size();mm++){
					Pair c = parejas.get(mm);
					for(int uu=0;uu<parejas.size();uu++){
						Pair k = parejas.get(uu);
						if(mm==uu)continue;
						if((c.b==k.a && c.a+k.b==q)||(c.a==k.b && c.b+k.a==q) && !mapa.contains(mm+""+uu)){
							cum++;
							mapa.add(mm+""+uu);
							mapa.add(uu+""+mm);
						}
					}
				}
				System.out.println(cum);
			}
		}catch(Exception e){
			System.out.println("fuck");
			continue;
		}
		}
	}
}
class Pair{
	long a, b;
	Pair(long a, long b){
		this.a = a;
		this.b =b;
	}
	public String toString(){
		return a+" "+b;
	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (int) (a ^ (a >>> 32));
//		result = prime * result + (int) (b ^ (b >>> 32));
//		return result;
//	}
}
