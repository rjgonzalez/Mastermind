import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class MastermindRun 
{

//	private static Pattern actualPattern;
	private static final Pattern defaultGuess = Pattern.generateAPattern("pyyp");
	private static String[] messages ={"Game/World over... - Skynet"
									,"You should have taken the red pill."
									,"YOU ARE DOING IT WRONG!"
									,"Thank you, now keep enjoying your crappy Windows :D"
									,"Not feeling lucky any more punk?"
									,"SE ACA-VOS!"};


	public static void main(String[] args) 
	{
		boolean play = true;
		while(play)
		{
			Pattern actualPattern = getActualPatternFromUser();
			startGuessing(actualPattern);
			play = continueGame();
		}
		System.out.println("\n" + sassyEndMessage());
		System.out.println("See ya next time!");
	}

	private static void startGuessing(Pattern actualPattern) 
	{

		Pattern currentGuess = defaultGuess;
		ArrayList<Pattern> possiblePatterns = Pattern.allPossiblePossibilities;
		int totalGuesses = 1;

		Score currentScore = Pattern.comparePatterns(currentGuess, actualPattern);

		while(true){
			System.out.println("\n\nActual Pattern: " + actualPattern);
			System.out.println("Trying this guess: " + currentGuess);
			if(currentScore.getCorrectPositionAndColor() == 4){
				System.out.println(currentScore);
				System.out.println("I win, in " + totalGuesses + " guesses.\n\n");
				break;
			}
			else{
				System.out.println(currentScore);
				System.out.println("I didn't win, yet :), will try again.");
			}

			possiblePatterns = Pattern.getNextPossibleGuesses(currentGuess, currentScore, possiblePatterns);
			currentGuess = getNextPossibleGuess(possiblePatterns);
			currentScore = Pattern.comparePatterns(currentGuess, actualPattern);
			totalGuesses++;
		}
	}

	private static boolean continueGame()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to play again?\n(Write 'y' to play again, anything else to not play again)");
		return scan.nextLine().toLowerCase().equals("y");
	}

	private static Pattern getNextPossibleGuess(ArrayList<Pattern> possiblePatterns){
		Random r = new Random();
		//System.out.println("\n\nDEBUG: SIZE OF POSSIBLE PATTERNS: " + possiblePatterns.size() + "\n\n");
		int guess = r.nextInt(possiblePatterns.size());
		return possiblePatterns.get(guess);
	}

	public static Pattern getActualPatternFromUser()
	{
		System.out.println("\n\n\nNEW GAME!:\n");
		boolean validPattern= false;
		String input="";
		while(!validPattern)
		{
			System.out.println("Please enter a four peg pattern choosing from these colors:\n" +
					"Colors: R=red, Y=yellow, G=green, B=blue, O=orange and P=purple \n" +
			"(e.g. gypb)");
			Scanner scanner = new Scanner(System.in);
			
			input= scanner.nextLine();
			input = input.toLowerCase();
			validPattern = validatePattern(input);
		}
		return Pattern.generateAPattern(input);
	}

	private static boolean validatePattern(String input) 
	{
		for(int counter = 0 ; counter < 4 ; counter++)
		{
			char currentCharacter=input.charAt(counter);
			if(Colors.generate(currentCharacter)==null)
			{
				System.out.println("Pattern given is not allowed, please read instructions carefully...RETARD!.\n");
				return false;
			}
		}
		return true;
	}
	
	private static String sassyEndMessage()
	{
		
		Random r = new Random();
		int msgIndex = r.nextInt(messages.length);
		return messages[msgIndex];
		
	}

}
