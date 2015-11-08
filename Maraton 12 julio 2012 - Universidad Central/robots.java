import java.io.*;
import java.util.*;

public class robots {
	public static int atoi(String n) {
		return Integer.parseInt(n);
	}

	public static boolean bfs(char lab[][]) {
		Queue<String> hacer = new LinkedList<String>();
		boolean visitados[][] = new boolean[lab.length][lab.length];
		hacer.offer("0 0");
		visitados[0][0] = true;
		while (!hacer.isEmpty()) {
			String actual = hacer.poll();
			int fila = atoi(actual.split(" ")[0]);
			int columna = atoi(actual.split(" ")[1]);
			if (fila == (lab.length - 1) && (lab[fila].length - 1) == columna)
				return true;
			for (int a = -1; a <= 1; a++) {
				for (int b = -1; b <= 1; b++) {
					if ((a == 0 || b == 0) && (a != 0 || b != 0)) {
						if (fila + a >= 0 && fila + a < lab.length&& columna + b >= 0&& columna + b < lab[fila].length) {
							if (lab[fila + a][columna + b] != '#'&& !visitados[fila + a][columna + b]) {
								visitados[fila + a][columna + b] = true;
								hacer.offer((fila + a) + " " + (columna + b));
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static void main(String fdfd[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while ((input = br.readLine()) != null && !input.equals("0")) {
			int f = atoi(input);
			int formas[][] = new int[f][f];
			char lab[][] = new char[f][f];
			int fila = 0;
			while (f-- > 0 && (input = br.readLine()) != null) {
				char[] cad = input.toCharArray();
				int col = 0;
				for (char c : cad)
					lab[fila][col++] = c;
				fila++;
			}
			formas[0][0] = 1;
			int mod = Integer.MAX_VALUE;
			for (int i = 0; i < formas.length; i++)
				for (int j = 0; j < formas[i].length; j++) {
					if (i == 0 && j == 0)
						continue;
					if (lab[i][j] == '#')
						continue;
					if (j == 0)
						formas[i][j] = formas[i - 1][j] % mod;
					else if (i == 0)
						formas[i][j] = formas[i][j - 1] % mod;
					else {
						long tmp = ((long) formas[i - 1][j] + (long) formas[i][j - 1])
								% mod;
						formas[i][j] = (int) tmp;
					}
				}
			if (formas[formas.length - 1][formas.length - 1] == 0)
				if (bfs(lab))
					System.out.println("THE GAME IS A LIE");
				else
					System.out.println("INCONCEIVABLE");
			else
				System.out.println(formas[formas.length - 1][formas.length - 1]);
		}
	}
}
