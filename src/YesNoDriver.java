import java.util.Scanner;

public class YesNoDriver {

  public static void main(String[] args) {

    // control variable for loop
    boolean programRunning = true;
    // initialize binary tree used in the main method
    BTree aiTree = setupTree();
    // initialize scanner used in the main method for user input
    Scanner sc = new Scanner(System.in);

    try {
      while (programRunning) { // continuously loop program until exited
        System.out.println("Input x to exit \nInput d to display tree"
            + " \nInput any other key to continue");
        String userInput = sc.nextLine();

        // switch statement decides the program's next action based on the user's input
        switch (userInput) {
          // exits the program
          case "x":
            programRunning = false;
            break;

          // displays the binary tree in its current state
          case "d":
            aiTree.print();
            break;

          // runs the Yes/No game
          default:
            System.out.println(aiTree.getCurrent().getQuestion()); // get question of root
            String yesOrNo = sc.nextLine(); // take user answer
            Node previousQuestion = null; // set default for previous node that the tree was on

            // while there are still nodes left to go to, travel through the tree
            while(aiTree.getCurrent().getLeft() != null){
              // if the current node has no animal, make it the previous node
              if(aiTree.getCurrent().getAnimal() == null) {
                previousQuestion = aiTree.getCurrent();
              }
              // traverse the tree, changing what the current node is
              aiTree.setCurrent(aiTree.moveCurrent(aiTree.getCurrent(), yesOrNo));
              // after moving, print the current question
              System.out.println(aiTree.getCurrent().getQuestion());
              yesOrNo = sc.nextLine(); // take the user's input
            }

            // Add a new question if the final answer is no. Print a victory message elsewise.
            if(yesOrNo.toUpperCase().equals("N")){
              aiTree.addNewQuestion(aiTree, previousQuestion);
            } else{
              System.out.println("Yes! I win! Your animal was a " + aiTree.getCurrent().getAnimal()
                  + "! Let's play again! \n");
            }

            aiTree.setCurrent(aiTree.getRoot()); // reset the current node so user can play again
        }
      }
    } finally{ // close scanners after the program finishes
      sc.close();
    }

  }

  // sets up the initial structure of the binary tree used in the program
  public static BTree setupTree(){
    BTree bTree = new BTree();
    Node root = new Node("Is it a mammal?");
    Node node2 = new Node("", "Dog");
    Node node3 = new Node("", "Fish");

    bTree.setRoot(root);
    bTree.setCurrent(root);
    root.setRight(node2);
    root.setLeft(node3);

    return bTree;
  }
}
