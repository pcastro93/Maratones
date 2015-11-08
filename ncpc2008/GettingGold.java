import java.io.*;
import java.util.*;
public class GettingGold{
	public static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String args[])throws IOException{
		File f = new File("./hunt/data/");
		for(File ff: f.listFiles()){
		if(!ff.getName().endsWith(".in"))continue;
		BufferedReader br =new BufferedReader(new FileReader(ff));
		String input = "";
		int w = atoi((input = br.readLine()).split(" ")[0]);
		int h = atoi(input.split(" ")[1]);
		char[][] mat= new char[h][w];
		int xi=0 ,yi=0;
		for(int i=0;i<h;i++){
			input = br.readLine();
			for(int j=0;j<w;j++){
				mat[i][j] = input.charAt(j);
				if(mat[i][j]=='P'){
				xi = i;
				yi =j;
				}
			}
		}
		Stack<coor> pila = new Stack<coor>();
		boolean vis[][] = new boolean[h][w];
		int oro = 0;
		pila.push(new coor(xi, yi));
		while(!pila.isEmpty()){
			coor act = pila.pop();
			int x = act.x;
			int y = act.y;
			if(vis[x][y])
			continue;
				//System.out.println(x+","+y+" "+mat[x][y]);
			vis[x][y] = true;
			if(mat[x][y]=='#')
				continue;
			if(mat[x][y]=='G'){
				mat[x][y] = '.';
				oro++;
				}
			boolean p = true;
			int cont = pila.size();
			//arriba
			if(x-1>=0 && (p&=mat[x-1][y]!='T'))
			pila.push(new coor(x-1, y));
			//abajo
			if(x+1<mat.length &&  (p&=mat[x+1][y]!='T'))
			pila.push(new coor(x+1, y));
			//derecha
			if(y+1<mat[0].length && (p&=mat[x][y+1]!='T'))
			pila.push(new coor(x, y+1));
			//izquierda
			if(y-1>=0 && (p&=mat[x][y-1]!='T'))
			pila.push(new coor(x, y-1));
			cont = pila.size()-cont;
			if(!p)
			for(int k=0;k<cont;k++){
			//System.out.println("E "+pila.peek().x+", "+pila.peek().y);
				pila.pop();
				}
		}
		System.out.println(oro);
		}
	}
}

class coor{
int x, y;
public coor(int x, int y){
this.x = x;
this.y = y;
}
}
