import java.io.*;
import java.util.*;
public class seats{
	public static char mat[][],matt[][];
	public static boolean termine = false;
	public static void main(String args[]) throws IOException{
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		String tmp = "";
		while(!(tmp=lector.readLine()).equals("0 0")){
			termine = false;
			int si = Integer.parseInt(tmp.substring(0,tmp.indexOf(" ")));
			mat = new char[si][si];
			matt = new char[si][si];
			for(int n = 0;n<mat.length;n++){
				tmp = lector.readLine();
				mat[n]= tmp.toCharArray();
				matt[n]=tmp.toCharArray();
			}
			hagale(new Vector<Integer>(),'A');
			imprimir(mat);
		}

	}
	public static void imprimir(char a[][]){
		for(int n = 0;n<a.length;n++){
			for(int m= 0;m<a[n].length;m++)System.out.print(a[n][m]);
			System.out.println();
		}
	}
	public static void hagale(Vector<Integer> cambio,char letra){
		//imprimir(mat);
		int r = listo();
		//System.out.println(cambio+" "+letra+" "+r);
		if(termine)return;
		if(r==-1){
			termine = true;
			//for(int n =0;n<matt.length;n++)
			//for(int m = 0;m<matt[n].length;m++)matt[n][m]=mat[n][m];
			return;
		}
		for(int n = r;n<mat.length;n++){
			for(int m = 0;m<mat[n].length;m++){
				boolean entro1 = false;
				if(mat[n][m]>='0' && mat[n][m]<='9'){
					entro1 = true;
					int a = mat[n][m]-'0';
					for(int k = 1;k<=a;k++){
						if(a%k==0){
							for(int o = 0;o<a;o++){
								boolean puedo = true;
								int esY = n-o/k;
								int esX = m-o%k;
								for(int x = 0;x<k && puedo;x++)
									for(int y = 0;y<a/k && puedo;y++)
										if((x+esX<0 || x+esX>=mat[0].length || y+esY<0 || y+esY>=mat.length) || ((x+esX!=m || y+esY!=n) && mat[y+esY][x+esX]!='.'))
											puedo = false;
								if(puedo && !termine){
									Vector<Integer> cambio2 = new Vector<Integer>(),cambio3 = new Vector<Integer>();
									for(int u = 0;u<cambio.size();u++)
										cambio2.add(cambio.get(u));
									for(int x = 0;x<k && puedo;x++)
										for(int y = 0;y<a/k && puedo;y++){
											cambio2.add((x+esX)*10000+(y+esY)*100+(int)mat[y+esY][x+esX]);
											cambio3.add((x+esX)*10000+(y+esY)*100+(int)mat[y+esY][x+esX]);
											mat[y+esY][x+esX] = letra;
										}
									hagale(cambio2,(char)((int)letra+1));
									if(!termine){
										for(int u = 0;u<cambio3.size();u++)
											mat[(cambio3.get(u)/100)%100][cambio3.get(u)/10000] = (char)(cambio3.get(u)%100);
									}else return;
								}
							}

						}
					}
				}
				if(entro1)return;
			}
		}
	}
	public static int listo(){
		for(int n = 0;n<mat.length;n++)
			for(int m = 0;m<mat[n].length;m++)
				if(mat[n][m]>='0' && mat[n][m]<='9')return n;
		return -1;
	}
}
