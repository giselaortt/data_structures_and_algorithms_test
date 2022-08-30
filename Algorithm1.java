//Use of algorithm:
//  In order to guarantee that the search would occur in log(n) it was necessary to create a
//  balanced tree. since there is no further insertion or deletion the tree structure will remain the same.
//  only the keys of the elements will change.
//  That is why there is no need to update the balancing and the median value.
//
//Analyses of complexity:
//
//  Tree insertion and search is done with O( n ) = log(n)
//  Median access is done on O( n )  = 1
//

import java.util.Locale;
import java.util.Random;
import java.util.Arrays;
import java.lang.Comparable;


class Pair{
    char information;
    int key;

    Pair( int key, char information ){
        this.key = key;
        this.information = information;
    }
}


class Node implements Comparable<Node> {
    public Node left;
    public Node right;
    Pair item;

    Node( char information, int key ){
        this.left = null;
        this.right = null;
        this.item = new Pair( key, information );
    }

    @Override
    public int compareTo( Node otherNode ){
        return (int)( this.item.key - otherNode.item.key );
    }

    public int getKey(){
        return this.item.key;
    }

    public char getInformation(){
        return this.item.information;
    }

    public void replaceInformation( char information ){
        this.item.information = information;
    }
}


//
//
//
class BinaryTree{
    Node root;
    int numberOfElements;

    BinaryTree( int[] keys, char[] informations, int size ){
        this.numberOfElements = size;
        Node elements[] = new Node[ size ];

        for( int i=0; i<size; i++ ){
            elements[i] = new Node( informations[i], keys[i] );
        }

        //
        //
        //
        Arrays.sort( elements );
        int medianPosition = (int)((size)/2);
        this.root = elements[ medianPosition ];
        this.root.left = constructSubtree( elements, 0, medianPosition );
        this.root.right = constructSubtree( elements, medianPosition+1, numberOfElements );
    }

    //Using exclusive interval on the end...
    private static Node constructSubtree( Node elements[], int begin, int end ){
        int middle = (int)( (end-begin)/2 + begin );

        if( end - begin == 0 )
            return null;

        if( end - begin == 1 )
            return elements[ begin ];
        
        if( end - begin == 2 ){
            elements[ end-1 ].left = elements[ begin ];
            return elements[ end-1 ];
        }
        Node rootOfSubtree = elements[ middle ];
        rootOfSubtree.left = constructSubtree( elements, begin, middle );
        rootOfSubtree.right = constructSubtree( elements, middle+1, end );

        return rootOfSubtree;
    }

    public char get( int key ){
        Node temporary = this.get( key, this.root );
        return temporary.item.information;
    }

    private Node get( int key, Node node ){
        if( node == null )
            return null;

        if( node.getKey() == key )
            return node;

        if( node.getKey() > key )
            return this.get( key, node.left );

        return this.get( key, node.right );
    }

    public boolean isPresent( int key ){
        if( this.get( key, this.root ) == null )
            return false;

        return true;
    }

    public void replaceInformation( int key, char information ){
        Node node = this.get( key, this.root );
        node.replaceInformation(information);
    }

    public void printInOrder(){
        printInOrder( this.root );
    }

    private void printInOrder( Node node ){
        if( node == null )
            return;
        printInOrder( node.left );
        System.out.printf( "< %d, %c >\n", node.getKey(), node.getInformation() );
        printInOrder( node.right );
    }

    public void printNodeWithMedianKey(){
        System.out.printf( "< %d, %c >\n", this.root.getKey(), this.root.getInformation() );
    }

 }


class SensorSimulator{
    Random rand;
    final int seed;
    final int rangeFloor, rangeRoof;

    SensorSimulator( int seed ){
        this.seed = seed;
        this.rangeFloor = 11;
        this.rangeRoof = 27;
        this.rand = new Random();
        this.rand.setSeed( seed );
    }

    //There are 9 Odd numbers between 11 and 27.
    int produceRandomK(){

        return (rand.nextInt( 9 )*2) + rangeFloor;

    }

    public char produceRandomCharacter(){
    
         return (char)(rand.nextInt(26) + 'a');
    
    }

    public int produceRandomKey(){

        return this.rand.nextInt( 100 )+1;

    }

    Pair produceRandomPair(){
        int key = this.rand.nextInt();
        char info = this.produceRandomCharacter();
        Pair answer = new Pair( key, info );

        return answer;
    }
}


//
//
//
class RTS{

    BinaryTree holder;
    SensorSimulator sensor;
    int k;
    int seed;

    RTS( int seed ){
        this.sensor = new SensorSimulator( seed );
        this.k = sensor.produceRandomK();
        this.seed = seed;
        this.createInitialTree();
    }

    void createInitialTree(){
        char[] initialPoolOfInfos = new char[ this.k ];
        int[] initialPoolOfKeys = new int[ this.k ];

        for( int i=0; i<this.k; i++ ){
            initialPoolOfInfos[i] = this.sensor.produceRandomCharacter();
            initialPoolOfKeys[i] = this.sensor.produceRandomKey();
        }
   
        this.holder = new BinaryTree( initialPoolOfKeys, initialPoolOfInfos, k );     
    }

    private boolean insertRandomPair(){
        int newKey = this.sensor.produceRandomKey(); 
        char newInfo = this.sensor.produceRandomCharacter();

        if( holder.isPresent( newKey ) ){
            this.holder.replaceInformation( newKey, newInfo );

            return true;
        }

        return false;
    }

    void kRandomPairs(){
        boolean sucess = false;
        for( int i=0; i< this.k; i++ )
            while( !sucess )
                sucess = this.insertRandomPair();
    }

    void display(){
        this.holder.printInOrder();
        System.out.printf("K = %d\n", this.k );
        System.out.printf("Initial seed: %d\n", this.seed );
    }

    void printMeddianValue(){
        System.out.println( "median value: " );
        this.holder.printNodeWithMedianKey();
    }
}


class Algorithm1{

    public static void main( String args[] ){
        Locale.setDefault(Locale.US);
        RTS rts = new  RTS( 570049 );
        rts.kRandomPairs();
        rts.display();
    }
}


