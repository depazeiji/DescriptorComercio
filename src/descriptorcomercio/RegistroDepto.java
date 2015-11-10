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
class RegistroDepto {
    
    private Double valor;
    private String nombre;

    public RegistroDepto(String nombre, String val2) {
        this.valor = (Double.parseDouble(val2.replaceAll("\"", "")));
        this.nombre = nombre;
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
    
}
