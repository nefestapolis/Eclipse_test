
public abstract class Osoba extends Thread{
	private static int posId=0;
	private int id=++posId;
	public boolean radi=true;
	
	public Osoba(){
		start();
	}
	
	public void run () {
		while (!interrupted()) {
			try {
				synchronized (this)  {if (!radi) wait(); }				
				radnja();	
			} catch (InterruptedException g) {}
		}
	}
	
	public abstract void radnja();
	
	public  void zaustavi(){
		radi=false;
	}
	
	public synchronized void nastavi(){
		radi=true; 
		notify();
	}
	
	public void prekini(){
		interrupt(); 
	}
	
	public int dohvatiId(){
		return id;
	}
	
}
