package edu.sysu.rovteam.szlrov.Network;

import edu.sysu.rovteam.szlrov.Tools.CRC.CRC16M;
import edu.sysu.rovteam.szlrov.Tools.StringTools.StringTools;

import java.util.Map;
/**
 * Restructure of the original class
 * Fix deCompile Error
 * Remove Android stuff
 * Complete Restructure
 */



public class SendRovData
{
    private static Map<String, SendRovData> sendRovDatas;
    private int AXIS_RZ = 0;
    private int AXIS_X = 0;
    private int AXIS_Y = 0;
    private int AXIS_Z = 0;
    private int Send_No = 0;
    private String[] menu;
    private int count=0;
    private Network netWork;
    private Map<String, Byte> netWorkByte;
    public static byte[] sendBytes={(byte) 0x53, (byte) 0x54, (byte) 0x52, (byte) 0x43, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                             (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                             (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                             (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                             (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                             (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                             (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                             (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                             (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x45, (byte) 0x44,
                             (byte) 0x52, (byte) 0x43, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00};

    /*
    * 0~7: Begin Sector
    * 8~65:Main Data
    * 66~67:Sequence
    * 68~69:CRC Checksum(LOW--HIGH)
    * 70~77:End Sector
    * */
    public static void updateBytesCRC(){
        int pos=60;
        byte[] mainData = new byte[pos];
        System.arraycopy(sendBytes, 8, mainData, 0, pos);
        byte[] result = CRC16M.getSendBuf(StringTools.bytesToHexString(mainData));
        sendBytes[68]=result[result.length-1];
        sendBytes[69]=result[result.length-2];
    }
    public static void setUpDown(byte value){
        sendBytes[18]=value;
        updateBytesCRC();
        sync();
    }
    public static void setWorkMode(byte value){
        //0x02: Run mode
        sendBytes[20]=value;//工作模式
        sendBytes[21]=value;//航向启停
        //to be determined
        updateBytesCRC();
        sync();
    }
    public static void setVelocity(byte forward,byte side){
        sendBytes[14]=forward;
        sendBytes[16]=side;
        updateBytesCRC();
        sync();
    }
    public static void sync(){
        Network.getNetwork().send(sendBytes);
    }
}
