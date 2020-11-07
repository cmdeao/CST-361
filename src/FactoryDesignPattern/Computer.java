package FactoryDesignPattern;

public interface Computer
{
	String getCpu();
	void setRam(int ram);
	int getRam();
	void setHDD(String hdd);
	String getHDD();
	String getOS();
	void showcaseSpecs();
}