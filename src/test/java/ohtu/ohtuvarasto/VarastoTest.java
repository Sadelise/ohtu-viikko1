package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void tilavuusNollaKunAlustetaanNollallaTaiAlemmalla() {
        Varasto varasto1 = new Varasto(0.0);
        Varasto varasto2 = new Varasto(-0.1);
        assertEquals(0.0, varasto1.getSaldo(), 0);
        assertEquals(0.0, varasto2.getSaldo(), 0);
    }

    @Test
    public void alkusaldoJaTilavuusNollaKunAlustetaanNollallaTaiAlemmalla() {
        Varasto varasto1 = new Varasto(0.0, 0.0);
        Varasto varasto2 = new Varasto(-0.1, -0.1);
        assertEquals(0.0, varasto1.getTilavuus(), 0);
        assertEquals(0.0, varasto2.getTilavuus(), 0);
        assertEquals(0.0, varasto1.getSaldo(), 0);
        assertEquals(0.0, varasto2.getSaldo(), 0);
    }

    @Test
    public void alkusaldoEiHuomioiYlimaaraa() {
        Varasto varasto1 = new Varasto(1.1, 2.1);
        assertEquals(1.1, varasto1.getSaldo(), 0);
    }

    @Test
    public void virheellisenMaaranLisaysEiOnnistu() {
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);
        assertEquals(saldo, varasto.getSaldo(), 0);
    }

    @Test
    public void varastoTayteenMuttaEiYli() {
        double tilavuus = varasto.getTilavuus();
        varasto.lisaaVarastoon(tilavuus + 1);
        assertEquals(tilavuus, this.varasto.getSaldo(), 0);
    }

    @Test
    public void pikapoistuminenOVirheellisellaOttoMaaralla() {
        double saldo = varasto.getSaldo();
        varasto.otaVarastosta(-1);
        assertEquals(saldo, varasto.getSaldo(), 0);
    }

    @Test
    public void ottaaVainJaljellaOlevanSaldon() {
        //ei ylimääräistä
        double saldo = varasto.getSaldo();
        varasto.otaVarastosta(saldo + 1);
        assertEquals(0, this.varasto.getSaldo(), 0);
    }

    @Test
    public void merkkijonoOnOikein() {
        Varasto varasto1 = new Varasto(3, 1);
        assertEquals("saldo = 1.0, vielä tilaa 2.", varasto1.toString());
    }
}
