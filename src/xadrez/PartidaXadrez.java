package xadrez;

import java.util.ArrayList;
import java.util.List;

import boardgame.Piece;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import pecas.xadrez.Rei;
import pecas.xadrez.Torre;

public class PartidaXadrez {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
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
		Posicao posicao = posicaoOrigem.toPosicao();//converte s podição do xadrez para a posição da matriz
		validaPosicaoOrigem(posicao);
		return tabuleiro.piece(posicao).movimentosPossiveis();
	}
	
	//metodo que realiza o movimento das peças
	public PecaXadrez movimentoDasPecas(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();//convertendo as posições de origem e destino para as posições da matriz
		Posicao destino = posicaoDestino.toPosicao();
		validaPosicaoOrigem(origem);//valida a posição de origem
		validaPosicaoDestino(origem, destino);//valida a posição de destino
		Piece pecaCapturada = fazMovimento(origem, destino);//operação que realiza efetivamente o movimento da peça
		proximoTurno();
		return (PecaXadrez)pecaCapturada;
	}
	
	//metodo que realiza efetivamente o movimento da peça
	private Piece fazMovimento(Posicao origem, Posicao destino) {
		Piece p = tabuleiro.removePeca(origem);
		Piece pecaCapturada =  tabuleiro.removePeca(destino);
		tabuleiro.colocaPeca(p, destino);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
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
	
	private void colocaNovaPeca(char coluna, int linha, PecaXadrez piece) {
		tabuleiro.colocaPeca(piece, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(piece);
	}
	
	private void iniciaPartida() {
		colocaNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA));
		
		colocaNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETA));
		colocaNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETA));
	}

}
