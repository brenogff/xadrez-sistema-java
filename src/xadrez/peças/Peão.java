package xadrez.pe�as;

import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Pe�aXadrez;

public class Pe�o extends Pe�aXadrez {

	public Pe�o(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posi��o p = new Posi��o(0, 0);
		
		if (getCor() == Cor.BRANCO) {
			p.setValores(posi��o.getLinha() - 1, posi��o.getColuna());
			if (getTabuleiro().posi��oExistente(p) && !getTabuleiro().temPe�a(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posi��o.getLinha() - 2, posi��o.getColuna());
			Posi��o p2 = new Posi��o(posi��o.getLinha() - 1, posi��o.getColuna());
			if (getTabuleiro().posi��oExistente(p) && !getTabuleiro().temPe�a(p) && getTabuleiro().posi��oExistente(p2) && !getTabuleiro().temPe�a(p2) && getContarMovimento() == 0) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() - 1);
			if (getTabuleiro().posi��oExistente(p) && temPe�aAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() + 1);
			if (getTabuleiro().posi��oExistente(p) && temPe�aAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
		}
		else {
			
			p.setValores(posi��o.getLinha() + 1, posi��o.getColuna());
			if (getTabuleiro().posi��oExistente(p) && !getTabuleiro().temPe�a(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posi��o.getLinha() + 2, posi��o.getColuna());
			Posi��o p2 = new Posi��o(posi��o.getLinha() + 1, posi��o.getColuna());
			if (getTabuleiro().posi��oExistente(p) && !getTabuleiro().temPe�a(p) && getTabuleiro().posi��oExistente(p2) && !getTabuleiro().temPe�a(p2) && getContarMovimento() == 0) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() - 1);
			if (getTabuleiro().posi��oExistente(p) && temPe�aAdversaria(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			}
		
			p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() + 1);
			if (getTabuleiro().posi��oExistente(p) && temPe�aAdversaria(p)) {
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
