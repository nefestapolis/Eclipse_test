
import java.awt.Label;

public class Ulaz extends Thread{
	
	private int minDolazi,maxDolazi;
	private static int minBoravi,maxBoravi;
	private static double minNapusta,maxNapusta;
	private Label prikaz;
	private Gost g;
	private Recepcija rec;
	
	
	public Ulaz(Recepcija r){
		rec=r;
		minDolazi=500;
		maxDolazi=1500;
		minBoravi=1000;
		maxBoravi=3000;
		minNapusta=0.05;
	    maxNapusta=0.15;
		prikaz=new Label();
		start();
	}
	
	public void run(){
		try {
			while(!interrupted()){
				rec.dodajGosta(g=new Gost(">"));
				sleep((long)(getDolaziVer()));
				prikaz.setText("Ulaz: "+g.toString());
				
				if(g!=null)
				{
					g.prekini();
					g=null;
				}
			}
		} catch (InterruptedException e) {System.err.println("Greska ulaz!");}
	
	}
	
	public Label prikaz(){
		return prikaz;
	}
	

	public void setMinDolazi(int minDolazi) {
		this.minDolazi = minDolazi;
	}


	public void setMaxDolazi(int maxDolazi) {
		this.maxDolazi = maxDolazi;
	}


	public static double getBoraviVer() {
		return minBoravi+Math.random()*(maxBoravi-minBoravi);
	}
	
	public void setMinBoravi(int minBoravi) {
		this.minBoravi = minBoravi;
	}
	
	public void setMaxBoravi(int maxBoravi) {
		this.maxBoravi = maxBoravi;
	}
	
	public  double getDolaziVer() {
		return minDolazi+Math.random()*(maxDolazi-minDolazi);
	}
	
	public static double getNapustaVer() {
		return minNapusta+Math.random()*(maxNapusta-minNapusta);
	}

	public void setMinNapusta(int minNapusta) {
		this.minNapusta = minNapusta/100;
	}

	public void setMaxNapusta(int maxNapusta) {
		this.maxNapusta = maxNapusta/100;
	}
}

