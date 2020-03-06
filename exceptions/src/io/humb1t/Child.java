package io.humb1t;

import java.nio.file.AccessDeniedException;

public class Child extends Parent {

    public Child() throws Main.LifeCycleActionExecutionException, AccessDeniedException {
    }

    @Override
    public void throwOnlyOneException() throws Main.LifeCycleActionExecutionException/*, AccessDeniedException*/ {
        super.throwOnlyOneException();
    }
}
