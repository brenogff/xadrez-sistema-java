package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Pe�a;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;
import xadrez.pe�as.Rei;
import xadrez.pe�as.Torre;

public class PartidaXadrez {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	
	private List<Pe�a> Pe�asNoTabuleiro = new ArrayList<>();
	private List<Pe�a> CapturaPe�as = new ArrayList<>();
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual =  Cor.BRANCO;
		inicioJogo();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}
	
	public Pe�aXadrez[][] getPe�as() {
		Pe�aXadrez[][] matriz = new Pe�aXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()]; 
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				matriz[i][j] = (Pe�aXadrez) tabuleiro.pe�a(i, j);
			}
		}
		
		return matriz;
	}
	
	public boolean[][] movimentosPossiveis(Posi��oXadrez inicioPosi��o) {
		Posi��o posi��o = inicioPosi��o.toPosi��o();
		validarInicioPosi��o(posi��o);
		return tabuleiro.pe�a(posi��o).movimentosPossiveis();
	}
	
	public Pe�aXadrez executarMovimentoPe�a(Posi��oXadrez inicioPosi��o, Posi��oXadrez destinoPosi��o) {
		Posi��o inicio = inicioPosi��o.toPosi��o();
		Posi��o destino = destinoPosi��o.toPosi��o();
		validarInicioPosi��o(inicio);
		validarDestinoPosi��o(inicio, destino);
		Pe�a capturaPe�a = fa�aMovimento (inicio, destino);
		
		if (testeCheck(jogadorAtual)) {
			desfa�aMovimento(inicio, destino, capturaPe�a);
			throw new XadrezException("Voc� n�o pode se colocar em xeque");
		}
		
		check = (testeCheck(adversario(jogadorAtual))) ? true : false;
		
		if (testeCheckMate(adversario(jogadorAtual))) {
			checkMate = true;
		}
		
		else {
			proximoTurno();
		}
		
		return (Pe�aXadrez) capturaPe�a;

	}
		
	private Pe�a fa�aMovimento(Posi��o inicio, Posi��o destino) {
		Pe�a p = tabuleiro.removerPe�a(inicio);
		Pe�a capturaPe�a = tabuleiro.removerPe�a(destino);
		tabuleiro.colocarPe�a(p, destino);
		
		if (capturaPe�a != null) {
			Pe�asNoTabuleiro.remove(capturaPe�a);
			CapturaPe�as.add(capturaPe�a);
		}
			
		return capturaPe�a;
	}
	
	private void desfa�aMovimento(Posi��o inicio, Posi��o destino, Pe�a capturaPe�a) {
		Pe�a p = tabuleiro.removerPe�a(destino);
		tabuleiro.colocarPe�a(p, inicio);
		
		if (capturaPe�a != null) {
			tabuleiro.colocarPe�a(capturaPe�a, destino);
			CapturaPe�as.remove(capturaPe�a);
			Pe�asNoTabuleiro.add(capturaPe�a);
		}
	}
	
	private void validarInicioPosi��o(Posi��o posi��o) {
		if (!tabuleiro.temPe�a(posi��o)) {
			throw new XadrezException("N�o h� pe�a na posi��o inicial");
		}
		
		if (jogadorAtual != ((Pe�aXadrez)tabuleiro.pe�a(posi��o)).getCor()) {
			throw new XadrezException("A pe�a escolhida n�o � sua");
		}
		
		if (!tabuleiro.pe�a(posi��o).temAlgumMovimentoPossivel() ) {
			throw new XadrezException("N�o existe movimentos poss�veis para a pe�a escolhida");
			
		}
	}
	
	private void validarDestinoPosi��o(Posi��o inicio, Posi��o destino) {
		if (!tabuleiro.pe�a(inicio).movimentosPossiveis(destino)) {
			throw new XadrezException("A pe�a n�o pode se mover para a posi��o de destino");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor adversario(Cor cor) {
		return (cor == Cor.BRANCO) ?Cor.PRETO : Cor.BRANCO;
	}
	
	private Pe�aXadrez Rei(Cor cor) {
		List<Pe�a> list = Pe�asNoTabuleiro.stream().filter(x -> ((Pe�aXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Pe�a p : list) {
			if (p instanceof Rei) {
				return (Pe�aXadrez)p;
			}
		}
		
		throw new IllegalStateException("N�o existe Rei " + cor + " no tabuleiro");
	}
	
	private boolean testeCheck(Cor cor) {
		Posi��o posi��oRei = Rei(cor).getPosi��oXadrez().toPosi��o();
		List<Pe�a>pe�asAdversarias = Pe�asNoTabuleiro.stream().filter(x -> ((Pe�aXadrez)x).getCor() == adversario(cor)).collect(Collectors.toList());
		for (Pe�a p : pe�asAdversarias) {
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posi��oRei.getLinha()][posi��oRei.getColuna()]) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean testeCheckMate(Cor cor) {
		if (!testeCheck(cor)) {
			return false;
		}
		
		List<Pe�a> list = Pe�asNoTabuleiro.stream().filter(x -> ((Pe�aXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Pe�a p : list) {
			boolean[][] matriz = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (matriz[i][j]) {
						Posi��o inicio = ((Pe�aXadrez)p).getPosi��oXadrez().toPosi��o();
						Posi��o destino =  new Posi��o(i, j);
						Pe�a capturaPe�a = fa�aMovimento(inicio, destino);
						boolean testeCheck = testeCheck(cor);
						desfa�aMovimento(inicio, destino, capturaPe�a);
						if (!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	private void colocarNovaPe�a(char coluna, int linha, Pe�aXadrez pe�a) {
		tabuleiro.colocarPe�a(pe�a, new Posi��oXadrez(coluna, linha).toPosi��o());
		Pe�asNoTabuleiro.add(pe�a);
	}
	
	
	private void inicioJogo() {
		
		
		colocarNovaPe�a('H', 7, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('D', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('E', 1, new Rei(tabuleiro, Cor.BRANCO));
		
		colocarNovaPe�a('B', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('A', 8, new Rei(tabuleiro, Cor.PRETO));
		
		
	}
}
