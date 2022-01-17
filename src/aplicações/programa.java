package aplicações;


import xadrez.PartidaXadrez;

public class programa {

	public static void main(String[] args) {
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		UI.printTabuleiro(partidaXadrez.getPeças());

	}

}
