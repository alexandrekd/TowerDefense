
package app.modele;

import app.modele.Eleve.Telio;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class ZoneTest {
    private Environnement envDeTest;
    private Zone zoneDeTest;

    @Before
    public void setup() throws Exception{
        Environnement.setMap(new ArrayList<Integer>(Arrays.asList(101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,
                101, 103, 101, 102, 102, 102, 101, 101, 101, 101, 101, 102, 102, 102, 102, 102, 102, 102, 102, 101, 101, 101, 101, 101, 102, 102, 102, 102, 102, 101, 101, 101,
                101, 101, 101, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 102, 101, 101, 101,
                101, 101, 101, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 103, 103, 103, 103, 101, 102, 101, 101, 101, 101, 101, 102, 101, 103, 101, 102, 101, 101, 101,
                101, 101, 101, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 102, 101, 101, 101,
                101, 101, 101, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 103, 103, 103, 103, 101, 102, 101, 103, 103, 103, 101, 102, 101, 101, 101, 102, 101, 101, 101,
                900, 102, 102, 102, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 103, 101, 102, 101, 101, 101,
                101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 102, 102, 102, 104,
                101, 101, 102, 102, 102, 102, 101, 103, 103, 103, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 101,
                101, 101, 102, 101, 101, 101, 101, 101, 101, 101, 101, 102, 101, 103, 103, 103, 103, 101, 102, 101, 103, 103, 103, 101, 102, 101, 103, 101, 101, 101, 101, 101,
                101, 101, 102, 101, 103, 103, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 101,
                101, 101, 102, 101, 101, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 102, 101, 101, 101, 101, 101, 101, 101,
                101, 101, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 101, 101, 101, 101, 101, 101, 102, 102, 102, 102, 102, 102, 102, 101, 101, 101, 101, 101, 101, 101,
                101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 103, 103, 103, 103, 101, 101, 101, 101, 101, 101, 101, 101, 101, 103, 101, 101, 101, 101, 101,
                101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,
                101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101
        )));
        Niveau.setIdVagues(1);
        envDeTest = new Environnement(300,300);
        zoneDeTest = new Zone(30,"RED",30,30,envDeTest,"TEST");



    }

    @Test
    public void siAttaquantArrive(){
        envDeTest.getActeurs().add(new Telio(envDeTest,0,0));
        zoneDeTest.quiEstDansLaZone();
        assertTrue(zoneDeTest.getActeursDansLaZone().size() == 1);
    }

    @Test
    public void siAttaquantArriveEtQuitte(){
        envDeTest.getActeurs().add(new Telio(envDeTest,0,0));
        zoneDeTest.quiEstDansLaZone();
        envDeTest.getActeurs().get(0).setX(61);
        envDeTest.getActeurs().get(0).setY(61);
        zoneDeTest.quiEstDansLaZone();
        assertTrue(zoneDeTest.getActeursDansLaZone().size() == 0);
    }

    @Test
    public void siAttaquantArriveEtMeurt(){
        envDeTest.getActeurs().add(new Telio(envDeTest,0,0));
        zoneDeTest.quiEstDansLaZone();
        envDeTest.getActeurs().remove(0);
        zoneDeTest.quiEstDansLaZone();
        assertTrue(zoneDeTest.getActeursDansLaZone().size() == 0);
    }

}