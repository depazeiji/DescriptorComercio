/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descriptorcomercio;

/**
 *
 * @author ine031
 */

public class Registro {
    
    private int trimestre, anio;
    private double valor;
    
    public Registro(String val1, String val2){
        String[] fecha = val1.split("-");
        this.trimestre = (Integer.parseInt(fecha[0].substring(1, 2)));
        this.anio = (Integer.parseInt(fecha[1]));
        this.valor = (Double.parseDouble(val2.replaceAll("\"", "")));
    }

    /**
     * @return the trimestre
     */
    public int getTrimestre() {
        return trimestre;
    }

    /**
     * @param trimestre the trimestre to set
     */
    public void setTrimestre(int trimestre) {
        this.trimestre = trimestre;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public String getTrimestreCadena(){
        if(this.trimestre == 1) return "primer";
        else if(this.trimestre == 2) return "segundo";
        else if(this.trimestre == 3) return "tercer";
        else if(this.trimestre == 4) return "cuarto";
        else return "";
    }
   
}