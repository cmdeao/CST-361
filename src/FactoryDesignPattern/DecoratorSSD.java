package FactoryDesignPattern;

public class DecoratorSSD extends ComputerDecorator
{

	public DecoratorSSD(Computer computer) 
	{
		super(computer);	
	}

	@Override
	public void showcaseSpecs()
	{
		addHDD(computer);
		computer.showcaseSpecs();
	}
	
	private void addHDD(Computer computer)
	{
		computer.setHDD(computer.getHDD() + " Western Digital");
	}

	@Override
	public String getCpu() 
	{
		return computer.getCpu();
	}

	@Override
	public void setRam(int ram) 
	{
		computer.setRam(ram);	
	}

	@Override
	public int getRam() 
	{
		return computer.getRam();
	}

	@Override
	public void setHDD(String hdd) 
	{
		computer.setHDD(hdd);	
	}

	@Override
	public String getHDD() 
	{
		return computer.getHDD();
	}

	@Override
	public String getOS() 
	{
		return computer.getOS();
	}
}
