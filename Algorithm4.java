//Use of algorithm:
//
//
//Analyses of complexity:
//
import java.util.Locale;
import java.util.ArrayList;
import java.io.FileReader; 
import java.util.Scanner;


class BackpackSolver{
    int number_of_objects;
    String[] names;
    int[] weights;
    ArrayList<Integer> solution;

    public void BackpackSolver( int[] weights, String[] names, int size ){
        this.names = names;
        this.weights = weights;
        this.number_of_objects  = size;
    }

    public void fitObjects(){}
/*
    public static void printSolution(){
        for(){
            System.out.printf( "Suitcase: %d\n" );
            for(){
                System.out.printf("%s %d\n");
            }
            System.out.println();
        }
    }
    */
}


class Algorithm4{

    public static void main( String args[] ) throws Exception {
        Locale.setDefault(Locale.US);
        int number_of_objects;
        FileReader reader = new FileReader( args[0] );
        Scanner scam = new Scanner( reader );
        number_of_objects = scam.nextInt();
        scam.nextLine();
        String[] object_names = new String[number_of_objects];
        int[] object_weights = new int[number_of_objects];
        String input_value;

        for( int i=0; i<number_of_objects; i++ ){
            String[] line = scam.nextLine().trim().replaceAll(" +", " ").split(" ");
            object_names[i] = line[0];
            object_weights[i] = Integer.parseInt(line[1]);
            System.out.printf( "%s,%d\n", object_names[i], object_weights[i] );
        }
        
        BackpackSolver solver = new BackpackSolver( object_weights, object_names, number_of_objects );
    }
}
