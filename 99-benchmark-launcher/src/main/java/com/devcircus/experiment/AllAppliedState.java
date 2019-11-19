package com.devcircus.experiment;

import com.devcircus.benchmark.AppCDSThinLauncherBenchmark;
import com.devcircus.utils.Constants;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;

/**
 *
 */
public class AllAppliedState extends AppCDSThinLauncherBenchmark {

    /**
     * Prepare the Benchmark
     */
    @Setup(Level.Trial)
    public void beforeBenchmark() {
        super.init(Constants.ALL_APPLIED.path(), 
                "-noverify", 
                "-XX:TieredStopAtLevel=1", 
                "-Dspring.jmx.enabled=false");
    }
}
