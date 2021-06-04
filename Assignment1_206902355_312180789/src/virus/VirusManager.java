/**
 * @author Bar Sela            206902355
 * @author Betsalel Koginsky   312180789
 */
package virus;

import java.util.Random;
/*
 * VirusManager class
 */
public class VirusManager 
{
	private static boolean[][] mutationTable;
	static {
		/**
		 * create the mutation table
		 */
		mutationTable=new boolean[Viruses.values().length][];
		for(int i=0;i<mutationTable.length;i++)
		{
			mutationTable[i]=new boolean[Viruses.values().length];
			for(int j=0;j<mutationTable.length;j++)
			{
				if(i!=j)
					mutationTable[i][j]=false;
				else
					mutationTable[i][j]=true;
			}
		}
	}
	public static void toogle(int i, int j)
	{
		/**
		 * change the value in the table
		 * @param i,j index in the table
		 */
		mutationTable[i][j]=!mutationTable[i][j];
	}
	
	public static IVirus contagion(IVirus src) 
	{
		/**
		 * get the virus that will contaige the person
		 * @param src is the virus
		 * @return the virus
		 */
		int index = Viruses.findv(src);
		if(index==-1)
			return null;
		IVirus v=randMutation(mutationTable[index]);
		return v;
	}
	public static IVirus randMutation(boolean[] mutationTable)
	{
		/**
		 * get a random virus
		 * @param mutationTable the table with the mutation
		 * @return the mutatoin
		 */
		int size=0;
		int[] indexes=null;
		for(int i=0;i<mutationTable.length;i++)
		{
			if(mutationTable[i])
			{
				size++;
			}
		}
		indexes=new int[size];
		int j=0;
		for(int i=0;i<size;i++)
		{
			if(mutationTable[i])
			{		
				indexes[j]=i;
				j++;
			}
		}
		Random rand=new Random();
		int x=rand.nextInt(size);
		
		return Viruses.values()[indexes[x]].getv();	
	}
	public static boolean getValue(int i, int j)
	{
		/**
		 * get the value true or false 
		 * @param i,j are indexs in the table
		 * @return the value in the mutation table
		 */
		return mutationTable[i][j];
	}
}
