package net.gobbz.tictactoe.server;

import java.net.ServerSocket;
import java.net.Socket;

import net.gobbz.tictactoe.TicTacToe;

public class TicTacToeServer extends TicTacToe {
	
	private Socket clientSocket;
	private static int FELDGROESSE, PORT;
	private ServerSocket server;
	

	@SuppressWarnings("static-access")
	public TicTacToeServer(int FELDGROESSE, int PORT) {
		super(FELDGROESSE);
		this.FELDGROESSE = FELDGROESSE;
		this.PORT = PORT;
	}
	
	public static void main(String[] Args) {
		TicTacToeServer ticTacToeServer = null;
		try {
			ticTacToeServer = new TicTacToeServer(3, 65000);
		}
	}
	
}
