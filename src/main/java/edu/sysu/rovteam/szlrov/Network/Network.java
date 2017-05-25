package edu.sysu.rovteam.szlrov.Network;

import edu.sysu.rovteam.szlrov.ProtocolData.RovInitData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Core network module
 */
public class Network implements Runnable {
    public static int Port=RovInitData.PORT_INIT;
    public static boolean ReadFlags = false;
    public static boolean SendFlags = false;
    private static Network network;
    public SendRovData sendRovData;
    private static String IP= RovInitData.ROV_IP_INIT;
    private static Socket socket;
    public static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;
    private static byte[] mBuf = new byte[114];
    public Network(){
        System.out.println("connecting");
        connect();
        network=this;
    }
    public void run() {
        while(true) {
            SendRovData.sync();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
        }
    }
    public static Network getNetwork() throws NullPointerException{
        if(network == null){
            throw new NullPointerException();
        }
        return network;
    }
    public SendRovData getSendRovData(){
        return this.sendRovData;
    }
    public byte getmBuf(int paramInt){
        return this.mBuf[paramInt];
    }
    public byte[] getmBuf(){
        return this.mBuf;
    }
    public boolean connect(){
        SocketAddress endpoint=new InetSocketAddress(IP,Port);
        try {
            System.out.print("...");
            socket = new Socket();
            socket.connect(endpoint,10000);
            dataInputStream=new DataInputStream(socket.getInputStream());
            dataOutputStream=new DataOutputStream(socket.getOutputStream());

            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error on making connection.");
            return false;
        }
    }
    public void send(byte[] paramArrayOfByte){
        if(socket==null||socket.isClosed()){
            connect();
        }
        if(socket.isConnected()){
            try {
                dataOutputStream.write(paramArrayOfByte);
                //socket.close();
            }catch (IOException iex){
                iex.printStackTrace();
                System.out.println("Error sending command!");
                connect();
            }
        }
    }

    public void setSendRovData(SendRovData sendRovData) {
        this.sendRovData = sendRovData;
    }
}
