//Use of algorithm:
//  The AVL data structure was used, with the treshold for balancing minimal. 
//  This was the only solution to guarantee that the search and insertion would occur in log(n).
//
//Analyses of complexity:
//
//  Tree insertion and search is done with O( n ) = log(n)
//  Median access is done on O = 1

import java.util.Locale;
import java.util.Random;
import java.util.Arrays;


class Node{

    public Node left;
    public Node right;
    public Node parent;
    public char information;
    public short key;
    public int height;
    public int factor;

    void Node( char information, short key ){
        this.information = information;
        this.key = key;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.height = 0;
    }
}

class BinaryTree{

    Node root;
    int numberOfElements;
    Node median;

    public void BinaryTree(){
        this.root = null;
        this.numberOfElements = 0;
        this.median = 0;
    }

    public void BinaryTree( int[] key_arr, char[] info_arr, int size ){
        this.root = null;
        this.numberOfElements = size;

        for( int i=0; i<size; i++ )
            this.insert( info_arr[i], key_arr[i] );

        java.util.Array.sort( key_arr );
        int medianValue = key_arr[ (int)(size - 1)/2 ]; 
        this.median = this.get( median );
    }

    public void insert( char information, int key ){
        Node node = new Node( information, key );
        this.numberOfElements += 1;
        if( this.root == null ){
            this.root = node;
            return;
        }

        if( root.left == null && node.key < root.left ){
            root.left = node;
            node.parent = root;
            return;
        }

        if( root.right == null && node.key > root.right ){
            root.right = node;
            node.parent = root;
            return;
        }

        this.insert( node, root );
        this.updateMedianAfterInsertion( key );
        this.numberOfElements+=1;
        this.updateHeights( node );
        this.rebalance( node );
    }

    private void insert( Node to_be_inserted, Node current_node ){
        if( to_be_inserted.key > current_node.key ){
            if( current_node.right != null )
                return insert( to_be_inserted, current_node.right );
            current_node.right = to_be_inserted;
            to_be_inserted.parent = current_node;
        } else {
            if( current_node.left != null )
                return insert( to_be_inserted, current_node.left );
            current_node.left = to_be_inserted;
            to_be_inserted.parent = current_node;
        }
    }
   
    //The insertion operation on a binary tree is only guaranteed to be log(n) when
    //the tree is balanced. So in order to keep with the requirement 3 this function is crucial. 
    private void rebalance( Node node ){
    
    }

    //     y                               x
    //    / \     Right Rotation          /  \
    //   x   T3   - - - - - - - >        T1   y
    //  / \       < - - - - - - -            / \
    // T1  T2     Left Rotation            T2  T3
    private void rightRotation( Node node ){
    
    }

    private void leftRotation( Node node ){
    
    
    }

    private void getNodeHeight( Node node ){
        if( node == null )
            return 0;
        return node.height;
    }

    private void updateHeights( Node node ){
        while( node != null ){
            node.height = Math.max( this.getNodeHeight( node.left ), this.getNodeHeight( node.right ) ) 
            node = node.parent;
        }
    }

    public char get( int key ){
        Node temporary = this.get( key, this.root );
        return temporary.information;
    }

    private Node get( short key, Node node ){
        if( node == null )
            return null;

        if( node.key == key )
            return node;

        if( node.key > key )
            return this.get( key, node.left );

        return this.get( key, node.right );
    }

    public boolean isPresent( short key ){
        if( this.get( key, this.root ) == null )
            return false;
        return true;
    }

    public void replaceInformation( short key, char information ){
        Node node = this.get( key );
        node.information = information;
    }

    public void printInOrder(){
        printInOrder( this.root );
    }

    private void printInOrder( Node node ){
        printInOrder( node.left );
        System.out.printf( "< %d, %c >\n", node.key, node.information );
        printInOrder( node.right );
    }

    public void printNodeWithMedianKey(){
        System.out.printf( "< %d, %c >\n", this.medianNodePointer.key, this.medianNodePointer.information );
    }

    //
    //
    //
    private void moveMedianToTheRight(){
        Node node = this.median;
        if( node.right != null ){
            node = node.right;
            while( node.left != null )
                node = node.left;
            this.median = node;
            return;
        }

        if( node.parent.key > node.key ){
            this.median = node.parent;
            return;
        }
        
        while(  node.key > node.parent.key  ){
            node = node.parent;
        }
        node = node.parent;
        node = node.right;
        while( node.left != null ){
            node = node.left;
        }
        this.median = node;
    }

    //
    //
    //
    private void moveMedianToTheLeft(){
        Node node = this.median;
        if( node.left != null ){
            node = node.left;
            while( node.right != null )
                node = node.right;
            this.median = node;
            return;
        }
    
        if( node.parent.key > node.key ){
            this.median = node.parent;
            return;
        }
    
        while(  node.key < node.parent.key  ){
            node = node.parent;
        }
        node = node.parent;
        node = node.left;
        while( node.right != null ){
            node = node.right;
        }
        this.median = node; 
    }

    private boolean isOdd( int number ){
        return ( number % 2 != 0 )
    }

    private boolean isEven( int number ){
        return ( number % 2 == 0 )
    }

    private void updateMedianAfterInsertion( int insertedValue ){
        int currentMedianValue;
        if( ( insertedValue > currentMedianValue ) && isEven(this.numberOfElements) ){
            this.moveMedianToTheRight();
        } else if( this.isOdd( this.numberOfElements ) && (insertedValue < currentMedianValue ) ){
            this.moveMedianToTheLeft();
        }
    }
}


class RST{

    BinaryTree holder;
    Random rand;
    int seed, rangeFloor, rangeRoof ;
    int k;

    public static void sensorSimulator( int seed ){
        this.seed = seed;
        this.rand = new Random();
        this.rand.setSeed( seed );
        //
    }

    boolean isOdd( int number ){

        return number % 2;

    }

    public void generateInitialPool(){
        this.k = this.generateRandomK();
        char[] initialPoolOfInfos = new String[ this.k ];
        int[] initialPoolOfKeys = new Int[ this.k ];

        for( int i=0; i<this.k; i++ ){
            initialPoolOfInfos[i] = generateRandomChar();
            initialPoolOfKeys[i] = this.rand.nextInt( 100 );
        }
   
        this.holder = new BinaryTree( initialPoolOfInfos, initialPoolOfKeys );     
    }

    private char generateRandomChar(){
    
         return (char)(rand.nextInt(26) + 'a');
    
    }

    private int generateRandomK(){
        int k = rand.nextInt( 27 - 11 ) + 11;
        if( !isOdd( k ) )
            k += 1;

        return k;
    }

    public void newPairGeneration(){
        //boolean wasThisPairAccepted = false; 
        //while( wasThisPairAccepted == false ){
            int newKey = this.generateRandomInt;
            int newInfo = this.generateRandomChar;
            if( holder.isPresent( newKey ) ){
        //        wasThisPairAccepted = true;
                holder.replaceInformation( newKey, newInfo );
            }
        }
    }

//    public void newPairKTimes(){
//        for( int i=0; i< this.k; i++ )
//            this.newPairGeneration();
//    }

    public void print(){
        this.holder.printInOrder();
        System.out.printf("K = %d\n", this.k );
        System.out.printf("Initial seed: %d\n", this.seed );
    }
}


class Algorithm1{

    public static void main( String args[] ){
        
        Locale.setDefault(Locale.US);
        RTS rts = new  RTS( 570049 );
        rts.generateInitialPool();
        rts.newPairKTimes();

    }
}

