package sistema.modelos;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Prova {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codProva;
	private Date dataAplicacao;
	private String curso;
	private String faculdade;
	private int nivel;
	private double tempo;
	private String turma;
	@ManyToOne
	private Disciplina disciplina;
	@ManyToMany(mappedBy="provas")
	private List<Pergunta> perguntas = new ArrayList<Pergunta>();
	@ManyToMany(mappedBy="provas")
	private List<Conteudo> conteudos = new ArrayList<Conteudo>();
	
}
