package com.devcircus.experiments;

import com.devcircus.utils.BaseBenchmark;
import com.devcircus.utils.Constants;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;

/**
 *
 */
public class IndexerState extends BaseBenchmark {

    /**
     * Prepare the Benchmark
     */
    @Setup(Level.Trial)
    public void beforeBenchmark() {
        super.init(Constants.INDEXER.path());
    }
}
