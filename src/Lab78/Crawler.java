package Lab78;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.Scanner;

public class Crawler
{
    static LinkedList <URLDepthPair> findLink = new LinkedList <URLDepthPair>();
    static LinkedList <URLDepthPair> viewedLink = new LinkedList <URLDepthPair>();


    public static void main(String[] args)
    {
        System.out.println("Введите (через пробел) URL адрес и макс. глубину поиска: ");
        Scanner input = new Scanner(System.in);

        String [] line = input.nextLine().split(" ");
        try {
            startScan(line[0], Integer.parseInt(line[1]));
        } catch (NumberFormatException | IOException e) {
            System.out.println("usage: java crawler " + line[0] + " " + line[1]);
        }
    }

    public static void getSites(LinkedList<URLDepthPair> viewedLink)
    {
        for (URLDepthPair pair: viewedLink)
        {
            System.out.println(pair.toString());
        }
    }

    public static void startScan(String pair, int maxDepth) throws IOException
    {
        findLink.add(new URLDepthPair(pair, 0));

        while (!findLink.isEmpty())
        {
            URLDepthPair currentPair = findLink.removeFirst();
            if (currentPair.getScanDepth() < maxDepth)
            {
                Socket mySocket = new Socket(currentPair.getHost(), 80);
                mySocket.setSoTimeout(1000);

                try
                {
                    BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
                    PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
                    request(out, currentPair);
                    String line;

                    while ((line = in.readLine()) != null)
                    {
                        if (line.indexOf(currentPair.URL_PREFIX) != -1 && line.indexOf('"') != -1)
                        {
                            StringBuilder currentLink = new StringBuilder();
                            int i = line.indexOf(currentPair.URL_PREFIX);

                            while (line.charAt(i) != '"' && line.charAt(i) != ' ')
                            {
                                if (line.charAt(i) == '<') {
                                    currentLink.deleteCharAt(currentLink.length() - 1);
                                    break;
                                } else {
                                    currentLink.append(line.charAt(i));
                                    i++;
                                }
                            }

                            URLDepthPair newPair = new URLDepthPair(currentLink.toString(), currentPair.getScanDepth() + 1);
                            if (currentPair.check(findLink, newPair) && currentPair.check(viewedLink, newPair) && !currentPair.getURL().equals(newPair.getURL()))
                                findLink.add(newPair);
                        }
                    }
                    mySocket.close();
                } catch (SocketTimeoutException e) {
                    mySocket.close();
                }

            }
            viewedLink.add(currentPair);
        }
        getSites(viewedLink);
    }

    public static void request(PrintWriter out,URLDepthPair pair) throws MalformedURLException
    {
        out.println("GET " + pair.getPath() + " HTTP/1.1");
        out.println("Host: " + pair.getHost());
        out.println("Connection: close");
        out.println();
        out.flush();
    }
}
