package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MailDatabaseManager implements DataManager {
    
    private final Connection conn;
    private final File file;
    private final String table;

    public MailDatabaseManager(File file,String table) throws SQLException {
        this.file = file;
        this.table = table;
        this.conn = DriverManager.getConnection("jdbc:sqlite:"+file.getAbsolutePath());
    }

    @Override
    public void Export(List<String> list,String table) {
        try {
            conn.createStatement().execute("CREATE TABLE IF NOT EXISTS`"+table+"` (\n" +
                                           "	`Id`	INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                                           "	`Mail`	TEXT NOT NULL\n" +
                                           ");");
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        for(String str: list){
            try {
                PreparedStatement ps= conn.prepareStatement("INSERT INTO "+table+"(Mail) VALUES(?)");
                ps.setString(1, str);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    @Override
    public Iterable Import() {
        return new Iterable<String>(){
            @Override
            public Iterator<String> iterator() {
                try {
                    return createIterator();
                } catch (SQLException e) {
                    System.out.println(e);
                    return Collections.emptyIterator();
                }
            }
        };
    }
    
    private Iterator<String> createIterator() throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM"+" "+table);
        
        return new Iterator<String>(){
            
            String nextString = nextStringIn(rs);
            
            @Override
            public boolean hasNext() {
                return nextString != null;
            }

            @Override
            public String next() {
                String per = nextString;
                nextString = nextStringIn(rs);
                return per;
            }
        };
    }            
    
    private String nextStringIn(ResultSet rs) {
        return next(rs) ? stringIn(rs):null;
    }

    private boolean next(ResultSet rs) {
        try {
            boolean next = rs.next();
            if (next) return true;
            rs.close();
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    private String stringIn(ResultSet rs) {
        return getString(rs, "Mail");
    }
    
    private String getString(ResultSet rs, String field){
        try {
            return rs.getString(field);
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }
}
