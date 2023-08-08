package avengers;
/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {
	
    public static void main (String [] args) {
    
       if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }


        String locateTitanInputFile = args[0];
        String locateTitanOutputFile = args[1];

        StdIn.setFile(locateTitanInputFile);

        int number = StdIn.readInt(); 
        int[] generalNumber = new int[number];
        double[] functionalityNumber = new double[number];

        int[][] edgeNumbers = new int[number][number];

        for(int i = 0; i < number; i++){
            generalNumber[i] = StdIn.readInt();
            functionalityNumber[i] = StdIn.readDouble();

        }
        
        for (int row = 0; row < number; row++){
            for (int column = 0; column < number; column++){
                edgeNumbers[row][column] = (int)(StdIn.readInt() / (functionalityNumber[row]* functionalityNumber[column]));


            }
                    
        }

        int [] minCost = dijkstra(edgeNumbers);
        StdOut.setFile(locateTitanOutputFile);  
        StdOut.print(minCost[number-1]); //altered fro nuber - 1 to this
    }

    private static int[] dijkstra (int[][] edge){

        int [] mincost = new int[edge.length];
        boolean [] dijkstraSet = new boolean[edge.length];

        for (int i = 0; i < mincost.length; i++){
            if (i == 0){
                mincost[i] = 0; 
            }
            else
            {
                mincost[i] = Integer.MAX_VALUE;
            }
        
        }




        for (int j = 0; j < mincost.length ; j++){
            int currentSource = getMinCostNode(mincost, dijkstraSet);

            dijkstraSet[currentSource] = true;
            for (int i = 0; i < edge.length; i++){
                if(mincost[currentSource] != Integer.MAX_VALUE && mincost[i] > (mincost[currentSource] + edge[currentSource][i]) && edge[currentSource][i] != 0 && !dijkstraSet[i])                
                {
                    mincost[i] = mincost[currentSource] + edge[currentSource][i]; 
                }

            } 



        }



        return mincost;
    }


    private static int getMinCostNode (int[] cost, boolean[] ds) {
        int min = Integer.MAX_VALUE;
        int minNode = -1;

        for (int i = 0; i < cost.length; i++) {
            if (!ds[i] && cost[i] < min) {
                min = cost[i];
                minNode = i;
            }
        }

        return minNode;
    }


}
