package monopoly.local.valueobjects;

//public class Feld<T> {
//	
//	private T inhalt;
//	
//	public Feld(T inhalt){
//		this.inhalt = inhalt;
//	}
//	
//	public T getInhalt(){
//		try{
//			return inhalt;
//		} catch(Exception e){
//			throw new NullPointerException("Kein Inhalt");
//		}
//	}
//}

public class Feld {
	
	protected String name;
	protected int nummer;

	public Feld(String name, int nr) {
		super();
		this.name = name;
		this.nummer = nr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	public boolean equals(Feld feld){
		if(feld.getName().equals(this.name)){
			if(feld.getNummer() == this.nummer){
				return true;
			}
		}
		return false;
	}
}