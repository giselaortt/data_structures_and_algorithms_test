//Use of algorithm:
//
//
//Analyses of complexity:
//
//
import java.util.Locale;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.LinkedList;
import java.io.FileReader; 
import java.util.Scanner;
import java.util.HashSet;
import java.lang.Comparable;


class Solver{
    int numberOfItems;
    int[] weights;
    final int capacity = 23;
    String[] names;

    //the other option would be to use a boolean array,and a variable to count
    //how many objects there are left. but this is more pretty.
    HashSet< Integer > wasUsed;

    //For this use-case a LinkedList is the ideal data structure.
    //First because several calls to list.add() function will happen,
    //which is more efficient on a LinkedList. Second because
    //we will make no calls to an intermediate element without the need
    //to acess the previous ones. The ArrayList would be less efficient on the add calls,
    //and the Hash option would take more memory.
    LinkedList< LinkedList <Integer> > solution;

    Solver( int[] weights, String[] names, int size ){
        this.numberOfItems  = size;
        this.solution = new LinkedList< LinkedList<Integer> >();
        this.weights = weights;
        this.names = names;
        this.wasUsed = new HashSet<Integer>();
    }

    // This is a wrapping function for the recursive knapsack solution. 
    private void knapsack(){
        int[][] intermediateStates = new int[ numberOfItems+1 ][ capacity+1 ];
        int i = this.numberOfItems;
        int w = this.capacity;
        knapsack( i, w, intermediateStates );

        LinkedList<Integer> currentSet  = new LinkedList<Integer>();
        reconstruct( intermediateStates, i, w, currentSet );
        this.solution.add( new LinkedList< Integer >() );

        for( int itemIndex : currentSet ){
            // adding current set to the set of used values
            this.wasUsed.add( itemIndex );
            // passing current set of items to the set of items already used.
            this.solution.getLast().add( itemIndex );
        }
    }

    private int knapsack( int i, int w, int[][] matrix ){
        if( i==0 || w==0 )
            return 0;

        if( matrix[i][w] != 0 )
            return matrix[i][w];

        if( this.wasUsed.contains( i ) || this.weights[i-1] > w )
            matrix[i][w] =  knapsack(i-1, w, matrix); 

        else if( this.weights[i-1] + knapsack( i-1, w-this.weights[i-1], matrix ) >  knapsack( i-1, w, matrix ) )
           matrix[i][w] = this.weights[i-1] + knapsack( i-1, w-this.weights[i-1], matrix ); 

        else
            matrix[i][w] = knapsack( i-1, w, matrix );

        return matrix[i][w];
    }

    //reverse the algorithm in order to know wich items were included on current iteration from knapsack.
    // - i -> objects from 1 to i are available
    // - w -> capacity of suitcase
    //
    //how the subproblems are defined:
    //
    //how the solutions to these subproblems are defined:
    //
    //how the solutions are calculated in the base case:
    //
    //how the solutions are calculated in the general case:
    //
    private void reconstruct( int[][] matrix, int i, int w, LinkedList<Integer> currentSet ){
        if( i==0 || w==0 )
            return;
        if( matrix[i][w] > matrix[i-1][w] ){
            currentSet.add( i );
            reconstruct( matrix, i-1, w - this.weights[i-1], currentSet );
        } else {
            reconstruct( matrix, i-1, w, currentSet );
        }
    }

    // while the number of packed objects is smaller than the total of items,
    // keep packing.
    public void solve(){
       while( this.wasUsed.size() < this.numberOfItems )
           this.knapsack();
    }
    
    public void display(){
        int i=0;
        for( LinkedList<Integer> suiticase : this.solution ){
            i++;
            System.out.printf( "Suitcase: %d\n", i );
            int suiticaseWeight = 0;
            for( int objectIndex : this.solution.get(i) ){
                System.out.printf("%s %d\n", names[objectIndex-1], weights[objectIndex-1] );
                suiticaseWeight += weights[objectIndex-1];
            }
            System.out.printf( "Residual capacity: %d\n", this.capacity - suiticaseWeight );
            System.out.println();
        }
    }
}


class Algorithm4{

    public static void main( String args[] ) throws Exception {
        Locale.setDefault(Locale.US);
        int numberOfItems;
        FileReader reader = new FileReader( args[0] );
        Scanner scam = new Scanner( reader );
        numberOfItems = scam.nextInt();
        scam.nextLine();
        String[] objectsNames = new String[numberOfItems];
        int[] objectWeights = new int[numberOfItems];
        String input_value;

        for( int i=0; i<numberOfItems; i++ ){
            String[] line = scam.nextLine().trim().replaceAll(" +", " ").split(" ");
            objectsNames[i] = line[0];
            objectWeights[i] = Integer.parseInt(line[1]);
        }
        
        Solver solver = new Solver( objectWeights, objectsNames, numberOfItems );
        solver.solve();
        solver.display();
    }
}



