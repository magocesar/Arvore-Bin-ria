public class programn {
    public static void main(String[] args){
        BinaryTree tree = new BinaryTree();
        tree.insert(50);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.print();
        System.out.println();
        tree.delete(50);
        tree.print();
    }
}
