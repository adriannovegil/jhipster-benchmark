package com.devcircus.experiment;

import com.devcircus.benchmark.AppCDSThinLauncherBenchmark;
import com.devcircus.utils.Constants;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;

/**
 *
 */
public class AppCDSThinLauncherState extends AppCDSThinLauncherBenchmark {

    /**
     * Prepare the Benchmark
     */
    @Setup(Level.Trial)
    public void beforeBenchmark() {
        super.init(Constants.APP_CDS_THIN_LAUNCHER.path());
    }
}
