package aplicações;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;
import xadrez.PosiçãoXadrez;
import xadrez.XadrezException;

public class programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PeçaXadrez> capturada = new ArrayList<>();
		
		while (!partidaXadrez.getCheckMate()) {
		
			try {
				UI.clearScreen();
				UI.printPartida(partidaXadrez, capturada);
				
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
				
				if (capturaPeça != null) {
					capturada.add(capturaPeça); 
				}
				
				if (partidaXadrez.getPromoção() != null) {
					System.out.print("Digite a peça que deseja promover (B/C/T/D): ");
					String tipo = sc.nextLine().toUpperCase();
					while (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("D")) {
						System.out.print("Valor inválido! Digite a peça que deseja promover (B/C/T/D): ");
						tipo = sc.nextLine().toUpperCase();
					}
					partidaXadrez.substituirPeçaPromovida(tipo);
				}  

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
		
		UI.clearScreen();
		UI.printPartida(partidaXadrez, capturada);
		
	}
}
