package gui.forms.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ISRowPanel extends JPanel{
	
	private String[] moneyString= {"1000", "500", "200", "100", "50", "20", "coins"};
	private JTextField field;
	private JPanel row;
	private JPanel panel = new JPanel();
	private JLabel label, totalLabel;
	private int ROW_WIDTH = 580, ROW_HEIGHT = 30, y, table = 0, componentCount;
	
	public ISRowPanel(JPanel panel, int table){
		
		this.panel = panel;
		this.table = table;
		
		init();
		addComponents();
	}
	
	private void init(){
		y = panel.getComponentCount() * ROW_HEIGHT;
		
		setLayout(new BorderLayout());
		
		row = new JPanel();
		row.setLayout(null);
		row.setOpaque(true);
		row.setBackground(Color.decode("#CCFFB2"));
		row.setBorder(BorderFactory.createEtchedBorder());
		
		field = new JTextField();
		field.setHorizontalAlignment(JTextField.CENTER);
		
		totalLabel = new JLabel();
		totalLabel.setHorizontalAlignment(JLabel.CENTER);
		totalLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
	}
	
	private void addComponents(){
		
		JLabel money, x, eq;
		componentCount = panel.getComponentCount();
		//money.setText(moneyString[panel.getComponentCount()]);
		//money.setHorizontalAlignment(JLabel.CENTER);
		money = getALabel(moneyString[panel.getComponentCount()]);
		x = getALabel("x");
		eq = getALabel("=");
		
		//sum = Integer.parseInt(field.getText()) *  Integer.parseInt(moneyString[panel.getComponentCount()]);
		
		money.setBounds(2, 7, 50, 15);
		x.setBounds(60, 5, 20, 20);
		field.setBounds(100, 7, 100, 17);
		field.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {

				if (!field.getText().equals("")){
					if(componentCount != moneyString.length-1)
						totalLabel.setText(""	+ Integer.parseInt(field.getText()) *  Integer.parseInt(moneyString[componentCount]));
					else
						totalLabel.setText(field.getText());
				}
				else
					totalLabel.setText("");
			}

		});
		eq.setBounds(220, 5, 20, 20);
		totalLabel.setBounds(240, 7, 100, 17);
		
		if(componentCount != moneyString.length-1)
			row.add(x);
		
		row.add(eq);
		row.add(money);
		row.add(field);
		row.add(totalLabel);
		
		add(row);
		
		setBounds(0, y, panel.getWidth(), ROW_HEIGHT);
	}
	
	private JLabel getALabel(String str){
		JLabel label = new JLabel(str);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		
		return label;
	}

}
