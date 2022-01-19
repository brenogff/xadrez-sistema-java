package xadrez.pe�as;

import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Pe�aXadrez;

public class Bispo extends Pe�aXadrez {

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
		
		Posi��o p = new Posi��o(0, 0);
		
		//diagonalNoroeste
				p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() - 1);
				while (getTabuleiro().posi��oExistente(p) && !getTabuleiro().temPe�a(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() - 1, p.getColuna() - 1);
				}
				if (getTabuleiro().posi��oExistente(p) && temPe�aAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
		
		//diagonalNordeste
				p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() + 1);
				while (getTabuleiro().posi��oExistente(p) && !getTabuleiro().temPe�a(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() - 1, p.getColuna() + 1);
				}
				if (getTabuleiro().posi��oExistente(p) && temPe�aAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudoeste
				p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() + 1);
				while (getTabuleiro().posi��oExistente(p) && !getTabuleiro().temPe�a(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() + 1, p.getColuna() + 1);
				}
				if (getTabuleiro().posi��oExistente(p) && temPe�aAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudeste
				p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() - 1);
				while (getTabuleiro().posi��oExistente(p) && !getTabuleiro().temPe�a(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() + 1, p.getColuna() - 1);
				}
				if (getTabuleiro().posi��oExistente(p) && temPe�aAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		return matriz;
	}
}