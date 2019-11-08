package hu.uni.eszterhazy.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hu.uni.eszterhazy.model.Cica;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class CicaDAO {
    private File jsonfile;
    ObjectMapper mapper;

    public CicaDAO(String jsonfile) throws IOException {
        this.jsonfile = new File(jsonfile);
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        if(!this.jsonfile.exists()){
            this.jsonfile.createNewFile();
            FileWriter writer = new FileWriter(this.jsonfile);
            writer.write("[]");
            writer.close();
        }
    }

    public Collection<Cica> readAllCica() throws IOException {
        Collection<Cica> result = new ArrayList<>();
        TypeReference reference = new TypeReference<ArrayList<Cica>>(){};
        result = mapper.readValue(jsonfile,reference);
        return result;
    }

    public void addCica(Cica cica) throws IOException {
        Collection<Cica> cicak = readAllCica();
        cicak.add(cica);
        mapper.writeValue(jsonfile, cicak);
    }
}
