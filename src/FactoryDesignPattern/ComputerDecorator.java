package FactoryDesignPattern;

public abstract class ComputerDecorator implements Computer
{
	protected Computer computer;
	
	public ComputerDecorator(Computer computer)
	{
		this.computer = computer;
	}
	
	public void showcaseSpecs()
	{
		computer.showcaseSpecs();
	}
}
