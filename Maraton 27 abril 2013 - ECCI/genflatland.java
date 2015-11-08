import java.io.*;
import java.util.*;
public class genflatland{
	static int atoi(String n){return Integer.parseInt(n);}
	static int aleatorio(int min, int max){
		return (int)(Math.random()*(max-min+1)+min);
	}
	public static void main(String fdjkfd[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		int nc = aleatorio(1,99);
		nc = 10000;//TODO
		sb.append(nc).append("\n");//numero de casos
		for(int k=0;k<nc;k++){
			int n, b,h;
			//System.out.println(k);
			n = aleatorio(1, 100);//numero de flatlanders
			sb.append(n).append("\n");
			do{
				b = aleatorio(2,100);//dimension
				h = aleatorio(2, 100);//dimension
			}while(b*h<n+100);
			sb.append(b+" "+h).append("\n");
			boolean pos[][] = new boolean[b][h];//posiciones de flatlanders para que no se repitan
			int nombre = 0;
			for(int i=0;i<n;i++){
				int x, y,d1, d2;
				boolean xx,fondo;//,faltan=false;
				//for(int u=0;u<pos.length;u++)
					//for(int v=0;v<pos[u].length &&!faltan;v++)
						//if(!pos[u][v])
							//faltan = true;
				//if(!faltan)
					//System.out.println("jfkdjfkdjkfdjk");
				do{
					x = aleatorio(1,b-1);
					y = aleatorio(1,h-1);
					xx = (aleatorio(1,2)==1);
					fondo = (aleatorio(1,2)==1);
					if(fondo){
						d1 = (xx?b:aleatorio(1, b-1));
						d2 = (!xx?h:aleatorio(1, h-1));
					}else{
						d1 = (xx?0:aleatorio(1, b-1));
						d2 = (!xx?0:aleatorio(1, h-1));
					}
				}while(pos[x][y]);
				pos[x][y] = true;
				sb.append(x+" "+y+" "+d1+" "+d2+" "+nombre).append("\n");
				nombre++;
			}
		}
		System.out.print(sb);
	}
}
