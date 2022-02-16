package pecas.xadrez;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{
	
	private PartidaXadrez partidaXadrez;
	
	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
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
			
			//movimento especial En Passant para as peças BRANCAS
			if(posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if(getTabuleiro().posicaoExistente(esquerda) && existeUmaPecaOponente(esquerda) && getTabuleiro().piece(esquerda) == partidaXadrez.getVulnerabilidadeEnPassant()) {
					matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().posicaoExistente(direita) && existeUmaPecaOponente(direita) && getTabuleiro().piece(direita) == partidaXadrez.getVulnerabilidadeEnPassant()) {
					matriz[direita.getLinha() - 1][direita.getColuna()] = true;
				}
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
			//movimento especial En Passant para as peças PRETAS
			if(posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if(getTabuleiro().posicaoExistente(esquerda) && existeUmaPecaOponente(esquerda) && getTabuleiro().piece(esquerda) == partidaXadrez.getVulnerabilidadeEnPassant()) {
					matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().posicaoExistente(direita) && existeUmaPecaOponente(direita) && getTabuleiro().piece(direita) == partidaXadrez.getVulnerabilidadeEnPassant()) {
					matriz[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}
		return matriz;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
