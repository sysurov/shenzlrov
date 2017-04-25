package com.tools.Permissions;

public class Jurisdictions
{
  private String JurisdictionName;
  private String Tag;
  
  public Jurisdictions(String paramString1, String paramString2)
  {
    this.JurisdictionName = paramString1;
    this.Tag = paramString2;
  }
  
  public String getJurisdictionName()
  {
    return this.JurisdictionName;
  }
  
  public String getTag()
  {
    return this.Tag;
  }
  
  public void setJurisdictionName(String paramString)
  {
    this.JurisdictionName = paramString;
  }
  
  public void setTag(String paramString)
  {
    this.Tag = paramString;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/Permissions/Jurisdictions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */