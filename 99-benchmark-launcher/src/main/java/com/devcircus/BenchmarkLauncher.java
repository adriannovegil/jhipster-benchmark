package com.devcircus;

import com.devcircus.experiments.BaseState;
import com.devcircus.experiments.IndexerState;
import com.devcircus.experiments.NoverifyState;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

@Measurement(iterations = 5)
@Warmup(iterations = 1)
@Fork(value = 2, warmups = 0)
@BenchmarkMode(Mode.SingleShotTime)
public class BenchmarkLauncher {

    /**
     * Base experiment. No modifications
     *
     * @param state
     * @throws Exception
     */
    @Benchmark
    public void case00_Base(BaseState state) throws Exception {
        state.run();
    }

    /**
     * Added the spring-context-indexer
     *
     * @param state
     * @throws Exception
     */
    @Benchmark
    public void case01_Indexer(IndexerState state) throws Exception {
        state.run();
    }

    /**
     * Added the spring-context-indexer
     *
     * @param state
     * @throws Exception
     */
    @Benchmark
    public void case02_Noverify(NoverifyState state) throws Exception {
        state.run();
    }

}
