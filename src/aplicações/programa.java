package aplica��es;

import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;
import xadrez.PartidaXadrez;

public class programa {

	public static void main(String[] args) {
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		UI.printTabuleiro(partidaXadrez.getPe�as());

	}

}
