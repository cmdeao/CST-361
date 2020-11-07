package beans;

public class Track 
{
	String title = "";
	int number = 0;
	
	public Track(String incTitle, int incNumber)
	{
		this.title = incTitle;
		this.number = incNumber;
		
	}
	
	public String getTitle()
	{
		return title;
	}
	
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public void setNumber(int number)
	{
		this.number = number;
	}
}
