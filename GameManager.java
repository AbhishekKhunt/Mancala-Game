
public class GameManager {

	GameBoard gameBoard;
	Player[] players = new Player[2];

	public void start() {
		gameBoard = new GameBoard();
		gameBoard.initGameboard();
		gameBoard.printGrid();
		setAllPlayerData();

		Player currentPlayer = players[0];
		int playerindex;

		while (true) {
			if (currentPlayer == players[0])
				playerindex = Utility.getInt(currentPlayer.getName() + " enter number (0-5):- ");
			else
				playerindex = Utility.getInt(currentPlayer.getName() + " enter number (6-12):- ");

			if (isplayerInputValid(currentPlayer, playerindex)) {

				if (!gameBoard.isCupEmpty(playerindex)) {
					if (gameBoard.isMancalaCup(playerindex)) {
						currentPlayer = changPlayer(currentPlayer);
					}
					gameBoard.updateGrid(playerindex);
					currentPlayer = changPlayer(currentPlayer);
					gameBoard.printGrid();
				}

				if (isGameFinish()) {
					printPlayersScore();
					break;
				}

			} else {
				System.err.println("this is not your cup");
			}
		}
	}

	boolean isGameFinish() {
		return gameBoard.isFirstPlayerAllCupEmpty() || gameBoard.isSecondPlayerAllCupEmpty();
	}

	void printPlayersScore() {

		gameBoard.calculateFirstPlayerScore();
		gameBoard.calculateSecondPlayerScore();
		System.out.println("-----------------------------");
		gameBoard.printGrid();
		System.out.println(players[0].getName() + " score is:- " + gameBoard.firstPlayerScore());
		System.out.println(players[1].getName() + " score is:- " + gameBoard.secondPlayerScore());
	}

	boolean isplayerInputValid(Player currentPlayer, int playerindex) {
		return (currentPlayer == players[0] && isFirstPlayerInputValid(playerindex))
				|| (currentPlayer == players[1] && isSecondPlayerInputValid(playerindex));
	}

	boolean isFirstPlayerInputValid(int input) {
		if (input >= 0 && input < 6)
			return true;
		return false;
	}

	boolean isSecondPlayerInputValid(int input) {
		if (input < 13 && input > 6)
			return true;
		return false;
	}

	Player changPlayer(Player currentPlayer) {
		if (currentPlayer == players[0])
			return players[1];
		return players[0];
	}

	void setAllPlayerData() {
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player();
			setPlayerData(players[i]);
		}
	}

	void setPlayerData(Player players) {
		String playerName = Utility.getString("Enter player name:- ");
		players.setName(playerName);

	}
}
