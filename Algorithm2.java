/*
Use of structures:

     2 hash tables that receive linked lists were used. one hashtable receives the key
     and returns the info and the other receives the info and returns the key.
     two tables are necessary and it is the only way in order for both accesses to happen in constant time
     (the access from  key and the access from information).
     They receive linked lists in order to keep several infos for the same key
     ( or several keys for the same info ).

     The hashtables were chosen because we need the access to happen on constant time.
     Another option would be to use an array of linked lists, that would have the same access time.
     But i consider a hashtable to be more readable.


Analyses of complexity:

     Insertion is done in O( n ) = 1 (access of element of Hash tables + append on a linked list).

*/
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

    //complexity: O(n) = 1
    //Allocates the LinkedList if none is available for the given pair, and adds the new pair to both tables.
     void addPair( int selector, char information ){
           if( this.isPresent( selector )==false )
                this.selectormaps.put( selector, new LinkedList< Character >() );
           this.selectormaps.get( selector ).add( information );
           if( this.isPresent( information ) == false )
               this.infomaps.put( information, new LinkedList< Integer >() );
           this.infomaps.get( information ).add( selector );
    }

    //complexity: O(n) = 1 + number of pairs having the value of the given selector, satisfing condition 1.
    //checks if the given pair exists on the system.
     void isPresent( int selector, char information ){
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

    //checks if the selector exists on the system.
    boolean isPresent( int selector ){
        return this.selectormaps.containsKey(selector);
    }

    //checks if the information exists on the system.
    boolean isPresent( char information ){
        return this.infomaps.containsKey(information);
    }

    //This function prints all the pairs that contain the given information.
    //complexity: O(n) = 1 + number of pairs having the value of the given information, satisfing condition 2.
     void displayPerInformation( char information ){
        if( this.isPresent( information ) == false ){
            System.out.println("empty list");
            return;
        }
        //int size = this.infomaps.get(information).size();
        for( int key : this.infomaps.get(information) )
            System.out.printf( "%d ", key );
        System.out.println();
    }

    //This function prints all the pairs that contain the given selector.
    //complexity: O(n) = 1 + number of pairs having the value of the given selector, satisfing condition 3.
     void displayPerSelector( int selector ){
        if( this.isPresent( selector ) == false ){
            System.out.println("empty list");
            return;
        }
        int size = this.selectormaps.get( selector ).size();
        for( char c : this.selectormaps.get(selector) )
            System.out.printf("%c ", c );
        System.out.println();
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
        solver.displayPerInformation( 'a' );
        solver.displayPerInformation( 'd' );
        solver.displayPerInformation( 's' );
        solver.displayPerInformation( 'f' );
        solver.displayPerInformation( 'u' );
        System.out.println();
        solver.displayPerSelector( 27 );
        solver.displayPerSelector( 30 );
        solver.displayPerSelector( 4 );
    }
}

