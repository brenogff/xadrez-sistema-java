package xadrez.peças;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaXadrez;

public class Rei extends PeçaXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posição posição) {
		PeçaXadrez p = (PeçaXadrez)getTabuleiro().peça(posição);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posição p = new Posição(0, 0);
		
		//acima
				p.setValores(posição.getLinha() - 1, posição.getColuna());
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
		
		//abaixo
				p.setValores(posição.getLinha() + 1, posição.getColuna());
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//esquerda
				p.setValores(posição.getLinha(), posição.getColuna() - 1);
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//direita
				p.setValores(posição.getLinha(), posição.getColuna() + 1);
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}	
				
		//diagonalNoroeste
				p.setValores(posição.getLinha() - 1, posição.getColuna() - 1);
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalNordeste
				p.setValores(posição.getLinha() - 1, posição.getColuna() + 1);
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudoeste
				p.setValores(posição.getLinha() + 1, posição.getColuna() + 1);
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudeste
				p.setValores(posição.getLinha() + 1, posição.getColuna() - 1);
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
			
		return matriz;
	}

}
