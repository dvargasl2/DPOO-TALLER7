package uniandes.dpoo.taller7.interfaz1;

import java.awt.BorderLayout;

import javax.swing.*;

public class Ventana extends JFrame{
	
	
	public Ventana() {
		this.setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		setTitle("Juego de LightsOut");
	}
	
}	
