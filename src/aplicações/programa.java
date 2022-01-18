package aplica��es;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import xadrez.PartidaXadrez;
import xadrez.Pe�aXadrez;
import xadrez.Posi��oXadrez;
import xadrez.XadrezException;

public class programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<Pe�aXadrez> capturada = new ArrayList<>();
		
		while (true) {
		
			try {
				UI.clearScreen();
				UI.printPartida(partidaXadrez, capturada);
				
				System.out.println();
				System.out.print("Inicio: ");
				Posi��oXadrez inicio = UI.lerPosi��oXadrez(sc);
				
				boolean [][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(inicio);
				UI.clearScreen();
				UI.printTabuleiro(partidaXadrez.getPe�as(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("Destino: ");
				Posi��oXadrez destino = UI.lerPosi��oXadrez(sc);
			
				Pe�aXadrez capturaPe�a = partidaXadrez.executarMovimentoPe�a(inicio, destino);
				
				if (capturaPe�a != null) {
					capturada.add(capturaPe�a); 
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
		
	}
}
