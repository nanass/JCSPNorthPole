package JCSPNorthPole;

import org.jcsp.lang.Bucket;
import org.jcsp.lang.CSTimer;
import org.jcsp.lang.ChannelOutput;

public class MrsClaus extends NorthPoleProcess {

	private final Bucket deliverCookies;
	private final String name = "MrsClaus";
	private final CSTimer timer = new CSTimer();

	MrsClaus(Bucket deliverCookies, ChannelOutput print){
		super("MrsClaus", print);
		this.deliverCookies = deliverCookies;
	}

	@Override
	public void run() {
		while(true){
			log("Going to Santa's");
			timer.sleep(10000);
			log("Waiting with Cookies");
			deliverCookies.fallInto();
			log("Leaving Santa's");
			timer.sleep(5000);
		}
	}
}