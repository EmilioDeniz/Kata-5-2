package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import view.EmailDomainLoader;
import view.FileLoader;
import view.HistogramDisplay;
import view.Loader;
import view.MailHistogramBuilder;
import view.MailListReader;

public class main {

    public static void main(String[] args) {
        Loader loader = new EmailDomainLoader(new MailListReader(new FileLoader(new File("email.txt"))));
        List<String> domains = loader.load();
        HistogramDisplay histDisplay = new HistogramDisplay("Histograma de e-mails",MailHistogramBuilder.build(domains));
        histDisplay.execute();
    }
}
