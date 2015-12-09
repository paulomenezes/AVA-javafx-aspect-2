delimiter |
-- * (não usado id/cpf... para evitar confusão no código com a coluna da tabela de mesmo nome)

-- passa o codCurso* , idDisciplina e cpfAluno
-- ele procura ba tabela prerequisito quando o codCurso e  idDisciplina são iguais
create function verificarSePreRequisitoCumprido (idDisciplina int, cpfAluno int) returns boolean not deterministic
	begin
		declare done int default 0;
		declare idD int; -- disciplinaLinha
		declare idPre int; -- préRequisitoLinha
        declare preReqOK boolean;
        declare tudoOK boolean default true;
		declare preCursor cursor for select dependente, requesito from preRequisito; 
		declare continue handler for not found set done = 1;

		open preCursor;
		repeat
			fetch cursoCursor into idD, idPre;
			if (idD = idDisciplina) then
			set preReqOK =  verificarSePreRequisitoCumpridoOferta(idPre, cpfAluno); -- idPre não idD porque vai verificar se o pré-requsito está ok
            set tudoOK = tudoOK and preReqOK;
            end if;
			until done
		end repeat;
        close preCursor;
        return tudoOK;
	end
	|
create function verificarSePreRequisitoCumpridoOferta (idDisc int, cpfAl int) returns boolean not deterministic
	begin
		declare done int default 0;
		declare idD int; -- disciplinaLinha
        declare idO int; -- ofertaLinha
        declare alunoPassado boolean default false;
        declare cond varchar(25);
		declare ofertaCursor cursor for select idDisciplina, idOferta from ofertaDisciplina; -- ajeitar lá na tabela, está prerequEsito
		declare continue handler for not found set done = 1;
        
        open ofertaCursor;
		repeat
			fetch ofertaCursor into idD, idO;
			if (idD = idDisc) then
				set cond = verificarSePreRequisitoCumpridoHistorico(cpfAl, idO);
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

create function verificarSePreRequisitoCumpridoHistorico (cpfAl int, idOf int) returns varchar(25) not deterministic-- ver o histórico
	begin
		declare done int default 0;
		declare cpfA int; -- alunoLinha
		declare idO int; -- ofertaLinha
		declare idC varchar(25); -- condiçãoLinha
        declare passadoCondicao varchar(25) default 'reprovado'; -- se ele não pagou é reprovado
		declare historicoCursor cursor for select cpfAluno, idOferta, condicao from historico; 
		declare continue handler for not found set done = 1;

		open historicoCursor;
		repeat
			fetch historicoCursor into cpfA, idO, idC;
			if (cpfA = cpfAl and idO = idOf) then 
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