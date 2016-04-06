
public class FeldValue {
	public String name;
	public int wert;
	public boolean status;
	
	public FeldValue(String name, int wert, boolean status){
		this.name = name;
		this.wert = wert;
		this.status = status;
	}
	

	public String getName(){
		return name;
	}
	
	public int getWert(){
		return wert;
	}
	
	public boolean getStatus(){
		return status;
	}
}
