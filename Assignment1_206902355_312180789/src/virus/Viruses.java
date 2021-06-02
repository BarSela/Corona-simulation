package virus;


public enum Viruses {
	BritishVariant("BritishVariant", new BritishVariant()), ChineseVariant("ChineseVariant", new ChineseVariant()),
	SouthAfricanVariant("SouthAfricanVariant", new SouthAfricanVariant());

	private final String string;
	private final IVirus v;

	Viruses(String string, IVirus v) {
		this.string = string;
		this.v = v;

	}
	public static int findv(IVirus v) {
		for (int i = 0; i < Viruses.values().length; i++) {
			if (Viruses.values()[i].v.equals(v))
				return i;

		}
		return -1;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return string;
	}
	
	public IVirus getv()
	{
		return v;
	}
}
