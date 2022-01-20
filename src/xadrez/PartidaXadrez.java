package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Pe�a;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;
import xadrez.pe�as.Bispo;
import xadrez.pe�as.Cavalo;
import xadrez.pe�as.Pe�o;
import xadrez.pe�as.Rainha;
import xadrez.pe�as.Rei;
import xadrez.pe�as.Torre;

public class PartidaXadrez {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	private Pe�aXadrez enPassant;
	private Pe�aXadrez promo��o;

	
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
	
	public Pe�aXadrez getEnPassant() {
		return enPassant;
	}
	
	public Pe�aXadrez getPromo��o() {
		return promo��o;
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
		
		Pe�aXadrez pe�aMoveu = (Pe�aXadrez)tabuleiro.pe�a(destino);
		
		//movimentoespeal Promo��o
				promo��o = null;
				if (pe�aMoveu instanceof Pe�o) {
					if ((pe�aMoveu.getCor() == Cor.BRANCO && destino.getLinha() == 0) || (pe�aMoveu.getCor() == Cor.PRETO && destino.getLinha() == 7)) {
						promo��o = (Pe�aXadrez)tabuleiro.pe�a(destino);
						promo��o = substituirPe�aPromovida("D");
				}
			}	
		
		check = (testeCheck(adversario(jogadorAtual))) ? true : false;
		
		if (testeCheckMate(adversario(jogadorAtual))) {
			checkMate = true;
		}
		
		else {
			proximoTurno();
		}
		
		//Jogada especial En Passant
		if (pe�aMoveu instanceof Pe�o && (destino.getLinha() == inicio.getLinha() - 2 || destino.getLinha() == inicio.getLinha() + 2)) {
			enPassant = pe�aMoveu;	
		}
		else {
			enPassant = null;
		}
		
		return (Pe�aXadrez) capturaPe�a;

	}
	
	public Pe�aXadrez substituirPe�aPromovida(String tipo) {
		if (promo��o == null) {
			throw new IllegalStateException("N�o h� pe�a para ser promovida");	
		}
		
		if (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("D") ) {
			return promo��o;
		}
		
		Posi��o pos = promo��o.getPosi��oXadrez().toPosi��o();
		Pe�a p = tabuleiro.removerPe�a(pos);
		Pe�asNoTabuleiro.remove(p);
		
		Pe�aXadrez novaPe�a = novaPe�a(tipo, promo��o.getCor());
		tabuleiro.colocarPe�a(novaPe�a, pos);
		Pe�asNoTabuleiro.add(novaPe�a);
		
		return novaPe�a;
	}   

	private Pe�aXadrez novaPe�a(String tipo, Cor cor) {
		if (tipo.equals("B")) return new Bispo(tabuleiro, cor);
		if (tipo.equals("C")) return new Cavalo(tabuleiro, cor);
		if (tipo.equals("D")) return new Rainha(tabuleiro, cor);
		return new Torre(tabuleiro, cor);
	}

		
	private Pe�a fa�aMovimento(Posi��o inicio, Posi��o destino) {
 		Pe�aXadrez p = (Pe�aXadrez)tabuleiro.removerPe�a(inicio);
		p.aumentarContarMovimento();
		Pe�a capturaPe�a = tabuleiro.removerPe�a(destino);
		tabuleiro.colocarPe�a(p, destino);
		
		if (capturaPe�a != null) {
			Pe�asNoTabuleiro.remove(capturaPe�a);
			CapturaPe�as.add(capturaPe�a);
		}
		
		//MovimentoEspecial Roque pequeno
		if (p instanceof Rei && destino.getColuna() == inicio.getColuna() + 2) {
			Posi��o inicioTorre = new Posi��o(inicio.getLinha(), inicio.getColuna() + 3);
			Posi��o destinoTorre = new Posi��o(inicio.getLinha(), inicio.getColuna() + 1);
			Pe�aXadrez torre = (Pe�aXadrez)tabuleiro.removerPe�a(inicioTorre);
			tabuleiro.colocarPe�a(torre, destinoTorre);
			torre.aumentarContarMovimento();
		}
		
		//MovimentoEspecial Roque grande
				if (p instanceof Rei && destino.getColuna() == inicio.getColuna() - 2) {
					Posi��o inicioTorre = new Posi��o(inicio.getLinha(), inicio.getColuna() - 4);
					Posi��o destinoTorre = new Posi��o(inicio.getLinha(), inicio.getColuna() - 1);
					Pe�aXadrez torre = (Pe�aXadrez)tabuleiro.removerPe�a(inicioTorre);
					tabuleiro.colocarPe�a(torre, destinoTorre);
					torre.aumentarContarMovimento();
				}
				
		//MovimentoEspecial En Passant
				if (p instanceof Pe�o) {
					if (inicio.getColuna() != destino.getColuna() && capturaPe�a == null) {
						Posi��o posi��oPe�o;
						if (p.getCor() == Cor.BRANCO) {
							posi��oPe�o = new Posi��o(destino.getLinha() + 1, destino.getColuna());
						}
						else {
							posi��oPe�o = new Posi��o(destino.getLinha() - 1, destino.getColuna());
						}
						capturaPe�a = tabuleiro.removerPe�a(posi��oPe�o);
						CapturaPe�as.add(capturaPe�a);
						Pe�asNoTabuleiro.remove(capturaPe�a);
					}
				}
			
		return capturaPe�a;
	}
	
	private void desfa�aMovimento(Posi��o inicio, Posi��o destino, Pe�a capturaPe�a) {
		Pe�aXadrez p = (Pe�aXadrez)tabuleiro.removerPe�a(destino);
		p.diminuirContarMovimento();
		tabuleiro.colocarPe�a(p, inicio);
		
		if (capturaPe�a != null) {
			tabuleiro.colocarPe�a(capturaPe�a, destino);
			CapturaPe�as.remove(capturaPe�a);
			Pe�asNoTabuleiro.add(capturaPe�a);
		}
		
		//MovimentoEspecial Roque pequeno
				if (p instanceof Rei && destino.getColuna() == inicio.getColuna() + 2) {
					Posi��o inicioTorre = new Posi��o(inicio.getLinha(), inicio.getColuna() + 3);
					Posi��o destinoTorre = new Posi��o(inicio.getLinha(), inicio.getColuna() + 1);
					Pe�aXadrez torre = (Pe�aXadrez)tabuleiro.removerPe�a(destinoTorre);
					tabuleiro.colocarPe�a(torre, inicioTorre);
					torre.diminuirContarMovimento();
				}
				
		//MovimentoEspecial Roque grande
				if (p instanceof Rei && destino.getColuna() == inicio.getColuna() - 2) {
					Posi��o inicioTorre = new Posi��o(inicio.getLinha(), inicio.getColuna() - 4);
					Posi��o destinoTorre = new Posi��o(inicio.getLinha(), inicio.getColuna() - 1);
					Pe�aXadrez torre = (Pe�aXadrez)tabuleiro.removerPe�a(destinoTorre);
					tabuleiro.colocarPe�a(torre, inicioTorre);
					torre.diminuirContarMovimento();
				}
				
		//MovimentoEspecial En Passant
				if (p instanceof Pe�o) {
					if (inicio.getColuna() != destino.getColuna() && capturaPe�a == enPassant) {
						Pe�aXadrez pe�o = (Pe�aXadrez)tabuleiro.removerPe�a(destino);
						Posi��o posi��oPe�o;
						if (p.getCor() == Cor.BRANCO) {
							posi��oPe�o = new Posi��o(destino.getLinha() + 1, destino.getColuna());
						}
						else {
							posi��oPe�o = new Posi��o(destino.getLinha() - 1, destino.getColuna());
						}
						tabuleiro.colocarPe�a(pe�o, posi��oPe�o);
					}
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
		
		
		colocarNovaPe�a('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
		colocarNovaPe�a('a', 2, new Pe�o(tabuleiro, Cor.BRANCO, this));
		colocarNovaPe�a('b', 2, new Pe�o(tabuleiro, Cor.BRANCO, this));
		colocarNovaPe�a('c', 2, new Pe�o(tabuleiro, Cor.BRANCO, this));
		colocarNovaPe�a('d', 2, new Pe�o(tabuleiro, Cor.BRANCO, this));
		colocarNovaPe�a('e', 2, new Pe�o(tabuleiro, Cor.BRANCO, this));
		colocarNovaPe�a('f', 2, new Pe�o(tabuleiro, Cor.BRANCO, this));
		colocarNovaPe�a('g', 2, new Pe�o(tabuleiro, Cor.BRANCO, this));
		colocarNovaPe�a('h', 2, new Pe�o(tabuleiro, Cor.BRANCO, this));
		
		colocarNovaPe�a('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
		colocarNovaPe�a('a', 7, new Pe�o(tabuleiro, Cor.PRETO, this));
		colocarNovaPe�a('b', 7, new Pe�o(tabuleiro, Cor.PRETO, this));
		colocarNovaPe�a('c', 7, new Pe�o(tabuleiro, Cor.PRETO, this));
		colocarNovaPe�a('d', 7, new Pe�o(tabuleiro, Cor.PRETO, this ));
		colocarNovaPe�a('e', 7, new Pe�o(tabuleiro, Cor.PRETO, this));
		colocarNovaPe�a('f', 7, new Pe�o(tabuleiro, Cor.PRETO, this));
		colocarNovaPe�a('g', 7, new Pe�o(tabuleiro, Cor.PRETO, this));
		colocarNovaPe�a('h', 7, new Pe�o(tabuleiro, Cor.PRETO, this));
		
	}
}
