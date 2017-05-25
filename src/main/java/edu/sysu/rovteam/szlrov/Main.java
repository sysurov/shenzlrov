package edu.sysu.rovteam.szlrov;

import edu.sysu.rovteam.szlrov.Network.Network;
import edu.sysu.rovteam.szlrov.Network.SendRovData;

/**
 * Created by sunny on 17-5-20.
 */
public class Main {
    public static void main(String[] arg){
        Network mNetwork=new Network();
        SendRovData.setWorkMode((byte)0x02);
        Thread mSyncThread = new Thread(mNetwork);
        mSyncThread.start();
        int i=0;
        SendRovData.setVelocity((byte) (0xDD), (byte) 0x00);
        //SendRovData.setUpDown((byte) 0xDD);
        while(true);

    }
}
