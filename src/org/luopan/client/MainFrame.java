package org.luopan.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import org.luopan.client.util.ContextManager;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = -2295538706810864538L;

    public static void main(String[] args) {
        setUIFont(new FontUIResource("宋体", Font.PLAIN, 12));
        final MainFrame frame = new MainFrame();
        final MainPanel panel = new MainPanel();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setSize(750, 500);
        Image image = Toolkit.getDefaultToolkit().getImage(frame.getClass().getResource("/resources/image/lock.png"));
        frame.setIconImage(image);
        frame.setTitle("注册机");
        ContextManager.locateOnMainScreenCenter(frame);
        frame.setVisible(true);
    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
        UIManager.put("ComboBox.background", new ColorUIResource(Color.WHITE));
        UIManager.put("ComboBox.foreground", new ColorUIResource(Color.BLACK));
        UIManager.put("ComboBox.disabledForeground", new ColorUIResource(Color.BLACK));
        UIManager.put("ComboBox.disabledBackground", new ColorUIResource(Color.WHITE));
        UIManager.put("ToolBar.separatorSize", new DimensionUIResource(30, 12));
    }
}
