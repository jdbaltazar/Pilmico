package test;

import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class FollowFocus {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {

      public void run() {
        final int ROWS = 100;
        final JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(new JLabel(
          "Thanks for helping out. Use tab to move around."));
        for (int i = 0; i < ROWS; i++) {
          JTextField field = new JTextField("" + i);
          field.setName("field#" + i);
          content.add(field);
        }

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                            .addPropertyChangeListener("focusOwner", 
                     new PropertyChangeListener() {

          @Override
          public void propertyChange(PropertyChangeEvent evt) {
            if (!(evt.getNewValue() instanceof JComponent)) {
              return;
            }
            JComponent focused = (JComponent) evt.getNewValue();
            if (content.isAncestorOf(focused)) {
              System.out.println("Scrolling to " + focused.getName());
              focused.scrollRectToVisible(focused.getBounds());
            }
          }
        });

        JFrame window = new JFrame("Follow focus");
        window.setContentPane(new JScrollPane(content));
        window.setSize(200, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
      }
    });
  }
}
