//Use of algorithm:
//
//  In order to guarantee that the search would occur in log(n) it was necessary to create a
//  balanced tree. since there is no further insertion or deletion the tree structure will remain the same.
//  only the keys of the elements will change.
//  That is why there is no need to update the balancing and the median value.
//  Because the tree is constructed in such a way that the right subtree and the left subtree have the same size (or 1 of diference),
//  the root will always be the median value.
//
//
//Analyses of complexity:
//
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
}

class Node implements Comparable<Node> {
    Node left;
    Node right;
    Pair item;

    Node( char information, int key ){
        this.left = null;
        this.right = null;
        this.item = new Pair( key, information );
    }

    //this function is necessary because we will sort the nodes on the tree.
    //like this we can sort the keys and the characters ar the same time and do not lose
    //the correlation between them.
    @Override
    public int compareTo( Node otherNode ){
        return (int)( this.item.key - otherNode.item.key );
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

    //  RECURSSION
    //  Recursive construction of the tree happens in O(n) = n.
    //  Using inclusive interval on the beggining and exclusive interval on the end.
    //  This function devides an array on two based on the median value,
    //  the median value enters as the root, it then recursivelly constructs the left array 
    //  as a tree and places it as the left child, then it recursivelly constructs the right
    //  array as the right subtree.
    static Node constructSubtree( Node elements[], int begin, int end ){
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
    char get( int key ){
        Node temporary = this.get( key, this.root );
        return temporary.item.information;
    }

    //RECURSSION
    //this function returns the node that contains the key passed, if it is present on the current
    //node or one of the subtrees. otherwise it returns null.
    Node get( int key, Node node ){
        if( node == null )
            return null;
        if( node.item.key == key )
            return node;
        if( node.item.key > key )
            return this.get( key, node.left );
        return this.get( key, node.right );
    }

    //checks if the key exists on this entire tree.
    boolean isPresent( int key ){
        if( this.get( key, this.root ) == null )
            return false;
        return true;
    }

    //replace the information of an existing node if a node with the same key exists. if
    //the node key is not present then it does not insert this information.
    //it returns weather the information was replaced or not.
    boolean replaceInformation( Pair pair ){
        Node node = this.get( pair.key, this.root );
        if( node == null )
            return false;
        node.item.information = pair.information;
        return true;
    }

    //this is a wrapping function to improve usability for the following function.
    void printInOrder(){
        printInOrder( this.root );
    }

    //RECURSSION
    void printInOrder( Node node ){
        if( node == null )
            return;
        printInOrder( node.left );
        System.out.printf("<%c, %d>\n", node.item.information, node.item.key);
        printInOrder( node.right );
    }
 }

//this class will produce the random values as if they were comming from the sensor
class SensorSimulator{
    Random rand;
    final int seed;
    final int rangeFloor = 11, rangeRoof = 27;

    SensorSimulator( int seed ){
        this.seed = seed;
        this.rand = new Random();
        this.rand.setSeed( seed );
    }

    //This method produces the variable k as described in the description of the problem. 
    //Note that there are 9 Odd numbers between 11 and 27.
    int produceRandomK(){
        return (rand.nextInt( 9 )*2) + rangeFloor;
    }

    //this method produces a character 
    char produceRandomCharacter(){
         return (char)(rand.nextInt(26) + 'a');
    }

    //This method produces an integer as described in the problem description, so an integer
    //between 1 and 100.
    int produceRandomKey(){
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
    void kRandomPairs(){
        int numberOfInsertions = 0;
        while( numberOfInsertions < this.k ){
            Pair randomPair = this.sensor.produceRandomPair();
            boolean wasInserted = this.holder.replaceInformation( randomPair );
            if( wasInserted ){
                numberOfInsertions++;
                System.out.printf( "<%c, %d>\n", randomPair.information, randomPair.key );
            }
        }
        //I understood we should print the k generations, is that correct ?
        System.out.println();
    }

    void display(){
        this.holder.printInOrder();
        System.out.printf("K = %d\n", this.k );
        System.out.printf("Initial seed: %d\n", this.seed );
    }

    void printMeddianValue(){
        System.out.println( "median value: " );
        //because the root has the same number of childs at each side it is always the median value
        Node median = this.holder.root;
        System.out.printf("<%c, %d>\n", median.item.information, median.item.key);
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
