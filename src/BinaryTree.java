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

    public Node search(int data){
        return search(this.root, data);
    }

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

    public void delete(int data){
        Node node = search(data);
        if(node != null){
            delete(node);
        }
    }

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

    public void print(){
        PreeoderTreeWalk(this.root);
    }  

    private void PreeoderTreeWalk(Node x){
        if(x != null){
            System.out.println(x.data);
            PreeoderTreeWalk(x.left);
            PreeoderTreeWalk(x.right);
        }
    }

    private void InorderTreeWalk(Node x){
        if(x != null){
            InorderTreeWalk(x.left);
            System.out.println(x.data);
            InorderTreeWalk(x.right);
        }
    }

    private void PostorderTreeWalk(Node x){
        if(x != null){
            PostorderTreeWalk(x.left);
            PostorderTreeWalk(x.right);
            System.out.println(x.data);
        }
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