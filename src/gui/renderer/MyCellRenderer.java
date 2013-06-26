package gui.renderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class MyCellRenderer extends JPanel implements TableCellRenderer {
	   private JLabel label = new JLabel();

	   public MyCellRenderer() {
		 
	      setOpaque(false);
	      setLayout(new BorderLayout());
	      add(label, BorderLayout.CENTER);
	      label.setHorizontalAlignment(SwingConstants.CENTER);
	      label.setForeground(Color.white);
	      label.setFont(new Font("Harabara", Font.PLAIN, 16));
	   }

	   @Override
	   public Component getTableCellRendererComponent(JTable table, Object value,
	         boolean isSelected, boolean hasFocused, int arg4, int arg5) {
	      label.setText(value.toString());
	      return this;
	   }

	   @Override
	   protected void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      Graphics2D g2 = (Graphics2D) g;
	      g2.draw3DRect(0, 0, getWidth(), getHeight(), true);
	      g2.setColor(new Color(119,136,153));
	      g2.fill3DRect(0, 0, getWidth(), getHeight(), true);
	   }
	}
