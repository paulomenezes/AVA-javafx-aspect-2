-- FUNÇÕES DE MYSQL --

delimiter |
create function verificarSePreRequisitoCumprido (idCurso int, idDisciplina int, idAluno int) returns boolean not deterministic
	begin
		declare done int default 0;
		declare idC int; --  cursoLinha
		declare idD int; -- disciplinaLinha
		declare idPre int; -- préRequisitoLinha
        declare preReqOK boolean;
        declare tudoOK boolean default true;
		declare cursoCursor cursor for select idCurso, dependente, requesito from preRequisito; -- ajeitar lá na tabela, está prerequEsito
		declare continue handler for not found set done = 1;

		open cursoCursor;
		repeat
			fetch cursoCursor into idC, idD, idPre;
			if (idC = idCurso and idD = idDisciplina) then
			set preReqOK =  verificarSePreRequisitoCumpridoOferta(idC, idPre, idAluno); -- idPre não idD porque vai verificar se o pré-requsito está ok
            set tudoOK = tudoOK and preReqOK;
            end if;
			until done
		end repeat;
        close cursoCursor;
        return tudoOK;
	end
	|

-- tem de fazer uma função para verificar se ele está matriculado em uma oferta de uma disciplina
-- transformamr tudo para função
create function verificarSePreRequisitoCumpridoOferta (idCurso int, idDisciplina int, idAluno int) returns boolean not deterministic
	begin
		declare done int default 0;
		declare idC int; -- cursoLinha
		declare idD int; -- disciplinaLinha
        declare idO int; -- ofertaLinha
        declare alunoPassado boolean default false;
        declare cond varchar(25);
		declare ofertaCursor cursor for select idCurso, idDisciplina, idOferta from ofertaDisciplina; -- ajeitar lá na tabela, está prerequEsito
		declare continue handler for not found set done = 1;
        
        open ofertaCursor;
		repeat
			fetch ofertaCursor into idC, idD, idO;
			if (idC = idCurso and idD = idDisciplina) then
				set cond = verificarSePreRequisitoCumpridoHistorico(idAluno, idO);
				if (cond = 'aprovado') then
                 	set alunoPassado = true; 
					set done = 1;
                end if;
            end if;
			until done
		end repeat;
        close ofertaCursor;
        return alunoPassado;
	end
|

create function verificarSePreRequisitoCumpridoHistorico (idAluno int, idOferta int) returns varchar(25) not deterministic-- ver o histórico
	begin
		declare done int default 0;
		declare idA int; -- alunoLinha
		declare idO int; -- ofertaLinha
		declare idC boolean; -- condiçãoLinha
        declare passadoCondicao varchar(25) default 'reprovado';
		declare historicoCursor cursor for select idAluno, idOferta, condicao from historico; 
		declare continue handler for not found set done = 1;

		open historicoCursor;
		repeat
			fetch historicoCursor into idA, idO, idC;
			if (idA = idAluno and idO = idOferta) then 
					if (idC = 'aprovado' or idC = 'aprovado por média') then
						set passadoCondicao = 'aprovado';
					end if;
					set done = 1;
			end if;
			until done
		end repeat;
        close historicoCursor;
        return passadoCondicao;
	end
|