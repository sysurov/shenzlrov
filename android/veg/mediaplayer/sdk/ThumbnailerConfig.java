package veg.mediaplayer.sdk;

public class ThumbnailerConfig
{
  private static final String TAG = "MediaPlayerConfig";
  private float bogoMIPS = 0.0F;
  private int connectionNetworkProtocol = -1;
  private String connectionUrl = "";
  private int dataReceiveTimeout = 30000;
  private int numberOfCPUCores = 1;
  private int out_height = 240;
  private int out_width = 240;
  
  public ThumbnailerConfig()
  {
    resetToDefault();
  }
  
  public ThumbnailerConfig(String paramString, int paramInt1, int paramInt2, int paramInt3, float paramFloat)
  {
    this.connectionUrl = paramString;
    this.connectionNetworkProtocol = paramInt1;
    this.dataReceiveTimeout = paramInt2;
    this.numberOfCPUCores = paramInt3;
    this.bogoMIPS = paramFloat;
  }
  
  public ThumbnailerConfig(ThumbnailerConfig paramThumbnailerConfig)
  {
    this.connectionUrl = paramThumbnailerConfig.connectionUrl;
    this.connectionNetworkProtocol = paramThumbnailerConfig.connectionNetworkProtocol;
    this.dataReceiveTimeout = paramThumbnailerConfig.dataReceiveTimeout;
    this.numberOfCPUCores = paramThumbnailerConfig.numberOfCPUCores;
    this.bogoMIPS = paramThumbnailerConfig.bogoMIPS;
    this.out_width = paramThumbnailerConfig.out_width;
    this.out_height = paramThumbnailerConfig.out_height;
  }
  
  public float getBogoMIPS()
  {
    return this.bogoMIPS;
  }
  
  public int getConnectionNetworkProtocol()
  {
    return this.connectionNetworkProtocol;
  }
  
  public String getConnectionUrl()
  {
    return this.connectionUrl;
  }
  
  public int getDataReceiveTimeout()
  {
    return this.dataReceiveTimeout;
  }
  
  public int getNumberOfCPUCores()
  {
    return this.numberOfCPUCores;
  }
  
  public int getOutHeight()
  {
    return this.out_height;
  }
  
  public int getOutWidth()
  {
    return this.out_width;
  }
  
  public void resetToDefault()
  {
    this.connectionUrl = "";
    this.connectionNetworkProtocol = -1;
    this.dataReceiveTimeout = 30000;
    this.numberOfCPUCores = 1;
    this.bogoMIPS = 0.0F;
    this.out_width = 240;
    this.out_height = 240;
  }
  
  public void setBogoMIPS(float paramFloat)
  {
    this.bogoMIPS = paramFloat;
  }
  
  public void setConnectionNetworkProtocol(int paramInt)
  {
    this.connectionNetworkProtocol = paramInt;
  }
  
  public void setConnectionUrl(String paramString)
  {
    this.connectionUrl = paramString;
  }
  
  public void setDataReceiveTimeout(int paramInt)
  {
    this.dataReceiveTimeout = paramInt;
  }
  
  public void setNumberOfCPUCores(int paramInt)
  {
    this.numberOfCPUCores = paramInt;
  }
  
  public void setOutHeight(int paramInt)
  {
    this.out_height = paramInt;
  }
  
  public void setOutWidth(int paramInt)
  {
    this.out_width = paramInt;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/veg/mediaplayer/sdk/ThumbnailerConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */