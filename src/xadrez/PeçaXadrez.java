package xadrez;

import tabuleiro.Peça;
import tabuleiro.Tabuleiro;

public abstract class PeçaXadrez extends Peça {
		
		private Cor cor;

		public PeçaXadrez(Tabuleiro tabuleiro, Cor cor) {
			super(tabuleiro);
			this.cor = cor;
		}

		public Cor getCor() {
			return cor;
		}

		
		
}
