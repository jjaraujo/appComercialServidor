package app.jm.funcional.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import app.jm.funcional.controller.FuncoesSql;
import app.jm.funcional.controller.NaoUsarNaBase;
import app.jm.funcional.controller.VariaveisControle;
import app.jm.funcional.controller.funcoesGerais.FuncoesGerais;
import app.jm.funcional.controller.funcoesGerais.VerificaTipos;
import app.jm.funcional.model.entidades.Entidade;

@SuppressWarnings("serial")
public abstract class Tabela implements Serializable {

	/**
	 * Informar instancias da tabela que contÈm valores a serem inseridos no banco
	 */
	
	@NaoUsarNaBase
	private HashMap<String, Object> map;
		
	public long id;

	public long empresaClienteId = VariaveisControle.empresaCliente == null ? 0 : VariaveisControle.empresaCliente.getId() ;

	public Calendar dataExclusao;
	
	@NaoUsarNaBase
	private boolean isPegandoAtributosSuperClass;
	
	public long idAnterior;

	public boolean getPrecisaRegistroInicial() {
		return false;
	}
	
	/**Deve ser sobrescrito caso a tabela tenha ids definidos no banco*/
	public long getId() {
		id = id == 0 ? FuncoesGerais.getIdUnico() : id;
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Calendar dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public long getEmpresaClienteId() {
		return empresaClienteId;
	}

	public void setEmpresaClienteId(long empresaCliente) {
		this.empresaClienteId = empresaCliente;
	}

	/**
	 * Busca o nome da classe, que ser· sempre o mesmo da tabela.
	 * 
	 * @param minusculo
	 *            - retorna o nome completamente minusculo
	 */
	public String getNomeTabela(boolean minusculo) {
		String nome = this.getClass().getSimpleName();
		return minusculo ? nome.substring(0, 1).toLowerCase().concat(nome.substring(1)) : nome;
	}
	
	public String getNomeCompletoTabela() {
		String nome = this.getClass().getName();
		return nome.replace("class ","");
	}
	

	/** @return Retorna o caminho da tabela no banco de dados */
	public String getCaminhoTabelaBanco(int tipoSql) {
		String nome = this.getClass().getSimpleName();
		if (tipoSql == FuncoesSql.SQL_SERVER) {
			return FuncoesSql.caminhoBanco() + "." + nome;
		}
		return nome;
	}
	
	 public void setMapAtributos(HashMap<String, Object> map){
	       for(String s :map.keySet()) {
	           Field field = null;
	           try {
	                field = this.getClass().getDeclaredField(s);
	           } catch (NoSuchFieldException e) {
	               try {
	                   field = this.getClass().getField(s);
	               } catch (NoSuchFieldException e1) {
	                   e1.printStackTrace();
	               }
	           }
	           try {
	               field.setAccessible(true);
	               field.set(this,map.get(s));
	           } catch (IllegalAccessException e) {
	               e.printStackTrace();
	           }
	       }
	    }

	/**
	 * @param carregaMapNovamente - se true, os dados da tabela ser√£o carregados novamente no map
	 * @return Retorna o map com todos os atributos da classe e atributos  e seus valores
	 */
	  public HashMap<String, Object> getMapAtributos(boolean carregaMapNovamente) {

	        // retornar· o map da mem√≥ria se ele j· tiver sido criado uma vez ou se nao
	        if (map != null && !carregaMapNovamente && !isPegandoAtributosSuperClass ) {
	            return map;
	        }

	        map = isPegandoAtributosSuperClass ? map : new HashMap<String, Object>();

 //******** se em 30/07/2018 isso  estiver comentado, pode excluir
	     //   map.put("id",0);
        //	map.put("dataExclusao",FuncoesGerais.getCalendarNulo());
        //	map.put("empresaCliente",getEmpresaCliente());
	        
	        Field[] fields = getClass().getDeclaredFields();
	        if(!isPegandoAtributosSuperClass) {
	            isPegandoAtributosSuperClass = true;
	            getMapAtributos(false);
	            fields = getClass().getFields();
	       //     Log.i("Fields super", fields.length +"");
	        }
	        isPegandoAtributosSuperClass = false;
	       
//******** se em 30/07/2018 isso  estiver comentado, pode excluir
	        //caso o id da tabela tenha valor > 0, ser· adicionado no map o valor atual
//	        int id = getId() == 0 ? 0 : getId();
//	        map.put(getIdNome(), id); 

	        // caso a dataExclusao nao tenha nenhum valor, ser· criado um valor vazio
//	        Calendar dataExclusao = getDataExclusao() == null ? FuncoesGerais.getCalendarNulo() : getDataExclusao();
//	        map.put(getDataExclusaoNome(), dataExclusao);
//********
	        
	        for (Field field : fields) {
	            field.setAccessible(true);

	            //verifica casos onde o field nao deve entrar no map 
	            int modifiers = field.getModifiers();
	            if (Modifier.isStatic(modifiers) 
	            		|| field.isAnnotationPresent(NaoUsarNaBase.class)
	            		|| FuncoesGerais.fieldOverrideEhAnotadoNaoUsar(field.getName(),this)) {
	                continue;
	            }
	            
	            try {
	                Object objectField = null;
	                try {
	                    objectField = !FuncoesGerais.classIsFinal(field.getType()) ? field.getType().newInstance() : null;
	                } catch (InstantiationException e) {
	                    // variaveis que nao podem ser instanciadas
	                        objectField = field.getType().getName().toLowerCase().contains("boolean") ? field.get(this) : null;
	                   if(objectField == null)
	                        e.printStackTrace();
	                }

	                // caso o field seja uma entidade, ser· adicionado o  na chave no map para ficar igual ao banco
	                if (VerificaTipos.isTabela(field, this)) {

	                    Tabela object = FuncoesGerais.getFieldTypeTabela(this, field) == null ?
	                            FuncoesGerais.getNovaInstanciaTabela(field) :
	                            FuncoesGerais.getFieldTypeTabela(this, field);
	                    object.getMapAtributos(false);
	                    map.put(field.getName(),object);// + FuncoesGerais.prefixoChaveEstrangeira(), object);

	                } else {
	                    if (field.get(this) == null) { // neste IF, valores que podem estar nulos
	                        if (VerificaTipos.isBoolean(objectField, field)) {
	                            map.put(field.getName(), FuncoesGerais.booleanToint(field.getBoolean(this)));
	                        } else if (VerificaTipos.isString(objectField, field)) {
	                            map.put(field.getName(), "");
	                        } else if (VerificaTipos.isList(objectField, field)) {
	                            map.put(field.getName(), new ArrayList<>());
	                        } else if (VerificaTipos.isCalendar(objectField, field)) {
	                            map.put(field.getName(), FuncoesGerais.getCalendarNulo());
	                        } else {
	                            map.put(field.getName(), 0);
	                        }
	                    } else {
	                        map.put(field.getName(), field.get(this));
	                    }
	                }
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        }
	        return map;
	    }
	public String getIdNome() {
		return "id";
	}
	
	public String getNomeTabelaNomeId() {
		return getNomeTabela(false) + "." + getIdNome() ;
	}

	public String getDataExclusaoNome() {
		return "dataExclusao";
	}

	public String prefixoDataExclusao() {
		return "dataExclusao";
	}

	/**
	 * Verifica se a tabela È uma entidade
	 */

	public boolean isEntidade() {
		try {
			Entidade e = (Entidade) this;
			return true;
		} catch (ClassCastException e) {
			return false;
		}
	}

	/**
	 * Busca o nome de todos os atributos do map e adiciona em uma unica linha,
	 * separados por virgula. Ideal em uso de SQL
	 */
	public String getNomesAtibutosInLinha() {
		Set<String> set = getMapAtributos(false).keySet();
		String array = "";
		for (String s : set) {
			array += ", " + s;
		}
		return array;
	}

	/** Busca o nome de todos os atributos do map */

	public String[] getNomesAtributos() {
		Object[] o = getMapAtributos(false).keySet().toArray();
		String[] strings = new String[o.length];
		for (int i = 0; i < o.length; i++) {
			strings[i] = o[i] + "";
		}
		return strings;
	}
	
	public void anulaMapAtributo(){
        map = null;
    }
	
	public boolean usaInsert() {
		return true;
	}
	
	public List<String> alterTableAposCriacao(){
		return null;
	}

	public long getIdAnterior() {
		return idAnterior;
	}

	public void setIdAnterior(long idAnterior) {
		this.idAnterior = idAnterior;
	}
	
	public List<Tabela> getListValoresIniciais(){
		
		return null;
	}


}
