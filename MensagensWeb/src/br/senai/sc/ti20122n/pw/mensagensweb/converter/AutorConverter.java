package br.senai.sc.ti20122n.pw.mensagensweb.converter;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.senai.sc.ti20122n.pw.mensagensweb.mb.AutorMb;
import br.senai.sc.ti20122n.pw.mensagensweb.model.entity.Autor;

@FacesConverter(forClass=Autor.class)
public class AutorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		ELContext elContext = facesContext.getELContext();
		ELResolver elResolver = elContext.getELResolver();
		
		AutorMb autorMb = (AutorMb) elResolver.getValue(elContext, null, "autorMb");
		
		Long id = Long.parseLong(value);
		return autorMb.buscarAutorPorId(id);
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		Autor autor = (Autor) object;
		return String.valueOf(autor.getId());
	}

}
