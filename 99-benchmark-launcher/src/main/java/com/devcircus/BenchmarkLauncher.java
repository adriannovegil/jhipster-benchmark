package com.devcircus;

import com.devcircus.utils.BaseBenchmark;
import com.devcircus.utils.JarPath;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.Warmup;

@Measurement(iterations = 5)
@Warmup(iterations = 1)
@Fork(value = 2, warmups = 0)
@BenchmarkMode(Mode.SingleShotTime)
public class BenchmarkLauncher {

    /**
     *
     * @param state
     * @throws Exception
     */
    @Benchmark
    public void case00_Base(BaseState state) throws Exception {
        state.run();
    }

    /**
     *
     */
    public static class BaseState extends BaseBenchmark {

        /**
         *
         */
        @Setup(Level.Trial)
        public void beforeBenchmark() {
            super.init(JarPath.BASE.path());
        }
    }

}
