package br.senai.sc.ti20122n.pw.mensagensweb.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.senai.sc.ti20122n.pw.mensagensweb.model.entity.Autor;
import br.senai.sc.ti20122n.pw.mensagensweb.util.FacesContextUtil;

@ManagedBean
public class AutorMb {

	private List<Autor>autores;
	private EntityManager entityManager;

	@PostConstruct
	public void init(){
		entityManager = FacesContextUtil.getEntityManager();
	}

	public List<Autor> getAutores() {
		if(autores == null){
			Query query = entityManager.createQuery("select autor from Autor autor",Autor.class);
			autores = query.getResultList();
		}
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public Autor buscarAutorPorId(Long id) {
		return entityManager.find(Autor.class, id);
	}
	
	
}
