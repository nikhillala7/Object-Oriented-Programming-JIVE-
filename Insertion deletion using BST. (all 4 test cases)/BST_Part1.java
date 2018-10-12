
// CSE 522: Assignment 1, Part 1

class BST_Part1 {

	public static void main(String args[]) {
		 Tree tr;
		 tr = new Tree(100);
		 tr.insert(50);
		 tr.insert(125);
		 tr.insert(150);
		 tr.insert(20);
		 tr.insert(75);
		 tr.insert(20);	
		 tr.insert(90);
		 tr.delete(20);
		 tr.delete(20);
		 tr.delete(125);
		 tr.delete(150);
		 tr.delete(100);
		 tr.delete(50);
		 tr.delete(75);
		 tr.delete(25);
		 tr.delete(90);
	}
}

class Tree { // Defines one node of a binary search tree
	
	public Tree(int n) {
		parent=null;
		value = n;
		left = null;
		right = null;
	}
	
	public void insert(int n) {
		if (value == n)
			return;
		System.out.println(n);

		if (value < n)
			if (right == null)
			{
				right = new Tree(n);
				right.parent=this;
			}
			else
				right.insert(n);
		else if (left == null)
		{
			left = new Tree(n);
			left.parent=this;
		}

		else
			left.insert(n);
	}

	
	
	public Tree min() {
		while(left!=null)
			return left.min();
			return this;
		
			
			
		
			
			
		// returns the Tree node with the minimum value;
		// you should write the code
	}
	
	public Tree max() {
		while(right!=null)
			return right.max();
			return this;

		// returns the Tree node with the maximum value;
		// you should write the code
	}
	
	
	public Tree find(int n) {

		if(value==n)
		{
			return this;
		}

		else if(n<value)
		{
			if(left==null)
			return null;
		
		else
		{
		return left.find(n);
		}
		}
		
		else
		
		{
			if(right==null)
				return null;
		
			else
				return right.find(n);
		}		
		
			
		
		
		// returns the Tree node with value n;
		// returns null if n is not present in the tree;
		// you should write the code
	}
	
		public void delete(int n) {  
		//
		// *** do not modify this method ***
		//
		Tree t = find(n);
		if (t == null) { // n is not in the tree
			System.out.println("Unable to delete " + n + " -- not in the tree!");
			return;
		} else if (t.left == null && t.right == null) { // n is at a leaf position
			if (t != this) // if t is not the root of the tree
				case1(t);
			else
				System.out.println("Uable to delete " + n + " -- tree will become empty!");
			return;
		} else if (t.left == null || t.right == null) {
			// t has one subtree only
			if (t != this) { // if t is not the root of the tree
				case2(t);
				return;
			} else { // t is the root of the tree with one subtree
				if (t.left != null)
					case3L(t);
				else
					case3R(t);
				return;
			}
		} else 
			// t has two subtrees; replace n with the smallest value in t's right subtree
			case3R(t);
	}
		
	private void case1(Tree t) {  
		if(t.parent.value>t.value)
			t.parent.left=null;
		
		else
			t.parent.right=null;
		
		t.parent=null;
	// remove leaf node t;
	// you should write the code
	}
	
	private void case2(Tree t) 
	{   
		if(t.value<t.parent.value)
		{	
			if(t.right==null)
			{
				t.parent.left=t.left;
				t.left.parent=t.parent;
			}
			else
			{
				t.parent.left=t.right;
				t.right.parent=t.parent;

			}
		}
		if(t.value>t.parent.value)
		{	
			if(t.left==null)
			{
				t.parent.right=t.right;
				t.right.parent=t.parent;
			}
			else
			{
				t.parent.left=t.left;
				t.left.parent=t.parent;
			}
			
		}
			t.parent=null;
				
		}
	
	
	private void case3L(Tree t) { 
		Tree temp;
		if(t.left==null)
			t.value=this.value;
		
		else
		{
			t.left.parent.value=t.left.max().value;
			temp=t.left.max();
			temp.parent.left=null;
			temp.parent.right=null;
			temp.parent=null;
		}

		
		
			
	// replace t.value with the largest value, v, in
	// t's left subtree; then delete value v from tree;
	// you should write the code
 	}

	private void case3R(Tree t) {   
		if(t.right==null)
			t.value=this.value;
		else 
		{
		t.right.parent.value=t.right.min().value;
		Tree temp;
		temp=t.right.max();
		temp.parent.left=null;
		temp.parent=null;
		}
		
		
		
	// replace t.value with the smallest value, v, in
	// t's right subtree; then delete value v from tree;
	// you should write the code
 	}

	protected int value;
	protected Tree left;
	protected Tree right;
      protected Tree parent;
}

























