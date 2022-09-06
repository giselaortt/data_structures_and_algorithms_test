/*
Use of algorithm:

    Algorithm 4 is an adaptation of the widely known rucksack problem. 
    In this case, the objects do not have different values, so instead
    of implementing the maximization of values, the solution was changed to
    maximize the total weight of the backpack.

Analyses of complexity:

    Defining n as the number of objects and w as the maximum capacity
    for 1 suitcase. For 1 suitcase it was used the widely known algorithm of the
    knapsack, that has complexity O(n) = n *  w. 
    Since the problem contains at most n suitcases (one per object if they could
    not possibly fit together), and for each suitcase there will be a call for
    the conventional knapsack problem, this means that the fina solution has
    complexity O(n) = n * n * w.
    On  the reconstruction of the knapsack set of items: O(n) = w + n.
    (because it does not go for every weight and object, and stops when any reaches 0).


* Definition of subproblems:

    The first subproblem consists of maximizing the use for 1 suitcase. Secondly we
    must reconstruct the set of items that were included on the suitcase for each 
    iteration. Finally, repeat the algorithm until all the items have been placed.

* How the solutions to these subproblems are defined:

    The knapsack solution is based on a knapsack of smaller capacity and a knapsack
    problem with a smaller set of items.
    To get the set of items the algorithm is reversed engineered with the values produced.

* How the solutions are calculated in the base case:

    When the current capacity or the number of items are zero, the solution is also zero.
    When the current item does not fit on the current capacity, the algorithm keeps 
    the solution for the same capacity without the current item.

*  How the solutions are calculated in the general case:

    The algorithm chooses between including the current item in the sack or not, 
    depending on which option produces the smallest residual space.
*/
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

    //This is a wrapping function for the recursive knapsack solution. 
    //it defines the variables as they should be on the start of the recursion. 
    void knapsack(){
        int[][] intermediateStates = new int[ numberOfItems+1 ][ capacity+1 ];
        int i = this.numberOfItems;
        int w = this.capacity;
        knapsack( i, w, intermediateStates );

        LinkedList<Integer> currentSet  = new LinkedList<Integer>();
        reconstruct( intermediateStates, i, w, currentSet );
        this.solution.add( new LinkedList< Integer >() );

        for( int itemIndex : currentSet ){
            // adding current set to the set of used objects
            this.wasUsed.add( itemIndex );
            // passing current set of items to the solution set.
            this.solution.getLast().add( itemIndex );
        }
    }
    
    //this is a recurssive function that optimizes the use of a suitcase,
    //the calculation is based on a backpack without the current item and on smaller suitcase.
    int knapsack( int i, int w, int[][] matrix ){
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
    
    //this is a recurssive function that will reverse the previous function in order to find out which items have
    //been added on the final solution
    void reconstruct( int[][] matrix, int i, int w, LinkedList<Integer> currentSet ){
        if( i==0 || w==0 )
            return;
        if( matrix[i][w] > matrix[i-1][w] ){
            currentSet.addFirst( i );
            reconstruct( matrix, i-1, w - this.weights[i-1], currentSet );
        } else {
            reconstruct( matrix, i-1, w, currentSet );
        }
    }
    
    //while the number of packed objects is smaller than the total of items, keep packing.
    void solve(){
        int i=0;
       while( i < 10 && this.wasUsed.size() < this.numberOfItems ){
           i++;
           this.knapsack();
       }
    }

    //this function prints the final answer
    void display(){
        int i=0;
        for( LinkedList<Integer> suitcase : this.solution ){
            i++;
            System.out.printf( "Suitcase: %d\n", i );
            int suitcaseWeight = 0;
            for( int objectIndex : suitcase ){
                System.out.printf("%s %d\n", names[objectIndex-1], weights[objectIndex-1] );
                suitcaseWeight += weights[objectIndex-1];
            }
            System.out.printf( "Residual capacity: %d\n", this.capacity - suitcaseWeight );
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
