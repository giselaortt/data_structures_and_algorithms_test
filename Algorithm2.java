//Use of structures:
//
// 2 hash tables that receive linked lists.
// we need two hashs in order for both accesses to happen in O(1)
// (the access from  key and the access from information).
// They receive linked lists in order to keep several infos for the same key
// ( or vice versa ).
//
//Analyses of complexity:
//
// Insertion is done in O( n ) = 1 (access of element of Hash tables + append on a linked list).
//
//

import java.util.Scanner;
import java.util.Locale;
import java.io.FileReader; 
import java.util.LinkedList;
import java.util.HashMap;


class Area51{
    int selectorBound;
    HashMap< Integer, LinkedList< Character >> selectormaps = new HashMap< Integer, LinkedList< Character >>();
    HashMap< Character, LinkedList< Integer >> infomaps = new HashMap< Character, LinkedList< Integer >>();

    Area51(){
        this.selectormaps = new HashMap< Integer, LinkedList< Character >>();
        this.infomaps = new HashMap< Character, LinkedList< Integer >>();
    }

    //O(n) = 1.
    //Allocates the LinkedList if none is available for the given pair, and adds the new pair
    //to both tables.
    public void addPair( int selector, char information ){
           if( this.isPresent( selector )==false )
                this.selectormaps.put( selector, new LinkedList< Character >() );
           this.selectormaps.get( selector ).add( information );
           if( this.isPresent( information ) == false )
               this.infomaps.put( information, new LinkedList< Integer >() );
           this.infomaps.get( information ).add( selector );
    }

    //O(n) = 1 + number of pairs having the value of the given selector, satisfing condition 1.
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
        return this.infomaps.containsKey(information);
    }

    //O(n) = 1 + number of pairs having the value of the given information, satisfing condition 2.
    public void searchPerInfo( char information ){
        if( this.isPresent( information ) == false ){
            System.out.println("empty list");
        } else {
            int size = this.infomaps.get(information).size();
            for( int i=0; i<size; i++ )
                System.out.printf( "%d ", this.infomaps.get(information).get(i) );
            System.out.println();
        }
    }

    //O(n) = 1 + number of pairs having the value of the given selector, satisfing condition 3.
    public void searchPerSelector( int selector ){
        if( this.isPresent( selector ) == false )
            System.out.println("empty list");
        else {
            int size = this.selectormaps.get( selector ).size();
            for( int i=0; i<size; i++ )

                System.out.printf("%c ", this.selectormaps.get( selector ).get( i ) );
            System.out.println();
        } 
    }
}

class Algorithm2{

    public static void main( String args[] ) throws Exception {
        
        Locale.setDefault(Locale.US);
        FileReader reader = new FileReader( args[0] );
        Scanner scan = new Scanner( reader );
        int n = scan.nextInt();
        int k = scan.nextInt();
        scan.nextLine();
        char information;
        int selector;
        Area51 solver = new Area51( );

        for( int i=0; i<n; i++ ){
            String line = scan.nextLine();
            String[] tokens = line.replaceAll("\n", "" ).split(" ");
            selector = Integer.parseInt(tokens[0]);
            information = (char)tokens[1].charAt(0);
            solver.addPair( selector, information );
        }

        solver.isPresent( 27, 'c' );
        solver.isPresent( 30, 'c' );
        solver.isPresent( 44, 'c' );
        System.out.println();
        solver.searchPerInfo( 'a' );
        solver.searchPerInfo( 'd' );
        solver.searchPerInfo( 's' );
        solver.searchPerInfo( 'f' );
        solver.searchPerInfo( 'u' );
        System.out.println();
        solver.searchPerSelector( 27 );
        solver.searchPerSelector( 30 );
        solver.searchPerSelector( 4 );
    }
}
