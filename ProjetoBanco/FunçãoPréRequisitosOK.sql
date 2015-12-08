-- FUNÇÕES DE MYSQL --

delimiter |
-- * (não usado id/cpf... para evitar confusão no código com a coluna da tabela de mesmo nome)

-- passa o codCurso* , idDisciplina e cpfAluno
-- ele procura ba tabela prerequisito quando o codCurso e  idDisciplina são iguais
create function verificarSePreRequisitoCumprido (codCurso int, idDisciplina int, cpfAluno int) returns boolean not deterministic
	begin
		declare done int default 0;
		declare idC int; --  cursoLinha
		declare idD int; -- disciplinaLinha
		declare idPre int; -- préRequisitoLinha
        declare preReqOK boolean;
        declare tudoOK boolean default true;
		declare cursoCursor cursor for select idCurso, dependente, requesito from preRequisito; 
		declare continue handler for not found set done = 1;

		open cursoCursor;
		repeat
			fetch cursoCursor into idC, idD, idPre;
			if (idC = codCurso and idD = idDisciplina) then
			set preReqOK =  verificarSePreRequisitoCumpridoOferta(idC, idPre, cpfAluno); -- idPre não idD porque vai verificar se o pré-requsito está ok
            set tudoOK = tudoOK and preReqOK;
            end if;
			until done
		end repeat;
        close cursoCursor;
        return tudoOK;
	end
	|


create function verificarSePreRequisitoCumpridoOferta (codCurso int, idDisc int, idAl int) returns boolean not deterministic
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
			if (idC = codCurso and idD = idDisc) then
				set cond = verificarSePreRequisitoCumpridoHistorico(idAl, idO);
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

create function verificarSePreRequisitoCumpridoHistorico (idAl int, idOf int) returns varchar(25) not deterministic-- ver o histórico
	begin
		declare done int default 0;
		declare idA int; -- alunoLinha
		declare idO int; -- ofertaLinha
		declare idC boolean; -- condiçãoLinha
        declare passadoCondicao varchar(25) default 'reprovado';
		declare historicoCursor cursor for select cpfAluno, idOferta, condicao from historico; 
		declare continue handler for not found set done = 1;

		open historicoCursor;
		repeat
			fetch historicoCursor into idA, idO, idC;
			if (idA = idAl and idO = idOf) then 
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