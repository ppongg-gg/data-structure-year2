
public class Treap {
	TreapNode root;
	int size;

	public Treap() {
		this.root = null;
		this.size = 0;
	}
	
	public TreapNode insert(int v, int h) {
		TreapNode r = insert(v, h, root, null);
		if(root.parent != null) root = root.parent;
		return r;
	}

	// return the node n after v was added into the tree
	public TreapNode insert(int v, int h, TreapNode n, TreapNode parent) {
		if (size == 0) {
			this.root = new TreapNode(v, h, null, null, parent);
			size++;
			return n;
		}
		if (n == null) {
			n = new TreapNode(v, h, null, null, parent);
			size++;
		} else if (v < n.bstValue) {
			n.left = insert(v, h, n.left, n);
		} else if (v > n.bstValue) {
			n.right = insert(v, h, n.right, n);
		}
		n = rebalance(n);
	
		return n;
	}
	
	public TreapNode rebalance(TreapNode n) {
		if (n == null)
			return n;
		if(n.left == null && n.right == null) return n; 
		if(n.left == null || n.right == null) {
			if(n.left == null) {
				if(n.right.heapValue < n.heapValue) {
					n = rotateRightChild(n);
				}
			}
			else if(n.right == null) {
				if(n.left.heapValue < n.heapValue) {
					n = rotateLeftChild(n);
				}
			}
		}
		else {
			if(n.left.heapValue < n.heapValue) {
				//rotate left
				n = rotateLeftChild(n);
			}
			else if(n.right.heapValue < n.heapValue) {
				//rotate right
				n = rotateRightChild(n);
			}
		}
		
		return n;
	}
	
	public TreapNode rotateLeftChild(TreapNode n) {
		TreapNode l = n.left;
		TreapNode lr = n.left.right; // can be null
		n.left = lr;
		if (lr != null) {
			lr.parent = n;
		}
		l.right = n;
		l.parent = n.parent;
		n.parent = l;

		return l;
	}
	
	public TreapNode rotateRightChild(TreapNode n) {
		TreapNode r = n.right;
		TreapNode rl = n.right.left; // can be null
		n.right = rl;
		if (rl != null) {
			rl.parent = n;
		}
		r.left = n;
		r.parent = n.parent;
		n.parent = r;

		return r;
	}
	
	
	public static void main(String[] args) {
		Treap t = new Treap();
		t.insert(10, 50);
		t.insert(5, 70);
		t.insert(15, 60);
		t.insert(3, 30);
		System.out.println(t.size);
		System.out.println(t.root.bstValue);
	}
	

}
