/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.jasper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportTester {

    public static void main(String[] args) {
        try {
//             JasperReport jasperreport=  JasperCompileManager.compileReport(
//                     ReportTester.class.getResourceAsStream(
//                             "/lk/ijse/jasperreport/reports/Blank_A4.jrxml"));
//           

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = null;
            try {
                connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "ijse");
            } catch (SQLException ex) {
                Logger.getLogger(ReportTester.class.getName()).log(Level.SEVERE, null, ex);
            }

            JasperReport jasperreport = (JasperReport) JRLoader.loadObject(ReportTester.class.getResourceAsStream("/lk/ijse/thogakade/jasper/reports/Customer_A4.jasper"));
//            JasperReport jasperreport = (JasperReport) JRLoader.loadObject(ReportTester.class.getResourceAsStream("/lk/ijse/thogakade/jasper/reports/Item.jasper"));
//            JasperReport jasperreport = (JasperReport) JRLoader.loadObject(ReportTester.class.getResourceAsStream("/lk/ijse/thogakade/jasper/reports/PlaceOrderInvoice.jasper"));
            HashMap<String,Object> params = new HashMap<>();
            params.put("name","j");
            JasperPrint filledreport = JasperFillManager.fillReport(jasperreport, new HashMap<>(), connection);

            JasperReportViewer reportViewer = new JasperReportViewer(filledreport);
            reportViewer.setVisible(true);
            reportViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);

            //JasperViewer.viewReport(filledreport);
        } catch (JRException | ClassNotFoundException ex) {
            Logger.getLogger(ReportTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

