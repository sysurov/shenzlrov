package veg.mediaplayer.sdk;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class M3U8
{
  private static final String TAG = "M3U8_Parser";
  private static DefaultHttpClient clientHTTP = null;
  private static DefaultHttpClient clientHTTPS = null;
  List<HLSStream> hls_list = new ArrayList();
  
  private int M3U8_Parse(BufferedReader paramBufferedReader)
  {
    for (;;)
    {
      try
      {
        if (paramBufferedReader.readLine().trim().toUpperCase().contains("#EXTM3U")) {
          continue;
        }
        return 0;
      }
      catch (IOException paramBufferedReader)
      {
        Object localObject1;
        Object localObject4;
        Object localObject3;
        Object localObject2;
        Pattern localPattern;
        HLSStream localHLSStream;
        int j;
        paramBufferedReader.printStackTrace();
        continue;
        localHLSStream.WIDTH = localHLSStream.RESOLUTION;
        continue;
        ((String)localObject1).startsWith("# ");
        continue;
        int i = 0;
        continue;
      }
      localObject1 = paramBufferedReader.readLine();
      if (localObject1 == null)
      {
        Collections.sort(this.hls_list, new HLSStreamComparator(1));
        return 0;
      }
      if (1 == 1)
      {
        if (!((String)localObject1).startsWith("#EXT-X-STREAM-INF")) {
          continue;
        }
        localObject4 = Pattern.compile("^#EXT-X-STREAM-INF:.*BANDWIDTH=(\\d+).*");
        localObject3 = Pattern.compile("^#EXT-X-STREAM-INF:.*RESOLUTION=([\\dx]+).*");
        localObject2 = Pattern.compile("^#EXT-X-STREAM-INF:.*PROGRAM-ID=(\\d+).*");
        localPattern = Pattern.compile("^#EXT-X-STREAM-INF:.*CODECS=\"(.*)\".*");
        localHLSStream = new HLSStream();
        localObject4 = ((Pattern)localObject4).matcher((CharSequence)localObject1);
        if (((Matcher)localObject4).find()) {
          localHLSStream.BANDWIDTH = ((Matcher)localObject4).group(1);
        }
        localObject3 = ((Pattern)localObject3).matcher((CharSequence)localObject1);
        if (((Matcher)localObject3).find())
        {
          localHLSStream.RESOLUTION = ((Matcher)localObject3).group(1);
          j = localHLSStream.RESOLUTION.indexOf("x");
          if (j == -1) {
            continue;
          }
          localHLSStream.WIDTH = localHLSStream.RESOLUTION.substring(0, j);
        }
        localObject2 = ((Pattern)localObject2).matcher((CharSequence)localObject1);
        if (((Matcher)localObject2).find()) {
          localHLSStream.ID = ((Matcher)localObject2).group(1);
        }
        localObject1 = localPattern.matcher((CharSequence)localObject1);
        if (((Matcher)localObject1).find()) {
          localHLSStream.CODECS = ((Matcher)localObject1).group(1);
        }
        localObject1 = paramBufferedReader.readLine();
        if (localObject1 != null)
        {
          localHLSStream.URL = ((String)localObject1);
          localHLSStream.ext_stream = i;
          i += 1;
          this.hls_list.add(localHLSStream);
        }
      }
    }
  }
  
  public static <T> AsyncTask<T, ?, ?> executeAsyncTask(AsyncTask<T, ?, ?> paramAsyncTask, T... paramVarArgs)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      return paramAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramVarArgs);
    }
    return paramAsyncTask.execute(paramVarArgs);
  }
  
  public static DefaultHttpClient getThreadSafeClient(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (;;)
    {
      SchemeRegistry localSchemeRegistry;
      try
      {
        if (clientHTTPS == null)
        {
          localObject1 = new BasicHttpParams();
          localSchemeRegistry = new SchemeRegistry();
          SSLSocketFactory localSSLSocketFactory = SSLSocketFactory.getSocketFactory();
          localSSLSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
          localSchemeRegistry.register(new Scheme("https", localSSLSocketFactory, 443));
          clientHTTPS = new DefaultHttpClient(new ThreadSafeClientConnManager((HttpParams)localObject1, localSchemeRegistry), (HttpParams)localObject1);
        }
        HttpConnectionParams.setSoTimeout(clientHTTPS.getParams(), 30000);
        HttpConnectionParams.setConnectionTimeout(clientHTTPS.getParams(), 30000);
        HttpConnectionParams.setSocketBufferSize(clientHTTPS.getParams(), 524288);
        localObject1 = clientHTTPS;
        return (DefaultHttpClient)localObject1;
      }
      finally {}
      if (clientHTTP == null)
      {
        localObject1 = new BasicHttpParams();
        localSchemeRegistry = new SchemeRegistry();
        localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        clientHTTP = new DefaultHttpClient(new ThreadSafeClientConnManager((HttpParams)localObject1, localSchemeRegistry), (HttpParams)localObject1);
      }
      HttpConnectionParams.setSoTimeout(clientHTTP.getParams(), 30000);
      HttpConnectionParams.setConnectionTimeout(clientHTTP.getParams(), 30000);
      HttpConnectionParams.setSocketBufferSize(clientHTTP.getParams(), 524288);
      Object localObject1 = clientHTTP;
    }
  }
  
  public List<HLSStream> getChannelList()
  {
    return this.hls_list;
  }
  
  public String getDataAndParse(String paramString)
  {
    try
    {
      paramString = (String)new DownloadDataTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[] { paramString }).get();
      if (paramString != null)
      {
        boolean bool = paramString.isEmpty();
        if (!bool) {}
      }
      else
      {
        paramString = "";
      }
      return paramString;
    }
    catch (InterruptedException paramString)
    {
      paramString.printStackTrace();
      return "";
    }
    catch (ExecutionException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public int getDataSynchroAndParse(String paramString)
  {
    if (Build.VERSION.SDK_INT < 21)
    {
      if ((paramString != null) && (paramString.startsWith("https"))) {}
      for (boolean bool = true;; bool = false)
      {
        Object localObject = getThreadSafeClient(bool);
        try
        {
          paramString = ((HttpClient)localObject).execute(new HttpGet(paramString)).getEntity();
          localObject = paramString.getContent();
          paramString.getContentLength();
          M3U8_Parse(new BufferedReader(new InputStreamReader(new DataInputStream((InputStream)localObject))));
          return 0;
        }
        catch (Exception paramString)
        {
          paramString.printStackTrace();
        }
      }
    }
    return -1;
  }
  
  class DownloadDataTask
    extends AsyncTask<String, Integer, String>
  {
    DownloadDataTask() {}
    
    private int downloadData(String... paramVarArgs)
    {
      if ((paramVarArgs[0].startsWith("file://")) || (paramVarArgs[0].startsWith("/storage")))
      {
        try
        {
          paramVarArgs = new BufferedReader(new FileReader(new File(paramVarArgs[0])));
          paramVarArgs.printStackTrace();
        }
        catch (IOException paramVarArgs)
        {
          try
          {
            M3U8.this.M3U8_Parse(paramVarArgs);
            return 0;
          }
          catch (IOException paramVarArgs)
          {
            Object localObject;
            for (;;) {}
          }
          paramVarArgs = paramVarArgs;
        }
        return -1;
      }
      else
      {
        localObject = M3U8.getThreadSafeClient(paramVarArgs[0].startsWith("https"));
        try
        {
          paramVarArgs = ((HttpClient)localObject).execute(new HttpGet(paramVarArgs[0])).getEntity();
          localObject = paramVarArgs.getContent();
          paramVarArgs.getContentLength();
          paramVarArgs = new BufferedReader(new InputStreamReader(new DataInputStream((InputStream)localObject)));
          M3U8.this.M3U8_Parse(paramVarArgs);
          return 0;
        }
        catch (Exception paramVarArgs)
        {
          paramVarArgs.printStackTrace();
          return -1;
        }
      }
    }
    
    protected String doInBackground(String... paramVarArgs)
    {
      downloadData(paramVarArgs);
      return "";
    }
    
    protected void onPostExecute(String paramString) {}
    
    protected void onPreExecute() {}
    
    protected void onProgressUpdate(Integer... paramVarArgs) {}
  }
  
  public class HLSStream
  {
    public String BANDWIDTH = "";
    public String CODECS = "";
    public String ID = "";
    public String RESOLUTION = "";
    public String URL = "";
    public String WIDTH;
    public int ext_stream;
    public boolean worked = true;
    
    public HLSStream() {}
  }
  
  public static class HLSStreamComparator
    implements Comparator<M3U8.HLSStream>
  {
    int nOrder = 0;
    
    public HLSStreamComparator(int paramInt)
    {
      this.nOrder = paramInt;
    }
    
    public int compare(M3U8.HLSStream paramHLSStream1, M3U8.HLSStream paramHLSStream2)
    {
      int i = 1;
      try
      {
        int j = Integer.parseInt(paramHLSStream1.BANDWIDTH);
        int k = Integer.parseInt(paramHLSStream2.BANDWIDTH);
        if (j - k > 0) {
          i = -1;
        }
        return i;
      }
      catch (NumberFormatException paramHLSStream1)
      {
        paramHLSStream1.printStackTrace();
      }
      return 1;
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/veg/mediaplayer/sdk/M3U8.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */