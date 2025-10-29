/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Documentos
 */
public class Operacional {

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the precoPrimeiraHora
     */
    public double getPrecoPrimeiraHora() {
        return precoPrimeiraHora;
    }

    /**
     * @param precoPrimeiraHora the precoPrimaeiraHora to set
     */
    public void setPrecoPrimeiraHora(double precoPrimeiraHora) {
        this.precoPrimeiraHora = precoPrimeiraHora;
    }

    /**
     * @return the precoHorasAdicionais
     */
    public double getPrecoHorasAdicionais() {
        return precoHorasAdicionais;
    }

    /**
     * @param precoHorasAdicionais the precoHorasAdicionais to set
     */
    public void setPrecoHorasAdicionais(double precoHorasAdicionais) {
        this.precoHorasAdicionais = precoHorasAdicionais;
    }

    /**
     * @return the precoDiaria
     */
    public double getPrecoDiaria() {
        return precoDiaria;
    }

    /**
     * @param precoDiaria the precoDiaria to set
     */
    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }
    private int codigo;
    private double precoPrimeiraHora;
    private double precoHorasAdicionais;
    private double precoDiaria;
    
    
}
