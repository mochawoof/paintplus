import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.BasicButtonUI;

class Window extends JFrame {
    private Canvas canvas;
    private JToolBar bottomBar;

    public Window() {
        setTitle("Paint+");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLaf(UIManager.getCrossPlatformLookAndFeelClassName());
        //setLaf(UIManager.getSystemLookAndFeelClassName());

        canvas = new Canvas(500, 400, BufferedImage.TYPE_INT_ARGB);
        add(canvas, BorderLayout.CENTER);

        bottomBar = new JToolBar();
        add(bottomBar, BorderLayout.PAGE_END);
            bottomBar.add(Box.createGlue());

            // Predefine zoomSlider for use in resetZoomButton
            JSlider zoomSlider = new JSlider(10, 500, 100);

            JButton resetZoomButton = new JButton("O");
            resetZoomButton.setToolTipText("Reset Zoom");
            resetZoomButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    zoomSlider.setValue(100);
                }
            });
            bottomBar.add(resetZoomButton);

            JLabel zoomLabel = new JLabel(" 100% ");
            bottomBar.add(zoomLabel);

            zoomSlider.setPreferredSize(new Dimension(100, (int) zoomSlider.getPreferredSize().getHeight()));
            zoomSlider.setMaximumSize(zoomSlider.getPreferredSize());
            zoomSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    int zoom = zoomSlider.getValue();
                    zoomLabel.setText(" " + zoom + "% ");
                    canvas.setZoom((double) zoom / 100);
                }
            });
            bottomBar.add(zoomSlider);

        setVisible(true);
    }

    public void setLaf(String laf) {
        try {
            UIManager.setLookAndFeel(laf);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}