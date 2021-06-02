package virus;

import java.util.Random;

public class VirusManager 
{
	private static boolean[][] data;
	static {
		data=new boolean[Viruses.values().length][];
		for(int i=0;i<data.length;i++)
		{
			data[i]=new boolean[Viruses.values().length];
			for(int j=0;j<data.length;j++)
			{
				if(i!=j)
					data[i][j]=false;
				else
					data[i][j]=true;
			}
		}
	}
	public static void toogle(int i, int j)
	{
		data[i][j]=!data[i][j];
	}
	
	public static IVirus contagion(IVirus src) 
	{
		int index = Viruses.findv(src);
		if(index==-1)
			return null;
		IVirus v=findmutation(data[index]);
		return v;
	}
	public static IVirus findmutation(boolean[] data)
	{
		int size=0;
		int[] indexes=null;
		for(int i=0;i<data.length;i++)
		{
			if(data[i])
			{
				size++;
			}
		}
		indexes=new int[size];
		int j=0;
		for(int i=0;i<size;i++)
		{
			if(data[i])
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
		return data[i][j];
	}
}
