package hu.uni.eszterhazy.service;

import hu.uni.eszterhazy.dao.ICicaDAO;
import hu.uni.eszterhazy.exceptions.*;
import hu.uni.eszterhazy.model.Cica;
import hu.uni.eszterhazy.model.Nem;
import hu.uni.eszterhazy.model.Szin;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class CicaServiceTest {

    CicaService service;
    Collection<Cica> cicak;

    @Before
    public void setUp() throws Exception, NemJoNev, NemJoKor, NemJoDatum, NemJoChipSzam, EzAChipMarSzerepel, CicaNotFound {
        ICicaDAO dao= Mockito.mock(ICicaDAO.class);
        Cica cica1 = new Cica(3, Szin.Cirmos,"Cirmi",
                "Judit", LocalDate.of(2019,5,14),
                "1234567891234567", Nem.Him,false,
                "Házi");
        Cica cica2 = new Cica(2, Szin.Fekete,"Paca",
                "Judit", LocalDate.of(2019,5,14),
                "1234567191234567", Nem.Him,true,
                "Main Coon jellegű");
        Cica cica3 = new Cica(6, Szin.Fekete,"Picúr",
                "Judit", LocalDate.of(2017,5,14),
                "1234567191234577", Nem.Nosteny,false,
                "Házi");
        Cica cica4 = new Cica(4, Szin.Cirmos,"Cirmi úrfi",
                "Béla", LocalDate.of(2018,5,14),
                "1234567991234567", Nem.Him,false,
                "Házi");
        cicak = new ArrayList<>();
        cicak.add(cica1);
        cicak.add(cica2);
        cicak.add(cica3);
        cicak.add(cica4);
        Mockito.when(dao.readAllCica()).thenReturn(cicak);
        Mockito.doThrow(EzAChipMarSzerepel.class).when(dao).addCica(cica1);
        Mockito.doThrow(EzAChipMarSzerepel.class).when(dao).addCica(cica2);
        Mockito.doThrow(EzAChipMarSzerepel.class).when(dao).addCica(cica3);
        Mockito.doThrow(EzAChipMarSzerepel.class).when(dao).addCica(cica4);
        Mockito.doThrow(CicaNotFound.class).when(dao).readCicaById(Mockito.anyString());
        Mockito.doReturn(cica1).when(dao).readCicaById(cica1.getChip());
        service=new CicaService(dao);

    }

    @Test(expected =EzAChipMarSzerepel.class)
    public void addCica() throws NemJoNev, NemJoKor, NemJoDatum, NemJoChipSzam, IOException, EzAChipMarSzerepel {
        Cica cica = new Cica(4, Szin.Cirmos,"Cirmi úrfi",
                "Béla", LocalDate.of(2018,5,14),
                "1234567991234567", Nem.Him,false,
                "Házi");
        service.addCica(cica);
    }

    @Test
    public void listCicak() throws IOException, NemJoNev, NemJoKor, NemJoDatum, NemJoChipSzam {
        assertEquals(4, service.listCicak().size());
        Cica cica = new Cica(4, Szin.Cirmos,"Cirmi úrfi",
                "Béla", LocalDate.of(2018,5,14),
                "1234567991234567", Nem.Him,false,
                "Házi");
        assertThat(service.listCicak(), Matchers.hasItem(cica));
        assertThat(service.listCicak(), Matchers.containsInAnyOrder(cicak.toArray()));

    }

    @Test(expected = CicaNotFound.class)
    public void getCicaByChip() throws CicaNotFound {
        service.getCicaByChip("1234567821234567");
    }

    @Test
    public void getCicaBetweenKor() throws IOException {
        assertEquals(2, service.getCicaBetweenKor(0,3).size());
    }

    @Test
    public void getCicaByNev() throws IOException {
        assertEquals(2, service.getCicaByNev("cirmi").size());
    }

    @Test
    public void getCicaDueDateToUjraOltas() throws NemJoNev, NemJoKor, NemJoDatum, NemJoChipSzam, IOException {
        Cica cica = new Cica(6, Szin.Fekete,"Picúr",
                "Judit", LocalDate.of(2017,5,14),
                "1234567191234577", Nem.Nosteny,false,
                "Házi");
        int gyakorisag=2;
        assertEquals(1, service.getCicaDueDateToUjraOltas(gyakorisag).size());
        for (Cica c:service.getCicaDueDateToUjraOltas(gyakorisag)
             ) {
            assertThat(cica, Matchers.samePropertyValuesAs(c));

        }
    }
}