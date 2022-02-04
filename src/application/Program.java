package application;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		while(true) {
			UI.mostraTabuleiro(partidaXadrez.getPieces());
			System.out.println();
			System.out.print("Origem: ");
			PosicaoXadrez origem = UI.lendoPosicaoXadrez(sc);
			
			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrez destino = UI.lendoPosicaoXadrez(sc);
			
			PecaXadrez pecaCapturada = partidaXadrez.movimentoDasPecas(origem, destino);
		}

	}

}
