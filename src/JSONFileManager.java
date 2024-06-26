import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JSONFileManager {

    private static final String DIR = "src/files_json/";
    private static final int MAX_FILES = 3;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
    LocalDateTime currentDateTime = LocalDateTime.now();
    String formattedDateTime = currentDateTime.format(formatter);

    public void saveJSONFile(PairConversion pairConversion) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(DIR+pairConversion.base_code()+" to "+pairConversion.target_code()+" time "+formattedDateTime+".json");
        fileWriter.write(gson.toJson(pairConversion));
        fileWriter.close();
    }

}