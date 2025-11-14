package Model;

public class Veiculos {

    public int getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(int codcliente) {
        this.codcliente = codcliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    private int codigo;
    private int codcliente;
    private String placa;
    private String modelo;
    private String tipo;

    public boolean isValid() {
        return placa != null && !placa.trim().isEmpty() &&
               modelo != null && !modelo.trim().isEmpty() &&
               tipo != null && !tipo.trim().isEmpty() &&
               codcliente > 0;
    }
}
