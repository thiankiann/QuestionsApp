package com.stormit.ideas.springdata.transactions;

import java.util.function.Supplier;

//@Service
public class TransactionHelper {

  //  @Transactional
    public <T> T withTransaction(Supplier<T> supplier) {
        return supplier.get();
    }

   // @Transactional
    public void withTransaction(Runnable runnable) {
        runnable.run();
    }

    //@Transactional(readOnly = true)
    public <T> T withReadOnlyTransaction(Supplier<T> supplier) {
        return supplier.get();
    }

   // @Transactional(readOnly = true)
    public void withReadOnlyTransaction(Runnable runnable) {
        runnable.run();
    }

   // @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <T> T withNewTransaction(Supplier<T> supplier) {
        return supplier.get();
    }

    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void withNewTransaction(Runnable runnable) {
        runnable.run();
    }
}
