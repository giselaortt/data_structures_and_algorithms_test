//Use of algorithm:
//
//
//Analyses of complexity:
//

import java.util.Locale


class Solver(){

    ArrayList answer;
    int numberOfIterations;

    public static void Solver(){}

    public static void answer(){}

    public static void displayResult(  ){
        System.out.printf( "Number of trips: %d\n", );
        for( int i=0; i< ;i++ ){
            System.out.printf("Total load per trip number %d: %d\n");
        }
        System.out.printf("Number of iterations of the algorithm: %d\n", numberOfIterations );
        System.out.printf("Computational cost of the algorithm: O(n)\n");
    }

}


class Algorithm3{

    public static void main( String args[] ){
        
        Locale.setDefault(Locale.US);
        FileReader reader = new FileReader( args[0] );
        int ferryCapacity = reader.read();
        int numberOfVehicles = reader.read();
        int[] weightsPerVehicle = new int[ numberOfVehicles ];
        for( int i=0; i<numberOfVehicles; i++ ){
            weightsPerVehicle = reader.read();
        }
        
        Solver solution = new Solver(  );
        solution.answer();
        solution.displayResult();

    }

}
