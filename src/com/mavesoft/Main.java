package com.mavesoft;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main extends JPanel
{

    final static int COLUMNS = 128;
    final static int ROWS = 128;
    final static int WIDTH = 512;
    final static int HEIGHT = 548;

    final static int CELLSIZE = 4;

    static int mode = 0;

    static JFrame gameFrame;

    static int[][] grid;

    static Main main;

    Graphics graphics;

    static Color[] colors;

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Hi! Modes: \n 0 - random \n 1 - gun \n 2 - three cells \n 3 - PARTY!!");

        mode = in.nextInt();

        while (mode > 3 || mode < 0)
        {
            System.out.println("Wrong mode!");
            System.out.println("Modes: \n 0 - random \n 1 - gun \n 2 - three cells \n 3 - PARTY!!");
            mode = in.nextInt();
        }

        main = new Main();

        gameFrame = new JFrame();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setTitle("Conway's Game of Life (Mavesoft Production)");
        gameFrame.setBounds(30, 30, WIDTH, HEIGHT);
        gameFrame.setResizable(false);
        gameFrame.add(main);
        gameFrame.setVisible(true);

        colors = new Color[5];

        colors[0] = Color.cyan;
        colors[1] = Color.white;
        colors[2] = Color.magenta;
        colors[3] = Color.yellow;
        colors[4] = Color.green;

        new Main().startSimulation(mode);

    }

    public static int[][] createCells(int columns, int rows)
    {
        int[][] grid = new int[columns][rows];

        for (int i = 0; i < columns; i++)
        {
            for (int a = 0; a < rows; a++)
            {
                int state = (int) (Math.random() * 8);

                if (state < 7)
                {
                    grid[i][a] = 0;
                } else
                {
                    grid[i][a] = 1;
                }


            }
        }

        return grid;
    }

    public void paint(Graphics g)
    {
        graphics = g;

        Color bgColor = Color.black;
        Color cellsColor = Color.cyan;

        if (mode == 3)
        {
            cellsColor = chooseColor();
        }

        for (int i = 0; i < ROWS * CELLSIZE; i += CELLSIZE)
        {
            int x = 0;
            int y = i;
            for (int n = 0; n < COLUMNS * CELLSIZE; n += CELLSIZE)
            {
                x = n;
                if (grid[i / CELLSIZE][n / CELLSIZE] == 0)
                {
                    g.setColor(bgColor);
                } else
                {
                    g.setColor(cellsColor);
                }
                g.fillRect(x, y, CELLSIZE, CELLSIZE);
            }
        }


    }

    public Color chooseColor()
    {
        Color color = colors[(int) (Math.random() * 5)];

        return color;
    }

    public void startSimulation(int mode)
    {

        switch (mode)
        {
            case 3:
                grid = createCells(COLUMNS, ROWS);
                gameFrame.setAlwaysOnTop(true);

                while (true)
                {
                    Generation currentGeneration = new Generation(grid);

                    try
                    {
                        Thread.sleep(100);
                        currentGeneration.createNewGeneration();
                        grid = currentGeneration.getNextGeneration();

                        main.repaint();
                    } catch (Exception ex)
                    {

                    }
                }

            case 2:
                grid = initThreeCells();

                gameFrame.setAlwaysOnTop(true);

                while (true)
                {
                    Generation currentGeneration = new Generation(grid);

                    try
                    {
                        Thread.sleep(100);
                        currentGeneration.createNewGeneration();
                        grid = currentGeneration.getNextGeneration();

                        main.repaint();
                    } catch (Exception ex)
                    {

                    }
                }

            case 1:
                grid = initGlider();

                gameFrame.setAlwaysOnTop(true);

                while (true)
                {
                    Generation currentGeneration = new Generation(grid);

                    try
                    {
                        Thread.sleep(100);
                        currentGeneration.createNewGeneration();
                        grid = currentGeneration.getNextGeneration();

                        main.repaint();
                    } catch (Exception ex)
                    {

                    }
                }

            case 0:
                grid = createCells(COLUMNS, ROWS);

                gameFrame.setAlwaysOnTop(true);

                while (true)
                {
                    Generation currentGeneration = new Generation(grid);

                    try
                    {
                        Thread.sleep(100);
                        currentGeneration.createNewGeneration();
                        grid = currentGeneration.getNextGeneration();

                        main.repaint();
                    } catch (Exception ex)
                    {

                    }
                }

        }

    }

    public int[][] initGlider()
    {
        int[][] grid = new int[COLUMNS][ROWS];

        // Left cube
        grid[10][2] = 1;
        grid[11][2] = 1;
        grid[10][3] = 1;
        grid[11][3] = 1;

        // Left thing

        grid[10][12] = 1;
        grid[11][12] = 1;
        grid[12][12] = 1;
        grid[13][13] = 1;
        grid[14][14] = 1;
        grid[14][15] = 1;
        grid[13][17] = 1;
        grid[12][18] = 1;
        grid[11][18] = 1;
        grid[11][19] = 1;
        grid[10][18] = 1;
        grid[9][17] = 1;
        grid[8][15] = 1;
        grid[8][14] = 1;
        grid[9][13] = 1;
        grid[11][16] = 1;

        // Right thing

        grid[10][22] = 1;
        grid[9][22] = 1;
        grid[8][22] = 1;
        grid[8][23] = 1;
        grid[9][23] = 1;
        grid[10][23] = 1;
        grid[11][24] = 1;
        grid[7][24] = 1;
        grid[7][26] = 1;
        grid[6][26] = 1;
        grid[11][26] = 1;
        grid[12][26] = 1;

        // Right cube

        grid[9][36] = 1;
        grid[8][36] = 1;
        grid[9][37] = 1;
        grid[8][37] = 1;


        return grid;
    }

    public int[][] initThreeCells()
    {
        int[][] grid = new int[COLUMNS][ROWS];

        grid[40][41] = 1;
        grid[40][42] = 1;
        grid[40][43] = 1;

        return grid;
    }


}
