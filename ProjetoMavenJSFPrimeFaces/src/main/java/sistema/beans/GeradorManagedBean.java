package sistema.beans;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import sistema.modelos.Alternativa;
import sistema.modelos.Dissertativa;
import sistema.modelos.Pergunta;
import sistema.modelos.Prova;
import sistema.modelos.Verdadeiro;
import sistema.service.ProvaService;

@ManagedBean(name = "geradorManagedBean")
@ViewScoped
public class GeradorManagedBean {

	private Prova prova;
	private List<Prova> provas;
	private ProvaService provaService = new ProvaService();
	
	public Prova getProva() {
		return prova;
	}
	public void setProva(Prova prova) {
		this.prova = prova;
	}
	public List<Prova> getProvas() {
		if(provas == null)
		{
			provas = provaService.getProvas();
		}
		return provas;
	}
	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}
	public ProvaService getProvaService() {
		return provaService;
	}
	public void setProvaService(ProvaService provaService) {
		this.provaService = provaService;
	}
	
	
	public void GeradorPDF()
	{
		Document pdf = new Document();
		
		try
		{
          PdfWriter.getInstance(pdf, new FileOutputStream("C:\\prova"+ prova.getCodigo() + ".pdf"));
          pdf.open();
          
          pdf.add(new Paragraph("                                                                       Avaliação" ));
          pdf.add(new Paragraph("Faculdade: " + prova.getFaculdade()));
          pdf.add(new Paragraph("Curso: " + prova.getCurso()));
          pdf.add(new Paragraph("Turma: " + prova.getTurma()));
          pdf.add(new Paragraph("Data: " + prova.getDataAplicacao().getDate() 
        		          		   +"/"+(prova.getDataAplicacao().getMonth()+1)+"/"+(prova.getDataAplicacao().getYear()-100))); 

          pdf.add(new Paragraph("Disciplina : " + prova.getDisciplina().getNome()));
          
          
          pdf.add(Chunk.NEWLINE);
          pdf.add(Chunk.NEWLINE);
          pdf.add(Chunk.NEWLINE);
          
          
          for(int i = 0; i < prova.getPerguntas().size(); i++)
          {
        	  
        	  pdf.add(new Paragraph(""+(i + 1)+") " +  
                 prova.getPerguntas().get(i).getEnunciado())); 
        	  pdf.add(Chunk.NEWLINE);
              pdf.add(Chunk.NEWLINE);
              
          }
          
          pdf.add(Chunk.NEWLINE);
          pdf.add(Chunk.NEWLINE);
          pdf.add(new Paragraph("      Boa Sorte"));
		}
		catch(DocumentException e)
		{
			System.err.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
		pdf.close();
	}
	
	public void GeradorGabaritoPDF()
	{
		Document pdf = new Document();
		
		try
		{
          PdfWriter.getInstance(pdf, new FileOutputStream("C:\\gabarito"+ prova.getCodigo() + ".pdf"));
          pdf.open();
          
          pdf.add(new Paragraph("                                                                       Gabarito" ));
          pdf.add(new Paragraph("Faculdade: " + prova.getFaculdade()));
          pdf.add(new Paragraph("Curso: " + prova.getCurso()));
          pdf.add(new Paragraph("Turma: " + prova.getTurma()));
          pdf.add(new Paragraph("Data: " + prova.getDataAplicacao().getDate() 
        		          		   +"/"+(prova.getDataAplicacao().getMonth()+1)+"/"+(prova.getDataAplicacao().getYear()-100))); 

          pdf.add(new Paragraph("Disciplina : " + prova.getDisciplina().getNome()));
          
          
          pdf.add(Chunk.NEWLINE);
          pdf.add(Chunk.NEWLINE);
          pdf.add(Chunk.NEWLINE);
          
          
          for(int i = 0; i < prova.getPerguntas().size(); i++)
          {
        	  if(prova.getPerguntas().get(i) instanceof Alternativa)
        	  {
        		  Alternativa alt = (Alternativa)prova.getPerguntas().get(i);
        	     pdf.add(new Paragraph(""+(i + 1)+") " +  
                    alt.getRespostaalt())); 
        	     pdf.add(Chunk.NEWLINE);
                 pdf.add(Chunk.NEWLINE);
        	  }
        	  
        	  if(prova.getPerguntas().get(i) instanceof Dissertativa)
        	  {
        		  Dissertativa dis = (Dissertativa)prova.getPerguntas().get(i);
        	     pdf.add(new Paragraph(""+(i + 1)+") " +  
                    dis.getRespostadis())); 
        	     pdf.add(Chunk.NEWLINE);
                 pdf.add(Chunk.NEWLINE);
        	  }
        	  
        	  if(prova.getPerguntas().get(i) instanceof Verdadeiro)
        	  {
        		 Verdadeiro verd = (Verdadeiro)prova.getPerguntas().get(i);
        	     pdf.add(new Paragraph(""+(i + 1)+") " +  
                    verd.getRespostavf())); 
        	     pdf.add(Chunk.NEWLINE);
                 pdf.add(Chunk.NEWLINE);
        	  }
          }
          
		}
		catch(DocumentException e)
		{
			System.err.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
		pdf.close();
	}
}
