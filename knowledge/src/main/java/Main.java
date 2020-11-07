import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.*;

@Slf4j
public class Main {
    private static final String NAME = "Main";

    public static void main(String[] args) throws Exception {
        byte[] data = new byte[4096];
        File file = new File("1.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             OutputStream fileOutputStream = new FileOutputStream(file)) {
            for (int i = 0; i < 100000; i++) {
                String s = "long data " + i + "\n";
                outputStream.write(s.getBytes());
            }
            try (InputStream in = copyOutToIn(outputStream)) {
                StreamUtils.copy(in, fileOutputStream);
            }
        }
    }

    private static InputStream copyOutToIn(ByteArrayOutputStream outputStream) {
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}

