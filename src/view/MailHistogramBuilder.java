package view;

import java.util.List;
import model.Histogram;

public class MailHistogramBuilder {
    public static Histogram<String> build(List<String> list){
        Histogram<String> hist = new Histogram<String>();
    
        for(String mail : list){
            hist.increment(mail.substring(1,mail.length()));
        }
        return hist;
    }
}
