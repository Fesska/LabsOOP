package Lab45;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class FractalExplorer
{
    private int displaySize; // размер отображения
    private JImageDisplay imageDisplay; // ссылка для обновления отображения в процессе вычисления
    private FractalGenerator fractalGenerator;  // используемый для отображения фрактал
    private Rectangle2D.Double range;   // диапазон комплексной плоскости для вывода
    private JComboBox comboBox;

    public static void main(String[] args)
    {
        FractalExplorer explorer = new FractalExplorer(800);    // создаем наш объект для рисования фракталов

        explorer.createAndShowGUI();    // отрисовываем интерфейс
        explorer.drawFractal();     // рисуем фрактал
    }

    public FractalExplorer(int displaySize)
    {
        this.displaySize = displaySize;     // устанавливаем размер отображения
        this.fractalGenerator = new Mandelbrot();   // выбираем, какой фрактал будем рисовать
        this.range = new Rectangle2D.Double(0,0,0,0);   // устанавливаем диапазон для вывода
        fractalGenerator.getInitialRange(this.range);
    }

    public void createAndShowGUI()
    {
        JFrame frame = new JFrame("Fractal Generator");     // создаем окно
        JButton buttonReset = new JButton("Reset");      // кнопка для сброса
        JButton buttonSave = new JButton("Save");
        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JLabel label = new JLabel("Fractal:");

        imageDisplay = new JImageDisplay(displaySize, displaySize);     // объявляем изображение
        imageDisplay.addMouseListener(new MouseListener());     // создаем обработчик для мыши

        // выпадающий список
        comboBox = new JComboBox();
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
        comboBox.addActionListener(new ActionHandler());

        // обработчик для кнопки сброса
        buttonReset.setActionCommand("Reset");
        buttonReset.addActionListener(new ActionHandler());

        // обработчик для кнопки сохранить
        buttonSave.setActionCommand("Save");
        buttonSave.addActionListener(new ActionHandler());

        // добавляем кнопки и комбобокс на панельки
        panelNorth.add(label, BorderLayout.CENTER);
        panelNorth.add(comboBox, BorderLayout.CENTER);
        panelSouth.add(buttonReset, BorderLayout.CENTER);
        panelSouth.add(buttonSave, BorderLayout.CENTER);

        frame.setLayout(new BorderLayout());
        frame.add(imageDisplay, BorderLayout.CENTER);
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelSouth, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal()
    {
        for (int x = 0; x < displaySize; x++)
        {
            for (int y = 0; y < displaySize; y++)
            {
                int counter = fractalGenerator.numIterations(FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x),
                                                            fractalGenerator.getCoord(range.y, range.y + range.width, displaySize, y));
                if (counter == -1) {
                    imageDisplay.drawPixel(x, y, 0);
                }
                else {
                    float hue = 0.7f + (float) counter / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    imageDisplay.drawPixel(x, y, rgbColor);
                }
            }
        }
        imageDisplay.repaint();
    }

    public class ActionHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Reset")) {
                // перерисовка фрактала
                fractalGenerator.getInitialRange(range);
                drawFractal();
            } else if (e.getActionCommand().equals("Save")) {
                // сохранение
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("PNG Images", "png");
                fileChooser.setFileFilter(fileFilter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                int t = fileChooser.showSaveDialog(imageDisplay);
                if (t == JFileChooser.APPROVE_OPTION) {
                    try {
                        ImageIO.write(imageDisplay.getImage(), "png", fileChooser.getSelectedFile());
                    } catch (NullPointerException | IOException ee) {
                        JOptionPane.showMessageDialog(imageDisplay, ee.getMessage(), "Cannot save image", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                fractalGenerator = (FractalGenerator) comboBox.getSelectedItem();
                range = new Rectangle2D.Double(0,0,0,0);
                fractalGenerator.getInitialRange(range);
                drawFractal();
            }
        }
    }

    public class MouseListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)      // обработчик события клика по изображению
        {
            double x = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, e.getX());
            double y = FractalGenerator.getCoord(range.y, range.y + range.width, displaySize, e.getY());
            fractalGenerator.recenterAndZoomRange(range, x, y, 0.5);
            drawFractal();
        }
    }
}
