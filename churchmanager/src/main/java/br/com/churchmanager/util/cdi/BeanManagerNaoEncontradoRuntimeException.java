package br.com.churchmanager.util.cdi;

public class BeanManagerNaoEncontradoRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 8844568445091728640L;

	public BeanManagerNaoEncontradoRuntimeException(String msg) {
		super(msg);
	}

}
