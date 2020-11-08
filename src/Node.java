public class Node {

  String question;
  String animal;
  Node left;
  Node right;

  public Node(String question) {
    this.question = question;
    this.animal = null;
    this.left = null;
    this.right = null;
  }

  public Node(String question, String animal) {
    this.animal = animal;
    this.question = "Is it a " + animal + "?";
    this.left = null;
    this.right = null;
  }

  public void setLeft(Node node) {
    left = node;
  }

  public void setRight(Node node) {
    right = node;
  }

  public Node getLeft() {
    return left;
  }

  public Node getRight() {
    return right;
  }

  public String getQuestion() {
    return question;
  }

  public String getAnimal(){
    return animal;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  // prints the node, then its children (left to right child priority)
  void printPreorder(Node node) {
    if (node == null) {
      return;
    }
    System.out.print(node.question + " ");
    printPreorder(node.left);
    printPreorder(node.right);
  }

  // prints the children, then the node (left to right child priority)
  void printPostorder(Node node) {
    if (node == null) {
      return;
    }
    printPostorder(node.left);
    printPostorder(node.right);
    System.out.print(node.question + " ");
  }

  // prints the left child, the node, then the right child (left to right child priority)
  void printInOrder(Node node) {
    if (node == null) {
      return;
    }
    printInOrder(node.left);
    System.out.print(node.question + " ");
    printInOrder(node.right);
  }

  public String print() {
    return this.print("", true, "");
  }

  public String print(String prefix, boolean isTail, String sb) {
    if (right != null) {
      right.print(prefix + (isTail ? "│ " : " "), false, sb);
    }
    System.out.println(prefix + (isTail ? "\\--" : "/--") + question);
    if (left != null) {
      left.print(prefix + (isTail ? " " : "│ "), true, sb);
    }
    return sb;
  }

}
