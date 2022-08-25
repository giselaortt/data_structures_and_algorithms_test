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
    public int key;
    public int height;
    public int factor;

    Node( char information, int key ){
        this.information = information;
        this.key = key;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.height = 0;
    }

    public int calculateFactor(){
        if( this.right == null && this.left == null )
            return 0;
        if( this.right == null )
            return -1*this.left.height;
        if( this.left == null )
            return this.right.height;
        return ( this.right.height - this.left.height );
    }

}

class BinaryTree{

    Node root;
    int numberOfElements;
    Node median;

    BinaryTree(){
        this.root = null;
        this.numberOfElements = 0;
        this.median = null;
    }

    BinaryTree( int[] key_arr, char[] info_arr, int size ){
        this.root = null;
        this.numberOfElements = size;

        for( int i=0; i<size; i++ )
            this.insert( info_arr[i], key_arr[i] );

        Arrays.sort( key_arr );
        int medianValue = key_arr[ (int)(size - 1)/2 ]; 
        this.median = this.get( medianValue, this.root );
    }

    public void insert( char information, int key ){
        Node node = new Node( information, key );
        this.numberOfElements += 1;
        if( this.root == null ){
            this.root = node;
            return;
        }

        if( root.left == null && node.key < root.left.key ){
            root.left = node;
            node.parent = root;
            return;
        }

        if( root.right == null && node.key > root.right.key ){
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
            if( current_node.right != null ){
                insert( to_be_inserted, current_node.right );
                return;
            }
            current_node.right = to_be_inserted;
            to_be_inserted.parent = current_node;
        } else {
            if( current_node.left != null ){
                insert( to_be_inserted, current_node.left );
                return;
            }
            current_node.left = to_be_inserted;
            to_be_inserted.parent = current_node;
        }
    }
   
    //The insertion operation on a binary tree is only guaranteed to be log(n) when
    //the tree is balanced. So in order to keep with the requirement 3 this function is crucial. 
    private void rebalance( Node node ){
        Node leaf = node;
        while( node != null ){
            if( node.calculateFactor() >= 2 ){
                if( node.left.calculateFactor() == 1 ){
                    this.rightRotation( node );
                } else {
                    this.leftRotation( node.left );
                    this.rightRotation( node );
                }
                break;
            } else if ( node.calculateFactor() <= 2 ){
                 if( node.right.calculateFactor() == -1 ){
                    this.leftRotation( node );
                } else {
                    this.rightRotation( node.right );
                    this.leftRotation( node );
                }
                break;
            }
            node = node.parent;
        }
       this.updateHeights( leaf ); 
    }

    //     y                               x
    //    / \     Right Rotation          /  \
    //   x   T3   - - - - - - - >        T1   y
    //  / \       < - - - - - - -            / \
    // T1  T2     Left Rotation            T2  T3
    private void rightRotation( Node node ){
        Node newParent = node.left;
        newParent.parent = node.parent;
        if( node == this.root )
            this.root = newParent;
        else {
            if( node.parent.key > node.key )
                node.parent.left = newParent;
            else
                node.parent.right = newParent;
        }
        node.parent = newParent;
        node.left = newParent.right;
        if( newParent.right != null )
            newParent.right.parent = node;
        newParent.right = node;
        this.updateHeights( node );
    }

    private void leftRotation( Node node ){
        Node newParent = node.right;
        newParent.parent = node.parent;
        if( node == this.root )
            this.root = newParent;
        else{
            if( node.parent.key > node.key )
                node.parent.left = newParent;
            else
                node.parent.right = newParent;
        }
        node.parent = newParent;
        node.right = newParent.left;
        if( newParent.left != null )
            newParent.left.parent = node;
        newParent.left = node;
        this.updateHeights( node );
    }

    private int getNodeHeight( Node node ){
        if( node == null )
            return 0;
        return node.height;
    }

    private void updateHeights( Node node ){
        while( node != null ){
            node.height = Math.max( this.getNodeHeight( node.left ), this.getNodeHeight( node.right ) ); 
            node = node.parent;
        }
    }

    public char get( int key ){
        Node temporary = this.get( key, this.root );
        return temporary.information;
    }

    private Node get( int key, Node node ){
        if( node == null )
            return null;

        if( node.key == key )
            return node;

        if( node.key > key )
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
        System.out.printf( "< %d, %c >\n", this.median.key, this.median.information );
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
        return ( number % 2 != 0 );
    }

    private boolean isEven( int number ){
        return ( number % 2 == 0 );
    }

    private void updateMedianAfterInsertion( int insertedValue ){
        int currentMedianValue = this.median.key;
        if( ( insertedValue > currentMedianValue ) && isEven(this.numberOfElements) ){
            this.moveMedianToTheRight();
        } else if( this.isOdd( this.numberOfElements ) && (insertedValue < currentMedianValue ) ){
            this.moveMedianToTheLeft();
        }
    }
}


class RTS{

    BinaryTree holder;
    Random rand;
    int seed, rangeFloor, rangeRoof ;
    int k;

    RTS( int seed ){
        this.seed = seed;
        this.rand = new Random();
        this.rand.setSeed( seed );
    }

    public void generateInitialPool(){
        this.k = this.generateRandomK();
        char[] initialPoolOfInfos = new char[ this.k ];
        int[] initialPoolOfKeys = new int[ this.k ];

        for( int i=0; i<this.k; i++ ){
            initialPoolOfInfos[i] = generateRandomChar();
            initialPoolOfKeys[i] = this.rand.nextInt( 100 );
        }
   
        this.holder = new BinaryTree( initialPoolOfKeys, initialPoolOfInfos, k );     
    }

    private char generateRandomChar(){
    
         return (char)(rand.nextInt(26) + 'a');
    
    }

    private int generateRandomK(){
        int k = rand.nextInt( 27 - 11 ) + 11;
        if( k % 2 == 0 )
            k += 1;

        return k;
    }

    public void newPairGeneration(){
        //boolean wasThisPairAccepted = false; 
        //while( wasThisPairAccepted == false ){
            int newKey = this.rand.nextInt();
            char newInfo = this.generateRandomChar();
            if( holder.isPresent( newKey ) ){
        //        wasThisPairAccepted = true;
                this.holder.replaceInformation( newKey, newInfo );
            }
        //}
    }

    public void newPairKTimes(){
        for( int i=0; i< this.k; i++ )
            this.newPairGeneration();
    }

    public void print(){
        this.holder.printInOrder();
        System.out.printf("K = %d\n", this.k );
        System.out.printf("Initial seed: %d\n", this.seed );
    }
}


class Algorithm1{

    public void main( String args[] ){
        
        Locale.setDefault(Locale.US);
        RTS rts = new  RTS( 570049 );
        rts.generateInitialPool();
        rts.newPairKTimes();

    }
}

