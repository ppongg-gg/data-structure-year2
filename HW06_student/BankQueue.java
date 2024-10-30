
public class BankQueue { // must work for any implementation of DeQ
	DeQ[] counters;
	DeQ special;

	public BankQueue(DeQ[] counters, DeQ special) {
		super();
		this.counters = counters;
		this.special = special;
	}

	//Write this method
	public void distribute() throws Exception {
		//calculate needed queue len
		double nql = 0;
		for (DeQ q: counters) {
			nql += q.size();
		}
		nql /= (1+counters.length);
		int v = (int) nql;
		if (nql - v < 0.5) nql = v;
		else {
			nql = v+1;
		}
		System.out.println(nql);
		//element from index nql move to special
		for (DeQ q: counters) {
			if (special.size() == nql) break;
			if (q.size() == nql) {
				continue;
			}else {
				int nsize = q.size();
;				for (int i = 0; i < nql; i++) {
					q.insertLast(q.removeFirst());
				}
				for (int i = 0; i < nsize - nql; i++) {
					if (special.size() < nql) special.insertLast(q.removeFirst());
				}
				for (int i = 0; i < nql; i++) {
					q.insertFirst(q.removeLast());
				}
			}
		}
		if (special.size() == 0) {
			special.insertLast(counters[counters.length-1].removeLast());
		}
			
	}

}
