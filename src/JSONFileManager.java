import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class JSONFileManager {

    private static final String DIR = "src/files_json/";
    private static final int MAX_FILES = 3;

    public void saveJSONFile(PairConversion pairConversion) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(DIR+pairConversion.base_code()+" to "+pairConversion.target_code()+".json");
        fileWriter.write(gson.toJson(pairConversion));
        fileWriter.close();
    }

}