import java.io.*;
import java.util.*;
public class beehives{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int atoi(String n){return Integer.parseInt(n);}
	static int bfs(int inicio, ArrayList<ArrayList<Integer>> adj)throws IOException{
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(inicio);
		boolean vis[] = new boolean[adj.size()];//visitados
		int d[] = new int[adj.size()];//distancia a cada uno desde inicio
		int papa[] = new int[adj.size()];
		papa[inicio] = -1;
		vis[inicio] = true;
		int min =Integer.MAX_VALUE;
		while(!q.isEmpty()){
			int na = q.poll();
			for(int i: adj.get(na)){
				if(papa[na]==i)continue;
				if(!vis[i]){
					papa[i] = na;
					vis[na] = true;
					d[i] = d[na]+1;
					q.offer(i);
					}
				else{//ciclo
					min = Math.min(min, d[na]+d[i]+1);
					return min;
					}
			}
		}
		return min;
	}
	public static void main(String args[])throws IOException{
		SuperScanner sc = new SuperScanner();
		//int t = atoi(br.readLine());
		int t = sc.nextInt();
		int cas = 0;
		StringBuilder sb = new StringBuilder("");
		while(t-->0){
			//br.readLine();
			sc.nextLine();
			//StringTokenizer st = new StringTokenizer(br.readLine());
			//int n = atoi(st.nextToken()), a = atoi(st.nextToken());
			int n = sc.nextInt(), a = sc.nextInt();
			ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<n;i++)
				adj.add(new ArrayList<Integer>());
			for(int i=0;i<a;i++){
				//st= new StringTokenizer(br.readLine());
				//int c = atoi(st.nextToken()), d = atoi(st.nextToken());
				int c = sc.nextInt(), d = sc.nextInt();
				adj.get(c).add(d);
				adj.get(d).add(c);
			}
			long res = Integer.MAX_VALUE;
			for(int i=0;i<n;i++){
				long val = bfs(i, adj);
				res = Math.min(res, val);
				}
			sb.append("Case "+ ++cas+": ");
			if(res==Integer.MAX_VALUE)
				sb.append("impossible").append("\n");
			else
				sb.append(res).append("\n");
		}
		System.out.print(sb);
	}
	
	
	static class SuperScanner
        {
                char[] buffer;
                InputStreamReader isr;
                int p;
                int start;
                
                public SuperScanner()
                {
                        buffer = new char[6000001];
                        isr = new InputStreamReader(System.in);         
                        read(0);
                }
                
                void read(int init)
                {
                        try
                        {
                                int tam;
                                if((tam = isr.read(buffer, init, buffer.length - init)) < buffer.length - init)
                                {
                                        if(tam < 0)
                                                tam = 0;
                                        buffer[tam] = '\0';
                                }
                        }
                        catch(Exception e)
                        {
                                throw(new RuntimeException());
                        }
                }
                
                public void readNext()
                {
                        int tam = buffer.length;
                        int pos = p;
                        while(pos < tam && buffer[pos] <= ' ')
                                pos++;
                        if(pos == tam)
                        {
                                read(0);
                                readNext();
                                return;
                        }
                        start = pos;
                        while(pos < tam && buffer[pos] > ' ')
                                pos++;
                        if(pos == tam)
                        {
                                System.arraycopy(buffer, start, buffer, 0, buffer.length - start);
                                read(buffer.length - start);
                                p = start;
                                readNext();
                                return;
                        }
                        p = pos;
                }
                
                public String next()
                {
                        readNext();
                        return new String(buffer, start, p - start);
                }
                
                
                public long nextLong()
        {
                        readNext();
            int d = start;
            int pos = p;
            long r = 0;
            boolean n = buffer[d] == '-';
            if(n)
                d++;
            r -= buffer[d++] - '0';
            while (d < pos && (r = (r << 1) + (r << 3)) <= 0)
                    r -= buffer[d++] - '0';
            return n ? r : -r;
        }
                
                public int nextInt()
                {
                        return (int) nextLong();
                }
                
                public double nextDouble()
                {
                        return Double.parseDouble(next());
                }
                
                public String nextLine()
                {
                        int tam = buffer.length;
                        int pos = p;
                        while(pos < tam && buffer[pos] != '\n')
                                pos++;
                        if(pos == tam)
                        {
                                read(0);
                                return nextLine();
                        }
                        start = ++pos;
                        while(pos < tam && buffer[pos] != '\n')
                                pos++;
                        if(pos >= tam)
                        {
                                System.arraycopy(buffer, start, buffer, 0, buffer.length - start);
                                read(buffer.length - start);
                                return nextLine();
                        }
                        p = pos;
                        return new String(buffer, start, p - start);
                }
                
                public boolean EOF()
                {
                        int tam = buffer.length;
                        int pos = p;
                        while(pos < tam && buffer[pos] <= ' ')
                                if(buffer[pos++] == '\0')
                                        return true;
                        pos++;
                        if(pos >= tam)
                        {
                                if(p == 0)
                                        return false;
                                System.arraycopy(buffer, p, buffer, 0, buffer.length - p);
                                read(buffer.length - p);
                                p = 0;
                                return EOF();
                        }
                        return false;
                }
        }
}