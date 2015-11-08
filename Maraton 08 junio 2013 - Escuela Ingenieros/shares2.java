import java.io.*;
import java.util.*;
public class shares{
	static Scanner br= new Scanner();
	static int atoi(String n){return Integer.parseInt(n);}
	static String leer()throws Exception{
		String l = null;
		if(br.endLine())
			return null;
		l = br.next().trim();
		return l;
	}
	static int gcd(int a, int b){
		return(b==0?a:gcd(b,a%b));
	}
	public static void main(String jfkdfd[])throws Exception{
		String input= "";
		StringBuilder sb = new StringBuilder("");
		//boolean primera = true;
		while((input = leer())!=null){
			//if(!primera)
			//	sb.append("\n");
			//primera = false;
			if(input.equals(""))continue;
			int w, n,c;
			w = atoi(input);
			n = br.nextInt();
			c = br.nextInt();
			int precomp[][];
			precomp = new int[n][3];//precio compra
			for(int i=0;i<n;i++){
				precomp[i][0] = br.nextInt();//peso
				precomp[i][1] = br.nextInt();
				precomp[i][2] = precomp[i][1]-precomp[i][0];//profit
			}
			int items[];
			int pesos[];
			int suma= 0;
			
			ArrayList<Integer> ai = new ArrayList<Integer>();//profit items
			ArrayList<Integer> pi = new ArrayList<Integer>();//peso
			int dcg = 0;
			for(int i=0;i<c;i++){
				int fff = br.nextInt();
				int peso = 0;
				int profit = 0;
				for(int j=0;j<fff;j++){
					int pos =br.nextInt()-1;
					int veces =br.nextInt();
					peso += (veces*precomp[pos][0]);
					profit+= (veces*precomp[pos][2]);
				}
				if(profit>0 && peso<=w){
					dcg = gcd(dcg, peso);
					ai.add(profit);
					pi.add(peso);
				}
			}
			w = w/dcg;
			//for(int i=0;i<ai.size();i++)
			//	System.out.println(ai.get(i)+" "+pi.get(i));
			//System.out.println("gcd "+dcg);
			for(int i=0;i<ai.size();i++)
				pi.set(i,pi.get(i)/dcg);
			int dp[][] = new int[2][w+1];
			for(int i=1;i<=ai.size();i++){
				for(int j=1;j<=w;j++)
					if(j-pi.get(i-1)>=0)
						dp[(i&1)][j]= Math.max(dp[(i-1)&1][j],
								dp[(i-1)&1][j-pi.get(i-1)]+ai.get(i-1));
					else
						dp[i&1][j] = dp[(i-1)&1][j];
			}
			//System.out.println(dp[ai.size()&1][w]);
			sb.append(dp[ai.size()&1][w]).append("\n");
		}
		System.out.print(sb);
	}
	
	static class Scanner
        {
                BufferedReader br;
                StringTokenizer st;
                
                public Scanner(){
                System.setOut(new PrintStream(new BufferedOutputStream(System.out), true));
                        br = new BufferedReader(new InputStreamReader(System.in));
                }
                
                public String next(){
                        while(st == null || !st.hasMoreTokens()){
                                try { st = new StringTokenizer(br.readLine()); }
                                catch(Exception e) { throw new RuntimeException(); }
                        }
                        return st.nextToken();
                }

                public int nextInt(){
                        return Integer.parseInt(next());
                }
                
                public double nextDouble(){
                        return Double.parseDouble(next());
                }
                
                public String nextLine(){
                        st = null;
                        try { return br.readLine(); }
                        catch(Exception e) { throw new RuntimeException(); }
                }
                
                public boolean endLine(){
                        try {
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