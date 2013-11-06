package JCSPNorthPole;

import org.jcsp.lang.*;

import java.util.Random;

public class Elf extends NorthPoleProcess{

    int number;
    Bucket group;
    ChannelOutput needToConsult;
    ChannelInput joinGroup;
    ChannelOutput consult;
    ChannelInput consulting;
    ChannelOutput negotiating;
    ChannelInput consulted;

    public Elf(String name, int number, Bucket group, ChannelOutput needToConsult,
			   ChannelInput joinGroup,ChannelOutput consult, ChannelInput consulting,
			   ChannelOutput negotiating, ChannelInput consulted, ChannelOutput print){
        super(name, print);
        this.number = number;
        this.group = group;
        this.needToConsult = needToConsult;
        this.joinGroup = joinGroup;
        this.consult = consult;
        this.consulting = consulting;
        this.negotiating = negotiating;
        this.consulted = consulted;
    }

    int workingTime = 3;

	@Override
    public void run(){
        Random rng = new Random();
        CSTimer timer = new CSTimer();
        while(true){
            log("Working");
            timer.sleep(workingTime + rng.nextInt(workingTime));
            needToConsult.write(number);
            Integer join = (Integer)joinGroup.read();
            if(join == 1) {
                group.fallInto();
                log ("In the waiting room");
                consult.write(number);
                log("Entering Santa's house");
                consulting.read();
                log("Consulting with Santa");
                negotiating.write(1);
                consulted.read();
                log("Leaving santa's");
            }
            else {
                log("Waiting room is full");
                timer.sleep(workingTime + rng.nextInt(workingTime));
            }
        }
    }
}
