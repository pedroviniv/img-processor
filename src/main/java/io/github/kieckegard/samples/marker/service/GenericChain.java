package io.github.kieckegard.samples.marker.service;

/**
 *
 * @author Pedro Arthur
 */
public abstract class GenericChain<C> {
    
    private GenericChain<C> next;

    public GenericChain() {}
    
    public void setNext(GenericChain<C> next) {
        this.next = next;
    }
    
    /**
     * Passes the context over chains. 
     * This method verifies if the passed context can be handled
     * by this chain instance. That verification is done by {@link #shouldHandle(java.lang.Object) }.
     * If that verification passes, this instance handles the context, then passes the context 
     * to the next chain, if it exists.
     * 
     * @param context 
     */
    public void chain(C context) {
        
        if (this.shouldHandle(context)) {
            this.handle(context);
        }
        
        if (next == null) {
            return;
        }
        
        next.chain(context);
    }
    
    /**
     * method that handles a context.
     * @param context 
     */
    protected abstract void handle(C context);
    
    /**
     * method that verifies if this chain instance can
     * handle the passed context.
     * @param context context containing all the necessary data
     * to be handled by the Chains implementations.
     * @return a boolean value indicating if this chain is
     * able to handle the passed context.
     */
    protected abstract boolean shouldHandle(C context);
}
