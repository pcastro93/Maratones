import java.io.*;
import java.util.*;
public class fibonacci{

	static int MAXSIZEP = 29;
	static int MAXN = 110;
	static int N;
	static String p;
	static long[]fibo = new long[MAXSIZEP];
	static String palabra[] = new String[MAXN];
	static long dp[] = new long[MAXN];


	static void fibona(){
		fibo[0] = 1; fibo[1] = 1;
		palabra[0] = "0";
		palabra[1] = "1"; 

	   int n = 2;
    	while ( n < MAXSIZEP ){
	        fibo[n] = fibo[n-2] + fibo[n-1];
	        palabra[n] = palabra[n-1] + palabra[n-2];  
	        n = n + 1;
	    }
	}


	static ArrayList<Integer> KMP(String s, String w){
		ArrayList<Integer> ocur = new ArrayList<Integer>();
		//construccion de la tabla
		int t[] = new int[w.length()];
		t[0] = -1;
		int pos = 2, cnd = 0;
		while(pos<w.length()){
			if(w.charAt(pos-1)==w.charAt(cnd)){
				cnd++;
				t[pos++] = cnd;
			}
			else if(cnd>0)
				cnd = t[cnd];
			else
				t[pos++] = 0;
		}
		//kmp
		int m = 0,i=0;
		while(m+i<s.length()){
			if(w.charAt(i)==s.charAt(m+i)){
				if(i==w.length()-1){
					ocur.add(m);
					m = m+i-t[i];
					i = (t[i]>-1?t[i]:0);
					}
				else
					i++;
			}else{
				m = m+i-t[i];
				i = (t[i]>-1?t[i]:0);
			}
		}
		return ocur;
	}
	
	static int upper_bound(int n){
		//busco sobre fibonacci
		int i=0;
		while(fibo[i]<=n)i++;
		return i;
	}
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String args[])throws IOException{
		Scanner br = new Scanner();
		StringBuilder sb = new StringBuilder("");
		fibona();

    int caso = 0;
    while ( !br.endLine() ){
    	N = br.nextInt();
        if ( N == -1 ) break;
        caso = caso + 1;
        p = br.next();
        int posFibo = upper_bound(p.length());
        if ( N <= 1){
            int ri = KMP(palabra[N], p).size();
            sb.append("Case ").append(caso).append(": ").append(ri).append("\n");
        }
        else{
	        String F = palabra[ posFibo];
	        String G = palabra[ posFibo + 1];
	        
	        long ocurr[] = new long[2];
	
	        String cad[] = new String[2];
	        cad[0] = G+F;
	        cad[1] = F+G;

			long o0=KMP(cad[0], p).size(),o1=KMP(cad[1], p).size();
	        dp[posFibo - 1] = KMP(palabra[posFibo-1],p).size() ;
	        dp[posFibo] = KMP(F,p).size() ;
	        dp[posFibo + 1] = KMP(G,p).size();
	        
	        	        //en el overlap sera: ocurrencias en posFibo+1,posFibo(o al reves) menos ocurrencias en posFibo+1 menos ocurrencias en posFibo
	        ocurr[0] = o0-dp[posFibo+1]-dp[posFibo];
	        ocurr[1] = o1-dp[posFibo+1]-dp[posFibo];
	        
	        int ccc = 0;
	        for ( int i = posFibo + 2; i < 101; i++,ccc++ )
	                dp[i] = dp[ i-1 ] + dp[ i-2 ] + ocurr[ccc&1];
	        sb.append("Case ").append(caso).append(": ").append(dp[N]).append("\n");
        }
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


