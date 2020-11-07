package FactoryDesignPattern;

public class DecoratorRam extends ComputerDecorator 
{

	public DecoratorRam(Computer computer) 
	{
		super(computer);
	}

	@Override
	public String getCpu() 
	{
		return computer.getCpu();
	}

	@Override
	public void setRam(int ram) 
	{
		computer.setRam(computer.getRam() + 16);	
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