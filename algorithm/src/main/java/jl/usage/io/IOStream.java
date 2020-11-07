package jl.usage.io;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class IOStream {
    public static void main(String[] args) {
        readFile();
    }

    private static void readFile() {
        File file = new File("/Users/liudong17/Downloads/cn_windows_10_business_editions_version_1909_updated_jan_2020_x64_dvd_b3e1f3a6.iso");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[1024 * 1024];
            int n = 0;
            while (fileInputStream.read(bytes) != -1) {
                System.out.println(new String(bytes, StandardCharsets.UTF_8));
                if (n++ > 10) {
                    System.exit(0);
                }
            }

        } catch (IOException ioe) {
            log.error("ioe", ioe);
        }
    }
}
