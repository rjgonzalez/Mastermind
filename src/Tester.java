import java.util.ArrayList;


public class Tester 
{
	
	public static void main(String[] args) 
	{
//		testGenerateAPossibility();
//		testGenerateAllPossiblity();
//		testGenerateScores();
//		testArrayListRemove();
		testColorsEnumNonExistent();
	}
	
	public static void testArrayListRemove(){
		Pattern one = Pattern.generateAPattern(9);
		Pattern two = Pattern.generateAPattern(9);
		
		ArrayList<Pattern> tmp = new ArrayList<Pattern>();
		
		tmp.add(one);
		System.out.println("Size: " +tmp.size());

		tmp.remove(tmp.indexOf(two));
		System.out.println("Size: " +tmp.size());		
	}
	
	public static void testGenerateAPossibility()
	{
		System.out.println(Pattern.generateAPattern(9).toString());
		System.out.println(Pattern.generateAPattern(1295).toString());
		
		
	}
	
	public static void testGenerateAllPossiblity()
	{
		int counter =0;
		for(Pattern pos: Pattern.allPossiblePossibilities)
		{
			System.out.print(counter+++":  ");
			System.out.println(pos);
		}
	}
	
	public static void testGenerateScores(){
		
//		Pattern pattern = Pattern.generateAPattern("ybyb");
//		Pattern guess = Pattern.generateAPattern("ygbg");
		
		Pattern pattern = Pattern.generateAPattern("bbbb");
		Pattern guess = Pattern.generateAPattern("yyby");
		System.out.println(Pattern.comparePatterns(guess, pattern));
		
	}
	
	public static void testColorsEnumNonExistent()
	{
		Colors col = Colors.generate('a');
	}
	
	

}
