package hu.uni.eszterhazy.dao.impl;


import hu.uni.eszterhazy.exceptions.NemJoNev;
import hu.uni.eszterhazy.model.Cica;
import org.junit.Test;

import java.io.IOException;

public class CicaDAOTest {
    @Test
    public void testDB() throws IOException, NemJoNev {
        CicaDAO dao = new CicaDAO("nagycica.json");
        Cica c = new Cica();
        c.setNev("Paca");
        //dao.addCica(c);
        System.out.println(dao.readAllCica());
    }


}