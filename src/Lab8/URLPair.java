package Lab8;

public class URLPair
{
    private String address;
    private int depth;


    public URLPair(String address, int depth)
    {
        this.address = address;
        this.depth = depth;
    }

    public int getDepth()
    {
        return depth;
    }

    public String getURL()
    {
        return address;
    }

    @Override
    public String toString()
    {
        return "URL: " + this.address + "\tDepth: " + this.depth;
    }
}
