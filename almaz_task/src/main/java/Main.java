import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static List<String> brands;

    public static void main(String[] args) {

        try (FileOutputStream fos = new FileOutputStream("test2.csv")) {

            fos.write("title;pred;success;correction\n".getBytes());

            List<String> allTitlesData = Files.readAllLines(Paths.get("titles.csv"));


            brands = Files.readAllLines(Paths.get("brands.csv"));

            for(int i = 1; i < allTitlesData.size(); ++i) {
                String s = allTitlesData.get(i);
                String[] pos = s.split(";");

                if (pos[0].contains(";")) pos[0] = "\"" + pos[0] + "\"";
                fos.write((pos[0] + ";").getBytes());

                fos.write(pos[1].getBytes());

                if (pos[0].toUpperCase().matches(String.format("(^|(.*[^a-zA-Z0-9а-яА-ЯёЁ_]))%s(([^a-zA-Z0-9а-яА-ЯёЁ_].*)|$)",pos[1].toUpperCase()))) {
                    fos.write(";true;".getBytes());
                } else {
                    fos.write(";false;".getBytes());
                }

                for (String b : brands) {
                    if (pos[0].toUpperCase().matches(String.format("(^|(.*[^a-zA-Z0-9а-яА-ЯёЁ_]))%s(([^a-zA-Z0-9а-яА-ЯёЁ_].*)|$)",b.toUpperCase()))) {
                        fos.write(b.getBytes());break;
                    }
                }
                fos.write("\n".getBytes());
            }
            fos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
