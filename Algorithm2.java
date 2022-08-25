//Use of algorithm:
//
//
//Analyses of complexity:
//
//

import java.util.Locale;
import java.io.FileReader; 
import java.util.ArrayList;
import java.util.HashMap;


class Area51{
    int selectorBound;
    HashMap< Integer, ArrayList<Character>> selectormaps = new HashMap< Integer, ArrayList<Character>>();
    HashMap< Character, ArrayList<Integer>> infomaps = new HashMap< Character, ArrayList<Integer>>();

    Area51(){
        this.selectormaps = new HashMap< Integer, ArrayList<Character>>();
        this.infomaps = new HashMap< Character, ArrayList<Integer>>();
    }

    //
    public void addPair( int selector, char information ){
           if( this.isPresent( selector )==false )
                this.selectormaps.put( selector, new ArrayList<Character>() );
           this.selectormaps.get( selector ).add( information );
           if( this.isPresent( information ) == false )
               this.infomaps.put( information, new ArrayList<Integer>() );
           this.infomaps.get( information ).add( selector );
    }

    //
    public void isPresent( int selector, char information ){
        if( this.isPresent( selector ) == false || this.isPresent( information ) == false ){
            System.out.println("element not present");
            return;
        }
        int size = this.infomaps.get( information ).size();
        for( int i=0; i<size; i++ ){
            if( this.infomaps.get( information ).get(i) == selector ){
                System.out.println("element present");
                return;
            }
        }
        System.out.println("element not present");
    }

    private boolean isPresent( int selector ){
        return this.selectormaps.containsKey(selector);
    }

    private boolean isPresent( char information ){
        return this.selectormaps.containsKey(information);
    }

    //
    public void searchPerInfo( char information ){
        if( this.isPresent( information ) == false )
            System.out.println("empty list");
        else{
            int size = this.infomaps.get(information).size();
            for( int i=0; i<size; i++ )
                System.out.println( this.infomaps.get(information).get(i) );
        }
    }

    //
    public void searchPerSelector( int selector ){
        if( this.isPresent( selector ) == false )
            System.out.println("empty list");
        else {
            int size = this.selectormaps.get( selector ).size();
            for( int i=0; i<size; i++ )
                System.out.println( this.selectormaps.get( selector ).get( i ) );
        } 
    }
}

class Algorithm2{

    public static void main( String args[] ) throws Exception {
        
        Locale.setDefault(Locale.US);
        FileReader reader = new FileReader( args[0] );
        int n = reader.read();
        int k = reader.read();
        char information;
        int selector;
        Area51 solver = new Area51( );
        for( int i=0; i<n; i++ ){
            selector = reader.read();
            information = (char)reader.read();
            solver.addPair( selector, information );
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
