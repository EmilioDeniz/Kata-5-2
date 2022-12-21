package view;

import java.util.List;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.toList;

public class MailListReader implements Loader {
    private final Loader loader;

    public MailListReader(Loader loader) {
        this.loader = loader;
    }
    
    @Override
    public List<String> load() {
        return loader.load().stream().filter(line -> isEmail(line)).collect(toList());
    }  
    
    private static Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private boolean isEmail(String line){
        return pattern.matcher(line).matches();
    }
}
