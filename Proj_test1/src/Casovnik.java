
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Casovnik extends Thread{

	  private long t0;
	  private long dt;
	  private long trajanjeDana,obavesti=0;
	  private Obavestiv ob;
	  private int br=1;
	  
	  public Platno prik=new Platno();
	  
	  public Casovnik(Obavestiv o) {
		ob=o;
		t0 = System.currentTimeMillis();
	    trajanjeDana=2500;
	    start();
	  }

	  public void prekini() { interrupt(); }

	  public Platno prikazivac() {
		  return prik;
	  }

	  public  void prikazi(long dt) {
		  this.dt = dt;
//		  if(this.dt-(trajanjeDana*2)==obavesti || this.dt==0)
//	      {
//			  	System.out.println("Poceo: "+(br+1));
//	        	ob.poceoDan();
//	        	obavesti=this.dt;
//	        	br++;
//	       }
		  prik.repaint();
	 }
	  
	  public int brojDana(){
		  return br;
	  }
	  
	  public synchronized void postaviVreme(long pom){
			trajanjeDana=pom*500;
			obavesti=0;
			t0=System.currentTimeMillis();
		}

	  public void run () {
	    try {
	      while (!interrupted()) 
	      {
	    	  if(((trajanjeDana*2)*br)<=(System.currentTimeMillis()-t0))
				{
	    		  System.out.println(br);
				ob.poceoDan();
				br++;	
				
				}
	    	prikazi(System.currentTimeMillis()-t0);
	        sleep(1); 
	      }
	    } catch (InterruptedException g) {}
	  }
	  
	  public void zavrsi(){
		  interrupt();
	  }
	  
	  class Platno extends Canvas{
		  public void paint( Graphics g) {
		      int a = 46;
		      int b = 40;
		      g.translate( a, b);
		      a -= 10; b -=10;
		      g.setColor( Color.BLUE);
		      g.drawOval(-a, -b, 2*a, 2*b);
		      g.setColor( Color.RED);
		      double fi = Math.PI/2 - dt*Math.PI/trajanjeDana;//2500
		      int x = (int)(a * Math.cos( fi));
		      int y = (int)(- b * Math.sin( fi));
		      		      
		      g.drawLine( 0, 0, x, y);

		 }
	  }
	  
}
