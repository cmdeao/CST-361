package FactoryDesignPattern;

public class MainDemo
{
	public static void main(String[] args)
	{
		ComputerFactory compFactory = new ComputerFactory();
		
		Computer computer1 = compFactory.getComputer("pc");
		System.out.println(String.format("PC CPU: %s - Ram: %d GB - HDD: %s - CPU: %s", computer1.getCpu(), computer1.getRam(), computer1.getHDD(), computer1.getOS()));
		
		Computer computer2 = compFactory.getComputer("macintosh");
		System.out.println(String.format("PC CPU: %s - Ram: %d GB - HDD: %s - CPU: %s", computer2.getCpu(), computer2.getRam(), computer2.getHDD(), computer2.getOS()));
		
		Computer computer3 = compFactory.getComputer("server");
		System.out.println(String.format("PC CPU: %s - Ram: %d GB - HDD: %s - CPU: %s", computer3.getCpu(), computer3.getRam(), computer3.getHDD(), computer3.getOS()));
		
		//Computer computer = new PC("Intel", 16, "Seagate", "Windows");
		Computer computer = new DecoratorSSD(new PC("Intel", 16, "Seagate", "Windows"));
		computer = new DecoratorRam(computer);
		computer.showcaseSpecs();
	}
}