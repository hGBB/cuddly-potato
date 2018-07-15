package ownObserverPattern;

/**
 * This is a makeshift Observable cut to the needs of this program.
 *
 */
public class OwnObservable {
    private boolean changed = false;
    private OwnObserver obs;

    /**
     * Construct an OwnObservable.
     */
    public OwnObservable() {
    }

    /**
     * If this object has changed notify the OwnObserver.
     *
     * @param arg anyObject.
     */
    public void notifyObservers(Object arg) {
        synchronized (this) {
            if (!changed) {
                return;
            }
            changed = false;
        }
        obs.update(this, arg);
    }

    /**
     * Marks this {@code OwnObservable} object as having been changed.
     */
    protected synchronized void setChanged() {
        changed = true;
    }

    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     * The order in which notifications will be delivered to multiple
     * observers is not specified. See the class comment.
     *
     * @param o set the OwnObserver.
     */
    public synchronized void setObserver(OwnObserver o) {
        obs = o;
    }
}
