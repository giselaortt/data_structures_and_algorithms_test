//Use of algorithm:
//
//
//Analyses of complexity:
//

import java.util.Locale;
import java.util.Random;

class Node{

    public Node left;
    public Node right;
    public Node parent;
    public char information;
    public short key;

    void Node( char information, short key ){
        this.information = information;
        this.key = key;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree{

    Node root;
    int numberOfElements;
    short medianValue;
    Node medianNodePointer;

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

        //sort arr in order to know who is the median...
        //

        this.median = 0;
        this.medianNodePointer = this.get( median );

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
    private void rebalance( Node node ){}

    private void rightRotation( Node node ){}

    private void leftRotation( Node node ){}

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

    private void moveMedianToTheRight(){}

    private void modeMedianToTheLeft(){}

    private void updateMedianAfterInsertion( int new_kwy ){
    
    
    }
}


class RST{

    BinaryTree holder;
    int seed;

    public static void sensorSimulator( int seed ){
        this.seed = seed;
    
    }

    public void generateInitialPool(){}

    public char generateRandomChar(){}

    public short generateRandomInt(){}

    public void newPairGeneration(){
        int newKey = this.generateRandomInt;
        int newInfo = this.generateRandomChar;
        if( holder.isPresent( newKey ) )
            holder.replaceInformation( newKey, newInfo );
    }

    public void newPairKTimes(){
        int k = this.generateRandomInt();
        for( int i=0; i<k; i++ )
            this.newPairGeneration();
    }

    public void print(){
        
    }

}


class Algorithm1{

    public static void main( String args[] ){
        
        Locale.setDefault(Locale.US);
        RTS rts = new  RTS( 570049 );
        

    }
}




