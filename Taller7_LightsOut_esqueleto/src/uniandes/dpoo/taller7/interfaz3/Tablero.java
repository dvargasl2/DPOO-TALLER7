package uniandes.dpoo.taller7.interfaz3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tablero extends JPanel {
    private int boardSize;
    private boolean[][] lights;

    public Tablero(int size) {
        this.boardSize = size;
        this.lights = new boolean[boardSize][boardSize];
        this.setPreferredSize(new Dimension(500, 500));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cellSize = getWidth() / boardSize;
                int row = e.getY() / cellSize;
                int col = e.getX() / cellSize;
                toggleLights(row, col);
                repaint();
            }
        });

        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                lights[i][j] = true; // Luces prendassss al inicio
            }
        }
    }

    private void toggleLights(int row, int col) {
        toggleLight(row, col);
        if (row > 0) toggleLight(row - 1, col);
        if (row < boardSize - 1) toggleLight(row + 1, col);
        if (col > 0) toggleLight(row, col - 1);
        if (col < boardSize - 1) toggleLight(row, col + 1);
    }

    private void toggleLight(int row, int col) {
        lights[row][col] = !lights[row][col];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int cellSize = getWidth() / boardSize;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (lights[i][j]) {
                    g2.setColor(Color.YELLOW);
                } else {
                    g2.setColor(Color.GRAY);
                }
                g2.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g2.setColor(Color.BLACK);
                g2.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }

    public void setBoardSize(int newSize) {
        this.boardSize = newSize;
        this.lights = new boolean[boardSize][boardSize];
        inicializarTablero();
        repaint();
    }

    public boolean checkWin() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (!lights[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
