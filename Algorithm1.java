//
//Use of algorithm:
//
//
//Analyses of complexity:
//

import java.util.Locale;


class Node{

    public Node left;
    public Node right;
    public Node parent;
    public char information;
    public int key;

    void Node( char information, int key ){
        this.information = information;
        this.key = key;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

}


class BinaryTree{

    public Node root;

    public void BinaryTree(){
        this.root = null;
    }

    public void insert( char information, int key ){
    
        Node node = new Node( information, key );
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
    

    public void remove( int key ){
        Node node = this.get( key );
        //TODO
    }

    public Node get( int key ){
        
        return this.get( key, this.root );

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
}


class Algorithm1{

    public static void main( String args[] ){
        
        Locale.setDefault(Locale.US);
        System.out.println("Hallo world");

    }

}




