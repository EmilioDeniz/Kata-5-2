
package database;

import java.util.ArrayList;
import java.util.List;
import view.Loader;


public class DatabaseLoader implements Loader{

    private final MailDatabaseManager manager;

    public DatabaseLoader(MailDatabaseManager manager) {
        this.manager = manager;
    }
    
    @Override
    public List<String> load() {
        List<String> result = new ArrayList<String>();
        for(Object str: manager.Import()){
            result.add((String) str);
        }
        return result;
    }
}
