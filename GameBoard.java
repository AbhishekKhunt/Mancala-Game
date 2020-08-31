
public class GameBoard {

	Cup[] cups = new Cup[14];
	int firstPlayerMancalaCup = (cups.length / 2) - 1;
	int secondPlayerMancalaCup = (cups.length - 1);

	void initGameboard() {

		for (int i = 0; i < cups.length; i++) {
			cups[i] = new Cup();
			if (i == firstPlayerMancalaCup || i == secondPlayerMancalaCup)
				cups[i].setValue(0);
			else
				cups[i].setValue(4);
			cups[i].setPosition(i);
		}
	}

	void updateGrid(int index) {
		int lastIndex = findLastIndex(index);

		if (iscaptured(index,lastIndex)) {

			increaseNextCupsValue(index);
			capturedCup(index, lastIndex);

		} else
			increaseNextCupsValue(index);

	}

	void capturedCup(int index, int lastIndex) {
		int oppositeIndex = Math.abs(12 - lastIndex);

		if (index < firstPlayerMancalaCup - 1) {
			int value = 1 + cups[oppositeIndex].getValue() + cups[firstPlayerMancalaCup].getValue();
			cups[firstPlayerMancalaCup].setValue(value);
		} else {
			int value = 1 + cups[oppositeIndex].getValue() + cups[secondPlayerMancalaCup].getValue();
			cups[secondPlayerMancalaCup].setValue(value);
		}

		cups[index].setValue(0);
		cups[lastIndex].setValue(0);
		cups[oppositeIndex].setValue(0);
		System.out.println("captured...");

	}

	void increaseNextCupsValue(int index) {
		for (int i = cups[index].getPosition() + 1; i <= cups[index].getPosition() + cups[index].getValue(); i++) {
			int value = cups[i].getValue();
			cups[i].setValue(value + 1);
			if (i == 13)
				break;
		}

		for (int i = 0; i < cups[index].getValue() - (secondPlayerMancalaCup - cups[index].getPosition()); i++) {
			int value = cups[i].getValue();
			cups[i].setValue(value + 1);
		}
		cups[index].setValue(0);
	}

	int findLastIndex(int index) {
		return cups[index].getValue() + index;
	}

	boolean iscaptured(int index,int lastIndex) {
		int oppositeIndex = Math.abs(12 - lastIndex);
		return (lastIndex < secondPlayerMancalaCup) && cups[lastIndex].getValue() == 0
				&& (cups[oppositeIndex].getValue() > 0);
	}

	boolean isCupEmpty(int index) {
		if (cups[index].getValue() == 0) {
			System.err.println("invalid input cup is empty");
			return true;
		}
		return false;
	}

	boolean isMancalaCup(int index) {
		int lastIndex = findLastIndex(index);
		if (lastIndex == firstPlayerMancalaCup || lastIndex == secondPlayerMancalaCup) {
			return true;
		}
		return false;
	}

	boolean isFirstPlayerAllCupEmpty() {

		for (int i = 0; i < firstPlayerMancalaCup; i++) {
			if (cups[i].getValue() != 0)
				return false;
		}

		return true;
	}

	void calculateFirstPlayerScore() {
		int sum = 0;
		for (int i = cups.length - 2; i > firstPlayerMancalaCup; i--) {

			sum += cups[i].getValue();
			cups[i].setValue(0);
		}

		int playerOneScore = cups[secondPlayerMancalaCup].getValue() + sum;
		cups[secondPlayerMancalaCup].setValue(playerOneScore);

	}

	boolean isSecondPlayerAllCupEmpty() {

		for (int i = cups.length - 2; i > firstPlayerMancalaCup; i--) {
			if (cups[i].getValue() != 0)
				return false;
		}

		return true;
	}

	void calculateSecondPlayerScore() {
		int sum = 0;
		for (int i = 0; i < firstPlayerMancalaCup; i++) {

			sum += cups[i].getValue();
			cups[i].setValue(0);
		}

		int playerOneScore = cups[firstPlayerMancalaCup].getValue() + sum;
		cups[firstPlayerMancalaCup].setValue(playerOneScore);

	}

	int firstPlayerScore() {
		return cups[firstPlayerMancalaCup].getValue();
	}

	int secondPlayerScore() {
		return cups[secondPlayerMancalaCup].getValue();
	}

	void printGrid() {
		System.out.print("\t");
		for (int i = cups.length - 2; i > firstPlayerMancalaCup; i--) {
			System.out.print(cups[i].getPosition() + "");
			System.out.print("(" + cups[i].getValue() + ")   ");
		}
		System.out.println();
		System.out.print("(" + cups[secondPlayerMancalaCup].getValue() + ")   ");

		System.out.print("|--------------------------------------------|");
		System.out.print("   ");
		System.out.print("(" + cups[firstPlayerMancalaCup].getValue() + ")   ");
		System.out.println();
		System.out.print("\t");
		for (int i = 0; i < firstPlayerMancalaCup; i++) {
			System.out.print(cups[i].getPosition() + "");
			System.out.print("(" + cups[i].getValue() + ")    ");
		}
		System.out.println();
		System.out.println();

	}
}
