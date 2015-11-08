import java.io.*;
import java.util.*;
public class haunted{
	//public static int mat[][];
	static ArrayList<HashMap<Integer, Integer>> adj;
	public static boolean grave[][];
	static int atoi(String n){return Integer.parseInt(n);}
	
	static void bellman(int ini, int fin){
		long dis[] = new long[adj.size()];
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[ini] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		boolean ec[] = new boolean[adj.size()];//en cola?
		q.offer(ini);
		ec[ini] = true;
		int mat[][] = new int[adj.size()][adj.size()];
		mat[0][0]++;
		int aristas = 0;
		for(int i=0;i<adj.size();i++)
			aristas+=adj.get(i).size();
		boolean never = false;
		while(!q.isEmpty() && !never){
			int na = q.poll();
			ec[na] =false;
			if(na==fin)
				continue;
			for(int v: adj.get(na).keySet()){
				if(dis[v]>dis[na]+adj.get(na).get(v)){
					mat[na][v]++;
					if(mat[na][v]==adj.size())
						never = true;
					dis[v] = dis[na]+adj.get(na).get(v);
					if(!ec[v]){
						ec[v] = true;
						q.offer(v);
					}
				}
			}
		}
		boolean hubo = false;
		if(never){
			System.out.println("Never");
			return;
			}
		/*for(int n = 0;n<adj.size() && !hubo;n++)
			for(int m: adj.get(n).keySet()){
				if(dis[n]+adj.get(n).get(m)<dis[m]){
					System.out.println("Never");
					hubo = true;
					break;
				}
			}*/
		if(!hubo){
			if(dis[fin]==Integer.MAX_VALUE)System.out.println("Impossible");
			else System.out.println(dis[fin]);
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader lector= new BufferedReader(new InputStreamReader(System.in));
		String tmp = "";
		int cas = 0;
		while(!(tmp = lector.readLine()).equals("0 0")){
			//System.out.println(++cas);
			int x = Integer.parseInt(tmp.substring(0,tmp.indexOf(" "))),y = Integer.parseInt(tmp.substring(tmp.indexOf(" ")+1));
			//System.out.println(x+" "+y);
			//cantidad de rips
			int t = Integer.parseInt(lector.readLine());
			//System.out.println(t);
			int max = Math.max(x,y);//xy+x/x  xy+y/y
			grave = new boolean[y][x];//true si es un rip
			//mat = new int[x*y][x*y];
			adj = new ArrayList<HashMap<Integer,Integer>>();
			for(int n=0;n<x*y;n++)
				adj.add(new HashMap<Integer, Integer>());
			//for(int n = 0;n<mat.length;n++)
			//	Arrays.fill(mat[n],Integer.MAX_VALUE);
			//lectura rips
			for(int n = 0;n<t;n++){
				StringTokenizer st = new StringTokenizer(lector.readLine());
				int xx = atoi(st.nextToken()),yy = atoi(st.nextToken());
				grave[yy][xx]=true;
			}
			
			int coX = (x==max)?1:y;
			int coY = (x==max)?x:1;
			
			boolean hoyos[][] = new boolean[y][x];
			//leer hoyos
			t = Integer.parseInt(lector.readLine());
			for(int n = 0;n<t;n++){
				StringTokenizer st = new StringTokenizer(lector.readLine());
				int x1 = atoi(st.nextToken()),y1 = atoi(st.nextToken()),x2 = atoi(st.nextToken()),y2 = atoi(st.nextToken()),ti = atoi(st.nextToken());
				hoyos[y1][x1]= true;
				//mat[x1*coX+y1*coY][x2*coX+coY*y2] = ti;
				adj.get(x1*coX+y1*coY).put(x2*coX+coY*y2, ti);
			}
			//for(int n = 0;n<mat.length;n++)
			//for(int m = 0;m<mat[n].length;m++)
			//System.out.println(n+" "+m+" "+mat[n][m]);
			
			int dir[][] = {{0,1,0,-1},{1,0,-1,0}};//dx, dy
			for(int n = 0;n<x*y;n++){
				int pos[] = new int[2];
				pos[(x==max)?0:1]=n%max;
				pos[(x==max)?1:0]=n/max;
				if(grave[pos[1]][pos[0]] || hoyos[pos[1]][pos[0]])continue;
				for(int j = 0;j<dir[0].length;j++)
					if(pos[0]+dir[0][j]<x && pos[0]+dir[0][j]>-1 && pos[1]+dir[1][j]<y && pos[1]+dir[1][j]>-1 && !grave[pos[1]+dir[1][j]][pos[0]+dir[0][j]])
						//mat[coX*pos[0]+coY*pos[1]][coX*(pos[0]+dir[0][j])+coY*(pos[1]+dir[1][j])]=1;
						adj.get(coX*pos[0]+coY*pos[1]).put(coX*(pos[0]+dir[0][j])+coY*(pos[1]+dir[1][j]),1);
			}
			bellman(0,x*y-1);
		}
	}
}
