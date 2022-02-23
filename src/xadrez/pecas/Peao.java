package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	private PartidaXadrez partidaXadrez;
	
	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
		
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		if (getCor() == Cor.BRANCO) {
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().temPeca(p2) && getContarMovimento() == 0) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExistente(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			//movimento especial En Passant - Brancas
			if (posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
					if (getTabuleiro().posicaoExistente(esquerda) && temPecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassant()) {
						matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
					}
			}
			
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
					if (getTabuleiro().posicaoExistente(direita) && temPecaAdversaria(direita) && getTabuleiro().peca(direita) == partidaXadrez.getEnPassant()) {
						matriz[direita.getLinha() - 1][direita.getColuna()] = true;
			}
		
		}
		
		else {
			
			p.setValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().temPeca(p2) && getContarMovimento() == 0) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExistente(p) && temPecaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
			
			//movimento especial En Passant - Pretas
			if (posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
					if (getTabuleiro().posicaoExistente(esquerda) && temPecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassant()) {
						matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
					}
			}
			
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
					if (getTabuleiro().posicaoExistente(direita) && temPecaAdversaria(direita) && getTabuleiro().peca(direita) == partidaXadrez.getEnPassant()) {
						matriz[direita.getLinha() + 1][direita.getColuna()] = true;
			}
		
	}
		return matriz;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
