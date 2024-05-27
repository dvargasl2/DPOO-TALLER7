package uniandes.dpoo.taller7.interfaz4;

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
    private int boardSize = 5; // tamaño inicial del tablero
    private int jugadas = 0;
    private boolean[][] initialBoardState; // Para reiniciar el tablero

    public Ventana() {
        this.setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Juego de LightsOut");
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.LIGHT_GRAY);  // Cambiar color de fondo

        tamanio = new JLabel("Tamaño");
        String[] tamanios = {"5x5", "6x6", "7x7"};
        taman = new JComboBox<>(tamanios);
        taman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        buttonPanel.setBackground(Color.DARK_GRAY);  // Cambiar color de fondo

        nuevo = new JButton("NUEVO");
        reiniciar = new JButton("REINICIAR");
        top = new JButton("TOP-10");
        cambJugador = new JButton("CAMBIAR JUGADOR");

        nuevo.setBackground(Color.CYAN);  
        reiniciar.setBackground(Color.CYAN);
        top.setBackground(Color.CYAN);
        cambJugador.setBackground(Color.CYAN);

        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearNuevoTablero();
            }
        });

        reiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarTablero();
            }
        });

        top.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        cambJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarJugador();
            }
        });

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
        bottomPanel.setBackground(Color.LIGHT_GRAY);  

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
        boardPanel.setBackground(Color.BLACK);  

        this.add(topPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(boardPanel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    private void crearNuevoTablero() {
        boardPanel.setBoardSize(boardSize);
        jugadas = 0;
        jugadasT.setText(String.valueOf(jugadas));
        guardarEstadoInicial();
    }

    private void reiniciarTablero() {
        if (initialBoardState != null) {
            boardPanel.setBoardSize(boardSize);
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    boardPanel.toggleLight(i, j);
                }
            }
            jugadas = 0;
            jugadasT.setText(String.valueOf(jugadas));
            boardPanel.repaint();
        }
    }

    private void guardarEstadoInicial() {
        initialBoardState = new boolean[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                initialBoardState[i][j] = boardPanel.toggleLight(i, j);
            }
        }
    }



    private void cambiarJugador() {
        String nuevoJugador = JOptionPane.showInputDialog(this, "Ingrese el nombre del nuevo jugador:");
        if (nuevoJugador != null && !nuevoJugador.trim().isEmpty()) {
            jugadorT.setText(nuevoJugador);
        }
    }

    public static void main(String[] args) {
        new Ventana();
    }
}
