package com.devcircus.benchmark;

import com.devcircus.utils.BenchmarkUtils;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@State(Scope.Benchmark)
public abstract class AppCDSThinLauncherBenchmark extends BaseBenchmark {

    /**
     *
     * @param jarPath
     * @param additionalArgs
     */
    @Override
    public void init(String jarPath, String... additionalArgs) {
        args = new ArrayList<>();
        args.addAll(Arrays.asList("java", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=0"));
        args.addAll(Arrays.asList(
                "-Xshare:on",
                "-XX:+UseAppCDS",
                "-XX:SharedArchiveFile=app.jsa"));
        args.addAll(Arrays.asList(additionalArgs));
        args.addAll(Arrays.asList("-jar", jarPath));
        args.addAll(Arrays.asList(
                "--thin.root=.",
                "--thin.dryrun=false"));
        // Prepare the environment
        try {
            BenchmarkUtils.generateCdsClassListAndDataArchive(home, jarPath);
            BenchmarkUtils.generateThinJarClassPath(home, jarPath);
        } catch (InterruptedException ex) {
            Logger.getLogger(AppCDSThinLauncherBenchmark.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
