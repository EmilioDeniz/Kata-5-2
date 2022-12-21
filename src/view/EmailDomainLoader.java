package view;

import java.util.List;
import static java.util.stream.Collectors.toList;

public class EmailDomainLoader implements Loader{
    private final Loader loader;

    public EmailDomainLoader(Loader loader) {
        this.loader = loader;
    }

    @Override
    public List<String> load() {
        return loader.load().stream().map(line -> line.substring(line.indexOf("."+1))).collect(toList());
    }
}
