package Util;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;
import org.jeromq.ZMQ;

public class JCSPNetworkServiceIn implements CSProcess {

    private final ChannelInput in;
    String port;

    public JCSPNetworkServiceIn(ChannelInput in, String port){
        this.in = in;
        this.port = port;
    }

    @Override
    public void run() {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket push = context.socket(ZMQ.PUSH);
        push.connect("tcp://localhost:" + port);
        while(!Thread.currentThread().isInterrupted()){
            Data d = (Data)in.read();
            push.send(d.toByteArray());
        }
        push.close();
        context.term ();
    }
}
