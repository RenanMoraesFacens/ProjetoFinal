package sistema.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Conteudo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codConteudo;
	private String nome;
	private int ordem;
	
	@ManyToOne
	private Disciplina disciplina;
	
	@OneToMany(mappedBy="conteudo")
	private List<Pergunta> perguntas = new ArrayList<Pergunta>();
	
	@ManyToMany
	@JoinTable(name="TBL_CONTEUDO_PROVA",joinColumns=@JoinColumn(name="conteudo_id"),inverseJoinColumns=@JoinColumn(name="prova_id"))
	private List<Prova> provas = new ArrayList<Prova>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public List<Pergunta> getPerguntas() {
		return perguntas;
	}
	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}
	public List<Prova> getProvas() {
		return provas;
	}
	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}
	
	public void addPergunta(Pergunta pergunta)
	{
		perguntas.add(pergunta);
		
	}
	
	public void addProva(Prova prova)
	{
		provas.add(prova);
		
	}
}
