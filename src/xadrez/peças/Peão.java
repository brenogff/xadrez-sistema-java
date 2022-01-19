package xadrez.peças;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaXadrez;

public class Peão extends PeçaXadrez {

	public Peão(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posição p = new Posição(0, 0);
		
		if (getCor() == Cor.BRANCO) {
			p.setValores(posição.getLinha() - 1, posição.getColuna());
			if (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temPeça(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posição.getLinha() - 2, posição.getColuna());
			Posição p2 = new Posição(posição.getLinha() - 1, posição.getColuna());
			if (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temPeça(p) && getTabuleiro().posiçãoExistente(p2) && !getTabuleiro().temPeça(p2) && getContarMovimento() == 0) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posição.getLinha() - 1, posição.getColuna() - 1);
			if (getTabuleiro().posiçãoExistente(p) && temPeçaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posição.getLinha() - 1, posição.getColuna() + 1);
			if (getTabuleiro().posiçãoExistente(p) && temPeçaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
		}
		else {
			
			p.setValores(posição.getLinha() + 1, posição.getColuna());
			if (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temPeça(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posição.getLinha() + 2, posição.getColuna());
			Posição p2 = new Posição(posição.getLinha() + 1, posição.getColuna());
			if (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temPeça(p) && getTabuleiro().posiçãoExistente(p2) && !getTabuleiro().temPeça(p2) && getContarMovimento() == 0) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posição.getLinha() + 1, posição.getColuna() - 1);
			if (getTabuleiro().posiçãoExistente(p) && temPeçaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posição.getLinha() + 1, posição.getColuna() + 1);
			if (getTabuleiro().posiçãoExistente(p) && temPeçaAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
	}
		return matriz;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
