package boardgame;

public abstract class Piece {
	
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Piece (Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}
	
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	//metodo que determina movimentos possíveis
	public abstract boolean[][] movimentosPossiveis();
	
	//metodo que determina a possibilidade do movimento para uma determinada posição
	public boolean movimentosPossiveis(Posicao posicao) {
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean existeMovimentoPossivel() {
		boolean[][] matriz = movimentosPossiveis();
		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz.length; j++) {
				if(matriz[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
