package com.devcircus.experiment;

import com.devcircus.benchmark.AppCDSBenchmark;
import com.devcircus.utils.Constants;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;

/**
 *
 */
public class AppCDSState extends AppCDSBenchmark {

    /**
     * Prepare the Benchmark
     */
    @Setup(Level.Trial)
    public void beforeBenchmark() {
        super.init(Constants.APP_CDS.path());
    }
}
