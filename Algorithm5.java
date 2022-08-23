//Use of algorithm:
//
//
//Analyses of complexity:
//

import java.util.Locale;
import java.util.ArrayList;
import java.io.FileReader; 
import java.util.Scanner;
import java.lang.reflect.Method;


//There are 2 ways to represent a Graph, eighter as adjacency list or a matrix. Because we do not know wether the Graph is dense,
//it makes sense to use an adjacency list, to decrease time and space complexity for the cases when the graph is sparse, and have equal performance
//when the graph is dense. 
class Graph{

    ArrayList< ArrayList< Integer > > edges;

    public Graph( int number_of_vertexes ){
        edges = new ArrayList< ArrayList< Integer > >();
        for( int i=0; i<number_of_vertexes; i++ ){
            edges.add( new ArrayList< Integer >() );
        } 
    }

    public void addEdge( int vertex, int other_vertex ){
        edges.get(vertex).add( other_vertex );
    }

    //Implementation of Djakstra`s algorithm.
    //How to pass a function as a parameter and receive it here ??
    public static int calculate_minimal_path( int initial_vertex, int target_vertex, Method wait ){
    
        wait.invoke();

        return 5;
    }

}


//Placeholder for the waiting function that will ge given.
class ExampleWaitingFunction{

    double waitingTime( int i, double time ){
        return 0.5;
    }

}



class Algorithm5{

    public static void main( String args[] ) throws Exception {
        
        Locale.setDefault(Locale.US);
        int number_of_vertexes;
        int number_of_edges;

        FileReader reader = new FileReader( args[0] );
        number_of_vertexes = reader.read();
        number_of_edges = reader.read();
        int first_vertex, second_vertex;
        Graph graph = new Graph( number_of_vertexes );
        for( int i=0; i<number_of_edges; i++ ){
            first_vertex = reader.read();
            second_vertex = reader.read();
            //change on the third parameter to pass the real waiting function.
            graph.addEdge( first_vertex, second_vertex, ExampleWaitingFunction::waitingTime );
        }
    
        System.out.println(graph.calculate_minimal_path(0, number_of_vertexes-1));
       

    }
}



