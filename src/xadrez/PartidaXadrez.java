package xadrez;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Piece;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import pecas.xadrez.Bispo;
import pecas.xadrez.Cavalo;
import pecas.xadrez.Peao;
import pecas.xadrez.Rainha;
import pecas.xadrez.Rei;
import pecas.xadrez.Torre;

public class PartidaXadrez {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	private PecaXadrez vulnerabilidadeEnPassant;
	private PecaXadrez promovido;
	
	private List<Piece> pecasNoTabuleiro = new ArrayList<>();
	private List<Piece> pecasCapturadas = new ArrayList<>();
	
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCA;
		iniciaPartida();
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
	
	public PecaXadrez getVulnerabilidadeEnPassant() {
		return vulnerabilidadeEnPassant;
	}
	
	public PecaXadrez getPromovido() {
		return promovido;
	}
	
	public PecaXadrez[][] getPieces(){
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i = 0; i < tabuleiro.getLinhas(); i++) {
			for(int j = 0; j < tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaXadrez) tabuleiro.piece(i, j);
			}
		}
		return matriz;
	}
	
	//metodo indicador de movimentos possívies de cada peça
	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem){
		Posicao posicao = posicaoOrigem.paraPosicao();//converte s podição do xadrez para a posição da matriz
		validaPosicaoOrigem(posicao);
		return tabuleiro.piece(posicao).movimentosPossiveis();
	}
	
	//metodo que realiza o movimento das peças
	public PecaXadrez movimentoDasPecas(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.paraPosicao();//convertendo as posições de origem e destino para as posições da matriz
		Posicao destino = posicaoDestino.paraPosicao();
		validaPosicaoOrigem(origem);//valida a posição de origem
		validaPosicaoDestino(origem, destino);//valida a posição de destino
		Piece pecaCapturada = fazMovimento(origem, destino);//operação que realiza efetivamente o movimento da peça
		
		if(testeCheck(jogadorAtual)) {
			desfazMovimento(origem, destino, pecaCapturada);
			throw new XadrezException("Você não pode se colocar em Check.");
		}
		
		PecaXadrez pecaQueMoveu = (PecaXadrez)tabuleiro.piece(destino);
		
		//movimento especial Promoção
		promovido = null;
		if(pecaQueMoveu instanceof Peao) {
			if((pecaQueMoveu.getCor() == Cor.BRANCA && destino.getLinha() == 0) || pecaQueMoveu.getCor() == Cor.PRETA && destino.getLinha() == 7){
				promovido = (PecaXadrez)tabuleiro.piece(destino);
				promovido = substituiPecaPromovida("Q");
			}
		}
		
		check = (testeCheck(oponente(jogadorAtual))) ? true : false;
		
		if(testeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {
			proximoTurno();
		}
		
		//movimento especial En Passant
		if(pecaQueMoveu instanceof Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			vulnerabilidadeEnPassant = pecaQueMoveu;
		}
		else {
			vulnerabilidadeEnPassant = null;
		}
		
		return (PecaXadrez)pecaCapturada;
	}
	
	public PecaXadrez substituiPecaPromovida(String tipo) {
		if(promovido == null) {
			throw new IllegalStateException("Não há peça a ser promovida");
		}
		if(!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("Q")) {
			throw new InvalidParameterException("Tipo inválido para promoção.");
		}
		
		Posicao ps = promovido.getPosicaoXadrez().paraPosicao();
		Piece p = tabuleiro.removePeca(ps);
		pecasNoTabuleiro.remove(p);
		PecaXadrez novaPeca = novaPeca(tipo, promovido.getCor());
		tabuleiro.colocaPeca(novaPeca, ps);
		pecasNoTabuleiro.add(novaPeca);
		
		return novaPeca;
	}
	
	private PecaXadrez novaPeca(String tipo, Cor cor) {
		if(tipo.equals("B")) return new Bispo(tabuleiro, cor);
		if(tipo.equals("C")) return new Cavalo(tabuleiro, cor);
		if(tipo.equals("Q")) return new Rainha(tabuleiro, cor);
		return new Torre(tabuleiro, cor);
	}
	
	//metodo que realiza efetivamente o movimento da peça
	private Piece fazMovimento(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez)tabuleiro.removePeca(origem);
		p.incrementaContadorDeMovimento();
		Piece pecaCapturada =  tabuleiro.removePeca(destino);
		tabuleiro.colocaPeca(p, destino);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		//movimento especial Castling do lado do Rei
		if(p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(origemTorre);
			tabuleiro.colocaPeca(torre, destinoTorre);
			torre.incrementaContadorDeMovimento();
		}
		//movimento especial Castling do lado da Rainha
		if(p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(origemTorre);
			tabuleiro.colocaPeca(torre, destinoTorre);
			torre.incrementaContadorDeMovimento();
		}
		
		//movimento especial En Passant
		if(p instanceof Peao) {
			if(origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
				Posicao posicaoPeao;
				if(p.getCor() == Cor.BRANCA) {
					posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());	
					}
					else { 
						posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
					}
				pecaCapturada = tabuleiro.removePeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
				}
			}
		return pecaCapturada;
		}
	
	//metodo que desfaz efetivamente o movimento da peça
	private void desfazMovimento(Posicao origem, Posicao destino, Piece pecaCapturada) {
		PecaXadrez ps = (PecaXadrez)tabuleiro.removePeca(destino);
		tabuleiro.colocaPeca(ps, origem);
		ps.decrementaContadorDeMovimento();
		
		if(pecaCapturada != null) {
			tabuleiro.colocaPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
		//movimento especial Castling do lado do Rei
		if(ps instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(destinoTorre);
			tabuleiro.colocaPeca(torre, origemTorre);
			torre.decrementaContadorDeMovimento();
		}
		//movimento especial Castling do lado da Rainha
		if(ps instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(destinoTorre);
			tabuleiro.colocaPeca(torre, origemTorre);
			torre.decrementaContadorDeMovimento();
		}
		
		//movimento especial En Passant
		if(ps instanceof Peao) {
			if(origem.getColuna() != destino.getColuna() && pecaCapturada == vulnerabilidadeEnPassant) {
				PecaXadrez peao = (PecaXadrez)tabuleiro.removePeca(destino);
				Posicao posicaoPeao;
				if(ps.getCor() == Cor.BRANCA) {
					posicaoPeao = new Posicao(3, destino.getColuna());	
					}
					else { 
						posicaoPeao = new Posicao(4, destino.getColuna());
					}
				tabuleiro.colocaPeca(peao, posicaoPeao);
				}
			}
	}
	
	//metodo que verifica e valida a posição de origem
	private void validaPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.haUmaPeca(posicao)) {
			throw new XadrezException("Não há peça na posição de origem.");
		}
		if(jogadorAtual != ((PecaXadrez)tabuleiro.piece(posicao)).getCor()) {
			throw new XadrezException("A peça escolhida não é sua.");
		}
		if(!tabuleiro.piece(posicao).existeMovimentoPossivel()) {
			throw new XadrezException("Não existe movimentos possíveis para a peça escolhida.");
		}
	}
	
	//metodo que verifica e valida a posição de destino
	private void validaPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.piece(origem).movimentosPossiveis(destino)) {
			throw new XadrezException("A peça escolhida não pode se mover para a posição de destino,");
		}
	}
	
	//metodo que troca os turnos da partida
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private Cor oponente(Cor cor) {
		return(cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private PecaXadrez Rei(Cor cor) {
		List<Piece> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Piece p : list) {
			if(p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe o Rei " + cor + " no tabuleiro.");
	}
	
	private boolean testeCheck(Cor cor) {
		Posicao posicaoDoRei = Rei(cor).getPosicaoXadrez().paraPosicao();
		List<Piece> pecasOponentes = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for(Piece p : pecasOponentes) {
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testeCheckMate(Cor cor) {
		if(!testeCheck(cor)) {
			return false;
		}
		List<Piece> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Piece p : list) {
			boolean[][] matriz = p.movimentosPossiveis();
			for(int i = 0; i < tabuleiro.getLinhas(); i++) {
				for(int j = 0; j < tabuleiro.getColunas(); j ++) {
					if(matriz[i][j]) {
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().paraPosicao();
						Posicao destino = new Posicao (i, j);
						Piece pecaCapturada = fazMovimento(origem, destino);
						boolean testeCheck = testeCheck(cor);
						desfazMovimento(origem, destino, pecaCapturada);
						if(!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void colocaNovaPeca(char coluna, int linha, PecaXadrez piece) {
		tabuleiro.colocaPeca(piece, new PosicaoXadrez(coluna, linha).paraPosicao());
		pecasNoTabuleiro.add(piece);
	}
	
	private void iniciaPartida() {
		colocaNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA, this));
		colocaNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocaNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocaNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocaNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocaNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocaNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocaNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocaNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		
		colocaNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
		colocaNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETA));
		colocaNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETA));
		colocaNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETA, this));
		colocaNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETA));
		colocaNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
		colocaNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocaNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocaNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocaNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocaNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocaNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocaNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocaNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETA, this));
	}

}
