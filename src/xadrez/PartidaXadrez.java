package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	private PecaXadrez enPassant;
	private PecaXadrez promocao;

	
	private List<Peca> PecasNoTabuleiro = new ArrayList<>();
	private List<Peca> CapturaPecas = new ArrayList<>();
	
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
	
	public PecaXadrez getEnPassant() {
		return enPassant;
	}
	
	public PecaXadrez getpromocao() {
		return promocao;
	}

	
	
	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()]; 
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		
		return matriz;
	}
	
	public boolean[][] movimentosPossiveis(PosicaoXadrez inicioPosicao) {
		Posicao posicao = inicioPosicao.toPosicao();
		validarInicioPosicao(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	public PecaXadrez executarMovimentoPeca(PosicaoXadrez inicioPosicao, PosicaoXadrez destinoPosicao) {
		Posicao inicio = inicioPosicao.toPosicao();
		Posicao destino = destinoPosicao.toPosicao();
		validarInicioPosicao(inicio);
		validarDestinoPosicao(inicio, destino);
		Peca capturaPeca = facaMovimento (inicio, destino);
		
		if (testeCheck(jogadorAtual)) {
			desfacaMovimento(inicio, destino, capturaPeca);
			throw new XadrezException("Você não pode se colocar em xeque");
		}
		
		PecaXadrez pecaMoveu = (PecaXadrez)tabuleiro.peca(destino);
		
		//movimentoespeal promocao
				promocao = null;
				if (pecaMoveu instanceof Peao) {
					if ((pecaMoveu.getCor() == Cor.BRANCO && destino.getLinha() == 0) || (pecaMoveu.getCor() == Cor.PRETO && destino.getLinha() == 7)) {
						promocao = (PecaXadrez)tabuleiro.peca(destino);
						promocao = substituirPecaPromovida("D");
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
		if (pecaMoveu instanceof Peao && (destino.getLinha() == inicio.getLinha() - 2 || destino.getLinha() == inicio.getLinha() + 2)) {
			enPassant = pecaMoveu;	
		}
		else {
			enPassant = null;
		}
		
		return (PecaXadrez) capturaPeca;

	}
	
	public PecaXadrez substituirPecaPromovida(String tipo) {
		if (promocao == null) {
			throw new IllegalStateException("Não há peca para ser promovida");	
		}
		
		if (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("D") ) {
			return promocao;
		}
		
		Posicao pos = promocao.getPosicaoXadrez().toPosicao();
		Peca p = tabuleiro.removerPeca(pos);
		PecasNoTabuleiro.remove(p);
		
		PecaXadrez novaPeca = novaPeca(tipo, promocao.getCor());
		tabuleiro.colocarPeca(novaPeca, pos);
		PecasNoTabuleiro.add(novaPeca);
		
		return novaPeca;
	}   

	private PecaXadrez novaPeca(String tipo, Cor cor) {
		if (tipo.equals("B")) return new Bispo(tabuleiro, cor);
		if (tipo.equals("C")) return new Cavalo(tabuleiro, cor);
		if (tipo.equals("D")) return new Rainha(tabuleiro, cor);
		return new Torre(tabuleiro, cor);
	}

		
	private Peca facaMovimento(Posicao inicio, Posicao destino) {
 		PecaXadrez p = (PecaXadrez)tabuleiro.removerPeca(inicio);
		p.aumentarContarMovimento();
		Peca capturaPeca = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		
		if (capturaPeca != null) {
			PecasNoTabuleiro.remove(capturaPeca);
			CapturaPecas.add(capturaPeca);
		}
		
		//MovimentoEspecial Roque pequeno
		if (p instanceof Rei && destino.getColuna() == inicio.getColuna() + 2) {
			Posicao inicioTorre = new Posicao(inicio.getLinha(), inicio.getColuna() + 3);
			Posicao destinoTorre = new Posicao(inicio.getLinha(), inicio.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(inicioTorre);
			tabuleiro.colocarPeca(torre, destinoTorre);
			torre.aumentarContarMovimento();
		}
		
		//MovimentoEspecial Roque grande
				if (p instanceof Rei && destino.getColuna() == inicio.getColuna() - 2) {
					Posicao inicioTorre = new Posicao(inicio.getLinha(), inicio.getColuna() - 4);
					Posicao destinoTorre = new Posicao(inicio.getLinha(), inicio.getColuna() - 1);
					PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(inicioTorre);
					tabuleiro.colocarPeca(torre, destinoTorre);
					torre.aumentarContarMovimento();
				}
				
		//MovimentoEspecial En Passant
				if (p instanceof Peao) {
					if (inicio.getColuna() != destino.getColuna() && capturaPeca == null) {
						Posicao posicaoPeao;
						if (p.getCor() == Cor.BRANCO) {
							posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
						}
						else {
							posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
						}
						capturaPeca = tabuleiro.removerPeca(posicaoPeao);
						CapturaPecas.add(capturaPeca);
						PecasNoTabuleiro.remove(capturaPeca);
					}
				}
			
		return capturaPeca;
	}
	
	private void desfacaMovimento(Posicao inicio, Posicao destino, Peca capturaPeca) {
		PecaXadrez p = (PecaXadrez)tabuleiro.removerPeca(destino);
		p.diminuirContarMovimento();
		tabuleiro.colocarPeca(p, inicio);
		
		if (capturaPeca != null) {
			tabuleiro.colocarPeca(capturaPeca, destino);
			CapturaPecas.remove(capturaPeca);
			PecasNoTabuleiro.add(capturaPeca);
		}
		
		//MovimentoEspecial Roque pequeno
				if (p instanceof Rei && destino.getColuna() == inicio.getColuna() + 2) {
					Posicao inicioTorre = new Posicao(inicio.getLinha(), inicio.getColuna() + 3);
					Posicao destinoTorre = new Posicao(inicio.getLinha(), inicio.getColuna() + 1);
					PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(destinoTorre);
					tabuleiro.colocarPeca(torre, inicioTorre);
					torre.diminuirContarMovimento();
				}
				
		//MovimentoEspecial Roque grande
				if (p instanceof Rei && destino.getColuna() == inicio.getColuna() - 2) {
					Posicao inicioTorre = new Posicao(inicio.getLinha(), inicio.getColuna() - 4);
					Posicao destinoTorre = new Posicao(inicio.getLinha(), inicio.getColuna() - 1);
					PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(destinoTorre);
					tabuleiro.colocarPeca(torre, inicioTorre);
					torre.diminuirContarMovimento();
				}
				
		//MovimentoEspecial En Passant
				if (p instanceof Peao) {
					if (inicio.getColuna() != destino.getColuna() && capturaPeca == enPassant) {
						PecaXadrez peao = (PecaXadrez)tabuleiro.removerPeca(destino);
						Posicao posicaoPeao;
						if (p.getCor() == Cor.BRANCO) {
							posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
						}
						else {
							posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
						}
						tabuleiro.colocarPeca(peao, posicaoPeao);
					}
				}
				
	}
	
	private void validarInicioPosicao(Posicao posicao) {
		if (!tabuleiro.temPeca(posicao)) {
			throw new XadrezException("Não há peca na posicao inicial");
		}
		
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("A peca escolhida não é sua");
		}
		
		if (!tabuleiro.peca(posicao).temAlgumMovimentoPossivel() ) {
			throw new XadrezException("Não existe movimentos possíveis para a peca escolhida");
			
		}
	}
	
	private void validarDestinoPosicao(Posicao inicio, Posicao destino) {
		if (!tabuleiro.peca(inicio).movimentosPossiveis(destino)) {
			throw new XadrezException("A peca não pode se mover para a posicao de destino");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor adversario(Cor cor) {
		return (cor == Cor.BRANCO) ?Cor.PRETO : Cor.BRANCO;
	}
	
	private PecaXadrez Rei(Cor cor) {
		List<Peca> list = PecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		
		throw new IllegalStateException("Não existe Rei " + cor + " no tabuleiro");
	}
	
	private boolean testeCheck(Cor cor) {
		Posicao posicaoRei = Rei(cor).getPosicaoXadrez().toPosicao();
		List<Peca>pecasAdversarias = PecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == adversario(cor)).collect(Collectors.toList());
		for (Peca p : pecasAdversarias) {
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean testeCheckMate(Cor cor) {
		if (!testeCheck(cor)) {
			return false;
		}
		
		List<Peca> list = PecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] matriz = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (matriz[i][j]) {
						Posicao inicio = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao destino =  new Posicao(i, j);
						Peca capturaPeca = facaMovimento(inicio, destino);
						boolean testeCheck = testeCheck(cor);
						desfacaMovimento(inicio, destino, capturaPeca);
						if (!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		PecasNoTabuleiro.add(peca);
	}
	
	
	private void inicioJogo() {
		
		
		colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		
		colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this ));
		colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));
		
	}
}
