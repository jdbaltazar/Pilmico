package util.soy;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class FlatButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1271600327683151876L;
	private String name="";
	
	public FlatButton(String name){
		super(name);
		this.name = name;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		setName(name);
		
		setFont(new Font("TimeBurner", Font.BOLD, 14));
		setForeground(Color.white);
		setBackground(Color.BLUE);
		setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE.darker()));
		//setBorder(BorderFactory.createEmptyBorder());
		//setFocusable(false);
		setHorizontalAlignment(SwingConstants.RIGHT);
		//setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		
		/*addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.CYAN));
			}
		});*/
		
		addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				setFont(new Font("TimeBurner", Font.BOLD, 14));
				setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE.darker()));
				setForeground(Color.white);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				setFont(new Font("TimeBurner", Font.ITALIC, 14));
						setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.CYAN));
						setForeground(Color.CYAN);
					
			}
		});
	}

}
