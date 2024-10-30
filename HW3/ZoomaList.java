
public class ZoomaList extends CDLinkedList {
	int score = 0;

	public ZoomaList() {
		super();
	}

	public ZoomaList(CDLinkedList l) {
		header = l.header;
		size = l.size;
	}

	public void insert(int value, Iterator p) throws Exception {
		//fill code 
		super.insert(value, p);
		// check value around p
		DListIterator sp = (DListIterator) p;
		DListNode start = sp.currentNode.nextNode; 
		DListNode left = start;
		DListNode right = start;
		int count = 1;
		while (left.previousNode.data == start.data) {
			count += 1;
			left = left.previousNode;
		}
		while (right.nextNode.data == start.data) {
			count += 1;
			right = right.nextNode;
		}
		if (count >= 3) {
			DListIterator lposition = new DListIterator(left.previousNode);
			DListIterator rposition = new DListIterator(right.nextNode);
			removeBetween(lposition, rposition, count);
			score += count;
			while (lposition.currentNode.data == rposition.currentNode.data) {
				//check left right
				int c = 2;
				while (lposition.currentNode.previousNode.data == lposition.currentNode.data) {
					c += 1;
					lposition.currentNode = lposition.currentNode.previousNode;
				}
				while (rposition.currentNode.nextNode.data == rposition.currentNode.data) {
					c += 1;
					rposition.currentNode = rposition.currentNode.nextNode;
				}
				if (c >= 3) {
					lposition.currentNode = lposition.currentNode.previousNode;
					rposition.currentNode = rposition.currentNode.nextNode;
					removeBetween(lposition, rposition, c);
					score += c;
				}
				
			}
			return;
		}
		return;
	}	

	
	public void removeBetween(DListIterator left, DListIterator right, int inc) {
		//fill code
		if(left.currentNode == right.currentNode) return;
		left.currentNode.nextNode = right.currentNode;
		right.currentNode.previousNode = left.currentNode;
		size -= inc;
	}

}
