package com.mavesoft;

public class Generation
{
    int[][] currentGeneration;
    int[][] nextGeneration;

    public Generation(int[][] grid)
    {
        currentGeneration = grid;
        nextGeneration = new int[currentGeneration.length][currentGeneration[0].length];
    }


    public void createNewGeneration()
    {
        int aliveNeighbours = 0;

        for (int i = 0; i < currentGeneration.length; i++)
        {

            for (int a = 0; a < currentGeneration[0].length; a++)
            {
                int cell = currentGeneration[i][a];

                aliveNeighbours = countNeighbours(currentGeneration, i, a);

                if (aliveNeighbours < 2 || aliveNeighbours > 3)
                {
                    nextGeneration[i][a] = 0;
                } else if (cell == 0 && aliveNeighbours == 3)
                {
                    // System.out.println("YES");
                    nextGeneration[i][a] = 1;
                } else if (aliveNeighbours >= 2 && aliveNeighbours <= 3)
                {
                    nextGeneration[i][a] = cell;
                }

            }
        }
    }

    public int countNeighbours(int[][] grid, int x, int y)
    {
        int alive = 0;

        for (int i = -1; i < 2; i++)
        {
            for (int a = -1; a < 2; a++)
            {
                try
                {
                    alive += grid[x + i][y + a];
                } catch (Exception ex)
                {
                    continue;
                }

            }
        }

        alive -= grid[x][y];

        // System.out.println(alive);

        return alive;
    }

    public int[][] getNextGeneration()
    {
        return nextGeneration;
    }
}
