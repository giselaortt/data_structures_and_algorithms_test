//
//Use of algorithm:
//
//
//Analyses of complexity:
//

import java.util.Locale;


class Pair{
    short selector;
    char information;
    void Pair( short selector, char information ){
        this.selector = selector;
        this.information = information;
    }
}


class Area51{
    
    short selectorBound;
    ArrayList<char>[] mappingPerInfos;
    ArrayList<int>[] mappingPerSelector;

    public void Area51( short selectorBound ){}

    public void addPair( short selector, char info ){}

    public boolean isPresent( Pair pair ){
   
        System.out.println("element present");
        System.out.println("element nor present");

    }

    public static boolean isPresent( short selector, char info ){}

    //Required to be O(1)
    public static void searchPerInfo( char info ){
    
        System.out.println("empty list");
    }

    //Required to be O(1)
    public static void searchPerSelector( short selector ){
    
    
        System.out.println("empty list");
    }



}

class Algorithm2{

    public static void main( String args[] ) throws Exception {
        
        Locale.setDefault(Locale.US);
        FileReader reader = new FileReader( args[0] );
        short n = reader.read();
        short k = reader.read();
        char info;
        short selector;
        Area51 solver = new Area51( k );
        for( int i=0; i<n; i++ ){
            selector = reader.read();
            info = reader.read();
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


