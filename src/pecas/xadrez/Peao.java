package pecas.xadrez;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{
	
	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public boolean[][] movimentosPossiveis(){
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao ps = new Posicao(0, 0);
		
		//metodo de movimento do peão branco
		if(getCor() == Cor.BRANCA) {
			ps.setValor(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExistente(ps) && !getTabuleiro().haUmaPeca(ps)) {
				matriz[ps.getLinha()][ps.getColuna()] = true;
			}
			ps.setValor(posicao.getLinha() - 2, posicao.getColuna());
			Posicao ps2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExistente(ps) && !getTabuleiro().haUmaPeca(ps) && getTabuleiro().posicaoExistente(ps2) && !getTabuleiro().haUmaPeca(ps2) && getContadorDeMovimento() == 0) {
				matriz[ps.getLinha()][ps.getColuna()] =  true;
			}
			ps.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if(getTabuleiro().posicaoExistente(ps) && existeUmaPecaOponente(ps)){
				matriz[ps.getLinha()][ps.getColuna()] = true;
			}
			ps.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if(getTabuleiro().posicaoExistente(ps) && existeUmaPecaOponente(ps)){
				matriz[ps.getLinha()][ps.getColuna()] = true;
			}
		}
		else {
			ps.setValor(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExistente(ps) && !getTabuleiro().haUmaPeca(ps)) {
				matriz[ps.getLinha()][ps.getColuna()] = true;
			}
			ps.setValor(posicao.getLinha() + 2, posicao.getColuna());
			Posicao ps2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExistente(ps) && !getTabuleiro().haUmaPeca(ps) && getTabuleiro().posicaoExistente(ps2) && !getTabuleiro().haUmaPeca(ps2) && getContadorDeMovimento() == 0) {
				matriz[ps.getLinha()][ps.getColuna()] =  true;
			}
			ps.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if(getTabuleiro().posicaoExistente(ps) && existeUmaPecaOponente(ps)){
				matriz[ps.getLinha()][ps.getColuna()] = true;
			}
			ps.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if(getTabuleiro().posicaoExistente(ps) && existeUmaPecaOponente(ps)){
				matriz[ps.getLinha()][ps.getColuna()] = true;
			}
		}
		return matriz;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
