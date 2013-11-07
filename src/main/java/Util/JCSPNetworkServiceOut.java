package Util;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;
import org.jeromq.ZMQ;

public class JCSPNetworkServiceOut implements CSProcess {
    private final ChannelOutput out;
    String port;

    public JCSPNetworkServiceOut(ChannelOutput out, String port){
        this.port = port;
        this.out = out;
    }

    @Override
    public void run() {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket subscriber = context.socket(ZMQ.PULL);
        subscriber.bind("tcp://*:"+port);
        while (!Thread.currentThread ().isInterrupted ()) {
            Data contents = Data.buildFromBytes(subscriber.recv());
            out.write(contents);
        }
        subscriber.close();
        context.term ();
    }
}
