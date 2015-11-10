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
class RegistroDoble {
    
    private Double valor, valor2;
    private String nombre;

    public RegistroDoble(String nombre, String val, String val2) {
        this.valor = (Double.parseDouble(val.replaceAll("\"", "")));
        this.valor2 = (Double.parseDouble(val2.replaceAll("\"", "")));
        this.nombre = nombre;
    }
    
    public Double getSuma(){
        return valor + valor2;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the valor2
     */
    public Double getValor2() {
        return valor2;
    }

    /**
     * @param valor2 the valor2 to set
     */
    public void setValor2(Double valor2) {
        this.valor2 = valor2;
    }
    
}
