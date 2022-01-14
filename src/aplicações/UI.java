package aplica��es;

import xadrez.Pe�aXadrez;

public class UI {

	public static void printTabuleiro(Pe�aXadrez[][] pe�as) {
		for (int i=0; i<pe�as.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j=0; j<pe�as.length; j++) {
				printPe�a(pe�as[i][j]);
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");
	}
	
	public static void printPe�a(Pe�aXadrez pe�a) {
		if (pe�a == null) {
			System.out.print("-");
		}
		else {
			System.out.print(pe�a);
		}
		System.out.print(" ");
	}
}
