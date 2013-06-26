//package util.soy;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.GradientPaint;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//
//import util.SBTable;
//
//public class SoyTable{
//	
//	private JTable table;
//	private String[] header;
//	private Object[][] data;
//	private GradientPaint gradient;
//
//	public SoyTable(String[] header, Object[][] data){
//		this.header = header;
//		this.data = data;
//		init();
//	}
//
//	private void init() {
//		// TODO Auto-generated method stub
//		table = new SBTable(data, header);
//		//table.setOpaque(false);
//        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
//        table.setFillsViewportHeight(true);
//	}
//	
//	public int getColumnCount() {
//        return header.length;
//    }
//
//    public int getRowCount() {
//        return data.length;
//    }
//
//    public String getColumnName(int col) {
//        return header[col];
//    }
//
//    public Object getValueAt(int row, int col) {
//        return data[row][col];
//    }
//
//	public JTable getTable() {
//		return table;
//	}
//
//	public void setTable(JTable table) {
//		this.table = table;
//	}
//	
//}
