package xadrez.peças;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaXadrez;

public class Rainha extends PeçaXadrez {

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);

	}
	
	@Override
	public String toString() {
		return "D";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posição p = new Posição(0, 0);
		
		//acima
				p.setValores(posição.getLinha() - 1, posição.getColuna());
				while (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temPeça(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setLinha(p.getLinha() - 1);
				}
				if (getTabuleiro().posiçãoExistente(p) && temPeçaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
		
		//esquerda
				p.setValores(posição.getLinha(), posição.getColuna() - 1);
				while (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temPeça(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setColuna(p.getColuna() - 1);
				}
				if (getTabuleiro().posiçãoExistente(p) && temPeçaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//direita
				p.setValores(posição.getLinha(), posição.getColuna() + 1);
				while (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temPeça(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setColuna(p.getColuna() + 1);
				}
				if (getTabuleiro().posiçãoExistente(p) && temPeçaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//abaixo
				p.setValores(posição.getLinha() + 1, posição.getColuna());
				while (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temPeça(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setLinha(p.getLinha() + 1);
				}
				if (getTabuleiro().posiçãoExistente(p) && temPeçaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
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