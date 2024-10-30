public class CDLinkedList {
	DListNode header;
	int size;
	static final int HEADERVALUE = -9999999;

	public CDLinkedList() {
		header = new DListNode(HEADERVALUE);
		makeEmpty();// necessary, otherwise next/previous node will be null
	}

	public boolean isEmpty() {
		return header.nextNode == header;
	}

	public boolean isFull() {
		return false;
	}

	/** make the list empty. */
	public void makeEmpty() {
		header.nextNode = header;
		header.previousNode = header;
		size = 0;
	}

	// put in new data after the position of p.
	public void insert(int value, Iterator p) throws Exception {
		if (p == null || !(p instanceof DListIterator))
			throw new Exception();
		DListIterator p2 = (DListIterator) p;
		if (p2.currentNode == null)
			throw new Exception();

		DListIterator p3 = new DListIterator(p2.currentNode.nextNode);
		DListNode n = new DListNode(value, p3.currentNode, p2.currentNode);
		p2.currentNode.nextNode = n;
		p3.currentNode.previousNode = n;
		size++;
	}

	// return position number of value found in the list.
	// otherwise, return -1.
	public int find(int value) throws Exception {
		DListIterator itr = new DListIterator(header);
		int index = -1;
		while (itr.hasNext()) {
			int v = itr.next();
			index++;
			if (itr.currentNode == header)
				return -1;
			if (v == value)
				return index; // return the position of value.
		}
		return -1;
	}

	// return data stored at kth position.
	public int findKth(int kthPosition) throws Exception {
		if (kthPosition < 0 || kthPosition > size - 1)
			throw new Exception();// exit the method if the position is
		// beyond the first/last possible
		// position, throwing exception in the process.
		DListIterator itr = new DListIterator(header);
		int index = -1;
		while (itr.hasNext()) {
			int v = itr.next();
			index++;
			if (itr.currentNode == header)
				throw new Exception();
			if (index == kthPosition)
				return v;
		}
		throw new Exception();
	}

	// Return iterator at position before the first position that stores value.
	// If the value is not found, return null.
	public Iterator findPrevious(int value) throws Exception {
		if (isEmpty())
			return null;
		Iterator itr1 = new DListIterator(header);
		Iterator itr2 = new DListIterator(header);
		int currentData = itr2.next();
		while (currentData != value) {
			currentData = itr2.next();
			itr1.next();
			if (((DListIterator) itr2).currentNode == header)
				return null;
		}
		return itr1;
	}

	// remove content at position just after the given iterator. Skip header if
	// found.
	public void remove(Iterator p) {
		if (isEmpty())
			return;
		if (p == null || !(p instanceof DListIterator))
			return;
		DListIterator p2 = (DListIterator) p;
		if (p2.currentNode == null)
			return;
		if (p2.currentNode.nextNode == header)
			p2.currentNode = header;
		if (p2.currentNode.nextNode == null)
			return;
		DListIterator p3 = new DListIterator(p2.currentNode.nextNode.nextNode);
		p2.currentNode.nextNode = p3.currentNode;
		p3.currentNode.previousNode = p2.currentNode;
		size--;
	}

	// remove the first instance of the given data.
	public void remove(int value) throws Exception {
		Iterator p = findPrevious(value);
		if (p == null)
			return;
		remove(p);
	}

	// remove data at position p.
	// if p points to header or the list is empty, do nothing.
	public void removeAt(Iterator p) throws Exception {
		if (isEmpty() || p == null || !(p instanceof DListIterator) || ((DListIterator) p).currentNode == null
				|| ((DListIterator) p).currentNode == header)
			return;

		DListIterator p2 = (DListIterator) (findPrevious(p));
		remove(p2);

	}

	// Print each contact out, one by one.
	// To be completed by students.
	public void printList() throws Exception {
		Iterator itr = new DListIterator(header);
		while (itr.hasNext()) {
			Object data = itr.next();

			System.out.println(data);

		}
	}

	public int size() throws Exception {
		return size;
	}

	// return iterator pointing to location before position.
	public Iterator findPrevious(Iterator position) throws Exception {
		if (position == null)
			return null;
		if (!(position instanceof DListIterator))
			return null;
		if (((DListIterator) position).currentNode == null)
			return null;

		DListIterator p = ((DListIterator) position);
		DListIterator p2 = new DListIterator(p.currentNode.previousNode);
		return p2;

	}

	// write the sort method below
	public void sort() throws Exception{
		//bubbleSort();
		//selectionSort();
		//insertionSort();
		//mergeSort();
		quickSort();
	}
	
	public void bubbleSort() throws Exception{
		DListIterator i = new DListIterator(header.nextNode);
		while(i.currentNode != header) {
			DListIterator j = new DListIterator(header.nextNode);
			while(j.currentNode.nextNode != header) {
				if(j.currentNode.data > j.currentNode.nextNode.data) {
					int temp = j.currentNode.data;
					j.currentNode.data = j.currentNode.nextNode.data;
					j.currentNode.nextNode.data = temp;
				}
				j.next();
			}
			i.next();
		}
	}
	
	public void selectionSort() throws Exception{
		DListIterator i = new DListIterator(header.nextNode);
		while(i.currentNode.nextNode != header) {
			int min = i.currentNode.data;
			DListIterator m = new DListIterator(i.currentNode);
			DListIterator j = new DListIterator(i.currentNode.nextNode);
			while(j.currentNode != header) {
				if(j.currentNode.data < min) {
					min = j.currentNode.data;
					m.currentNode = j.currentNode;
				}
				j.next();
				
			}
			int temp = i.currentNode.data;
			i.currentNode.data = m.currentNode.data;
			m.currentNode.data = temp;	
			i.next();
		}
	}
	
	public void insertionSort() throws Exception{
		DListIterator i = new DListIterator(header.nextNode.nextNode);
		while(i.currentNode != header) {
			int temp = i.currentNode.data;
			DListIterator j = new DListIterator(i.currentNode.previousNode);
			while(j.currentNode != header && j.currentNode.data > temp) {
				j.currentNode.nextNode.data = j.currentNode.data;
				j.previous();
			}
			j.currentNode.nextNode.data = temp;
			i.next();
		}
	}
	
	public void mergeSort() throws Exception{
		this.header = mergeSort(this).header;
	}
	
	public CDLinkedList mergeSort(CDLinkedList l) throws Exception{
		if(l.header.nextNode.nextNode == l.header) return l;
		if(l.header.nextNode == l.header) return l;
		//partition
		DListIterator fast = new DListIterator(l.header);
		DListIterator slow = new DListIterator(l.header);
		while(fast.currentNode.nextNode != l.header && fast.currentNode.nextNode.nextNode != l.header) {
			fast.next();
			fast.next();
			slow.next();
		}
		CDLinkedList left = new CDLinkedList();
		CDLinkedList right = new CDLinkedList();
		DListIterator i = new DListIterator(l.header.previousNode);
		while(i.currentNode != slow.currentNode) {
			right.insert(i.currentNode.data, new DListIterator(right.header));
			i.previous();
		}
		while(slow.currentNode != l.header) {
			left.insert(slow.currentNode.data, new DListIterator(left.header));
			slow.previous();
		}
		left = mergeSort(left);
		right = mergeSort(right);
		return merge(left, right);
	}
	
	public CDLinkedList merge(CDLinkedList left, CDLinkedList right) throws Exception {
		CDLinkedList result = new CDLinkedList();
		DListIterator l = new DListIterator(left.header.previousNode);
		DListIterator r = new DListIterator(right.header.previousNode);
		//insert large first
		while(l.currentNode != left.header && r.currentNode != right.header) {
			if(l.currentNode.data > r.currentNode.data) {
				result.insert(l.currentNode.data, new DListIterator(result.header));
				l.previous();
			}
			else if(r.currentNode.data > l.currentNode.data) {
				result.insert(r.currentNode.data, new DListIterator(result.header));
				r.previous();
			}
		}
		while(l.currentNode != left.header) {
			result.insert(l.currentNode.data, new DListIterator(result.header));
			l.previous();
		}
		while(r.currentNode != right.header) {
			result.insert(r.currentNode.data, new DListIterator(result.header));
			r.previous();
		}
		return result;
	}
	
	public void quickSort() throws Exception{
		quickSort(this, new DListIterator(this.header.nextNode), new DListIterator(this.header.previousNode));
	}
	
	public void quickSort(CDLinkedList l, DListIterator start, DListIterator end) throws Exception{		
		if(start.currentNode == end.currentNode) return;
		if(end.currentNode.nextNode == start.currentNode) return;
		DListIterator pivot = partition(l, start, end);
		pivot.previous();
		quickSort(l, start, pivot); 
		pivot.next();
		pivot.next();
		quickSort(l, pivot, end);
	}
	
	public DListIterator partition(CDLinkedList l, DListIterator start, DListIterator end) throws Exception{
		DListIterator more = new DListIterator(start.currentNode);
		DListIterator less = new DListIterator(start.currentNode);
		more.previous();
		while(less.currentNode != end.currentNode) {
			//System.out.print("1");
			if(less.currentNode.data < end.currentNode.data) {
				more.next();
				int temp = more.currentNode.data;
				more.currentNode.data = less.currentNode.data;
				less.currentNode.data = temp;
			}
			less.next();
		}
		more.next();
		int t = end.currentNode.data;
		end.currentNode.data = more.currentNode.data;
		more.currentNode.data = t;
		
		return more;
	}
	
	

}