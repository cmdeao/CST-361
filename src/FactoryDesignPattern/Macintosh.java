package FactoryDesignPattern;

public class Macintosh implements Computer 
{

	String cpu;
	int ram;
	String hdd;
	String os;
	
	public Macintosh(String cpu, int ram, String hdd, String os)
	{
		this.cpu = cpu;
		this.ram = ram;
		this.hdd = hdd;
		this.os = os;
	}
	
	@Override
	public String getCpu() 
	{
		return cpu;
	}

	@Override
	public int getRam() 
	{
		return ram;
	}

	@Override
	public String getHDD() 
	{
		return hdd;
	}

	@Override
	public String getOS() 
	{
		return os;
	}

	@Override
	public void setRam(int ram) 
	{
		this.ram = ram;
	}

	@Override
	public void setHDD(String hdd) 
	{
		this.hdd = hdd;	
	}
	
	@Override
	public void showcaseSpecs() 
	{
		System.out.println(String.format("CPU: %s - Ram: %d GB - HDD: %s - OS: %s", cpu, ram, hdd, os));
	}
}
