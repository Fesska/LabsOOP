package Lab7;

import java.net.*;
import java.util.LinkedList;

import java.util.*;

public class Crawler {

    private int maxDepth;
    private LinkedList<URLDepthPair> toVisit;
    private HashMap<String, URLDepthPair> visited;
    private static final String protocol = "https:";

    public static void main(String args[])
    {
        Crawler crawler = new Crawler("https://www.google.ru/", 3);
        crawler.run();
    }

    public Crawler(String address, int maxDepth)
    {
        visited = new HashMap<>();
        toVisit = new LinkedList<>();
        // Добавляем в еще не посещенные поступившиую ссылку
        toVisit.add(new URLDepthPair(address, 0));
        this.maxDepth = maxDepth;
    }

    public void run()
    {
        while (toVisit.size() > 0)
        {
            // Достаем очередную пару из списка тех, которые надо посетить
            URLDepthPair current_address = toVisit.pop();

            if (visited.containsKey(current_address.getUrl())) continue;    // Уже посетили?

            visited.put(current_address.getUrl(), current_address);     // Помечаем посещенным
            System.out.println(current_address);

            if (current_address.getDepth() < maxDepth)      // Не достигли макс. глубины
                search(current_address);        // начинаем поиск
        }
    }

    private void search(URLDepthPair current_address)
    {
        try {
            // Получаем URL
            URL url = new URL(current_address.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Устанавливаем соединение
            connection.setRequestMethod("GET");

            // Направляем на сканнер входящий поток данных
            Scanner scanner = new Scanner(connection.getInputStream());

            // Пока находим что-то похожее на ссылку
            while (scanner.findWithinHorizon("<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1", 0) != null){
                String new_url = scanner.match().group(2);
                newURLPair(new_url, current_address);       // Создаем новую пару
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getLocalizedMessage());
        }
    }

    private void newURLPair(String new_url, URLDepthPair current_address)
    {
        if (new_url.startsWith("//"))
            new_url = protocol + new_url;
        else if (!new_url.startsWith(protocol)) return;     // Если ссылка не ок, то нафиг ее

        // Создаем пару
        URLDepthPair new_pair = new URLDepthPair(new_url, current_address.getDepth() + 1);
        toVisit.add(new_pair); // Добавляем в список тех, которые надо посмотреть
    }
}
