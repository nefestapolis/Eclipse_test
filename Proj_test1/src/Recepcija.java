
import java.awt.TextArea;

public class Recepcija {
	
	private class Elem{
		Gost gost;
		Elem sled;
		public Elem(Gost g) {
			gost=g;
			if(prvi==null) prvi=this; 
			else posl.sled=this;
			posl=this;
		}
	}
	
	private Elem prvi,posl;
	private TextArea pisi;
	private int minOpsluzuje,maxOpsluzuje;
	private double ukPrihod;
	private static double cenaSobe;
	private Recepcioner[] r=new Recepcioner[2];
	
	private Recepcioner r1,r2;
	

	public Recepcija(TextArea pisi,Hotel h){
		this.pisi=pisi;
		minOpsluzuje=100;
		maxOpsluzuje=400;
		cenaSobe=2000;
		
		
		r[0]=new Recepcioner(this,h);
		r[1]=new Recepcioner(this,h);
		
	}
	
	public Recepcioner vratiRec1(){
		return r[0];
	}
	
	public Recepcioner vratiRec2(){
		return r[1];
	}

	public double cenaSobe(){
		return cenaSobe;
	}
	
	
	
	public double ukupanPrihod(){
		ukPrihod+=r[0].naplati()+r[1].naplati();
		return ukPrihod;
	}
	
	
	public void unistiGoste(){
		for(Elem tek=prvi;tek!=null;tek=tek.sled)
			if(tek.gost!=null)
				tek.gost.prekini();
	}
	
	public synchronized void dodajGosta(Gost g){
		new Elem(g); 
		prikazi();		
		notifyAll();
	}
	
	public synchronized Gost uzmiGosta() throws InterruptedException{
		while(prvi==null) wait();
		Gost g=prvi.gost;
		prvi=prvi.sled;
		if(prvi==null) posl=null;
		prikazi();
		notifyAll();
		return g;
		
	}
	
	public  String toString() {
		StringBuilder s = new StringBuilder();
		for (Elem tek=prvi; tek!=null; tek=tek.sled) s.append(tek.gost.toString()).append("\n");
		return s.toString();
	}
	
	public TextArea vratiPrikaz(){
		return pisi;
	}
	
	public void prikazi(){
	 	pisi.setText(toString());
	 	pisi.revalidate();
	}
	
	public static double dajCenuSobe() {
		return cenaSobe;
	}

	public void setCenaSobe(double cenaSobe) {
		Recepcija.cenaSobe = cenaSobe;
	}
	
	public double getOpsluzujeVer() {
		return minOpsluzuje+Math.random()*(maxOpsluzuje-minOpsluzuje);
	}

	public void setMinOpsluzuje(int minOpsluzuje) {
		this.minOpsluzuje = minOpsluzuje;
	}

	public void setMaxOpsuluzuje(int maxOpsuluzuje) {
		this.maxOpsluzuje = maxOpsuluzuje;
	}
	
	public void posaljiNaOdmor(int i){
		
		r[i].Odmor(true);
	}
	
	public void uposliRec(int i){
		
		r[i].Odmor(false);
	}
	
}
