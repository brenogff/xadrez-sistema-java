package xadrez.peças;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaXadrez;

public class Torre extends PeçaXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);

	}
	
	@Override
	public String toString() {
		return "T";
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
				
		return matriz;
	}
}