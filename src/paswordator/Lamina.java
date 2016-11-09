package paswordator;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * @author Daniel
 * 30 de jun. de 2016
 * 
 */
public class Lamina extends JPanel{

	private JPanel generador, consejos;
	private JLabel avanzado, caracteres, longitud;
	private JTextField cCaracteres, cLongitud, cGenerar;
	private ButtonGroup grupo = new ButtonGroup();
	private Font peque = new Font("Dialog", Font.PLAIN, 14);
	private Font grande = new Font("Dialog", Font.BOLD, 16);
	
	private final int PRIMEROS_ELEMENTOS = 5;
	private int posicion = 0;
	private String seleccionado;
	
	private String[] tipos = {"JLabel", "JRadioButton", "JRadioButton", "JRadioButton", "JRadioButton"};
	private String[] descs = {"- Selecciona el tipo de contraseña que mejor se adapte: ",
								"Contraseña simple", "Contraseña media", "Contraseña compleja", "Contraseña personalizada"};
	private Font[] fuentes = {grande, peque, peque, peque, peque};
	private int[] colocaciones = {5, 5, 600, 20, 15, 25, 300, 15, 15, 40, 300, 15, 15, 55, 300, 15, 15, 70, 300, 15};
	
	public Lamina(){
		
		generador = new JPanel();
		generador.setLayout(null);
		consejos = new JPanel();
		consejos.setLayout(new GridLayout(4, 1));
		
		//--------------------------------------------------------------------------------------------------------
		//Construccion laminaGenerador
		
		for(int i=0; i<PRIMEROS_ELEMENTOS; i++){
			
			int[] colocacion = {0, 0, 0, 0};
			
			for(int j=posicion, x=0; j<posicion + 4; j++, x++){
				
				colocacion[x] = colocaciones[j];
			}
			posicion += 4;
			
			construyePrimeraParte(tipos[i], descs[i], fuentes[i], colocacion);
		}
		
		//Parte activable y desactivable
		avanzado = new JLabel("- AVANZADO");
		avanzado.setFont(grande);
		avanzado.setBounds(5, 100, 200, 20);
		avanzado.setEnabled(false);
		generador.add(avanzado);
		
		caracteres = new JLabel("Introduce los caracteres que compondrán la contraseña separados por ',': ");
		caracteres.setFont(peque);
		caracteres.setBounds(15, 125, 500, 15);
		caracteres.setEnabled(false);
		generador.add(caracteres);
		
		cCaracteres = new JTextField();
		cCaracteres.setFont(peque);
		cCaracteres.setBounds(15, 145, 600, 20);
		cCaracteres.setEnabled(false);
		generador.add(cCaracteres);
		
		longitud = new JLabel("Longitud: ");
		longitud.setFont(peque);
		longitud.setBounds(15, 165, 100, 20);
		longitud.setEnabled(false);
		generador.add(longitud);
		
		cLongitud = new JTextField();
		cLongitud.setFont(peque);
		cLongitud.setBounds(15, 190, 50, 20);
		cLongitud.setEnabled(false);
		generador.add(cLongitud);
		
		//Parte resultado
		JButton generar = new JButton("Generar");
		generar.setFont(grande);
		generar.setBounds(15, 235, 100, 30);
		generar.addActionListener(new AccionGenerar());
		generador.add(generar);
		
		cGenerar = new JTextField();
		cGenerar.setFont(peque);
		cGenerar.setBounds(15, 270, 200, 25);
		generador.add(cGenerar);
		
		//--------------------------------------------------------------------------------------------------------
		//Construccion laminaConsejos
		construyeConsejos("- No utilices la misma contraseña para todo.");
		construyeConsejos("- Una buena contraseña debe ser alfanumérica, combinar mayúsculas y minúsculas y contener símbolos.");
		construyeConsejos("- Cambia la contraseña regularmente, cada cierto tiempo.");
		construyeConsejos("- Una contraseña debe ser individual y es recomendable que solo tú la sepas.");
		
		//--------------------------------------------------------------------------------------------------------
		//Menu emergente
		JPopupMenu emergente = new JPopupMenu();
		JMenuItem copiar = new JMenuItem("Copiar");
		copiar.addActionListener(new AccionMenuEmergente());
		emergente.add(copiar);
		cGenerar.setComponentPopupMenu(emergente);		
	}
	
	private class AccionMenuEmergente implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			StringSelection data = new StringSelection(cGenerar.getText());
			clipboard.setContents(data, data);
		}		
	}
	
	private class AccionGenerar implements ActionListener{

		private int longitud;
		private Random aleatorio = new Random();
		private String[] caracteresCompleja = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z",
												"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z",
												"0","1","2","3","4","5","6","7","8","9",
												"!","@","#","$","%","&","-","_",",",";","¡","?","¿","/","\\","|","\"","~","(",")","=","^",".",":","<",">","{","}","[","]"};
		private String[] caracteresSimple = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z",
											"0","1","2","3","4","5","6","7","8","9",};
		private String[] caracteresMedia = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z",
											"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z",
											"0","1","2","3","4","5","6","7","8","9"};
		
		public void actionPerformed(ActionEvent e) {
			
			if(seleccionado == "simple"){
				
				longitud = 8;
				calcularContrasena(caracteresSimple, longitud, 36);
				
			}else if(seleccionado == "media"){
				
				longitud = (int)(aleatorio.nextDouble() * 5 + 8);
				calcularContrasena(caracteresMedia, longitud, 62);
				
			}else if(seleccionado == "compleja"){
				
				longitud = (int)(aleatorio.nextDouble() * 13 + 8);
				calcularContrasena(caracteresCompleja, longitud, 92);
				
			}else if(seleccionado == "personalizada"){
				
				longitud = Integer.parseInt(cLongitud.getText());
				
				String caracteresPersonalizados = cCaracteres.getText();
				String[] caracteresPersonalizada = caracteresPersonalizados.split(",");
				
				calcularContrasena(caracteresPersonalizada, longitud, caracteresPersonalizada.length);
			}
		}
		
		private void calcularContrasena(String[] caracteres, int longitud, int numCaracteres){
			
			String contrasena = "";
			String caracter;
			
			for(int i=0; i<longitud; i++){
				
				caracter = caracteres[(int)(aleatorio.nextDouble() * numCaracteres)];
				contrasena += caracter;
			}
			
			cGenerar.setText(contrasena);
		}
	}
	
	private class AccionSeleccionada implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			avanzado.setEnabled(false);
			caracteres.setEnabled(false);
			cCaracteres.setEnabled(false);
			longitud.setEnabled(false);
			cLongitud.setEnabled(false);
			
			if(e.getActionCommand() == "Contraseña simple"){
				
				seleccionado = "simple";
				
			}else if(e.getActionCommand() == "Contraseña media"){
				
				seleccionado = "media";
				
			}else if(e.getActionCommand() == "Contraseña compleja"){
				
				seleccionado = "compleja";
				
			}else if(e.getActionCommand() == "Contraseña personalizada"){
				
				avanzado.setEnabled(true);
				caracteres.setEnabled(true);
				cCaracteres.setEnabled(true);
				longitud.setEnabled(true);
				cLongitud.setEnabled(true);
				seleccionado = "personalizada";
				
			}
		}
		
	}
	
	private void construyePrimeraParte(String tipo, String desc, Font fuente, int[] colocacion){
		
		if(tipo == "JLabel"){
			
			JLabel elemento = new JLabel(desc);
			elemento.setFont(fuente);
			elemento.setBounds(colocacion[0], colocacion[1], colocacion[2], colocacion[3]);
			generador.add(elemento);
			
		}else if(tipo == "JRadioButton"){
			
			JRadioButton elemento = new JRadioButton(desc);
			grupo.add(elemento);
			elemento.addActionListener(new AccionSeleccionada());
			elemento.setFont(fuente);
			elemento.setBounds(colocacion[0], colocacion[1], colocacion[2], colocacion[3]);
			generador.add(elemento);
			
		}
	}
	
	private void construyeConsejos(String consejo){
		
		JLabel etiqueta = new JLabel(consejo);
		
		etiqueta.setFont(new Font("Dialog", Font.BOLD, 16));
		
		consejos.add(etiqueta);
	}
	
	public JPanel getGenerador(){
		
		return generador;
	}
	
	public JPanel getConsejos(){
		
		return consejos;
	}
}
