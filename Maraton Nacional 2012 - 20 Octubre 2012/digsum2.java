import java.io.*;
import java.util.*;
public class digsum{
	static int atoi(String n){return Integer.parseInt(n);}
	static long solve(int a){
		long d[] = new long[10];//cuenta de cada uno de los digitos
		int dec = (int)Math.log10(a);
		for(int i=0;i<=dec;i++){
			int cadaDig = (int)Math.pow(10,i);//en las decenas, centenas, etc...cuantas veces aparece.
			int cuan = a/(int)Math.pow(10,i+1);//cuantas decenas, centenas, etc...hay hasta el numero
			int sobra = (a%((int)Math.pow(10,i+1)))/(int)Math.pow(10,i);//el valor despues de las decenas, centenas, etc..a la derecha
			int ult = (a%((int)Math.pow(10,i)));//si es 112 sera 2...al 1(sobra) se le suma 2(ult) veces a las apariciones
			for(int j=0;j<d.length;j++)
				d[j] += cadaDig*cuan;
			for(int j=0;j<sobra;j++)
				d[j] +=cadaDig;
			d[sobra]+=ult;
		}
		long suma=0;
		for(int i=0;i<d.length;i++)
			suma+=i*d[i];
		return suma;
	}
	public static void main(String jfkd[])throws Exception{
		Scanner br = new Scanner();
		String input = "";
		StringBuilder sb = new StringBuilder("");
		while(true){
			int a = br.nextInt(), b = br.nextInt();
			if(a==0 && b==0)break;
			long res = solve(b+1)-solve(a);
			sb.append(res).append("\n");
		}
		System.out.print(sb);
	}
	
	static class Scanner
        {
                BufferedReader br;
                StringTokenizer st;
                
                public Scanner()
                {
                System.setOut(new PrintStream(new BufferedOutputStream(System.out), true));
                        br = new BufferedReader(new InputStreamReader(System.in));
                }
                
                public String next()
                {


                        while(st == null || !st.hasMoreTokens())
                        {
                                try { st = new StringTokenizer(br.readLine()); }
                                catch(Exception e) { throw new RuntimeException(); }
                        }
                        return st.nextToken();
                }


                public int nextInt()
                {
                        return Integer.parseInt(next());
                }
                
                public double nextDouble()
                {
                        return Double.parseDouble(next());
                }
                
                public String nextLine()
                {
                        st = null;
                        try { return br.readLine(); }
                        catch(Exception e) { throw new RuntimeException(); }
                }
                
                public boolean endLine()
                {
                        try 
                        {
                                String next = br.readLine();
                                while(next != null && next.trim().isEmpty())
                                        next = br.readLine();
                                if(next == null)
                                        return true;
                                st = new StringTokenizer(next);
                                return !st.hasMoreTokens();
                        }
                        catch(Exception e) { throw new RuntimeException(); }
                }
        }
}