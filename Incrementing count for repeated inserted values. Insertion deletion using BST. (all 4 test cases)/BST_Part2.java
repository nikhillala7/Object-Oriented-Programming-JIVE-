  class BST_Part2 {

	  public static void main(String[] args) {
		  
		AbsTree tr = new DupTree(100);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(20);
		tr.insert(75);
		tr.insert(20);
		tr.insert(90);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(75);
		tr.insert(90);
		
		tr.delete(20);
		tr.delete(20);
		tr.delete(20);
		tr.delete(150);
		tr.delete(100);
		tr.delete(150);
		tr.delete(125);
		tr.delete(125);
		tr.delete(50);
		tr.delete(50);
		tr.delete(50);
		tr.delete(75);
		tr.delete(90);
		tr.delete(75);
		tr.delete(90);
		}
  }
  
  abstract class AbsTree {
	public AbsTree(int n) {
		value = n;
		left = null;
		right = null;
	}

	public void insert(int n) {
		if (value == n)
			count_duplicates();
		else if (value < n)
			if 
			(right == null) {
				right = add_node(n);
				right.parent=this;}
				
			else {
				right.insert(n);
		
			}
		else if (left == null)
		{
			left = add_node(n);
			left.parent=this;}
		else
			left.insert(n);

		
	}
	
	public void delete(int n) {  
		AbsTree t = find(n);
		
		if (t == null)
		{ // n is not in the tree
		System.out.println("Unable to delete " + n + " -- not in the tree!");
		return;
		} 
		
		if(t.count())
		{		
		
			
			 if (t.left == null && t.right == null) { // n is at a leaf position
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
		
		else 
			t.dec();
	// adapt Part 1 solution and use here
		
	}

	
	protected void case1(AbsTree t) {  
		
		if(t.parent.value>t.value)
			t.parent.left=null;
		
		else
			t.parent.right=null;
		
		t.parent=null;
	// adapt Part 1 solution and use here
	}
	
	private void case2(AbsTree t) 
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
	
	
	// adapt Part 1 solution and use here
	
	protected void case3L(AbsTree t) { 
		
		AbsTree temp;
		if(t.left==null)
			t.value=this.value;
		
		else
		{
			t.value=t.left.max().value;
			t.copycount(t.left.max());
			t.left.parent.value=t.left.max().value;
			temp=t.left.max();
			temp.parent.left=null;
			temp.parent.right=null;
			temp.parent=null;
		}

	// adapt Part 1 solution and use here
	}

	protected void case3R(AbsTree t) {  
		if(t.right==null)
			t.value=this.value;
		else 
		{
		t.value=t.right.min().value;
		t.copycount(t.right.min());
		AbsTree temp;
		temp=t.right.min();
		t.right=temp.right;
		temp.right.parent=temp.parent;
		temp.parent=null;
		}
	// adapt Part 1 solution and use here
	}

	private AbsTree find(int n) {
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
		
			
	// adapt Part 1 solution and use here
	}
	
	public AbsTree min() {
		while(left!=null)
			return left.min();
			return this;
		
	// adapt Part 1 solution and use here
	}

	public AbsTree max() {
		while(right!=null)
			return right.max();
			return this;
	// adapt Part 1 solution and use here
	}

	protected int value;
	protected AbsTree left;
	protected AbsTree right;
	protected AbsTree parent;

	// Protected Abstract Methods
	

	protected abstract AbsTree add_node(int n);
	protected abstract void count_duplicates();
	protected abstract boolean count();
	protected abstract int getcount();
	protected abstract void dec();
	protected abstract void copycount(AbsTree t1);

	// Additional protected abstract methods, as needed
}


class Tree extends AbsTree {

	public Tree(int n) {
		super(n);
	}

	protected AbsTree add_node(int n) {
		return new Tree(n);
	}

	protected void count_duplicates() {
		;
	}
	protected boolean count()
	{
		return true;
	}
	protected void dec()
	{
		
	}
	protected void copycount(AbsTree t1)
	{
		return;
	}
	protected int getcount()
	{
		return 1;
	}
	
	
	// define additional protected methods here, as needed

}

class DupTree extends AbsTree {

	public DupTree(int n) {
		super(n);
		count = 1;
	};

	protected AbsTree add_node(int n) {
		return new DupTree(n);
	}

	protected void count_duplicates() {
		count++;
	}
	protected void dec()
	{
		count--;
	}
	
	protected boolean count()
	{
		if(this.count==1)
		{
			return true;
		}
			
			return false;
				
	}
	protected int getcount()
	{
		return this.count;
	}
	protected void copycount(AbsTree t1)
	{
		this.count = t1.getcount();
	}

	// define additional protected methods here, as needed

	protected int count;
}
