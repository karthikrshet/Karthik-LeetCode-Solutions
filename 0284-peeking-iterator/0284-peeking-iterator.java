import java.util.Iterator;

class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer nextElement;
    private boolean hasNextCalled;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        this.hasNextCalled = false;
        if (iterator.hasNext()) {
            this.nextElement = iterator.next();
            this.hasNextCalled = true;
        }
    }
    
    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return nextElement;
    }
    
    // Returns the next element in the iteration and advances the iterator.
    @Override
    public Integer next() {
        Integer result = nextElement;
        if (iterator.hasNext()) {
            nextElement = iterator.next();
        } else {
            nextElement = null;
            hasNextCalled = false;
        }
        return result;
    }
    
    @Override
    public boolean hasNext() {
        return hasNextCalled;
    }
}