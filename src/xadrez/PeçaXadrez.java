package xadrez;

import tabuleiro.Pe�a;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;

public abstract class Pe�aXadrez extends Pe�a {
		
		private Cor cor;

		public Pe�aXadrez(Tabuleiro tabuleiro, Cor cor) {
			super(tabuleiro);
			this.cor = cor;
		}

		public Cor getCor() {
			return cor;
		}
		
		protected boolean temPe�aAdversaria(Posi��o posi��o) {
			Pe�aXadrez p = (Pe�aXadrez)getTabuleiro().pe�a(posi��o);
			return p != null && p.getCor() != cor;
		}

		
		
}
