//Use of algorithm:
// Simple array.
//
//Analyses of complexity:
//  - O(n) where n stands for the number of loads
//  Because we iterate over the weights only one time

import java.util.Locale;
import java.util.LinkedList;
import java.io.FileReader;
import java.util.Scanner;

class Solver{

    LinkedList<Integer> answer;
    int numberOfIterations;
    int ferryCapacity;
    int numberOfVehicles;
    int[] weights;
    int numberOfTrips;

    Solver( int ferryCapacity, int numberOfVehicles, int[] weights ){
        this.numberOfIterations = 0;
        this.numberOfVehicles = numberOfVehicles;
        this.ferryCapacity = ferryCapacity;
        this.weights = weights;
        this.answer = new LinkedList<Integer>();
        this.numberOfTrips = 0;
    }

    //add the load to the current boat as long as possible, and start another boat otherwise.
    public void solve(){
        int currentLoad = 0;
        for( int i=0; i<this.numberOfVehicles; i++ ){
            if( currentLoad + this.weights[i] >= this.ferryCapacity ){
                this.answer.add( currentLoad );
                currentLoad = 0;
                this.numberOfTrips += 1;
            }
            currentLoad += this.weights[i];
        }
        if( currentLoad != 0 )
            this.answer.add( currentLoad );
    }

    public void displayResult(){
        System.out.printf( "Number of trips: %d\n", this.answer.size() );
        int i=0;
        for( int load : answer ){
            i++;
            System.out.printf("Total load per trip number %d: %d\n", i, load );
        }
        System.out.printf("Number of iterations of the algorithm: %d\n", this.numberOfVehicles );
        System.out.printf("Computational cost of the algorithm: O(n)\n");
    }
}


class Algorithm3{

    public static void main( String args[] ) throws Exception {
        
        Locale.setDefault(Locale.US);
        FileReader reader = new FileReader( args[0] );
        Scanner scan = new Scanner( reader );
        int ferryCapacity = scan.nextInt();
        int numberOfVehicles = scan.nextInt();
        int[] weightsPerVehicle = new int[ numberOfVehicles ];

        for( int i=0; i<numberOfVehicles; i++ ){
            weightsPerVehicle[i] = scan.nextInt();
        }
        
        Solver solution = new Solver( ferryCapacity, numberOfVehicles, weightsPerVehicle );
        solution.solve();
        solution.displayResult();
    }

}
