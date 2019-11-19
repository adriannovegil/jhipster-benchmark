package com.devcircus;

import com.devcircus.experiment.AllAppliedState;
import com.devcircus.experiment.AppCDSState;
import com.devcircus.experiment.AppCDSThinLauncherState;
import com.devcircus.experiment.BaseState;
import com.devcircus.experiment.IndexerState;
import com.devcircus.experiment.NoverifyState;
import com.devcircus.experiment.ThinLauncherState;
import com.devcircus.experiment.Tiered1State;
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
     * Added the spring-context-indexer dependency
     *
     * @param state
     * @throws Exception
     */
    @Benchmark
    public void case01_Indexer(IndexerState state) throws Exception {
        state.run();
    }

    /**
     * Added the -noverify parameter
     *
     * @param state
     * @throws Exception
     */
    @Benchmark
    public void case02_Noverify(NoverifyState state) throws Exception {
        state.run();
    }

    /**
     * Added the -XX:TieredStopAtLevel=1 parameter
     *
     * @param state
     * @throws Exception
     */
    @Benchmark
    public void case03_Tiered1(Tiered1State state) throws Exception {
        state.run();
    }

    /**
     * Added the -Dspring.jmx.enabled=false parameter
     *
     * @param state
     * @throws Exception
     */
    @Benchmark
    public void case04_JmxFlase(Tiered1State state) throws Exception {
        state.run();
    }

    /**
     * AppCDS (Application Class Data Sharing)
     *
     * @param state
     * @throws Exception
     */
    @Benchmark
    public void case05_AppCDS(AppCDSState state) throws Exception {
        state.run();
    }

    /**
     * Thin Launcher
     *
     * @param state
     * @throws Exception
     */
    @Benchmark
    public void case06_ThinLauncher(ThinLauncherState state) throws Exception {
        state.run();
    }

    /**
     * AppCDS (Application Class Data Sharing) + Thin Launcher
     *
     * @param state
     * @throws Exception
     */
    @Benchmark
    public void case07_AppCDSThinLauncher(AppCDSThinLauncherState state) throws Exception {
        state.run();
    }

    /**
     * AppCDS (Application Class Data Sharing) + Thin Launcher
     *
     * @param state
     * @throws Exception
     */
    @Benchmark
    public void case08_AllApplied(AllAppliedState state) throws Exception {
        state.run();
    }

}
