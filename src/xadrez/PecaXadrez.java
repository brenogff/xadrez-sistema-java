package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {
		
		private Cor cor;
		
		private int contarMovimento;

		public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
			super(tabuleiro);
			this.cor = cor;
		}

		public Cor getCor() {
			return cor;
		}
		
		public int getContarMovimento() {
			return contarMovimento;
		}
		
		public void aumentarContarMovimento() {
			contarMovimento++;
		}
		
		public void diminuirContarMovimento() {
			contarMovimento--;
		}
		
		public PosicaoXadrez getPosicaoXadrez() {
			return PosicaoXadrez.dePosicao(posicao);
		}
		
		protected boolean temPecaAdversaria(Posicao posicao) {
			PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
			return p != null && p.getCor() != cor;
		}

		
		
}
