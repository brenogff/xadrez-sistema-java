package xadrez.pe�as;

import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Pe�aXadrez;

public class Torre extends Pe�aXadrez {

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
		
		Posi��o p = new Posi��o(0, 0);
		
		//acima
				p.setValores(posi��o.getLinha() - 1, posi��o.getColuna());
				while (getTabuleiro().posi��oExistente(p) && !getTabuleiro().temPe�a(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setLinha(p.getLinha() - 1);
				}
				if (getTabuleiro().posi��oExistente(p) && temPe�aAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
		
		//esquerda
				p.setValores(posi��o.getLinha(), posi��o.getColuna() - 1);
				while (getTabuleiro().posi��oExistente(p) && !getTabuleiro().temPe�a(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setColuna(p.getColuna() - 1);
				}
				if (getTabuleiro().posi��oExistente(p) && temPe�aAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
				//direita
				p.setValores(posi��o.getLinha(), posi��o.getColuna() + 1);
				while (getTabuleiro().posi��oExistente(p) && !getTabuleiro().temPe�a(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setColuna(p.getColuna() + 1);
				}
				if (getTabuleiro().posi��oExistente(p) && temPe�aAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
				//abaixo
				p.setValores(posi��o.getLinha() + 1, posi��o.getColuna());
				while (getTabuleiro().posi��oExistente(p) && !getTabuleiro().temPe�a(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setLinha(p.getLinha() + 1);
				}
				if (getTabuleiro().posi��oExistente(p) && temPe�aAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		return matriz;
	}
}