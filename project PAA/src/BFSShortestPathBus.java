/*
    QUIZ 2 DAA

    Finding the shortest route for a bus program
    Contributors :
        Abiansyah Adzani Gymnastiar (5025211077)
        Muhammad Fadhlan Ashila Harashta (5025211068)
        Fauzan Ahmad Faisal (5025211067)

    made using Intellij IDEA
 */

import java.util.*;
import javax.swing.*;

public class BFSShortestPathBus
{
    public static void main(String[] args)
    {
        int vertices = 9;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(vertices);
        for (int i = 0; i < vertices; i++)
            adj.add(new ArrayList<Integer>());

        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 1, 5);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);
        addEdge(adj, 3, 5);
        addEdge(adj, 5, 6);
        addEdge(adj, 4, 7);
        addEdge(adj, 7, 6);
        addEdge(adj, 7, 8);

        String input = JOptionPane.showInputDialog("""
                0 = Galaxy Mall Station
                1 = Kertajaya Station
                2 = Park N Ride Station
                3 = Cosmopolis Station
                4 = UHT Station
                5 = ITS Station
                6 = Pakuwon Station
                7 = Kayungyun Station
                8 = Keputih Terminal

                Input starting station:\s""");
        int source = Integer.parseInt(input);

        String input2 = JOptionPane.showInputDialog("""
                0 = Galaxy Mall Station
                1 = Kertajaya Station
                2 = Park N Ride Station
                3 = Cosmopolis Station
                4 = UHT Station
                5 = ITS Station
                6 = Pakuwon Station
                7 = Kayungyun Station
                8 = Keputih Terminal

                Input destination station:\s""");
        int dest = Integer.parseInt(input2);


        printBFS(adj, source, dest, vertices);

    }

    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int source, int destination, int vertices, int[] pred, int[] distance)
    {

        LinkedList<Integer> queue = new LinkedList<Integer>();

        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++)
        {
            visited[i] = false;
            distance[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }


        visited[source] = true;
        distance[source] = 0;
        queue.add(source);

        while (!queue.isEmpty())
        {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++)
            {
                if (!visited[adj.get(u).get(i)])
                {
                    visited[adj.get(u).get(i)] = true;
                    distance[adj.get(u).get(i)] = distance[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));

                    if (adj.get(u).get(i) == destination)
                        return true;
                }
            }
        }
        return false;
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j)
    {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }

    private static void printBFS(ArrayList<ArrayList<Integer>> adj, int start, int destination, int vertices)
    {
        int[] pred = new int[vertices];
        int[] dist = new int[vertices];

        if (!BFS(adj, start, destination, vertices, pred, dist))
        {
            System.out.println("Given source and destination" + "are not connected");
            return;
        }

        LinkedList<Integer> path = new LinkedList<Integer>();
        int traverse = destination;
        path.add(traverse);

        while (pred[traverse] != -1)
        {
            path.add(pred[traverse]);
            traverse = pred[traverse];
        }

        String pathString = "";
        for (int i = path.size() - 1; i >= 0; i--)
            pathString = pathString.concat(" " + path.get(i));

        JOptionPane.showMessageDialog(null, "0 = Galaxy Mall Station\n" +
                "1 = Kertajaya Station\n" +
                "2 = Park N Ride Station\n" +
                "3 = Cosmopolis Station\n" +
                "4 = UHT Station\n" +
                "5 = ITS Station\n" +
                "6 = Pakuwon Station\n" +
                "7 = Kayungyun Station\n" +
                "8 = Keputih Terminal\n\n" + "The shortest route will take " + dist[destination] + " stops" + "\n\n It passes the following stops:\n" + pathString);
    }
}