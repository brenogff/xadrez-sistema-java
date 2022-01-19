package xadrez.pe�as;

import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.Pe�aXadrez;

public class Rei extends Pe�aXadrez {
	
	private PartidaXadrez partidaXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posi��o posi��o) {
		Pe�aXadrez p = (Pe�aXadrez)getTabuleiro().pe�a(posi��o);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testePossivelRoque(Posi��o posi��o) {
		Pe�aXadrez p = (Pe�aXadrez)getTabuleiro().pe�a(posi��o);
		return p == null || p instanceof Torre && p.getCor() == getCor() && p.getContarMovimento() == 0;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posi��o p = new Posi��o(0, 0);
		
		//acima
				p.setValores(posi��o.getLinha() - 1, posi��o.getColuna());
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
		
		//abaixo
				p.setValores(posi��o.getLinha() + 1, posi��o.getColuna());
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//esquerda
				p.setValores(posi��o.getLinha(), posi��o.getColuna() - 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//direita
				p.setValores(posi��o.getLinha(), posi��o.getColuna() + 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}	
				
		//diagonalNoroeste
				p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() - 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalNordeste
				p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() + 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudoeste
				p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() + 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudeste
				p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() - 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//movimentoEspecialRoque
				
				if (getContarMovimento() == 0 && !partidaXadrez.getCheck()) {
					//testando posi��o para Roque pequeno
					Posi��o posi��oT1 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() + 3);
					if (testePossivelRoque(posi��oT1)) {
						Posi��o p1 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() + 1);
						Posi��o p2 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() + 2);
						if (getTabuleiro().pe�a(p1) == null && getTabuleiro().pe�a(p2) == null) {
							matriz[posi��o.getLinha()][posi��o.getColuna() + 2] = true;
						}
					}
					
					//testando posi��o para Roque grande
					Posi��o posi��oT2 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() - 4);
					if (testePossivelRoque(posi��oT1)) {
						Posi��o p1 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() - 1);
						Posi��o p2 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() - 2);
						Posi��o p3 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() - 3);
						if (getTabuleiro().pe�a(p1) == null && getTabuleiro().pe�a(p2) == null && getTabuleiro().pe�a(p3) == null) {
							matriz[posi��o.getLinha()][posi��o.getColuna() - 2] = true;
						}
					}
				}
			
		return matriz;
	}

}
