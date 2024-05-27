package uniandes.dpoo.taller7.interfaz3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {
    private JLabel tamanio;
    private JLabel dificultad;
    private JComboBox<String> taman;
    private JRadioButton facil;
    private JRadioButton medio;
    private JRadioButton dificil;
    private JButton nuevo;
    private JButton reiniciar;
    private JButton top;
    private JButton cambJugador;
    private JTextField jugadasT;
    private JTextField jugadorT;
    private ButtonGroup dificultadGroup;
    private Tablero boardPanel;
    private int boardSize = 5; // 5*5 al comienzo auqnue despues cambia!!!!!
    private int jugadas = 0;

    public Ventana() {
        this.setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Juego de LightsOut");
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        tamanio = new JLabel("Tamaño");
        String[] tamanios = {"5x5", "6x6", "7x7"};
        taman = new JComboBox<>(tamanios);
        taman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pa cambiar el tamaño
                String selectedSize = (String) taman.getSelectedItem();
                boardSize = Integer.parseInt(selectedSize.substring(0, 1));
                boardPanel.setBoardSize(boardSize);
                jugadas = 0;
                jugadasT.setText(String.valueOf(jugadas));
            }
        });

        dificultad = new JLabel("Dificultad");
        facil = new JRadioButton("Fácil");
        medio = new JRadioButton("Medio");
        dificil = new JRadioButton("Difícil");
        dificultadGroup = new ButtonGroup();
        dificultadGroup.add(facil);
        dificultadGroup.add(medio);
        dificultadGroup.add(dificil);

        topPanel.add(tamanio);
        topPanel.add(taman);
        topPanel.add(dificultad);
        topPanel.add(facil);
        topPanel.add(medio);
        topPanel.add(dificil);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        nuevo = new JButton("NUEVO");
        reiniciar = new JButton("REINICIAR");
        top = new JButton("TOP-10");
        cambJugador = new JButton("CAMBIAR JUGADOR");

        Dimension buttonSize = new Dimension(150, 30);
        nuevo.setPreferredSize(buttonSize);
        reiniciar.setPreferredSize(buttonSize);
        top.setPreferredSize(buttonSize);
        cambJugador.setPreferredSize(buttonSize);

        buttonPanel.add(nuevo);
        buttonPanel.add(reiniciar);
        buttonPanel.add(top);
        buttonPanel.add(cambJugador);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel jugadas = new JLabel("Jugadas:");
        jugadasT = new JTextField("0", 5);
        jugadasT.setEditable(false);

        JLabel jugador = new JLabel("Jugador:");
        jugadorT = new JTextField(10);

        bottomPanel.add(jugadas);
        bottomPanel.add(jugadasT);
        bottomPanel.add(jugador);
        bottomPanel.add(jugadorT);

        boardPanel = new Tablero(boardSize);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(boardPanel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Ventana();
    }
}
