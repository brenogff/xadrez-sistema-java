package xadrez;

import tabuleiro.Tabuleiro;
import xadrez.pe�as.Rei;
import xadrez.pe�as.Torre;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		inicioJogo();
	}

	public Pe�aXadrez[][] getPe�as() {
		Pe�aXadrez[][] matriz = new Pe�aXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()]; 
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				matriz[i][j] = (Pe�aXadrez) tabuleiro.pe�a(i, j);
			}
		}
		
		return matriz;
	}
	
	private void colocarNovaPe�a(char coluna, int linha, Pe�aXadrez pe�a) {
		tabuleiro.colocarPe�a(pe�a, new Posi��oXadrez(coluna, linha).toPosi��o());
	}
	
	private void inicioJogo() {
		
		
		colocarNovaPe�a('C', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('C', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('D', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('E', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('E', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('D', 1, new Rei(tabuleiro, Cor.BRANCO));

		colocarNovaPe�a('C', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('C', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('D', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('E', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('E', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('D', 8, new Rei(tabuleiro, Cor.PRETO));
		
	}
}
