package ui;

import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import country.City;
import country.Kibbutz;
import virus.BritishVariant;
import virus.ChineseVariant;
import virus.IVirus;
import virus.SouthAfricanVariant;

public class MutationTable extends JDialog
{

    private static class MutationModel extends AbstractTableModel {
        private IVirus[] data;
        private final String[] columnNames ={"British Mutation","Chinese Mutation","SouthAfrican Mutation"};

        public MutationModel(IVirus[] data) {
            this.data = data;
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
           IVirus v= data[rowIndex];
           if(v instanceof BritishVariant)
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
           if(v instanceof ChineseVariant)
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
           if(v instanceof SouthAfricanVariant)
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
        public void setValueAt(Object aValue, int row, int col) {
        	IVirus v=data[row];
        	boolean bl=(Boolean) aValue;
            if(v instanceof BritishVariant)
            {
         	   if(bl)
         	   {
         		   BritishVariant.addMutation(v);
         	   }
         	   else
         	   {
         		  BritishVariant.removeMutation(v);
         	   }
         	   

            }
            if(v instanceof ChineseVariant)
            {
          	   if(bl)
          	   {
          		 ChineseVariant.addMutation(v);
          	   }
          	   else
          	   {
          		 ChineseVariant.removeMutation(v);
          	   }

            }
            if(v instanceof SouthAfricanVariant)
            {
           	   if(bl)
           	   {
           		SouthAfricanVariant.addMutation(v);
           	   }
           	   else
           	   {
           		SouthAfricanVariant.removeMutation(v);
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
