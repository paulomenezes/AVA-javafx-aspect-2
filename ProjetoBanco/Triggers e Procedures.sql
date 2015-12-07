-- fazer uma procedure que verifica após confirmar o botão de matrícula, verifica se tem ao mínimo 3 matérias e no máximo 10
-- fazer uma procedure que quando confirmado a matrícula de uma aluno numa oferta de disciplina, ele aumente a qtd de alunos matriculados nela

use ava;


delimiter |

create trigger atualizarNAlunosOferta after update on matricular for each row -- tanto quanto se matricular quanto sair
	begin
		declare nAlunos int default 0;
		if (new.cpfAluno is not null and new.idOferta is not null) then
			update ofertaDisciplina
				set nAlunos = nAlunos + 1
				where idOferta = new.idOferta;
		end if;
		if (old.cpfAluno is not null and old.idOferta is not null) then
			update ofertaDisciplina
				set nAlunos = nAlunos - 1
				where idOferta = old.idOferta;
		end if;
	end
	|

create trigger atualizarNAlunosCurso after update on aluno for each row -- tanto quanto se matricular quanto sair
	begin
		declare nAlunos int default 0;
		if (new.cpfAluno is not null and new.idCurso is not null) then
			update curso
					set	nAlunos = nAlunos + 1
					where idCurso = new.idCurso;
		end if;
		if (old.cpfAluno is not null and old.idCurso is not null) then
			update curso
				set nAlunos = nAlunos - 1
				where idCurso = old.idCurso;
		end if;
	end
	|

-- quando se cria um novo projeto de pesquisa, ele gera um aviso novo, disponível a todos (público)
create trigger criarAvisoProjeto after insert on projetopesquisa for each row -- tanto quanto se matricular quanto sair
	begin
		declare descr text default null; -- descrição 

        set  descr = concat('Uma nova bolsa foi lançada. ', new.nome, ' é uma bolsa do(a) ', new.organizacao, 
        ' na modalidade ', new.modalidade, ' com ', new.nVagas, 'disponível(is) e bolsa com valor de ', new.valorBolsa);
		INSERT INTO aviso(idRemetente, titulo, descricao, prioridade, dataEnvio, horaEnvio, idDestinatarioO, idDestinatarioU)
        VALUES(NULL, 'Oportunidade', descr, 0, NULL, NULL, NULL, NULL);
	end
	|

create function buscarNomeProjeto (idP int) returns varchar(35) deterministic begin
		declare done int default 0;
		declare titulo varchar(35) default null;
		declare idProj int;
		declare nomeProj varchar(35);
		declare projetoCursor cursor for select idProjeto, nome from projetopesquisa; 
		declare continue handler for not found set done = 1;

		open projetoCursor;
		repeat
			fetch projetoCursor into idProj, nomeProj;
			if (idProj = idP) then
				set titulo = nomeProj;
				set done = 1;
            end if;
			until done
		end repeat;
        close projetoCursor;
        return titulo;
end
|

create function buscarNomeUsuario (cpfU varchar(14)) returns varchar(35) deterministic begin
		declare done int default 0;
		declare nomeUsuario varchar(35) default null;
		declare cpfUser int;
		declare nomeUser varchar(35);
		declare usuarioCursor cursor for select cpf, nome from usuario; 
		declare continue handler for not found set done = 1;

		open usuarioCursor;
		repeat
			fetch usuarioCursor into cpfUser, nomeUser;
			if (cpfUser = cpfU) then
				set nomeUsuario = nomeUser;
				set done = 1;
            end if;
			until done
		end repeat;
        close usuarioCursor;
        return nomeUsuario;
end
|

-- se alguém entra em um proj o n de vagas diminui, se sai, aumenta
create trigger atualizarNVagasProjeto after update on participarprojeto for each row
	begin
		declare texto text;
		if (new.cpfAluno is not null and new.idProjeto is not null) then
			update projetopesquisa
				set nVagas = nVagas - 1
				where idProjeto = new.idProjeto;
			set texto = concat('Parabéns, ' buscarNomeUsuario(new.cpfAluno), 'você está dentro do projeto ', buscarNomeProjeto(new.idProjeto), '!');
		INSERT INTO aviso(idRemetente, titulo, descricao, prioridade, dataEnvio, horaEnvio, idDestinatarioO, idDestinatarioU) 
        VALUES(NULL, 'Vaga conquistada!', texto, 0, NULL, NULL, NULL, new.cpfAluno);-- mensagem específica para o usuário
		end if;
        
        if (old.cpfAluno is not null and old.idProjeto is not null) then
			update projetopesquisa
				set nVagas = nVagas + 1
				where idProjeto = old.idProjeto;
			set texto = concat('O projeto ', buscarNomeProjeto(old.idProjeto), ' tem uma vaga em aberto.');
		INSERT INTO aviso(idRemetente, titulo, descricao, prioridade, dataEnvio, horaEnvio, idDestinatarioO, idDestinatarioU)
        VALUES(NULL, 'Há uma nova vaga', texto, 0, NULL, NULL, NULL, NULL); -- mensagem para todos, pois tem null tanto no ultimo q penultimo
		end if;
	end
	|

-- quando se inicia, está tudo null, tem chamá-la primeiro antes de chamar as outras de atualizar.
create procedure atualizarNAlunosCursoQuandoNull (in idC int) 
	begin
		declare done int default 0;
		declare nAlunos int default 0;
		declare idCA int; -- cursoAlunoCursorLinha

		declare alunoCursor cursor for select idCurso from aluno;
		declare continue handler for not found set done = 1;

		open alunoCursor;
		repeat
			fetch alunoCursor into idCA;
			if (idCA = idC) then
				set nAlunos = nAlunos + 1;
			end if;
			until done
		end repeat;
		update curso
			set qtdAlunos = nAlunos
			where (idC = idCurso);
		close alunoCursor;
	end
	|
    
create procedure atualizarNAlunosOfertaQuandoNull (in idO int) 
	begin
		declare done int default 0;
		declare nAlunos int default 0;
		declare idOM int; -- cursoOfertaCursorLinha

		declare ofertaCursor cursor for select idOferta from matricular;
		declare continue handler for not found set done = 1;

		open ofertaCursor;
		repeat
			fetch ofertaCursor into idOM;
			if (idOM = idO) then
				set nAlunos = nAlunos + 1;
			end if;
			until done
		end repeat;
		update ofertadisciplina
			set qtdAlunos = nAlunos
			where (idO = idOferta);
	end
	|
    

create procedure atualizarAlunos () 
begin
	declare done int default 0;
    declare nOfertaCurso int default 1;
    declare continue handler for not found set done = 1;
    
	repeat
		call atualizarNAlunosCursoQuandoNull(nOfertaCurso);
        set nOfertaCurso = nOfertaCurso + 1;
        if nOfertaCurso = 200 then 
			set done = 1;
        end if;
        until done
	end repeat;
end
|

create procedure atualizarHistorico () 
begin
	declare done int default 0;
    declare nOferta int default 1;
    declare continue handler for not found set done = 1;
    
	repeat
		call calcularMediaDeCadaAlunoNaOferta(nOferta);
        set nOferta = nOferta + 1;
        if nOferta = 200 then 
			set done = 1;
        end if;
        until done
	end repeat;
end
|

delimiter ;
call atualizarAlunos();
call atualizarHistorico();