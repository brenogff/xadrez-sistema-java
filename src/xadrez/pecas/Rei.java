package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {
	
	private PartidaXadrez partidaXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testePossivelRoque(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p instanceof Torre && p.getCor() == getCor() && p.getContarMovimento() == 0;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		//acima
				p.setValores(posicao.getLinha() - 1, posicao.getColuna());
				if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
		
		//abaixo
				p.setValores(posicao.getLinha() + 1, posicao.getColuna());
				if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//esquerda
				p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//direita
				p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}	
				
		//diagonalNoroeste
				p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalNordeste
				p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudoeste
				p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudeste
				p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//movimentoEspecialRoque
				
				if (getContarMovimento() == 0 && !partidaXadrez.getCheck()) {
					//testando posicao para Roque pequeno
					Posicao posicaoT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
					if (testePossivelRoque(posicaoT1)) {
						Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
						Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
						if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
							matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
						}
					}
					
					//testando posicao para Roque grande
					if (testePossivelRoque(posicaoT1)) {
						Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
						Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
						Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
						if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
							matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
						}
					}
				}
			
		return matriz;
	}

}
