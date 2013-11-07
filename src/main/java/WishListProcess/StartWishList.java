package WishListProcess;

import Util.JCSPNetworkServiceIn;
import Util.JCSPNetworkServiceOut;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannel;
import org.jcsp.lang.Parallel;

public class StartWishList {
    public static void main(String[] args){

        One2OneChannel delivery = Channel.one2one();
        One2OneChannel wish = Channel.one2one();
        One2OneChannel printOut = Channel.one2one();

        CSProcess[] procs = {
            new WishList(wish.in(), delivery.in(), printOut.out()),
            new JCSPNetworkServiceOut(wish.out(), "5563"),
            new JCSPNetworkServiceOut(delivery.out(), "5564"),
            new JCSPNetworkServiceIn(printOut.in(), "5565")
        };
        new Parallel(procs).run();
    }
}
