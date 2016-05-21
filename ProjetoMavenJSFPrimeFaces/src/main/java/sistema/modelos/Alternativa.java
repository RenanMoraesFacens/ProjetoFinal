package sistema.modelos;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Alternativa extends Pergunta implements Serializable  {
	
	private char respostaalt ;

	private static final long serialVersionUID = 1L;
	
	public Alternativa() {
		super();
	}

	public char getRespostaalt() {
		return respostaalt;
	}

	public void setRespostaalt(char respostaalt) {
		this.respostaalt = respostaalt;
	}
	
	

}
