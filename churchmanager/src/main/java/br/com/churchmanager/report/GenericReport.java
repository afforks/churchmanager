package br.com.churchmanager.report;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GenericReport {

	private GenericReport() {
	}

	private static final Logger LOGGER = Logger.getLogger(GenericReport.class.getName());

	private static Connection conexao;

	public static void gerarPdfComJRBeanCollectionDataSource(Map<String, Object> mapa, List<?> lista,
			String nomeArquivoJasper, String nomeDoArquivoDeSaida, boolean download) {
		reportFactory(mapa, lista, nomeArquivoJasper, nomeDoArquivoDeSaida, download, false);
	}

	public static void gerarPdfComConnection(Map<String, Object> mapa, String nomeArquivoJasper,
			String nomeDoArquivoDeSaida, boolean download) {
		reportFactory(mapa, null, nomeArquivoJasper, nomeDoArquivoDeSaida, download, true);
	}

	private static void reportFactory(Map<String, Object> mapa, List<?> lista, String nomeArquivoJasper,
			String nomeDoArquivoDeSaida, boolean download, boolean connection) {
		try {
			File jasper = new File(FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/resources/report/" + nomeArquivoJasper + ".jasper"));
			JasperPrint jasperPrint = null;
			if (connection) {
				jasperPrint = JasperFillManager.fillReport(jasper.getPath(), mapa, getConexao());
			} else {
				jasperPrint = JasperFillManager.fillReport(jasper.getPath(), mapa,
						new JRBeanCollectionDataSource(lista));
			}

			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			response.setContentType("application/pdf");
			if (download) {
				response.addHeader("content-disposition", "attachment; filename=\\" + nomeDoArquivoDeSaida + ".pdf");
			}

			ServletOutputStream stream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			stream.flush();
			stream.close();
			closeConexao();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException | JRException arg10) {
			LOGGER.info(arg10.getMessage());
		}

	}

	private static Connection getConexao() {
		try {
			InitialContext e = new InitialContext();
			Context envContext = (Context) e.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/churchmanagerDS");
			conexao = ds.getConnection();
		} catch (SQLException | NamingException arg2) {
			LOGGER.info(arg2.getMessage());
		}

		return conexao;
	}

	private static void closeConexao() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException arg0) {
				LOGGER.info(arg0.getMessage());
			}
		}

	}
}