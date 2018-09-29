package murphy.christopher.popularmoviesstage1.database.utilities;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class dbExecutor {
    private static dbExecutor dbInstance;

    final private Executor dbThread;

    private dbExecutor(Executor db){
        this.dbThread = db;
    }

    public static  dbExecutor getInstance(){
        if(dbInstance == null){
            dbInstance = new dbExecutor(Executors.newSingleThreadExecutor());
        }
        return dbInstance;
    }

    public Executor getDbThread() {
        return dbThread;
    }
}
