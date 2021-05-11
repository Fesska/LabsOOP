package Lab78;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class URLDepthPair
{
    public static final String URL_PREFIX = "http://";
    private String URL;
    private int scanDepth;

    public URLDepthPair(String URL, int scanDepth)
    {
        this.URL = URL;
        this.scanDepth = scanDepth;
    }

    public String getHost() throws MalformedURLException
    {
        URL host = new URL(URL);
        return host.getHost();
    }

    public String getPath() throws MalformedURLException
    {
        URL path = new URL(URL);
        return path.getPath();
    }

    public int getScanDepth()
    {
        return this.scanDepth;
    }

    public String getURL()
    {
        return this.URL;
    }

    public static boolean check(LinkedList<URLDepthPair> resultLink, URLDepthPair pair)
    {
        boolean isAlready = true;
        for (URLDepthPair c : resultLink)
            if (c.getURL().equals(pair.getURL()))
                isAlready=false;
        return isAlready;
    }

    @Override
    public String toString()
    {
        return "URL: " + this.URL + "\tDepth: " + scanDepth;
    }
}
