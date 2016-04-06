
public class Feld<T> {
	
	private T inhalt;
	
	public Feld(T inhalt){
		this.inhalt = inhalt;
	}
	
	public T getInhalt(){
		try{
			return inhalt;
		} catch(Exception e){
			throw new NullPointerException("Kein Inhalt");
		}
	}
}
