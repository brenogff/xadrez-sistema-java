package xadrez.peças;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaXadrez;

public class Bispo extends PeçaXadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);

	}
	
	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posição p = new Posição(0, 0);
		
		//diagonalNoroeste
				p.setValores(posição.getLinha() - 1, posição.getColuna() - 1);
				while (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temPeça(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() - 1, p.getColuna() - 1);
				}
				if (getTabuleiro().posiçãoExistente(p) && temPeçaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
		
		//diagonalNordeste
				p.setValores(posição.getLinha() - 1, posição.getColuna() + 1);
				while (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temPeça(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() - 1, p.getColuna() + 1);
				}
				if (getTabuleiro().posiçãoExistente(p) && temPeçaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudoeste
				p.setValores(posição.getLinha() + 1, posição.getColuna() + 1);
				while (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temPeça(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() + 1, p.getColuna() + 1);
				}
				if (getTabuleiro().posiçãoExistente(p) && temPeçaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudeste
				p.setValores(posição.getLinha() + 1, posição.getColuna() - 1);
				while (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temPeça(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() + 1, p.getColuna() - 1);
				}
				if (getTabuleiro().posiçãoExistente(p) && temPeçaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		return matriz;
	}
}