package br.com.churchmanager.exception;

public class ViolacaoDeRestricaoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803341658237396573L;

	public ViolacaoDeRestricaoException(String msg) {
		super(msg);
	}

	public ViolacaoDeRestricaoException() {
	}

}
