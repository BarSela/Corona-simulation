package ui;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import virus.BritishVariant;
import virus.ChineseVariant;
import virus.IVirus;
import virus.SouthAfricanVariant;

public class MutationTable extends JDialog 
{

    private static class MutationModel extends AbstractTableModel {
        private Boolean[][] data;
        private final String[] columnNames ={"British Mutation","Chinese Mutation","SouthAfrican Mutation"};

        public MutationModel(IVirus[] data) {
        	Boolean arr[][]=new Boolean[3][3];
        	arr[0][0]=BritishVariant.getSetMutation().contains(new BritishVariant());
        	arr[0][1]=BritishVariant.getSetMutation().contains(new ChineseVariant());
        	arr[0][2]=BritishVariant.getSetMutation().contains(new SouthAfricanVariant());
        	arr[1][0]=ChineseVariant.getSetMutation().contains(new BritishVariant());
        	arr[1][1]=ChineseVariant.getSetMutation().contains(new ChineseVariant());
        	arr[1][2]=ChineseVariant.getSetMutation().contains(new SouthAfricanVariant());
        	arr[2][0]=SouthAfricanVariant.getSetMutation().contains(new BritishVariant());
        	arr[2][1]=SouthAfricanVariant.getSetMutation().contains(new ChineseVariant());
        	arr[2][2]=SouthAfricanVariant.getSetMutation().contains(new SouthAfricanVariant());
            this.data = arr;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
           if(rowIndex == 0)
           {
        	   Set<IVirus> british=BritishVariant.getSetMutation();
        	   switch (columnIndex) {
               case 0: 
            	   return british.contains(new BritishVariant());
               case 1:
   	    			return british.contains(new ChineseVariant());
               case 2: return british.contains(new SouthAfricanVariant());
        	   }
           }
           if(rowIndex == 1)
           {
        	   Set<IVirus> chinese=BritishVariant.getSetMutation();
        	   switch (columnIndex) {
               case 0: 
            	   return  chinese.contains(new BritishVariant());
               case 1:
   	    			return  chinese.contains(new ChineseVariant());
               case 2: return  chinese.contains(new SouthAfricanVariant());
        	   }
           }
           if(rowIndex == 2)
           {
        	   Set<IVirus> sa=BritishVariant.getSetMutation();
        	   switch (columnIndex) {
               case 0: 
            	   return  sa.contains(new BritishVariant());
               case 1:
   	    			return  sa.contains(new ChineseVariant());
               case 2: return  sa.contains(new SouthAfricanVariant());
        	   }
           }
           return null;
            
        }

        @Override
        public String getColumnName(int column) {
        	return columnNames[column];
        }

        @Override
        public Class getColumnClass(int column) {
        	return getValueAt(0, column).getClass(); 
        }
        @Override
        public boolean isCellEditable(int row, int col) 
        { return true; 
        }   
        @Override
        public void setValueAt(Object aValue, int row, int col) {
        	boolean bl=(Boolean) aValue;
            if(row == 0)
            {
         	   if(bl)
         	   {
         		   BritishVariant.addMutation(new BritishVariant());
         	   }
         	   else
         	   {
         		  BritishVariant.removeMutation(new BritishVariant());
         	   }  	   
            }
            if(row == 1)
            {
          	   if(bl)
          	   {
          		 ChineseVariant.addMutation(new ChineseVariant());
          	   }
          	   else
          	   {
          		 ChineseVariant.removeMutation(new ChineseVariant());
          	   }
            }
            if(row == 2)
            {
           	   if(bl)
           	   {
           		SouthAfricanVariant.addMutation(new SouthAfricanVariant());
           	   }
           	   else
           	   {
           		SouthAfricanVariant.removeMutation(new SouthAfricanVariant());
           	   }
            }
            fireTableCellUpdated(row, col);
        }
    }

    public MutationTable(JFrame window,IVirus[] data) {
        super(window, "Edit Mutation ",true);
        MutationModel model = new MutationModel(data);
        JTable table = new JTable(model);
        String row[]= {"British Variant", "Chinese Variant", "SouthAfrican Variant"};
		RowedTableScroll jt_rowed =new RowedTableScroll(table,row);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(500, 150));
        table.setFillsViewportHeight(true);
        this.add(new RowedTableScroll(table,row));

        this.pack();
    }


}
