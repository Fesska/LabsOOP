package Lab8;

import java.util.HashMap;
import java.util.LinkedList;

public class URLPool
{
    private HashMap<String, URLPair> visited;
    private LinkedList<URLPair> toVisit;

    public URLPool()
    {
        visited = new HashMap<>();
        toVisit = new LinkedList<>();
    }

    // Если метод помечен как synchronised то вне зависимости
    // от того, какой из объектов класса URLPool захочет
    // использовать метод getLink(), он может быть одновременно занят
    // ТОЛЬКО ОДНИМ потоком

    public synchronized URLPair getLink()
    {
        boolean isWaiting = false;
        if (toVisit.size() == 0)
        {
            try
            {
                Crawler.threadsWaiting++;
                isWaiting = true;
                // Функция дорабатывает до сюда и ждет this.notify от addLink, занятой другим потоком
                this.wait();
            }
            catch (Exception e)
            {
                return null;
            }
        }
        if (isWaiting) Crawler.threadsWaiting--;    // Уменьшаем кол-во ожидающих потоков
        URLPair urlPair = toVisit.pop();
        visited.put(urlPair.getURL(), urlPair);
        return urlPair;
    }

    public synchronized void addLink(URLPair urlPair)
    {
        if (!visited.containsKey(urlPair.getURL()))
        {
            toVisit.add(urlPair);
            this.notify();      // Оповещаем getLink, что появилась новая запись в linkedList
        }
    }
}
