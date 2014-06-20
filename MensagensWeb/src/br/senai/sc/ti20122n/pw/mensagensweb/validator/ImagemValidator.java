package br.senai.sc.ti20122n.pw.mensagensweb.validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import br.senai.sc.ti20122n.pw.mensagensweb.util.ImagemUtil;

@FacesValidator("br.senai.sc.ti20122n.pw.mensagensweb.validator.ImagemValidator")
public class ImagemValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part) value;
		if (file.getSize() > ImagemUtil.TAMANHO_MAXIMO) {
			msgs.add(new FacesMessage("Arquivo muito grande"));
		}
		if (!ImagemUtil.TIPOS.containsKey(file.getContentType())) {
			msgs.add(new FacesMessage("Formato não permitido."));
		}
		if (!msgs.isEmpty()) {
			throw new ValidatorException(msgs);
		}
	}
}