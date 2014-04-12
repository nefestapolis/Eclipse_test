
import java.awt.Color;
import java.awt.Label;

public class Recepcioner extends Osoba{

	private Gost g;
	private Recepcija rec;
	private Soba soba;
	
	
	private Label prikaz=new Label("Rec");
	private double ukupnaCena=0;
	private Hotel hot;
	
	public Recepcioner(Recepcija r,Hotel h){
		rec=r;
		hot=h;
		prikaz.setBackground(Color.BLUE);
		soba=new Soba();
	}
		
	public synchronized void Odmor(boolean odm){
		if(odm)
		{
			zaustavi();
			prikaz.setBackground(Color.RED);
		}
		else{
			nastavi();
			prikaz.setBackground(Color.BLUE);
		}
		 notifyAll();
	}
	
	public double naplati(){
		return ukupnaCena;
	}
	
	public Label vratiPrikaz(){
		return prikaz;
	}
	
	public void prikaz(){
		 prikaz.setText(toString());
	}
	

	public void radnja() {
		try {

			g = rec.uzmiGosta();
			
			if (g != null && g.getStanje().equals(">")) {
				if((soba = hot.dajSobu())!= null)
				{
					soba.smesti(g);
					g.setStanje("@");
					prikaz();
				}
				else{
					g.prekini();g=null;
				}
			} else if (g != null && g.getStanje().equals("<")) {
				ukupnaCena += g.ukupnaCena();
				g.napolje();
				prikaz();
				g.prekini();
				g = null;
				
			}
			sleep((long)rec.getOpsluzujeVer());
		} catch (InterruptedException e) {}
	}
	
	public synchronized String toString(){
		if(g!=null)
			return "RecID:"+dohvatiId()+"/"+g.toString()+"/"+g.ukupnaCena();
		else
			return "RecID:"+dohvatiId()+"";
	}

}

