package aplica��es;

import java.util.Scanner;
import xadrez.PartidaXadrez;
import xadrez.Pe�aXadrez;
import xadrez.Posi��oXadrez;

public class programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		while (true) {
			
		UI.printTabuleiro(partidaXadrez.getPe�as());
		
		System.out.println();
		System.out.print("Inicio: ");
		Posi��oXadrez inicio = UI.lerPosi��oXadrez(sc);
		
		System.out.println();
		System.out.print("Destino: ");
		Posi��oXadrez destino = UI.lerPosi��oXadrez(sc);
		
		Pe�aXadrez capturaPe�a = partidaXadrez.executarMovimentoPe�a(inicio, destino);
		}

	}

}
