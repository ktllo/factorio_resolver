package org.leolo.factorio.resolver.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VersionTest {

    @Test
    @DisplayName("Basic Parsing")
    public void basicParsingTest(){
        _basicParsingTest(1,2,3);
        _basicParsingTest(0,0,1);
        _basicParsingTest(65535, 65535, 65535);
    }

    @Test
    @DisplayName("Number with sign")
    public void specialNumberFmt(){
        Version v = new Version("+1.-34.0");
        assertEquals(1, v.getMajor());
        assertEquals(-34, v.getMiddle());
        assertEquals(0, v.getMinor());
    }

    @Test
    @DisplayName("Missing part")
    public void missingPart(){
        Version v = new Version("1.2");
        assertEquals(1, v.getMajor());
        assertEquals(2, v.getMiddle());
        assertEquals(0, v.getMinor());
    }

    @Test
    @DisplayName("Has too many parts")
    public void tooManyPart(){
        Version v = new Version("1.2.3.4");
        assertEquals(1, v.getMajor());
        assertEquals(2, v.getMiddle());
        assertEquals(3, v.getMinor());
    }



    @Test
    @DisplayName("Non number in version")
    public void nonNumber(){
        assertThrowsExactly(NumberFormatException.class, ()->{
            new Version ("x.y.z");
        });
        assertThrowsExactly(NumberFormatException.class, ()->{
            new Version ("1.0.z");
        });
        assertThrowsExactly(NumberFormatException.class, ()->{
            new Version ("x.0.2");
        });
    }

    private void _basicParsingTest(int major, int middle, int minor){
        String versionString = ""+major+"."+middle+"."+minor;
        System.out.println(versionString);
        Version v = new Version(versionString);
        assertEquals(major, v.getMajor());
        assertEquals(middle, v.getMiddle());
        assertEquals(minor, v.getMinor());
    }

}
