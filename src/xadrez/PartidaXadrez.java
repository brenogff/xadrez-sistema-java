package xadrez;

import tabuleiro.Peça;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.peças.Rei;
import xadrez.peças.Torre;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		inicioJogo();
	}

	public PeçaXadrez[][] getPeças() {
		PeçaXadrez[][] matriz = new PeçaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()]; 
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PeçaXadrez) tabuleiro.peça(i, j);
			}
		}
		
		return matriz;
	}
	
	public PeçaXadrez executarMovimentoPeça(PosiçãoXadrez inicioPosição, PosiçãoXadrez destinoPosição) {
		Posição inicio = inicioPosição.toPosição();
		Posição destino = destinoPosição.toPosição();
		validarInicioPosição(inicio);
		Peça capturaPeça = façaMovimento (inicio, destino);
		return (PeçaXadrez) capturaPeça;
	}
	
	private Peça façaMovimento(Posição inicio, Posição destino) {
		Peça p = tabuleiro.removerPeça(inicio);
		Peça capturaPeça = tabuleiro.removerPeça(destino);
		tabuleiro.colocarPeça(p, destino);
		return capturaPeça;
	}
	
	private void validarInicioPosição(Posição posição) {
		if (!tabuleiro.temPeça(posição)) {
			throw new XadrezException("Não há peça na posição inicial");
		}
	}
	
	private void colocarNovaPeça(char coluna, int linha, PeçaXadrez peça) {
		tabuleiro.colocarPeça(peça, new PosiçãoXadrez(coluna, linha).toPosição());
	}
	
	private void inicioJogo() {
		
		
		colocarNovaPeça('C', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('C', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('D', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('E', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('E', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('D', 1, new Rei(tabuleiro, Cor.BRANCO));

		colocarNovaPeça('C', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeça('C', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeça('D', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeça('E', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeça('E', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeça('D', 8, new Rei(tabuleiro, Cor.PRETO));
		
	}
}
