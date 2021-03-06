package Server;

import Util.Data;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;

import java.io.IOException;
import java.util.concurrent.Future;

public class BroadcastOutMain implements CSProcess {
    final ChannelInput in;
    Broadcaster b;

    public BroadcastOutMain(ChannelInput in) {
        this.b = BroadcasterFactory.getDefault().lookup("/");
        this.in = in;
    }

    private final ObjectMapper mapper = new ObjectMapper();
    public void run() {
        while (true) {
           Data data = (Data)in.read();
                Future br = b.broadcast("{\"message\": " + wrapInQuotes(data) + "," +
										 "\"who\":\""+data.getWho()+"\","+
                                         "\"type\":\"northPole\"}");

        }
    }
    private String wrapInQuotes(Data data){
        return ("Delivery".equals(data.getType()) ? data.getMessage() : "\"" + data.getMessage()+ "\" " );
    }
}
