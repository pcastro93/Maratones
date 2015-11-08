import java.io.*;
import java.util.*;

public class I{
static int read(int idx, int []tree){
	int sum = 0;
	while (idx > 0){
		sum += tree[idx];
		idx -= (idx & -idx);
	}
	return sum;
}
static void update(int idx ,int val, int []tree){
	while (idx < tree.length){
		if(tree[idx]+tree[idx]+val>=0)//mio
			tree[idx] += val;
		idx += (idx & -idx);
	}
}
static int readSingle(int idx, int []tree){
	int sum = tree[idx]; // sum will be decreased
	if (idx > 0){ // special case
		int z = idx - (idx & -idx); // make z first
		idx--; // idx is no important any more, so instead y, you can use idx
		while (idx != z){ // at some iteration idx (y) will become z
			sum -= tree[idx]; 
		// substruct tree frequency which is between y and "the same path"
			idx -= (idx & -idx);
		}
	}
	return sum;
}
static int signum(int a){
	if(a==0)
		return 0;
	if(a>0)
		return 1;
	return -1;
}
public static void main(String jkfd[])throws Exception{
	//BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	Scanner sc = new Scanner();
	int n,k,i;
	StringBuilder sb = new StringBuilder();
	String input = "";
	while((input = sc.nextLine())!=null && !input.equals("")){
	String div[] = input.split(" ");
	n = Integer.parseInt(div[0]);
	k = Integer.parseInt(div[1]);
	int treec[] = new int[n+1];
	int treem[] = new int[n+1];
	//input = sc.readLine();
	for(i=0;i<n;i++){
		int ll = sc.nextInt();//Integer.parseInt(input.split(" ")[i]);
		ll= signum(ll);
		if(ll==0)
			update(i+1, 1, treec);
		if(ll==-1)
			update(i+1, 1,treem);
		}
	char op;
	int a,b;
	while(k-->0){
		//input = sc.readLine();
		op = sc.next().charAt(0);//input.charAt(0);
		a = sc.nextInt();//Integer.parseInt(input.split(" ")[1]);
		int bb = sc.nextInt();//Integer.parseInt(input.split(" ")[2]);
		b = signum(bb);
		int val = readSingle(a, treem);
		int valc = readSingle(a, treec);
		if(op=='C'){
			if(b==0){
				if(val==valc && valc==0){//era positivo
				update(a,1,treec);
				}
				else if(val>0){//era menos
					update(a,-1,treem);
					update(a,1,treec);
				}else{}//ya era cero, no hace nada
			}else if(b==-1){
				if(val==valc && valc==0){//era positivo
					update(a,1,treem);
				}else if(val>0){}//era negativo, no hace nada
				else{//era cero
					update(a,-1, treec);
					update(a,1,treem);
				}
			}else{
				if(val==0 && valc==0){}//no naga nada, ya era positivo
				else{
					if(val>0)//era menos
						update(a, -1, treem);
					else//era cero
						update(a, -1, treec);
				}
			}
		}
		if(op=='P'){
			//System.out.print(read(bb, treec)+" "+read(a,treec));
			int ceros;
			ceros = read(bb, treec)-read(a-1,treec);
			if(ceros>0)
				sb.append("0");
			else{
				int menos = read(bb, treem)-read(a-1,treem);
				if((menos&1)==0)
					sb.append("+");
				else
					sb.append("-");
			}
		}
	}
	sb.append("\n");
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
                                return st.hasMoreTokens();
                        }
                        catch(Exception e) { throw new RuntimeException(); }
                }
        }

}
