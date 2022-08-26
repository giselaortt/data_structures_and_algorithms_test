//Use of algorithm:
//
//
//Analyses of complexity:
//
//
import java.util.Locale;
import java.util.ArrayList;
import java.io.FileReader; 
import java.util.Scanner;


class BackpackSolver{
    int numberOfObjects;
    String[] names;
    int[] weights;
    ArrayList< ArrayList <Integer>> solution;

    public void BackpackSolver( int[] weights, String[] names, int size ){
        this.names = names;
        this.weights = weights;
        this.numberOfObjects  = size;
        this.solution = new ArrayList< ArrayList<Integer> >();
    }


    //how the subproblems are defined;
    //how the solutions to these subproblems are defined
    //how the solutions are calculated in the base case
    //how the solutions are calculated in the general case
    public void solve(){}

    
    public static void printSolution(){
        for( int i=0; i< this.solution.size(); i++ ){
            System.out.printf( "Suitcase: %d\n", i );
            int suiticaseWeight = 0;
            for( int j=0; j< this.solution.get(i).size(); j++ ){
                int objectIndex = solution.get(i).get(j);
                System.out.printf("%s %d\n", names[objectIndex], weights[objectIndex] );
                suiticaseWeight += weights[objectIndex];
            }
            System.out.println( "Residual capacity: " + suiticaseWeight );
            System.out.println();
        }
    }
}


class Algorithm4{

    public static void main( String args[] ) throws Exception {
        Locale.setDefault(Locale.US);
        int numberOfObjects;
        FileReader reader = new FileReader( args[0] );
        Scanner scam = new Scanner( reader );
        numberOfObjects = scam.nextInt();
        scam.nextLine();
        String[] objectsNames = new String[numberOfObjects];
        int[] objectWeights = new int[numberOfObjects];
        String input_value;

        for( int i=0; i<numberOfObjects; i++ ){
            String[] line = scam.nextLine().trim().replaceAll(" +", " ").split(" ");
            objectsNames[i] = line[0];
            objectWeights[i] = Integer.parseInt(line[1]);
            System.out.printf( "%s,%d\n", objectsNames[i], objectWeights[i] );
        }
        
        BackpackSolver solver = new BackpackSolver( objectWeights, objectsNames, numberOfObjects );
    }
}
