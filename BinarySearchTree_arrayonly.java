public class BinarySearchTree {
	
	final static int RECURSIVE = 1;
	final static int ITERATIVE = 2;
	private Node [] inorderList = new Node[0];

	public class Stack {
		private int maxSize;
		private Node[] stackArray;
		private int top;
	
		public Stack() {
			stackArray = new Node[0];
		}

		public Stack(int s) {
			maxSize = s;
			stackArray = new Node[maxSize];
			top = -1;
		}
		public void push(Node j) {
			stackArray[++top] = j;
		}
		public Node pop() {
			return stackArray[top--];
		}
		public Node peek() {
			return stackArray[top];
		}
		public boolean isEmpty() {
			return (top == -1);
		}
		public boolean isFull() {
			return (top == maxSize - 1);
		}
	}

	class Customer {
		
		private String Name;
		private String PhoneNumber;
		
		public Customer(String name, String phonenum) {
			// this.id = id;
			this.Name = name;
			this.PhoneNumber = phonenum;
		}

		Boolean isEqualName(String str) {
			if (Name.compareTo(str) == 0) return true;
			else return false;
		}

		Boolean isGreaterName(String str) {
			if (Name.compareTo(str) > 0) return true;
			else return false;
		}

		Boolean isLessName(String str) {
			if (Name.compareTo(str) < 0) return true;
			else return false;
		}
	}

	class Node {

		Node lLink, rLink;
		Boolean lTag = false, rTag = false;
		Customer Info;

		public Node(Customer c) {
			this.Info = c;
			this.lLink = null;
			this.rLink = null;
		}

		public String getSCustomer() {
			return "Name: " + Info.Name + ", Phone Number: " + Info.PhoneNumber;
		}

	}

	Node myRoot;

	BinarySearchTree () {
		myRoot = null;
	}

	public Node [] add(Node [] list, Node a) {

		Node [] temp = new Node[list.length + 1];
		int i = 0;
		for (i = 0; i < list.length; i++) {
			temp[i] = list[i];
		}
		
		temp[i] = a;
		return temp;
	}

	public String [] add(String [] list, String a) {
		String [] temp = new String[list.length + 1];
		int i = 0;
		for (i = 0; i < list.length; i++) {
			temp[i] = list[i];
		}
		temp[i] = a;
		return temp;
	}

	public Node [] removeAll(Node [] list) {
		Node [] temp = new Node[0];
		return temp;
	}

	public String toString(String [] a) {
		String temp = "[";
		for (int i = 0; i < a.length; i++) {
			temp += a[i];
			if (a.length > 1 && (i + 1) < a.length) {
				temp += ", ";
			}
		}
		temp += "]";
		return temp;
	}

	public static String customerName(Node treePoint) {
		return (treePoint.Info.Name);
	}
	
	public static String customerPhone(Node treePoint) {
		return (treePoint.Info.PhoneNumber);
	}

	// methods to inser Node begin
	public void insert(String custName, String custPhone) {
		if (myRoot == null) {
			myRoot = insertBinarySearchTree(myRoot, custName, custPhone);
		} else {
			Node node = insertBinarySearchTree(myRoot, custName, custPhone);
		}

		System.out.println("Inserted Customer: " + custName);
	}

	private Node insertBinarySearchTree(Node root, String custName, String custPhone) {
		
		Customer c = new Customer(custName, custPhone);
		
		Node newNode = new Node(c);

		Node curr = root;
		Node parent = null;

		while (curr != null) {
			
			// if (curr.Info.isEqualName(custName)) {
			//     System.out.printf("Duplicate Key!\n");
			//     return root;
			// }

			parent = curr;

			if (curr.Info.isGreaterName(custName)) {
				if (curr.lTag){
					curr = curr.lLink;
				} else break;
			} else {
				if (curr.rTag){
					curr = curr.rLink;
				} else break;
			}
		}
		
		if (parent == null) { // root node
			parent = newNode;
		} else if (parent.Info.isGreaterName(custName)) {
			parent.lTag = true;
			parent.lLink = newNode;
		} else {
			parent.rTag = true;
			parent.rLink = newNode;
		}
		
		return parent;
	}
	// methods to inser Node end

	// methods to delete Node begin
	public void delete(String custName) {
		Node a = find(custName, RECURSIVE);
		if (a != null) {
			myRoot = deleteNode(myRoot, custName);
		} else {
			System.out.println("\"" + custName + "\" Could not found!");
		}
		System.out.println("Deleted Customer: " + custName);
	}

	private Node deleteNode(Node root, String value) {

		if (root == null)
			return root;

		if (root.Info.isGreaterName(value)) {
			root.lLink = deleteNode(root.lLink, value);
		} else if (root.Info.isLessName(value)) {
			root.rLink = deleteNode(root.rLink, value);
		} else {

			if (root.lLink == null) {
				return root.rLink;
			} else if (root.rLink == null)
				return root.lLink;

			root.Info.Name = getMin(root.rLink);
			root.rLink = deleteNode(root.rLink, root.Info.Name);
		}

		return root;
	}

	private String getMin(Node root) {
		String min = root.Info.Name;
		while (root.lLink != null) {
			min = root.lLink.Info.Name;
			root = root.lLink;
		}
		return min;
	}
	// methods to delete Node end

	// methods to find Node begin
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
			if (root.Info.isLessName(customerName)) {
				root = root.rLink;
			} else if (root.Info.isGreaterName(customerName)) {
				root = root.lLink;
			} else {
				break;
			}
		}

		return root;
	}

	private Node findCustomerRecursive(Node root, String customerName) {
		if (root==null || root.Info.isEqualName(customerName))
			return root;

		if (root.Info.isGreaterName(customerName))
			return findCustomerRecursive(root.lLink, customerName);

		return findCustomerRecursive(root.rLink, customerName);
	}
	// methods to find Node end

	// methods to store Node in the arraylist begin
	public void updateInorderList(){
		Node treePoint = myRoot;
		inorderList = removeAll(inorderList);
		updateInorderRec(treePoint);
	}

	private void updateInorderRec(Node treePoint) {
		if (treePoint != null) {
			updateInorderRec(treePoint.lLink);
			inorderList = add(inorderList, treePoint);
			updateInorderRec(treePoint.rLink);
		}
	}
	// methods to store Node in the arraylist end

	// methods to be called from main methos
	public void callInOrderSuccessor(Node treePoint) {
		if (treePoint == null) {
			treePoint = myRoot;
		}

		for (int i = 0; i < inorderList.length; i++) {
			Node last = inOrderSuccesor(inorderList[i]);
		}
	}

	public void callInOrder(Node treePoint) {
		if (treePoint == null) {
			treePoint = myRoot;
		}
		inOrder(treePoint);
	}

	public void callInOrderCyclic(Node treePoint) {
		if (treePoint == null) {
			treePoint = myRoot;
		}
		inOrderCyclic(treePoint);
	}

	public void callPreOrder(Node treePoint) {
		if (treePoint == null) {
			treePoint = myRoot;
		}
		preOrder(treePoint);
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

	public void callPreOrderThread(Node treePoint) {
		if (treePoint == null) {
			treePoint = myRoot;
		}
		preOrderThread(treePoint);
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

	public void callReverseInOrder(Node treePoint) {
		if (treePoint == null) {
			treePoint = myRoot;
		}
		reverseInOrder(treePoint);
	}

	private Node inOrderSuccesor(Node treePoint) {

		if(treePoint.rLink != null){
			Node temp = leftMostTreeNode(treePoint.rLink);
			System.out.println("Successor of " + treePoint.Info.Name + " -> " + customerName(temp) + ", " + customerPhone(temp));
			return null;
		}
		return findRec(myRoot, treePoint);
	}

	private Node leftMostTreeNode(Node curr){
		while(curr.lLink != null){
			curr = curr.lLink;
		}
		return curr;
	}

	public Node findRec(Node root, Node curr){
		Node n = null;
		if(root==null) return null;
		if(root==curr||(n = findRec(root.lLink,curr))!=null||(n=findRec(root.rLink,curr))!=null){	

			if(n!=null){
				if(root.lLink==n){
					System.out.println("Successor of " + curr.Info.Name + " -> " + customerName(root) + ", " + customerPhone(root));
					return null;
				}
			}
			return root;
		}
		return null;
	}

	private void inOrder(Node treePoint) {
		
		if (treePoint != null) {
			inOrder(treePoint.lLink);
			System.out.println(customerName(treePoint));
			inOrder(treePoint.rLink);
		}
	}

	private void inOrderCyclic(Node treePoint) {
		
		String [] preList = new String[0];
		String [] postList = new String[0];
		Boolean greater = false;
		for (int i = 0; i < inorderList.length; i++) {
			if (!greater && treePoint == inorderList[i]){
				greater = true;
			}
			if (!greater) {
				preList = add(preList,customerName(inorderList[i]));
			} else {
				postList = add(postList,customerName(inorderList[i]));
			}
		}
		
		for (int i = 0; i < postList.length; i++) {
			System.out.println(postList[i]);
		}

		for (int i = 0; i < preList.length; i++) {
			System.out.println(preList[i]);
		}
	}

	private Node preOrder(Node treePoint) {
		if (treePoint != null) {
			System.out.println(customerName(treePoint));
			preOrder(treePoint.lLink);
			preOrder(treePoint.rLink);
		}
		return treePoint;
	}

	private void preOrderTraversalIterative(Node treePoint) {
		if (treePoint == null) return;

		String [] myList = new String[0];
		if (treePoint != null) {

			Stack myStack = new Stack(100);

			myStack.push(treePoint);

			while (!myStack.isEmpty()) {
				Node temp = myStack.peek();
				
				// System.out.println(customerName(temp));
				myList = add(myList, customerName(temp));

				myStack.pop();

				if (temp.rLink != null) {
					myStack.push(temp.rLink);
				} 
				if (temp.lLink != null) {
					myStack.push(temp.lLink);
				}
			}
		}
		
		System.out.println(toString(myList));
	}

	private void postOrderTraversalIterative(Node treePoint) { 
		// Check for empty tree 
		if (treePoint == null) return;

		String [] myList = new String[0];
		Stack myStack = new Stack(100); 
		
		myStack.push(treePoint);

		Node prev = null;

		while (!myStack.isEmpty()) { 
			Node current = myStack.peek(); 

			if (prev == null || prev.lLink == current || prev.rLink == current) { 
				if (current.lLink != null) 
					myStack.push(current.lLink); 
				else if (current.rLink != null) 
					myStack.push(current.rLink); 
				else { 
					myStack.pop(); 
					myList =add(myList, customerName(current)); 
				}
			}  
			else if (current.lLink == prev) { 
				if (current.rLink != null) 
					myStack.push(current.rLink); 
				else { 
					myStack.pop(); 
					myList = add(myList, customerName(current)); 
				}
			}  
			else if (current.rLink == prev) { 
				myStack.pop(); 
				myList = add(myList, customerName(current)); 
			} 
   
			prev = current; 
		} 
   
		System.out.println(toString(myList)); 
	}

	private void preOrderThread(Node treePoint) {
		if (treePoint != null) {
			System.out.println(customerName(treePoint));
			if (treePoint.lTag) {
				preOrderThread(treePoint.lLink);
			}
			if (treePoint.rTag) {
				preOrderThread(treePoint.rLink);
			}
		}
	}

	private void postOrderIterative(Node treePoint) {
		if (treePoint != null) {
			
			if (treePoint.lTag) {
				postOrderIterative(treePoint.lLink);
			}
			
			if (treePoint.rTag) {
				postOrderIterative(treePoint.rLink);
			}
			System.out.println(customerName(treePoint));
		}
	}

	private void postOrderRecursive(Node treePoint) {
		if (treePoint != null) {
			postOrderRecursive(treePoint.lLink);
			postOrderRecursive(treePoint.rLink);
			System.out.println(customerName(treePoint));
		}
	}

	private void reverseInOrder(Node treePoint) {
		if (treePoint != null) {
			reverseInOrder(treePoint.rLink);
			System.out.println(customerName(treePoint));
			reverseInOrder(treePoint.lLink);
		}
	}

	public static void main(String args[]) {

		BinarySearchTree mySearchTree = new BinarySearchTree();

		// transaction 1 (OPTION C)
		System.out.println("\ntransaction 1 (OPTION C)");
		mySearchTree.insert("Moutafis", "295-1492");
		mySearchTree.insert("Ikerd", "291-1864");
		mySearchTree.insert("Gladwin", "295-1601");
		mySearchTree.insert("Robson", "293-6122");
		mySearchTree.insert("Dang", "295-1882");
		mySearchTree.insert("Bird", "291-7890");
		mySearchTree.insert("Harris", "294-8075");
		mySearchTree.insert("Ortiz", "584-3622");
		mySearchTree.updateInorderList();

		System.out.println("\nIn Order list:");
		mySearchTree.callInOrder(null);

		String searchKey = "";

		Node result = null;

		System.out.println("");

		// transaction 2 (OPTION C)
		System.out.println("\ntransaction 2 (OPTION C)");
		searchKey = "Ortiz";
		result = mySearchTree.find(searchKey, ITERATIVE);
		if (result != null) {
			System.out.println("\"" + searchKey + "\" found (ITERATIVE method)! Phone Number is: " + customerPhone(result));
		} else {
			System.out.println("\"" + searchKey + "\" could not be found (ITERATIVE method)!");
		}

		// transaction 3 (OPTION C)
		System.out.println("\ntransaction 3 (OPTION C)");
		result = mySearchTree.find(searchKey, RECURSIVE);
		if (result != null) {
			System.out.println("\"" + searchKey + "\" found (RECURSIVE method)! Phone Number is: " + customerPhone(result));
		} else {
			System.out.println("\"" + searchKey + "\" could not be found (RECURSIVE method)!");
		}

		// transaction 4 (OPTION C)
		System.out.println("\ntransaction 4 (OPTION C)");
		searchKey = "Penton";
		result = mySearchTree.find(searchKey, ITERATIVE);
		if (result != null) {
			System.out.println("\"" + searchKey + "\" found (ITERATIVE method)! Phone Number is: " + customerPhone(result));
		} else {
			System.out.println("\"" + searchKey + "\" could not be found (ITERATIVE method)!");
		}

		// transaction 5 (OPTION C)
		System.out.println("\ntransaction 5 (OPTION C)");
		result = mySearchTree.find(searchKey, RECURSIVE);
		if (result != null) {
			System.out.println("\"" + searchKey + "\" found (RECURSIVE method)! Phone Number is: " + customerPhone(result));
		} else {
			System.out.println("\"" + searchKey + "\" could not be found (RECURSIVE method)!");
		}

		// transaction 6 (OPTION C)
		System.out.println("\ntransaction 6 (OPTION C)");
		searchKey = "Ikerd";
		result = mySearchTree.find(searchKey, ITERATIVE);
		if (result != null) {
			System.out.println("\nIn Order Starting from " + searchKey + " back to " + searchKey + ":");
			mySearchTree.callInOrderCyclic(result);
		}

		// transaction 7 (OPTION C)
		System.out.println("\ntransaction 7 (OPTION C)");
		System.out.println("");
		mySearchTree.insert("Avila", "294-1568");
		mySearchTree.insert("Robson", "294-1882");
		mySearchTree.insert("Villatoro", "295-6622");
		mySearchTree.updateInorderList();

		// transaction 8 (OPTION C)
		System.out.println("\ntransaction 8 (OPTION C)");
		System.out.println("\nIn Order Successor list (Name and Phone Number):");
		mySearchTree.callInOrderSuccessor(null);

		// transaction 9 (OPTION C)
		System.out.println("\ntransaction 9 (OPTION C)");
		System.out.println("\nPre Order list using Stack: ");
		mySearchTree.callPreOrderTraversalIterative(null);

		// transaction 10 (OPTION C)
		System.out.println("\ntransaction 10 (OPTION C)");
		System.out.println("\nPost Order list using Stack: ");
		mySearchTree.callPostOrderTraversalIterative(null);

		// transaction 7 (OPTION B)
		System.out.println("\ntransaction 7 (OPTION B)");
		System.out.println("");
		searchKey = "Robson";
		mySearchTree.delete(searchKey);

		searchKey = "Moutafis";
		mySearchTree.delete(searchKey);

		searchKey = "Ikerd";
		mySearchTree.delete(searchKey);
		mySearchTree.updateInorderList();

		System.out.println("\nAfter Deletion: ");
		mySearchTree.callInOrder(null);

		// transaction 8 (OPTION B)
		System.out.println("\ntransaction 8 (OPTION B)");
		System.out.println("");
		mySearchTree.insert("Poudel", "294-1666");
		mySearchTree.insert("Spell", "295-1882");
		mySearchTree.updateInorderList();

		System.out.println("\nAfter Insertion: ");
		mySearchTree.callInOrder(null);

		// transaction 9 (OPTION B)
		System.out.println("\ntransaction 9 (OPTION B)");
		System.out.println("\nIn Order Successor list (Name and Phone Number):");
		mySearchTree.callInOrderSuccessor(null);

		// transaction 10 (OPTION B)
		System.out.println("\ntransaction 10 (OPTION B)");
		System.out.println("\nIn Reverse Order:");
		mySearchTree.callReverseInOrder(null);

		// transaction 11 (OPTION B)
		System.out.println("\ntransaction 11 (OPTION B)");
		System.out.println("\nPre Order using Thread:");
		mySearchTree.callPreOrderThread(null);

		// transaction 12 (OPTION A)
		System.out.println("\ntransaction 12 (OPTION A)");
		System.out.println("\nPost Order using Thread (ITERATIVE):");
		mySearchTree.callPostOrder(null, ITERATIVE);

		// transaction 13 (OPTION A)
		System.out.println("\ntransaction 13 (OPTION A)");
		System.out.println("\nPost Order (RECURSIVE):");
		mySearchTree.callPostOrder(null, RECURSIVE);
	}
}
