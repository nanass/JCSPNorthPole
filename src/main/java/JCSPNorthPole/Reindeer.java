package JCSPNorthPole;

import org.jcsp.lang.*;

import java.util.Random;

public class Reindeer extends NorthPoleProcess{

	AltingBarrier stable;
	AltingBarrier sleigh;
	ChannelOutput harness;
	ChannelInput harnessed;
	ChannelInput returned;
	ChannelInput unharness;
	int holidayTime = 4000;

    public Reindeer(String name, AltingBarrier stable, AltingBarrier sleigh,
					ChannelOutput harness, ChannelInput harnessed,
                    ChannelInput returned, ChannelInput unharness){
        super(name);
        this.stable = stable;
        this.sleigh = sleigh;
        this.harness = harness;
        this.harnessed = harnessed;
        this.returned = returned;
        this.unharness = unharness;
    }

	@Override
    public void run(){
        Random rng = new Random();
        CSTimer timer = new CSTimer();
        while (true) {
            log("Going on Vacation");
            timer.sleep(holidayTime + rng.nextInt(holidayTime));
            log("Back from vacation");
            stable.sync();
            harness.write(name);
            log("Getting Hitched");
            harnessed.read();
            sleigh.sync();
            log("Delivering Toys");
            returned.read();
            log("Getting unhitched");
            unharness.read();
        }
    }
}
