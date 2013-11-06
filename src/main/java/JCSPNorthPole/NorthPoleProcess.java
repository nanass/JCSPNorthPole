package JCSPNorthPole;

import org.jcsp.lang.CSProcess;

public class NorthPoleProcess implements CSProcess {

	String name;

	NorthPoleProcess(String name){
		this.name = name;
	}
	@Override
	public void run() {}

	public void log(String s){
		System.out.println(name + " : " + s);
	}
}
