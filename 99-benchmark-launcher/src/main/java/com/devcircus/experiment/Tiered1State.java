package com.devcircus.experiment;

import com.devcircus.benchmark.BaseBenchmark;
import com.devcircus.utils.Constants;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;

/**
 *
 */
public class Tiered1State extends BaseBenchmark {

    /**
     * Prepare the Benchmark
     */
    @Setup(Level.Trial)
    public void beforeBenchmark() {
        super.init(Constants.TIERED_1.path(), "-XX:TieredStopAtLevel=1");
    }
}
