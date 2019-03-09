package name.ihorko.parsers;

import com.google.gson.Gson;

import java.io.*;

public class GSON {
    public static <T> void writeObjectToJsonFile(T object, File json) {
        writeStringToFile(new Gson().toJson(object), json);
    }

    private static void writeStringToFile(String str, File file) {

    }

    public static <T> T readObjectFromJsonFile(T object, File json) {
        return (T) new Gson().fromJson(readStringFromFile(json),
                object.getClass());
    }
    private static String readStringFromFile(File file) {
        StringBuilder sb = new StringBuilder();
        try(InputStream is = new FileInputStream(file);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while((line = buf.readLine()) != null){
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
