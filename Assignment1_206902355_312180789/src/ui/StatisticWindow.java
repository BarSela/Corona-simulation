package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import IO.StatisticsFile;
import country.Map;
import country.Settlement;
import simulation.Main;
import virus.IVirus;

public class StatisticWindow extends JDialog 
{
	private Map world=null;
	private TableMVCStatistic table;
	public StatisticWindow(JFrame window,Map world,String row_name) 
	{
        super(window,"Statistics",false);
        setBounds(390,170,200,300);
        getContentPane().setLayout(new GridLayout(2, 1));
        JPanel bottom_panel=new JPanel(new GridLayout(1, 3));
    	bottom_panel.setLayout(new GridLayout(1, 3));
    	
    	//table
    	Settlement[] settlements = world.getSettlement();
        TableMVCStatistic table_model = new TableMVCStatistic(settlements,row_name);
      //bottom panel:
    	
    	JButton b_save = new JButton("Save");
    	b_save.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) 
    		{
    			StatisticsFile.writeCsv(world,Main.loadFileFunc());
    		}
    		});

    	JButton b_sick = new JButton("Add Sick");
    	b_sick.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) 
    		{
    			try 
    			{
    				table_model.setSick();

    			} 
    			catch (Exception e1) {
    				e1.printStackTrace();
    			}
    		}
    		});
    	JButton b_vaccinate= new JButton("Vaccinate");
    	SpinnerModel vaccinate_nodel=new SpinnerNumberModel(1,1,100,1);
    	JSpinner spinner = new JSpinner(vaccinate_nodel);
    	JPanel p_douses=new JPanel();
    	JButton b_dose = new JButton("Add");
    	JLabel l_dose = new JLabel("Douses:");
    	p_douses.add(l_dose);
    	p_douses.add(spinner);
    	p_douses.add(b_dose);
    	
    	b_dose.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{

    			int douses = (Integer) spinner.getValue();
    			table_model.setDouse(douses);

    		}
    	});
    	JDialog vaccinate=new JDialog(this,"Add vaccinate douses",true);
    	vaccinate.setBounds(390,170,200,300);
    	vaccinate.getContentPane().add(p_douses);
    	vaccinate.pack();
    	b_vaccinate.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			vaccinate.setVisible(true);
    		}
    	});
    	bottom_panel.add(b_save);
    	bottom_panel.add(b_sick);
    	bottom_panel.add(b_vaccinate);
    	
    	getContentPane().add(table_model);
    	getContentPane().add(bottom_panel);
    	
    	pack();
	}
	public JTable getTableFromDialog()
    {
    	return table.getTableFromPanel();
    }

	       
	
	
	
	
}
