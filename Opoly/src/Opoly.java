import java.util.Random;

/**
 * @author sagesilberman
 *
 */
public class Opoly {

	private int boardSize; // describes the board size
	private int reward; // the reward number
	private int numRounds; // number of rounds
	String board = ""; // builds board
	private int position; // position of player on board
	private Random random; // number randomizer

	/**
	 * Constructs an Opoly with a board size and random number generator
	 * 
	 * @param bs the board size
	 */
	public Opoly(int bs) {
		boardSize = bs;
		random = new Random();
	}

	/**
	 * Constructs an Opoly with a board size and random number generator that is
	 * kept constant
	 * 
	 * @param bs   the board size
	 * @param seed the constant number instead of random one
	 */
	public Opoly(int bs, int seed) {
		boardSize = bs;
		random = new Random(seed);
	}

	/**
	 * Initializes the order the game will run
	 */
	public void playGame() {
		initializeGame();
		while (isGameOver() == false) {
			drawBoard();
			spinAndMove();
		}
		drawBoard();
		spinAndMove();
		displayReport();
		isGameOver();

	}

	/**
	 * Returns <code>true</code> if the reward is less than or equal to 1000
	 * 
	 * @return <code>true</code> if the reward is less than or equal to 1000;
	 *         <code>false</code> otherwise gameOver is false
	 * 
	 */
	private boolean isGameOver() {
		if (reward >= 1000) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Prints out the position of player on the board and their reward that goes
	 * with the position
	 */
	private void drawBoard() {
		for (int i = 0; i < boardSize; i++) {
			if (i == position) {
				board += "o";
			} else {
				board += "*";
			}

		}
		System.out.println(board + " " + reward);
		board = " ";
	}

	/**
	 * Initializes the game by setting the variables to a starting point of 0
	 */
	private void initializeGame() {
		reward = 0;
		position = 0;

	}

	/**
	 * Simulates a round of Opoly
	 */
	public void spinAndMove() {
		numRounds++;
		spin();
		move();
	}

	/**
	 * Returns a single random number between 1 and 5
	 * 
	 * @return random number between 1 and 5
	 */
	public int spin() {
		// Generate a number between 1 and 5
		return random.nextInt(5) + 1;
	}

	/**
	 * Simulates the movement of Opoly and tallies the rewards accordingly
	 */
	private void move() {
		position = (position + spin()) % boardSize;
		reward = reward + 100;

		if (position == boardSize - 1) {
			position = position - 3;
		}
		if (position % 7 == 0) {
			reward = reward * 2;
		}
		if (position % 10 == 0) {
			position = position + 50;
		}
	}

	/**
	 * Prints out number of rounds played and the final reward
	 */
	public void displayReport() {
		System.out.println("game over");
		System.out.println("rounds of play: " + numRounds);
		System.out.println("final reward: " + reward);
	}
}
