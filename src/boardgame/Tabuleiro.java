package boardgame;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Piece[][] pieces; //matriz de peças
	
	public Tabuleiro(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pieces = new Piece[linhas][colunas];
	}
	
	public Integer getLinhas() {
		return linhas;
	}
	
	public void setLinhas(Integer linhas) {
		this.linhas = linhas;
	}
	
	public Integer getColunas() {
		return colunas;
	}
	
	public void setColunas(Integer colunas) {
		this.colunas = colunas;
	}

}
