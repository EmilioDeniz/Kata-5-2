package database;
import java.util.List;


public interface DataManager {
    
    public void Export(List<String> list,String table);
    public Iterable<String> Import();
}
