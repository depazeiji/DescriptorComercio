/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descriptorcomercio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ine031
 */
public class Generador {
    
    private String ruta;
    private File texs;
    private String trimestre;
    private int anio;
    
    public Generador(String ruta, String rutaTEX, String trimestre, int anio){
        this.ruta = ruta;
        texs = new File(rutaTEX);
        this.trimestre = trimestre;
        this.anio = anio;
    }
    
    public void run(){
        generarDescripcion("1_01");
        generarDescripcion("1_02");
        generarDescripcion("1_03");
        
    }

    
    
    private void generarDescripcion(String csv) {
        File seccion = new File(texs, csv);
        File des = new File(seccion, "descripcion");
        switch(csv){
            case "1_01":
                //las series históricas utilizan una lista de registros para hacer
                //su descripción; getRegistros lee el csv y retorna esa lista
                escribirTEX(des.getAbsolutePath(), des1_01(getRegistros(csv)));
                break;
            case "1_02":
                escribirTEX(des.getAbsolutePath(), des1_02(getRegistros(csv)));
                break;
            case "1_03":
                escribirTEX(des.getAbsolutePath(), des1_03(getRegistros(csv)));
                break;
            case "1_04":
                escribirTEX(des.getAbsolutePath(), des1_04(getRegistros(csv)));
                break;
            case "1_05":
                escribirTEX(des.getAbsolutePath(), des1_05(getRegistros(csv)));
                break;
            case "1_06":
                escribirTEX(des.getAbsolutePath(), des1_06(getRegistros(csv)));
                break;
            case "1_07":
                escribirTEX(des.getAbsolutePath(), des1_07(getRegistrosDobles(csv)));
                break;
            default:
                break;
        }
    }
    
    private String des1_01(List<Registro> lista){
        int actual = (int)(lista.get(lista.size()-1).getValor());
        int anterior = (int)(lista.get(lista.size()-2).getValor());
        int anioAnterior = (int)(lista.get(lista.size()-5).getValor());
        String descripcion = "El valor de las exportaciones del "
                + this.trimestre + " trimestre de " + this.anio
                + " se registró en " + getNumero(((double)actual)/1000000)
                + " millones de dólares estadounidenses, " + getPorcentaje(actual, anterior) 
                + " que lo registrado en el " 
                + " trimestre anterior y " + getPorcentaje(actual, anioAnterior)
                + " que lo registrado en el " + (lista.get(lista.size()-5).getTrimestreCadena())
                + " trimestre del año " + (lista.get(lista.size()-5).getAnio()) + ".";
        return descripcion;
    }
    
    private String des1_02(List<Registro> lista){
        Double actual = (lista.get(lista.size()-1).getValor());
        String descripcion = "La gráfica muestra la variación porcentual del valor"
                + " de las exportaciones de la serie histórica "
                + lista.get(0).getAnio() + " a " + this.anio
                + ", donde se relaciona la cantidad exportada en determinado trimestre comparada"
                + " con el correspondiente del año anterior.\n\n"
                + "Así, el " + this.trimestre + " trimestre de " + this.anio
                + ", tuvo un crecimiento interanual de " + getNumero(actual) + "\\%.";
        return descripcion;
    }
    
    private String des1_03(List<Registro> lista){
        int actual = (int)(lista.get(lista.size()-1).getValor());
        int anterior = (int)(lista.get(lista.size()-2).getValor());
        int anioAnterior = (int)(lista.get(lista.size()-5).getValor());
        String descripcion = "El valor de las importaciones del "
                + this.trimestre + " trimestre de " + this.anio
                + " se registró en " + getNumero(((double)actual)/1000000)
                + " millones de dólares estadounidenses, " + getPorcentaje(actual, anterior) 
                + " que lo registrado en el " 
                + " trimestre anterior y " + getPorcentaje(actual, anioAnterior)
                + " que lo registrado en el " + (lista.get(lista.size()-5).getTrimestreCadena())
                + " trimestre del año " + (lista.get(lista.size()-5).getAnio()) + ".";
        return descripcion;
    }
    
    private String des1_04(List<Registro> lista){
        Double actual = (lista.get(lista.size()-1).getValor());
        String descripcion = "La gráfica muestra la variación porcentual del valor"
                + " de las importaciones de la serie histórica "
                + lista.get(0).getAnio() + " a " + this.anio
                + ", donde se relaciona la cantidad importado en determinado trimestre comparada"
                + " con el correspondiente del año anterior.\n\n"
                + "Así, el " + this.trimestre + " trimestre de " + this.anio
                + ", tuvo un crecimiento interanual de " + getNumero(actual) + "\\%.";
        return descripcion;
    }
    
    private String des1_05(List<Registro> lista){
        Double actual = (lista.get(lista.size()-1).getValor());
        Double anterior = (lista.get(lista.size()-2).getValor());
        String descripcion = "El " + this.trimestre + " trimestre de " + this.anio
                + " presentó un " + getDeficit(actual, anterior)
                + " de las importaciones de la serie histórica "
                + lista.get(0).getAnio() + " a " + this.anio
                + ", donde se relaciona la cantidad importado en determinado trimestre comparada"
                + " con el correspondiente del año anterior.\n\n"
                + "Así, el " + this.trimestre + " de " + this.anio
                + ", tuvo un crecimiento interanual de " + getNumero(actual) + "\\%.";
        return descripcion;
    }
    
    private String des1_06(List<Registro> lista){
        Double actual = (lista.get(lista.size()-1).getValor());
        String descripcion = "La gráfica muestra la balanza comercial relativa,"
                + " que es el cociente entre el saldo de la balanza comercial y la suma"
                + " de las exportaciones y las importaciones totales del país.\n\n"
                + "Cuando la balanza comercial relativa está entre -1 y 0 hay una cantidad"
                + " mayor de importaciones que de exportaciones, mientras que cuando se"
                + " encuentra entre 0 y 1 indica que hay una mayor cantidad de exportaciones"
                + " que de importaciones. En el caso de Guatemala, la balanza comercial relativa"
                + " indica que, en general, se " + getBalanza(actual);
        return descripcion;
    }
    
    private String des1_07(List<RegistroDoble> lista){
        int totalExportaciones = 0, totalImportaciones = 0;
        for(int i=0; i<lista.size(); i++){
            totalExportaciones += lista.get(i).getValor();
            totalImportaciones += lista.get(i).getValor2();
        }
        String descripcion = "La gráfica muestra el saldo de la balanza comercial"
                + " de Guatemala respecto a cada uno de los continentes, en el " 
                + this.trimestre + " trimestre de " + this.anio
                + ".\n\n"
                + "El continente con mayor volumen de operaciones con Guatemala es "
                + lista.get(lista.size()-1).getNombre()
                + " (con " + getNumero(lista.get(lista.size()-1).getValor()/totalExportaciones)
                + "\\% de las exportaciones y "
                + getNumero(lista.get(lista.size()-1).getValor2()/totalImportaciones)
                + "\\% de las importaciones). En segundo lugar está el continente de "
                + lista.get(lista.size()-2).getNombre()
                + " (con " + getNumero(lista.get(lista.size()-2).getValor()/totalExportaciones)
                + "\\% en exportaciones y "
                + getNumero(lista.get(lista.size()-2).getValor2()/totalImportaciones)
                + "\\% en importaciones). En tercer lugar está el continente de "
                + lista.get(lista.size()-3).getNombre()
                + " (" + getNumero(lista.get(lista.size()-3).getValor()/totalExportaciones)
                + "\\% de exportaciones y "
                + getNumero(lista.get(lista.size()-3).getValor2()/totalImportaciones)
                + "\\% de importaciones)."
                ;
        
        return descripcion;
    }
    
    private String des1_08(List<RegistroDoble> lista){
        int totalExportaciones = 0, totalImportaciones = 0;
        for(int i=0; i<lista.size(); i++){
            totalExportaciones += lista.get(i).getValor();
            totalImportaciones += lista.get(i).getValor2();
        }
        String descripcion = "De los países Centroamericanos, "
                + lista.get(lista.size()-1).getNombre()
                + " es el principal socio comercial, "
                + "con " + getNumero(lista.get(lista.size()-1).getValor()/totalExportaciones)
                + "\\% del total de las exportaciones realizadas a los seis países de la región y el "
                + getNumero(lista.get(lista.size()-1).getValor2()/totalImportaciones)
                + "\\% del total de las importaciones.\n\n "
                + lista.get(lista.size()-2).getNombre()
                + " ocupa el segundo lugar respecto a las exportaciones e "
                + "imporatciones realizadas, con un " 
                + getNumero(lista.get(lista.size()-2).getValor()/totalExportaciones)
                + "\\% de las exportaciones y "
                + getNumero(lista.get(lista.size()-2).getValor2()/totalImportaciones)
                + "\\% en las importaciones."
                ;
        
        return descripcion;
    }
    
    private String getBalanza(Double val){
        if(val==0) return "mantiene un equilibrio fiscal.";
        else if(val<0) return "importa más de lo que se exporta.";
        else return "exporta más de lo que se importa";
    }
    
    private String getDeficit(Double val, Double anterior){
        if(val==0){
            return "un equilibrio fiscal.";
        }
        //se tomará en cuenta que siempre es déficit (no entra al else)
        else if(val<0){
            val = -val;
            anterior = -anterior;
            if(val==anterior) return "un déficit comercial de US$ " + (val) + " millones."
                    + " La variación intertrimestral muestra que el déficit del "
                    + this.trimestre + " trimestre de " + this.anio
                    + " fue el mismo comparado con el trimestre anterior."
                    ;
            else if(val > anterior) return "un déficit comercial de US$ " + (val) + " millones."
                    + " La variación intertrimestral muestra que el déficit del "
                    + this.trimestre + " trimestre de " + this.anio
                    + " fue " + getNumero((1-(anterior/val))*100) 
                    + "\\% mayor comparado con el trimestre anterior."
                    ;
            else return "un déficit comercial de US$ " + (val) + " millones."
                    + " La variación intertrimestral muestra que el déficit del "
                    + this.trimestre + " trimestre de " + this.anio
                    + " fue " + getNumero((1-(val/anterior))*100) 
                    + "\\% menor comparado con el trimestre anterior."
                    ;
        }
        else{
            return "un superávit comercial de US$ " + (val) + " millones."
                    + " La variación intertrimestral muestra que el superávit del "
                    + this.trimestre + " trimestre de " + this.anio
                    + " fue " + getNumero((1-(val/anterior))*100) 
                    + "\\% mayor comparado con el trimestre anterior."
                    ;
        }
    }
      
    private Double getPromedio(List<Registro> lista){
        Double total = 0.0;
        for(int i=0; i<lista.size(); i++){
            total += lista.get(i).getValor();
        } 
        return total/lista.size();
    }
    
    private List<Registro> ordenarLista(List<Registro> lista){
        //ordenando lista de menor a mayor
        for(int i=0; i<lista.size(); i++){
            for(int j=0; j<lista.size(); j++){
                if(lista.get(i).getValor() > lista.get(j).getValor()){
                    Registro aux = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, aux);
                }
            }
        }
        return lista;
    }
    
    //devuelve la lista de registrosDepto con el porcentaje como valor dentro de todos los valores sumados de la lista
    private List<RegistroDepto> getPorcentajeTotal(List<RegistroDepto> lista){
        int total = 0;
        for(int i=0; i<lista.size(); i++){
            total += lista.get(i).getValor();
        }
        List<RegistroDepto> porcentajes = new ArrayList();
        for(int i=0; i<lista.size(); i++){
            if(!lista.get(i).getNombre().toLowerCase().equals("ignorado")){
                Double porcentaje = lista.get(i).getValor()/total;
                porcentajes.add(new RegistroDepto(lista.get(i).getNombre(), porcentaje.toString()));
            }
        }
        //ordenando lista de menor a mayor
        for(int i=0; i<porcentajes.size(); i++){
            for(int j=0; j<porcentajes.size(); j++){
                if(porcentajes.get(i).getValor() > porcentajes.get(j).getValor()){
                    RegistroDepto aux = porcentajes.get(i);
                    porcentajes.set(i, porcentajes.get(j));
                    porcentajes.set(j, aux);
                }
            }
        }
        return porcentajes;
    }
    
    private Double getIgnorado(List<RegistroDepto> lista){
        int total = 0;
        for(int i=0; i<lista.size(); i++){
            total += lista.get(i).getValor();
        }
        for(int i=0; i<lista.size(); i++){
            if(lista.get(i).getNombre().toLowerCase().equals("ignorado")){
                return lista.get(i).getValor()/total;
            }
        }
        return -1.0;
    }
    
    private String getPorcentaje(int a, int b){
        if(a==b) return "misma cantidad";
        else if(a<b) return getNumero((double)(1-(a/b))) + "\\% menos";
        else return getNumero((double)(1-(b/a))) + "\\% más";
    }
    
    private String getNumero(Double n){
        DecimalFormat decim = new DecimalFormat("0.00");
        return (decim.format(n));
    }
    
    private void escribirTEX(String nombre, String texto){
         File file = new File(nombre);
         file.getParentFile().setReadable(true, false);
        file.getParentFile().setExecutable(true, false);
        file.getParentFile().setWritable(true, false);
        file.getParentFile().mkdirs();
        String fileName = nombre + ".tex";
         Path textFile = Paths.get(fileName);
         List<String> lines = new ArrayList<>();
         lines.add(texto);
        try {
            Files.write(textFile, lines, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    //lee el archivo csv y retorna la lista con los registros de una serie histórica
    private List<Registro> getRegistros(String csv){
        File f = new File(ruta, csv + ".csv");
        BufferedReader br = null;
        String line = "";
        boolean encabezado = true;
        
        List<Registro> lista = new ArrayList();
        try {
            br = new BufferedReader(new FileReader(f.getAbsolutePath()));
        while ((line = br.readLine()) != null) {
                    if(encabezado){
                        encabezado = false;
                    }
                    else{
            String[] valores = line.split(";");
                        if(csv.equals("1_05")){
                            lista.add(new Registro(valores[0], valores[3]));
                        }
                        else{
                            lista.add(new Registro(valores[0], valores[1]));
                        }
                    }
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lista;
    }
    
    //lee el archivo csv y retorna la lista con los registros por departamento
    private List<RegistroDepto> getRegistrosDepartamento(String csv){
        File f = new File(ruta, csv + ".csv");
        BufferedReader br = null;
        String line = "";
        boolean encabezado = true;
        
        List<RegistroDepto> lista = new ArrayList();
        try {
            br = new BufferedReader(new FileReader(f.getAbsolutePath()));
        while ((line = br.readLine()) != null) {
                    if(encabezado){
                        encabezado = false;
                    }
                    else{
            String[] valores = line.split(";");
                        lista.add(new RegistroDepto(valores[0], valores[1]));
                    }
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        //ordenando lista de menor a mayor
        for(int i=0; i<lista.size(); i++){
            for(int j=0; j<lista.size(); j++){
                if(lista.get(i).getValor() > lista.get(j).getValor()){
                    RegistroDepto aux = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, aux);
                }
            }
        }
        
        return lista;
    }
    
    //lee el archivo csv y retorna la lista con los registros por departamento
    private List<RegistroDoble> getRegistrosDobles(String csv){
        File f = new File(ruta, csv + ".csv");
        BufferedReader br = null;
        String line = "";
        boolean encabezado = true;
        
        List<RegistroDoble> lista = new ArrayList();
        try {
            br = new BufferedReader(new FileReader(f.getAbsolutePath()));
        while ((line = br.readLine()) != null) {
                    if(encabezado){
                        encabezado = false;
                    }
                    else{
            String[] valores = line.split(";");
                        lista.add(new RegistroDoble(valores[0], valores[1], valores[2]));
                    }
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        //ordenando lista de menor a mayor
        for(int i=0; i<lista.size(); i++){
            for(int j=0; j<lista.size(); j++){
                if((lista.get(i).getSuma()) > lista.get(j).getSuma()){
                    RegistroDoble aux = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, aux);
                }
            }
        }
        
        return lista;
    }
    
}
