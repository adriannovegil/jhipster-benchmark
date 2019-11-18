package com.devcircus.utils;

import java.io.File;
import java.io.IOException;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import java.util.ArrayList;
import java.util.Arrays;

@State(Scope.Benchmark)
public abstract class ThinLauncherBenchmark extends BaseBenchmark {

    /**
     *
     * @param jarPath
     * @param additionalArgs
     */
    @Override
    public void init(String jarPath, String... additionalArgs) {
        args = new ArrayList<>();
        args.addAll(Arrays.asList("java", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=0"));
        args.addAll(Arrays.asList(additionalArgs));
        args.addAll(Arrays.asList("-jar", jarPath));        
        args.addAll(Arrays.asList(
                "--thin.root=.", 
                "--thin.dryrun=false"));        

        generateThinJarClassPath(home, jarPath);
    }

    /**
     * Method to generate thin jar Class Path
     *
     * @param home
     * @param jarPath
     */
    private static void generateThinJarClassPath(File home, String thinJarPath) {
        try {
            var builder = new ProcessBuilder(
                    "java",
                    "-jar",
                    thinJarPath,
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

}
