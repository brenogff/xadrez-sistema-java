package xadrez.pe�as;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Pe�aXadrez;

public class Torre extends Pe�aXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);

	}
	
	@Override
	public String toString() {
		return "T";
	}

}
