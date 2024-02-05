package RatMaze;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DFS_Class {
    private char[][] graph;             // The graph representation
    private int N;                      // Dimensions of the graph
    private boolean[][] traversed;     // Array to keep track of visited nodes
    private int MAX_THREADS = 1024;     // Maximum number of threads allowed
    private boolean found = false;      // Flag to indicate if a solution is found
    private ArrayList<Pair> Answer;     // List to store the answer path

// Constructor for initializing the DFS_Class
public DFS_Class(char[][] indexes, int dimensions) {
    graph = indexes;
    this.N = dimensions;
    this.traversed = new boolean[N][N];
    ArrayList<Pair> resultPairs = new ArrayList<>();
    DFS(0, 0, resultPairs);

    // Wait until all threads finish before proceeding
    while (Thread.activeCount() > 3) {

    }

    // If a solution is found, update the graph and print the result
    if (found) {
        for (Pair pair : Answer) {
            graph[pair.First][pair.Second] = '2';
        }
        graph[0][0] = '2';
        getAnswer();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(graph[i][j]);
            System.out.println("\n");
        }
    } else {
        System.out.println("Not found!");
    }
}

// Pair class representing a pair of integers
private static class Pair {
    public int First;
    public int Second;

    Pair(int x, int y) {
        First = x;
        Second = y;
    }
}

// Threaded DFS implementation
private class RealDFS implements Runnable {
    int x, y;
    ArrayList<Pair> answerList;

    RealDFS(int x, int y, ArrayList<Pair> answerList) {
        this.x = x;
        this.y = y;
        this.answerList = answerList;
    }

    @Override
    public void run() {
        try {
            DFS(x, y, answerList);
        } catch (InterruptedException ex) {
            Logger.getLogger(DFS_Class.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void DFS(int x, int y, ArrayList<Pair> answerList) throws InterruptedException {
        // If the solution is already found by another thread, return
        if (found) {
            return;
        }

        // If the destination is reached, update the Answer and set found to true
        if (graph[x][y] == '2') {
            Answer = answerList;
            found = true;
            return;
        }

        // Explore the next possible moves in the graph
        if (!(x + 1 == N) && !(traversed[x + 1][y]) && !(graph[x + 1][y] == '0')) {
            ArrayList<Pair> tempAnswerList = new ArrayList<>(answerList);
            tempAnswerList.add(new Pair(x + 1, y));

            traversed[x + 1][y] = true;

            // Create a new thread if the maximum thread count is not reached
            if (Thread.activeCount() < MAX_THREADS) {
                RealDFS threadDFS = new RealDFS(x + 1, y, tempAnswerList);
                Thread newThread = new Thread(threadDFS);
                newThread.run();  // Use start() instead of run() to create a new thread
            } else {
                DFS(x + 1, y, tempAnswerList);
            }
        }

        if (!(y + 1 == N) && !(traversed[x][y + 1]) && !(graph[x][y + 1] == '0')) {
            ArrayList<Pair> tempAnswerList = new ArrayList<>(answerList);
            tempAnswerList.add(new Pair(x, y + 1));
            traversed[x][y + 1] = true;

            // Create a new thread if the maximum thread count is not reached
            if (Thread.activeCount() < MAX_THREADS) {
                RealDFS threadDFS = new RealDFS(x, y + 1, tempAnswerList);
                Thread newThread = new Thread(threadDFS);
                newThread.run();  // Use start() instead of run() to create a new thread
            } else {
                DFS(x, y + 1, tempAnswerList);
            }
        }
    }
}



    // Our init DFS method, invoked only once in most cases when MAXTHREAD's value is more than 2

    private void DFS(int x, int y, ArrayList<Pair> ans) {
        if (found) {
            return;
        }
        if (graph[x][y] == '2') {
            Answer = ans;
            found = true;
            return;
        }
        if (!(x + 1 == N) && !(traversed[x + 1][y]) && !(graph[x + 1][y] == '0')) {

            ArrayList<Pair> ans1 = new ArrayList<>(ans);
            ans1.add(new Pair(x + 1, y));

            traversed[x + 1][y] = true;
            if (Thread.activeCount() < MAX_THREADS) {
                RealDFS g1 = new RealDFS(x + 1, y, ans1);
                Thread t1 = new Thread(g1);

                t1.run();

            } else {
                DFS(x + 1, y, ans1);
            }
        }
        if (!(y + 1 == N) && !(traversed[x][y + 1]) && !(graph[x][y + 1] == '0')) {
            ArrayList<Pair> ans1 = new ArrayList<>(ans);
            ans1.add(new Pair(x, y + 1));
            traversed[x][y + 1] = true;
            if (Thread.activeCount() < MAX_THREADS) {
                RealDFS g1 = new RealDFS(x, y + 1, ans1);
                Thread t1 = new Thread(g1);
                t1.run();

            } else {
                DFS(x, y + 1, ans1);
            }
        }
    }

    public char[][] getAnswer() {
        while (Thread.activeCount() > 3) {

        }

        // Check var found first before calling the function
        return graph;
    }
    public boolean isFound()
    {
        return found;
    }
}