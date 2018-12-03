package br.com.doghero.dhproject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {

    private static final int THREAD_POOL_SIZE = 4;

    private static final Executor instance = new Executor();
    private ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    private Executor() {};

    public static Executor getInstance() {
        return instance;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

}
