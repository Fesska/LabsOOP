package Lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent
{
    private BufferedImage image;

    // объявление изображения и его параметров
    public JImageDisplay(int width, int height)
    {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension dimension = new Dimension(width, height);
        super.setPreferredSize(dimension);
    }

    // метод отрисовки
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    // установка пикселя в определенный цвет
    public void drawPixel(int x, int y, int rgbColor)
    {
        image.setRGB(x, y, rgbColor);
    }

    // очистка изображения (устанавливает все пиксели изображение в черный цвет)
    public void clearImage()
    {
        for (int i = 0; i < image.getWidth(); i++)
        {
            for (int j = 0; j < image.getHeight(); j++)
            {
                drawPixel(i, j, 0);
            }
        }
    }

    public BufferedImage getImage()
    {
        return image;
    }
}
