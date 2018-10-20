package br.edu.undra.mysqlsimpleclient;

import br.edu.undra.keyhandler.KeyHandler;
import br.edu.undra.servicodepersistencia.ServicoDePersistencia;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollBar;

public class MysqlSimpleClientGUI extends javax.swing.JFrame {

    private final Connection connection = null;
    private final Color foregroundColor;
    private final List<String> sqlStatements;
    private KeyHandler keyHandler;

    String nomeServidorSqlStatements;
    String nomeBancoDadosSqlStatements = "sql_statements";
    String nomeUsuarioSqlStatements;
    String senhaUsuarioSqlStatements;
    int queryProgressBarSleep = 250;

    /**
     * Creates new form NewJFrame
     */
    public MysqlSimpleClientGUI() throws ClassNotFoundException, SQLException {

        this.sqlStatements = new ArrayList();

        initComponents();

        setQueryExecutingProgress(0);
        queryExecutingJProgressBar.setVisible(false);

        setUpSqlStatements();

        //centralizes the frame
        setBounds(
                (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2, getWidth(), getHeight());
        foregroundColor = consoleJTextArea.getForeground();

        scrollerJScrollPane.getVerticalScrollBar()
                .addAdjustmentListener((AdjustmentEvent event) -> {
                    JScrollBar scrollBar = (JScrollBar) event.getAdjustable();
                    scrollBar.getModel().setValue(scrollBar.getMaximum());
                }
                );

        setUpKeyHandlers();
    }

    private void setUpSqlStatements() throws ClassNotFoundException, SQLException {

        nomeServidorSqlStatements = servidorJTextField.getText();    //caminho do servidor do BD

        nomeUsuarioSqlStatements = usuarioJTextField.getText();        //nome de um usuário de seu BD

        senhaUsuarioSqlStatements = new String(senhausuarioJPasswordField.getPassword());      //sua senha de acesso

        ServicoDePersistencia.setUpConexaoJDBC(nomeServidorSqlStatements, nomeBancoDadosSqlStatements, nomeUsuarioSqlStatements, senhaUsuarioSqlStatements);

        String tableName = "sql_statements";
        
        try {//se nao tiver tabela cria uma novinha

            ServicoDePersistencia.executarQuery("select * from "+ tableName);

        } catch (Exception e) {

            if (e.getMessage().contains("doesn't exist")) {
                //System.out.println("doesn't exist");
            }

            
            String createTableQuery = "create table " + tableName + "(n_indexstate int not null, c_statement text not null)";

            ServicoDePersistencia.executar(createTableQuery);

//            String query = "insert into " + tableName + " values(0,'testando')";
//
//            ServicoDePersistencia.executar(query);
            
            

        }

        String result = "";
        ResultSet resultSet;

        try {

            resultSet = ServicoDePersistencia.executarQuery("select * from sql_statements");

            while (resultSet.next()) {

                try {

                    int i = 1;

                    while (true) {

                        sqlStatements.add((Integer) resultSet.getObject(i), (String) resultSet.getObject(i + 1));;

                        i++;
                    }

                } catch (Exception e) {
                }
            }

        } catch (Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollerJScrollPane = new javax.swing.JScrollPane();
        consoleJTextArea = new javax.swing.JTextArea();
        queryJInputText = new javax.swing.JTextField();
        runSQLJButton = new javax.swing.JButton();
        clearJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        servidorJTextField = new javax.swing.JTextField();
        nomeBancoJTextField = new javax.swing.JTextField();
        usuarioJTextField = new javax.swing.JTextField();
        senhausuarioJPasswordField = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        reconnecToServerJButton = new javax.swing.JButton();
        queryExecutingJProgressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 221, 216));

        consoleJTextArea.setEditable(false);
        consoleJTextArea.setBackground(new java.awt.Color(1, 1, 1));
        consoleJTextArea.setColumns(20);
        consoleJTextArea.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        consoleJTextArea.setForeground(new java.awt.Color(24, 255, 0));
        consoleJTextArea.setRows(5);
        consoleJTextArea.setText("mysql>");
        consoleJTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        scrollerJScrollPane.setViewportView(consoleJTextArea);

        queryJInputText.setBackground(new java.awt.Color(254, 254, 254));
        queryJInputText.setForeground(new java.awt.Color(124, 145, 142));
        queryJInputText.setText("insert query");
        queryJInputText.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        queryJInputText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                queryInputTextOnClickEventHandler(evt);
            }
        });
        queryJInputText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                queryKeyPressedHandler(evt);
            }
        });

        runSQLJButton.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        runSQLJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-play-30-ios.png"))); // NOI18N
        runSQLJButton.setToolTipText("EXECUTA SQL STATEMENT");
        runSQLJButton.setActionCommand("");
        runSQLJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        runSQLJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                runButtonMouseClickedHandler(evt);
            }
        });

        clearJButton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        clearJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/broom.png"))); // NOI18N
        clearJButton.setToolTipText("LIMPAR CONSOLE");
        clearJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearJButtonMouseClickHandler(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("CONEXÃO JDBC");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel2.setText("servidor");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel3.setText("nome do banco");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel4.setText("nome usuario");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel5.setText("senha usuario");

        servidorJTextField.setText("localhost");
        servidorJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servidorJTextFieldActionPerformed(evt);
            }
        });

        nomeBancoJTextField.setText("comercio");
        nomeBancoJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeBancoJTextFieldActionPerformed(evt);
            }
        });

        usuarioJTextField.setText("usermysql");
        usuarioJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioJTextFieldActionPerformed(evt);
            }
        });

        senhausuarioJPasswordField.setText("cursomysql");

        reconnecToServerJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-rotate-right-50.png"))); // NOI18N
        reconnecToServerJButton.setToolTipText("RECONECTAR");
        reconnecToServerJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reconnecToServerJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reconectarButtonHandler(evt);
            }
        });

        queryExecutingJProgressBar.setBackground(new java.awt.Color(0, 255, 123));
        queryExecutingJProgressBar.setValue(50);
        queryExecutingJProgressBar.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(nomeBancoJTextField)
                            .addComponent(servidorJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(usuarioJTextField))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(senhausuarioJPasswordField)))
                        .addGap(54, 54, 54)
                        .addComponent(reconnecToServerJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(506, 506, 506))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(scrollerJScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(queryExecutingJProgressBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(queryJInputText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(clearJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(runSQLJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(servidorJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usuarioJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeBancoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5)
                                .addComponent(senhausuarioJPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(reconnecToServerJButton))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(runSQLJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(queryJInputText, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(queryExecutingJProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollerJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void queryInputTextOnClickEventHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_queryInputTextOnClickEventHandler

        queryJInputText.setText("");//insert query

    }//GEN-LAST:event_queryInputTextOnClickEventHandler

    private void runButtonMouseClickedHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runButtonMouseClickedHandler

        if ("insert query".trim().equals(queryJInputText.getText()) || (queryJInputText.getText().trim().equals(""))) {
            return;
        }

        if (queryJInputText.getText().trim().toUpperCase().equals("CLEAR")) {
            clearJButtonMouseClickHandler(null);
            queryJInputText.setText("insert query");
            return;
        }

        consoleJTextArea.setForeground(foregroundColor);

        new Thread(new SqlRunnable(this)).start();

    }//GEN-LAST:event_runButtonMouseClickedHandler

    private int indexLastSqlStatement = 0;
    private void queryKeyPressedHandler(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_queryKeyPressedHandler
        try {
            keyHandler.executeHandler(evt.getKeyCode());

        } catch (Exception ex) {
            Logger.getLogger(MysqlSimpleClientGUI.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_queryKeyPressedHandler

    private void clearJButtonMouseClickHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearJButtonMouseClickHandler
        consoleJTextArea.setText("");
        consoleJTextArea.setForeground(foregroundColor);
        printToFakeMysqlConsole("\nmysql> ");

    }//GEN-LAST:event_clearJButtonMouseClickHandler

    private void servidorJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servidorJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_servidorJTextFieldActionPerformed

    private void nomeBancoJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeBancoJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeBancoJTextFieldActionPerformed

    private void usuarioJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioJTextFieldActionPerformed

    private void reconectarButtonHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reconectarButtonHandler

        connect();

    }//GEN-LAST:event_reconectarButtonHandler

    private void setUpKeyHandlers() {
        keyHandler = new KeyHandler();

        Runnable handler;
        handler = () -> {
            runButtonMouseClickedHandler(null);
        };
        keyHandler.addHandler(KeyHandler.ENTER, handler);

        handler = () -> {
            if (sqlStatements.size() > 0) {

                indexLastSqlStatement++;

                if (indexLastSqlStatement > sqlStatements.size() - 1) {

                    indexLastSqlStatement = 0;

                }

                String sqlStatement = sqlStatements.get(indexLastSqlStatement);
                queryJInputText.setText(sqlStatement);

            }
        };
        keyHandler.addHandler(KeyHandler.UP, handler);

        handler = () -> {
            if (sqlStatements.size() > 0) {

                indexLastSqlStatement--;

                if (indexLastSqlStatement < 0) {

                    indexLastSqlStatement = sqlStatements.size() - 1;

                }

                String sqlStatement = sqlStatements.get(indexLastSqlStatement);

                queryJInputText.setText(sqlStatement);

            }
        };
        keyHandler.addHandler(KeyHandler.DOWN, handler);

        handler = () -> {
            queryJInputText.setText("");
        };
        keyHandler.addHandler(KeyHandler.ESCAPE, handler);

    }

    class SqlRunnable implements Runnable {

        public SqlRunnable(MysqlSimpleClientGUI client) {
            clientGUI = client;
        }

        MysqlSimpleClientGUI clientGUI;

        @Override
        public void run() {

            try {

                handleExecuteQuery(queryJInputText.getText());

            } catch (SQLException ex) {

                handleExecute(queryJInputText.getText());

            }

        }

        private void handleExecute(String query) {

            setQueryExecutingProgress(0);
            queryExecutingJProgressBar.setVisible(true);

            try {

                ServicoDePersistencia.executar(query);
                setQueryExecutingProgress(30);
                clientGUI.printToFakeMysqlConsole("\nmysql> " + query + " [OK] ");
                addAndPersistSqlStatement(query);
                setQueryExecutingProgress(99);
                clientGUI.queryJInputText.setText("insert query");

                new Thread(() -> {
                    try {
                        Thread.sleep(queryProgressBarSleep);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(MysqlSimpleClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    setQueryExecutingProgress(0);
                    queryExecutingJProgressBar.setVisible(false);
                }).start();

            } catch (SQLException ex1) {
                clientGUI.printErrorToMysqlConsoleFake(ex1.getMessage());
            }

        }

        private void handleExecuteQuery(String query) throws SQLException {

            setQueryExecutingProgress(0);
            queryExecutingJProgressBar.setVisible(true);

            String result = "";

            ResultSet resultSet = ServicoDePersistencia.executarQuery(query);

            setQueryExecutingProgress(30);

            result = ServicoDePersistencia.resultSetToString(resultSet);

            setQueryExecutingProgress(60);

            if (!result.trim().equals("")) {
                result = "\n\n" + result;
                clientGUI.printToFakeMysqlConsole("\nmysql> " + query);
                clientGUI.printToFakeMysqlConsole(result);
            } else {
                clientGUI.printToFakeMysqlConsole("\nmysql> " + query + "[OK]");
                clientGUI.printToFakeMysqlConsole("\nSEM RESULTADO");
            }

            setQueryExecutingProgress(80);
            addAndPersistSqlStatement(query);

            clientGUI.queryJInputText.setText("insert query");

            setQueryExecutingProgress(100);

            new Thread(() -> {
                try {
                    Thread.sleep(queryProgressBarSleep);

                } catch (InterruptedException ex) {
                    Logger.getLogger(MysqlSimpleClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                setQueryExecutingProgress(0);
                queryExecutingJProgressBar.setVisible(false);
            }).start();
        }

        private void addAndPersistSqlStatement(String sqlStatement) throws SQLException {
            if (!sqlStatements.contains(sqlStatement)) {

                sqlStatements.add(sqlStatement);

                try {
                    persistirSqlStatement(sqlStatement);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MysqlSimpleClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        private void persistirSqlStatement(String sqlStatement) throws SQLException, ClassNotFoundException {

            Connection c = ServicoDePersistencia.getConexaoJDBC(nomeServidorSqlStatements, nomeBancoDadosSqlStatements, nomeUsuarioSqlStatements, senhaUsuarioSqlStatements);

            String query = "insert into sql_statements values(" + (sqlStatements.size() - 1) + ",'" + sqlStatement + "')";

            try {

                ServicoDePersistencia.executarQuery(query, c);

            } catch (SQLException e) {

                try {

                    ServicoDePersistencia.executar(query, c);

                } catch (SQLException exx) {
                    throw exx;
                }

            }
        }

    }

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
            java.util.logging.Logger.getLogger(MysqlSimpleClientGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MysqlSimpleClientGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MysqlSimpleClientGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MysqlSimpleClientGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    MysqlSimpleClientGUI gUI = new MysqlSimpleClientGUI();
                    gUI.setVisible(true);
                    gUI.connect();

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MysqlSimpleClientGUI.class
                            .getName()).log(Level.SEVERE, null, ex);

                } catch (SQLException ex) {
                    Logger.getLogger(MysqlSimpleClientGUI.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
    }

    private void connect() {

        setQueryExecutingProgress(0);
        queryExecutingJProgressBar.setVisible(true);

        try {

            String serverName = servidorJTextField.getText();    //caminho do servidor do BD

            setQueryExecutingProgress(20);

            String mydatabase = nomeBancoJTextField.getText();        //nome do seu banco de dados

            String username = usuarioJTextField.getText();        //nome de um usuário de seu BD      

            String password = new String(senhausuarioJPasswordField.getPassword());      //sua senha de acesso

            setQueryExecutingProgress(50);

            ServicoDePersistencia.setUpConexaoJDBC(serverName, mydatabase, username, password);

            setQueryExecutingProgress(100);

            printToFakeMysqlConsole("\nmysql> Conectado com sucesso!");

        } catch (ClassNotFoundException e) {  //Driver não encontrado

            printErrorToMysqlConsoleFake("\nmysql> O driver expecificado nao foi encontrado.");
            printErrorToMysqlConsoleFake(e.getMessage());

        } catch (SQLException e) {

            printErrorToMysqlConsoleFake("\nmysql> Nao foi possivel conectar ao Banco de Dados.");
            printErrorToMysqlConsoleFake(e.getMessage());
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(queryProgressBarSleep);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MysqlSimpleClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                setQueryExecutingProgress(0);
                queryExecutingJProgressBar.setVisible(false);
            }
        }).start();

    }

    public void printToFakeMysqlConsole(String msg) {
        consoleJTextArea.setForeground(foregroundColor);
        consoleJTextArea.setText(consoleJTextArea.getText() + msg);
    }

    public void printErrorToMysqlConsoleFake(String msg) {

        consoleJTextArea.setForeground(Color.red);
        consoleJTextArea.setText(consoleJTextArea.getText() + "\nmysql> " + msg);
//        consoleJTextArea.setForeground(current);
    }

    public void setQueryExecutingProgress(int newValue) {
        queryExecutingJProgressBar.setValue(newValue);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearJButton;
    private javax.swing.JTextArea consoleJTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nomeBancoJTextField;
    private javax.swing.JProgressBar queryExecutingJProgressBar;
    private javax.swing.JTextField queryJInputText;
    private javax.swing.JButton reconnecToServerJButton;
    private javax.swing.JButton runSQLJButton;
    private javax.swing.JScrollPane scrollerJScrollPane;
    private javax.swing.JPasswordField senhausuarioJPasswordField;
    private javax.swing.JTextField servidorJTextField;
    private javax.swing.JTextField usuarioJTextField;
    // End of variables declaration//GEN-END:variables
}
