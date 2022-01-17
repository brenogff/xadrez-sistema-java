package aplicações;

import java.util.Scanner;
import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;
import xadrez.PosiçãoXadrez;

public class programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		while (true) {
			
		UI.printTabuleiro(partidaXadrez.getPeças());
		
		System.out.println();
		System.out.print("Inicio: ");
		PosiçãoXadrez inicio = UI.lerPosiçãoXadrez(sc);
		
		System.out.println();
		System.out.print("Destino: ");
		PosiçãoXadrez destino = UI.lerPosiçãoXadrez(sc);
		
		PeçaXadrez capturaPeça = partidaXadrez.executarMovimentoPeça(inicio, destino);
		}

	}

}
