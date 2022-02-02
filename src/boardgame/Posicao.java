package boardgame;

public class Posicao {
	
	private int linha;
	private int coluna;
	
	public Posicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public Integer getColuna() {
		return coluna;
	}
	
	public void setColuna(Integer coluna) {
		this.coluna = coluna;
	}
	
	public Integer getLinha() {
		return linha;
	}
	
	public void setLinha(Integer linha) {
		this.linha = linha;
	}
	
	@Override
	public String toString() {
		return linha + ", " + coluna;
	}

}
