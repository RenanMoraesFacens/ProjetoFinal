package sistema.beans.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sistema.modelos.Prova;
import sistema.service.ProvaService;



@FacesConverter("converterProva")
public class ProvaConverter implements Converter {

	private ProvaService servico = new ProvaService();
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		
		if (value != null && !value.isEmpty()) {
			
			  for(Prova f : servico.getProvas())
				 if(f.getNomeprova().equals(value))
				  	return f;
					
		}

		return null;
		
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic,
			Object prova) {
		if (prova == null || prova.equals("")) {
			return null;
		} else {
			return ((Prova) prova).getNomeprova();

		}
	}

}
