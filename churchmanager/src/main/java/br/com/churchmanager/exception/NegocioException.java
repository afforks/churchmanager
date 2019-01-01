package br.com.churchmanager.exception;

public class NegocioException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4737674795693349469L;

	public NegocioException(String msg) {
		super(msg);
	}
}
