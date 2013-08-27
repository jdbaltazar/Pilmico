package gui.forms.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class ViewFormLabel extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6498600351131412302L;
	private String label;
	
	public ViewFormLabel(String label){
//		super("<HTML><U>"+label+"<U><HTML>");
		super(label);
//		setFont(new Font("FangSong", Font.PLAIN, 14));
		setHorizontalAlignment(JLabel.RIGHT);
		setFont(new Font("Consolas", Font.PLAIN, 11));
//		setForeground(Color.decode("#000038"));
		setForeground(Color.decode("#006600"));
		setOpaque(false);
		
		
		/*original = getFont();
		Map attributes = original.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		
		setFont(original.deriveFont(attributes));*/
		
	}
	
	public ViewFormLabel(String label, boolean remarks){
		super(label);
		this.label = label;
		
		initRemarks();
	}
	
	private void initRemarks(){
		setFont(new Font("Segoe Print", Font.ITALIC, 12));
		setHorizontalAlignment(JLabel.RIGHT);
		setForeground(Color.RED);
		setToolTipText(label);
		
	}

}
