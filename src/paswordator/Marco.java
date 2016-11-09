package paswordator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * @author Daniel
 * 30 de jun. de 2016
 * 
 */
public class Marco extends JFrame{

	public Marco(){
		
		setTitle("Generador de contraseñas");
		setBounds(500, 250, 850, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Lamina lamina = new Lamina();
		
		//--------------------------------------------------------------------------------------------------------
		//Añadiendo pestañas
		JTabbedPane pestanas = new JTabbedPane();
		JScrollPane barra1 = new JScrollPane(lamina.getGenerador());
		JScrollPane barra2 = new JScrollPane(lamina.getConsejos());
		
		pestanas.addTab("Generador", barra1);
		pestanas.addTab("Consejos", barra2);
		
		getContentPane().add(pestanas);
		
		setVisible(true);
	}
}
