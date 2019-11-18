package com.devcircus.utils;

import java.io.File;
import java.io.IOException;

public enum Constants {
    // Project Folder - Artifact ID - Version
    BASE("00-base", "jhipster-performance-bench-00-base", "0.0.1-SNAPSHOT"),
    INDEXER("01-spring-context-indexer", "jhipster-performance-bench-01-spring-context-indexer", "0.0.1-SNAPSHOT"),
    NO_VERIFY("02-noverify", "jhipster-performance-bench-02-noverify", "0.0.1-SNAPSHOT"),
    TIERED_1("03-tiered1", "jhipster-performance-bench-03-tiered1", "0.0.1-SNAPSHOT"),
    JMX_FALSE("04-jmx-false", "jhipster-performance-bench-04-jmx-false", "0.0.1-SNAPSHOT"),
    APP_CDS("05-appcds", "jhipster-performance-bench-05-appcds", "0.0.1-SNAPSHOT"),
    THIN_LAUNCHER("06-thin-launcher", "jhipster-performance-bench-06-thin-launcher", "0.0.1-SNAPSHOT");

    private String path;

    /**
     * Generate the experiment path
     *
     * @param artifactId 
     * @param version
     */
    Constants(String projectFolder, String artifactId, String version) {
        try {
            path = new File("..").getAbsoluteFile().getCanonicalPath() + File.separator
                    + projectFolder + File.separator + "target" + File.separator + artifactId
                    + "-" + version + ".jar";
        } catch (IOException e) {
            throw new IllegalStateException("Cannot find benchmarks", e);
        }
    }

    /**
     * Return the project path
     *
     * @return
     */
    public String path() {
        return path;
    }

}
