import java.io.*;
import java.util.*;
public class boxes {
	
	static int atoi(String n){return Integer.parseInt(n);}
	
	public static void main(String jfk[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringBuilder sb = new StringBuilder("");
		while(!(input = br.readLine()).equals("-1 -1")){
			StringTokenizer st= new StringTokenizer(input);
			int c = atoi(st.nextToken()), b=atoi(st.nextToken());
			PriorityQueue<cucu> pq = new PriorityQueue<cucu>();
			
			
			for(int i=0;i<c;i++)
				pq.offer(new cucu(Double.parseDouble(br.readLine()),1.0));
			b-=c;
			//System.out.println(pq);
			while(b>0){
				cucu n=pq.poll();
				//System.out.println(n);
				cucu cc =new cucu(n.num,n.den+1);
				pq.offer(cc);
				b--;
				
			}
			cucu m= pq.poll();
			//System.out.println((int)Math.ceil(m.num/m.den));
			sb.append((int)Math.ceil(m.num/m.den)).append("\n");
			br.readLine();
		}
		System.out.print(sb);
	}
}

class cucu implements Comparable<cucu>{
	double den;
	double num;
	double res;
	
	cucu(double num, double den){
		this.den=den;
		this.num=num;
		this.res = this.num/this.den;
	}
	public int compareTo(cucu a){
		return (int)-Math.signum(this.res-a.res);
	}
	public String toString(){
		return this.num+"/"+this.den;
	}

}