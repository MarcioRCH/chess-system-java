package xadrez;

import boardgame.Posicao;

public class PosicaoXadrez {
	
	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {//condição que testa os valores da instanciação das posições do tabuleiro
			throw new XadrezException("Erro: Os valores validos sao de a1 ate h8.");
		}
		this.coluna = coluna;
		this.linha = linha;
		}
	
	public char getColuna() {
		return coluna;
	}
	
	public int getLinha() {
		return linha;
	}
	
	protected Posicao paraPosicao() {
		return new Posicao(8 - linha, coluna - 'a');		
	}
	
	protected static PosicaoXadrez daPosicao(Posicao posicao) {
		return new PosicaoXadrez((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}

}
