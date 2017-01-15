package org.luopan.client.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ContextManager {

    private static JFrame mainFrame;
    private static JPanel upperPane;

    public static Icon getImageIconByName(String iconName) {
        return new ImageIcon(ContextManager.class.getResource("/resources/images/" + iconName));
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    public static void setMainFrame(JFrame mainFrame) {
        ContextManager.mainFrame = mainFrame;
    }

    public static void locateOnScreenCenter(Component component) {
        Dimension paneSize = component.getSize();
        Dimension screenSize = component.getToolkit().getScreenSize();
        component.setLocation(
                (screenSize.width - paneSize.width) / 2,
                (screenSize.height - paneSize.height) / 2);
    }

    public static void locateOnMainScreenCenter(Component component) {
        Dimension paneSize = component.getSize();
        Dimension screenSize = component.getToolkit().getScreenSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gds = ge.getScreenDevices();
        if (gds.length >= 1) {
            screenSize = gds[0].getDefaultConfiguration().getBounds().getSize();
        }
        component.setLocation(
                (screenSize.width - paneSize.width) / 2,
                (screenSize.height - paneSize.height) / 2);
    }

    public static JPanel getUpperPane() {
        return upperPane;
    }

    public static void setUpperPane(JPanel upperPane) {
        ContextManager.upperPane = upperPane;
    }
}
