package Lab7;

public class URLDepthPair
{
    private String address;
    private int depth;


    public URLDepthPair(String address, int depth)
    {
        this.address = address;
        this.depth = depth;
    }

    public int getDepth()
    {
        return depth;
    }

    public String getUrl()
    {
        return address;
    }

    @Override
    public String toString()
    {
        return "URL: " + this.address + "\tDepth: " + this.depth;
    }
}
