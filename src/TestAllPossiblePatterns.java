import java.util.ArrayList;
import java.util.Random;


public class TestAllPossiblePatterns 
{
	
	public static void main(String[] args) 
	{
		double sumOfAverages=0;
		int counter;
		System.out.println("Worse case scenario, it takes "+getWorseCaseScenario()+" guesses.");
		for(counter = 0 ; counter < 10 ; counter++)
			sumOfAverages+=averageAllPatterns();
		System.out.println("Average amount of guesses of checking all possibilities "+counter+" times:\n"+sumOfAverages/counter);
	}
	
	private static double averageAllPatterns()
	{
		System.out.println("Calculating average of all 1296 posibilities(May take a few seconds):");
		ArrayList<Pattern> patterns=Pattern.allPossiblePossibilities;
		int guesses=0;
		for(Pattern aPattern : patterns)
		{
			guesses+=startGuessing(aPattern);
		}
		return (guesses+0.0)/(patterns.size());
	}
	
	private static int getWorseCaseScenario()
	{
		System.out.println("Calculating worse case scenario:");
		ArrayList<Pattern> patterns=Pattern.allPossiblePossibilities;
		int guessAmount=0;
		int worseGuess=0;
		for(Pattern aPattern : patterns)
		{
			guessAmount=startGuessing(aPattern);
			if(worseGuess<guessAmount)
			{
				worseGuess=guessAmount;
			}
		}
		return worseGuess;
	}
	
	private static int startGuessing(Pattern actualPattern) 
	{

//		System.out.println("DEBUG:"+"Guessing:"+actualPattern.toString());
		Pattern currentGuess = Pattern.generateAPattern("pyyp");
		ArrayList<Pattern> possiblePatterns = Pattern.allPossiblePossibilities;
		int totalGuesses = 1;

		Score currentScore = Pattern.comparePatterns(currentGuess, actualPattern);

		while(true){

			if(currentScore.getCorrectPositionAndColor() == 4){
//				System.out.println("DEBUG:"+"GuessedSoFar"+totalGuesses);
				return totalGuesses;
			}
			else{
				
			}

			possiblePatterns = Pattern.getNextPossibleGuesses(currentGuess, currentScore, possiblePatterns);
			currentGuess = getNextPossibleGuess(possiblePatterns);
			currentScore = Pattern.comparePatterns(currentGuess, actualPattern);
			totalGuesses++;
		}
	}
	
	private static Pattern getNextPossibleGuess(ArrayList<Pattern> possiblePatterns){
		Random r = new Random();
		//System.out.println("\n\nDEBUG: SIZE OF POSSIBLE PATTERNS: " + possiblePatterns.size() + "\n\n");
		int guess = r.nextInt(possiblePatterns.size());
		return possiblePatterns.get(guess);
	}

}
