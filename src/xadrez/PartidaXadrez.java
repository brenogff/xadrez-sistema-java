package xadrez;

import tabuleiro.Pe�a;
import tabuleiro.Posi��o;
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
	
	public Pe�aXadrez executarMovimentoPe�a(Posi��oXadrez inicioPosi��o, Posi��oXadrez destinoPosi��o) {
		Posi��o inicio = inicioPosi��o.toPosi��o();
		Posi��o destino = destinoPosi��o.toPosi��o();
		validarInicioPosi��o(inicio);
		Pe�a capturaPe�a = fa�aMovimento (inicio, destino);
		return (Pe�aXadrez) capturaPe�a;
	}
	
	private Pe�a fa�aMovimento(Posi��o inicio, Posi��o destino) {
		Pe�a p = tabuleiro.removerPe�a(inicio);
		Pe�a capturaPe�a = tabuleiro.removerPe�a(destino);
		tabuleiro.colocarPe�a(p, destino);
		return capturaPe�a;
	}
	
	private void validarInicioPosi��o(Posi��o posi��o) {
		if (!tabuleiro.temPe�a(posi��o)) {
			throw new XadrezException("N�o h� pe�a na posi��o inicial");
		}
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
