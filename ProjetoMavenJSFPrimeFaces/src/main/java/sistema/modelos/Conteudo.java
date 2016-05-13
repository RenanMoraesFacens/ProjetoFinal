package sistema.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
@Entity
public class Conteudo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codConteudo;
	private String nome;
	private int ordem;
	@ManyToOne
	private Disciplina disciplina;
	@ManyToMany(mappedBy="conteudo")
	private List<Pergunta> perguntas = new ArrayList<Pergunta>();
	@ManyToMany(mappedBy="conteudos")
	private List<Prova> provas = new ArrayList<Prova>();
}
