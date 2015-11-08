import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class sokoban {
	public static void imprimir(char[][]tab){
		for(int i=0;i<tab.length;i++)
			System.out.println(new String(tab[i]));
	}
//	public static boolean bien()
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sokoban.in"));
		String input = "";
		int cont = 1;
		while ((input = br.readLine()) != null && !(input).equals("0 0")) {
			int alto = Integer.parseInt(input.split(" ")[0]);
			int ancho = Integer.parseInt(input.split(" ")[1]);
			char tab [][]= new char[alto][ancho];
			int x = -1;
			int y = -1;
			for(int i=0;i<alto;i++){
				input =br.readLine();
				tab[i]=input.toCharArray();
				if(input.indexOf('w')!=-1){
					x = input.indexOf('w');
					y = i;
				}else if(input.indexOf("W")!=-1){x=input.indexOf("W");y=i;}
			}
			String instruc = br.readLine();
			for(int k=0;k<instruc.length();k++){
				//imprimir(tab);
if(y<1)y=1;
					if(x<1)x=1;
					if(y>=tab.length-1)y=tab.length-2;
					if(x>=tab[y].length-1)x=tab[y].length-2;
                                boolean entro = false;
                                for(int l=0;l<tab.length;l++){
                                        if((new String(tab[l])).contains("b")){
                                                entro = true;
                                                break;
                                        }
                                        }
                                if(!entro)break;

				if(instruc.charAt(k)=='U'){
					if(y<=1 || ((tab[y-1][x]=='b' || tab[y-1][x]=='B')&& ((tab[y-2][x]=='#') || (tab[y-2][x]=='b' || tab[y-2][x]=='B')))){
					}else if((tab[y-1][x]=='b' || tab[y-1][x]=='B')){
						tab[y-2][x] = (tab[y-2][x]=='+'?'B':'b');
						tab[y-1][x] = ((tab[y-1][x]=='+' || tab[y-1][x]=='B')?'W':'w');
						tab[y][x] = ((tab[y][x]=='W')?'+':'.');
						y-=1;
					}
					else if(tab[y-1][x]=='.' || tab[y-1][x]=='+'){
						tab[y-1][x] = ((tab[y-1][x]=='+' || tab[y-1][x]=='B')?'W':'w');
						tab[y][x] = ((tab[y][x]=='W')?'+':'.');
						y-=1;
					}
				}
				if(instruc.charAt(k)=='D'){
					if(y>=tab.length-2 ||((tab[y+1][x]=='b' || tab[y+1][x]=='B')&& ((tab[y+2][x]=='#') || (tab[y+2][x]=='b' || tab[y+2][x]=='B')))){
					}else if((tab[y+1][x]=='b' || tab[y+1][x]=='B')){
						tab[y+2][x] = (tab[y+2][x]=='+'?'B':'b');
						tab[y+1][x] = ((tab[y+1][x]=='+' || tab[y+1][x]=='B')?'W':'w');
						tab[y][x] = ((tab[y][x]=='W')?'+':'.');
						y+=1;
					}
					else if(tab[y+1][x]=='.'|| tab[y+1][x]=='+'){
						tab[y+1][x] = ((tab[y+1][x]=='+' || tab[y+1][x]=='B')?'W':'w');
						tab[y][x] = ((tab[y][x]=='W')?'+':'.');
						y+=1;
					}
				}
				if(instruc.charAt(k)=='R'){
					if(x>=tab[y].length-2 || ((tab[y][x+1]=='b' || tab[y][x+1]=='B')&& ((tab[y][x+2]=='#') || (tab[y][x+2]=='b' || tab[y][x+2]=='B')))){
					}else if((tab[y][x+1]=='b' || tab[y][x+1]=='B')){
						tab[y][x+2] = (tab[y][x+2]=='+'?'B':'b');
						tab[y][x+1] = ((tab[y][x+1]=='+' || tab[y][x+1]=='B')?'W':'w');
						tab[y][x] = ((tab[y][x]=='W')?'+':'.');
						x+=1;
					}else if(tab[y][x+1]=='.'|| tab[y][x+1]=='+'){
						tab[y][x+1] = ((tab[y][x+1]=='+' || tab[y][x+1]=='B')?'W':'w');
						tab[y][x] = ((tab[y][x]=='W')?'+':'.');
						x+=1;
					}
				}
				if(instruc.charAt(k)=='L'){
					if(x<=1 ||((tab[y][x-1]=='b' || tab[y][x-1]=='B')&& ((tab[y][x-2]=='#') || (tab[y][x-2]=='b' || tab[y][x-2]=='B')))){
					}else if((tab[y][x-1]=='b' || tab[y][x-1]=='B')){
						tab[y][x-2] = (tab[y][x-2]=='+'?'B':'b');
						tab[y][x-1] = ((tab[y][x-1]=='+' || tab[y][x-1]=='B')?'W':'w');
						tab[y][x] = ((tab[y][x]=='W')?'+':'.');
						x-=1;
					}else if(tab[y][x-1]=='.'|| tab[y][x-1]=='+'){
						tab[y][x-1] = ((tab[y][x-1]=='+' || tab[y][x-1]=='B')?'W':'w');
						tab[y][x] = ((tab[y][x]=='W')?'+':'.');
						x-=1;
					}
					
				}
				
				}
			
			String result = "complete";
			for(int k=0;k<tab.length;k++){
				if((new String(tab[k])).contains("b")){
					result = "incomplete";
					break;
				}
			}
			System.out.println("Game "+ (cont++) + ": " +result);
			imprimir(tab);
			}
		}

}
