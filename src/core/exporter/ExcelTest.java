package core.exporter;

import java.awt.event.*;

import javax.swing.*;
import java.io.*;
import javax.swing.table.*;


public class ExcelTest {

 /**
  * @param args
  */
 public static void main(String[] args) {
//  An array of book titles and their associated ISBN numbers
  String [][] data = {
    {"Summerall","0785214925"},
    {"The Secret Message of Jesus","084990000X"},
    {"Buck Wild","159555064X"},
    {"25 Ways to Win with People","0785260943"},
    {"Aesop and the CEO ","0785260102"},
    {"ALL Business is Show Business ","0785206086"},
    {"Becoming A Person of Influence","0785271007"},
    {"Checklist for Life for Leaders","0785260013"},
    {"Duct Tape Marketing ","078522100X"},
    {"38 Values to Live By ","0849916631"},
    {"Blue Moon","0785260641"},
    {"Blue Like Jazz ","9780785263708"},
    {"Wild at Heart ","0785262989"},
    {"Wild Men, Wild Alaska ","078521772X "},
    {"The Duct Tape Bible, NCV","0718018249"}
  };
  String [] headers = {"Title","ISBN"};
  final JFrame frame = new JFrame("JTable to Excel Hack");
  DefaultTableModel model = new DefaultTableModel(data,headers);
  final JTable table = new JTable(model);
  JScrollPane scroll = new JScrollPane(table);

//  my JPopupMenu component
  final JPopupMenu popup = new JPopupMenu();

//  the save JMenuItem and its associated ActionListener
  JMenuItem save = new JMenuItem("save to file");
  save.addActionListener(new
    ActionListener() {
   public void actionPerformed(ActionEvent action){
    try {
     ExcelExporter exp = new ExcelExporter();
     exp.exportTable(table, new File("results.xls"));
    }
    catch (IOException ex) {
     System.out.println(ex.getMessage());
     ex.printStackTrace();
    }
   }
  });
  popup.add(save);

//  The open JMenuItem and its associated ActionListener
  JMenuItem open = new JMenuItem("open in Excel");
  open.addActionListener(new
    ActionListener() {
   public void actionPerformed(ActionEvent action){
    try {
//     Note that i'm actually saving the file first
     ExcelExporter exp = new ExcelExporter();
     File file = new File("results1.xls");
     exp.exportTable(table, file);
     ExcelOpener opn = new ExcelOpener();
     opn.openTable(file);
    }
    catch (IOException ex) {
     System.out.println(ex.getMessage());
     ex.printStackTrace();
    }

   }
  });
  popup.add(open);

//  the following method only works in JDK 5.0 or greater
//  table.setComponentPopupMenu(popup);

  JLabel label1 = new JLabel("Right Click to Export Data...", JLabel.CENTER);

//  the following code is needed for JDK 1.4
  table.addMouseListener(new MouseAdapter() {
   public void mousePressed(MouseEvent event){
    if(popup.isPopupTrigger(event)){
     popup.show(event.getComponent(), event.getX(),event.getY());
    }
   }
   public void mouseReleased(MouseEvent event){
    if(popup.isPopupTrigger(event)){
     popup.show(event.getComponent(), event.getX(),event.getY());
    }
   }
  });

  frame.getContentPane().add("Center",scroll);
  frame.getContentPane().add("South",label1);
  frame.pack();
  frame.setVisible(true);
 }
}

