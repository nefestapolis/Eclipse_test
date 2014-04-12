
import java.awt.*;
import java.awt.event.*;


public class Hotel extends Frame implements Obavestiv{
	 private Casovnik cas=new Casovnik(this);
	 private TextArea prikazReda=new TextArea("",15,15);
	 
	 private Recepcija rec;
	 Ulaz ulaz;
	 public int brSoba=15;
	 Soba[] sobe=new Soba[brSoba];
	 
	 private TextField txtPostaviVreme=new TextField("",5);
	 private TextField txtMinDolazvi,
	 				   txtMaxDolazi,
	 				   txtMinBoravi,
	 				   txtMaxBoravi,
	 				   txtMinNapusta,
	 				   txtMaxNapusta,
	 				   txtMinOpsuzuje,
	 				   txtMaxOpsuzuje;
	 
	 private TextField txtPostaviCenu;
	 private Checkbox chkRec1,chkRec2;
	 
	 
	 private Button btnPromenaVremena=new Button("Postavi vreme"); 
	 private Button btnPostaviDolazenje,
	 				btnPostaviBoravi,
	 				btnPostaviCenuSobe,
	 				btnPostaviNapusHotel,
	 				btnPostaviOpsl;
	 
	
	
	public Hotel(){
		super("Odmorite se kod Pedje");
		setSize(700,700);
		setVisible(true);
		setResizable(false);
		
		for(int i=0;i<brSoba;i++){//stvara sobe
			sobe[i]=new Soba();
		}
		
		
		 rec=new Recepcija(prikazReda,this);
		 ulaz=new Ulaz(rec);
		
		popuniProzor();
			
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent d){
				rec.unistiGoste();
				//rec.unisiRecepcionare();
				
				cas.zavrsi();
				dispose();
			}
		});
		
	}
	
	
	private void popuniProzor() {
		
		
		Panel plo=new Panel(new BorderLayout());
			  plo.setBackground(Color.LIGHT_GRAY);
			  add(plo,"West");
			    
			  
				Panel plo2=new Panel();
				Panel plo3=new Panel();		
				
				plo.setLayout(new GridLayout(9,1));
				plo.setBackground(Color.GRAY);		
				plo2.setLayout(new FlowLayout());
				plo3.setLayout(new FlowLayout());
				
				Panel dodatni=new Panel();
				dodatni.setLayout(new GridLayout(2,1));
				
				Panel sat=new Panel(new GridLayout(1,1));
				sat.add(cas.prikazivac());
				plo.add(sat);
				
				plo2.add(txtPostaviVreme);
				dodatni.add(plo2);			
				plo2.add(btnPromenaVremena);		
					btnPromenaVremena.addActionListener(new PostaviVreme());
				
				plo2=new Panel(new FlowLayout());
				plo2.add(dodatni);	
				plo.add(plo2);		
				
				plo3.setLayout(new GridLayout(3,1));
					Panel rec1=new Panel(new FlowLayout());
					rec1.add(rec.vratiRec1().vratiPrikaz());//prvi recepcioner
					
					rec1.add(chkRec1=new Checkbox("Odmor"));
						chkRec1.addItemListener(new PosaljiNaOdmor());
					
					Panel rec2=new Panel(new FlowLayout());
					rec2.add(rec.vratiRec2().vratiPrikaz());//drugi recepcioner
				
					rec2.add(chkRec2=new Checkbox("Odmor"));
						chkRec2.addItemListener(new PosaljiNaOdmorRec2());
	
					
				plo3.add(rec1);
				plo3.add(rec2);	
				
				plo.add(plo3);
				
				//opsluzivanje
				Panel opsuzuje=new Panel(new FlowLayout());
					opsuzuje.add(new Label("Min Opsluz:"));
					opsuzuje.add(txtMinOpsuzuje=new TextField("",5));
					opsuzuje.add(new Label("Max Opsluz:"));
					opsuzuje.add(txtMaxOpsuzuje=new TextField("",5));
					opsuzuje.add(btnPostaviOpsl=new Button("Postavi"));
						btnPostaviOpsl.addActionListener(new PostaviOpsluzivanje());
				plo.add(opsuzuje);//opsluzivanje
				
				//Dolazenje gostiju
				Panel dolazi =new Panel(new FlowLayout());
				dolazi.add(new Label("Min Dolazi"));
				dolazi.add(txtMinDolazvi=new TextField("",5));
				dolazi.add(new Label("Max Dolazi"));
				dolazi.add(txtMaxDolazi=new TextField("",5));
				dolazi.add(btnPostaviDolazenje=new Button("Postavi"));
					btnPostaviDolazenje.addActionListener(new PostaviDolazenjeGostiju());
				plo.add(dolazi);//dolazenje gostiju
				
				//boravak u sobi
				Panel boravi=new Panel(new FlowLayout());
					  boravi.add(new Label("Min Boravi"));
					  boravi.add(txtMinBoravi=new TextField("",5));
					  boravi.add(new Label("Max Boravi"));
					  boravi.add(txtMaxBoravi=new TextField("",5));
					  boravi.add(btnPostaviBoravi=new Button("Postavi"));
					  	btnPostaviBoravi.addActionListener(new PostaviBoravi());
				plo.add(boravi);//boravak u sobi  
				
				//napusta hotel
				Panel napusta=new Panel(new FlowLayout());
					  napusta.add(new Label("Min Napusta"));
					  napusta.add(txtMinNapusta=new TextField("",5));
					  napusta.add(new Label("Max Napusta"));
					  napusta.add(txtMaxNapusta=new TextField("",5));
					  napusta.add(btnPostaviNapusHotel=new Button("Postavi"));
					  	btnPostaviBoravi.addActionListener(new PostaviDolazenjeGostiju());
			   plo.add(napusta);//napusta hotel
			   
			   //cena sobe
			   Panel cenaSobe=new Panel(new FlowLayout());
			   		 cenaSobe.add(new Label("Cena sobe: "));
			   		 cenaSobe.add(txtPostaviCenu=new TextField("",5));
			   		 cenaSobe.add(btnPostaviCenuSobe=new Button("Postavi"));
			   		 btnPostaviCenuSobe.addActionListener(new PostaviCenuSobe());
			   plo.add(cenaSobe);//cena sobe
			   
			   
			   Panel ulazPanel=new Panel(new GridLayout(2,1));
			   		
			   		Panel red=new Panel(new GridLayout(0, 1, 10, 10)); 
			   		red.add(rec.vratiPrikaz());
					ulazPanel.add(red); 
			   		
					ulazPanel.add(ulaz.prikaz());
					
			   	plo.add(ulazPanel,"South");//ulaz
	   	
	   	//ukupna suma glavni panel
	   	plo=new Panel();
	   	plo.setLayout(new FlowLayout());
	   	plo.setBackground(Color.RED);
	   	plo.add(new Label("Ukupna suma:"+rec.ukupanPrihod()));
	   	add(plo,"North");
		
		
		add(Soba.vratiSobu(),"Center");
			
		this.revalidate();
		
	
	}

	private class PostaviVreme implements ActionListener{
		public void actionPerformed(ActionEvent d) {
			try {
				cas.postaviVreme(Long.parseLong(txtPostaviVreme.getText()));
			} catch (NumberFormatException e) {}
		}
	}
	
	private class PostaviDolazenjeGostiju implements ActionListener{
		public void actionPerformed(ActionEvent d) {
			try {
				ulaz.setMinDolazi(Integer.parseInt(txtMaxDolazi.getText()));
				ulaz.setMaxDolazi(Integer.parseInt(txtMinDolazvi.getText()));
			} catch (NumberFormatException e) {}
		}
	}
	
	private class PostaviNapusHotel implements ActionListener{
		public void actionPerformed(ActionEvent d) {
			ulaz.setMinNapusta(Integer.parseInt(txtMinNapusta.getText()));
			ulaz.setMaxNapusta(Integer.parseInt(txtMaxNapusta.getText()));
		}
	}
	
	private class PostaviBoravi implements ActionListener{
		public void actionPerformed(ActionEvent d) {
			try {
				ulaz.setMinBoravi(Integer.parseInt(txtMinBoravi.getText()));
				ulaz.setMaxBoravi(Integer.parseInt(txtMaxBoravi.getText()));
			} catch (NumberFormatException e) {}			
		}
	}
	
	private class PostaviOpsluzivanje implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			rec.setMinOpsluzuje(Integer.parseInt(txtMinOpsuzuje.getText()));
			rec.setMaxOpsuluzuje(Integer.parseInt(txtMaxOpsuzuje.getText()));
			
		}
		
	}
	
	private class PostaviCenuSobe implements ActionListener{
		public void actionPerformed(ActionEvent d) {
			try {
				rec.setCenaSobe(Double.parseDouble(txtPostaviCenu.getText()));
			} catch (NumberFormatException e) {}
		}
	}
	
	private class PosaljiNaOdmor implements ItemListener{
		
		public void itemStateChanged(ItemEvent e) {
			rec.vratiRec1().vratiPrikaz().revalidate();
			
			if(chkRec1.getState())
				rec.posaljiNaOdmor(0);
			else rec.uposliRec(0);
		}
		
	}
	
	
	
	
	private class PosaljiNaOdmorRec2 implements ItemListener{
		
		public void itemStateChanged(ItemEvent e) {
			rec.vratiRec2().vratiPrikaz().revalidate();
			if(chkRec2.getState())
				rec.posaljiNaOdmor(1);
			else rec.uposliRec(1);
		}
		
	}
	
	public synchronized Soba dajSobu(){
		for(int i=0;i<brSoba;i++){
			if(!(sobe[i].jelPuna())){
				return sobe[i];
			}
		}
		return null;
	}
	
	
	
	public static void main(String[] args){
		new Hotel();
		
	}

	@Override
	public void poceoDan() {
		// TODO Auto-generated method stub
		
	}
}
