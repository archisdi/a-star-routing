/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestpath;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Archie
 */
public class GUI extends javax.swing.JFrame {

    public static ArrayList<city> cityList = new ArrayList<>();
    public static ArrayList<path> pathList = new ArrayList<>();
    public static ArrayList<path> closed = new ArrayList<>();
    public static city start, stop;

    public GUI() {
        initComponents();
    }

    public void setT() {
        ArrayList<city> PathTemp;
        double ValueTemp, DistanceTemp;
        final int first = 0;
        int mark = 1;

        insertData(); //Memasukkan Data Kota
        pointStart(); //Menunjuk Kota awal

        initial.setText(start.getName());
        finish.setText(stop.getName());

        while (pathList.get(first).getCity(pathList.get(first).getPath().size() - 1).getStat() != "end") {
            int nCity = (pathList.get(first).getPath().size()) - 1;

            for (RelativeDistance kota : pathList.get(first).getCity(nCity).getAllConection()) {
                path a = new path();
                a.setValue(pathList.get(first).getPathDistance() + kota.getDistance() + kota.getNextCity().getHeur()); //Menentukan Value Path

                for (int i = 0; i <= (pathList.get(first).getPath().size() - 1); i++) {
                    a.addPath(pathList.get(first).getCity(i));
                }

                a.addPath(kota.getNextCity());
                a.setLevel(a.getPath().size() - 1);
                a.setPathDistance(pathList.get(first).getPathDistance());
                a.addPathDistance(kota.getDistance());
                pathList.add(a);

            }
            closed.add(pathList.get(first));
            pathList.remove(0);

            Collections.sort(pathList, path.ValueCompare);

            log.append(System.getProperty("line.separator"));
            log.append("------------------  Proses Ke-" + mark + "  ------------------");
            log.append(System.getProperty("line.separator"));
            log.append(System.getProperty("line.separator"));
            log.append("----------- Opened Path -----------");
            log.append(System.getProperty("line.separator"));

            for (int i = 0; i <= (pathList.size() - 1); i++) {

                log.append("Path Value         : " + (pathList.get(i).getValue()));
                log.append(System.getProperty("line.separator"));
                log.append("City in Path       : ");

                for (int j = 0; j <= (pathList.get(i).getPath().size() - 1); j++) {
                    log.append(System.getProperty("line.separator"));
                    log.append(" -" + pathList.get(i).getPath().get(j).getName());
                }

                log.append(System.getProperty("line.separator"));
                log.append("-----------------------------");
                log.append(System.getProperty("line.separator"));
            }

            log.append(System.getProperty("line.separator"));
            log.append("----------- Closed Path -----------");
            log.append(System.getProperty("line.separator"));

            for (int i = 0; i <= (closed.size() - 1); i++) {
                log.append("Path Value         : " + (closed.get(i).getValue()));
                log.append(System.getProperty("line.separator"));
                log.append("City in Path       : ");
                for (int j = 0; j <= (closed.get(i).getPath().size() - 1); j++) {
                    log.append(System.getProperty("line.separator"));
                    log.append(" -" + closed.get(i).getPath().get(j).getName());
                }
                log.append(System.getProperty("line.separator"));
                log.append("-----------------------------");
                log.append(System.getProperty("line.separator"));
            }
            mark++;
        }

        String p = "";
        for (city kota : pathList.get(first).getPath()) {
            p = p + (kota.getName() + "  ");
        }

        best.setText(p);
        value.setText(String.valueOf(pathList.get(first).getValue()));

    }

    public static void insertData() {
        city Bobbia = new city("Bobbia", "start", 10.5);
        city Piacenza = new city("Piacenza", "none", 10);
        city Carpi = new city("Carpi", "none", 8);
        city Terme = new city("Terme", "none", 7);
        city Emilia = new city("Emilia", "none", 6);
        city Imola = new city("Imola", "none", 5);
        city Faenza = new city("Faenza", "none", 4);
        city Cesena = new city("Cesena", "none", 4.5);
        city Forli = new city("Forli", "none", 2);
        city Ferrara = new city("Ferrara", "none", 5);
        city Rimini = new city("Rimini", "none", 0.5);
        city Ravena = new city("Ravena", "end", 0);

        //Create Object Relative Distance
        //Boobia
        RelativeDistance BOtoPI = new RelativeDistance(Piacenza, 5);
        RelativeDistance BOtoTER = new RelativeDistance(Terme, 3);
        RelativeDistance BOtoCES = new RelativeDistance(Cesena, 15);

        //Piacenza
        RelativeDistance PItoCAR = new RelativeDistance(Carpi, 3);
        RelativeDistance PItoTER = new RelativeDistance(Terme, 3);

        //Carpi
        RelativeDistance CARtoFE = new RelativeDistance(Ferrara, 8);
        RelativeDistance CARtoEM = new RelativeDistance(Emilia, 2);

        //Terme
        RelativeDistance TERtoEM = new RelativeDistance(Emilia, 2);
        RelativeDistance TERtoFA = new RelativeDistance(Faenza, 3);

        //Emilia
        RelativeDistance EMtoIM = new RelativeDistance(Imola, 2);

        //Imola
        RelativeDistance IMtoFA = new RelativeDistance(Faenza, 1);
        RelativeDistance IMtoFOR = new RelativeDistance(Forli, 3);

        //Faenza
        RelativeDistance FAtoCES = new RelativeDistance(Cesena, 6);
        RelativeDistance FAtoFOR = new RelativeDistance(Forli, 2);

        //Ferrara
        RelativeDistance FERtoIM = new RelativeDistance(Imola, 3);
        RelativeDistance FERtoRAV = new RelativeDistance(Ravena, 6);

        //Forli
        RelativeDistance FORtoRAV = new RelativeDistance(Ravena, 3);
        RelativeDistance FORtoCES = new RelativeDistance(Cesena, 2);

        //Cesena
        RelativeDistance CEStoRIM = new RelativeDistance(Rimini, 5);

        //Rimini
        RelativeDistance RIMtoRAV = new RelativeDistance(Ravena, 1);

        //initialize distance between city
        Bobbia.addConnection(BOtoPI);
        Bobbia.addConnection(BOtoTER);
        Bobbia.addConnection(BOtoCES);

        Piacenza.addConnection(PItoCAR);
        Piacenza.addConnection(PItoTER);

        Carpi.addConnection(CARtoFE);
        Carpi.addConnection(CARtoEM);

        Terme.addConnection(TERtoEM);
        Terme.addConnection(TERtoFA);

        Emilia.addConnection(EMtoIM);

        Imola.addConnection(IMtoFA);
        Imola.addConnection(IMtoFOR);

        Faenza.addConnection(FAtoCES);
        Faenza.addConnection(FAtoFOR);

        Ferrara.addConnection(FERtoIM);
        Ferrara.addConnection(FERtoRAV);

        Forli.addConnection(FORtoRAV);
        Forli.addConnection(FORtoCES);

        Cesena.addConnection(CEStoRIM);

        Rimini.addConnection(RIMtoRAV);

        //insert all city to list
        cityList.add(Bobbia);
        cityList.add(Piacenza);
        cityList.add(Carpi);
        cityList.add(Terme);
        cityList.add(Emilia);
        cityList.add(Imola);
        cityList.add(Faenza);
        cityList.add(Ferrara);
        cityList.add(Forli);
        cityList.add(Cesena);
        cityList.add(Rimini);
        cityList.add(Ravena);
    }

    public static void showData() {
        for (city kota : cityList) {
            System.out.println("City Name : " + kota.getName());
            System.out.println("Heuristic : " + kota.getHeur());

            System.out.println("Next Connection :");
            for (RelativeDistance nextC : kota.getAllConection()) {
                System.out.println("       - " + nextC.getNextCity().getName() + " : " + nextC.getDistance() + " KM, " + nextC.getNextCity().getHeur() + " Heuristic");
            }

            System.out.println();
        }
    }

    public static void pointStart() {
        for (city kota : cityList) {
            if (kota.getStat() == "start") {
                start = kota;
            } else if (kota.getStat() == "end") {
                stop = kota;
            }
        }
        path a = new path();
        a.addPath(start);
        pathList.add(a);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        initial = new javax.swing.JTextField();
        finish = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        best = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        value = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        log.setEditable(false);
        log.setColumns(20);
        log.setRows(5);
        jScrollPane1.setViewportView(log);

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Initial : ");

        initial.setEditable(false);
        initial.setBorder(null);
        initial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initialActionPerformed(evt);
            }
        });

        finish.setEditable(false);
        finish.setBorder(null);
        finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishActionPerformed(evt);
            }
        });

        jLabel2.setText("Finish : ");

        jLabel3.setText("Best Path :");

        best.setEditable(false);
        best.setBorder(null);

        jLabel4.setText("Cost :");

        value.setEditable(false);
        value.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(initial, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(220, 220, 220)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(best, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(initial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(best, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void initialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_initialActionPerformed

    private void finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_finishActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField best;
    private javax.swing.JTextField finish;
    private javax.swing.JTextField initial;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea log;
    private javax.swing.JTextField value;
    // End of variables declaration//GEN-END:variables
}
