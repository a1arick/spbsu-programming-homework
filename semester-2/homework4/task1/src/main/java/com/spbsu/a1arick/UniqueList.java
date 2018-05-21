package com.spbsu.a1arick;


/**
 * List that stores only unique values
 *
 * @param <T> object type to store in this list
 */
public class UniqueList<T> extends MyList<T> {
    /**
     * Adds value to the list
     *
     * @param value to add
     * @throws ElementAlreadyExistsException if value already stored in the list
     */
    @Override
    public void add(T value) throws ElementAlreadyExistsException {
        if (contains(value))
            throw new ElementAlreadyExistsException(value);
        super.add(value);
    }

    /**
     * Removes value to the list
     *
     * @param value to remove
     * @throws ElementNotFoundException if value isn't stored in the list
     */
    @Override
    public void remove(T value) throws ElementNotFoundException {
        if (!contains(value))
            throw new ElementNotFoundException(value);
        super.remove(value);
    }
}
