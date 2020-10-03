package beans;

public class Track 
{
	String title = "";
	int number = 0;
	
	public String GetTitle()
	{
		return title;
	}
	
	
	public void SetTitle(String inc)
	{
		this.title = inc;
	}
	
	public int GetNumber()
	{
		return number;
	}
	
	public void SetNumber(int inc)
	{
		this.number = inc;
	}
}
