package aplicações;

import xadrez.PeçaXadrez;

public class UI {

	public static void printTabuleiro(PeçaXadrez[][] peças) {
		for (int i=0; i<peças.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j=0; j<peças.length; j++) {
				printPeça(peças[i][j]);
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");
	}
	
	public static void printPeça(PeçaXadrez peça) {
		if (peça == null) {
			System.out.print("-");
		}
		else {
			System.out.print(peça);
		}
		System.out.print(" ");
	}
}
