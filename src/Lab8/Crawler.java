package Lab8;


public class Crawler
{
    private String URL;
    private static int maxDepth;
    public static int threadsWaiting = 0;       // Кол-во ожидающих потоков
    public static int URLCount = 0;

    public static void main(String args[])
    {
        Crawler crawler = new Crawler("https://google.ru/", 2);
        crawler.run();
        // Когда из какого-либо потока произойдет вызов printURLCount у нас останавливается выполнение программы
        Runtime.getRuntime().addShutdownHook(new Thread(Crawler::printURLCount));
    }

    public Crawler(String address, int maxDepth)
    {
        this.URL = address;
        this.maxDepth = maxDepth;
    }

    public void run()
    {
        CrawlerTask task = new CrawlerTask(new URLPair(URL, 0));
        task.start();       // Запускаем новый поток
    }

    public static int getMaxDepth()
    {
        return maxDepth;
    }

    private static void printURLCount()
    {
        System.out.println("Final URL count: " + URLCount);
    }
}