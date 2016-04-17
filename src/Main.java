import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main (String args[]) {

        //TEST

        try{
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//            String input;
//
//            while((input=br.readLine())!=null){
//                System.out.println(input);
//            }
            Assembler cpu = new Assembler();
            Path path = Paths.get("test.txt");
            // TODO reikia po eilute perkelti i atminti.
            Files.lines(path).forEachOrdered(line -> cpu.execute(line.toUpperCase().split(" ")));

        }catch(IOException io){
            io.printStackTrace();
        }
    }

}
