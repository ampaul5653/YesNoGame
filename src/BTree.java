import java.util.Scanner;

public class BTree {

  private Node root;
  private Node currentNode;

  public BTree() {
    root = null;
  }

  public boolean search(String question) {
    return search(root, question);
  }

  // searches for a node with a particular value
  private boolean search(Node node, String question) {
    if (node.getQuestion().equals(question)) {
      return true;
    }
    if (node.getLeft() != null) {
      if (search(node.getLeft(), question)) {
        return true;
      }
    }
    if (node.getRight() != null) {
      if (search(node.getRight(), question)) {
        return true;
      }
    }
    return false;
  }

  //traverses left down the tree if "Y" is entered, right if "N" is entered
  public Node moveCurrent(Node currentNode, String yesOrNo){
    // go to the left if the guess is wrong & the tree isn't at its end
    if(currentNode.getLeft() != null && yesOrNo.toUpperCase().equals("N")){
      return currentNode.getLeft();
      // go to the right if the guess is right & the tree isn't at its end
    } else if(currentNode.getLeft() != null && yesOrNo.toUpperCase().equals("Y")){
      return currentNode.getRight();
      // don't move if there's nowhere else to go (failsafe)
    } else{
      return currentNode;
    }
  }

  // adds a new question if the computer fails to guess the animal the person was thinking of
  public void addNewQuestion(BTree bTree, Node previousQuestion){
    Scanner sc = new Scanner(System.in);
    //prompt user for the animal they were thinking of
    System.out.println("I give up! You win. What were you thinking of?");
    String newAnimal = sc.nextLine();
    //prompt user for their animal's identifier
    System.out.println("What question would you ask to tell the difference between a "
        + newAnimal + " and a " + bTree.currentNode.animal + "?");
    String newQuestion = sc.nextLine();
    //prompt user for the answer to their identifier
    System.out.println("And what would your answer for this question be if you were thinking of "
        + "a " + newAnimal + "?");
    String newAnswer = sc.nextLine();

    // create node holding the new animal
    Node newAnimalNode = new Node("", newAnimal);

    // create node holding the new question
    Node newQuestionNode = new Node(newQuestion);

    // rearrange tree structure

    if(previousQuestion.left == bTree.currentNode){ // if the current node is to the left of the previous one

      //put the new question to the left of the previous question
      previousQuestion.setLeft(newQuestionNode);

      // then, set up the answers to the newly-added questions according to the user's input
      if(newAnswer.toUpperCase().equals("N")){ // if the answer is no
        newQuestionNode.setLeft(newAnimalNode); // set the new animal to the left
        newQuestionNode.setRight(bTree.currentNode); // set the old animal to the right
      } else{ // if the answer is yes
        newQuestionNode.setRight(newAnimalNode); // set the new animal to the right
        newQuestionNode.setLeft(bTree.currentNode); // set the old animal to the left
      }
    } else{ // if the current node is to the right of the previous one

      //put the new question to the right of the previous one
      previousQuestion.setRight(newQuestionNode);

      // then, set up the answers to the newly-added questions according to the user's input
      if(newAnswer.toUpperCase().equals("N")){ // if the answer is no
        newQuestionNode.setLeft(newAnimalNode); // set the new animal to the left
        newQuestionNode.setRight(bTree.currentNode); // set the old animal to the right
      } else{ // if the answer is yes
        newQuestionNode.setRight(newAnimalNode); // set the new animal to the right
        newQuestionNode.setLeft(bTree.currentNode); // set the old animal to the left
      }

    }
  }

  // calls printInOrder from node
  public void printInOrder() {
    root.printInOrder(root);
  }

  // calls printPreOrder from node
  public void printPreOrder() {
    root.printPreorder(root);
  }

  // calls printPostOrder from node
  public void printPostOrder() {
    root.printPostorder(root);
  }

  public Node getRoot() {
    return root;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public int countNodes() {
    return countNodes(root);
  }

  private int countNodes(Node node) {
    int count = 1;
    if (node == null) {
      return 0;
    } else {
      count += countNodes(node.getLeft());
      count += countNodes(node.getRight());
      return count;
    }
  }

  // displays the tree in diagram format
  public void print() {
    root.print();
  }

  public Node getCurrent() {
    return currentNode;
  }

  public void setCurrent(Node node) {
    this.currentNode = node;
  }

  public void setRoot(Node root) {
    this.root = root;
  }

}
