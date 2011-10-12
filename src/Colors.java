
public enum Colors 
{
	RED(0,'r'),GREEN(1,'g'),BLUE(2,'b'),YELLOW(3,'y'),PURPLE(4,'p'),ORANGE(5,'o');
	
	private int number;
	private char character;
	
	Colors(int number,char character)
	{
		this.number=number;
		this.character= character;
	}
	
	
	public static Colors generate (int number)
	{
		for(Colors color :Colors.values())
		{
			if(color.getNumber() == number)
			{
				return color;
			}
		}
		return null;
	}
	
	public static Colors generate(char character)
	{
		for(Colors color :Colors.values())
		{
			if(color.getCharacter() == character)
			{
				return color;
			}
		}
		return null;
	}



	public int getNumber() 
	{
		return number;
	}

	public char getCharacter() 
	{
		return character;
	}
	
	public String toString()
	{
		return this.name();
	}


}
