import java.util.*;

// ============  THE MAIN METHOD WITH TWO TESTS.  ==============
// ============  DON'T MODIFY THE TEST METHODS   ===============
// ============  COMPLETE ONLY THE containedIn METHOD ==========

public class GenericIterators {

public static void main(String[] args) {
	
	test1();
	System.out.println();
	test2();
}

static void test1() {
	
	AbsTree<Integer> set1 = new Tree<Integer>(100);
	set1.insert(50);
	set1.insert(50);
	set1.insert(25);
	set1.insert(75);
	set1.insert(75);
	set1.insert(150);
	set1.insert(125);
	set1.insert(200);
	set1.insert(100);
	
	AbsTree<Integer> set2 = new Tree<Integer>(100);
	set2.insert(150);
	set2.insert(125);
	set2.insert(50);
	set2.insert(50);
	set2.insert(26);
	set2.insert(25);
	set2.insert(27);
	set2.insert(75);
	set2.insert(75);
	set2.insert(76);
	set2.insert(150);
	set2.insert(125);
	set2.insert(200);
	
	System.out.print("set1 = "); print(set1);
	System.out.print("set2 = "); print(set2);
	
	if (containedIn(set1, set2))
		System.out.println("set1 is contained in set2.");
	else
		System.out.println("set1 is not contained in set2.");
}


static void test2() {
	
	AbsTree<Integer> bag1 = new DupTree<Integer>(100);
	bag1.insert(50);
	bag1.insert(50);
	bag1.insert(25);
	bag1.insert(75);
	bag1.insert(75);
	bag1.insert(150);
	bag1.insert(125);
	bag1.insert(200);
	bag1.insert(100);
	
	AbsTree<Integer> bag2 = new DupTree<Integer>(100);
	bag2.insert(150);
	bag2.insert(125);
	bag2.insert(50);
	bag2.insert(50);
	bag2.insert(26);
	bag2.insert(25);
	bag2.insert(27);
	bag2.insert(75);
	bag2.insert(75);
	bag2.insert(76);
	bag2.insert(150);
	bag2.insert(125);
	bag2.insert(200);
	
	System.out.print("bag1 = "); print(bag1);
	System.out.print("bag2 = "); print(bag2);
	
	if (containedIn(bag1, bag2))
		System.out.println("bag1 is contained in bag2.");
	else
		System.out.println("bag1 is not contained in bag2.");
}


static void print(AbsTree<Integer> bs) {
	System.out.print("{ ");
	for (int x : bs) 
		System.out.print(x + " ");
	System.out.println("}");
}



static <T extends Comparable<T>> boolean containedIn(AbsTree<T> tr1, AbsTree<T> tr2) {
	Iterator<T> iter1 = tr1.iterator();
	Iterator<T> iter2 = tr2.iterator();
	T i1;
	T i2;
	int flg=0;
	 while(iter1.hasNext())
	 {
		 i1=iter1.next();
		 while(iter2.hasNext()) 
		 {
			 i2=iter2.next();
			 if(i1.compareTo(i2)==0)
			 {
				 flg=1;
				 System.out.println(i1 +" = " +i2);
				 break;
			 }
				 else if(i1.compareTo(i2)>0)
				 {
					 
					 System.out.println(i1 +" > " +i2);
					 if(flg!=1)
						{
							return false;
						}

				 }
				 else
				 {
					 System.out.println(i1 +" < " +i2);
					 return false;
				 }
				 
		 }
		 
			 
	 
	 }
	 return !iter1.hasNext() && !iter2.hasNext();
	// fill in rest of the code here
}

}


//========= GENERIC ABSTREE, TREE, AND DUPTREE (DON'T MODIFY THESE CLASSES)


abstract class AbsTree<T extends Comparable<T>> implements Iterable<T> {

	public AbsTree(T v) {
		value = v;
		left = null;
		right = null;
	}

	public void insert(T v) {
		if (value.compareTo(v) == 0)
			count_duplicates();
		if (value.compareTo(v) > 0)
			if (left == null)
				left = add_node(v);
			else
				left.insert(v);
		else if (value.compareTo(v) < 0)
			if (right == null)
				right = add_node(v);
			else
				right.insert(v);
	}

	public Iterator<T> iterator() {
		return create_iterator();
	}

	protected abstract AbsTree<T> add_node(T n);
	protected abstract void count_duplicates();
	protected abstract int get_count();
	protected abstract Iterator<T> create_iterator();
	
	protected T value;
	protected AbsTree<T> left;
	protected AbsTree<T> right;
}


class Tree<T extends Comparable<T>> extends AbsTree<T> {
	public Tree(T n) {
		super(n);
	}
	
	public Iterator<T> create_iterator() {
		return new AbsTreeIterator<T>(this);
	}

	protected AbsTree<T> add_node(T n) {
		return new Tree<T>(n);
	}

	protected void count_duplicates() {
		;
	}
	
	protected int get_count() {
		return 1;
	}
}


class DupTree<T extends Comparable<T>> extends AbsTree<T> {
	public DupTree(T n) {
		super(n);
		count = 1;
	};

	public Iterator<T> create_iterator() {
		return new AbsTreeIterator<T>(this);   // to do
	}
	
	protected AbsTree<T> add_node(T n) {
		return new DupTree<T>(n);
	}

	protected void count_duplicates() {
		count++;
	}
	
	protected int get_count() {
		return count;
	}

	protected int count;
}




// ========  GENERIC TREE ITERATORS (COMPLETE THE OUTLINES) =========

 

class AbsTreeIterator<T extends Comparable<T>> implements Iterator<T> {

public AbsTreeIterator(AbsTree<T> root) {
	if(root==null)
		return;
	
	else
		stack_left_spine(root);

		
	// fill in code here
}

public boolean hasNext() {
	if(stack.isEmpty()==true)
		return false;
	
	else
		return true;

	// fill in code here
	
}

public T next() {

	Pair<T> node=stack.peek();
	T ret=node.node.value;
		node.count--;

		if(node.count==0)
		{
			//node.node=null;
			stack.pop();

		
		

		if(node.node.right!=null)
			stack_left_spine(node.node.right);

		node.node=null;


		}
		return ret;


}


private void stack_left_spine(AbsTree<T> node) {
	
	while(node!=null)
	{		

		Pair<T> prnode = new Pair(node);
		stack.push(prnode);
		node=node.left;
	}
	
	// fill in code here

}

private Stack<Pair<T>> stack = new Stack<Pair<T>>();

}

class TreeIterator<T extends Comparable<T>> extends AbsTreeIterator<T> {

	public TreeIterator(AbsTree<T> root) {
		super(root);
		// TODO Auto-generated constructor stub
	}
	
	// fill in code here

}

class DupTreeIterator<T extends Comparable<T>> extends AbsTreeIterator<T> {

	public DupTreeIterator(AbsTree<T> root) {
		super(root);
		// TODO Auto-generated constructor stub
	}
	
	// fill in code here
}
class Pair <T extends Comparable<T>> 
{
	public Object t;
	int count;
	AbsTree<T> node;
	
	public Pair(AbsTree<T> nnode)
	{
		node=nnode;
		count=nnode.get_count();
	}
	
	
	
}


