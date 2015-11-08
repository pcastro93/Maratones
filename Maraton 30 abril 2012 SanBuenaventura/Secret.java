import java.io.*;

public class Secret {
	public static void main(String[] args) throws IOException{
		//mmm el algoritmo habla por si solo :P
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int fin = Integer.parseInt(br.readLine());
		while(fin-->0){
			String palabra = br.readLine();
			int lon = 0;
			int ini = 0;
			for(int i=palabra.length()-1;i>-1;i--){
				int j = 0,jj=i;
				int cont = 0;
				while((j<palabra.length())&& (jj>-1) && palabra.charAt(j)==palabra.charAt(jj)){
					j++;
					jj--;
					cont++;
				}
				if(cont>lon){
					lon = cont;
					ini = jj;
				}
			}
			System.out.println(palabra.substring(ini+1,ini+1+lon));
		}
	}

}
