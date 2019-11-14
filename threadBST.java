import java.util.Stack;

public class BinarySearchTree {
    
    final static int RECURSIVE = 1;
    final static int ITERATIVE = 2;
    
    class Customer {
        
        private String Name;
        private String PhoneNumber;
        private int id;
        
        public Customer(int id, String name, String phonenum) {
            this.id = id;
            this.Name = name;
            this.PhoneNumber = phonenum;
        }
        
        public String getName() {
            return Name;
        }
        
        public String getPhone() {
            return PhoneNumber;
        }
        
        public int getId() {
            return id;
        }
    }

    class Node {
        
        Node lLink, rLink;
        Boolean lTag, rTag;
        Customer Info;
        int key;
        
        public Node(Customer c) {
            key = c.getId();
            this.Info = c;
            this.lLink = null;
            this.rLink = null;
        }
        
        public String getSCustomer() {
            return "Name: " + Info.getName() + ", Phone Number: " + Info.getPhone();   
        }

        public int getKey() {
            return Info.getId();
        }
    }

    Node myRoot;

    BinarySearchTree () {
        myRoot = null;
    }

    public static String customerName(Node treePoint) {
        return (treePoint.Info.getName());
    }
    
    public static String customerPhone(Node treePoint) {
        return (treePoint.Info.getPhone());
    }

    public void insert(int id, String custName, String custPhone) {
        if (myRoot == null) {
            myRoot = insertBinarySearchTree(myRoot, id, custName, custPhone);
        } else {
            Node node = insertBinarySearchTree(myRoot, id, custName, custPhone);
        }
    }

    private Node insertBinarySearchTree(Node root, int id, String custName, String custPhone) {
        Customer c = new Customer(id, custName, custPhone);
        
        Node newNode = new Node(c);
        
        newNode.lTag = true;
        newNode.rTag = true;

        if (root == null) {
            return newNode;
        }
        
        Node ptr = root;
        Node parent = null;

        while (ptr != null) {
            parent = ptr;

            if (id < ptr.getKey()) {
                if (!ptr.lTag){
                    ptr = ptr.lLink;
                } else break;
            } else {
                if (!ptr.rTag){
                    ptr = ptr.rLink;
                } else break;
            }
        }


        if (parent == null) {
            root = newNode;
            newNode.lLink = null;
            newNode.rLink = null;
        } else if (id < parent.getKey()) {
            newNode.lLink = parent.lLink;
            newNode.rLink= parent;
            parent.lTag = false;
            parent.lLink = newNode;
        } else {
            newNode.lLink = parent;
            newNode.rLink = parent.rLink;
            parent.rTag = false;
            parent.rLink = newNode;
        }

        return root;
    }

    private Node insertBinarySearchTreeRecursive(Node root, int id, String custName, String custPhone) {
        
        if (root == null) {
            Customer c = new Customer(id, custName, custPhone);
            return new Node(c);
        }
        
        if (id < root.getKey()) {
            root.lLink = insertBinarySearchTreeRecursive(root.lLink, id, custName, custPhone);

        } else if (id > root.getKey()) {
            root.rLink = insertBinarySearchTreeRecursive(root.rLink, id, custName, custPhone);
        } else {
            // value already exists
            return root;
        }
     
        return root;
    }

    public Node find(String custName, int option) {
        Node temp = null;
        if (option == RECURSIVE) {
            temp = findCustomerRecursive(myRoot, custName);
        } else if (option == ITERATIVE) {
            temp = findCustomerIterative(myRoot, custName);
        }
        
        return temp;
    }

    private Node findCustomerIterative(Node root, String customerName) {
        
        while (root != null) {
            
            if (root.rLink == null) {
                root = null;
                break;
            }

            root = root.rLink;
            
            if (customerName(root) == customerName) break;
            
        }
        
        return root;
    }
    
    private Node findCustomerRecursive(Node root, String customerName) {
        if (root != null) {
            return customerName(root) == customerName ? root : findCustomerRecursive(root.rLink, customerName);
        } else {
            return null;
        }
    }
    
    public void callInOrderSuccessor(Node treePoint) {
        if (treePoint == null) {
            treePoint = myRoot;
        }
        Node last = inOrderSuccesor(treePoint);
    }

    private Node inOrderSuccesor(Node treePoint) {
        
        while (treePoint != null) {
            
            if (treePoint.rLink == null) {
                System.out.println(customerName(treePoint) + ", " + customerPhone(treePoint));
                treePoint = null;
                break;
            }
            System.out.println(customerName(treePoint) + ", " + customerPhone(treePoint));
            treePoint = treePoint.rLink;
        }

        return treePoint;
    }
    
    public void callInOrder(Node treePoint) {
        if (treePoint == null) {
            treePoint = myRoot;
        }
        inOrder(treePoint);
    }

    private void inOrder(Node treePoint) {
        // System.out.println(treePoint.rLink.rLink.rLink.rLink.rLink.rLink.rLink.getKey());

        if (treePoint != null) {
            System.out.println(treePoint.key);
            // System.out.println(customerName(treePoint));
            inOrder(treePoint.rLink);
        }
    }

    public void callInOrderCyclic(Node treePoint) {
        if (treePoint == null) {
            treePoint = myRoot;
        }
        inOrderCyclic(treePoint);
    }

    private void inOrderCyclic(Node treePoint) {
        
        Node curr = myRoot;

        while (curr != treePoint) {
            curr = curr.rLink;
        }
        
        System.out.println(customerName(curr));
        
        if (curr.rLink != null) {
            curr = curr.rLink;
        } else {
            curr = myRoot;
        }

        while (curr != treePoint)
        {
            System.out.println(customerName(curr));
            if (curr.rLink == null) {
                curr = myRoot;
            } else {
                curr = curr.rLink;
            }
        }
        
    }

    public void callPreOrderTraversalIterative(Node treePoint) {
        if (treePoint == null) {
            treePoint = myRoot;
        }
        preOrderTraversalIterative(treePoint);
    }

    public void callPostOrderTraversalIterative(Node treePoint) {
        if (treePoint == null) {
            treePoint = myRoot;
        }
        postOrderTraversalIterative(treePoint);
    }

    public void callPreOrder(Node treePoint) {
        if (treePoint == null) {
            treePoint = myRoot;
        }
        preOrder(treePoint);
    }

    private Node preOrder(Node treePoint) {
        if (treePoint != null) {
            System.out.println(customerName(treePoint));
            preOrder(treePoint.lLink);
            preOrder(treePoint.rLink);
        }
        return treePoint;
    }

    public void callPreOrderThread(Node treePoint) {
        if (treePoint == null) {
            treePoint = myRoot;
        }
        preOrderThread(treePoint);
    }

    private Node preOrderSuccessor(Node treePoint) {
        if (treePoint.rTag) return treePoint.rLink;

        treePoint = treePoint.rLink;
        while (!treePoint.lTag)
            treePoint = treePoint.lLink;
        return treePoint;
    }

    private void preOrderThread(Node treePoint) {
        if (treePoint != null) {
            Node temp = treePoint;

            while(!temp.lTag)
                temp = temp.lLink;

            while (temp != null){
                System.out.println(customerName(temp));
                temp = preOrderSuccessor(temp);
            }
        }
    }

    private void preOrderTraversalIterative(Node treePoint) {
        if (treePoint != null) {

            Stack<Node> s = new Stack<Node>();

            while (treePoint != null || s.size() > 0) { 
  
                s.push(treePoint); 
                treePoint = treePoint.lLink;
                
                // Current must be NULL at this point
                treePoint = s.pop(); 
                
                System.out.println(customerName(treePoint));
                
                treePoint = treePoint.rLink; 
                
            }
        }
    }

    public void callPostOrder(Node treePoint, int option) {
        if (treePoint == null) {
            treePoint = myRoot;
        }
        if (option == ITERATIVE) {
            postOrderIterative(treePoint);
        } else if (option == RECURSIVE) {
            postOrderRecursive(treePoint);
        }
    }

    private void postOrderIterative(Node treePoint) {
        if (treePoint != null) {
            Node temp = treePoint;

            while(!temp.lTag)
                temp = temp.lLink;

            while (temp != null){
                System.out.println(customerName(temp));
                temp = preOrderSuccessor(temp);
            }
        }
    }

    private void postOrderRecursive(Node treePoint) {
        if (treePoint != null) {
            postOrderTraversalIterative(treePoint.lLink);
            postOrderTraversalIterative(treePoint.rLink);
            System.out.println(customerName(treePoint));
        }
    }

    private void postOrderTraversalIterative(Node treePoint) {
        Stack<Node> s = new Stack<Node>(); 
        if (treePoint != null || s.size() > 0) {
            
            s.push(treePoint);
            
            treePoint = s.pop(); 
            postOrderTraversalIterative(treePoint.rLink);

            System.out.println(customerName(treePoint));
        }
    }

    public void callReverseInOrder(Node treePoint) {
        if (treePoint == null) {
            treePoint = myRoot;
        }
        reverseInOrder(treePoint);
    }

    private void reverseInOrder(Node treePoint) {
        if (treePoint != null) {
            
            reverseInOrder(treePoint.rLink);
            System.out.println(customerName(treePoint));
            reverseInOrder(treePoint.lLink);
        }
    }

    public void delete(String custName) {
        Node a = find(custName, RECURSIVE);

        if (a != null) {
            int key = a.getKey();
            myRoot = deleteRandomNode(myRoot, key);
            System.out.println("New Root:" + myRoot.key);
        } else {
            System.out.println("\"" + custName + "\" Could not found!");
        }
    }

    private Node deleteRandomNode(Node deletePoint, int key) {
        
        Node parent = null, curr = deletePoint; 

        // Set true if key is found 
        int found = 0; 

        // Search key in BST : find Node and its parent. 
        while (curr != null) {
            
            if (key == curr.key) { 
                found = 1; 
                break; 
            } 
            parent = curr; 
            if (key < curr.key) { 
                if (curr.lTag == false) {
                    curr = curr.lLink; 
                }
                else {
                    break; 
                }
            } 
            else { 
                if (curr.rTag == false){
                    curr = curr.rLink;
                } else {
                    break; 
                }
            } 
        } 
        
        if (found == 0) {
            System.out.printf("key not present in tree\n"); 
        }

        // Two Children 
        else if (curr.lTag == false && curr.rTag == false) {
            deletePoint = caseThree(deletePoint, parent, curr);
        } 

        // Only Left Child 
        else if (curr.lTag == false) {
            deletePoint = caseTwo(deletePoint, parent, curr); 
        }

        // Only Right Child 
        else if (curr.rTag == false) {
            deletePoint = caseTwo(deletePoint, parent, curr); 
        }

        // No child 
        else {
            deletePoint = caseOne(deletePoint, parent, curr); 
        }
        
        return deletePoint;
        
    }

    static Node caseOne(Node root, Node parent, Node curr) { 
        // If Node to be deleted is root 
        if (parent == null) root = null; 

        // If Node to be deleted is left of its parent
        else if (curr == parent.lLink) { 
            parent.lTag = true; 
            parent.lLink = curr.lLink; 
        } 
        else { 
            parent.rTag = true; 
            parent.rLink = curr.rLink; 
        } 

        return root; 
    } 

    static Node caseTwo(Node root, Node parent, Node curr) { 
        Node child; 

        // Initialize child Node to be deleted has left child.
        if (curr.lTag == false) {
            child = curr.lLink;
        } else { // Node to be deleted has right child. 
            child = curr.rLink; 
        }

        if (parent == null) {
            root = child; 
        } else if (curr == parent.lLink) {
            parent.lLink = child; 
        } else {
            parent.rLink = child; 
        }

        // Find successor and predecessor 
        Node s = inSucc(curr); 
        
        Node p = inPred(curr); 

        System.out.println("Pred:" + p.key);
        System.out.println("Curr:" + curr.key);
        System.out.println("Succ:" + s.key);
        // If curr has left subtree. 
        if (curr.lTag == false) {
            p.rLink = s;
        } else { // If curr has right subtree. 
            if (curr.rTag == false) {
                s.lLink = p;
            }    
        }

        return root; 
    }

    static Node caseThree(Node root, Node par, Node curr) { 
        // Find inorder successor and its parent. 
        Node parsucc = curr; 
        Node succ = curr.rLink; 

        // Find leftmost child of successor 
        while (succ.lLink != null) { 
            parsucc = succ; 
            succ = succ.lLink; 
        } 

        curr.key = succ.key; 

        if (succ.lTag == true && succ.rTag == true) 
            root = caseOne(root, parsucc, succ); 
        else
            root = caseTwo(root, parsucc, succ); 

        return root; 
    }

    static Node inSucc(Node curr) { 
        if (curr.rTag == true) 
            return curr.rLink; 

        curr = curr.rLink;
        while (curr.rLink != null) {
            if (curr.lLink == null) break;
            curr = curr.lLink; 
        }

        return curr; 
    } 

    static Node inPred(Node curr) { 
        if (curr.lTag == true) 
            return curr.rLink; 

        curr = curr.lLink; 
        while (curr.rTag) 
            ; 
        curr = curr.rLink; 
        return curr; 
    } 

    private int findSmallestValue(Node root) {
        int value = root.key;
        while(root.lLink != null) {
            value = root.lLink.key;
            root = root.lLink;
        }
        return value;
        // return root.lLink == null ? root.getKey() : findSmallestValue(root.lLink);
    }
    public void test(){
        System.out.println(myRoot.lLink.key);
    }
    public static void main(String args[]) {
        
        BinarySearchTree mySearchTree = new BinarySearchTree();

        // transaction 1
        mySearchTree.insert(1, "Moutafis", "295-1492");
        mySearchTree.insert(2, "Ikerd", "291-1864");
        mySearchTree.insert(3, "Gladwin", "295-1601");
        mySearchTree.insert(4, "Robson", "293-6122");
        mySearchTree.insert(5, "Dang", "295-1882");
        mySearchTree.insert(6, "Bird", "291-7890");
        mySearchTree.insert(7, "Harris", "294-8075");
        mySearchTree.insert(8, "Ortiz", "584-3622");
         
        mySearchTree.callInOrder(null);
        
        String searchKey = "";

        Node result = null;

        System.out.println("");

        //transaction 2
        searchKey = "Ortiz";
        result = mySearchTree.find(searchKey, ITERATIVE);
        if (result != null) {
            System.out.println("\"" + searchKey + "\" found (ITERATIVE method)! Phone Number is: " + customerPhone(result));
        } else {
            System.out.println("\"" + searchKey + "\" could not be found (ITERATIVE method)!");
        }

        // transaction 3
        result = mySearchTree.find(searchKey, RECURSIVE);
        if (result != null) {
            System.out.println("\"" + searchKey + "\" found (RECURSIVE method)! Phone Number is: " + customerPhone(result));
        } else {
            System.out.println("\"" + searchKey + "\" could not be found (RECURSIVE method)!");
        }
        
        // transaction 4
        searchKey = "Penton";
        result = mySearchTree.find(searchKey, ITERATIVE);
        if (result != null) {
            System.out.println("\"" + searchKey + "\" found (ITERATIVE method)! Phone Number is: " + customerPhone(result));
        } else {
            System.out.println("\"" + searchKey + "\" could not be found (ITERATIVE method)!");
        }

        // transaction 5
        result = mySearchTree.find(searchKey, RECURSIVE);
        if (result != null) {
            System.out.println("\"" + searchKey + "\" found (RECURSIVE method)! Phone Number is: " + customerPhone(result));
        } else {
            System.out.println("\"" + searchKey + "\" could not be found (RECURSIVE method)!");
        }

        System.out.println("");
        
        // transaction 6
        searchKey = "Ikerd";
        result = mySearchTree.find(searchKey, RECURSIVE);
        if (result != null) {
            mySearchTree.callInOrderCyclic(result);
        }

        // transaction 7
        mySearchTree.insert(9, "Avila", "294-1568");
        mySearchTree.insert(10, "Robson", "294-1882");
        mySearchTree.insert(11, "Villatoro", "295-6622");
/*
        // transaction 8 (OPTION C)
        System.out.println("\nIn Order Successor with Name and Phone Number:");
        mySearchTree.callInOrderSuccessor(null);

        // transaction 9 (OPTION C)
        System.out.println("\nPre Order with Name: ");
        mySearchTree.callPreOrderTraversalIterative(null);

        // transaction 10 (OPTION C)
        System.out.println("\nPost Order with Name: ");
        mySearchTree.callPostOrderTraversalIterative(null);
        */
        System.out.println("");
        mySearchTree.callInOrder(null);
        
        searchKey = "Robson";
        mySearchTree.delete(searchKey);
        System.out.println("");
        mySearchTree.callInOrder(null);
        System.out.println("Test Case:");
mySearchTree.test();
System.exit(1);
        searchKey = "Ikerd";
        mySearchTree.delete(searchKey);
        System.out.println("1");
        mySearchTree.callInOrder(null);
        
        // searchKey = "Moutafis";
        // mySearchTree.delete(searchKey);

System.exit(1);
        searchKey = "Ikerd";
        mySearchTree.delete(searchKey);


        mySearchTree.insert(9, "Poudel", "294-1666");
        mySearchTree.insert(10, "Spell", "295-1882");

        System.out.println("\nIn Order Successor with Name and Phone Number:");
        mySearchTree.callInOrderSuccessor(null);

        System.out.println("\nIn Reverse Order with Name:");
        mySearchTree.callReverseInOrder(null);

        System.out.println("\nPre Order using Thread:");
        mySearchTree.callPreOrderThread(null);

    } 
} 
