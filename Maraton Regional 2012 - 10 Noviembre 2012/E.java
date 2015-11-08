import java.io.*;
import java.util.*;
public class E{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int atoi(String n){return Integer.parseInt(n);}

	static void leerPolinomio(double p1[])throws Exception{
		String div[] = br.readLine().trim().split(" ");
			for(int i=0;i<p1.length;i++)
				p1[i] = atoi(div[i]);
	}
	static double evaluarProlinomio(double p[], double x){
		double res = 0;
		for(int i=0;i<p.length;i++)
			res+=Math.pow(x,i)*p[i];
		return res;
	}
	static double[] derivarPolinomio(double p[]){
		double res[] = new double[p.length-1];
		for(int i=p.length-1;i>0;i--)
			res[i-1] = i*p[i];
		return res;
	}
	static double f(double p[],double x){
		return evaluarPolinomio(p, x);
	}
	static double encontrarRaiz(double p[], double pp[]){
		//newton-raphson
		double x1 = 1;//valor aleatorio xD
		while(Math.abs(f(p,x1))>=eps){
			x1 = x1-(f(p,x1)/f(pp,x1));
			//x1 = truncar(x1);
			}
		return x1;
	}
	static double[] dividirPolinomio(double p[], double x){
		//se divide cada uno de los coeficientes del polinomio por un x
		double res[] = Arrays.copyOf(p, p.length);
		for(int i=0;i<res.length;i++)
			res[i]/=x;
		return res;
	}
	static double[] encontrarRaices(double p[]){
		double pp[] = derivarPolinomio(p);
		ArrayList<Double> raices = new ArrayList<Double>
		raices.add(encontrarRaiz(p, pp));
		Collections.sort(raices);
		double[] res = new double[raices.size()];
		return res;
		}
	static double reglaSimpson38(double p[], double q[], double a, double b){
		//integral de f (que es de la forma p/q. p y q son polinomios) entre a y b.
		double y[] = new double[4];
		double h = (b-a)/3.0;
		double x1 = a+h;
		double x2 = a+2*h;
		y[0] = evaluarPolinomio(p,a)/evaluarPolinomio(q,a);//calc.evaluar(exp.replaceAll("x",""+Math.toDegrees(a))); //a me lo dan en radianes por eso lo paso a grados
		y[1] = evaluarPolinomio(p,x1)/evaluarPolinomio(q,x1);//calc.evaluar(exp.replaceAll("x",""+Math.toDegrees(x1)));
		y[2] = evaluarPolinomio(p,x2)/evaluarPolinomio(q,x2);//calc.evaluar(exp.replaceAll("x",""+Math.toDegrees(x2)));
		y[3] = evaluarPolinomio(p,b)/evaluarPolinomio(q,b);//calc.evaluar(exp.replaceAll("x",""+Math.toDegrees(b)));
		return (3.0/8)*h*(y[0]+3*y[1]+3*y[2]+y[3]);
	}
	public static void main(String args[])throws Exception{
		for(double c: pp)
			System.out.print(c+" ");
		String input = "";
		while((input = br.readLine())!=null){
			double W,D,A,K;
			String div[] = input.trim().split(" ");
			W = atoi(div[0]);
			D = atoi(div[1]);
			A = atoi(div[2]);
			K = atoi(div[3]);
			
			double p1[] = new double[(int)K+1];
			double q1[] = new double[(int)K+1];
			double p2[] = new double[(int)K+1];
			double q2[] = new double[(int)K+1];
				
			leerPolinomio(p1);
			leerPolinomio(q1);
			leerPolinomio(p2);
			leerPolinomio(q2);
			
			/*for(double c:p1)
				System.out.print(c+" ");
			System.out.println();
			for(double c:q1)
				System.out.print(c+" ");
			System.out.println();
			for(double c:p2)
				System.out.print(c+" ");
			System.out.println();
			for(double c:q2)
				System.out.print(c+" ");*/
		}
	}
}
