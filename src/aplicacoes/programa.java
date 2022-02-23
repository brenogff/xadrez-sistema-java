package aplicacoes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaXadrez> capturada = new ArrayList<>();
		
		while (!partidaXadrez.getCheckMate()) {
		
			try {
				UI.clearScreen();
				UI.printPartida(partidaXadrez, capturada);
				
				System.out.println();
				System.out.print("Inicio: ");
				PosicaoXadrez inicio = UI.lerPosicaoXadrez(sc);
				
				boolean [][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(inicio);
				UI.clearScreen();
				UI.printTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);
			
				PecaXadrez capturaPeca = partidaXadrez.executarMovimentoPeca(inicio, destino);
				
				if (capturaPeca != null) {
					capturada.add(capturaPeca); 
				}
				
				if (partidaXadrez.getpromocao() != null) {
					System.out.print("Digite a peca que deseja promover (B/C/T/D): ");
					String tipo = sc.nextLine().toUpperCase();
					while (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("D")) {
						System.out.print("Valor inválido! Digite a peca que deseja promover (B/C/T/D): ");
						tipo = sc.nextLine().toUpperCase();
					}
					partidaXadrez.substituirPecaPromovida(tipo);
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
