package Server;

import Util.JCSPNetworkServiceIn;
import Util.JCSPNetworkServiceOut;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.Parallel;

public class StartServer {

    public static void main(String[] args){

        NettoServer ns = new NettoServer();
        Any2OneChannel printOut = Channel.any2one();

        CSProcess[] procs = {
            new BroadcastOutMain(printOut.in()),
            new JCSPNetworkServiceIn(NorthPole.GetChannel.getInput(), "5563"),
            new JCSPNetworkServiceOut(printOut.out(), "5565"),
            new JCSPNetworkServiceOut(printOut.out(), "5566")
        };
        new Parallel(procs).run();
    }
}
