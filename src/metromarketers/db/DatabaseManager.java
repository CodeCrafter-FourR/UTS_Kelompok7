package metromarketers.db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class DatabaseManager {
    private static ObjectContainer db = null;
    private static final String DB_FILE = "metromarketers_case1.db4o";

    public static ObjectContainer getDB() {
        if (db == null || db.ext().isClosed()) {
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB_FILE);
        }
        return db;
    }

    public static void close() {
        if (db != null) {
            db.close();
            db = null; 
        }
    }
}