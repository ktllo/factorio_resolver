package org.leolo.factorio.resolver.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Version extends Comparable<Version> {

    private static Logger log = LoggerFactory.getLogger(Version.class);

    private int major;
    private int middle;
    private int minor;

    public Version(String version){
        String [] tokens = version.split("\\.");
        if(tokens.length>3){
            log.warn("Too many version parts! 3 expected. {} found. Only first 3 will be used!", tokens.length);
        }
        if(tokens.length<3){
            log.warn("Too little version parts! 3 expected. {} found. Rest will be treated as 0!", tokens.length);
        }
        if(tokens.length>0){
            try{
                major = Integer.parseInt(tokens[0]);
            } catch (NumberFormatException e){
                log.error("Unable to parse string \"{}\" as number.", tokens[0], e);
                throw e;
            }
        }
        if(tokens.length>1){
            try{
                middle = Integer.parseInt(tokens[1]);
            } catch (NumberFormatException e){
                log.error("Unable to parse string \"{}\" as number.", tokens[1], e);
                throw e;
            }
        }
        if(tokens.length>2){
            try{
                minor = Integer.parseInt(tokens[2]);
            } catch (NumberFormatException e){
                log.error("Unable to parse string \"{}\" as number.", tokens[2], e);
                throw e;
            }
        }
    }

    @Override
    public String toString(){
        return String.format("%d.%d.%d", major, middle, minor);
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMiddle() {
        return middle;
    }

    public void setMiddle(int middle) {
        this.middle = middle;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    @Override
    public int hashCode() {
        return (major << 24) | (middle << 12) | minor;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(!(obj instanceof Version))
            return false;
        Version v = (Version) obj;
        return major == v.major && middle == v.middle && minor == v.minor;
    }

    @Override
    public int compareTo(Version o) {
        int res = Integer.compare(major, o.major);
        if(res!=0) return res;
        res = Integer.compare(middle, o.middle);
        if(res!=0) return res;
        return Integer.compare(minor, o.minor);
    }
}
