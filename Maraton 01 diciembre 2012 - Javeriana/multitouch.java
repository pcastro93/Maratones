import java.io.*;
import java.util.*;

public class multitouch {
	public static int atoi(String n) {return Integer.parseInt(n);}

	static void intercambiar(int ar[], int f,int s){
		int temp = ar[f];
		ar[f] = ar[s];
		ar[s] = temp;
	}
	static void reversar(int ar[]){
		for(int i=0,j=ar.length-1;i<j;i++,j--){
			intercambiar(ar,i,j);
		}
	}
	static void reversar(int ar[],int b,int f){
		for(int i=b,j=f;i<j;i++,j--){
			intercambiar(ar,i,j);
		}
	}
	static int[] nextPermutation(int ar[]){
		int in1,in2;
		for(in1 = ar.length-2,in2= ar.length-1;ar[in2]<=ar[in1] && in1!=0;in1--,in2--);
		if(ar[in2]<=ar[in1]){
			reversar(ar);
			return ar;
		}else{
			for (in2 = ar.length-1;in2 > in1 && ar[in2] <= ar[in1]; in2--);
			intercambiar(ar,in1,in2);
			reversar(ar,in1+1,ar.length-1);
			return ar;
		}
	}
	static void imprimir(int ar[]){
		for(int i=0;i<ar.length;i++)
			System.out.print(ar[i] + " ");
		System.out.println();
	}
	static ArrayList<int[]> permutaciones(int ar2[]){
		int ar[] = Arrays.copyOf(ar2, ar2.length);
		ArrayList<int[]> permuts = new ArrayList<int[]>();
		permuts.add(ar);
		if(ar.length==1)
			return permuts;
		int i=0;
		while(true){
			int temp[] = nextPermutation(ar);
			int r[] = Arrays.copyOf(temp, temp.length);
			if(Arrays.equals(r,ar2))
				break;
			permuts.add(r);
			i++;
		}
		return permuts;
	}
	static Punto dfs(int f, int c, char[][] mat){
		Punto res = new Punto(0,0);
		double tot = 0;
		Stack<Punto> visit = new Stack<Punto>();
		visit.push(new Punto(f,c));
		while(!visit.isEmpty()){
			tot+=1;
			Punto act = visit.pop();
			mat[(int)act.f][(int)act.c] = '.';
			int x = (int)act.f;
			int y = (int)act.c;
			res.f += act.f;
			res.c += act.c;
			if(y-1>=0 && mat[x][y-1]=='X')visit.push(new Punto(x,y-1));
			if(x-1>=0 && mat[x-1][y]=='X')visit.push(new Punto(x-1,y));
			if(x+1<mat.length && mat[x+1][y]=='X')visit.push(new Punto(x+1,y));
			if(y+1<mat[0].length && mat[x][y+1]=='X')visit.push(new Punto(x, y+1));
		}
		res.f /=(double)tot;
		res.c /=(double)tot;
		return res;
	}
	static ArrayList<Punto> getPuntos(char [][] com){
		ArrayList<Punto> puntosi = new ArrayList<Punto>();
		for(int i=0;i<com.length;i++){
			for(int j=0;j<com[i].length;j++)
				if(com[i][j]=='X')
					puntosi.add(dfs(i,j,com));
		}
		return puntosi;
	}
	static Punto getgp(ArrayList<Punto> puntosi){
		Punto gp1 = new Punto(0,0);
		for(int i=0;i<puntosi.size();i++){
			gp1.f +=puntosi.get(i).f;
			gp1.c +=puntosi.get(i).c;
			}
		gp1.f/=(double)puntosi.size();
		gp1.c/=(double)puntosi.size();
		return gp1;
	}
	static ArrayList<Vector> tv(Punto gp1, ArrayList<Punto> puntosi){
		ArrayList<Vector> touchVector1 = new ArrayList<Vector>();
		for(int i=0;i<puntosi.size();i++){
			touchVector1.add(new Vector(gp1.f, gp1.c, puntosi.get(i).f, puntosi.get(i).c));
		}
		return touchVector1;
	}
	static double dist(Punto p1, Punto p2){
		return Math.hypot((p1.f-p2.f), (p1.c-p2.c));
	}
	static double gs(Punto gp1,ArrayList<Punto> puntosi){
		double gs1 = 0;
		for(int i=0;i<puntosi.size();i++)
			gs1+=dist(puntosi.get(i),gp1);
		gs1/=(double)puntosi.size();
		return gs1;
	}
	static double propunto(Vector v1, Vector v2){
		return v1.x*v2.x + v1.y*v2.y;
	}
	static double ang(Vector v1, Vector v2){
		//el angulo entre 2 vectores es: cos-1((a.b)/(|a||b|))...esto es, el producto punto entre la multiplicacion de las magnitudes
		//return Math.acos((propunto(v1, v2))/(v1.m*v2.m));
		double tmp = Math.atan2(v2.y,v2.x)-Math.atan2(v1.y,v1.x);
		//System.out.println(tmp*180/Math.PI+" "+tmp);
		if(tmp<-Math.PI){
			tmp+=2*Math.PI;
			}
		else if(tmp>Math.PI){
			tmp-=2*Math.PI;
			}
		//System.out.println(tmp*180/Math.PI+" "+tmp);
		return tmp;
	}
	static int[] mejorcorr(ArrayList<Punto> puntosi, ArrayList<Punto> puntosf){
		int ar[] = new int[puntosi.size()];
		for(int i=0;i<ar.length;i++)
			ar[i] = i;
		ArrayList<int[]> per = permutaciones(ar);
		int mejor[] = null;
		double summ = Double.MAX_VALUE;
		for(int arr[]:per){
			double sum = 0;
			for(int k=0;k<puntosi.size();k++)
				sum+=Math.pow(dist(puntosi.get(k),puntosf.get(arr[k])),2.0);
			if(sum<summ){
				summ = sum;
				mejor = arr;
			}
		}
		return mejor;
	}
	public static void main(String fjkdfd[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		char com[][] = new char[15][30];
		char fin[][] = new char[15][30];
		while ((input = br.readLine()) != null && !input.equals("")) {
			int fila = 0;
			com[fila] = input.substring(0,input.indexOf(" ")).toCharArray();
			fin[fila] = input.substring(input.indexOf(" ")+1,input.length()).toCharArray();
			fila++;
		while(fila<15){
			input =br.readLine();
			com[fila] = input.substring(0,input.indexOf(" ")).toCharArray();
			fin[fila] = input.substring(input.indexOf(" ")+1,input.length()).toCharArray();
			fila++;
		}
		ArrayList<Punto> puntosi = getPuntos(com);
		ArrayList<Punto> puntosf = getPuntos(fin);
			
		Punto gp1 = getgp(puntosi);//grip point initial
		Punto gp2 = getgp(puntosf);//grip point final

		ArrayList<Vector> touchVector1 = tv(gp1, puntosi);
		ArrayList<Vector> touchVector2 = tv(gp2, puntosf);
		double panDistance = dist(gp1, gp2);
		double gs1 = gs(gp1, puntosi);//grip spread initial
		double gs2 = gs(gp2, puntosf);//grip spread final

		double zoomDistance = Math.abs(gs1-gs2);
		ArrayList<Double> touchRotations = new ArrayList<Double>();

		int mejor[] = mejorcorr(puntosi, puntosf);//mejor correspondencia

		ArrayList<Double> touchRotation = new ArrayList<Double>();
		for(int i=0;i<touchVector1.size();i++)
			touchRotation.add(ang(touchVector1.get(i),touchVector2.get( mejor[i] )));
		double gr = 0;//grip rotation
		for(int i=0;i<touchRotation.size();i++)
			gr+=touchRotation.get(i);
		gr/=(double)touchRotation.size();
		double rotationDistance = Math.abs(gs1*gr);//L = r*theta
		
		System.out.print(puntosi.size()+" ");
		if(panDistance>zoomDistance && panDistance>rotationDistance){
			System.out.println("pan");
		}else if(panDistance<zoomDistance && zoomDistance>rotationDistance){
			System.out.println("zoom "+((gs2<gs1)?"in":"out"));
		}else{
			boolean cond = gr<0;
			System.out.println("rotate "+((cond)?"":"counter-")+"clockwise");
		}
		}
	}
}
class Punto{
	double f, c;
	Punto(double f, double c){
		this.f = f;
		this.c = c;
	}
	public String toString(){
		return this.f+" "+this.c;
	}
}
class Vector{
	double x1, y1, x2, y2;
	double x, y;//componentes i,j
	double m;//magnitud
	Vector(double x1, double y1, double x2, double y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x = x2-x1;
		this.y = y2-y1;
		this.m = Math.hypot(this.x,this.y);
	}
}
