package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class UI {
	//codigos das cores do texto
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	//codigos das cores do fundo
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	//metodo para limpar a tela a cada jogada
	public static void limpaTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	//metodo para ler uma posição informada pelo usuário
	public static PosicaoXadrez lendoPosicaoXadrez(Scanner sc) {
		try {
			String str = sc.nextLine();
			char coluna = str.charAt(0);
			int linha = Integer.parseInt(str.substring(1));//recorta a string na posição informada:(1) e converte o resultado para inteiro
			return new PosicaoXadrez(coluna, linha);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Erro na leitura da posição. Os valores validos são a1 até h8.");
		}
	}
	
	public static void mostraPartida(PartidaXadrez partidaXadrez, List<PecaXadrez> capturada) {
		mostraTabuleiro(partidaXadrez.getPieces());
		System.out.println();
		mostraPecasCapturadas(capturada);
		System.out.println();
		System.out.println("Turno: " + partidaXadrez.getTurno());
		if(!partidaXadrez.getCheckMate()) {
			System.out.println("Esperando o jogador: " + partidaXadrez.getJogadorAtual());
			if(partidaXadrez.getCheck()) {
				System.out.println("CHECK!");
			}
		}
		else {
			System.out.println("CHECKMATE!");
			System.out.println("O vencedor é: " + partidaXadrez.getJogadorAtual());
		}
	}
	
	public static void mostraTabuleiro(PecaXadrez[][] pieces) {
		for(int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for(int j = 0; j < pieces.length; j++) {
				mostraPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");

	}
	
	public static void mostraTabuleiro(PecaXadrez[][] pieces, boolean[][] movimentosPossiveis) {
		for(int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for(int j = 0; j < pieces.length; j++) {
				mostraPiece(pieces[i][j], movimentosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");

	}
	
	private static void mostraPiece(PecaXadrez piece, boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if(piece == null) {
			System.out.print("-" + ANSI_RESET);
		}
		else {
			if(piece.getCor() == Cor.BRANCA) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			}
			else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	//metodo que mostra as peças capturadas
	private static void mostraPecasCapturadas(List<PecaXadrez> capturada) {
		List<PecaXadrez> branca = capturada.stream().filter(x -> x.getCor() == Cor.BRANCA).collect(Collectors.toList());
		List<PecaXadrez> preta = capturada.stream().filter(x -> x.getCor() == Cor.PRETA).collect(Collectors.toList());
		System.out.println("Peças capturadas: ");
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(branca.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print("Pretas: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(preta.toArray()));
		System.out.print(ANSI_RESET);
	}
}
