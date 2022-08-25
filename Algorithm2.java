//
//Use of algorithm:
//
//
//Analyses of complexity:
//

import java.util.Locale;
import java.io.FileReader; 
import java.util.ArrayList;


class Pair{
    int selector;
    char information;
    Pair( int selector, char information ){
        this.selector = selector;
        this.information = information;
    }
}


class Area51{
    
    int selectorBound;
    ArrayList<Character>[] mappingPerInfos;
    ArrayList<Integer>[] mappingPerSelector;

    Area51( int selectorBound ){}

    public void addPair( int selector, char info ){}

    public boolean isPresent( Pair pair ){
   
        System.out.println("element present");
        System.out.println("element nor present");
        return true;
    }

    public static boolean isPresent( int selector, char info ){
        return true;
    }

    //Required to be O(1)
    public static void searchPerInfo( char info ){
    
        System.out.println("empty list");
    }

    //Required to be O(1)
    public static void searchPerSelector( int selector ){
    
    
        System.out.println("empty list");
    }



}

class Algorithm2{

    public static void main( String args[] ) throws Exception {
        
        Locale.setDefault(Locale.US);
        FileReader reader = new FileReader( args[0] );
        int n = reader.read();
        int k = reader.read();
        char info;
        int selector;
        Area51 solver = new Area51( k );
        for( int i=0; i<n; i++ ){
            selector = reader.read();
            info = (char)reader.read();
            solver.addPair( selector, info );
        }

        solver.isPresent( 27, 'c' );
        solver.isPresent( 30, 'c' );
        solver.isPresent( 44, 'c' );

        solver.searchPerInfo( 'a' );
        solver.searchPerInfo( 'd' );
        solver.searchPerInfo( 's' );
        solver.searchPerInfo( 'f' );
        solver.searchPerInfo( 'u' );

        solver.searchPerSelector( 27 );
        solver.searchPerSelector( 30 );
        solver.searchPerSelector( 4 );
    }

}




