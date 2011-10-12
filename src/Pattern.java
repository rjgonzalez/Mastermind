import java.util.ArrayList;
import java.util.HashMap;


public class Pattern
{
	
	public static ArrayList<Colors> possibleColors;
	public static ArrayList<Pattern> allPossiblePossibilities;

	private Colors colorPos1;
	private Colors colorPos2;
	private Colors colorPos3;
	private Colors colorPos4;
	
	static
	{
		Pattern.possibleColors = new ArrayList<Colors>();
		possibleColors.add(Colors.RED);
		possibleColors.add(Colors.GREEN);
		possibleColors.add(Colors.BLUE);
		possibleColors.add(Colors.YELLOW);
		possibleColors.add(Colors.PURPLE);
		possibleColors.add(Colors.ORANGE);
		
		allPossiblePossibilities = generateAllPossibilities();
	}
	
	private static ArrayList<Pattern> generateAllPossibilities()
	{
		ArrayList<Pattern> allPosibilities  = new ArrayList<Pattern>();
		for(int counter = 0 ; counter <1296 ; counter++)
		{
			allPosibilities.add(Pattern.generateAPattern(counter));
		}
		return allPosibilities;
		
	}
	
	public static Pattern generateAPattern(int num)
	{
		Colors colorPos4 = Colors.generate((num/1)%6);
		Colors colorPos3 = Colors.generate((num/6)%6);
		Colors colorPos2 = Colors.generate((num/36)%6);
		Colors colorPos1 = Colors.generate((num/216)%6);
		
		return new Pattern(colorPos1, colorPos2, colorPos3, colorPos4);
	}

	public static Pattern generateAPattern(String input)
	{
		Colors colorPos4 = Colors.generate(input.charAt(3));
		Colors colorPos3 = Colors.generate(input.charAt(2));
		Colors colorPos2 = Colors.generate(input.charAt(1));
		Colors colorPos1 = Colors.generate(input.charAt(0));
		
		return new Pattern(colorPos1, colorPos2, colorPos3, colorPos4); 
	}
	
	public Pattern
		(Colors colorPos1, Colors colorPos2, Colors colorPos3,Colors colorPos4) 
	{
		super();
		this.colorPos1 = colorPos1;
		this.colorPos2 = colorPos2;
		this.colorPos3 = colorPos3;
		this.colorPos4 = colorPos4;
	}
	
	public ArrayList<Colors> getColors()
	{
		ArrayList<Colors> colors = new ArrayList<Colors>();
		colors.add(this.colorPos1);
		colors.add(this.colorPos2);
		colors.add(this.colorPos3);
		colors.add(this.colorPos4);
		
		return colors;
	}
	
	public String toString()
	{
		return 
			"("+
			this.colorPos1.toString()+","+
			this.colorPos2.toString()+","+
			this.colorPos3.toString()+","+
			this.colorPos4.toString()+
			")";
	}
	
	public String toSimpleString()
	{
		return ""+this.colorPos1.getCharacter()+this.colorPos2.getCharacter()+this.colorPos3.getCharacter()+this.colorPos4.getCharacter();
	}
	
	public static Score comparePatterns(Pattern guess,Pattern pattern)
	{
		ArrayList<Integer> positionOfNonBlackFlags = new ArrayList<Integer>();
		int blackFlags=0;
		int whiteFlags=0;

		ArrayList<Colors> guessColors = guess.getColors();
		ArrayList<Colors> patternColors = pattern.getColors();
		HashMap<Colors, Integer> colorsMap = getEmptyColorsMap();

		for(int counter =0 ; counter < 4 ; counter++)
		{
			if(guessColors.get(counter).getCharacter() == patternColors.get(counter).getCharacter())
			{
				++blackFlags;
			}
			else
			{
				positionOfNonBlackFlags.add(counter);
			}
		}

		int currentValue = 0;
		Colors currentColor = null;
		for(Integer pos: positionOfNonBlackFlags)
		{
			currentColor = patternColors.get(pos);
			currentValue = colorsMap.get(currentColor) + 1;
			colorsMap.put(currentColor, currentValue);
		}

		for(Integer pos: positionOfNonBlackFlags)
		{
			currentColor = guessColors.get(pos);
			
			for(Colors color: patternColors){
				if(currentColor.getCharacter() == color.getCharacter()){
					if(colorsMap.get(currentColor) > 0){
						whiteFlags++;
						currentValue = colorsMap.get(currentColor) - 1;
						colorsMap.put(currentColor, currentValue);
						break;
					}
				}
			}
		}

		return new Score(blackFlags, whiteFlags);

	}
	
	private static HashMap<Colors, Integer> getEmptyColorsMap(){
		HashMap<Colors, Integer> temp = new HashMap<Colors,Integer>();
		temp.put(Colors.BLUE, 0);
		temp.put(Colors.RED, 0);
		temp.put(Colors.YELLOW, 0);
		temp.put(Colors.PURPLE, 0);
		temp.put(Colors.GREEN, 0);
		temp.put(Colors.ORANGE, 0);
		return temp;

	}
	
	public static ArrayList<Pattern> getNextPossibleGuesses(Pattern currentGuess, Score score, ArrayList<Pattern> possiblePatterns){
		ArrayList<Pattern> matchingScores = new ArrayList<Pattern>();
		for(Pattern pattern: possiblePatterns){
			if(Pattern.comparePatterns(currentGuess, pattern).toString().equals(score.toString())){
				matchingScores.add(pattern);
			}
		}
		
		//matchingScores.remove(matchingScores.indexOf(currentGuess));
		return matchingScores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((colorPos1 == null) ? 0 : colorPos1.hashCode());
		result = prime * result
				+ ((colorPos2 == null) ? 0 : colorPos2.hashCode());
		result = prime * result
				+ ((colorPos3 == null) ? 0 : colorPos3.hashCode());
		result = prime * result
				+ ((colorPos4 == null) ? 0 : colorPos4.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pattern other = (Pattern) obj;
		if (colorPos1 == null) {
			if (other.colorPos1 != null)
				return false;
		} else if (!colorPos1.equals(other.colorPos1))
			return false;
		if (colorPos2 == null) {
			if (other.colorPos2 != null)
				return false;
		} else if (!colorPos2.equals(other.colorPos2))
			return false;
		if (colorPos3 == null) {
			if (other.colorPos3 != null)
				return false;
		} else if (!colorPos3.equals(other.colorPos3))
			return false;
		if (colorPos4 == null) {
			if (other.colorPos4 != null)
				return false;
		} else if (!colorPos4.equals(other.colorPos4))
			return false;
		return true;
	}

}
