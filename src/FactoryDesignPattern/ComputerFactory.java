package FactoryDesignPattern;

public class ComputerFactory 
{
	public Computer getComputer(String object)
	{
		if(object == null)
		{
			return null;
		}
		
		switch(object.toUpperCase())
		{
			case "PC":
				return new PC("Intel", 16, "Seagate", "Windows");
		case "MACINTOSH":
				return new Macintosh("Coffee Lake", 16, "Toshiba", "iOS");
			case "SERVER":
				return new Server("Ryzen", 64, "Western Digitial", "Windows Server");
		}
		return null;		
	}
}
