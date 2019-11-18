package com.devcircus.experiment;

import com.devcircus.utils.Constants;
import com.devcircus.benchmark.ThinLauncherBenchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;

/**
 *
 */
public class ThinLauncherState extends ThinLauncherBenchmark {

    /**
     * Prepare the Benchmark
     */
    @Setup(Level.Trial)
    public void beforeBenchmark() {
        super.init(Constants.THIN_LAUNCHER.path());
    }
}
