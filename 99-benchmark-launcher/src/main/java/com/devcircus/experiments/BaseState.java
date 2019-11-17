/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcircus.experiments;

import com.devcircus.utils.BaseBenchmark;
import com.devcircus.utils.Constants;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;

/**
 *
 */
public class BaseState extends BaseBenchmark {

    /**
     * Prepare the Benchmark
     */
    @Setup(Level.Trial)
    public void beforeBenchmark() {
        super.init(Constants.BASE.path());
    }
}
