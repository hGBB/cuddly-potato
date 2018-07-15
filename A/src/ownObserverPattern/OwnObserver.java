package ownObserverPattern;

/**
 * A class can implement the {@code OwnObserver} interface when it
 * wants to be informed of changes in observable objects.
 * This is a makeshift solution to avoid using the deprecated Observer Pattern
 * and cut to the ad hoc needs of this program.
 *
 */
public interface OwnObserver {
    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code OwnObservable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param   o     the observable object.
     * @param   arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    void update(OwnObservable o, Object arg);
}
