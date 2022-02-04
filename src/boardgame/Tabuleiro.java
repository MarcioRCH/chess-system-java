package boardgame;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Piece[][] pieces; //matriz de peças
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas < 1 || colunas < 1) { //condição que verifica os parâmetros do tabuleiro no momento da criação
			throw new TabuleiroException("Erro ao criar tabuleiro: Necessário que haja pelo memos 01 linha e 01 coluna.");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pieces = new Piece[linhas][colunas];
	}
	
	public Integer getLinhas() {
		return linhas;
	}
	
	public Integer getColunas() {
		return colunas;
	}

	public Piece piece(int linha, int coluna) {
		if(!posicaoExistente(linha, coluna)) {//condição que verifica se uma posição no tabuleiro existe ou não
			throw new TabuleiroException("Posição não existente no tabuleiro.");
		}
		return pieces[linha][coluna];
	}
	
	public Piece piece(Posicao posicao) {
		if(!posicaoExistente(posicao)) {//condição que verifica se uma posição no tabuleiro existe ou não
			throw new TabuleiroException("Posição não existente no tabuleiro.");
		}
		return pieces[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocaPeca(Piece piece, Posicao posicao) {
		if(haUmaPeca(posicao)) {//condição que verifica se a posição já está sendo ocupada por uma peça
			throw new TabuleiroException("Há uma peça ocupando essa posição" + posicao);
		}
		pieces[posicao.getLinha()][posicao.getColuna()] = piece;
		piece.posicao = posicao;
	}
	
	//metodo para remover uma peça do tabuleiro
	public Piece removePeca(Posicao posicao) {
		if(!posicaoExistente(posicao)) {//condição que verifica se a peça existe na posição informada
			throw new TabuleiroException("Posição não existente no tabuleiro.");
		}
		if(piece(posicao) == null) {
			return null;
		}
		//metodo para retirar efetivamente a peça do tabuleiro
		Piece auxiliar = piece(posicao);
		auxiliar.posicao = null;
		pieces[posicao.getLinha()][posicao.getColuna()] = null;//acessando e removendo a peça da matriz
		return auxiliar;
	}
	
	//metodos para verificar se uma posição de uma peça existe
	public boolean posicaoExistente(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posicaoExistente(Posicao posicao) {
		return posicaoExistente(posicao.getLinha(), posicao.getColuna());
	}
	
	//metodo que verifica se uma peça já ocupa uma posição
	public boolean haUmaPeca(Posicao posicao) {
		if(!posicaoExistente(posicao)) {//condição que verifica se uma posição no tabuleiro existe ou não
			throw new TabuleiroException("Posição não existente no tabuleiro.");
		}
		return piece(posicao) != null;
	}

}
