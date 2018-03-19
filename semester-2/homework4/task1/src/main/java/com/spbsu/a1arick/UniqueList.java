package com.spbsu.a1arick;

public class UniqueList<T> extends MyList<T>{

    @Override
    public void add(T value) {
        if (contains(value))
            throw new ElementAlreadyExistsException(value);
        super.add(value);
    }

    @Override
    public void remove(T value) {
        if (!contains(value))
            throw new ElementNotFoundException(value);
        super.remove(value);
    }
}
