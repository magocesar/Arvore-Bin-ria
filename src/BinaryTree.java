public class BinaryTree{

    private Node root;

    public BinaryTree(){
        this.root = null;
    }

    public BinaryTree(int data){
        this.root = new Node(data);
    }

    public void insert(int data){
        if(this.root == null){
            this.root = new Node(data);
        }else{	
            insert(this.root, data);
        }
    }
    
    // Method used to insert data into the binary tree comparing the values 
    // and throwing it or to the right or to the left based on its value 
    private void insert(Node parent, int data){
        if(data < parent.data){
            if(parent.left == null){
                parent.left = new Node(data, parent);
            }else{
                insert(parent.left, data);
            }
        }else{
            if(parent.right == null){
                parent.right = new Node(data, parent);
            }else{
                insert(parent.right, data);
            }
        }
    }

    // Public method of search
    public Node search(int data){
        return search(this.root, data);
    }

    //Private method of search that uses recusivity to find the data and the side
    // of the node that the data is linked to
    private Node search(Node node, int data){
        if(node == null || node.data == data){
            return node;
        }

        if(data < node.data){
            return search(node.left, data);
        }

        return search(node.right, data);
    }


    //Finds the node with the smallest key greater than the x's key
    private Node Sucessor(Node x){
        if(x.right != null){
            return Min(x.right);
        }

        Node y = x.parent;
        while(y != null && x == y.right){
            x = y;
            y = y.parent;
        }

        return y;
    }

    
    //Finds the node with the largest key smaller than the x's key
    private Node Predecessor(Node x){
        if(x.left != null){
            return Max(x.left);
        }

        Node y = x.parent;
        while(y != null && x == y.left){
            x = y;
            y = y.parent;
        }

        return y;
    }

    
    //Finds the node with the smallest key
    private Node Min(Node x){
        while(x.left != null){
            x = x.left;
        }

        return x;
    }

    
    //Finds the node with the largest key
    private Node Max(Node x){
        while(x.right != null){
            x = x.right;
        }

        return x;
    }

    //Public method used to delete a given value of the node from the binary tree 
    public void delete(int data){
        Node node = search(data);
        if(node != null){
            delete(node);
        }
    }

    //Private method to delete the value of the node from tne binary tree, and realocate 
    //the others node in order 
    private void delete(Node node){
        if(node.left == null){
            transplant(node, node.right);
        }

        else if(node.right == null){
            transplant(node, node.left);
        }

        else{
            Node aux = Sucessor(node);

            if(aux.parent != node){
                transplant(aux, aux.right);
                aux.right = node.right;
                aux.right.parent = aux;
            }

            transplant(node, aux);
            aux.left = node.left;
            aux.left.parent = aux;
        }
    }

    private void transplant(Node u, Node v){
        //Replace u with v
        //If u is the root, v becomes the root
        if(u.parent == null){
            this.root = v;
        }
        
         //u is not the root

        //If u is the left child, v becomes the left child
        else if(u == u.parent.left){
            u.parent.left = v;
        }
        
        //u is the right child, v becomes the right child
        else{
            u.parent.right = v;
        }

        //Change value of v's parent
        //If v is not null, v's parent becomes u's parent
        if(v != null){
            v.parent = u.parent;
        }
    }

    //Public method used to print the binary tree 
    public void print(){
        print(this.root, 10);
    }

    //Private method used to print the binary tree 
    private void print(Node node, int space){
        if(node == null){
            return;
        }

        space += 10;

        //Process right child first
        print(node.right, space);

        //Print current node after space
        System.out.println();
        for(int i = 10; i < space; i++){
            System.out.print(" ");
        }
        System.out.print(node.data + "\n");


        //Process left child
        print(node.left, space);
    }

}


class Node{
    int data;
    Node parent;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
        this.left = this.right = this.parent = null;
    }

    public Node(int data, Node parent){
        this.data = data;
        this.parent = parent;
        this.left = this.right = null;
    }
}