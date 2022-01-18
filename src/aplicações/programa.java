package aplicações;

import java.util.InputMismatchException;
import java.util.Scanner;
import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;
import xadrez.PosiçãoXadrez;
import xadrez.XadrezException;

public class programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		while (true) {
		
			try {
				UI.clearScreen();
				UI.printTabuleiro(partidaXadrez.getPeças());
				
				System.out.println();
				System.out.print("Inicio: ");
				PosiçãoXadrez inicio = UI.lerPosiçãoXadrez(sc);
				
				boolean [][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(inicio);
				UI.clearScreen();
				UI.printTabuleiro(partidaXadrez.getPeças(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("Destino: ");
				PosiçãoXadrez destino = UI.lerPosiçãoXadrez(sc);
			
				PeçaXadrez capturaPeça = partidaXadrez.executarMovimentoPeça(inicio, destino);
			}
				catch(XadrezException e) {
					System.out.println(e.getMessage());
					sc.nextLine();
				}
				catch(InputMismatchException e) {
					System.out.println(e.getMessage());
					sc.nextLine();
				}
		}
		
	}
}
