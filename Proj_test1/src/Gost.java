

public class Gost extends Osoba implements Obavestiv {

	private String stanje;
	private double ukCena=0;
	private Soba s;
	
	
	public Gost(String s){
		stanje=s;
	}
	
	public  double ukupnaCena(){
		return ukCena;
	}
	
	public synchronized void poceoDan() {
		ukCena+=Recepcija.dajCenuSobe();
		System.out.println("Uk cena: "+ukCena);
	}

	public void radnja() {
			if(stanje.equals("@")){
			setStanje("~");
			try {
				sleep((long)Ulaz.getBoraviVer());
			} catch (InterruptedException e) {}
			
		}else if(stanje.equals("~")){
			try {
				sleep((long)Ulaz.getBoraviVer());
			} catch (InterruptedException e) {}
			if((0+Math.random()*(100-0)<Ulaz.getNapustaVer())){
				stanje="<";
			}
		else
			setStanje("@");
		}
		if(this.stanje.equals("<")){
			setStanje("<");
		}
	}
	
	public void ulazi(Soba soba){
		s=soba;
	}
	
	public void napolje(){
		s.isprazniSobu();
	}
	
	public String toString(){
		return dohvatiId()+": "+ stanje;
	}
	
	public String getStanje() {
		return stanje;
	}

	public void setStanje(String stanje) {
		this.stanje = stanje;
	}

}
