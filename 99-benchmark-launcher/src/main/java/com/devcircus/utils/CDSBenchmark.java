package com.devcircus.utils;

import java.io.File;
import java.io.IOException;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import java.util.ArrayList;
import java.util.Arrays;

@State(Scope.Benchmark)
public abstract class CDSBenchmark extends BaseBenchmark {

    /**
     *
     * @param jarPath
     * @param additionalArgs
     */
    @Override
    public void init(String jarPath, String... additionalArgs) {
        args = new ArrayList<>();
        args.addAll(Arrays.asList("java", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=0"));
        args.addAll(Arrays.asList(
                "-Xshare:on",
                "-XX:+UseAppCDS",
                "-XX:SharedArchiveFile=app.jsa"));
        args.addAll(Arrays.asList(additionalArgs));
        args.addAll(Arrays.asList("-jar", jarPath));

        generateCdsClassListAndDataArchive(home, jarPath);
    }

    /**
     * Method to generate Cds Class list and the Data Archive
     *
     * @param home
     * @param jarPath
     */
    private static void generateCdsClassListAndDataArchive(File home, String jarPath) {
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
            System.out.println(output(process.getInputStream(), "total    :"));
            process.destroyForcibly();
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CDS ClassList.", e);
        }
    }

}
