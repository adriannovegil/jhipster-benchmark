package com.devcircus.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Benchmark)
public abstract class BaseBenchmark {

    private final File home = new File("target");
    private List<String> args;
    private Process started;

    /**
     *
     * @param jarPath
     * @param additionalArgs
     */
    public void init(String jarPath, String... additionalArgs) {
        args = new ArrayList<>();
        args.addAll(Arrays.asList("java", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=0"));
        args.addAll(Arrays.asList(additionalArgs));
        args.addAll(Arrays.asList("-jar", jarPath));
    }

    /**
     *
     * @throws Exception
     */
    public void run() throws Exception {
        var builder = new ProcessBuilder(args);
        builder.directory(home);
        builder.redirectErrorStream(true);
        started = builder.start();
        System.out.println(output(started.getInputStream(), "Started BenchmarkApp in"));
    }

    /**
     * Halt the experiment
     *
     * @throws Exception
     */
    @TearDown(Level.Iteration)
    public void afterIteration() throws Exception {
        if (started != null && started.isAlive()) {
            started.destroyForcibly().waitFor();
        }
    }

    /**
     *
     * @param inputStream
     * @param marker
     * @return
     * @throws IOException
     */
    private static String output(InputStream inputStream, String marker) throws IOException {
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
