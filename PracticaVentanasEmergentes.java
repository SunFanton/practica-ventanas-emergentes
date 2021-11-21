import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class PracticaVentanasEmergentes {

	public static void main(String[] args) {
		
		MarcoVenEm marco = new MarcoVenEm();
		marco.setVisible(true);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

//Marco Principal
class MarcoVenEm extends JFrame{
	
	public MarcoVenEm() {
		
		setTitle("Práctica ventanas emergentes");
		setSize(600,350);
		setLocationRelativeTo(null);
		setResizable(true);
		
		add(new LaminaVenEm());
	}
}

//Lamina Principal
class LaminaVenEm extends JPanel{
	
	private JPanel laminaCentro,
				   laminaSur;
	private JButton mostrar;
	private ButtonGroup gTipo,
					    gTipoMensaje,
					    gMensaje,
					    gConfirmar,
					    gOpcion,
					    gEntrada;
	private String[] tipo = {"Mensaje",
							 "Confirmar",
							 "Opción",
							 "Entrada"},
			
				tipoMensaje = {"ERROR_MESSAGE",
							   "INFORMATION_MESSAGE",
							   "WARNING_MESSAGE",
							   "QUESTION_MESSAGE",
							   "PLAIN_MESSAGE"},
				
				   mensaje = {"Cadena",
						      "Icono",
						      "Componente",
						      "Otros",
						      "Object[]"},
				   
				 confirmar = {"DEFAULT_OPTION",
						      "YES_NO_OPTION",
						      "YES_NO_CANCEL_OPTION",
						      "OK_CANCEL_OPTION"},
				 
				   opcion = {"String[]",
						     "Icon[]",
						     "Object[]"},
				   
				  entrada = {"Campo Texto",
						     "Combo"};
	
	public LaminaVenEm() {
		
		setLayout(new BorderLayout());
		laminaCentro = new JPanel();
		laminaCentro.setLayout(new GridLayout(2,3));
		laminaSur = new JPanel();
		
		mostrar = new JButton("Mostrar");
		mostrar.addActionListener(new AccionMostrar());
		laminaSur.add(mostrar);
		laminaSur.setBackground(Color.LIGHT_GRAY);
		add(laminaSur,BorderLayout.SOUTH);
		
		//Contenido Caja1
		crearCaja(tipo,gTipo = new ButtonGroup(),Box.createVerticalBox(),"Tipo");
		//Contenido Caja2
		crearCaja(tipoMensaje,gTipoMensaje = new ButtonGroup(),Box.createVerticalBox(),"Tipo Mensaje");
		//Contenido Caja3
		crearCaja(mensaje,gMensaje = new ButtonGroup(),Box.createVerticalBox(),"Mensaje");
		//Contenido Caja4
		crearCaja(confirmar,gConfirmar = new ButtonGroup(),Box.createVerticalBox(),"Confirmar");
		//Contenido Caja5
		crearCaja(opcion,gOpcion = new ButtonGroup(),Box.createVerticalBox(),"Opción");
		//Contenido Caja6
		crearCaja(entrada,gEntrada = new ButtonGroup(),Box.createVerticalBox(),"Entrada");
		
		add(laminaCentro,BorderLayout.CENTER);
	}
	
	//-----------------------------------------------------
	//Metodo para crear las cajas (Box):
	public void crearCaja(String[] nombre,ButtonGroup grupo,Box caja,String nomCaja) {
		
		for(int i=0;i<nombre.length;i++) {
			
			JRadioButton botonRadio = new JRadioButton(nombre[i]);
			botonRadio.setSelected(i==0);
			botonRadio.setActionCommand(nombre[i]);
			grupo.add(botonRadio);
			caja.add(botonRadio);
		}
		
		TitledBorder titulo = BorderFactory.createTitledBorder(nomCaja);
		titulo.setTitleJustification(TitledBorder.LEFT);
		caja.setBorder(titulo);
		
		laminaCentro.add(caja);
	}
	
	//-----------------------------------------------------
	//Clase interna eventos:
	private class AccionMostrar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Object mensaje = dameMensaje();
			Object[] opcion = dameOpcion();
			int tipoMensaje = dameTipoMensaje(),
				confirmar = dameConfirmar();
			
			//JOPTIONPANE´S--------------------------------------
			String tipo = gTipo.getSelection().getActionCommand();
			
			if(tipo.equalsIgnoreCase("Mensaje")) {
				JOptionPane.showMessageDialog(LaminaVenEm.this,mensaje,"Mensaje",tipoMensaje);
			}
			else if(tipo.equalsIgnoreCase("Confirmar")) {
				JOptionPane.showConfirmDialog(LaminaVenEm.this,mensaje,"Confirmación",confirmar,tipoMensaje);
			}
			else if(tipo.equalsIgnoreCase("Opción")) {
				JOptionPane.showOptionDialog(LaminaVenEm.this,mensaje,"Opciones",confirmar,tipoMensaje,null,opcion,null);
			}
			else if(tipo.equalsIgnoreCase("Entrada")) {
				
				String entrada = gEntrada.getSelection().getActionCommand();
				
				if(entrada.equalsIgnoreCase("Campo Texto"))
					JOptionPane.showInputDialog(LaminaVenEm.this,mensaje,"Entrada",tipoMensaje);
				
				else if(entrada.equalsIgnoreCase("Combo")) {
					String[] combo = {"Amarillo","Azul","Rojo"};
					JOptionPane.showInputDialog(LaminaVenEm.this,mensaje,"Entrada",tipoMensaje,null,combo,"Azul");
				}
			}
		}
		
		//---------------------------------------------------
		
		//Metodos para obtener los radiobutton seleccionados:
		
		//1) Seleccion en Tipo Mensaje:
		public int dameTipoMensaje() {
			
			String tipoMen = gTipoMensaje.getSelection().getActionCommand();
			
			if(tipoMen.equalsIgnoreCase("ERROR_MESSAGE"))
				return 0;
			else if(tipoMen.equalsIgnoreCase("INFORMATION_MESSAGE"))
				return 1;
			else if(tipoMen.equalsIgnoreCase("WARNING_MESSAGE"))
				return 2;
			else if(tipoMen.equalsIgnoreCase("QUESTION_MESSAGE"))
				return 3;
			else if(tipoMen.equalsIgnoreCase("PLAIN_MESSAGE"))
				return -1;
			else {
				return -1;
			}
		}
		
		//2)Seleccion en Mensaje:
		public Object dameMensaje() {
			
			String men = gMensaje.getSelection().getActionCommand();
			
			if(men.equalsIgnoreCase("Cadena"))
				return "Mensaje de prueba";
			else if(men.equalsIgnoreCase("Icono"))
			return new ImageIcon("azul.png");
			else if(men.equalsIgnoreCase("Componente")) 
				return new JLabel("ETIQUETA");
			else if(men.equalsIgnoreCase("Otros"))
				return new Date();
			else if(men.equalsIgnoreCase("Object[]"))
				return new Object[] {"Mensaje de prueba",
									new ImageIcon("azul.png"),
									new JLabel("ETIQUETA"),
									new Date()};
			else {
				return null;
			}
		}
		
		//3)Seleccion en Confirmar:
		public int dameConfirmar() {
			
			String confi = gConfirmar.getSelection().getActionCommand();
			
			if(confi.equalsIgnoreCase("DEFAULT_OPTION"))
				return -1;
			else if(confi.equalsIgnoreCase("YES_NO_OPTION"))
				return 0;
			else if(confi.equalsIgnoreCase("YES_NO_CANCEL_OPTION"))
				return 1;
			else if(confi.equalsIgnoreCase("OK_CANCEL_OPTION"))
				return 2;
			else {
				return -1;
			}
		}
		
		//4) Seleccion en Opcion:
		public Object[] dameOpcion() {
			
			String op = gOpcion.getSelection().getActionCommand();
			
			if(op.equalsIgnoreCase("String[]"))
				return new String[]{"Amarillo",
						  			  "Azul",
						  			  "Rojo"};
			else if(op.equalsIgnoreCase("Icon[]"))
				return new ImageIcon[]{new ImageIcon("amarillo.png"),
								         new ImageIcon("azul.png"),
								         new ImageIcon("rojo.png")};
			else if(op.equalsIgnoreCase("Object[]"))
				return new Object[] {"Prueba",
										new ImageIcon("azul.png"),
										new JLabel("ETIQUETA"),
										new Date()};
			else {
				return null;
			}
		}
		
	}
	
}


