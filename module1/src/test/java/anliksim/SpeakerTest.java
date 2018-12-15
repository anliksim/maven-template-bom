package anliksim;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpeakerTest {

    @Test
    public void speak() {
        assertNotNull("He really speaks", new Speaker().speak());
    }
}