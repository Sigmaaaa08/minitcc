/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.ConClientes;
import Controller.ConFuncionarios;
import Controller.ConOperacional;
import Controller.ConVeiculos;
import Controller.ConServicos;
import Model.Clientes;
import Model.Funcionarios;
import Model.Operacional;
import Model.Servicos;
import Model.Veiculos;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.time.LocalDate;

/**
 *
 * @author LABINFO
 */
public class frmMenu extends javax.swing.JFrame {

    int codigo1;
    int codigo2;
    int codFuncionarioLogado;

    /**
     * Creates new form frmMenu
     */
    public frmMenu() {
    
        initComponents();
        
        try{
            //Mask para Telefone
          MaskFormatter mascaraTelefone = new MaskFormatter("(##) #####-####");
          mascaraTelefone.setPlaceholderCharacter('0'); // Permite caracteres inválidos
          mascaraTelefone.setValidCharacters("0123456789");
          txtTelefoneCliente1.setColumns(15);
          txtTelefoneFuncionario.setColumns(15);
          
          DefaultFormatterFactory factoryTelefone = new DefaultFormatterFactory(mascaraTelefone);
          txtTelefoneCliente1.setFormatterFactory(factoryTelefone);
          txtTelefoneFuncionario.setFormatterFactory(factoryTelefone);
          
         //Mask para Data
          MaskFormatter mascaraData = new MaskFormatter("##/##/####");
          mascaraData.setPlaceholderCharacter('_'); // Permite caracteres inválidos
          mascaraData.setValidCharacters("0123456789");
          txtDataEntrada.setColumns(10);
          txtDataSaida.setColumns(10);
          
          DefaultFormatterFactory factoryData = new DefaultFormatterFactory(mascaraData);
          txtDataEntrada.setFormatterFactory(factoryData);
          txtDataSaida.setFormatterFactory(factoryData);
 
          //Mask para Horário
          MaskFormatter mascaraHorario = new MaskFormatter("##:##:##");
          mascaraHorario.setPlaceholderCharacter('*'); // Permite caracteres inválidos
          mascaraHorario.setValidCharacters("0123456789");
          txtHoraEntrada.setColumns(8);
          txtHoraSaida.setColumns(8);
          
          DefaultFormatterFactory factoryHorario = new DefaultFormatterFactory(mascaraHorario);
          txtHoraEntrada.setFormatterFactory(factoryHorario);
          txtHoraSaida.setFormatterFactory(factoryHorario);
          
          
          //Mask para CPF
          MaskFormatter mascaraCPF = new MaskFormatter("###.###.###-##");
          mascaraCPF.setPlaceholderCharacter(' '); // Permite caracteres inválidos
          mascaraCPF.setValidCharacters("0123456789");
          txtCpfCliente1.setColumns(14);
          txtCpfFuncionario1.setColumns(14);
          
          DefaultFormatterFactory factoryCPF = new DefaultFormatterFactory(mascaraCPF);
          txtCpfCliente1.setFormatterFactory(factoryCPF);
          txtCpfFuncionario1.setFormatterFactory(factoryCPF);
          
          //Mask para preço
          MaskFormatter mascaraPreco = new MaskFormatter("##.##");
          mascaraPreco.setPlaceholderCharacter('0'); // Permite caracteres inválidos
          mascaraPreco.setValidCharacters("0123456789");
          txtValorPrimeiraHoraOperacional.setColumns(5);
          txtValorHorasOperacional.setColumns(5);
          txtValorDiariaOperacional.setColumns(5);
          
          DefaultFormatterFactory factoryPreco = new DefaultFormatterFactory(mascaraPreco);
          txtValorPrimeiraHoraOperacional.setFormatterFactory(factoryPreco);
          txtValorHorasOperacional.setFormatterFactory(factoryPreco);
          txtValorDiariaOperacional.setFormatterFactory(factoryPreco);
          
        }catch(ParseException  e){
             e.printStackTrace();
        }
        //Init dialogLogin
        dialogLogin.setTitle("Login");
        
        //Foca no campo Email
        txtUsuarioLogin.requestFocus();
        //Caso aperte enter acionará o bntLogar
        txtUsuarioLogin.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt){
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
        bntLogar.doClick();
        }
    }
    });
        //Init dialogCliente1
        dialogCadastroCliente.setTitle("Cadastro do Cliente/Veículo");
        
        //Init dialogOperacional
        dialogOperacional.setTitle("Sistema operacional de preços");
        
        //Init dialogFuncionário
        dialogCadastroFuncionario.setTitle("Cadastro do Funcionário");
        
    tableSourcePlaca.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 1) { // clique simples
            int linha = tableSourcePlaca.getSelectedRow(); // pega a linha clicada

            if (linha >= 0) { // garante que há uma linha selecionada
                try {
                    // Pega a placa da linha clicada
                    String placa = tableSourcePlaca.getValueAt(linha, 0).toString();
                    System.out.println("Placa selecionada: " + placa);

                    // Busca o veículo no banco
                    ConVeiculos conVeiculo = new ConVeiculos();
                    Veiculos veiculo = conVeiculo.pesquisar(placa); // método deve receber a placa

                    if (veiculo != null) {
                        // Busca o cliente relacionado
                        ConClientes conCliente = new ConClientes();
                        Clientes cliente = conCliente.pesquisar(veiculo.getCodcliente());

                        // Preenche os campos da tela
                        txtPlacaVeiculoServico.setText(veiculo.getPlaca());
                        txtModeloVeiculoServico.setText(veiculo.getModelo());

                        if (cliente != null) {
                            txtNomeClienteServico.setText(cliente.getNome());
                        } else {
                            txtNomeClienteServico.setText("Cliente não encontrado");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Veículo não encontrado!");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar dados: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
});

    
    txtNumPlaca.getDocument().addDocumentListener(new DocumentListener() {
    private void atualizarTabela() {
        ConVeiculos conVeiculo = new ConVeiculos();
        String placa = txtNumPlaca.getText();

        Vector cabecalhoVeiculo = new Vector();
        cabecalhoVeiculo.addElement("Placa");
        cabecalhoVeiculo.addElement("Modelo");

        Vector dados = conVeiculo.listarSourcePlaca(placa);
        tableSourcePlaca.setModel(new DefaultTableModel(dados, cabecalhoVeiculo));
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        atualizarTabela(); // quando o usuário digita
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        atualizarTabela(); // quando o usuário apaga
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        atualizarTabela(); // quando muda algum estilo (raramente usado)
    }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        dialogCadastroFuncionario = new javax.swing.JDialog();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtEmailFuncionario = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtNomeFuncionario = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cbStatusFuncionario = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtCpfFuncionario = new javax.swing.JTextField();
        bntPesquisarFuncionario = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        txtSenhaFuncionario = new javax.swing.JPasswordField();
        chbsenhafunci = new javax.swing.JCheckBox();
        txtTelefoneFuncionario = new javax.swing.JFormattedTextField();
        txtCpfFuncionario1 = new javax.swing.JFormattedTextField();
        jPanel14 = new javax.swing.JPanel();
        bntEditarFuncionario = new javax.swing.JButton();
        bntNovoFuncionario = new javax.swing.JButton();
        bntExcluirFuncionario = new javax.swing.JButton();
        bntCancelarFuncionario = new javax.swing.JButton();
        bntSalvarFuncionario1 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        bntListarFuncionario = new javax.swing.JButton();
        cbConsultarStatusFuncionario = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableListarFuncionario = new javax.swing.JTable();
        dialogLogin = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        passwordLoginSenhaFuncionario = new javax.swing.JPasswordField();
        bntLogar = new javax.swing.JButton();
        bntSairLogin = new javax.swing.JButton();
        chbsenhafunciLogar = new javax.swing.JCheckBox();
        bntCancelarLogin = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        txtUsuarioLogin = new javax.swing.JTextField();
        dialogOperacional = new javax.swing.JDialog();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        bntEditarOperacional = new javax.swing.JButton();
        bntCancelarOperacional = new javax.swing.JButton();
        txtValorHorasOperacional = new javax.swing.JFormattedTextField();
        txtValorDiariaOperacional = new javax.swing.JFormattedTextField();
        txtValorPrimeiraHoraOperacional = new javax.swing.JFormattedTextField();
        dialogCadastroCliente = new javax.swing.JDialog();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        bntVoltarCliente1 = new javax.swing.JButton();
        bntExcluirCliente1 = new javax.swing.JButton();
        bntEditarCliente1 = new javax.swing.JButton();
        bntSalvarCliente1 = new javax.swing.JButton();
        bntNovoCliente = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTelefoneCliente1 = new javax.swing.JFormattedTextField();
        txtCpfCliente1 = new javax.swing.JFormattedTextField();
        txtNomeCliente1 = new javax.swing.JFormattedTextField();
        jPanel34 = new javax.swing.JPanel();
        cbTipoVeiculo1 = new javax.swing.JComboBox<>();
        txtModeloVeiculo1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtPlacaVeiculo1 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bntPesquisarCliente1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtPesquisarPlacaVeiculo = new javax.swing.JFormattedTextField();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableListarCliente1 = new javax.swing.JTable();
        bntListarCliente1 = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        bntNovo1 = new javax.swing.JButton();
        bntSalvarServico1 = new javax.swing.JButton();
        bntExcluirServico1 = new javax.swing.JButton();
        bntEditarServico1 = new javax.swing.JButton();
        bntOperacional1 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtNumEntrada = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtModeloVeiculoServico = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        bntPesquisarServico1 = new javax.swing.JButton();
        txtNomeClienteServico = new javax.swing.JTextField();
        txtPlacaVeiculoServico = new javax.swing.JFormattedTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtQtdHoras1 = new javax.swing.JTextField();
        txtQtdDias1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtDataEntrada = new javax.swing.JFormattedTextField();
        txtDataEntrada = new javax.swing.JFormattedTextField();
        txtHoraSaida = new javax.swing.JFormattedTextField();
        txtHoraEntrada = new javax.swing.JFormattedTextField();
        txtDataSaida = new javax.swing.JFormattedTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtValorDiariaMenu = new javax.swing.JTextField();
        txtPrimeiraHoraMenu = new javax.swing.JTextField();
        txtValorHorasMenu = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableSourcePlaca = new javax.swing.JTable();
        txtNumPlaca = new javax.swing.JFormattedTextField();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableServico = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        cbStatusServico1 = new javax.swing.JComboBox<>();
        bntListarServico1 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        txtNomeFuncionarioLogado = new javax.swing.JLabel();
        bntDialogFuncionario = new javax.swing.JButton();
        bntSair = new javax.swing.JButton();
        bntDialogClienteVeiculo = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jLabel34.setText("Nome:");

        jLabel33.setText("CPF:");

        jLabel32.setText("Email:");

        txtNomeFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeFuncionarioActionPerformed(evt);
            }
        });

        jLabel36.setText("Telefone:");

        jLabel29.setText("Senha:");

        cbStatusFuncionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));

        jLabel37.setText("Status");

        txtCpfFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfFuncionarioActionPerformed(evt);
            }
        });

        bntPesquisarFuncionario.setText("Pesquisar");
        bntPesquisarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPesquisarFuncionarioActionPerformed(evt);
            }
        });

        jLabel49.setText("CPF:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpfFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntPesquisarFuncionario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCpfFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntPesquisarFuncionario)
                    .addComponent(jLabel49))
                .addContainerGap())
        );

        chbsenhafunci.setText("Exibir");
        chbsenhafunci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbsenhafunciActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmailFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(chbsenhafunci)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtSenhaFuncionario))))
                        .addGap(115, 115, 115)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(31, 31, 31))
                            .addComponent(cbStatusFuncionario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(18, 18, 18)
                                .addComponent(txtCpfFuncionario1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefoneFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(txtTelefoneFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtCpfFuncionario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addComponent(jLabel37)
                            .addGap(22, 22, 22))
                        .addComponent(cbStatusFuncionario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(txtEmailFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(txtSenhaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbsenhafunci)))
                .addContainerGap())
        );

        bntEditarFuncionario.setText("Editar");
        bntEditarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEditarFuncionarioActionPerformed(evt);
            }
        });

        bntNovoFuncionario.setText("Novo");
        bntNovoFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNovoFuncionarioActionPerformed(evt);
            }
        });

        bntExcluirFuncionario.setText("Excluir");
        bntExcluirFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExcluirFuncionarioActionPerformed(evt);
            }
        });

        bntCancelarFuncionario.setText("Cancelar");
        bntCancelarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCancelarFuncionarioActionPerformed(evt);
            }
        });

        bntSalvarFuncionario1.setText("Salvar");
        bntSalvarFuncionario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalvarFuncionario1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bntNovoFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntSalvarFuncionario1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntEditarFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntExcluirFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bntCancelarFuncionario)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntEditarFuncionario)
                    .addComponent(bntExcluirFuncionario)
                    .addComponent(bntCancelarFuncionario)
                    .addComponent(bntNovoFuncionario)
                    .addComponent(bntSalvarFuncionario1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Cadastro", jPanel10);

        bntListarFuncionario.setText("Listar");
        bntListarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntListarFuncionarioActionPerformed(evt);
            }
        });

        cbConsultarStatusFuncionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Ativo", "Inativo" }));

        jLabel25.setText("Status");

        tableListarFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tableListarFuncionario);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbConsultarStatusFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bntListarFuncionario)
                .addGap(39, 39, 39))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(bntListarFuncionario)
                    .addComponent(cbConsultarStatusFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Consultar", jPanel11);

        javax.swing.GroupLayout dialogCadastroFuncionarioLayout = new javax.swing.GroupLayout(dialogCadastroFuncionario.getContentPane());
        dialogCadastroFuncionario.getContentPane().setLayout(dialogCadastroFuncionarioLayout);
        dialogCadastroFuncionarioLayout.setHorizontalGroup(
            dialogCadastroFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogCadastroFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2))
        );
        dialogCadastroFuncionarioLayout.setVerticalGroup(
            dialogCadastroFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogCadastroFuncionarioLayout.createSequentialGroup()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jLabel24.setText("Senha:");

        bntLogar.setText("Logar");
        bntLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntLogarActionPerformed(evt);
            }
        });

        bntSairLogin.setText("Sair");
        bntSairLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSairLoginActionPerformed(evt);
            }
        });

        chbsenhafunciLogar.setText("Exibir");
        chbsenhafunciLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbsenhafunciLogarActionPerformed(evt);
            }
        });

        bntCancelarLogin.setText("Cancelar");
        bntCancelarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCancelarLoginActionPerformed(evt);
            }
        });

        jLabel35.setText("Email:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bntLogar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntCancelarLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntSairLogin))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsuarioLogin))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(passwordLoginSenhaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chbsenhafunciLogar))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(passwordLoginSenhaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chbsenhafunciLogar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntLogar)
                    .addComponent(bntSairLogin)
                    .addComponent(bntCancelarLogin))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout dialogLoginLayout = new javax.swing.GroupLayout(dialogLogin.getContentPane());
        dialogLogin.getContentPane().setLayout(dialogLoginLayout);
        dialogLoginLayout.setHorizontalGroup(
            dialogLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogLoginLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dialogLoginLayout.setVerticalGroup(
            dialogLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel20.setText("Valor 1ª Hora");

        jLabel21.setText("Valor Horas");

        jLabel22.setText("Valor Diaria");

        bntEditarOperacional.setText("Editar");
        bntEditarOperacional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEditarOperacionalActionPerformed(evt);
            }
        });

        bntCancelarOperacional.setText("Cancelar");
        bntCancelarOperacional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCancelarOperacionalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogOperacionalLayout = new javax.swing.GroupLayout(dialogOperacional.getContentPane());
        dialogOperacional.getContentPane().setLayout(dialogOperacionalLayout);
        dialogOperacionalLayout.setHorizontalGroup(
            dialogOperacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogOperacionalLayout.createSequentialGroup()
                .addGroup(dialogOperacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(dialogOperacionalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bntCancelarOperacional)
                        .addGap(35, 35, 35)
                        .addComponent(bntEditarOperacional))
                    .addGroup(dialogOperacionalLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txtValorPrimeiraHoraOperacional, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtValorHorasOperacional, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dialogOperacionalLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(dialogOperacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValorDiariaOperacional, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogOperacionalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dialogOperacionalLayout.setVerticalGroup(
            dialogOperacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogOperacionalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(dialogOperacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogOperacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorHorasOperacional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorPrimeiraHoraOperacional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValorDiariaOperacional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(dialogOperacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntCancelarOperacional)
                    .addComponent(bntEditarOperacional))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntVoltarCliente1.setText("Cancelar");
        bntVoltarCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntVoltarCliente1ActionPerformed(evt);
            }
        });

        bntExcluirCliente1.setText("Excluir");
        bntExcluirCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExcluirCliente1ActionPerformed(evt);
            }
        });

        bntEditarCliente1.setText("Editar");
        bntEditarCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEditarCliente1ActionPerformed(evt);
            }
        });

        bntSalvarCliente1.setText("Salvar");
        bntSalvarCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalvarCliente1ActionPerformed(evt);
            }
        });

        bntNovoCliente.setText("Novo");
        bntNovoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNovoClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(bntNovoCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntSalvarCliente1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntEditarCliente1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntExcluirCliente1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(bntVoltarCliente1))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntSalvarCliente1)
                    .addComponent(bntExcluirCliente1)
                    .addComponent(bntEditarCliente1)
                    .addComponent(bntVoltarCliente1)
                    .addComponent(bntNovoCliente))
                .addGap(34, 34, 34))
        );

        jLabel9.setText("Telefone:");

        jLabel10.setText("CPF:");

        jLabel15.setText("Nome:");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpfCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefoneCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel15)
                    .addComponent(jLabel9)
                    .addComponent(txtCpfCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefoneCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        cbTipoVeiculo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pequeno", "Médio", "Grande" }));
        cbTipoVeiculo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoVeiculo1ActionPerformed(evt);
            }
        });

        txtModeloVeiculo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModeloVeiculo1ActionPerformed(evt);
            }
        });

        jLabel12.setText("Tipo:");

        jLabel13.setText("Modelo:");

        jLabel14.setText("Placa:");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlacaVeiculo1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtModeloVeiculo1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTipoVeiculo1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(cbTipoVeiculo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtModeloVeiculo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlacaVeiculo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Cliente");

        jLabel2.setText("Veículo");

        bntPesquisarCliente1.setText("Pesquisar");
        bntPesquisarCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPesquisarCliente1ActionPerformed(evt);
            }
        });

        jLabel11.setText("Placa:");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1))))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarPlacaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntPesquisarCliente1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntPesquisarCliente1)
                    .addComponent(jLabel11)
                    .addComponent(txtPesquisarPlacaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane5.addTab("Cadastro", jPanel29);

        tableListarCliente1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(tableListarCliente1);

        bntListarCliente1.setText("Listar");
        bntListarCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntListarCliente1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addContainerGap(390, Short.MAX_VALUE)
                .addComponent(bntListarCliente1)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntListarCliente1))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Consulta", jPanel32);

        javax.swing.GroupLayout dialogCadastroClienteLayout = new javax.swing.GroupLayout(dialogCadastroCliente.getContentPane());
        dialogCadastroCliente.getContentPane().setLayout(dialogCadastroClienteLayout);
        dialogCadastroClienteLayout.setHorizontalGroup(
            dialogCadastroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogCadastroClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        dialogCadastroClienteLayout.setVerticalGroup(
            dialogCadastroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogCadastroClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bntNovo1.setText("Novo");
        bntNovo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNovo1ActionPerformed(evt);
            }
        });

        bntSalvarServico1.setText("Salvar");
        bntSalvarServico1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalvarServico1ActionPerformed(evt);
            }
        });

        bntExcluirServico1.setText("Excluir");
        bntExcluirServico1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExcluirServico1ActionPerformed(evt);
            }
        });

        bntEditarServico1.setText("Editar");
        bntEditarServico1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEditarServico1ActionPerformed(evt);
            }
        });

        bntOperacional1.setText("Operacional");
        bntOperacional1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOperacional1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bntNovo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntSalvarServico1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntEditarServico1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntExcluirServico1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bntOperacional1)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntSalvarServico1)
                    .addComponent(bntNovo1)
                    .addComponent(bntExcluirServico1)
                    .addComponent(bntEditarServico1)
                    .addComponent(bntOperacional1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel26.setText("Nº Entrada");

        txtNumEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumEntradaActionPerformed(evt);
            }
        });

        jLabel27.setText("Placa");

        jLabel28.setText("Modelo do Veículo");

        jLabel30.setText("Cliente");

        bntPesquisarServico1.setText("?");
        bntPesquisarServico1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPesquisarServico1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(txtNumEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntPesquisarServico1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPlacaVeiculoServico, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtModeloVeiculoServico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeClienteServico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jLabel30)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtModeloVeiculoServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bntPesquisarServico1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomeClienteServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPlacaVeiculoServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel26))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jLabel31.setText("Qtd. Dias");

        jLabel38.setText("Qtd. Horas");

        txtQtdHoras1.setEditable(false);

        txtQtdDias1.setEditable(false);

        jLabel39.setText("Data Entrada");

        jLabel40.setText("Hora Entrada");

        jLabel41.setText("Data Saída");

        jLabel42.setText("Hora Saída");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel40))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel42))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQtdDias1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel38)
                            .addComponent(txtQtdHoras1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHoraSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQtdDias1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQtdHoras1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoraSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel43.setText("Valor 1ª Hora");

        jLabel44.setText("Valor Horas");

        jLabel45.setText("Valor Diaria");

        txtValorDiariaMenu.setEditable(false);

        txtPrimeiraHoraMenu.setEditable(false);

        txtValorHorasMenu.setEditable(false);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel45))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(txtPrimeiraHoraMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtValorHorasMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel44))))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(txtValorDiariaMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrimeiraHoraMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorHorasMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtValorDiariaMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
        );

        jLabel46.setText("Valor Total");

        txtValorTotal.setEditable(false);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel46)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel47.setText("Localizar Veículo");

        tableSourcePlaca.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableSourcePlaca.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(tableSourcePlaca);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtNumPlaca))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Cadastro", jPanel15);

        tableServico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tableServico);

        jLabel48.setText("Status");

        cbStatusServico1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Pendente", "Finalizado" }));

        bntListarServico1.setText("Listar");
        bntListarServico1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntListarServico1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbStatusServico1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntListarServico1)
                        .addGap(14, 14, 14))))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntListarServico1)
                    .addComponent(cbStatusServico1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Consulta", jPanel22);

        bntDialogFuncionario.setText("Funcionário");
        bntDialogFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntDialogFuncionarioActionPerformed(evt);
            }
        });

        bntSair.setText("Sair");
        bntSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSairActionPerformed(evt);
            }
        });

        bntDialogClienteVeiculo.setText("Cliente");
        bntDialogClienteVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntDialogClienteVeiculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNomeFuncionarioLogado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bntDialogClienteVeiculo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntDialogFuncionario)
                .addGap(18, 18, 18)
                .addComponent(bntSair)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bntDialogFuncionario)
                            .addComponent(bntSair)
                            .addComponent(bntDialogClienteVeiculo)))
                    .addComponent(txtNomeFuncionarioLogado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane3)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeFuncionarioActionPerformed
        
    private void bntListarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntListarFuncionarioActionPerformed
        // TODO add your handling code here:
        Vector cabecalhoFuncionario = new Vector();
        try{
            cabecalhoFuncionario.addElement("Código");
            cabecalhoFuncionario.addElement("Nome");
            cabecalhoFuncionario.addElement("CPF");
            cabecalhoFuncionario.addElement("Telefone");
            cabecalhoFuncionario.addElement("Email");
            cabecalhoFuncionario.addElement("Senha");
            cabecalhoFuncionario.addElement("Status");
            
            if(!cbConsultarStatusFuncionario.getSelectedItem().equals("...")){
            ConFuncionarios conFuncionario = new ConFuncionarios();
            this.tableListarFuncionario.setModel(new DefaultTableModel(
            conFuncionario.listar(cbConsultarStatusFuncionario.getSelectedItem().toString()),cabecalhoFuncionario));  
            }else{
            ConFuncionarios conFuncionario = new ConFuncionarios();
            this.tableListarFuncionario.setModel(new DefaultTableModel(
                conFuncionario.listar(),cabecalhoFuncionario));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha durante "
                + "a exibição de dados");
        }
    }//GEN-LAST:event_bntListarFuncionarioActionPerformed

    private void bntNovoFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNovoFuncionarioActionPerformed
        // TODO add your handling code here:
        try{
            txtNomeFuncionario.setText("");
            txtCpfFuncionario.setText("");
            txtTelefoneFuncionario.setText("");
            txtEmailFuncionario.setText("");
            txtSenhaFuncionario.setText("");
            txtCpfFuncionario.setText("");
            
            chbsenhafunci.setSelected(false);
            
            cbStatusFuncionario.setSelectedIndex(0);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
        }
    }//GEN-LAST:event_bntNovoFuncionarioActionPerformed

    private void bntPesquisarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPesquisarFuncionarioActionPerformed
        //
       try {
            Funcionarios funcionario = new Funcionarios();
            ConFuncionarios conFuncionario = new ConFuncionarios();

            if (txtCpfFuncionario.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o CPF para realizar a pesquisa");
            } else {
                funcionario.setCpf(txtCpfFuncionario.getText());
                if(conFuncionario.pesquisar(funcionario.getCpf())!=null)
                funcionario = conFuncionario.pesquisar(funcionario.getCpf());
                else{
                JOptionPane.showMessageDialog(null, "Funcionário inválido ou não encontrado"); 
                return;
                } 

                this.txtCpfFuncionario.setText(funcionario.getCpf());
                this.txtNomeFuncionario.setText(funcionario.getNome());
                this.txtTelefoneFuncionario.setText(funcionario.getTelefone());
                this.txtEmailFuncionario.setText(funcionario.getEmail());
                this.txtSenhaFuncionario.setText(funcionario.getSenha());
                if (funcionario.getStatus().equals("ATIVO")) {
                    cbStatusFuncionario.setSelectedIndex(0);
                } else {
                    cbStatusFuncionario.setSelectedIndex(1);
                }
                this.codigo1 = funcionario.getCodigo();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
        }
    }//GEN-LAST:event_bntPesquisarFuncionarioActionPerformed

    private void txtNumEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumEntradaActionPerformed

    private void bntSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSairActionPerformed
        // TODO add your handling code here:
        try {
            this.dispose();

            this.dialogLogin.setSize(250, 200);
            this.dialogLogin.setLocationRelativeTo(null);
            this.dialogLogin.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
        }
    }//GEN-LAST:event_bntSairActionPerformed

    private void bntSairLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSairLoginActionPerformed
        // TODO add your handling code here:
        try {
            System.exit(0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
        }
    }//GEN-LAST:event_bntSairLoginActionPerformed

	    private void bntLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntLogarActionPerformed
                // TODO add your handling code here:
                try {
            ConFuncionarios conFunci = new ConFuncionarios();
            
            String usuario = txtUsuarioLogin.getText().toUpperCase();
            
            char[] senhaChar = passwordLoginSenhaFuncionario.getPassword();
            String senha = new String(senhaChar);
            Arrays.fill(senhaChar, '\0');
            Funcionarios funcionario = conFunci.logar(senha, usuario);

            if (funcionario != null) {
                frmMenu menu = new frmMenu();
                menu.setVisible(true);
                menu.setSize(780, 450);
                menu.setLocationRelativeTo(null);              

                menu.txtNomeFuncionarioLogado.setText(funcionario.getNome());
                this.codFuncionarioLogado = funcionario.getCodigo();

                ConOperacional conOperacional = new ConOperacional();
                Operacional operacional = conOperacional.precos();

                menu.txtDataEntrada.setText(conOperacional.getDate());
                menu.txtHoraEntrada.setText(conOperacional.getTime());

                menu.txtPrimeiraHoraMenu.setText(String.valueOf(operacional.getPrecoPrimeiraHora()));
                menu.txtValorHorasMenu.setText(String.valueOf(operacional.getPrecoHorasAdicionais()));
                menu.txtValorDiariaMenu.setText(String.valueOf(operacional.getPrecoDiaria()));
                
                Vector cabecalhoVeiculo = new Vector();
                ConVeiculos conVeiculo = new ConVeiculos();
           
                cabecalhoVeiculo.addElement("Placa"); // 1
                cabecalhoVeiculo.addElement("Modelo"); // 2

                menu.tableSourcePlaca.setModel(new DefaultTableModel(
                conVeiculo.listarSourcePlaca(), cabecalhoVeiculo));
                
                Vector cabecalhoServico = new Vector();
                ConServicos conServico = new ConServicos();
                
                cabecalhoServico.addElement("Nº Entrada"); // 1
                cabecalhoServico.addElement("Veículo"); // 1
                cabecalhoServico.addElement("Cliente"); // 1
                cabecalhoServico.addElement("D. Entrada"); // 1
                cabecalhoServico.addElement("D. Saída"); // 1
                cabecalhoServico.addElement("H. Entrada"); // 1
                cabecalhoServico.addElement("H. Saída"); // 1
                cabecalhoServico.addElement("Status"); // 1
                cabecalhoServico.addElement("Valor Total"); // 1
                
                menu.tableServico.setModel(new DefaultTableModel(
                conServico.listar(), cabecalhoServico));
                
                this.dialogLogin.dispose();
                this.dialogLogin.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Senha ou usuário inválido");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
                }
        
                /*frmMenu menu = new frmMenu();
                        menu.setVisible(true);
                        menu.setSize(700, 450);
                        menu.setLocationRelativeTo(null);

                       

                        ConOperacional conOperacional = new ConOperacional();
                        Operacional operacional = conOperacional.precos();

                        menu.txtDataEntrada.setText(conOperacional.getDate());
                        menu.txtHoraEntrada.setText(conOperacional.getTime());

                        menu.txtPrimeiraHoraMenu.setText(String.valueOf(operacional.getPrecoPrimeiraHora()));
                        menu.txtValorHorasMenu.setText(String.valueOf(operacional.getPrecoHorasAdicionais()));
                        menu.txtValorDiariaMenu.setText(String.valueOf(operacional.getPrecoDiaria()));
                        
                         Vector cabecalhoVeiculo = new Vector();
                ConVeiculos conVeiculo = new ConVeiculos();
           
                cabecalhoVeiculo.addElement("Placa"); // 1
                cabecalhoVeiculo.addElement("Modelo"); // 2

                menu.tableSourcePlaca.setModel(new DefaultTableModel(
                conVeiculo.listarSourcePlaca(), cabecalhoVeiculo));
                
                Vector cabecalhoServico = new Vector();
                ConServicos conServico = new ConServicos();
                
                cabecalhoServico.addElement("Nº Entrada"); // 1
                cabecalhoServico.addElement("Veículo"); // 1
                cabecalhoServico.addElement("Cliente"); // 1
                cabecalhoServico.addElement("D. Entrada"); // 1
                cabecalhoServico.addElement("D. Saída"); // 1
                cabecalhoServico.addElement("H. Entrada"); // 1
                cabecalhoServico.addElement("H. Saída"); // 1
                cabecalhoServico.addElement("Status"); // 1
                cabecalhoServico.addElement("Valor Total"); // 1
                
                menu.tableServico.setModel(new DefaultTableModel(
                conServico.listar(), cabecalhoServico));
                        
                        this.dialogLogin.dispose();
                        this.dialogLogin.setVisible(false);*/
    }//GEN-LAST:event_bntLogarActionPerformed

    private void bntDialogFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntDialogFuncionarioActionPerformed
        // TODO add your handling code here:
        try {
            this.dialogCadastroFuncionario.setVisible(true);
            this.dialogCadastroFuncionario.setSize(600, 400);
            this.dialogCadastroFuncionario.setLocationRelativeTo(null);

            Vector cabecalhoFuncionario = new Vector();
            try {
                cabecalhoFuncionario.addElement("Código");
                cabecalhoFuncionario.addElement("Nome");
                cabecalhoFuncionario.addElement("CPF");
                cabecalhoFuncionario.addElement("Telefone");
                cabecalhoFuncionario.addElement("Email");
                cabecalhoFuncionario.addElement("Senha");
                cabecalhoFuncionario.addElement("Status");

                ConFuncionarios conFuncionario = new ConFuncionarios();
                tableListarFuncionario.setModel(new DefaultTableModel(
                        conFuncionario.listar(), cabecalhoFuncionario));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu uma falha durante a exibição de dados");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
        }
    }//GEN-LAST:event_bntDialogFuncionarioActionPerformed

    private void bntOperacional1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOperacional1ActionPerformed
        // TODO add your handling code here:
        try {
            this.dialogOperacional.setVisible(true);
            this.dialogOperacional.setSize(600, 400);
            this.dialogOperacional.setLocationRelativeTo(null);

            ConOperacional conOperacional = new ConOperacional();
            Operacional operacional = conOperacional.precos();

            this.txtValorPrimeiraHoraOperacional.setText(String.valueOf(operacional.getPrecoPrimeiraHora()));
            this.txtValorHorasOperacional.setText(String.valueOf(operacional.getPrecoHorasAdicionais()));
            this.txtValorDiariaOperacional.setText(String.valueOf(operacional.getPrecoDiaria()));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
        }
    }//GEN-LAST:event_bntOperacional1ActionPerformed

    private void bntCancelarOperacionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelarOperacionalActionPerformed
        // TODO add your handling code here:
        try {
            this.dialogOperacional.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
        }
    }//GEN-LAST:event_bntCancelarOperacionalActionPerformed

    private void bntEditarOperacionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEditarOperacionalActionPerformed
        // TODO add your handling code here:
        try {
            Operacional operacional = new Operacional();
            ConOperacional conOperacional = new ConOperacional();

            if (txtValorDiariaOperacional.getText().isEmpty() || txtValorHorasOperacional.getText().isEmpty()
                    || txtValorPrimeiraHoraOperacional.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos, corretamente");
            } else {
                operacional.setPrecoPrimeiraHora(Double.parseDouble(txtValorPrimeiraHoraOperacional.getText()));
                operacional.setPrecoHorasAdicionais(Double.parseDouble(txtValorHorasOperacional.getText()));
                operacional.setPrecoDiaria(Double.parseDouble(txtValorDiariaOperacional.getText()));

                conOperacional.editar(operacional);
                JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!");

                operacional = conOperacional.precos();

                this.txtPrimeiraHoraMenu.setText(String.valueOf(operacional.getPrecoPrimeiraHora()));
                this.txtValorHorasMenu.setText(String.valueOf(operacional.getPrecoHorasAdicionais()));
                this.txtValorDiariaMenu.setText(String.valueOf(operacional.getPrecoDiaria()));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
        }
    }//GEN-LAST:event_bntEditarOperacionalActionPerformed

    private void bntEditarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEditarFuncionarioActionPerformed
        // TODO add your handling code here:
        try {
            Funcionarios funcionario = new Funcionarios();
            ConFuncionarios conFuncionario = new ConFuncionarios();

            if (txtNomeFuncionario.getText().trim().isEmpty()
                    || txtCpfFuncionario.getText().trim().isEmpty()
                    || txtTelefoneFuncionario.getText().trim().isEmpty()
                    || txtEmailFuncionario.getText().trim().isEmpty()
                    || txtSenhaFuncionario.getPassword().length == 0) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos, corretamente");
            } else {

                funcionario.setNome(txtNomeFuncionario.getText());
                funcionario.setTelefone(txtTelefoneFuncionario.getText());
                funcionario.setCpf(txtCpfFuncionario.getText());
                funcionario.setEmail(txtEmailFuncionario.getText());
                funcionario.setSenha(txtSenhaFuncionario.getText());
                funcionario.setStatus(cbStatusFuncionario.getSelectedItem().toString().toUpperCase());
                funcionario.setCodigo(this.codigo1);

                boolean funcionarioAtualizado = conFuncionario.editar(funcionario);
                if (funcionarioAtualizado) {
            JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum registro foi alterado.");
       }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
        }
    }//GEN-LAST:event_bntEditarFuncionarioActionPerformed

    private void bntExcluirFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExcluirFuncionarioActionPerformed
        // TODO add your handling code here:
        try {
            ConFuncionarios conFuncionario = new ConFuncionarios();
            if (this.codigo1 == 0) {
                JOptionPane.showMessageDialog(null, "Pesquise os campos do funcionárioa a ser excluido");
            } else {
                if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir?", "Confirme exclusão", JOptionPane.YES_NO_OPTION) != 1) {
                    conFuncionario.excluir(codigo1);
                    JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
        }
    }//GEN-LAST:event_bntExcluirFuncionarioActionPerformed

    private void bntCancelarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelarFuncionarioActionPerformed
        // TODO add your handling code here:
        try{
        this.dialogCadastroFuncionario.dispose();
        this.codigo1 = 0;
        }catch(Exception ex){
           JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
        }
    }//GEN-LAST:event_bntCancelarFuncionarioActionPerformed

    private void bntDialogClienteVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntDialogClienteVeiculoActionPerformed
        // TODO add your handling code here:
        try {
            this.dialogCadastroCliente.setVisible(true);
            this.dialogCadastroCliente.setSize(600, 400);
            this.dialogCadastroCliente.setLocationRelativeTo(null);

            Vector cabecalhoVeiculo = new Vector();
            try {
                //cabecalho.addElement("Código"); //0
                cabecalhoVeiculo.addElement("Nome"); // 2
                cabecalhoVeiculo.addElement("CPF"); // 2
                cabecalhoVeiculo.addElement("Telefone"); // 2
                cabecalhoVeiculo.addElement("Veículo"); // 3
                cabecalhoVeiculo.addElement("Modelo"); // 4
                cabecalhoVeiculo.addElement("Tipo"); // 5

                ConVeiculos conVeiculo = new ConVeiculos();
                tableListarCliente1.setModel(new DefaultTableModel(
                        conVeiculo.listarVeiculoCliente(), cabecalhoVeiculo));

            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, "Ocorreu uma falha durante a exibição de dados: " + error);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
        }
    }//GEN-LAST:event_bntDialogClienteVeiculoActionPerformed

    private void bntNovo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNovo1ActionPerformed
        // TODO add your handling code here:
        try {
            ConOperacional conOperacional = new ConOperacional();
            Operacional operacional = conOperacional.precos();

            this.txtDataEntrada.setText(conOperacional.getDate());
            this.txtHoraEntrada.setText(conOperacional.getTime());

            this.txtPrimeiraHoraMenu.setText(String.valueOf(operacional.getPrecoPrimeiraHora()));
            this.txtValorHorasMenu.setText(String.valueOf(operacional.getPrecoHorasAdicionais()));
            this.txtValorDiariaMenu.setText(String.valueOf(operacional.getPrecoDiaria()));

            this.txtPlacaVeiculoServico.setText("");
            this.txtNumEntrada.setText("");
            this.txtModeloVeiculoServico.setText("");
            this.txtNomeClienteServico.setText("");
            this.txtValorTotal.setText("");
            this.txtDataSaida.setText("");
            this.txtHoraSaida.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
        }
    }//GEN-LAST:event_bntNovo1ActionPerformed

    private void bntVoltarCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVoltarCliente1ActionPerformed
        // TODO add your handling code here:
        this.dialogCadastroCliente.dispose();
        this.codigo1 = 0;
    }//GEN-LAST:event_bntVoltarCliente1ActionPerformed

    private void bntExcluirCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExcluirCliente1ActionPerformed
        // TODO add your handling code here:
        try {
            ConVeiculos conVeiculo = new ConVeiculos();
            if (this.codigo1 == 0) {
                JOptionPane.showMessageDialog(null, "Pesquise os campos do funcionárioa a ser excluido");
            } else {
                if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir?", "Confirme exclusão", JOptionPane.YES_NO_OPTION) != 1) {
                    conVeiculo.excluir(this.codigo1);
                    JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
        }
    }//GEN-LAST:event_bntExcluirCliente1ActionPerformed

    private void bntEditarCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEditarCliente1ActionPerformed
        // TODO add your handling code here:
        try {
            Clientes cliente = new Clientes();
            ConClientes conCliente = new ConClientes();

            Veiculos veiculo = new Veiculos();
            ConVeiculos conVeiculo = new ConVeiculos();

            if (txtPlacaVeiculo1.getText().trim().isEmpty()
                    || txtModeloVeiculo1.getText().trim().isEmpty()
                    || txtNomeCliente1.getText().trim().isEmpty()
                    || txtCpfCliente1.getText().trim().isEmpty()
                    || txtTelefoneCliente1.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha os campos a partir da pesquisa");
            } else {
                cliente.setNome(txtNomeCliente1.getText());
                cliente.setTelefone(txtTelefoneCliente1.getText());
                cliente.setCpf(txtCpfCliente1.getText());
                cliente.setCodigo(this.codigo1);

                boolean clienteAtualizado = conCliente.editar(cliente);

                veiculo.setPlaca(txtPlacaVeiculo1.getText());
                veiculo.setModelo(txtModeloVeiculo1.getText());
                veiculo.setTipo(cbTipoVeiculo1.getSelectedItem().toString().toUpperCase());
                veiculo.setCodcliente(this.codigo1);
                veiculo.setCodigo(this.codigo2);

                boolean veiculoAtualizado = conVeiculo.editar(veiculo);
                
                if (clienteAtualizado && veiculoAtualizado) {
    JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
        } else {
    JOptionPane.showMessageDialog(null, "Nenhum registro foi alterado.");
       }
                
            Vector<String> cabecalho = new Vector<>();
            cabecalho.add("Placa");
            cabecalho.add("Modelo");

            tableSourcePlaca.setModel(new DefaultTableModel(
            conVeiculo.listarSourcePlaca(), cabecalho));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
        }
    }//GEN-LAST:event_bntEditarCliente1ActionPerformed

    private void bntPesquisarCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPesquisarCliente1ActionPerformed
        // TODO add your handling code here:
        try {
            Clientes cliente = new Clientes();
            ConClientes conCliente = new ConClientes();

            Veiculos veiculo = new Veiculos();
            ConVeiculos conVeiculo = new ConVeiculos();

            if (txtPesquisarPlacaVeiculo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha a PLACA para realizar a pesquisa");
            } else {
                veiculo.setPlaca(txtPesquisarPlacaVeiculo.getText());
                veiculo = conVeiculo.pesquisar(veiculo.getPlaca());
                
                cliente.setCodigo(veiculo.getCodcliente());
                cliente = conCliente.pesquisar(veiculo.getCodcliente());

                this.codigo1 = cliente.getCodigo();
                this.codigo2 = veiculo.getCodigo();
                
                txtCpfCliente1.setText(cliente.getCpf());
                txtNomeCliente1.setText(cliente.getNome());
                txtTelefoneCliente1.setText(cliente.getTelefone());
                
                txtPlacaVeiculo1.setText(veiculo.getPlaca());
                txtModeloVeiculo1.setText(veiculo.getModelo());
                switch (veiculo.getTipo()) {
                    case "PEQUENO" -> cbTipoVeiculo1.setSelectedIndex(0);
                    case "MÉDIO" -> cbTipoVeiculo1.setSelectedIndex(1);
                    case "GRANDE" -> cbTipoVeiculo1.setSelectedIndex(2);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
        }
    }//GEN-LAST:event_bntPesquisarCliente1ActionPerformed

    private void bntSalvarCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalvarCliente1ActionPerformed
        // TODO add your handling code here:
        try {
            Clientes cliente = new Clientes();
            ConClientes conCliente = new ConClientes();

            Veiculos veiculo = new Veiculos();
            ConVeiculos conVeiculo = new ConVeiculos();

            if (txtNomeCliente1.getText().trim().isEmpty()
                    || txtCpfCliente1.getText().trim().isEmpty()
                    || txtTelefoneCliente1.getText().trim().isEmpty()
                    || txtPlacaVeiculo1.getText().trim().isEmpty()
                    || txtModeloVeiculo1.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos, corretamente");
            } else {
                
                cliente.setNome(txtNomeCliente1.getText().trim());
                cliente.setTelefone(txtTelefoneCliente1.getText().trim());
                cliente.setCpf(txtCpfCliente1.getText().trim());
                
                if(conCliente.pesquisar(cliente.getCpf()) == null){
                    conCliente.cadastrar(cliente);
                    veiculo.setCodcliente(conCliente.IDCliente());
                }else{
                    cliente = conCliente.pesquisar(cliente.getCpf());
                    veiculo.setCodcliente(cliente.getCodigo());
                    txtNomeCliente1.setText(cliente.getNome());
                    txtTelefoneCliente1.setText(cliente.getTelefone());
                }

                veiculo.setPlaca(txtPlacaVeiculo1.getText().trim());
                veiculo.setModelo(txtModeloVeiculo1.getText().trim());
                veiculo.setTipo(cbTipoVeiculo1.getSelectedItem().toString().toUpperCase());
                
                if (conVeiculo.pesquisar(veiculo.getPlaca()) != null) {
                JOptionPane.showMessageDialog(null, "Esta placa já está cadastrada!");
                return;
                }
                
                conVeiculo.cadastrar(veiculo);
                
                //Atualizando a tabela de pesquisa de placa na tela principal
                Vector cabecalhoVeiculo = new Vector();
           
                cabecalhoVeiculo.addElement("Placa"); // 1
                cabecalhoVeiculo.addElement("Modelo"); // 2

                tableSourcePlaca.setModel(new DefaultTableModel(
                    conVeiculo.listarSourcePlaca(), cabecalhoVeiculo));

                JOptionPane.showMessageDialog(null, "registro cadastrado com sucesso!");
            }
            this.codigo1 = 0;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
        }
    }//GEN-LAST:event_bntSalvarCliente1ActionPerformed

    private void bntListarCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntListarCliente1ActionPerformed
        // TODO add your handling code here:
        Vector cabecalhoVeiculo = new Vector();
        try {
            //cabecalho.addElement("Código"); //0
            cabecalhoVeiculo.addElement("Nome"); // 2
            cabecalhoVeiculo.addElement("CPF"); // 2
            cabecalhoVeiculo.addElement("Telefone"); // 2
            cabecalhoVeiculo.addElement("Veículo"); // 3
            cabecalhoVeiculo.addElement("Modelo"); // 4
            cabecalhoVeiculo.addElement("Tipo"); // 5

            ConVeiculos conVeiculo = new ConVeiculos();
            tableListarCliente1.setModel(new DefaultTableModel(
                    conVeiculo.listarVeiculoCliente(), cabecalhoVeiculo));

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha durante a exibição de dados: " + error);
        }
    }//GEN-LAST:event_bntListarCliente1ActionPerformed

    private void cbTipoVeiculo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoVeiculo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoVeiculo1ActionPerformed

    private void txtModeloVeiculo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloVeiculo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloVeiculo1ActionPerformed

    private void bntSalvarServico1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalvarServico1ActionPerformed
        try {
            Servicos servico = new Servicos();
            Veiculos veiculo = new Veiculos();
            ConServicos conServico = new ConServicos();
            ConVeiculos conVeiculo = new ConVeiculos();
            ConOperacional conOperacional = new ConOperacional();

            String dataEntrada = txtDataEntrada.getText().trim();
            String horaEntrada = txtHoraEntrada.getText().trim();
            String dataSaida = txtDataSaida.getText().trim();
            String horaSaida = txtHoraSaida.getText().trim();

            if (dataEntrada.trim().isEmpty()
                    || horaEntrada.trim().isEmpty()
                    || txtModeloVeiculoServico.getText().trim().isEmpty()
                    || txtNomeCliente1.getText().trim().isEmpty()
                    || txtPlacaVeiculoServico.getText().trim().isEmpty()){

                JOptionPane.showMessageDialog(null, "Preencha os campos necessários (Data Entrada, Hora Entrada, Cliente)");
                return;
            }else{
            
                servico.setDatafinal(conOperacional.formatarDataParaAmericana(dataSaida));
                servico.setHorasaida(horaSaida);
                servico.setDatainicial(conOperacional.formatarDataParaAmericana(dataEntrada));
                servico.setHoraentrada(horaEntrada);
                servico.setStatus("Pendente");
                
            if(!dataSaida.trim().isEmpty()
               || !horaSaida.trim().isEmpty()){

                String placa = txtPlacaVeiculoServico.getText();
                veiculo = conVeiculo.pesquisar(placa);
                servico.setCodveiculo(veiculo.getCodigo());
                
                
                /*conOperacional.diferencaEmDias(dataEntrada, dataSaida);*/
                
                
                servico.setStatus("Finalizado");
            }
            

            conServico.editar(servico);

            bntNovo1ActionPerformed(evt);
            
          }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Erro no formato do número de entrada do serviço: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o serviço: " + ex.getMessage());
        }
    }//GEN-LAST:event_bntSalvarServico1ActionPerformed
    private void aplicarMascaras() {
        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.install((JFormattedTextField) txtCpfFuncionario1);
            cpfMask.install((JFormattedTextField) txtCpfCliente1);

            MaskFormatter telMask = new MaskFormatter("(##) #####-####");
            telMask.install((JFormattedTextField) txtTelefoneFuncionario);
            telMask.install((JFormattedTextField) txtTelefoneCliente1);

            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.install(txtDataEntrada);
            dateMask.install(txtDataSaida);

            MaskFormatter timeMask = new MaskFormatter("##:##");
            timeMask.install(txtHoraEntrada);
            timeMask.install(txtHoraSaida);

            MaskFormatter placaMask = new MaskFormatter("UUU-####");
            placaMask.install((JFormattedTextField) txtPlacaVeiculo1);
            placaMask.install((JFormattedTextField) txtPlacaVeiculoServico);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void bntExcluirServico1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExcluirServico1ActionPerformed
        // TODO add your handling code here:
        if (txtNumEntrada.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pesquise ou informe o código (Número de Entrada) do serviço a ser excluído.");
            return;
        }

        try {
            // Obtém o ID do serviço da caixa de texto (txtNumEntrada)
            int codigoServico = Integer.parseInt(txtNumEntrada.getText());

            // 1. Confirmação da Exclusão
            int resposta = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja excluir o serviço de código: " + codigoServico + "?",
                    "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {

                ConServicos controller = new ConServicos();
                controller.excluir(codigoServico);
                bntNovo1ActionPerformed(evt);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O campo Número de Entrada deve conter um código de serviço numérico válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bntExcluirServico1ActionPerformed

    private void bntEditarServico1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEditarServico1ActionPerformed
        if (txtNumEntrada.getText().isEmpty()
                || txtDataSaida.getText().trim().replace(" ", "").length() != 10
                || // Formato DD/MM/AAAA
                txtHoraSaida.getText().trim().replace(" ", "").length() != 5) {  // Formato HH:MM

            JOptionPane.showMessageDialog(this,
                    "Para finalizar a edição (saída), preencha o Código do Serviço, Data de Saída e Hora de Saída corretamente.",
                    "Dados Ausentes", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {

            String dataFinal = txtDataSaida.getText();
            String dataEntrada = txtDataEntrada.getText();
            String horaSaida = txtHoraSaida.getText();
            String horaEntrada = txtHoraEntrada.getText();

            Servicos servico = new Servicos();
            servico.setDatafinal(dataFinal);
            servico.setHorasaida(horaSaida);

            ConServicos controller = new ConServicos();

            controller.editar(servico);
            
            Vector cabecalhoVeiculo = new Vector();
                ConVeiculos conVeiculo = new ConVeiculos();
           
            cabecalhoVeiculo.addElement("Placa"); // 1
            cabecalhoVeiculo.addElement("Modelo"); // 2

            tableSourcePlaca.setModel(new DefaultTableModel(
                    conVeiculo.listarSourcePlaca(), cabecalhoVeiculo));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "O campo Número de Entrada deve ser um código numérico válido.",
                    "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Ocorreu um erro ao processar a edição: " + e.getMessage(),
                    "Erro Inesperado", JOptionPane.ERROR_MESSAGE);
        }    }//GEN-LAST:event_bntEditarServico1ActionPerformed

    private void bntListarServico1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntListarServico1ActionPerformed
        // TODO add your handling code here:
        Vector cabecalhoServico = new Vector();
                ConServicos conServico = new ConServicos();
                
                cabecalhoServico.addElement("Nº Entrada"); // 1
                cabecalhoServico.addElement("Veículo"); // 1
                cabecalhoServico.addElement("Cliente"); // 1
                cabecalhoServico.addElement("D. Entrada"); // 1
                cabecalhoServico.addElement("D. Saída"); // 1
                cabecalhoServico.addElement("H. Entrada"); // 1
                cabecalhoServico.addElement("H. Saída"); // 1
                cabecalhoServico.addElement("Status"); // 1
                cabecalhoServico.addElement("Valor Total"); // 1
                
                if(!cbStatusServico1.getSelectedItem().equals("...")){
                this.tableServico.setModel(new DefaultTableModel(
                conServico.listar(cbStatusServico1.getSelectedItem().toString()), cabecalhoServico));
                } else{
                this.tableServico.setModel(new DefaultTableModel(
                conServico.listar(), cabecalhoServico));    
                }
    }//GEN-LAST:event_bntListarServico1ActionPerformed

    private void bntPesquisarServico1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPesquisarServico1ActionPerformed
        // TODO add your handling code here:
        try {
            Servicos servico = new Servicos();
            ConServicos conServico = new ConServicos();

            Veiculos veiculo = new Veiculos();
            ConVeiculos conVeiculo = new ConVeiculos();
            
            Clientes cliente = new Clientes();
            ConClientes conCliente = new ConClientes();
            
            ConOperacional conOperacional = new ConOperacional();

            if (txtNumEntrada.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o Nº de Entrada para realizar a pesquisa");
            } else {
                servico.setCodigo(Integer.parseInt(txtNumEntrada.getText()));
                servico = conServico.pesquisar(servico.getCodigo());
                
                veiculo.setCodigo(servico.getCodveiculo());
                veiculo = conVeiculo.pesquisarVeiculoServico(veiculo.getCodigo());
                
                cliente.setCodigo(veiculo.getCodcliente());
                cliente = conCliente.pesquisar(cliente.getCodigo());

                this.txtModeloVeiculoServico.setText(veiculo.getModelo());
                this.txtPlacaVeiculoServico.setText(veiculo.getPlaca());
                this.txtNomeClienteServico.setText(cliente.getNome());
                this.txtDataEntrada.setText(conOperacional.formatarDataParaBrasileira(servico.getDatainicial()));
                this.txtDataSaida.setText(conOperacional.formatarDataParaBrasileira(servico.getDatafinal()));
                this.txtHoraEntrada.setText(servico.getHoraentrada());
                this.txtHoraSaida.setText(servico.getHorasaida());
                this.txtValorTotal.setText(String.valueOf(servico.getValorTotal()));
            }   
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
        }
    }//GEN-LAST:event_bntPesquisarServico1ActionPerformed

    private void bntNovoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNovoClienteActionPerformed
        // TODO add your handling code here:
         try{
            txtPesquisarPlacaVeiculo.setText("");
            txtNomeCliente1.setText("");
            txtTelefoneCliente1.setText("");
            txtCpfCliente1.setText("");
            txtPlacaVeiculo1.setText("");
            txtModeloVeiculo1.setText("");
            
            cbTipoVeiculo1.setSelectedIndex(0);
            
            this.codigo1 = 0;
            this.codigo2 = 0;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
        }
    }//GEN-LAST:event_bntNovoClienteActionPerformed

    private void chbsenhafunciLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbsenhafunciLogarActionPerformed
        // TODO add your handling code here:
        if (chbsenhafunciLogar.isSelected()) {
            passwordLoginSenhaFuncionario.setEchoChar((char) 0);
            chbsenhafunciLogar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/hide.png")));

        } else {
            passwordLoginSenhaFuncionario.setEchoChar('*');
            chbsenhafunciLogar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/view.png")));
        }
    }//GEN-LAST:event_chbsenhafunciLogarActionPerformed

    private void bntCancelarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelarLoginActionPerformed
        // TODO add your handling code here:
        try {
            txtUsuarioLogin.setText("");
            passwordLoginSenhaFuncionario.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
        }
    }//GEN-LAST:event_bntCancelarLoginActionPerformed

    private void txtCpfFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfFuncionarioActionPerformed

    private void bntSalvarFuncionario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalvarFuncionario1ActionPerformed
        // TODO add your handling code here:
        try {
            Funcionarios funcionario = new Funcionarios();
            ConFuncionarios conFuncionario = new ConFuncionarios();
            
            if(!txtNomeFuncionario.getText().trim().isEmpty()
               || !txtCpfFuncionario.getText().trim().isEmpty()
               || !txtTelefoneFuncionario.getText().trim().isEmpty()
               || !txtEmailFuncionario.getText().trim().isEmpty()
               || txtSenhaFuncionario.getPassword().length > 0){
                
            funcionario.setNome(txtNomeFuncionario.getText());
            funcionario.setCpf(txtCpfFuncionario.getText());
            funcionario.setTelefone(txtTelefoneFuncionario.getText());
            funcionario.setEmail(txtEmailFuncionario.getText());
                char[] senhaChar = txtSenhaFuncionario.getPassword();
                String senha = new String(senhaChar);
                funcionario.setSenha(senha);
                Arrays.fill(senhaChar, ' ');
            funcionario.setStatus(cbStatusFuncionario.getSelectedItem().toString().toUpperCase());
            
            if(conFuncionario.pesquisar(funcionario.getCpf()) == null)
            conFuncionario.cadastrar(funcionario);
            else{
                JOptionPane.showMessageDialog(null, "Este funcionário já existe");
                return;
            } 
            JOptionPane.showMessageDialog(null, "registro cadastrado com sucesso!");
            this.codigo1 = 0;
            }else{
                JOptionPane.showMessageDialog(null, "Preencha todos os campos, corretamente");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
        }
    }//GEN-LAST:event_bntSalvarFuncionario1ActionPerformed

    private void chbsenhafunciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbsenhafunciActionPerformed
        // TODO add your handling code here:
        if (chbsenhafunci.isSelected()) {
            txtSenhaFuncionario.setEchoChar((char) 0);
            chbsenhafunci.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/hide.png")));

        } else {
            txtSenhaFuncionario.setEchoChar('*');
            chbsenhafunci.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/view.png")));
        }
    }//GEN-LAST:event_chbsenhafunciActionPerformed

    /**
     * @param args the command line arguments
     */
          
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmMenu menu = new frmMenu();

                if (!menu.isVisible()) {
                    menu.dialogLogin.setSize(250, 200);
                    menu.dialogLogin.setLocationRelativeTo(null);
                    menu.dialogLogin.setVisible(true);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCancelarFuncionario;
    private javax.swing.JButton bntCancelarLogin;
    private javax.swing.JButton bntCancelarOperacional;
    private javax.swing.JButton bntDialogClienteVeiculo;
    private javax.swing.JButton bntDialogFuncionario;
    private javax.swing.JButton bntEditarCliente1;
    private javax.swing.JButton bntEditarFuncionario;
    private javax.swing.JButton bntEditarOperacional;
    private javax.swing.JButton bntEditarServico1;
    private javax.swing.JButton bntExcluirCliente1;
    private javax.swing.JButton bntExcluirFuncionario;
    private javax.swing.JButton bntExcluirServico1;
    private javax.swing.JButton bntListarCliente1;
    private javax.swing.JButton bntListarFuncionario;
    private javax.swing.JButton bntListarServico1;
    private javax.swing.JButton bntLogar;
    private javax.swing.JButton bntNovo1;
    private javax.swing.JButton bntNovoCliente;
    private javax.swing.JButton bntNovoFuncionario;
    private javax.swing.JButton bntOperacional1;
    private javax.swing.JButton bntPesquisarCliente1;
    private javax.swing.JButton bntPesquisarFuncionario;
    private javax.swing.JButton bntPesquisarServico1;
    private javax.swing.JButton bntSair;
    private javax.swing.JButton bntSairLogin;
    private javax.swing.JButton bntSalvarCliente1;
    private javax.swing.JButton bntSalvarFuncionario1;
    private javax.swing.JButton bntSalvarServico1;
    private javax.swing.JButton bntVoltarCliente1;
    private javax.swing.JComboBox<String> cbConsultarStatusFuncionario;
    private javax.swing.JComboBox<String> cbStatusFuncionario;
    private javax.swing.JComboBox<String> cbStatusServico1;
    private javax.swing.JComboBox<String> cbTipoVeiculo1;
    private javax.swing.JCheckBox chbsenhafunci;
    private javax.swing.JCheckBox chbsenhafunciLogar;
    private javax.swing.JDialog dialogCadastroCliente;
    private javax.swing.JDialog dialogCadastroFuncionario;
    private javax.swing.JDialog dialogLogin;
    private javax.swing.JDialog dialogOperacional;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JPasswordField passwordLoginSenhaFuncionario;
    private javax.swing.JTable tableListarCliente1;
    private javax.swing.JTable tableListarFuncionario;
    private javax.swing.JTable tableServico;
    private javax.swing.JTable tableSourcePlaca;
    private javax.swing.JFormattedTextField txtCpfCliente1;
    private javax.swing.JTextField txtCpfFuncionario;
    private javax.swing.JFormattedTextField txtCpfFuncionario1;
    private javax.swing.JFormattedTextField txtDataEntrada;
    private javax.swing.JFormattedTextField txtDataSaida;
    private javax.swing.JTextField txtEmailFuncionario;
    private javax.swing.JFormattedTextField txtHoraEntrada;
    private javax.swing.JFormattedTextField txtHoraSaida;
    private javax.swing.JTextField txtModeloVeiculo1;
    private javax.swing.JTextField txtModeloVeiculoServico;
    private javax.swing.JFormattedTextField txtNomeCliente1;
    private javax.swing.JTextField txtNomeClienteServico;
    private javax.swing.JTextField txtNomeFuncionario;
    private javax.swing.JLabel txtNomeFuncionarioLogado;
    private javax.swing.JTextField txtNumEntrada;
    private javax.swing.JFormattedTextField txtNumPlaca;
    private javax.swing.JFormattedTextField txtPesquisarPlacaVeiculo;
    private javax.swing.JFormattedTextField txtPlacaVeiculo1;
    private javax.swing.JFormattedTextField txtPlacaVeiculoServico;
    private javax.swing.JTextField txtPrimeiraHoraMenu;
    private javax.swing.JTextField txtQtdDias1;
    private javax.swing.JTextField txtQtdHoras1;
    private javax.swing.JPasswordField txtSenhaFuncionario;
    private javax.swing.JFormattedTextField txtTelefoneCliente1;
    private javax.swing.JFormattedTextField txtTelefoneFuncionario;
    private javax.swing.JTextField txtUsuarioLogin;
    private javax.swing.JTextField txtValorDiariaMenu;
    private javax.swing.JFormattedTextField txtValorDiariaOperacional;
    private javax.swing.JTextField txtValorHorasMenu;
    private javax.swing.JFormattedTextField txtValorHorasOperacional;
    private javax.swing.JFormattedTextField txtValorPrimeiraHoraOperacional;
    private javax.swing.JTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables
}
