import java.awt.List;



public class Soba implements Obavestiv{

	private static int podId=0;
	private int id=++podId;
	private int brDana=0;
	private boolean puna=false;
	private Gost gost;
	private static List lst=new List(10);
	
	public static List vratiSobu(){
		return lst;
	}
	
	public boolean jelPuna(){
		return puna;
	}
			
	public synchronized void smesti(Gost g){
		gost=g;
		puna=true;
		g.setStanje("@");
		lst.add(toString());
	}
	
	public void isprazniSobu(){
		gost.setStanje("<");
		lst.remove(toString());
		gost=null;
		puna=false;

	}

	public synchronized void poceoDan() {
		if(gost!=null)
			brDana++;
		System.out.println(brDana);
	}
	
	
	
	public String toString(){
		if(gost != null){
			return id+"/"+gost.toString()+"/Cena/"+gost.ukupnaCena()+"/zauzeta "+brDana+" dana/";
		}
		else{
			return id+"/zauzeta "+brDana+" dana/";
		}
	}

}
