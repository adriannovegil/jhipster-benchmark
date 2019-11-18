package com.devcircus.benchmark;

import com.devcircus.utils.BenchmarkUtils;
import java.io.File;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Benchmark)
public abstract class BaseBenchmark {

    protected final File home = new File("target");
    protected List<String> args;
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
        System.out.println(BenchmarkUtils.output(started.getInputStream(), "Started BenchmarkApp in"));
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

}
