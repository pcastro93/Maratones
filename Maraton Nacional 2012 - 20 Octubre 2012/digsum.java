import java.io.*;
public class digsum{
	public static long d[] = new long[40];
	public static void main(String args[]) throws IOException{
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		String tmp = "";
		d[0]=1;d[1]=45;
		for(int n = 2;n<d.length;n++)
			d[n]=10*d[n-1]+(long)Math.pow(10,n-1)*45;
		while((tmp = lector.readLine())!=null && !tmp.equals("0 0")){
			int t = Integer.parseInt(tmp.substring(0,tmp.indexOf(" ")))-1;
			System.out.println(func(tmp.substring(tmp.indexOf(" ")+1,tmp.length()))-func(t+""));
		}
	}
	public static long func(String a){
		if(a.length()==0)
			return 0;
		if(a.length()==1)
			return ((Long.parseLong(a)*(Long.parseLong(a)+1))>>1);
		int r = a.charAt(0)-'0';
		String par = a.substring(1,a.length());
		return r*(Long.parseLong(par.length()==0?"0":par)+1)+func(par)+r*d[a.length()-1]+(long)Math.pow(10,a.length()-1)*((r*(r-1))>>1);
	}
}
