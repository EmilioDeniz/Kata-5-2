package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileLoader implements Loader {
    private final File file;

    public FileLoader(File file) {
        this.file = file;
    }

    @Override
    public List<String> load() {
        List<String> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(true){
                String line = reader.readLine();
                if(line == null) break;
                result.add(line);
            }
        } catch (Exception e) {
        }
        return result;
    }
}
