public class Score 
{
	private int correctPositionAndColor;
	private int correctColor;

	public Score(int correctPositionAndColor, int correctColor) 
	{
		super();
		this.correctPositionAndColor = correctPositionAndColor;
		this.correctColor = correctColor;
	}

	public int getCorrectPositionAndColor() {
		return correctPositionAndColor;
	}
	
	public String toString(){
		return "(Black Flags:" + this.correctPositionAndColor +", White Flags: " + this.correctColor + ")";
	}
}
