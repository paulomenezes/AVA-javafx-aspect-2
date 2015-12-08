-- fazer uma procedure que verifica após confirmar o botão de matrícula, verifica se tem ao mínimo 3 matérias e no máximo 10

use ava;
-- só para adi

DELIMITER |
CREATE TRIGGER ava.salvarAlunoProjeto
AFTER INSERT ON solicitacaoprojeto
FOR EACH ROW
BEGIN
	IF(new.estado = 1) THEN
		
        INSERT INTO ava.participarprojeto(cpfAluno,idProjeto)VALUES(new.cpfAluno,new.idProjeto);
        DELETE FROM ava.solicitacaoprojeto WHERE idSolicitacao = new.idSolicitacao;
    END IF;
		
END|


delimiter |

-- TRIGGER

create trigger inserirNotaAposMatricularAluno after insert on matricular for each row begin
-- cria um 'objeto' nota para poder comportar as notas do aluno numa 
-- oferta de disciplina após confirmado(matriculado)
	if (new.cpfAluno is not null and new.idOferta is not null) then
		INSERT INTO nota(cpfAluno, idOferta, nota1, nota2, nota3, notaFinal)
			VALUES(new.cpfAluno, new.idOferta, null, null, null, null);
	end if;
	end
|

-- toda vez que alterar alguma coisa na tabela nota, as notas, logicamente, ele chama o procedimento de 
-- calcular...Média (outro arquivo) que calcula a média para todos os alunos daquela oferta, consequentemente
-- a do cara com a nota alterada 
-- põe lá em histórico ()

-- OBS: o controle de fluxo é feito no procedimento 
create trigger calcularMedia after update on nota for each row begin
	if (new.idOferta is not null) then
		call calcularMediaDeCadaAlunoNaOferta(new.idOferta);
	end if;
end
|
create trigger inserirAlunoNaOfertaEmHistorico after insert on nota for each row begin
-- cria um 'objeto' 'historico' para poder comportar a média e situação
-- de um aluno numa oferta de disciplina após inserido na nota
	if (new.cpfAluno is not null and new.idOferta is not null) then
		INSERT INTO historico(cpfAluno, idOferta, condicao, media)
			VALUES(new.cpfAluno, new.idOferta, null, null);
	end if;
end
|

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
    
    -- se alguém entra em um proj o n de vagas diminui, se sai, aumenta
create trigger atualizarNVagasProjeto after update on participarprojeto for each row
	begin
		declare texto text;
        declare texto2 text;
		if (new.cpfAluno is not null and new.idProjeto is not null) then
			update projetopesquisa
				set nVagas = nVagas - 1
				where idProjeto = new.idProjeto;
                set texto2 = buscarNomeUsuario(new.cpfAluno);
			set texto = concat('Parabéns ', texto2, ' você está dentro do projeto ', buscarNomeProjeto(new.idProjeto), '!');
		INSERT INTO aviso(idRemetente, titulo, descricao, prioridade, dataEnvio, horaEnvio, idDestinatarioO, idDestinatarioU) 
        VALUES(NULL, 'Vaga conquistada!', texto, 0, NULL, NULL, NULL, new.cpfAluno);-- mensagem específica para o usuário
		end if;
        
        if (old.cpfAluno is not null and old.idProjeto is not null) then
			update projetopesquisa
				set nVagas = nVagas + 1
				where idProjeto = old.idProjeto;
                set texto2 =  buscarNomeProjeto(old.idProjeto);
			set texto = concat('O projeto ', texto2, ' tem uma vaga em aberto.');
		INSERT INTO aviso(idRemetente, titulo, descricao, prioridade, dataEnvio, horaEnvio, idDestinatarioO, idDestinatarioU)
        VALUES(NULL, 'Há uma nova vaga', texto, 0, NULL, NULL, NULL, NULL); -- mensagem para todos, pois tem null tanto no ultimo q penultimo
		end if;
	end
	|

-- PROCEDURE

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



create procedure adicionarAlunoAOferta(in cpfAl varchar(14), in idO int, in dataMatr date, in numProt int) begin
-- numProt pode ser gerado aleatoriamente com até 20 caraceteres alfanuméricos lá no java
	if (cpfAl is not null and idO is not null and idO <> 0) then
		INSERT INTO matricular(cpfAluno, idOferta, dataMatricula, numeroProtocolo)
			VALUES(cpfAl, idO, dataMatr, numProt);
	end if;
end
|

-- para professor ministra uma oferta
create procedure adicionarProfessorAOferta(in cpfProf varchar(14), in idO int) begin
	if (cpfProf is not null and idO is not null and idO <> 0) then
		INSERT INTO ministraroferta(idOferta, cpfProfessor)
			VALUES(idO, cpfProf);
	end if;
end
|

-- para aluno estar vinculado a um projeto
create procedure adicionarAlunoAoProjeto (in cpfAl varchar(14), in idP int) begin
	if (cpfAl is not null and idP is not null and idP <> 0) then
		INSERT INTO projetopesquisa(cpfAluno, idProjeto)
			VALUES(cpfAl, idP);
	end if;
end
|

-- para adicionar uma nota ao aluno, não necessita dizer qual é (1ª, 2ª, 3ª), faz automaticamente
create procedure adicionarNotaAAluno (in cpfAl varchar(14), in idOf int, in nota double) begin
		declare done int default 0;
		declare cpfA varchar(14);
        declare idO int;
		declare notaP double;
        declare notaS double;
        declare notaT double;
        declare notaF double;

		declare notaCursor cursor for select cpfAluno, idOferta, nota1, nota2, nota3, notaFinal from nota;
		declare continue handler for not found set done = 1;
        
        open notaCursor;
        repeat
        fetch notaCursor into cpfA, idO, notaP, notaS, notaT, notaF;
        if (idO = idOf and cpfA = cpfAl) then
			if (notaP is null) then
				set notaP = nota, done = 1;
			else if (notaS is null) then
				set notaS = nota, done = 1;
			else if (notaT is null) then
				set notaT = nota, done = 1;
			else if (notaF is null) then
				set notaF = nota, done = 1;
			end if; -- não sei pq tanto end if, mas fica sem indicar erro assim, coisas da vida
			end if;
            end if;
            end if;
            end if;
        until done
        end repeat;
end
|

-- FUNCTION


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
delimiter ;
call atualizarAlunos();
call atualizarHistorico();