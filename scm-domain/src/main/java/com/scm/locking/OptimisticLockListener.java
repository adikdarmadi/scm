package com.scm.locking;

import javax.persistence.PreUpdate;

public class OptimisticLockListener {

    @PreUpdate
    public void preUpdate(Object entity) {
            //getChecker().check((T) entity);
    	System.out.println("masuk");
    }

    private OptimisticLockChecker getChecker() {
        return ApplicationContextProvider
            .getApplicationContext()
            .getBean(OptimisticLockChecker.class);
    }
}