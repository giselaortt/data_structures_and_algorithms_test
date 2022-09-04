//Use of algorithm:
//  In order to guarantee that the search would occur in log(n) it was necessary to create a
//  balanced tree. since there is no further insertion or deletion the tree structure will remain the same.
//  only the keys of the elements will change.
//  That is why there is no need to update the balancing and the median value.
//  Because the tree is constructed in such a way that the right subtree and the left subtree have the same size (or 1 of diference),
//  the root will always be the median value.
//
//Analyses of complexity:
//
// Tree search is done with O( n ) = log(n)
// Median access is done on O( n ) = 1
// Tree building is done on O( n ) = n, because each node needs to be accessed only once.
// Ordering of the array of all elements before building the tree is done on O( n ) = n * log n.
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

    void display(){
        System.out.printf("<%c, %d>\n", this.information, this.key);
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


class BinaryTree{
    Node root;
    int numberOfElements;

    BinaryTree( int[] keys, char[] informations, int size ){
        this.numberOfElements = size;
        Node elements[] = new Node[ size ];

        for( int i=0; i<size; i++ ){
            elements[i] = new Node( informations[i], keys[i] );
        }

        //Sorting happens in O(n) = n*log n
        Arrays.sort( elements );
        int medianPosition = (int)((size)/2);
        this.root = elements[ medianPosition ];
        this.root.left = constructSubtree( elements, 0, medianPosition );
        this.root.right = constructSubtree( elements, medianPosition+1, numberOfElements );
    }

    //RECURSSION
    //  Recursive construction of the tree happens in O(n) = n.
    //  Using inclusive interval on the beggining and exclusive interval on the end.
    //  This function devides an array on two based on the median value,
    //  the median value enters as the root, it then recursivelly constructs the left array 
    //  as a tree and places it as the left child, then it recursivelly constructs the right
    //  array as the right subtree.
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

    //This is a wrapping function to improve usability of the following recursive function.
    public char get( int key ){
        Node temporary = this.get( key, this.root );
        return temporary.item.information;
    }

    //RECURSSION
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

    //RECURSSION
    private void printInOrder( Node node ){
        if( node == null )
            return;
        printInOrder( node.left );
        node.item.display();
        printInOrder( node.right );
    }

    public void printNodeWithMedianKey(){
        this.root.item.display();
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
        int key = this.produceRandomKey();
        char info = this.produceRandomCharacter();
        Pair answer = new Pair( key, info );

        return answer;
    }
}


class RTS{

    BinaryTree holder;
    SensorSimulator sensor;
    int seed;
    //k is the variable as defined in the problem description.
    int k;

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

    //Generates n new pairs until one of them is accepted as an insertion.
    private Pair insertRandomPair(){
        Pair randomPair = this.sensor.produceRandomPair();
        int newKey = randomPair.key;
        char newInfo = randomPair.information;

        if( holder.isPresent( newKey ) ){
            this.holder.replaceInformation( newKey, newInfo );
        }

        return randomPair;
    }

    void kRandomPairs(){
        for( int i=0; i< this.k; i++ ){
             Pair pair = this.insertRandomPair();
             pair.display(); //I understood we should print the k generations, is that correct ?
        }
        System.out.println();
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


