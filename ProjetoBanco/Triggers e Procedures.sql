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
			if (idCA is not null and idCA = idC) then
				set nAlunos = nAlunos + 1;
			end if;
			until done
		end repeat;
		update curso
			set qtdAlunos = nAlunos
			where (idC = idCurso);
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
    
    
delimiter ;
call atualizarNAlunosCursoQuandoNull(1);
call atualizarNAlunosCursoQuandoNull(2);
call atualizarNAlunosCursoQuandoNull(3);
call atualizarNAlunosCursoQuandoNull(4);
call atualizarNAlunosCursoQuandoNull(5);
call atualizarNAlunosCursoQuandoNull(6);
call atualizarNAlunosCursoQuandoNull(7);
call atualizarNAlunosCursoQuandoNull(8);
call atualizarNAlunosCursoQuandoNull(9);

call atualizarNAlunosOfertaQuandoNull(1);
call atualizarNAlunosOfertaQuandoNull(2);
call atualizarNAlunosOfertaQuandoNull(3);
call atualizarNAlunosOfertaQuandoNull(4);
call atualizarNAlunosOfertaQuandoNull(5);
call atualizarNAlunosOfertaQuandoNull(6);
call atualizarNAlunosOfertaQuandoNull(7);