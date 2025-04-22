import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

class Canvas extends JComponent {
    private BufferedImage image;
    private double zoom;

    public Canvas(int width, int height, int type) {
        image = new BufferedImage(width, height, type);
        setZoom(1.0);
    }

    public void setZoom(double z) {
        zoom = z;
        repaint();
    }

    public void drawCheckerboard(Graphics2D g, int size, int x, int y, int width, int height) {
        g.setColor(new Color(220, 220, 220));

        for (int i = 0; i < (int) Math.ceil((double) (x + width) / size); i++) {
            for (int j = 0; j < (int) Math.ceil((double) (y + height) / size); j++) {
                if (j % 2 != 0) {
                    if (i % 2 == 0) {
                        continue;
                    }
                } else {
                    if (i % 2 != 0) {
                        continue;
                    }
                }

                g.fillRect((i * size) + x, (j * size) + y, size, size);
            }
        }
    }

    public void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;

        int width = (int) (image.getWidth() * zoom);
        int height = (int) (image.getHeight() * zoom);

        int centerX = (getWidth() / 2) - (width / 2);
        int centerY = (getHeight() / 2) - (height / 2);

        Rectangle2D rect = new Rectangle2D.Float();
        rect.setFrame(centerX, centerY, width, height);
        g.setClip(rect);

        g.setColor(Color.WHITE);
        g.fillRect(centerX, centerY, width, height);
        drawCheckerboard(g, 10, centerX, centerY, width, height);

        g.drawImage(image.getScaledInstance(width, height, BufferedImage.SCALE_FAST), centerX, centerY, null);
    }
}