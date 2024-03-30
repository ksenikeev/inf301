import java.util.function.Consumer;

public class BTreeMain {
    private static Node root;
    private static int count;
    public static void main(String[] args) {

        Long timeStart = System.nanoTime();

        root = new Node(8);
        Node node5 = new Node(5);
        Node node11 = new Node(11);
        root.left = node5;
        root.right = node11;
        Node node3 = new Node(3);
        Node node6 = new Node(6);
        node5.left = node3;
        node5.right = node6;
        node3.left = new Node(1);
        node11.left = new Node(10);
        node11.right = new Node(12);

        showReccurent1(root, node -> System.out.println(node.value));
        Long timeEnd = System.nanoTime();
        System.out.println((timeEnd - timeStart));
        System.out.println(count);
    }

    public  static void showReccurent1(Node node, Consumer<Node> consumer) {
        if (node.left != null) showReccurent1(node.left, consumer);
        consumer.accept(node);count++;
        if (node.right != null) showReccurent1(node.right, consumer);
    }

    public static class Node {

        public Node(Integer v) {
            this.value = v;
        }

        public Node left;
        public Node right;
        public Integer value;
    }
}
