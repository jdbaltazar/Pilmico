package util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DarkBackground extends JPanel {
   private JComponent glassPane;

   public DarkBackground() {
      setPreferredSize(new Dimension(0, 0));
   }

   public void setGlassPane(JComponent glassPane) {
      JRootPane rootpane = SwingUtilities.getRootPane(this);
      this.glassPane = glassPane; 

      rootpane.setGlassPane(glassPane);
   }

   public void setGlassPaneVisible(boolean visible) {
      glassPane.setVisible(visible);
   }
}

