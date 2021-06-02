/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package ui;

import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import virus.BritishVariant;
import virus.ChineseVariant;
import virus.IVirus;
import virus.SouthAfricanVariant;
import virus.VirusManager;
import virus.Viruses;

public class MutationTable extends JDialog 
{
	/**
	 * 
	 * this class represent the mutation editor dialog
	 * the user can set the Development of mutant variants
	 */

    private static class MutationModel extends AbstractTableModel 
    {
    	/**
    	 * this class provides default implementations for most of the methods in the TableModel interface
    	 */
    	
    	//data members
        private int row_length=Viruses.values().length;
        private final String[] columnNames ={"British Mutation","Chinese Mutation","SouthAfrican Mutation"};

        @Override
        public int getRowCount() 
        {
        	/**
        	 * getter to number of rows
        	 * @return the number of rows 
        	 */
            return row_length;
        }

        @Override
        public int getColumnCount() 
        {
         	/**
        	 * getter to number of columns
        	 * @return the number of columns
        	 */
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) 
        {
        	/**
        	 * get the value at cell by row and col index
        	 * @param rowIndex row index
        	 * @param columnIndex col index
        	 * return the value at the cell 
        	 */
        	return VirusManager.getValue(rowIndex, columnIndex);
            
        }

        @Override
        public String getColumnName(int column) 
        {
        	/**
        	 * get column name
        	 * @param column the number of column
        	 * @return the name of the column
        	 */
        	return columnNames[column];
        }

        @Override
        public Class getColumnClass(int column) 
        {
        	/**
        	 * @param column column index
        	 * @returm class of the value
        	 */
        	return getValueAt(0, column).getClass(); 
        }
        @Override
        public boolean isCellEditable(int row, int col) 
        { 	
        	/**
        	 * all cells is editable
        	 * @param row row index
        	 * @param col col index
        	 * @return if cell is editable-allways true
        	 */
        	return true; 
        }   
        @Override
        public void setValueAt(Object aValue, int row, int col) 
        {
        	/**
        	 * setter for cells
        	 * @param avalue new value
        	 * @param row row index
        	 * @param col column index
        	 */
        	VirusManager.toogle(row, col);
            
            fireTableCellUpdated(row, col);
        }
    }

    public MutationTable(JFrame window) 
    {
    	/**
    	 * Constructor
    	 * @param window parent window
    	 * @param data contain all the variant types 
    	 */
        super(window, "Edit Mutation ",true);
        MutationModel model = new MutationModel();
        
        //table
        JTable table = new JTable(model);
        String row[]= {"British Variant", "Chinese Variant", "SouthAfrican Variant"};
		RowedTableScroll jt_rowed =new RowedTableScroll(table,row);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(getPreferredSize()));
        table.setFillsViewportHeight(true);
        
        
        this.add(new RowedTableScroll(table,row));
        this.getPreferredSize();
        setBounds(390,170,200,300);
        this.pack();
    }
	@Override
	public Dimension getPreferredSize() 
	{
		/**
		 * selected according max x and y points
		 * @return dimention 
		 */
		return new Dimension(700,125);
	}


}
