package ru.mos.etp.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("props")
public class Props {
    private String pathToFile;
    private Integer threshold;

    public String getPathToFile() {return pathToFile;}
    public void setPathToFile(String pathToFile) {this.pathToFile = pathToFile;}

    public Integer getThreshold() {return threshold;}
    public void setThreshold(Integer threshold) {this.threshold = threshold;}
}
