package control;

import entity.Review;

/**
 * @author  dandi on 28/12/16.
 */

class Factory {

    private static Factory instance = new Factory();

    private Factory(){}

    static Factory getInstance(){
        return instance;
    }

    Review getReview(){
        return( new Review());
    }
}
