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
		
		while (!partidaXadrez.getCheckMate()) {
		
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
				
				if (partidaXadrez.getPromo��o() != null) {
					System.out.print("Digite a pe�a que deseja promover (B/C/T/D): ");
					String tipo = sc.nextLine().toUpperCase();
					while (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("D")) {
						System.out.print("Valor inv�lido! Digite a pe�a que deseja promover (B/C/T/D): ");
						tipo = sc.nextLine().toUpperCase();
					}
					partidaXadrez.substituirPe�aPromovida(tipo);
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
