package com.ufrpe.ava.negocio.entidades;

	/**
	 * Created by paulomenezes on 20/11/15.
	 */
	public class Usuario {
	    private String cpf;
	    private String nome;
	    private String email;
	    private String foto;
	    private String senha;
	    private int grad;

		public Usuario() {

		}

	    public Usuario(String cpf, String nome, String foto, String email, String senha, int grad) {
			this.cpf = cpf;
			this.nome = nome;
			this.email = email;
			this.foto = foto;
			this.senha = senha;
			this.grad = grad;
		}


		public int getGrad() {
			return grad;
		}

		public void setGrad(int grad) {
			this.grad = grad;
		}

		public String getCPF() {
	        return cpf;
	    }

	    public void setCPF(String CPF) {
	        this.cpf = CPF;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getFoto() {
	        return foto;
	    }

	    public void setFoto(String foto) {
	        this.foto = foto;
	    }

	    public String getSenha() {
	        return senha;
	    }

	    public void setSenha(String senha) {
	        this.senha = senha;
	    }
	    
	    public String getTipo() {
	    	   if (grad == -1)
	    	        return "Coordenador";
	    	  else if (grad == 0)
	    	       return "Professor";
	    	  else if (grad == 1)
	    	       return "Aluno graduação";
	    	  else if (grad == 2)
	    	      return "Aluno pós-graduação";
	    	  return null;
	    }

		@Override
		public String toString() {
			return this.nome;
		}
	    
	    
	
	}

