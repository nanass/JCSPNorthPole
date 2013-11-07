package WishListProcess;

import Util.Data;
import org.jcsp.lang.*;

import java.util.ArrayList;


public class WishList implements CSProcess {

    AltingChannelInput wishIn;
    AltingChannelInput delivery;
    ChannelOutput out;
    Alternative alt;
    ArrayList<Data> wishList = new ArrayList<Data>();

    public WishList(AltingChannelInput wishIn, AltingChannelInput delivery, ChannelOutput out){
        this.wishIn = wishIn;
        this.delivery = delivery;
        Guard[] guard = {delivery, wishIn};
        alt = new Alternative(guard);
        this.out = out;
    }

    public void run(){
        while(true){
            switch (alt.priSelect()){
                case 0 :
                    delivery.read();
                    String output = "[";
                    System.out.println("Gifts for: ");
                    for(Data d : wishList){
                        output += "\"" + d.getAuthor() + " : " + d.getMessage() + "\",";
                    }
                    wishList.clear();
                    output = output.substring(0,output.length() - 1) + "]";
                    System.out.println(output);
                    Data data = new Data("all", output);
                    data.setType("Delivery");
                    out.write(data);
                    break;
                case 1 :
                    Data msg = (Data)wishIn.read();
                    if(msg.getType().equals("wishlist")){
                        wishList.add(msg);
                        System.out.println(msg.getMessage());
                    }
                    break;
            }
        }
    }
}