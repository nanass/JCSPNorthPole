package JCSPNorthPole;

import Util.Data;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;

public class NorthPoleProcess implements CSProcess {

	String name;
	ChannelOutput print;
	NorthPoleProcess(String name, ChannelOutput print){
		this.name = name;
		this.print = print;
	}
	@Override
	public void run() {}

	public void log(String s){
		print.write(new Data(name,s));
		System.out.println(name + " : " + s);
	}
}
