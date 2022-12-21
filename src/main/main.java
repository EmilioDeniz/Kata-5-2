package main;

import database.DatabaseLoader;
import database.MailDatabaseManager;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.EmailDomainLoader;
import view.FileLoader;
import view.HistogramDisplay;
import view.Loader;
import view.MailHistogramBuilder;
import view.MailListReader;

public class main {

    public static void main(String[] args) {
        
        MailDatabaseManager manager;
        try {
            manager = new MailDatabaseManager(new File("KATA5.db"),"EMAIL");
        } catch (SQLException ex) {
            manager = null;
            System.out.println(ex);
        }
        Loader loader = new EmailDomainLoader(new DatabaseLoader(manager));
        List<String> domains = loader.load();
        HistogramDisplay histDisplay = new HistogramDisplay("Histograma de e-mails",MailHistogramBuilder.build(domains));
        histDisplay.execute();
    }
}
