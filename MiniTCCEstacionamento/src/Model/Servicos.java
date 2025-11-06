package Model;

public class Servicos {

    /**
     * @return the codfuncionario_entrada
     */
    public int getCodfuncionario_entrada() {
        return codfuncionario_entrada;
    }

    /**
     * @param codfuncionario_entrada the codfuncionario_entrada to set
     */
    public void setCodfuncionario_entrada(int codfuncionario_entrada) {
        this.codfuncionario_entrada = codfuncionario_entrada;
    }

    /**
     * @return the codfuncionario_saida
     */
    public int getCodfuncionario_saida() {
        return codfuncionario_saida;
    }

    /**
     * @param codfuncionario_saida the codfuncionario_saida to set
     */
    public void setCodfuncionario_saida(int codfuncionario_saida) {
        this.codfuncionario_saida = codfuncionario_saida;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the valorTotal
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

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
     * @return the codvaga
     */
    public int getCodCat() {
        return codCat;
    }

    /**
     * @param codCat the codvaga to set
     */
    public void setCodCat(int codCat) {
        this.codCat = codCat;
    }

    /**
     * @return the codveiculo
     */
    public int getCodveiculo() {
        return codveiculo;
    }

    /**
     * @param codveiculo the codveiculo to set
     */
    public void setCodveiculo(int codveiculo) {
        this.codveiculo = codveiculo;
    }

    /**
     * @return the datafinal
     */
    public String getDatafinal() {
        return datafinal;
    }

    /**
     * @param datafinal the datafinal to set
     */
    public void setDatafinal(String datafinal) {
        this.datafinal = datafinal;
    }

    /**
     * @return the datainicial
     */
    public String getDatainicial() {
        return datainicial;
    }

    /**
     * @param datainicial the datainicial to set
     */
    public void setDatainicial(String datainicial) {
        this.datainicial = datainicial;
    }

    /**
     * @return the horaentrada
     */
    public String getHoraentrada() {
        return horaentrada;
    }

    /**
     * @param horaentrada the horaentrada to set
     */
    public void setHoraentrada(String horaentrada) {
        this.horaentrada = horaentrada;
    }

    /**
     * @return the horasaida
     */
    public String getHorasaida() {
        return horasaida;
    }

    /**
     * @param horasaida the horasaida to set
     */
    public void setHorasaida(String horasaida) {
        this.horasaida = horasaida;
    }
    private int codigo;
    private int codCat;
    private int codveiculo;
    private int codfuncionario_entrada;
    private int codfuncionario_saida;
    private String datafinal;
    private String datainicial;
    private String horaentrada;
    private String horasaida;
    private String status;
    private double valorTotal;
}
