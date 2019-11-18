package com.devcircus.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class BenchmarkUtils {

    /**
     * Method to generate Cds Class list and the Data Archive
     *
     * @param home
     * @param jarPath
     */
    public static void generateCdsClassListAndDataArchive(File home, String jarPath) {
        try {
            // java -XX:+UseAppCDS -XX:DumpLoadedClassList=app.lst -Xshare:dump -XX:SharedArchiveFile=app.jsa 
            // --class-path app.jar
            var builder = new ProcessBuilder(
                    "java",
                    "-XX:+UseAppCDS",
                    "-XX:DumpLoadedClassList=app.lst",
                    "-Xshare:dump",
                    "-XX:SharedArchiveFile=app.jsa",
                    "--class-path", jarPath);
            builder.directory(home);
            builder.redirectErrorStream(true);
            // Start the process
            var process = builder.start();
            String output = output(process.getInputStream(), null);
            process.destroyForcibly();
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CDS ClassList.", e);
        }
    }

    /**
     * Method to generate thin jar Class Path
     *
     * @param home
     * @param jarPath
     */
    public static void generateThinJarClassPath(File home, String jarPath) {
        try {
            var builder = new ProcessBuilder(
                    "java",
                    "-jar",
                    jarPath,
                    "--thin.dryrun",
                    "--thin.root=.");
            builder.directory(home);
            builder.redirectErrorStream(true);
            // Start the process
            var process = builder.start();
            String output = output(process.getInputStream(), null);
            process.destroyForcibly();
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate ThinJar classpath.", e);
        }
    }
    
    
    /**
     *
     * @param inputStream
     * @param marker
     * @return
     * @throws IOException
     */
    public static String output(InputStream inputStream, String marker) throws IOException {
        var sb = new StringBuilder();
        var br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = br.readLine()) != null && (marker == null || !line.contains(marker))) {
            sb.append(line).append(System.getProperty("line.separator"));
        }
        if (line != null) {
            sb.append(line).append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
