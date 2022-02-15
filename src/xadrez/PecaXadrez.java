package xadrez;

import boardgame.Piece;
import boardgame.Posicao;
import boardgame.Tabuleiro;

public abstract class PecaXadrez extends Piece{
	
	private Cor cor;
	private int contadorDeMovimento;
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}
	
	public int getContadorDeMovimento() {
		return contadorDeMovimento;
	}
	
	public void incrementaContadorDeMovimento() {
		contadorDeMovimento++;
	}
	
	public void decrementaContadorDeMovimento() {
		contadorDeMovimento--;
	}
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.daPosicao(posicao);
	}
	
	//metodo que verifica se existe uma peça adversária na posição de destino
	protected boolean existeUmaPecaOponente(Posicao posicao) {
		PecaXadrez peca = (PecaXadrez)getTabuleiro().piece(posicao);
		return peca != null && peca.getCor() != cor;//condição que verifica se a cor da peça adversária é diferente e/ou a posição é nula
	}

}
